package minisale;

import java.sql.SQLException;

public interface IDBMutation {
	String getSqlString();

	void setParams(SPU spu) throws SQLException;

	void runOnSuccess(VoidCallback cb);

	void runOnFail(FailCallback cb);
}
