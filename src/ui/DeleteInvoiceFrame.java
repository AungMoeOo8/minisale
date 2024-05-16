package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data_classes.Invoice;
import db_queries.DeleteHistoryByInvoiceId;
import db_queries.DeleteInvoiceById;
import db_queries.GetHistoriesByInvoiceId;
import db_queries.GetInvoiceById;
import minisale.Database;
import minisale.Util;

import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteInvoiceFrame extends JFrame {

	private JPanel contentPane;
	private JTextField invoiceIdTxt;
	private InvoicePanel invoicePanel;
	private JButton deleteBtn;

	/**
	 * Create the frame.
	 */
	public DeleteInvoiceFrame() {
		setTitle("Delete Invoice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel invoiceIdLbl = new JLabel("Invoice ID");
		invoiceIdLbl.setFont(new Font("Arial", Font.BOLD, 14));
		invoiceIdLbl.setBounds(10, 25, 70, 35);
		contentPane.add(invoiceIdLbl);

		invoiceIdTxt = new JTextField();
		invoiceIdTxt.setFont(new Font("Arial", Font.PLAIN, 14));
		invoiceIdTxt.setMargin(new Insets(6, 6, 6, 6));
		invoiceIdTxt.setBounds(90, 25, 200, 35);
		contentPane.add(invoiceIdTxt);
		invoiceIdTxt.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(128, 128, 128));
		separator.setBounds(10, 81, 416, 2);
		contentPane.add(separator);

		invoicePanel = new InvoicePanel();
		invoicePanel.setBounds(0, 94, 436, 107);
		getContentPane().add(invoicePanel);

		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int id = Integer.parseInt(invoiceIdTxt.getText());
					GetInvoiceById gIBI = new GetInvoiceById(id);
					gIBI.runOnSuccess((invoice) -> {
						if (invoice == null) {
							deleteBtn.setEnabled(false);
						} else {
							deleteBtn.setEnabled(true);
						}
						invoicePanel.setInvoice(invoice);
					});
					gIBI.runOnFail((reason) -> {
						JOptionPane.showMessageDialog(null, "Something wrong with the system!");
					});

					Database.execute(gIBI);
				} catch (NumberFormatException excep) {
					JOptionPane.showMessageDialog(null, "Invoice ID must be an integer!");
				}
			}
		});
		searchBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		searchBtn.setBounds(346, 25, 80, 35);
		contentPane.add(searchBtn);

		this.deleteBtn = new JButton("Delete");
		this.deleteBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		this.deleteBtn.setBounds(178, 217, 80, 35);
		this.deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					int invoiceId = Integer.parseInt(invoiceIdTxt.getText());
					DeleteHistoryByInvoiceId deleteHistoryRequest = new DeleteHistoryByInvoiceId(invoiceId);

					deleteHistoryRequest.runOnSuccess(() -> {
						DeleteInvoiceById deleteInvoiceRequest = new DeleteInvoiceById(invoiceId);
						deleteInvoiceRequest.runOnSuccess(() -> dispose());
						deleteInvoiceRequest.runOnFail(
								(reason) -> JOptionPane.showMessageDialog(null, "Something wrong with the system!"));
						Database.execute(deleteInvoiceRequest);
					});

					deleteHistoryRequest.runOnFail(
							(reason) -> JOptionPane.showMessageDialog(null, "Something wrong with the system!"));

					Database.execute(deleteHistoryRequest);

				} catch (NumberFormatException excep) {
					JOptionPane.showMessageDialog(null, "Invoice ID must be an integer!");
				}

			}
		});
		contentPane.add(deleteBtn);

	}
}

class InvoicePanel extends JPanel {
	private JLabel amountTxt;
	private JLabel dateTxt;
	private Invoice invoice;
	private JButton showBtn;

	InvoicePanel() {
		this.setLayout(null);

		JLabel amountLbl = new JLabel("Amount :");
		amountLbl.setFont(new Font("Arial", Font.BOLD, 14));
		amountLbl.setBounds(10, 10, 70, 35);
		this.add(amountLbl);

		this.amountTxt = new JLabel("");
		this.amountTxt.setFont(new Font("Arial", Font.BOLD, 14));
		this.amountTxt.setBounds(90, 10, 200, 35);
		this.add(this.amountTxt);

		JLabel dateLbl = new JLabel("Date      :");
		dateLbl.setFont(new Font("Arial", Font.BOLD, 14));
		dateLbl.setBounds(10, 50, 70, 35);
		this.add(dateLbl);

		this.dateTxt = new JLabel("");
		this.dateTxt.setFont(new Font("Arial", Font.BOLD, 14));
		this.dateTxt.setBounds(90, 50, 200, 35);
		this.add(this.dateTxt);

		this.showBtn = new JButton("Show");
		this.showBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		this.showBtn.setBounds(346, 10, 80, 35);
		this.showBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (invoice == null) {
					JOptionPane.showMessageDialog(null, "You need to search an invoice first!");
					return;
				}
				GetHistoriesByInvoiceId query = new GetHistoriesByInvoiceId(invoice.id);
				query.runOnSuccess((histories) -> invoice.histories = histories);
				query.runOnFail((reason) -> System.out.println(reason));
				Database.execute(query);

				InvoiceFrame frame = new InvoiceFrame(invoice);
				frame.setVisible(true);
			}
		});
		this.add(showBtn);
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;

		if (invoice == null) {
			this.showBtn.setEnabled(false);
			this.amountTxt.setText("");
			this.dateTxt.setText("");
			JOptionPane.showMessageDialog(null, "Invoice not found!");
			return;
		}

		this.showBtn.setEnabled(true);

		this.amountTxt.setText(Float.toString(this.invoice.totalAmt));

		String date = Util.formatDate(this.invoice.createdAt.toString());
		this.dateTxt.setText(date);
	}
}
