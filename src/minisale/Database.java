package minisale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private static Connection connection;

	Database(Connection con) {
		connection = con;
	}

	public static <T> void execute(DBQuery<T> query) {
		try {
			PreparedStatement stmt = connection.prepareStatement(query.getSqlString());
			SPU spu = new SPU(stmt);
			query.setParams(spu);
			ResultSet rs = stmt.executeQuery();
			T entity = query.map(rs);
			query.onSuccess.run(entity);
		} catch (SQLException e) {
			query.onFail.run(e.getMessage());
		}
	}

	public static void execute(DBMutation update) {
		try {
			PreparedStatement stmt = connection.prepareStatement(update.getSqlString());
			SPU spu = new SPU(stmt);
			update.setParams(spu);
			stmt.executeUpdate();
			update.onSuccess.run();
		} catch (SQLException e) {
			update.onFail.run(e.getMessage());
		}
	}
}
