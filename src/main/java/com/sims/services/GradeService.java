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
import com.sims.models.Grade;
import com.sims.services.interfaces.GradeInterface;

public class GradeService implements GradeInterface {
   
   private final String TABLE = "grades";

   /** Initialize logger */
   public static final Logger log = Logger.getLogger(GradeService.class.getName());

   private static Connection connection;

   private PreparedStatement preparedStatement;

   @Override
   public boolean create(Grade grade) throws Exception {
      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection(); 

         preparedStatement = connection.prepareStatement("INSERT INTO `" + TABLE + "` (`title`) VALUES (?)");
         connection.setAutoCommit(false);

         preparedStatement.setString(1, grade.getTitle());

         preparedStatement.execute();
         connection.commit();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } 
      catch(Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG: GradeService preparedStatement Exception =============================");
         log.log(Level.SEVERE, e.getMessage());
         throw new Exception(e.getMessage());
      } 
      finally {
         // Close prepared statement and database connectivity at the end of transaction
         try {
            if (preparedStatement != null) {
               preparedStatement.close();
            }
            if (connection != null) {
               connection.close();
            }
            log.log(Level.INFO,
                  "==================== GradeService preparedStatement DB CONNECTION CLOSED =============================");
         } 
         catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= GradeService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }
   }

   public ArrayList<Grade> all() throws Exception {

      ArrayList<Grade> GradeList = new ArrayList<Grade>();

      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();
         
         preparedStatement = connection.prepareStatement("SELECT * FROM `" + TABLE + "`");

         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
            Grade grade = new Grade(resultSet);
            GradeList.add(grade);
         }

      } 
      catch (Exception e) {
         log.log(Level.SEVERE, e.getMessage());
         throw new Exception(e.getMessage());
      }
      finally {
         // Close prepared statement and database connectivity at the end of transaction
         try {
            if (preparedStatement != null) {
               preparedStatement.close();
            }
            /** 
             ** No need to close the connection for "SELECT queries" 
            ** when using the singleton pattern for "ConnectionProvider"
            **/
            // if (connection != null) {
            //    connection.close();
            // }
         } 
         catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
         }
      }
      return GradeList;
   }

   @Override
   public boolean update(Grade grade) throws Exception {
      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();
         
         preparedStatement = connection
               .prepareStatement(
                     "UPDATE `" + TABLE + "` SET `title`=?, `updated_at`=? WHERE `id`=?");
         
         preparedStatement.setString(1, grade.getTitle());
         preparedStatement.setString(2, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
         preparedStatement.setInt(3, grade.getId());
         preparedStatement.executeUpdate();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } 
      catch (Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG: GradeService preparedStatement Exception =============================");
         log.log(Level.SEVERE, e.getMessage());
         throw new Exception(e.getMessage());
      } 
      finally {
         try {
            if (preparedStatement != null) {
               preparedStatement.close();
            }
            if (connection != null) {
               connection.close();
            }
            log.log(Level.INFO,
                  "==================== GradeService preparedStatement DB CONNECTION CLOSED =============================");
         } 
         catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= GradeService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }

   }

   @Override
   public boolean destroy(String grade_id) throws Exception {
      
      if (grade_id != null && !grade_id.isEmpty()) {
         try {

            ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
            connection = connectionProvider.getConnection();
         
            preparedStatement = connection.prepareStatement("DELETE FROM `" + TABLE + "` WHERE `id` = ?");
            preparedStatement.setString(1, grade_id);
            preparedStatement.executeUpdate();

            log.log(Level.INFO, "LOG: GradeService - GRADE DESTROYED");
            return true;
         } 
         catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            throw new Exception(e.getMessage());
         } 
         finally {
            // Close prepared statement and database connectivity at the end of transaction
            try {
               if (preparedStatement != null) {
                  preparedStatement.close();
               }
               if (connection != null) {
                  connection.close();
               }
            } 
            catch (SQLException e) {
               log.log(Level.SEVERE, e.getMessage());
            }
         }
      }
      return false;
   }
   
}
