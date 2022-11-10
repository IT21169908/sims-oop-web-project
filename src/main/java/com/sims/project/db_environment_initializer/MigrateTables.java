/**
 * 
 */
package com.sims.project.db_environment_initializer;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.sims.project.tables.GradesTable;
import com.sims.project.tables.LeaveRequestTable;
import com.sims.project.tables.StudentTable;
import com.sims.project.tables.SubjectsTable;
import com.sims.project.tables.UpdateUserTable;
import com.sims.project.tables.UsersTable;
import com.sims.project.tables.TeacherSubjectsTable;

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
        new SubjectsTable(stmt);
        new GradesTable(stmt);
        new StudentTable(stmt);
        new UpdateUserTable(stmt);
        new TeacherSubjectsTable(stmt);
        
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
