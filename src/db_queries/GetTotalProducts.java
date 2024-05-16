package db_queries;

import java.sql.ResultSet;
import java.sql.SQLException;

import minisale.DBQuery;

public class GetTotalProducts extends DBQuery<Integer> {

	@Override
	public String getSqlString() {
		return "select count(*) as total_products from product;";
	}

	@Override
	public Integer map(ResultSet rs) throws SQLException {
		while (rs.next()) {
			return rs.getInt("total_products");
		}
		return null;
	}

}
