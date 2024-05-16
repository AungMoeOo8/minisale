package minisale;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBQuery<T> {
	String getSqlString();

	void setParams(SPU spu) throws SQLException;

	T map(ResultSet rs) throws SQLException;

	void runOnSuccess(SuccessCallback<T> cb);

	void runOnFail(FailCallback cb);
}