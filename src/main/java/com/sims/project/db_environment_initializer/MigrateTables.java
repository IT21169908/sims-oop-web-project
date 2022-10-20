/**
 * 
 */
package com.sims.project.db_environment_initializer;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.sims.project.tables.LeaveRequestTable;
import com.sims.project.tables.UsersTable;

/**
*
* @author maneesh
*/
public class MigrateTables extends DatabaseEnvironment {
		
    /**
     * @throws SQLException 
	 * @see addTables(Statement stmt)
     * @addTables - Add new created table classes here
     */
	private static void addTables(Statement stmt) throws SQLException {

		// List new table class here
		new UsersTable(stmt);
		new LeaveRequestTable(stmt);
//		new UsersTable(stmt);
        
	}
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Statement stmt = null; 
        
        try {
        	DatabaseEnvironment.getConnection();
            
            System.out.println("Connection established......");
        	
            stmt = CON.createStatement();
            
            addTables(stmt);

            JOptionPane.showMessageDialog(null, "Tables created successfully.!");
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            
        } finally {
            try {
            	if (CON != null) {
            		CON.close();
        		}
            	if (CON != null) {
            		stmt.close();
            	}

            
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

}
