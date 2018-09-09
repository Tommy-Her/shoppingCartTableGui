/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcarttablegui;


import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
/**
 *
 * @author vangu
 */
public class loginPane extends JDialog{


    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JButton cancelButton;
    private boolean correctLogin;

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                loginPane newLoginPane = new loginPane();
            }
        });
    }
    
    public boolean isCorrectLogin() {
        return correctLogin;
    }
    
    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }    
    
    public loginPane(Frame parent){
        super(parent, "Login", true);
        JPanel MainPanel = new JPanel(null);
        MainPanel.setSize(300, 300);
        
        
        userLabel = new JLabel("Username");
        userLabel.setLocation(100, 50);
        userLabel.setSize(75,30);
        MainPanel.add(userLabel);
        
        usernameField = new JTextField();
        usernameField.setLocation(175, 50);
        usernameField.setSize(100, 30);
        usernameField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    authenticateLogin(parent);
                    if(correctLogin){
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(loginPane.this,"Invalid Login","Login",JOptionPane.ERROR_MESSAGE);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        MainPanel.add(usernameField);
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setLocation(100, 85);
        passwordLabel.setSize(75,30);
        MainPanel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setLocation(175, 85);
        passwordField.setSize(100, 30);
        passwordField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    authenticateLogin(parent);
                    if(correctLogin){
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(loginPane.this,"Invalid Login","Login",JOptionPane.ERROR_MESSAGE);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        MainPanel.add(passwordField);
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                authenticateLogin(parent);
                if(correctLogin){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(loginPane.this,"Invalid Login","Login",JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
        loginButton.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    authenticateLogin(parent);
                    if(correctLogin){
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(loginPane.this,"Invalid Login","Login",JOptionPane.ERROR_MESSAGE);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        cancelButton.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                dispose();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        
        loginButton.setLocation(125,125);
        loginButton.setSize(75,30);
        cancelButton.setLocation(225,125);
        cancelButton.setSize(75,30);
        
        MainPanel.add(loginButton);
        MainPanel.add(cancelButton);
        add(MainPanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(400, 220);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    
    public loginPane(){
        super();
        JPanel MainPanel = new JPanel(null);
        MainPanel.setSize(300, 300);
        
        
        userLabel = new JLabel("Username");
        userLabel.setLocation(100, 50);
        userLabel.setSize(75,30);
        MainPanel.add(userLabel);
        
        usernameField = new JTextField();
        usernameField.setLocation(175, 50);
        usernameField.setSize(100, 30);
        usernameField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    authenticateLogin();
                    if(correctLogin){
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(loginPane.this,"Invalid Login","Login",JOptionPane.ERROR_MESSAGE);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        MainPanel.add(usernameField);
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setLocation(100, 85);
        passwordLabel.setSize(75,30);
        MainPanel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setLocation(175, 85);
        passwordField.setSize(100, 30);
        passwordField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    authenticateLogin();
                    if(correctLogin){
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(loginPane.this,"Invalid Login","Login",JOptionPane.ERROR_MESSAGE);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        MainPanel.add(passwordField);
        
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                authenticateLogin();
                if(correctLogin){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(loginPane.this,"Invalid Login","Login",JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                }
            }
        });
        loginButton.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    authenticateLogin();
                    if(correctLogin){
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(loginPane.this,"Invalid Login","Login",JOptionPane.ERROR_MESSAGE);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        cancelButton.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                dispose();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        
        loginButton.setLocation(125,125);
        loginButton.setSize(75,30);
        cancelButton.setLocation(225,125);
        cancelButton.setSize(75,30);
        
        MainPanel.add(loginButton);
        MainPanel.add(cancelButton);
        add(MainPanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(400, 220);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }    
    
    public void authenticateLogin(){
        if(usernameField.getText().equals("temp")){
            if(passwordField.getPassword().length != 0){
                char[] passwordString = passwordField.getPassword();
                String password = "";
                for(char character: passwordString){
                    password += character;
                }
                System.out.println(password);
                if(password.equals("temp")){
                    correctLogin = true;
                }
            }
            

        }
    }
    
    public void authenticateLogin(Frame parent){
        if(usernameField.getText().equals("temp")){
            if(passwordField.getPassword().length != 0){
                char[] passwordString = passwordField.getPassword();
                String password = "";
                for(char character: passwordString){
                    password += character;
                }
                System.out.println(password);
                if(password.equals("temp")){
                    correctLogin = true;
                    parent.setVisible(true);
                }
            }
            

        }
    }
}
