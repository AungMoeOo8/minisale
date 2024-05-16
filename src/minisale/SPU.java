package minisale;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/* 
 * SPU stands for Statement Parameters Updater.
 * */
public class SPU {
	PreparedStatement stmt;

	public SPU(PreparedStatement stmt) {
		this.stmt = stmt;
	}

	public void setString(int index, String str) throws SQLException {
		stmt.setString(index, str);
	}

	public void setInt(int index, int number) throws SQLException {
		stmt.setInt(index, number);
	}

	public void setBoolean(int index, boolean bool) throws SQLException {
		stmt.setBoolean(index, bool);
	}

	public void setFloat(int index, float number) throws SQLException {
		stmt.setFloat(index, number);
	}
}