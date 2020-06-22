package com.quatriz.readFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import common.Constant;
import connection.ConnectionString;

public class Execute {

	// static final Logger logger = Logger.getLogger(Execute.class);

	public String execute() {
		boolean flag = false;
		boolean flagFile = false;
		String situation = "";
		Constant obj = new Constant();
		Properties prop = new Properties();
		try {
			// FileInputStream access = new
			// FileInputStream("/opt/tomcat/conf/config.propeties");
			String propFileName = "config.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
			String temp = prop.getProperty("temp");
			// logger.debug(temp);
			System.out.println(temp);
			String duplicate = prop.getProperty("duplicate");
			// logger.debug(duplicate);
			System.out.println(duplicate);

			File folder = new File(temp);
			ArrayList<String> recordList = new ArrayList<String>();
			recordList = listFilesForFolder(folder);

			for (int i = 0; i < recordList.size(); i++) {

				flagFile = checkingFileDAO(recordList.get(i));
				if (!flagFile) {

					getPath(recordList.get(i), temp, duplicate);
					insertFileDAO(recordList.get(i));
					flag = true;
				}
			}

				if (flag) {
				// logger.debug("flag situation: " + flag);
				System.out.println("flag situation: " + flag);
				situation = obj.ReadFileConsole_SuccessInsertRecord;
			} else {
				// logger.debug("flag situation: " + flag);
				System.out.println("flag situation: " + flag);
				situation = obj.ReadFileConsole_UnsuccesfulInsertRecord;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return situation;
	}

	static byte[] output(byte[] data) {
		byte[] outputTest = null;
		outputTest = data;
		return outputTest;
	}

	public static void insertRecordDAO(String data, String fileName, String duplicate) {
		Connection con = null;
		boolean flag = false;
		int count = 0;
		ConnectionString conn = new ConnectionString();
		con = conn.connectToDB();
		try {
			flag = insertData(data, con, fileName, duplicate);
			if (flag) {
				count = count + 1;
				// logger.debug("Insertion successful. " + count + " records");
				System.out.println("Insertion successful. " + count + " records");
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	static void getPath(String fileName, String temp, String duplicate) {
		try {
			InputStream is = null;

			is = new FileInputStream(temp + "/" + fileName);

			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();
			int count = 1;
			while (line != null) {

				if (count > 1) {
					sb.append(line).append("\n");
					line = buf.readLine();
					if (line.contains("T")) {
						break;
					} else {
						line = line.substring(2, 26); // get the TagId Value.
						insertRecordDAO(line, fileName, duplicate); // insert into object and database
						count++;
					}
				}
				if (count == 1) {
					line = "";
					count = count + 1;
				}
			}
			
			String fileAsString = sb.toString();
			System.out.println("Contents : " + fileAsString);
			
			is.close();

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static ArrayList<String> listFilesForFolder(final File folder) {
		ArrayList<String> fileNameWrap = new ArrayList<String>();
		String fileName = "";
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				//System.out.println(fileEntry.getName());
				fileName = fileEntry.getName();
				System.out.println("fileName : "+fileName);
				fileNameWrap.add(fileName);
			}
		}
		return fileNameWrap;
	}

	static boolean insertData(String data, Connection con, String fileName, String duplicate) {

		boolean flag = false;
		PreparedStatement stmt = null;
		int rs = 0;
		String sql = "INSERT INTO [Record]..ListData (itemHead,item) VALUES (?,?)";
		try {
			// logger.debug(sql);
			System.out.println(sql);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, fileName);
			stmt.setString(2, data);
			rs = stmt.executeUpdate();
			// logger.debug(rs + "records updated");
			System.out.println(rs + "records updated");
			flag = true;

		} catch (Exception ex) {
			// logger.debug("Error duplicate : " + ex.getMessage());
			System.out.println("Error duplicate : " + ex.getMessage());
			createFile(data, fileName, duplicate,con);
		} finally {
			// logger.debug("Done.");
			System.out.println("Done.");
		}
		return flag;
	}

	public static void insertFileDAO(String fileName) {
		Connection con = null;
		boolean flag = false;
		int count = 0;
		ConnectionString conn = new ConnectionString();
		con = conn.connectToDB();
		try {
			flag = insertFileName(con, fileName);
			if (flag) {
				count = count + 1;
				// logger.debug("Insertion successful. " + count + " records");
				System.out.println("Insertion successful. " + count + " records");
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	static boolean insertFileName(Connection con, String fileName) {

		String currentDateTime = getCurrentDateTime();
		boolean flag = false;
		PreparedStatement stmt = null;
		int rs = 0;
		String sql = "INSERT INTO [Record]..ListFile VALUES (?,?)";
		try {
			// logger.debug(sql);
			System.out.println(sql);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, fileName);
			stmt.setString(2,currentDateTime);
			
			rs = stmt.executeUpdate();
			// logger.debug(rs + "records updated");
			System.out.println(rs + "records updated");
			flag = true;

		} catch (Exception ex) {
			// logger.debug("Error duplicate : " + ex.getMessage());
			System.out.println("Error duplicate : " + ex.getMessage());
		} finally {
			// logger.debug("Done.");
			System.out.println("Done.");
		}
		return flag;
	}

	static boolean checkingFile(String data, Connection con) {

		boolean flag = false;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT item FROM [Record]..ListFile WHERE item = '" + data + "'";
		String fileName = "";
		try {
			// logger.debug(sql);
			System.out.println(sql);
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				fileName = rs.getString("item");
				if (!"".equals(fileName))
					flag = true;
			}
		} catch (Exception ex) {
			// logger.debug(ex);
			System.out.println(ex);
		} finally {
			// logger.debug(flag);
			System.out.println(flag);
			// logger.debug("Done.");
			System.out.println("Done.");
		}
		return flag;
	}

	static boolean checkingFileDAO(String fileName) {
		Connection con = null;
		boolean flag = false;
		ConnectionString conn = new ConnectionString();
		con = conn.connectToDB();
		try {
			flag = checkingFile(fileName, con);
			// logger.debug("checkingFile flag : " + flag);
			System.out.println("checkingFile flag : " + flag);
			if (flag) {
				// logger.debug("File is existed for " + fileName);
				System.out.println("File is existed for " + fileName);
				con.close();
			}
			con.close();
		} catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace();

		}
		return flag;
	}

	static void createFile(String tagId, String fileName, String duplicate,Connection con) {
		
		boolean  flag = InsertFileDuplicate (tagId, fileName,con);
		
		File file = null;
		String typeFile = ".txt";
		try {
			file = File.createTempFile(fileName.replace(".dat", "") + "_" + tagId + "_", typeFile,
					new File(duplicate + "/"));
			// logger.debug(file.getAbsolutePath());
			System.out.println(file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static String getCurrentDateTime() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.parse(dateFormat.format(date).toString(),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");
		ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// String today = dateFormat.format(date).toString();
		System.out.println("today : " + format.format(asiaZonedDateTime));
		return format.format(asiaZonedDateTime);
	}
	
	static boolean InsertFileDuplicate (String tagId, String fileName,Connection con) {
		String currentDateTime = getCurrentDateTime();
		boolean flag = false;
		PreparedStatement stmt = null;
		int rs = 0;
		String sql = "INSERT INTO [Record]..DuplicateFile VALUES (?,?,?)";
		try {

			System.out.println(sql);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tagId);
			stmt.setString(2, fileName);
			stmt.setString(3, currentDateTime);		
			rs = stmt.executeUpdate();
			// logger.debug(rs + "records updated");
			System.out.println(rs + "records updated");
			flag = true;

		} catch (Exception ex) {
			// logger.debug("Error duplicate : " + ex.getMessage());
			System.out.println("Error duplicate : " + ex.getMessage());
		} finally {
			// logger.debug("Done.");
			System.out.println("Done.");
		}
		return flag;
	}
}
