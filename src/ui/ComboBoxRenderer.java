package ui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import data_classes.Product;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer<Product> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Product> list, Product product, int index,
			boolean isSelected, boolean cellHasFocus) {
		this.setText(product.name);
		return this;
	}

}
