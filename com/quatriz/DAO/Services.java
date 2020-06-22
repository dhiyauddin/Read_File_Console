package com.quatriz.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBAction;
import connection.DBConn;

public class Services {

	DBAction action;
	DBConn con;

	public ResultSet fetchRecords(String sql) {
		ResultSet result = null;
		action = new DBAction();
		con = new DBConn() {

			@Override
			public void cleanConnection() throws Exception {
				// TODO Auto-generated method stub
			}
		};
		try {
			con.openConnection();
			action.setConn(con);
			action.setQuery(sql);
			result = action.getResultSet();

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
