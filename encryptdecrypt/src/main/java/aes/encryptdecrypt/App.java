package aes.encryptdecrypt;

import javax.swing.SwingUtilities;

import aes.encryptdecrypt.gui.DatabaseGUI;

public class App {
    public static void main( String[] args ){
    	SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new DatabaseGUI();
            }
        });
    }
}
