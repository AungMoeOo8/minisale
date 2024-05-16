package db_queries;

import java.sql.ResultSet;
import java.sql.SQLException;

import minisale.DBQuery;

public class GetLastInvoiceId extends DBQuery<Integer> {

	@Override
	public String getSqlString() {
		return "select (id) from invoice order by id desc limit 1;";
	}

	@Override
	public Integer map(ResultSet rs) throws SQLException {
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		return id;
	}

}
