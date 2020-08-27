package aes.encryptdecrypt.database;

import java.sql.*;

public class DbFunctions {
	
	public static Connection getConnection(String ipAddress, String userName, 
			String userPassword, String port) {
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://"+ipAddress+
	    			":"+port+
	    			"/AES?allowPublicKeyRetrieval=true&useSSL=false",
	    			userName, userPassword);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return conn;
	}
	
	public static int createTable(Connection conn) {
		int status = 0;
		PreparedStatement pst;
		String sqlQuery;
		try {
			sqlQuery = "create table user(username varchar(1000), password varchar(30));";
			pst = conn.prepareStatement(sqlQuery);
			status = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	public static int insert(String encryptedUsername, String password) {
		int status  = 0;
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}
}
