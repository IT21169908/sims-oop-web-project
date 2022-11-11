/**
 * 
 */
package com.sims.project.tables;

import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author M.M.N.H.Fonseka
 */
public class PeriodTable {
	
    public PeriodTable(Statement stmt) throws SQLException {
    	
    	String tableName = "users";
    	
        try {
            stmt.executeUpdate(
                  "CREATE TABLE IF NOT EXISTS `periods` ("
                  + " `id` int(10) unsigned NOT NULL AUTO_INCREMENT,"
                  + " `title` varchar(256) NOT NULL,"
                  + " `subject_id` int(10) unsigned DEFAULT NULL,"
                  + " `grade_id` int(10) unsigned DEFAULT NULL,"
                  + " `teacher_id` int(10) unsigned DEFAULT NULL,"
                  + " `start_time` time NOT NULL,"
                  + " `end_time` time NOT NULL,"
                  + " `day` enum('sunday','monday','tuesday','wednesday','thursday','friday','saturday') NOT NULL,"
                  + " `start_date` date NOT NULL,"
                  + " `end_date` date NOT NULL,"
                  + " `updated_at` datetime DEFAULT current_timestamp(),"
                  + " `created_at` datetime DEFAULT current_timestamp(),"
                  + " PRIMARY KEY (`id`),"
                  + " KEY `period_subjects_fk` (`subject_id`),"
                  + " KEY `period_teacher_fk` (`teacher_id`),"
                  + " KEY `period_grade_fk` (`grade_id`),"
                  + " CONSTRAINT `period_grade_fk` FOREIGN KEY (`grade_id`) REFERENCES `grades` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,"
                  + " CONSTRAINT `period_subjects_fk` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,"
                  + " CONSTRAINT `period_teacher_fk` FOREIGN KEY (`teacher_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE"
                  + ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4"
            );
            
            
            System.out.println(tableName + " table created successfully.!");
            
        } catch (SQLException e) {
        	e.printStackTrace();
            throw e;
            
        }
        
    }

}
