package common;

import java.sql.ResultSet;

import connection.DBAction;

public class Config {

	protected String sql = "";
	protected ResultSet rs = null;
	protected DBAction db = null;

	protected String sql1 = "";
	protected ResultSet rs1 = null;
	protected DBAction db1 = null;

	protected String sql2 = "";
	protected ResultSet rs2 = null;
	protected DBAction db2 = null;

	protected String sql3 = "";
	protected ResultSet rs3 = null;
	protected DBAction db3 = null;

	// localhost
	 protected String dbName = "Record";
	 protected String dbUsername = "sa";
	 protected String dbPassword = "pa$$w0rd";

	// server PC Support
	// protected String dbName = "Testing";
	// protected String dbUsername = "root";
	// protected String dbPassword = "";

	// server 10.0.1.113 (Production)
	//protected String dbName = "testing";
	//protected String dbUsername = "root";
	//protected String dbPassword = "ASDFqwer1234#";

	public String SMTP_HOST_NAME = "";
	public String SMTP_AUTH_USER = "";
	public String SMTP_AUTH_PWD = "";

}
