package com.sims.project.tables;

import java.sql.SQLException;
import java.sql.Statement;

public class StudentTable {

   String sqlQuery = "CREATE TABLE IF NOT EXISTS Student("
         + "id int(11) unsigned NOT NULL,"
         + "studentID INT AUTO_INCREMENT NOT NULL,"
         + "studentName VARCHAR(100)NOT NULL,"
         + "studentClass VARCHAR(10) NOT NULL,"
         + "studentDOB DATE NOT NULL,"
         + "studentParentName VARCHAR(100) NOT NULL,"
         + "studentMobileNumber VARCHAR(11) NOT NULL,"
         + "studentAddress VARCHAR(225) NOT NULL,"
         + "CONSTRAINT PRIMARY KEY(studentID),"
         + "CONSTRAINT FOREIGN KEY (id) REFERENCES users (id)"
         + "On UPDATE CASCADE On DELETE CASCADE"
         + ")ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4";

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
