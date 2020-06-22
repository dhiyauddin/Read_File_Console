package connection;

import java.sql.*;

public class DBAction extends DBConn {

	Statement stmt = null;
	ResultSet myResultSet = null;
	private String query = "";
	DBConn conn;

	public void setQuery(String qstr) {
		this.query = qstr;
	}

	public void setConn(DBConn conn) {
		this.conn = conn;
	}

	public ResultSet getResultSet() throws SQLException {
		myConn = conn.myConn;
		stmt = myConn.createStatement();
		myResultSet = stmt.executeQuery(query);
		stmt = null;
		return myResultSet;
		
	}

	public void insertRecord() throws SQLException {
		updateRecord();
	}

	public void deleteRecord() throws SQLException {
		updateRecord();
	}

	public void updateRecord() throws SQLException {
		myConn = conn.myConn;
		stmt = myConn.createStatement();
		stmt.executeUpdate(query);
		stmt = null;
	}

	public void cleanConnection() throws SQLException {
		myConn = conn.myConn;
		myResultSet.close();
		stmt.close();
		myConn.close();
	}
}
