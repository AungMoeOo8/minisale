package db_queries;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import data_classes.Invoice;
import minisale.DBMutation;
import minisale.DBQuery;
import minisale.SPU;

public class InsertInvoice extends DBMutation {

	private float totalAmt;
	private Date createdAt;

	public InsertInvoice(float totalAmt, Date createdAt) {
		this.totalAmt = totalAmt;
		this.createdAt = createdAt;
	}

	@Override
	public String getSqlString() {
		// TODO Auto-generated method stub
		return "insert into invoice (totalAmount, createdAt) values (?, ?);";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		spu.setFloat(1, this.totalAmt);
		spu.setString(2, this.createdAt.toString());
	}

}
