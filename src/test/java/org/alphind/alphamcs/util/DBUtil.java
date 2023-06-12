package org.alphind.alphamcs.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;;

public class DBUtil {

	public DBUtil() {
		// Loading the required JDBC Driver class
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private Connection getConnection(String connectionString) throws SQLException, ClassNotFoundException {
		// Creating a connection to the database
		return DriverManager.getConnection(connectionString);
	}

	public Map<String, String> executeQuery(String connectionString, String query) {
		Map<String, String> resultMap = new HashMap<>();
		try {
			Connection conn = getConnection(connectionString);
			// Executing SQL query and fetching the result
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			ResultSetMetaData meta = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					String key = meta.getColumnName(i);
					String value = rs.getString(key);
					resultMap.put(key, value);
				}
			}
			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	public void executeUpdate(String connectionString, String query) {
		try {
			Connection conn = getConnection(connectionString);
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executeSP(String connectionString, String storedProcedureName) {
		try {
			Connection conn = getConnection(connectionString);
			String SQL = "{call " + storedProcedureName + "(?)}";
			CallableStatement cs = conn.prepareCall(SQL);
			ResultSet rs = cs.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i <= meta.getColumnCount(); i++) {
					String key = meta.getColumnName(i);
					String value = rs.getString(key);
					System.out.println(key + " " + value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
