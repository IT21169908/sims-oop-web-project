package com.sims.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sims.configs.ConnectionProvider;
import com.sims.models.TeacherSubject;
import com.sims.services.interfaces.TeacherSubjectInterface;

public class TeacherSubjectService implements TeacherSubjectInterface {
   
   private final String TABLE = "teacher_subjects";

   /** Initialize logger */
   public static final Logger log = Logger.getLogger(TeacherSubjectService.class.getName());

   private static Connection connection;

   private PreparedStatement preparedStatement;

   @Override
   public boolean create(TeacherSubject teacher_subject) throws Exception {
      try {
         connection = ConnectionProvider.getConnection();

         preparedStatement = connection.prepareStatement("INSERT INTO `" + TABLE + "` (`teacher_id`, `subject_id`) VALUES (?,?)");
         connection.setAutoCommit(false);

         preparedStatement.setInt(1, teacher_subject.getTeacher_id());
         preparedStatement.setInt(2, teacher_subject.getSubject_id());

         preparedStatement.execute();
         connection.commit();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } 
      catch(Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG: TeacherSubjectService preparedStatement Exception =============================");
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
                  "==================== TeacherSubjectService preparedStatement DB CONNECTION CLOSED =============================");
         } 
         catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= TeacherSubjectService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }
   }

   public ArrayList<TeacherSubject> all() throws Exception {

      ArrayList<TeacherSubject> TeacherSubjectList = new ArrayList<TeacherSubject>();

      try {
         connection = ConnectionProvider.getConnection();

         preparedStatement = connection.prepareStatement("SELECT * FROM `" + TABLE + "`");

         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
            TeacherSubject subj = new TeacherSubject(resultSet);
            TeacherSubjectList.add(subj);
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
      return TeacherSubjectList;
   }

   @Override
   public boolean update(TeacherSubject teacher_subject) throws Exception {
      try {
         connection = ConnectionProvider.getConnection();
         preparedStatement = connection
               .prepareStatement(
                     "UPDATE `" + TABLE + "` SET `teacher_id`=?  WHERE `subject_id`=?");
         
         preparedStatement.setInt(1, teacher_subject.getTeacher_id());
         preparedStatement.setInt(2, teacher_subject.getSubject_id());
         preparedStatement.executeUpdate();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } 
      catch (Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG: TeacherSubjectService preparedStatement Exception =============================");
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
                  "==================== TeacherSubjectService preparedStatement DB CONNECTION CLOSED =============================");
         } 
         catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= TeacherSubjectService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }

   }

   @Override
   public boolean destroy(String teacher_id, String subject_id) throws Exception {
      
      if (teacher_id != null && !teacher_id.isEmpty() && subject_id != null && !subject_id.isEmpty()) {
         try {
            connection = ConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM `" + TABLE + "` WHERE `teacher_id` = ? AND `subject_id` = ?");
            preparedStatement.setString(1, teacher_id);
            preparedStatement.setString(2, subject_id);
            preparedStatement.executeUpdate();

            log.log(Level.INFO, "LOG: TeacherSubjectService - SUBJECT DESTROYED");
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
   
   @Override
   public boolean destroy(String subject_id) throws Exception {
      
      if (subject_id != null && !subject_id.isEmpty()) {
         try {
            connection = ConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM `" + TABLE + "` WHERE `subject_id` = ?");
            preparedStatement.setString(1, subject_id);
            preparedStatement.executeUpdate();

            log.log(Level.INFO, "LOG: TeacherSubjectService - SUBJECT DESTROYED");
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
   
   @Override
   public ArrayList<TeacherSubject> allBySubject(int subject_id) throws Exception {
      ArrayList<TeacherSubject> TeacherSubjectList = new ArrayList<TeacherSubject>();
      
      try {
         connection = ConnectionProvider.getConnection();

         preparedStatement = connection.prepareStatement("SELECT * FROM `" + TABLE + "` WHERE `subject_id` = ?");
         preparedStatement.setInt(1, subject_id);

         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
            TeacherSubject teacherSubject = new TeacherSubject(resultSet.getInt(1));
            TeacherSubjectList.add(teacherSubject);
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
      return TeacherSubjectList;
      
   }
   
}
