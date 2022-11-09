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
import com.sims.models.Subject;
import com.sims.services.interfaces.SubjectInterface;

public class SubjectService implements SubjectInterface {
   
   private final String TABLE = "subjects";

   /** Initialize logger */
   public static final Logger log = Logger.getLogger(SubjectService.class.getName());

   private static Connection connection;

   private PreparedStatement preparedStatement;

   @Override
   public boolean create(Subject subject) throws Exception {
      try {
         connection = ConnectionProvider.getConnection();

         preparedStatement = connection.prepareStatement("INSERT INTO `" + TABLE + "` (`code`, `title`) VALUES (?,?)");
         connection.setAutoCommit(false);

         preparedStatement.setString(1, subject.getCode());
         preparedStatement.setString(2, subject.getTitle());

         preparedStatement.execute();
         connection.commit();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } 
      catch(Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG: SubjectService preparedStatement Exception =============================");
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
                  "==================== SubjectService preparedStatement DB CONNECTION CLOSED =============================");
         } 
         catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= SubjectService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }
   }

   public ArrayList<Subject> all() throws Exception {

      ArrayList<Subject> SubjectList = new ArrayList<Subject>();

      try {
         connection = ConnectionProvider.getConnection();

         preparedStatement = connection.prepareStatement("SELECT * FROM `" + TABLE + "`");

         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
            Subject subj = new Subject(resultSet);
            SubjectList.add(subj);
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
            if (connection != null) {
               connection.close();
            }
         } 
         catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
         }
      }
      return SubjectList;
   }

   @Override
   public boolean update(Subject subject) throws Exception {
      try {
         connection = ConnectionProvider.getConnection();
         preparedStatement = connection
               .prepareStatement(
                     "UPDATE `" + TABLE + "` SET `code`=?, `title`=?, `updated_at`=? WHERE `id`=?");
         
         preparedStatement.setString(1, subject.getCode());
         preparedStatement.setString(2, subject.getTitle());
         preparedStatement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
         preparedStatement.setInt(4, subject.getId());
         preparedStatement.executeUpdate();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } 
      catch (Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG: SubjectService preparedStatement Exception =============================");
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
                  "==================== SubjectService preparedStatement DB CONNECTION CLOSED =============================");
         } 
         catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= SubjectService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }

   }

   @Override
   public boolean destroy(String subject_id) throws Exception {
      
      if (subject_id != null && !subject_id.isEmpty()) {
         try {
            connection = ConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM `" + TABLE + "` WHERE `id` = ?");
            preparedStatement.setString(1, subject_id);
            preparedStatement.executeUpdate();

            log.log(Level.INFO, "LOG: SubjectService - SUBJECT DESTROYED");
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
