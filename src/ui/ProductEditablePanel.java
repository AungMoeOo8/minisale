package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import data_classes.Product;

import javax.swing.event.ChangeEvent;

public class ProductEditablePanel extends JPanel {
	private JTextField productIdTxt;
	private JTextField nameTxt;
	private JTextField priceTxt;
	private JSlider discountSlider;

	/**
	 * Create the panel.
	 */
	public ProductEditablePanel() {
		setLayout(null);

		JLabel productIdLbl = new JLabel("ProductId");
		productIdLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		productIdLbl.setBounds(25, 30, 70, 35);
		add(productIdLbl);

		productIdTxt = new JTextField();
		productIdTxt.setMargin(new Insets(6, 6, 6, 6));
		productIdTxt.setFont(new Font("Arial", Font.PLAIN, 14));
		productIdTxt.setColumns(10);
		productIdTxt.setBounds(100, 30, 300, 35);
		add(productIdTxt);

		JLabel nameLbl = new JLabel("Name");
		nameLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		nameLbl.setBounds(25, 80, 70, 35);
		add(nameLbl);

		nameTxt = new JTextField();
		nameTxt.setMargin(new Insets(6, 6, 6, 6));
		nameTxt.setFont(new Font("Arial", Font.PLAIN, 14));
		nameTxt.setColumns(10);
		nameTxt.setBounds(100, 80, 300, 35);
		add(nameTxt);

		JLabel priceLbl = new JLabel("Price");
		priceLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		priceLbl.setBounds(25, 130, 70, 35);
		add(priceLbl);

		priceTxt = new JTextField();
		priceTxt.setMargin(new Insets(6, 6, 6, 6));
		priceTxt.setFont(new Font("Arial", Font.PLAIN, 14));
		priceTxt.setColumns(10);
		priceTxt.setBounds(100, 130, 300, 35);
		add(priceTxt);

		JLabel discountLbl = new JLabel("Discount");
		discountLbl.setFont(new Font("Arial", Font.PLAIN, 14));
		discountLbl.setBounds(25, 180, 70, 35);
		add(discountLbl);

		this.discountSlider = new JSlider();
		JLabel discountPercent = new JLabel("0");
		discountSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				discountPercent.setText(Integer.toString(discountSlider.getValue()));
			}
		});
		discountSlider.setValue(0);
		discountSlider.setMinorTickSpacing(1);
		discountSlider.setMajorTickSpacing(10);
		discountSlider.setBounds(100, 180, 225, 35);
		add(discountSlider);

		discountPercent.setHorizontalTextPosition(SwingConstants.CENTER);
		discountPercent.setHorizontalAlignment(SwingConstants.TRAILING);
		discountPercent.setFont(new Font("Arial", Font.PLAIN, 14));
		discountPercent.setBounds(360, 180, 20, 35);
		add(discountPercent);

		JLabel percentSign = new JLabel("%");
		percentSign.setFont(new Font("Arial", Font.PLAIN, 14));
		percentSign.setBounds(385, 180, 15, 35);
		add(percentSign);

	}

	private ProductEditablePanel setProductId(int id) {
		this.productIdTxt.setText(Integer.toString(id));
		return this;
	}

	private ProductEditablePanel setNameTxt(String name) {
		this.nameTxt.setText(name);
		return this;
	}

	private ProductEditablePanel setPrice(float price) {
		this.priceTxt.setText(Float.toString(price));
		return this;
	}

	private ProductEditablePanel setDiscount(float discount) {
		this.discountSlider.setValue((int) discount);
		return this;
	}

	public Product getProduct() {
		int productId = Integer.parseInt(this.productIdTxt.getText());
		String name = this.nameTxt.getText();
		float price = Float.parseFloat(this.priceTxt.getText());
		float discount = this.discountSlider.getValue();

		Product product = new Product(productId, name, price, discount);
		return product;
	}

	public void setProduct(Product product) {
		int id = product.id;
		String name = product.name;
		float price = product.price;
		float discount = product.discount;

		this.setProductId(id).setNameTxt(name).setPrice(price).setDiscount(discount);
	}

}
