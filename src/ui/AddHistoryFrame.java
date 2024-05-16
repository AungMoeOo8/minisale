package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data_classes.History;
import data_classes.Product;
import minisale.Global;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;
import java.beans.PropertyChangeListener;
import java.util.regex.Pattern;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class AddHistoryFrame extends JFrame {

	private JPanel contentPane;
	private JTextField quantityField;
	private int index;

	/**
	 * Create the frame.
	 */
	public AddHistoryFrame(CreateInvoiceFrame parent, int index) {

		this.index = index;

		setTitle("Add Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ComboBoxRenderer renderer = new ComboBoxRenderer();
		DefaultComboBoxModel<Product> model = new DefaultComboBoxModel<>(Global.getAllProduct());
		JComboBox<Product> comboBox = new JComboBox<>();
		comboBox.setRenderer(renderer);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBox.setModel(model);
		comboBox.setBounds(100, 11, 326, 30);
		contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("Product");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 16, 70, 20);
		contentPane.add(lblNewLabel);

		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Arial", Font.PLAIN, 14));
		lblQuantity.setBounds(10, 65, 70, 20);
		contentPane.add(lblQuantity);

		quantityField = new JTextField("0");
		quantityField.setHorizontalAlignment(SwingConstants.TRAILING);
		quantityField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent ke) {

				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
					quantityField.setEditable(true);
				} else if (ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					quantityField.setEditable(true);
				} else {
					quantityField.setEditable(false);
				}
			}
		});

		quantityField.setMargin(new Insets(2, 4, 2, 4));
		quantityField.setFont(new Font("Arial", Font.PLAIN, 14));
		quantityField.setBounds(160, 61, 120, 30);
		contentPane.add(quantityField);
		quantityField.setColumns(10);

		JButton addBtn = new JButton("Add");

		JButton incrementBtn = new JButton("+");
		incrementBtn.setFont(new Font("Arial", Font.PLAIN, 11));
		incrementBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String quantityTxt = quantityField.getText();
				int quantity = 0;

				if (!quantityTxt.equalsIgnoreCase("")) {
					quantity = Integer.parseInt(quantityField.getText());
				}

				if (quantity > 0) {
					addBtn.setEnabled(true);
				}

				quantity++;
				quantityField.setText(Integer.toString(quantity));

				if (quantity > 0) {
					addBtn.setEnabled(true);
				}
			}
		});
		incrementBtn.setBounds(100, 61, 50, 30);
		contentPane.add(incrementBtn);

		JButton decrementBtn = new JButton("-");
		decrementBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String quantityTxt = quantityField.getText();
				int quantity = 0;

				if (!quantityTxt.equalsIgnoreCase("")) {
					quantity = Integer.parseInt(quantityField.getText());
				}

				// this statement is to stop the action when quantity is zero
				if (quantity <= 0) {
					addBtn.setEnabled(false);
					return;
				}

				quantity--;
				quantityField.setText(Integer.toString(quantity));

				// this statement is for disabling the add button
				if (quantity <= 0) {
					addBtn.setEnabled(false);
					return;
				}
			}
		});
		decrementBtn.setFont(new Font("Arial", Font.PLAIN, 11));
		decrementBtn.setBounds(290, 61, 50, 30);
		contentPane.add(decrementBtn);

		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product product = (Product) comboBox.getSelectedItem();
				int quantity = Integer.parseInt(quantityField.getText());
				History history = new History(index, 0, product, quantity);
				HistoryRecord record = new HistoryRecord(index, history);
				System.out.println("addHistoryFrame: " + index);
				parent.addHistory(record);
				dispose();
			}
		});
		addBtn.setFont(new Font("Arial", Font.PLAIN, 12));
		addBtn.setBounds(168, 222, 100, 30);
		addBtn.setEnabled(false);
		contentPane.add(addBtn);
	}
}
