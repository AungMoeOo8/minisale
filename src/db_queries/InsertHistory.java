package db_queries;

import java.sql.SQLException;

import minisale.DBMutation;
import minisale.SPU;

public class InsertHistory extends DBMutation {

	private int invoiceId;
	private int productId;
	private float price;
	private int quantity;
	private float discount;

	public InsertHistory(int invoiceId, int productId, float price, int quantity, float discount) {
		this.invoiceId = invoiceId;
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.discount = discount;
	}

	@Override
	public String getSqlString() {
		return "insert into history (invoiceId, productId, price, quantity, discount) values (?, ?, ?, ?, ?);";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		spu.setInt(1, this.invoiceId);
		spu.setInt(2, this.productId);
		spu.setFloat(3, this.price);
		spu.setInt(4, this.quantity);
		spu.setFloat(5, this.discount);
	}

}
