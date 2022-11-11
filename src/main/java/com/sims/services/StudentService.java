package com.sims.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sims.configs.ConnectionProvider;
import com.sims.models.Student;
import com.sims.services.interfaces.StudentInterface;

public class StudentService implements StudentInterface {


   /** Initialize logger */
   public static final Logger log = Logger.getLogger(LeaveRequestService.class.getName());

   private static Connection connection;

   private PreparedStatement preparedStatement;
   
   @Override
   public boolean create(Student student) throws Exception { 
      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();

         preparedStatement = connection.prepareStatement(
               "INSERT INTO `students`(`parent_name`, `address`, `user_id`) VALUES(?,?,?)");
         connection.setAutoCommit(false);

         preparedStatement.setString(1, student.getParent_name());
         preparedStatement.setString(2, student.getAddress());
         preparedStatement.setInt(3, student.getUser_id()); 

         preparedStatement.execute();
         connection.commit();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } catch (Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG: StudentService preparedStatement Exception =============================");
         log.log(Level.SEVERE, e.getMessage());
         throw new Exception(e.getMessage());
      } finally {
         // Close prepared statement and database connectivity at the end of transaction
         try {
            if (preparedStatement != null) {
               preparedStatement.close();
            }
            if (connection != null) {
               connection.close();
            }
            log.log(Level.INFO,
                  "==================== StudentService preparedStatement DB CONNECTION CLOSED =============================");
         } catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= StudentService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }
   }
   
   @Override
   public ArrayList<Student> all() throws Exception {

      ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
      connection = connectionProvider.getConnection();

      PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Student");

      ResultSet rst = pstm.executeQuery();

      ArrayList<Student> studentList = new ArrayList<>();

      while (rst.next()) {

         Student st = new Student(rst.getInt(1),rst.getInt(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6),
               rst.getString(7), rst.getString(8),rst.getString(9));

         studentList.add(st);

      }

      return studentList;
      
   }

   @Override
   public boolean update(Student student) throws Exception {
      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();
         
         preparedStatement = connection
               .prepareStatement(
                     "UPDATE `students` SET   `parent_name`= ? ,`address`= ? ,`updated_at`= ? WHERE `user_id` = ?");

         preparedStatement.setString(1, student.getParent_name());
         preparedStatement.setString(2, student.getAddress()); 
         preparedStatement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
         preparedStatement.setInt(4, student.getUser_id());
         preparedStatement.executeUpdate();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } catch (Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG: UserService preparedStatement Exception =============================");
         log.log(Level.SEVERE, e.getMessage());
         throw new Exception(e.getMessage());
      } finally {
         try {
            if (preparedStatement != null) {
               preparedStatement.close();
            }
            if (connection != null) {
               connection.close();
            }
            log.log(Level.INFO,
                  "==================== StudentService preparedStatement DB CONNECTION CLOSED =============================");
         } catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= StudentService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }
   }

   @Override
   public boolean destroy(Integer id) throws Exception {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public Student search(Integer id) throws Exception {
      // TODO Auto-generated method stub
      return null;
   }

  

}
