package db_queries;

import java.sql.SQLException;

import data_classes.Product;
import minisale.DBMutation;
import minisale.SPU;

public class UpdateProductById extends DBMutation {

	int id;
	Product product;

	public UpdateProductById(int id2, Product product) {
		this.id = id2;
		this.product = product;
	}

	@Override
	public String getSqlString() {
		return "update product set id = ?, name = ?, price = ?, discount = ? where id = ?;";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		int id = product.id;
		String name = product.name;
		float price = product.price;
		float discount = product.discount;

		spu.setInt(1, id);
		spu.setString(2, name);
		spu.setFloat(3, price);
		spu.setFloat(4, discount);
		spu.setInt(5, this.id);

	}

}
