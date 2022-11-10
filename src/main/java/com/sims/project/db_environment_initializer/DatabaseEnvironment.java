/**
 * 
 */
package com.sims.project.db_environment_initializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
*
* This class data members set from "env.properties" file
*  -> WEB-INF/classes/environments/env.properties
*
* @author maneesh
*/
public class DatabaseEnvironment {
	
    protected static String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306";
    protected static String DB_NAME = "sims_db";
    protected static String DB_USERNAME = "root";
    protected static String DB_PASSWORD = "";
    
    public static String getDB_NAME() {
      return DB_NAME;
   }

   protected static Connection CON = null;
    
    protected static void getConnection(String createDatabase) throws ClassNotFoundException, SQLException {
    	
    	if ( createDatabase.equals("create_db") || createDatabase.equals("create") ) {
    		
    		if (MYSQL_URL.equals("") || DB_NAME.equals("") || DB_USERNAME.equals("")) {
        		JOptionPane.showMessageDialog(null, "First:  Please set variables (MYSQL_URL, DB_NAME, DB_USERNAME) from \"env.properties\" .!");
        		return;
        	}
        	
        	Class.forName("com.mysql.cj.jdbc.Driver");
            CON = DriverManager.getConnection(MYSQL_URL, DB_USERNAME, DB_PASSWORD);
    	}
    	
    }
    
    protected static void getConnection() throws ClassNotFoundException, SQLException {
    	
    	if (MYSQL_URL.equals("") || DB_NAME.equals("") || DB_USERNAME.equals("")) {
    		JOptionPane.showMessageDialog(null, "First:  Please set variables (MYSQL_URL, DB_NAME, DB_USERNAME) from \"env.properties\" .!");
    		return;
    	}
    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
        CON = DriverManager.getConnection(MYSQL_URL + "/" + DB_NAME, DB_USERNAME, DB_PASSWORD);
    	
    }

}
