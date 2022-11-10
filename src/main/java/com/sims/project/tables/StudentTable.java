package com.sims.project.tables;

import java.sql.SQLException;
import java.sql.Statement;

public class StudentTable {

   String sqlQuery = "CREATE TABLE IF NOT EXISTS `students` ("
         + " `id` int(11) unsigned NOT NULL AUTO_INCREMENT,"
         + " `user_id` int(11) unsigned NOT NULL,"
         + " `name` varchar(100) DEFAULT NULL,"
         + " `dob` date DEFAULT NULL,"
         + " `parent_name` varchar(100) NOT NULL,"
         + " `mobile_number` varchar(11) DEFAULT NULL,"
         + " `address` varchar(225) NOT NULL,"
         + " `created_at` datetime DEFAULT current_timestamp(),"
         + " `updated_at` datetime DEFAULT current_timestamp(),"
         + " PRIMARY KEY (`id`)," 
         + " CONSTRAINT `student_user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE"
         + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";

   public StudentTable(Statement stmt) {

      try {
         stmt.executeUpdate(sqlQuery);
         System.out.println("Student table created successfully.!");
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

}
