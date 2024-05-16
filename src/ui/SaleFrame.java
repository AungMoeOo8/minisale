package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import data_classes.DatabaseCredential;
import data_classes.History;
import data_classes.Invoice;
import data_classes.Product;
import db_queries.GetTotalProducts;
import minisale.Database;
import minisale.DatabaseConnector;
import minisale.Global;

import java.awt.Dimension;
import javax.swing.JScrollBar;
import java.awt.Component;
import java.awt.Point;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class SaleFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SaleFrame() {
		setResizable(false);
		setTitle("MiniSale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu productMenu = new JMenu("Product");
		productMenu.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(productMenu);

		JMenuItem addProductBtn = new JMenuItem("Add product");
		addProductBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		addProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductFrame frame = new AddProductFrame();
				frame.setVisible(true);
			}
		});
		productMenu.add(addProductBtn);

		JMenuItem updateProductBtn = new JMenuItem("Update product");
		updateProductBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		updateProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProductFrame frame = new EditProductFrame();
				frame.setVisible(true);
			}
		});
		productMenu.add(updateProductBtn);

		JMenuItem deleteProductBtn = new JMenuItem("Delete product");
		deleteProductBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		deleteProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteProductFrame frame = new DeleteProductFrame();
				frame.setVisible(true);
			}
		});
		productMenu.add(deleteProductBtn);

		JMenu invoiceMenu = new JMenu("Invoice");
		invoiceMenu.setFont(new Font("Arial", Font.PLAIN, 12));
		menuBar.add(invoiceMenu);

		JMenuItem createInvoiceBtn = new JMenuItem("Create invoice");
		createInvoiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateInvoiceFrame frame = new CreateInvoiceFrame();
				frame.setVisible(true);
			}
		});
		createInvoiceBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		invoiceMenu.add(createInvoiceBtn);

		JMenuItem deleteInvoiceBtn = new JMenuItem("Delete invoice");
		deleteInvoiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteInvoiceFrame frame = new DeleteInvoiceFrame();
				frame.setVisible(true);
			}
		});
		deleteInvoiceBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		invoiceMenu.add(deleteInvoiceBtn);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		StatisticBox productBox = new StatisticBox("Products", Global.getAllProduct().length);
		contentPane.add(productBox);

		StatisticBox invoiceBox = new StatisticBox("Invoices", 20);
		invoiceBox.setLocation(250, 20);
		contentPane.add(invoiceBox);

		JLabel recentInvoicesTitle = new JLabel("Recent Invoices");
		recentInvoicesTitle.setFont(new Font("Arial", Font.BOLD, 16));
		recentInvoicesTitle.setBounds(25, 125, 150, 25);
		contentPane.add(recentInvoicesTitle);

		Product product = new Product(1, "Toy", 1000, 2);
		Product[] products = new Product[] { product };
		History[] histories = new History[] { new History(1, 10, product, 10) };
		Invoice[] invoices = new Invoice[] { new Invoice(9, histories, new Date(1000)),
				new Invoice(10, histories, new Date(1000)) };

		RecentInvoicePanel recentInvoices = new RecentInvoicePanel(invoices);
		recentInvoices.setBounds(0, 150, 786, 300);
		contentPane.add(recentInvoices);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(128, 128, 128));
//		separator.setBounds(10, 149, 766, 1);
		separator.setBounds(25, 149, 137, 1);
		contentPane.add(separator);

	}
}

class StatisticBox extends JPanel {
	JLabel keyLbl;
	JLabel valueLbl;

	StatisticBox(String keyText, float valueText) {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBounds(25, 20, 200, 80);
		setLayout(null);

		keyLbl = new JLabel(keyText);
		keyLbl.setFont(new Font("Arial", Font.BOLD, 18));
		keyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		keyLbl.setBounds(10, 11, 178, 25);
		this.add(keyLbl);

		valueLbl = new JLabel(Float.toString(valueText));
		valueLbl.setFont(new Font("Arial", Font.BOLD, 22));
		valueLbl.setHorizontalAlignment(SwingConstants.CENTER);
		valueLbl.setBounds(10, 47, 178, 28);
		this.add(valueLbl);
	}

	public void setValue(float value) {
		this.valueLbl.setText(Float.toString(value));
	}
}

class RecentInvoicePanel extends JPanel {

	Invoice[] invoices;

	RecentInvoicePanel(Invoice[] invoices) {

		this.invoices = invoices;

		setLayout(null);

		JLabel noInvoiceLbl = new JLabel("No recent invoice");
		noInvoiceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		noInvoiceLbl.setFont(new Font("Arial", Font.BOLD, 16));
		noInvoiceLbl.setBounds(318, 135, 150, 30);

		if (this.invoices == null || this.invoices.length == 0) {
			this.add(noInvoiceLbl);
			return;
		}

		if (this.invoices.length >= 1) {
			RecentInvoiceBox recentInvoice1 = new RecentInvoiceBox(invoices[0]);
			recentInvoice1.setBounds(25, 20, 200, 250);
			this.add(recentInvoice1);
		}

		if (this.invoices.length >= 2) {
			RecentInvoiceBox recentInvoice2 = new RecentInvoiceBox(invoices[1]);
			recentInvoice2.setBounds(250, 20, 200, 250);
			this.add(recentInvoice2);
		}

		if (this.invoices.length >= 3) {
			RecentInvoiceBox recentInvoice3 = new RecentInvoiceBox(invoices[2]);
			recentInvoice3.setBounds(475, 20, 200, 250);
			this.add(recentInvoice3);
		}
	}
}
