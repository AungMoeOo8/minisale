package db_queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data_classes.History;
import data_classes.Product;
import minisale.DBQuery;
import minisale.Global;
import minisale.SPU;

public class GetHistoriesByInvoiceId extends DBQuery<History[]> {
	private int id;

	public GetHistoriesByInvoiceId(int id) {
		this.id = id;
	}

	@Override
	public String getSqlString() {
		return "select * from history where invoiceId = ?";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		spu.setInt(1, this.id);
	}

	@Override
	public History[] map(ResultSet rs) throws SQLException {
		ArrayList<History> list = new ArrayList<>();
		while (rs.next()) {
			int id = rs.getInt("id");
			int invoiceId = rs.getInt("invoiceId");
			int productId = rs.getInt("productId");
			float price = rs.getFloat("price");
			float discount = rs.getFloat("discount");
			int quantity = rs.getInt("quantity");

			Product product = Global.getProductById(productId);
			product.price = price;
			product.discount = discount;
			History history = new History(id, invoiceId, product, quantity);

			list.add(history);
		}

		History[] histories = new History[list.size()];
		for (int i = 0; i < list.size(); i++) {
			histories[i] = list.get(i);
		}
		return histories;
	}

}
