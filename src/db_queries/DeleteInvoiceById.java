package db_queries;

import java.sql.SQLException;

import minisale.DBMutation;
import minisale.SPU;

public class DeleteInvoiceById extends DBMutation {

	private int id;
	
	public DeleteInvoiceById(int id) {
		this.id = id;
	}

	@Override
	public String getSqlString() {
		return "delete from invoice where id = ?";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		spu.setInt(1, this.id);
	}

}
