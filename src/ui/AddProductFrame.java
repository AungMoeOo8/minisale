package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data_classes.Product;
import db_queries.InsertProduct;

import java.awt.Font;

import minisale.Database;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.Thread.Builder;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class AddProductFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AddProductFrame() {
		setResizable(false);
		setTitle("Add Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ProductEditablePanel productPanel = new ProductEditablePanel();
		productPanel.setBounds(0, 0, 450, 230);
		contentPane.add(productPanel);

		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product product = productPanel.getProduct();
				InsertProduct ip = new InsertProduct(product);

				ip.runOnSuccess(() -> dispose());

				Database.execute(ip);
			}
		});
		saveBtn.setFont(new Font("Arial", Font.PLAIN, 14));
		saveBtn.setBounds(168, 230, 100, 35);
		contentPane.add(saveBtn);
	}
}
