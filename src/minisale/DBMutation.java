package minisale;

import java.sql.SQLException;

public abstract class DBMutation implements IDBMutation {

	VoidCallback onSuccess = () -> {
	};
	FailCallback onFail = (String reason) -> {
	};

	@Override
	public abstract String getSqlString();

	@Override
	public void setParams(SPU spu) throws SQLException {
	};

	@Override
	public void runOnSuccess(VoidCallback cb) {
		this.onSuccess = cb;
	}

	@Override
	public void runOnFail(FailCallback cb) {
		this.onFail = cb;
	}

}
