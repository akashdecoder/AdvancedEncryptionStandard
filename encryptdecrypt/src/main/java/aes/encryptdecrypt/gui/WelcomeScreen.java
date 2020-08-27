package aes.encryptdecrypt.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class WelcomeScreen {
	public JFrame frame;
	public WelcomeScreen() {
		frame = new JFrame("Advanced Encryption Standard Software");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(1000,800);
        frame.setLayout(null);
        
        new DatabaseGUI();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);
	}
	
}
