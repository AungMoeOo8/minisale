package minisale;

import java.util.ArrayList;

import data_classes.History;
import data_classes.Invoice;
import data_classes.Product;

public class Global {
	public static ArrayList<Product> products = new ArrayList<>();
	public static ArrayList<Invoice> invoices = new ArrayList<>();
	public static ArrayList<History> histories = new ArrayList<>();

	public static Product[] getAllProduct() {
		Product[] productList = new Product[products.size()];
		return products.toArray(productList);
	}

	public static void addProducts(Product[] productArray) {
		for (Product product : productArray) {
			products.add(product);
		}
	}

	public static Product getProductById(int id) {
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.id == id)
				return product;
		}
		return null;
	}

}
