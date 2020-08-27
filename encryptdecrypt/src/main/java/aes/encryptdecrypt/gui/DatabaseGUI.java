package aes.encryptdecrypt.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;

import aes.encryptdecrypt.aes.EncryptDecrypt;
import aes.encryptdecrypt.database.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DatabaseGUI {
	public JFrame frame;
	public JLabel ipAddress, userName, userPassword, port;
	public JTextField t_ipAddress, t_userName, t_userPass, t_port;
	public JButton connect;
	
	public Connection conn = null;
	
	public DatabaseGUI() {
		Font f = new Font("New Times Roman",Font.BOLD,18);
        Font bBold = new Font("New Times Roman", Font.BOLD, 18);
        Font ft1 = new Font("Courier",Font.BOLD,80);
        
		frame = new JFrame("Advanced Encryption Standard Software");
        frame.setSize(600,400);
        frame.setLayout(null);
        
        ipAddress = new JLabel("IPAddress");
        ipAddress.setBounds(20, 30, 100, 30);
        
        userName = new JLabel("UserName");
        userName.setBounds(20, 70, 100, 30);
        
        userPassword = new JLabel("Password");
        userPassword.setBounds(20, 110, 100, 30);
        
        port = new JLabel("Port[3306]");
        port.setBounds(20, 150, 100, 30);
        
        t_ipAddress = new JTextField();
        t_ipAddress.setBounds(200, 30, 200, 30);
        
        t_userName = new JTextField();
        t_userName.setBounds(200, 70, 200, 30);
        
        t_userPass = new JTextField();
        t_userPass.setBounds(200, 110, 200, 30);
        
        t_port = new JTextField();
        t_port.setBounds(200, 150, 200, 30);
        
        connect = new JButton("Connect");
        connect.setBounds(250, 200, 100, 40);
        
        connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	Class.forName("com.mysql.jdbc.Driver");
                	conn = DriverManager.getConnection(
                			"jdbc:mysql://"+t_ipAddress.getText().toString()+
                			":"+t_port.getText().toString()+
                			"/AES?allowPublicKeyRetrieval=true&useSSL=false",
                			t_userName.getText().toString(), t_userPass.getText().toString());
                	Database database = new Database(
                			t_ipAddress.getText().toString(),
                			t_userName.getText().toString(),
                			t_userPass.getText().toString(),
                			t_port.getText().toString(),
                			conn);
                	database.setIpAddress(t_ipAddress.getText().toString());
                	database.setUserName(t_userName.getText().toString());
                	database.setUserPassword(t_userPass.getText().toString());
                	database.setPort(t_port.getText().toString());
                	database.setConn(conn);
                	if(conn != null) {
                		JOptionPane.showMessageDialog(null,"Connection Successfull");
                		new GUI();
                		frame.dispose();
                	}
                } catch(Exception exception) {
                	JOptionPane.showMessageDialog(null,exception.getMessage());
                	exception.printStackTrace();
                }
            }
        });
        
        frame.add(ipAddress);
        frame.add(userName);
        frame.add(userPassword);
        frame.add(port);
        
        frame.add(t_ipAddress);
        frame.add(t_userName);
        frame.add(t_userPass);
        frame.add(t_port);
        
        frame.add(connect);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);
	}
}
