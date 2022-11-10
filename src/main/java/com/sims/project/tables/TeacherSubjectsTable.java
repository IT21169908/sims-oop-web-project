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
public class TeacherSubjectsTable {
	
    public TeacherSubjectsTable(Statement stmt) throws SQLException {
    	
    	String tableName = "teacher_subjects";
    	
        try {
            stmt.executeUpdate(
                  " CREATE TABLE IF NOT EXISTS " + tableName + "("
                  + " teacher_id int(11) unsigned NOT NULL, "
                  + " subject_id int(11) unsigned NOT NULL, "
                  + " PRIMARY KEY (teacher_id,subject_id), "
                  + " KEY teacher_subjects_subjects_fk (subject_id), "
                  + " CONSTRAINT teacher_subjects_subjects_fk FOREIGN KEY (subject_id) REFERENCES subjects (id) ON DELETE CASCADE ON UPDATE CASCADE, "
                  + " CONSTRAINT teacher_subjects_teachers_fk FOREIGN KEY (teacher_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE "
                  + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4"
            );
            
            System.out.println(tableName + " table created successfully.!");
            
        } catch (SQLException e) {
        	e.printStackTrace();
            throw e;
            
        }
        
    }

}
