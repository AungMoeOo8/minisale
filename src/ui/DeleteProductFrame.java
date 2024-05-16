package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data_classes.Product;
import db_queries.DeleteProductById;
import db_queries.GetAllProducts;
import minisale.Database;
import minisale.Global;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class DeleteProductFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public DeleteProductFrame() {
		
		Product firstProduct = Global.products.get(0);

		JLabel productIdLbl = new JLabel("ProductId");
		JLabel productIdValue = new JLabel(Integer.toString(firstProduct.id));
		JLabel nameLbl = new JLabel("Name");
		JLabel nameValue = new JLabel(firstProduct.name);
		JLabel priceLbl = new JLabel("Price");
		JLabel priceValue = new JLabel(Float.toString(firstProduct.price));
		JLabel discountLbl = new JLabel("Discount");
		JLabel discountValue = new JLabel(Float.toString(firstProduct.discount));

		setTitle("Delete Item");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);

		JLabel productListLbl = new JLabel("ProductId");
		productListLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		productListLbl.setBounds(25, 10, 70, 35);
		contentPane.add(productListLbl);

		JComboBox<Product> productComboBox = new JComboBox<>();

		productComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int index = productComboBox.getSelectedIndex();
				Product product = Global.products.get(index);

				int id = product.id;
				String name = product.name;
				float price = product.price;
				float discount = product.discount;

				productIdValue.setText(Integer.toString(id));
				nameValue.setText(name);
				priceValue.setText(Float.toString(price));
				discountValue.setText(Float.toString(discount));
			}
		});

		ComboBoxRenderer renderer = new ComboBoxRenderer();
		
		DefaultComboBoxModel<Product> model = new DefaultComboBoxModel<>(Global.getAllProduct());

		productComboBox.setModel(model);
		productComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
		productComboBox.setBounds(100, 10, 300, 35);
		productComboBox.setRenderer(renderer);
		contentPane.add(productComboBox);

		JPanel panel = new JPanel();
		panel.setBounds(0, 60, 450, 240);
		contentPane.add(panel);
		panel.setLayout(null);

		// Start

		productIdLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		productIdLbl.setBounds(25, 30, 70, 35);
		panel.add(productIdLbl);

		productIdValue.setFont(new Font("Arial", Font.PLAIN, 14));
		productIdValue.setBounds(100, 30, 300, 35);
		panel.add(productIdValue);

		nameLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		nameLbl.setBounds(25, 80, 70, 35);
		panel.add(nameLbl);

		nameValue.setFont(new Font("Arial", Font.PLAIN, 14));
		nameValue.setBounds(100, 80, 300, 35);
		panel.add(nameValue);

		priceLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		priceLbl.setBounds(25, 130, 70, 35);
		panel.add(priceLbl);

		priceValue.setFont(new Font("Arial", Font.PLAIN, 14));
		priceValue.setBounds(100, 130, 300, 35);
		panel.add(priceValue);

		discountLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		discountLbl.setBounds(25, 180, 70, 35);
		panel.add(discountLbl);

		discountValue.setFont(new Font("Arial", Font.PLAIN, 14));
		discountValue.setBounds(100, 180, 300, 35);
		panel.add(discountValue);
		// End

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = productIdValue.getText();

				DeleteProductById dp = new DeleteProductById(id);

				dp.runOnSuccess(() -> dispose());
				dp.runOnFail((reason) -> System.err.println("Failed"));

				Database.execute(dp);
			}
		});
		deleteBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		deleteBtn.setBounds(168, 300, 100, 35);
		contentPane.add(deleteBtn);

	}

}
