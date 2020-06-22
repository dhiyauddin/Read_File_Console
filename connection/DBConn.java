package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import common.Config;

public abstract class DBConn extends Config {

	// connection string for mysql
	// private String myDriver = "com.mysql.jdbc.Driver";
	//private String myDriver = "com.mysql.cj.jdbc.Driver";
	// private String myURL = "jdbc:mysql://172.20.40.134:3306/" + dbName;

	// localhost
	// private String myURL = "jdbc:mysql://localhost/" + dbName +
	// "?useTimezone=true&serverTimezone=UTC";

	// Server PC Support
	// private String myURL = "jdbc:mysql://192.200.10.169:3306/" + dbName +
	// "?useTimezone=true&serverTimezone=UTC";

	// Server 10.0.1.113 (Production)
	//private String myURL = "jdbc:mysql://10.0.1.113:3306/" + dbName + "?useTimezone=true&serverTimezone=UTC";

	// connection string for sql server
	// private String myDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	// private String myURL =
	// "jdbc:sqlserver://DHIYAUDDINTH\\LOCALHOST;databaseName="+dbName+";user="+dbUsername+";password="+dbPassword;

	 private String myDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	 private String myURL =
	 "jdbc:sqlserver://192.168.0.15\\XSQL;databaseName="+dbName+";user="+dbUsername+";password="+dbPassword;

	
	public Connection myConn;

	public DBConn() {
	}

	public void openConnection() throws SQLException {
		try {
			Class.forName(myDriver);
			// myConn = DriverManager.getConnection(myURL);
			// mysql
			myConn = DriverManager.getConnection(myURL, dbUsername, dbPassword);
		} catch (ClassNotFoundException ex) {
			throw new SQLException("Driver not found");
		}
	}

	public abstract void cleanConnection() throws Exception;

	public void closeConnection() throws Exception {
		cleanConnection();
		myConn.close();
	}
}
