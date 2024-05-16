package ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import data_classes.Product;
import db_queries.GetAllProducts;
import db_queries.UpdateProductById;
import minisale.Database;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ItemEvent;

public class EditProductFrame extends JFrame {

	private JPanel contentPane;

	private ArrayList<Product> productList;

	/**
	 * Create the frame.
	 */
	public EditProductFrame() {
		this.productList = new ArrayList<>();

		setResizable(false);
		setTitle("Update Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JLabel itemIdLbl = new JLabel("Items");
		itemIdLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		itemIdLbl.setBounds(25, 10, 70, 35);
		contentPane.add(itemIdLbl);

		JComboBox<Product> productComboBox = new JComboBox<>();
		ProductEditablePanel productEditablePanel = new ProductEditablePanel();

		productComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int index = productComboBox.getSelectedIndex();
				Product product = productList.get(index);
				productEditablePanel.setProduct(product);
			}
		});

		productComboBox.setFont(new Font("Arial", Font.PLAIN, 14));

		ComboBoxRenderer renderer = new ComboBoxRenderer();
		DefaultComboBoxModel<Product> model = new DefaultComboBoxModel<>(new Product[] {});

		GetAllProducts queryAllProducts = new GetAllProducts();

		queryAllProducts.runOnSuccess(products -> {
			for (Product product : products) {
				model.addElement(product);
				productList.add(product);
			}

			productComboBox.setModel(model);

		});

		Database.execute(queryAllProducts);

		productComboBox.setBounds(100, 10, 300, 35);
		productComboBox.setRenderer(renderer);
		contentPane.add(productComboBox);

		productEditablePanel.setBounds(0, 60, 450, 230);
		contentPane.add(productEditablePanel);

		setContentPane(contentPane);

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = productComboBox.getSelectedIndex();
				int id = productList.get(index).id;
				Product product = productEditablePanel.getProduct();
				UpdateProductById up = new UpdateProductById(id, product);

				up.runOnSuccess(() -> dispose());
				up.runOnFail((reason) -> System.out.println(reason));

				Database.execute(up);

			}
		});
		saveBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		saveBtn.setBounds(168, 300, 100, 35);
		contentPane.add(saveBtn);
	}

}
