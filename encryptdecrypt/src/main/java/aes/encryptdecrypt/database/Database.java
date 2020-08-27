package aes.encryptdecrypt.database;

import java.sql.Connection;

public class Database {
	public String ipAddress;
	public String userName;
	public String userPassword;
	public String port;
	public Connection conn;
	public Database(String ipAddress, String userName, String userPassword, String port, Connection conn) {
		super();
		this.ipAddress = ipAddress;
		this.userName = userName;
		this.userPassword = userPassword;
		this.port = port;
		this.conn = conn;
	}
	public Database() {
		
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	
	
}
