package db_queries;

import java.sql.ResultSet;
import java.sql.SQLException;

import data_classes.Product;
import minisale.DBQuery;
import minisale.FailCallback;
import minisale.IDBQuery;
import minisale.SPU;
import minisale.SuccessCallback;

public class GetProductById extends DBQuery<Product> {
	String id;

	public GetProductById(String id) {
		this.id = id;
	}

	@Override
	public String getSqlString() {
		return "SELECT * FROM product where id = ?;";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		spu.setString(1, this.id);
	}

	@Override
	public Product map(ResultSet rs) throws SQLException {
		Product product = new Product(0, "", 0, 0);
		while (rs.next()) {
			product.id = rs.getInt("id");
			product.name = rs.getString("name");
			product.price = rs.getFloat("price");
			product.discount = rs.getFloat("discount");
		}
		return product;
	}

}