package db_queries;

import java.sql.ResultSet;
import java.sql.SQLException;

import data_classes.Invoice;
import minisale.DBQuery;
import minisale.SPU;

public class GetInvoiceById extends DBQuery<Invoice> {

	private int id;

	public GetInvoiceById(int id) {
		this.id = id;
	}

	@Override
	public String getSqlString() {
		return "select * from invoice where id = ? limit 1;";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		spu.setInt(1, this.id);
		;
	}

	@Override
	public Invoice map(ResultSet rs) throws SQLException {
		Invoice invoice = null;
		while (rs.next()) {
			invoice = new Invoice(id, null, null);
			invoice.totalAmt = rs.getFloat("totalAmount");
			invoice.createdAt = rs.getDate("createdAt");
		}
		return invoice;
	}

}
