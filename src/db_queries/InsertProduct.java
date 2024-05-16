package db_queries;

import java.sql.SQLException;

import data_classes.Product;
import minisale.DBMutation;
import minisale.SPU;

public class InsertProduct extends DBMutation {

	Product product;

	public InsertProduct(Product product) {
		this.product = product;
	}

	@Override
	public String getSqlString() {
		return "INSERT INTO product VALUES(?, ?, ?, ?)";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		int id = this.product.id;
		String name = this.product.name;
		float price = this.product.price;
		float discount = this.product.discount;

		spu.setInt(1, id);
		spu.setString(2, name);
		spu.setFloat(3, price);
		spu.setFloat(4, discount);
	}

}
