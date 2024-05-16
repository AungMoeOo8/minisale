package db_queries;

import java.sql.SQLException;

import minisale.DBMutation;
import minisale.SPU;

public class DeleteProductById extends DBMutation {

	String id;

	public DeleteProductById(String id) {
		this.id = id;
	}

	@Override
	public String getSqlString() {
		return "delete from product where id = ?;";
	}

	@Override
	public void setParams(SPU spu) throws SQLException {
		spu.setString(1, this.id);
	}

}
