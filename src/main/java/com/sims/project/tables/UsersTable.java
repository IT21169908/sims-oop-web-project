/**
 * 
 */
package com.sims.project.tables;

import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author maneesh
 */
public class UsersTable {
	
    public UsersTable(Statement stmt) throws SQLException {
    	
    	String tableName = "users";
    	
        try {
            stmt.executeUpdate(
                  "CREATE TABLE IF NOT EXISTS " + tableName + "("
                  + " id int(11) unsigned NOT NULL AUTO_INCREMENT, "
                  + " name varchar(100) NOT NULL, "
                  + " email varchar(100) NOT NULL, "
                  + " password varchar(255) NOT NULL, "
                  + " nic varchar(12) DEFAULT NULL, "
                  + " profile_photo varchar(150) DEFAULT NULL, "
                  + " type enum('admin','teacher','student','') DEFAULT NULL, "
                  + " email_verified tinyint(4) NOT NULL DEFAULT 0, "
                  + " email_verify_code varchar(8) DEFAULT NULL, "
                  + " password_reset_code varchar(255) DEFAULT NULL, "
                  + " created_at datetime DEFAULT current_timestamp(), "
                  + " updated_at datetime DEFAULT current_timestamp(), "
                  + " PRIMARY KEY (id), "
                  + " UNIQUE KEY email (email), "
                  + " UNIQUE KEY nic (nic) "
                  + ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4"
            );
            
            
            System.out.println(tableName + " table created successfully.!");
            
        } catch (SQLException e) {
        	e.printStackTrace();
            throw e;
            
        }
        
    }

}
