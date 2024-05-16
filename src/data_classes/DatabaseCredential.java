package data_classes;

public class DatabaseCredential {
	public String dbName;
	public String username;
	public String password;
	public String host;
	public int port;

	public DatabaseCredential(String host, int port, String username, String password, String dbName) {
		this.username = username;
		this.password = password;
		this.dbName = dbName;
		this.host = host;
		this.port = port;
	}

}
