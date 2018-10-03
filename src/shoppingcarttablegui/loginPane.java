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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author vangu
 */
public class loginPane extends JDialog{


    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel addUserLabel;
    private JButton loginButton;
    private JButton cancelButton;
    private JRadioButton addUserButton;
    private boolean newUser;
    private boolean correctLogin;
    
    private static int userSequence = 0;

    
    private static final String DBNAME = "shoppingcart";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING = "jdbc:mysql://localhost/" + DBNAME + "?user=" + USERNAME + "&password=" + PASSWORD + "&useSSL=false";

    public static void main(String[] args) throws SQLException{
        
            Connection conn = null;
            Statement stmt1 = null;
            Statement stmt2 = null;
            ResultSet rs1 = null;
            ResultSet rs2 = null;
            
            try {                
                conn = DriverManager.getConnection(CONN_STRING);
                System.out.println("Connected!");
                
                /* 
                ResultSet.TYPE_SCROLL_INSENSITIVE : allows for the connection to scroll up and down the database 
                and is not sensitive to changes in the database. (READ ONLY) 
                
                ResultSet.CONCUR_READ_ONLY : (READ ONLY)
                */
                
                // THIS IS AN EXAMPLE OF HOW TO CREATE A QUERY
                
                
                stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1 = stmt1.executeQuery("select * from client_order order by order_id desc limit 1");
                rs1.last();
                System.out.println("Last row of orders = " + rs1.getInt("order_id"));
                
                stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs2 = stmt2.executeQuery("select * from users order by client_id desc limit 1");
                if(rs2.next() == false){
                    System.out.println("Last row of users = 0");
                    userSequence = 0;
                }
                else{
                    rs2.last();
                    System.out.println("Last row of users = " + rs2.getInt("client_id"));
                    userSequence = rs2.getInt("client_id");
                }
                
                
            } catch (SQLException ex) {

                //shows the error message, code, and SQL state
                Db.processMessage(ex);
                
            } finally {
                if(conn != null){
                    conn.close();
                }
                if(rs1 != null){
                    rs1.close();
                }
                if(stmt1 != null){
                    stmt1.close();
                }
                if(rs2 != null){
                    rs2.close();
                }
                if(stmt2 != null){
                    stmt2.close();
                }
            }
        
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
        
        addUserLabel = new JLabel("New User?");
        addUserLabel.setLocation(245, 50);
        addUserLabel.setSize(75,30);
        MainPanel.add(addUserLabel);
        
        addUserButton = new JRadioButton();
        addUserButton.setLocation(220, 50);
        addUserButton.setSize(25, 25);
        addUserButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(newUser == false){
                    newUser = true;
                    loginButton.setText("Sign Up");
                }
                else{
                    newUser = false;
                    loginButton.setText("Login");
                }
                
            }
        });
        MainPanel.add(addUserButton);
        
        // moving all components to the left 75 units
        
        userLabel = new JLabel("Username");
        userLabel.setLocation(25, 50);
        userLabel.setSize(75,30);
        MainPanel.add(userLabel);
        
        usernameField = new JTextField();
        usernameField.setLocation(100, 50);
        usernameField.setSize(100, 30);
        usernameField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        authenticateLogin();
                    } catch (SQLException ex) {
                        Logger.getLogger(loginPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
        passwordLabel.setLocation(25, 85);
        passwordLabel.setSize(75,30);
        MainPanel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setLocation(100, 85);
        passwordField.setSize(100, 30);
        passwordField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    try {
                        authenticateLogin();
                    } catch (SQLException ex) {
                        Logger.getLogger(loginPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                if(newUser){
                    try {
                        createNewUser();
                    } catch (SQLException ex) {
                        Logger.getLogger(loginPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("HELLO");
                    return;
                }
                try {
                    authenticateLogin();
                } catch (SQLException ex) {
                    Logger.getLogger(loginPane.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                    try {
                        authenticateLogin();
                    } catch (SQLException ex) {
                        Logger.getLogger(loginPane.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
        
        loginButton.setLocation(100,125);
        loginButton.setSize(100,30);
        cancelButton.setLocation(225,125);
        cancelButton.setSize(100,30);
        
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
    
    public void createNewUser() throws SQLException{
        
            Connection conn = null;
            Statement stmt1 = null;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
            LocalDateTime now = LocalDateTime.now();  
            String password = "";
            
            try {                
                conn = DriverManager.getConnection(CONN_STRING);
                System.out.println("Connected!");
                
                /* 
                ResultSet.TYPE_SCROLL_INSENSITIVE : allows for the connection to scroll up and down the database 
                and is not sensitive to changes in the database. (READ ONLY) 
                
                ResultSet.CONCUR_READ_ONLY : (READ ONLY)
                */
                
                // THIS IS AN EXAMPLE OF HOW TO CREATE A QUERY
                
                if(passwordField.getPassword().length != 0){
                    char[] passwordString = passwordField.getPassword();
                    for(char character: passwordString){
                        password += character;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(loginPane.this,"Invalid Credentials","Login",JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                    return;
                }
                userSequence++;
                stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                System.out.println("insert into users values (" + userSequence + ",'" + dtf.format(now) + "','" + usernameField.getText() + "','" + password + "')");
                stmt1.executeUpdate("insert into users values (" + userSequence + ",'" + dtf.format(now) + "','" + usernameField.getText() + "','" + password + "')");
                System.out.println("Inserted " + usernameField.getText()+ " into the database");
                correctLogin = true;
                
            } catch (SQLException ex) {

                //shows the error message, code, and SQL state
                Db.processMessage(ex);
                
            } finally {
                if(conn != null){
                    conn.close();
                }
                if(stmt1 != null){
                    stmt1.close();
                }
            }
    }
    
    public void authenticateLogin() throws SQLException{
        
            Connection conn = null;
            Statement stmt1 = null;
            String password = "";
            
            try {                
                conn = DriverManager.getConnection(CONN_STRING);
                System.out.println("Connected!");
                
                /* 
                ResultSet.TYPE_SCROLL_INSENSITIVE : allows for the connection to scroll up and down the database 
                and is not sensitive to changes in the database. (READ ONLY) 
                
                ResultSet.CONCUR_READ_ONLY : (READ ONLY)
                */
                
                // THIS IS AN EXAMPLE OF HOW TO CREATE A QUERY
                
                if(passwordField.getPassword().length != 0){
                    char[] passwordString = passwordField.getPassword();
                    for(char character: passwordString){
                        password += character;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(loginPane.this,"Invalid Login","Login",JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                    return;
                }
                userSequence++;
                stmt1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                stmt1.executeQuery(""); // write a query checking the username as well as the password and if they match keep the client_id
                correctLogin = true;
                
            } catch (SQLException ex) {

                //shows the error message, code, and SQL state
                Db.processMessage(ex);
                
            } finally {
                if(conn != null){
                    conn.close();
                }
                if(stmt1 != null){
                    stmt1.close();
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
                if(password.equals("temp")){
                    correctLogin = true;
                    parent.setVisible(true);
                }
            }
            

        }
    }
}
