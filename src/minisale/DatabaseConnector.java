package minisale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import data_classes.DatabaseCredential;

public class DatabaseConnector {

	public static void connect(DatabaseCredential credential) throws SQLException, ClassNotFoundException {
		String username = credential.username;
		String password = credential.password;
		String dbName = credential.dbName;
		String host = credential.host;
		int port = credential.port;

		Class.forName("com.mysql.cj.jdbc.Driver");
		String connectionString = "jdbc:mysql://" + credential.host + ":" + port + "/" + dbName;

		Connection connection = DriverManager.getConnection(connectionString, username, password);
		new Database(connection);
	}
}
