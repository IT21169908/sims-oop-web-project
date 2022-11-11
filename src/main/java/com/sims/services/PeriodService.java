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
import com.sims.models.Period;
import com.sims.services.interfaces.PeriodInterface;

public class PeriodService implements PeriodInterface {

   /** Initialize logger */
   public static final Logger log = Logger.getLogger(PeriodService.class.getName());

   private static Connection connection;

   private PreparedStatement preparedStatement;

   public PeriodService() {
      connection = ConnectionProvider.getConnection();
   }

   @Override
   public boolean create(Period period) throws Exception {

      try {
         preparedStatement = connection
               .prepareStatement(
                     "INSERT INTO `periods`(`title`, `subject_id`, `grade_id`, `teacher_id`, `start_time`, `end_time`, `day`, `start_date`, `end_date`) VALUES (?,?,?,?,?,?,?,?,?)");
         connection.setAutoCommit(false);

         preparedStatement.setString(1, period.getTitle());
         preparedStatement.setInt(2, period.getSubject_id());
         preparedStatement.setInt(3, period.getGrade_id());
         preparedStatement.setInt(4, period.getTeacher_id());
         preparedStatement.setString(5, period.getStart_time());
         preparedStatement.setString(6, period.getEnd_time());
         preparedStatement.setString(7, period.getDay());
         preparedStatement.setString(8, period.getStart_date());
         preparedStatement.setString(9, period.getEnd_date());

         preparedStatement.execute();
         connection.commit();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } catch (Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG PeriodService preparedStatement Exception =============================");
         log.log(Level.SEVERE, e.getMessage());
         throw new Exception(e.getMessage());
      } finally {
         /*
          * Close prepared statement and database connectivity at the end of
          * transaction
          */
         try {

            if (preparedStatement != null) {
               preparedStatement.close();
            }
            if (connection != null) {
               connection.close();
            }
            log.log(Level.INFO,
                  "==================== PeriodService preparedStatement DB CONNECTION CLOSED =============================");
         } catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= PeriodService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }
   }

   @Override
   public ArrayList<Period> all() throws Exception {
      
      
      ArrayList<Period> periods = new ArrayList<Period>();

      try {
            
         preparedStatement = connection.prepareStatement("SELECT periods.*, subjects.title subject_title, grades.title grade_title, users.name teacher_name FROM `periods`, subjects, grades, users WHERE periods.subject_id = subjects.id AND periods.grade_id = grades.id AND periods.teacher_id = users.id ");

         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
            Period period = new Period(resultSet);
            periods.add(period);
         }

      } catch (Exception e) {
         log.log(Level.SEVERE, e.getMessage());
      } finally {
         /*
          * Close prepared statement and database connectivity at the end of
          * transaction
          */
         try {
            if (preparedStatement != null) {
               preparedStatement.close();
            }
            if (connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
         }
      }
      return periods;
   }

   @Override
   public boolean update(Period period) throws Exception {
      try { 
         preparedStatement = connection
               .prepareStatement(
                     "UPDATE  `periods` SET  `title` = ?, `subject_id` = ?, `grade_id` = ?, `teacher_id` = ?, `start_time` = ?, `end_time` = ?, `day` = ?, `start_date` = ?, `end_date` = ?, `updated_at` = ? WHERE `id` = ?");

         preparedStatement.setString(1, period.getTitle());
         preparedStatement.setInt(2, period.getSubject_id());
         preparedStatement.setInt(3, period.getGrade_id());
         preparedStatement.setInt(4, period.getTeacher_id());
         preparedStatement.setString(5, period.getStart_time());
         preparedStatement.setString(6, period.getEnd_time());
         preparedStatement.setString(7, period.getDay());
         preparedStatement.setString(8, period.getStart_date());
         preparedStatement.setString(9, period.getEnd_date());
         preparedStatement.setString(10, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
         preparedStatement.setInt(11, period.getId());
         preparedStatement.executeUpdate();

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } catch (Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG PeriodService preparedStatement Exception =============================");
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
                  "==================== PeriodService preparedStatement DB CONNECTION CLOSED =============================");
         } catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= PeriodService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }

   }

   @Override
   public boolean destroy(Integer id) throws Exception {
      if (id >= 0) {
         try {
            connection = ConnectionProvider.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM `periods` WHERE `id` = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            log.log(Level.INFO, "PERIOD DESTROYED");
            return true;
         } catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            return false;
         } finally {
            /*
             * Close prepared statement and database connectivity at the end
             * of transaction
             */
            try {
               if (preparedStatement != null) {
                  preparedStatement.close();
               }
               if (connection != null) {
                  connection.close();
               }
            } catch (SQLException e) {
               log.log(Level.SEVERE, e.getMessage());
            }
         }
      }
      return false;
   }

   @Override
   public Period search(Integer id) throws Exception {
      // TODO Auto-generated method stub
      return null;
   }

}
