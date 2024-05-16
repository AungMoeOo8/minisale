package db_queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data_classes.Product;
import minisale.DBQuery;
import minisale.IDBQuery;
import minisale.SPU;

public class GetAllProducts extends DBQuery<Product[]> {

	@Override
	public String getSqlString() {
		return "SELECT * FROM product;";
	}

	@Override
	public Product[] map(ResultSet rs) throws SQLException {
		ArrayList<Product> products = new ArrayList<>();

		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			float discount = rs.getFloat("discount");

			Product product = new Product(id, name, price, discount);
			products.add(product);
		}
		Product[] productList = new Product[products.size()];
		return products.toArray(productList);
	}

}
