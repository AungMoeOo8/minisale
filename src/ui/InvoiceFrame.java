package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import data_classes.History;
import data_classes.Invoice;
import minisale.IIndexed;

public class InvoiceFrame extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public InvoiceFrame(Invoice invoice) {

		setTitle("Invoice " + invoice.id);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel productLbl = new JLabel("Product");
		productLbl.setBounds(20, 10, 49, 14);
		contentPane.add(productLbl);

		JLabel quantityLbl = new JLabel("Quantity");
		quantityLbl.setBounds(170, 10, 49, 14);
		contentPane.add(quantityLbl);

		JLabel amountLbl = new JLabel("Amount");
		amountLbl.setBounds(245, 10, 49, 14);
		contentPane.add(amountLbl);

		HistoryPanelWithoutDeleteBtn[] historyPanelList = new HistoryPanelWithoutDeleteBtn[invoice.histories.length];

		for (int i = 0; i < invoice.histories.length; i++) {
			History currentHistory = invoice.histories[i];

			HistoryPanelWithoutDeleteBtn panel = new HistoryPanelWithoutDeleteBtn(currentHistory);
			historyPanelList[i] = panel;
		}

		ListPanel<HistoryPanelWithoutDeleteBtn> listPanel = new ListPanel<>(10, historyPanelList);
		listPanel.build();
		this.scrollPane = new JScrollPane(listPanel);
		this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.scrollPane.setBounds(10, 30, 416, 220);
		contentPane.add(this.scrollPane);

	}

}

class HistoryPanelWithoutDeleteBtn extends JPanel implements IIndexed {
	HistoryPanelWithoutDeleteBtn(History history) {
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setPreferredSize(new Dimension(396, 400));
		this.setBounds(10, 10, 396, 40);
		this.setLayout(null);

		JLabel productName = new JLabel(history.product.name);
		productName.setBounds(10, 7, 100, 25);
		productName.setFont(new Font("Arial", Font.PLAIN, 12));
		this.add(productName);

		JLabel productQuantity = new JLabel(Integer.toString(history.quantity));
		productQuantity.setBounds(180, 7, 80, 25);
		productQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
		productQuantity.setHorizontalAlignment(SwingConstants.LEADING);
		this.add(productQuantity);

		JLabel productTotalAmount = new JLabel(Float.toString(history.amount));
		productTotalAmount.setBounds(230, 7, 200, 25);
		productTotalAmount.setFont(new Font("Arial", Font.PLAIN, 14));
		productTotalAmount.setHorizontalAlignment(SwingConstants.LEADING);
		this.add(productTotalAmount);

	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
}
