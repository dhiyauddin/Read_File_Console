package connection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionString {

	public Connection connectToDB() {

		System.out.println("Inside the connection funtion start");
		Connection con = null;

		//String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=Record;";
		//String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//String dbUname = "sa";
		//String dbPwd = "P@ssw0rd";
		
		//String dbUrl = "jdbc:as400://172.20.40.11:446/S654723D";
		//String dbDriver = "com.ibm.as400.access.AS400JDBCDriver";
		//String dbUname = "THDBUSER";
		//String dbPwd = "THDBUSER";

		// Load the JDBC driver
		try {
			Properties prop = new Properties();
			
			String propFileName = "config.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			//FileInputStream access = new FileInputStream("/opt/tomcat/conf/config.propeties");
			prop.load(inputStream);
			String dbUrl = prop.getProperty("dbUrl");
			String dbDriver = prop.getProperty("dbDriver");
			String dbName = prop.getProperty("dbName");
			String dbUname = prop.getProperty("dbUname");
			String dbPwd = prop.getProperty("dbPwd");
			Class.forName(dbDriver);
			String url = dbUrl+"databaseName="+dbName+";user="+dbUname+";password="+dbPwd;
			// Get the connection
			//con = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
			con = DriverManager.getConnection(url);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Inside the connection funtion end");
		return con;
	}
}
