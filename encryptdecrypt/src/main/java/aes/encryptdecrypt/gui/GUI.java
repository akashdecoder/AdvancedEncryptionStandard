package aes.encryptdecrypt.gui;

import aes.encryptdecrypt.aes.*;
import aes.encryptdecrypt.database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class GUI {
    public JFrame frame;
    public JLabel l_text, l_pass;
    public JPasswordField t_pass;
    public JTextField t_text;
    public JButton encrypt, decrypt;
    public JLabel encrypted_text;
    public JTextField t_encrypted;
    public JLabel decrypted_text;
    public JTextField t_decrypted;
    public String encrypted_text64;

    public Connection conn = null;
    public PreparedStatement pst;

    public int count = 0;
    
    public String OUTPUT_FORMAT = "%-30s:%s";

    public GUI(){
        Font f = new Font("New Times Roman",Font.BOLD,18);
        Font bBold = new Font("New Times Roman", Font.BOLD, 18);
        Font ft1 = new Font("Courier",Font.BOLD,80);

        frame = new JFrame("Advanced Encryption Standard Software");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(1000,600);
        frame.setLayout(null);


        l_text = new JLabel("Enter Plain Text ");
        l_text.setFont(new Font("New Times Roman", Font.BOLD, 18));
        l_text.setBounds(60, 40, 200, 30);

        l_pass = new JLabel("Enter Password ");
        l_pass.setFont(new Font("New Times Roman", Font.BOLD, 18));
        l_pass.setBounds(60, 80, 200, 30);

        t_text = new JTextField();
        t_text.setFont(f);
        t_text.setBounds(280, 40, 600, 30 );

        t_pass = new JPasswordField();
        t_pass.setFont(f);
        t_pass.setBounds(280, 80, 600, 30 );

        encrypt = new JButton("Encrypt");
        encrypt.setFont(bBold);
        encrypt.setBounds(250, 140, 200, 40);

        decrypt = new JButton("Decrypt");
        decrypt.setFont(bBold);
        decrypt.setBounds(550, 140, 200, 40);

        encrypted_text = new JLabel("Encrypted Text");
        encrypted_text.setFont(new Font("New Times Roman", Font.BOLD, 18));
        encrypted_text.setBounds(60, 200, 200, 30);

        t_encrypted = new JTextField();
        t_encrypted.setEditable(false);
        t_encrypted.setBounds(280, 200, 600, 30);

        decrypted_text = new JLabel("Decrypted Text");
        decrypted_text.setFont(new Font("New Times Roman", Font.BOLD, 18));
        decrypted_text.setBounds(60, 240, 200, 30);

        t_decrypted = new JTextField();
        t_decrypted.setEditable(false);
        t_decrypted.setBounds(280, 240, 600, 30);
        encrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	if(t_text.getText().isEmpty() || t_pass.getText().isEmpty()) {
                		t_encrypted.setText("");
                		t_decrypted.setText("");
                		JOptionPane.showMessageDialog(null,"Empty Field");
                		count = 0;
                	} else if(count == 0){
                		encrypted_text64 =
                                EncryptDecrypt.encrypt(t_text.getText().getBytes(EncryptDecrypt.UTF_8), t_pass.getText());
                        t_encrypted.setText(encrypted_text64);
                        count += 1;
                        Database db = new Database();
                        System.out.println(db.getIpAddress());
                        conn = db.getConn();
                        pst = conn.prepareStatement(
                        		"insert into user(username, password) values(?,?)");
                        pst.setString(1, encrypted_text64);
                        pst.setString(2, t_pass.getText().toString());
                        int status = pst.executeUpdate();
                	}
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        decrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	if(t_text.getText().isEmpty() || t_pass.getText().isEmpty()) {
                		t_encrypted.setText("");
                		t_decrypted.setText("");
                		JOptionPane.showMessageDialog(null,"Empty Field");
                		count = 0;
                	} else if(count == 1) {
                		String decrypted_text = EncryptDecrypt.decrypt(encrypted_text64, t_pass.getText());
                        t_decrypted.setText(decrypted_text);
                	}
                } catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        frame.add(l_text);
        frame.add(l_pass);
        frame.add(t_text);
        frame.add(t_pass);
        frame.add(encrypt);
        frame.add(decrypt);
        frame.add(encrypted_text);
        frame.add(t_encrypted);
        frame.add(decrypted_text);
        frame.add(t_decrypted);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}