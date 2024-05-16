package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import data_classes.History;
import data_classes.Invoice;
import db_queries.GetLastInvoiceId;
import db_queries.InsertHistory;
import db_queries.InsertInvoice;
import minisale.Database;
import minisale.IIndexed;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

class HistoryRecord implements IIndexed {
	int index;
	History history;

	HistoryRecord(int index, History history) {
		this.index = index;
		this.history = history;
	}

	public int getIndex() {
		return this.index;

	}
}

public class CreateInvoiceFrame extends JFrame {

	private CreateInvoiceFrame self;
	private Invoice invoice;
	private JPanel contentPane;
	private ArrayList<HistoryRecord> historyRecords;
	private ListPanel<HistoryPanel> listPanel;
	private JScrollPane scrollPane;
	private JLabel totalAmtValue;

	/**
	 * Create the frame.
	 */
	public CreateInvoiceFrame() {
		this.self = this;
		this.historyRecords = new ArrayList<>();

		setTitle("Create Invoice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel invoiceIdLbl = new JLabel("Invoice 1");
		invoiceIdLbl.setFont(new Font("Arial", Font.BOLD, 18));
		invoiceIdLbl.setBounds(10, 11, 150, 30);
		contentPane.add(invoiceIdLbl);

		listPanel = new ListPanel<>(10);
		listPanel.build();
		this.scrollPane = new JScrollPane(listPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.scrollPane.setBounds(10, 86, 416, 196);

		contentPane.add(scrollPane);

		JButton addBtn = new JButton("Add");

		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int currentIndex = historyRecords.size() + 1;
				System.out.println("addBtn: " + currentIndex);
				AddHistoryFrame frame = new AddHistoryFrame(self, currentIndex);
				frame.setVisible(true);
			}
		});
		addBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		addBtn.setBounds(326, 12, 100, 30);
		contentPane.add(addBtn);

		JLabel totalAmtLbl = new JLabel("Total Amt:");
		totalAmtLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		totalAmtLbl.setBounds(10, 300, 80, 16);
		contentPane.add(totalAmtLbl);

		totalAmtValue = new JLabel("0");
		totalAmtValue.setFont(new Font("Arial", Font.PLAIN, 14));
		totalAmtValue.setBounds(100, 300, 120, 16);
		contentPane.add(totalAmtValue);

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long currentMillis = System.currentTimeMillis();
				Date createdAt = new Date(currentMillis);

				float totalAmt = Float.parseFloat(totalAmtValue.getText());
				InsertInvoice insertInvoice = new InsertInvoice(totalAmt, createdAt);

				insertInvoice.runOnSuccess(() -> {

					// last invoice id query start here
					GetLastInvoiceId idQuery = new GetLastInvoiceId();
					idQuery.runOnSuccess((id) -> {
						// insert histories start here
						for (HistoryRecord historyRecord : historyRecords) {
							InsertHistory insertHistory = new InsertHistory(id, historyRecord.history.product.id,
									historyRecord.history.product.price, historyRecord.history.quantity,
									historyRecord.history.product.discount);

							insertHistory.runOnSuccess(() -> {
							});
							insertHistory.runOnFail((reason) -> System.out.println(reason));

							Database.execute(insertHistory);
						}
						// insert histories end here
					});

					idQuery.runOnFail((reason) -> System.out.println(reason));
					Database.execute(idQuery);
					// last invoice id query end here
				});

				dispose();
				insertInvoice.runOnFail((reason) -> System.out.println(reason));

				Database.execute(insertInvoice);

			}
		});
		saveBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		saveBtn.setBounds(168, 347, 100, 35);
		contentPane.add(saveBtn);

		JLabel productLbl = new JLabel("Product");
		productLbl.setBounds(20, 61, 49, 14);
		contentPane.add(productLbl);

		JLabel quantityLbl = new JLabel("Quantity");
		quantityLbl.setBounds(170, 61, 49, 14);
		contentPane.add(quantityLbl);

		JLabel amountLbl = new JLabel("Amount");
		amountLbl.setBounds(245, 61, 49, 14);
		contentPane.add(amountLbl);

	}

	void addHistory(HistoryRecord record) {
		this.historyRecords.add(record);
		int index = record.index;
		HistoryPanel panel = new HistoryPanel(this, record, index);
		this.listPanel.addPanel(panel);
		this.scrollPane.updateUI();

		float totalAmt = Float.parseFloat(this.totalAmtValue.getText());
		float historyAmt = record.history.amount * record.history.quantity;
		totalAmt += historyAmt;
		this.totalAmtValue.setText(Float.toString(totalAmt));
	}

	void deleteHistoryByIndex(int index) {
		HistoryRecord record = null;
		System.out.println("deleteByIndex: " + index);

		for (int i = 0; i < this.historyRecords.size(); i++) {
			HistoryRecord currentRecord = this.historyRecords.get(i);
			if (currentRecord.getIndex() == index) {
				record = this.historyRecords.get(i);
				break;
			}
		}

		float totalAmt = Float.parseFloat(this.totalAmtValue.getText());
		float historyAmt = record.history.amount * record.history.quantity;
		totalAmt -= historyAmt;
		this.totalAmtValue.setText(Float.toString(totalAmt));

		this.historyRecords.remove(record);
		this.listPanel.deletePanel(index);
		this.scrollPane.updateUI();

	}
}

class HistoryPanel extends JPanel implements IIndexed {

	private int index;

	HistoryPanel(CreateInvoiceFrame parent, HistoryRecord record, int index) {
		this.index = index;

		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setPreferredSize(new Dimension(396, 400));
		this.setBounds(10, 10, 396, 40);
		this.setLayout(null);

		JLabel productName = new JLabel(record.history.product.name);
		productName.setBounds(10, 7, 100, 25);
		productName.setFont(new Font("Arial", Font.PLAIN, 12));
		this.add(productName);

		JLabel productQuantity = new JLabel(Integer.toString(record.history.quantity));
		productQuantity.setBounds(180, 7, 80, 25);
		productQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
		this.add(productQuantity);

		JLabel productTotalAmount = new JLabel(Float.toString(record.history.amount));
		productTotalAmount.setBounds(230, 7, 200, 25);
		productTotalAmount.setFont(new Font("Arial", Font.PLAIN, 14));
		this.add(productTotalAmount);

		JButton deleteBtn = new JButton("x");
		deleteBtn.setBounds(335, 7, 50, 25);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.deleteHistoryByIndex(index);
			}
		});
		this.add(deleteBtn);
	}

	@Override
	public int getIndex() {
		return this.index;
	}
}
