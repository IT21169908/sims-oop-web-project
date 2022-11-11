/**
 * 
 */
package com.sims.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sims.configs.ConnectionProvider;
import com.sims.models.Student;
import com.sims.models.User;
import com.sims.services.interfaces.StudentInterface;
import com.sims.services.interfaces.UserInterface;
import com.sims.utils.QueryBuilder;

/**
 * This is the User Service class
 * 
 * @author maneesh, nishadi, M.M.N.H.Fonseka
 */
public class UserService implements UserInterface {
   
   private final String TABLE = "users";

   /** Initialize logger */
   public static final Logger log = Logger.getLogger(UserService.class.getName());

   private static Connection connection;

   private PreparedStatement preparedStatement;

   @Override
   public User getUserByEmail(String email) {

      User emailUser = null;
//      Connection con = null;

      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();
         
         ResultSet rSet = QueryBuilder.readData(connection, "SELECT * FROM users WHERE email='" + email + "'");

         if (rSet != null) {
            if (rSet.next()) {
               int id = rSet.getInt(1);
               emailUser = new User(id);
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      } 
      /** 
       ** No need to close the connection for "SELECT queries" 
         ** when using the singleton pattern for "ConnectionProvider"
      **/
      // finally {
      //    try {
      //       if (connection != null) {
      //          connection.close();
      //       } 
      //      catch (SQLException e) {
      //         e.printStackTrace();
      //      }
      //   }
      return emailUser;
   }

   @Override
   public ArrayList<User> all() {
      ArrayList<User> userList = new ArrayList<User>();

      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();

         preparedStatement = connection.prepareStatement("SELECT * FROM `users` WHERE type <> 'admin'");

         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
            User user = new User(resultSet);
            userList.add(user);
         }

      } catch (Exception e) {
         log.log(Level.SEVERE, e.getMessage());
      } finally {
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
         } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
         }
      }
      return userList;
   }

   @Override
   public boolean create(User user) throws Exception {
      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();

         preparedStatement = connection.prepareStatement(
               "INSERT INTO `users`(`name`,`email`,`mobile_number`,`password`,`nic`,`gender`,`dob`,`type`) VALUES(?,?,?,?,?,?,?,?)",
               Statement.RETURN_GENERATED_KEYS);
         connection.setAutoCommit(false);

         preparedStatement.setString(1, user.getName());
         preparedStatement.setString(2, user.getEmail());
         preparedStatement.setString(3, user.getMobile_number());
         preparedStatement.setString(4, user.getPassword());
         preparedStatement.setString(5, user.getNic());
         preparedStatement.setString(6, user.getGender());
         preparedStatement.setString(7, user.getDob());
         preparedStatement.setString(8, user.getType());

         preparedStatement.execute();

         ResultSet createdUser = preparedStatement.getGeneratedKeys();
         createdUser.next();
         int user_id = createdUser.getInt(1);
         System.out.println("LOG USERSERVICE: CREATED USER ID = " + user_id);
         connection.commit();

         if (user.getType().equals("student")) {
            Student student = new Student();
            StudentInterface studentService = new StudentService();
            student.setUser_id(user_id);
            student.setParent_name("sample name for parent");// TODO: GeT data from user
            student.setAddress("sample address");

            studentService.create(student);
         }

         if (user.getType().equals("teacher")) {
            // TODO: save teacher
         }

         log.log(Level.INFO, "==================== SQL EXECUTED SUCCESSFULLY =============================");
         return true;
      } catch (Exception e) {
         log.log(Level.SEVERE,
               "==================== LOG: SubjectService preparedStatement Exception =============================");
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
                  "==================== SubjectService preparedStatement DB CONNECTION CLOSED =============================");
         } catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= SubjectService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }
   }

   @Override
   public boolean update(User user) throws Exception {
      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();
         
         preparedStatement = connection
               .prepareStatement(
                     "UPDATE `users` SET `name` = ?, `email` = ?,  `nic` = ?,  `mobile_number` = ?, `gender` = ?, `dob` = ?, `updated_at` = ? WHERE `id` = ? ");

         String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

         preparedStatement.setString(1, user.getName());
         preparedStatement.setString(2, user.getEmail());
         preparedStatement.setString(3, user.getNic());
         preparedStatement.setString(4, user.getMobile_number());
         preparedStatement.setString(5, user.getGender());
         preparedStatement.setString(6, user.getDob());
         preparedStatement.setString(7, now);
         preparedStatement.setInt(8, user.getId());
         preparedStatement.executeUpdate();

         if (user.getType().equals("student")) {
            Student student = new Student();
            StudentInterface studentService = new StudentService();
            student.setUser_id(user.getId());
            student.setParent_name(now + "update sample name for parent"); // TODO: GeT data from user
            student.setAddress(now + "update sample address");

            studentService.update(student);
         }

         if (user.getType().equals("teacher")) {
            // TODO: save teacher
         }

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
                  "==================== UserService preparedStatement DB CONNECTION CLOSED =============================");
         } catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= UserService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }

   }

   @Override
   public boolean destroy(int user_id) throws Exception {
      if (user_id != 0 && user_id > 0) {
         try {

            ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
            connection = connectionProvider.getConnection();
         
            preparedStatement = connection.prepareStatement("DELETE FROM `users` WHERE `id` = ?");
            preparedStatement.setInt(1, user_id);
            preparedStatement.executeUpdate();

            log.log(Level.INFO, "LOG: UserService - USER DESTROYED");
            return true;
         } catch (Exception e) {
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
            } catch (SQLException e) {
               log.log(Level.SEVERE, e.getMessage());
            }
         }
      }
      return false;
   }

	@Override
	public ArrayList<User> allByType(String user_type) throws Exception {
	   ArrayList<User> TeachersList = new ArrayList<User>();
	   
	   try {

	      ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
	      connection = connectionProvider.getConnection();

          preparedStatement = connection.prepareStatement("SELECT * FROM `" + TABLE + "` WHERE `type` = ?");
          preparedStatement.setString(1, user_type);

          ResultSet resultSet = preparedStatement.executeQuery();

          while (resultSet.next()) {
             User user = new User(resultSet.getInt(1));
             TeachersList.add(user);
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
            //  if (connection != null) {
            //     connection.close();
            //  }
          } 
          catch (SQLException e) {
             log.log(Level.SEVERE, e.getMessage());
          }
       }
       return TeachersList;
	   
	}

   public boolean changePassword(User user) throws Exception {
      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();
         
         preparedStatement = connection
               .prepareStatement(
                     "UPDATE `users` SET `password` = ? , updated_at = ? WHERE `id` = ? ");

         String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
 
         preparedStatement.setString(1, user.getPassword());
         preparedStatement.setString(2, now);
         preparedStatement.setInt(3, user.getId());
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
                  "==================== UserService preparedStatement DB CONNECTION CLOSED =============================");
         } catch (SQLException e) {
            log.log(Level.SEVERE,
                  "================= UserService preparedStatement DB CONNECTION CLOSE FAILED ==========================");
            log.log(Level.SEVERE, e.getMessage());
         }
      }
   }

}
