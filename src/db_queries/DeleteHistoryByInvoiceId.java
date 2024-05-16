package db_queries;

import java.sql.SQLException;

import minisale.DBMutation;
import minisale.SPU;

public class DeleteHistoryByInvoiceId extends DBMutation {

	private int invoiceId;

	public DeleteHistoryByInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Override
	public String getSqlString() {
		return "delete from history where invoiceId = ?;";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		spu.setInt(1, this.invoiceId);
	}

}
