package com.quatriz.DO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.quatriz.DAO.Services;
import com.quatriz.readFile.ListFile;

import connection.ConnectionString;

public class ListData {

	private String fileName, fileData, dateExecuted;
	public ArrayList dataList = new ArrayList();

	public void fetchOriginalFiles(ListFile passObj) {
		
		Connection con = null;
		ConnectionString conn = new ConnectionString();
		con = conn.connectToDB();
		
		String sql = "SELECT itemHead,item FROM ListData ";

		if (!"".equals(passObj.getData())&&"".equals(passObj.getFileName())) {
			//custom date data to meet the sql query
			String tagId = "";
			tagId = passObj.getData();
			System.out.println("tadId : "+tagId);
			sql = sql + "where item like '%"+tagId+"%'";
		}
		if (!"".equals(passObj.getFileName())&&"".equals(passObj.getData())) {
			//custom date data to meet the sql query
			String fileName = "";
			fileName = passObj.getFileName();
			System.out.println("fileName : "+fileName);			
			sql = sql + "where itemHead like '%"+fileName+"%'";
		}
		if (!"".equals(passObj.getData()) && !"".equals(passObj.getFileName())) {
			//custom date data to meet the sql query
			String fileName = "";
			fileName = passObj.getFileName();
			System.out.println("fileName : "+fileName);
			
			String tagId = "";
			tagId = passObj.getData();
			System.out.println("tadId : "+tagId);
			sql = sql + "where item like '%"+tagId+"%' and itemHead like '%"+fileName+"%'";
		}
		
		System.out.println(sql);

		try {
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				fileName = rs.getString("itemHead");
				fileData = rs.getString("item");
				ListEntry data = new ListEntry(fileName, fileData);
				synchronized (data) {
					dataList.add(data);

				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void fetchDuplicateFiles(ListFile passObj) {
		
		Connection con = null;
		ConnectionString conn = new ConnectionString();
		con = conn.connectToDB();
		
		String sql = "SELECT itemHead,item,DateExecuted FROM DuplicateFile ";

		if (!"".equals(passObj.getDateExecuted())&&"".equals(passObj.getData())&&"".equals(passObj.getFileName())) {
			//custom date data to meet the sql query
			String dd = ""; String mm = ""; String yy="";
			yy = passObj.getDateExecuted().substring(0,4);
			mm = passObj.getDateExecuted().substring(5,7);
			dd = passObj.getDateExecuted().substring(8,10);
			
			System.out.println("yy : "+yy);
			System.out.println("mm : "+mm);
			System.out.println("dd : "+dd);
			
			//sql = sql + "where datediff(day, DateExecuted, '"+mm+"/"+dd+"/"+yy+"') = 0";
			sql = sql + "where itemHead like '%"+dd+mm+yy+"%'";		
		}
		if (!"".equals(passObj.getData())&&"".equals(passObj.getDateExecuted())&&"".equals(passObj.getFileName())) {
			//custom date data to meet the sql query
			String tagId = "";
			tagId = passObj.getData();
			System.out.println("tadId : "+tagId);
			sql = sql + "where item like '%"+tagId+"%'";
		}
		if (!"".equals(passObj.getFileName())&&"".equals(passObj.getData())&&"".equals(passObj.getDateExecuted())) {
			//custom date data to meet the sql query
			String fileName = "";
			fileName = passObj.getFileName();
			System.out.println("fileName : "+fileName);			
			sql = sql + "where itemHead like '%"+fileName+"%'";
		}
		if (!"".equals(passObj.getData()) && !"".equals(passObj.getDateExecuted())) {
			//custom date data to meet the sql query
			String dd = ""; String mm = ""; String yy="";
			yy = passObj.getDateExecuted().substring(0,4);
			mm = passObj.getDateExecuted().substring(5,7);
			dd = passObj.getDateExecuted().substring(8,10);
			
			System.out.println("yy : "+yy);
			System.out.println("mm : "+mm);
			System.out.println("dd : "+dd);
			String tagId = "";
			tagId = passObj.getData();
			System.out.println("tadId : "+tagId);
			sql = sql + "where item like '%"+tagId+"%' and itemHead like '%"+dd+mm+yy+"%'";
		}
		if (!"".equals(passObj.getData()) && !"".equals(passObj.getFileName())) {
			//custom date data to meet the sql query
			String fileName = "";
			fileName = passObj.getFileName();
			System.out.println("fileName : "+fileName);
			
			String tagId = "";
			tagId = passObj.getData();
			System.out.println("tadId : "+tagId);
			sql = sql + "where item like '%"+tagId+"%' and itemHead like '%"+fileName+"%'";
		}
		if (!"".equals(passObj.getFileName()) && !"".equals(passObj.getDateExecuted())) {
			//custom date data to meet the sql query
			String dd = ""; String mm = ""; String yy="";
			yy = passObj.getDateExecuted().substring(0,4);
			mm = passObj.getDateExecuted().substring(5,7);
			dd = passObj.getDateExecuted().substring(8,10);
			
			System.out.println("yy : "+yy);
			System.out.println("mm : "+mm);
			System.out.println("dd : "+dd);
			String fileName = "";
			fileName = passObj.getFileName();
			System.out.println("fileName : "+fileName);
			sql = sql + "where itemHead like '%"+fileName+"%' and itemHead like '%"+dd+mm+yy+"%'";
		}
		if (!"".equals(passObj.getFileName()) && !"".equals(passObj.getDateExecuted()) && !"".equals(passObj.getData())) {
			//custom date data to meet the sql query
			String dd = ""; String mm = ""; String yy="";
			yy = passObj.getDateExecuted().substring(0,4);
			mm = passObj.getDateExecuted().substring(5,7);
			dd = passObj.getDateExecuted().substring(8,10);
			
			System.out.println("yy : "+yy);
			System.out.println("mm : "+mm);
			System.out.println("dd : "+dd);
			
			String fileName = "";
			fileName = passObj.getFileName();	
			System.out.println("fileName : "+fileName);			
			
			String tagId = "";
			tagId = passObj.getData();
			System.out.println("tadId : "+tagId);

			sql = sql + "and itemHead like '%"+fileName+"%' and item like '%"+tagId+"%' and itemHead like '%"+dd+mm+yy+"%'";
		}
		
		
		
		System.out.println(sql);
		/**
		 * if (!"".equals(passObj.getFileName())) { sql = sql + "where itemHead like '%"
		 * + passObj.getFileName() + "'"; if (!"".equals(passObj.getData())) sql = sql +
		 * "and item like '%" + passObj.getData() + "'"; else if
		 * (!"".equals(passObj.getDateExecuted())) sql = sql + "and DateExecuted like
		 * '%" + passObj.getDateExecuted() + "'"; } else if
		 * (!"".equals(passObj.getDateExecuted())) { sql = sql + "where DateExecuted
		 * like '%" + passObj.getDateExecuted() + "'"; } else if
		 * (!"".equals(passObj.getData())) { sql = sql + "where item like '%" +
		 * passObj.getData() + "'"; }
		 */

		try {
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				fileName = rs.getString("itemHead");
				fileData = rs.getString("item");
				dateExecuted = rs.getString("DateExecuted");

				ListEntry data = new ListEntry(fileName, fileData, dateExecuted);
				synchronized (data) {
					dataList.add(data);

				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileData() {
		return fileData;
	}

	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	public ArrayList getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}

}
