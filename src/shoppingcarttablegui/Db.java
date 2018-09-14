/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcarttablegui;

import java.sql.SQLException;

/**
 *
 * @author vangu
 */
public class Db {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public static void processMessage(SQLException e){
        System.err.println("Error Message " + e.getMessage());
        
        System.err.println("Error Code " + e.getErrorCode());

        System.err.println("SQL State " + e.getSQLState());

    }
    
}
