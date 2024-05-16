package minisale;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBQuery<T> implements IDBQuery<T> {

	SuccessCallback<T> onSuccess = (entity) -> {
	};
	FailCallback onFail = (String reason) -> {
	};

	@Override
	public abstract String getSqlString();

	@Override
	public void setParams(SPU spu) throws SQLException {
	};

	@Override
	public abstract T map(ResultSet rs) throws SQLException;

	@Override
	public void runOnSuccess(SuccessCallback<T> cb) {
		this.onSuccess = cb;
	}

	@Override
	public void runOnFail(FailCallback cb) {
		this.onFail = cb;

	}

}
