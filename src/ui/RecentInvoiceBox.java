package ui;

import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import data_classes.History;
import data_classes.Invoice;
import data_classes.Product;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.SystemColor;

public class RecentInvoiceBox extends JPanel {

	private Invoice invoice;

	/**
	 * Create the panel.
	 */
	public RecentInvoiceBox(Invoice invoice) {
		this.invoice = invoice;

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked" + Integer.toString(invoice.id));
			}
		});
		this.invoice = invoice;

		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(0, 0, 200, 250);

		JLabel invoiceIdLbl = new JLabel("Invoice " + Integer.toString(this.invoice.id));
		invoiceIdLbl.setHorizontalAlignment(SwingConstants.CENTER);
		invoiceIdLbl.setFont(new Font("Arial", Font.BOLD, 14));
		invoiceIdLbl.setBounds(50, 10, 100, 35);
		add(invoiceIdLbl);

		JLabel lblNewLabel = new JLabel("Products");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 50, 70, 20);
		add(lblNewLabel);

		ListRenderer renderer = new ListRenderer();

		Product product1 = new Product(10, "Toy", 1000, 2);
		Product product2 = new Product(20, "Plastic", 2000, 5);
		History[] histories = new History[] { new History(1, 10, product1, 10), new History(1, 10, product2, 10) };

		JList<History> list = new JList<>();
		list.setCellRenderer(renderer);
		list.setBackground(SystemColor.control);
		list.setFont(new Font("Arial", Font.PLAIN, 11));
		list.setModel(new AbstractListModel<History>() {

			public int getSize() {
				return histories.length;
			}

			public History getElementAt(int index) {
				return histories[index];
			}
		});
		list.setBounds(10, 70, 180, 120);
		add(list);

//		JPanel productRow = new JPanel();
//		productRow.setBounds(10, 220, 180, 18);
//		add(productRow);
//		productRow.setLayout(null);
//
//		JLabel productNameLbl = new JLabel("Barbie Doll");
//		productNameLbl.setBounds(0, 0, 70, 18);
//		productRow.add(productNameLbl);
//
//		JLabel quantityLbl = new JLabel("2");
//		quantityLbl.setHorizontalAlignment(SwingConstants.CENTER);
//		quantityLbl.setBounds(70, 0, 20, 18);
//		productRow.add(quantityLbl);
//
//		JLabel discountLbl = new JLabel("5%");
//		discountLbl.setHorizontalAlignment(SwingConstants.CENTER);
//		discountLbl.setBounds(90, 0, 30, 18);
//		productRow.add(discountLbl);
//
//		JLabel amountLbl = new JLabel("15000");
//		amountLbl.setHorizontalAlignment(SwingConstants.TRAILING);
//		amountLbl.setBounds(120, 0, 60, 18);
//		productRow.add(amountLbl);
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}

class HistoryRow extends JPanel {

	public HistoryRow(History history) {

		this.setBounds(10, 220, 180, 18);
		this.setLayout(null);

		String name = history.product.name;
		String quantity = Float.toString(history.quantity);
		String discount = Float.toString(history.product.discount);
		String amount = Float.toString(history.amount);

		JLabel productNameLbl = new JLabel(name);
		productNameLbl.setBounds(0, 0, 70, 18);
		this.add(productNameLbl);

		JLabel quantityLbl = new JLabel(quantity);
		quantityLbl.setHorizontalAlignment(SwingConstants.CENTER);
		quantityLbl.setBounds(70, 0, 20, 18);
		this.add(quantityLbl);

		JLabel discountLbl = new JLabel(discount + "%");
		discountLbl.setHorizontalAlignment(SwingConstants.CENTER);
		discountLbl.setBounds(90, 0, 30, 18);
		this.add(discountLbl);

		JLabel amountLbl = new JLabel(amount);
		amountLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		amountLbl.setBounds(120, 0, 60, 18);
		this.add(amountLbl);
	}
}

class ListRenderer extends JLabel implements ListCellRenderer<History> {

	@Override
	public Component getListCellRendererComponent(JList<? extends History> list, History history, int index,
			boolean isSelected, boolean cellHasFocus) {

		this.setLayout(null);
		this.setBounds(0, 0, 180, 18);
		this.setVisible(true);

		HistoryRow row = new HistoryRow(history);
		row.setBounds(0, 0, 180, 18);
		row.setVisible(true);

		this.add(row);

//		this.setText(history.product.name);

		return this;
	}

}
