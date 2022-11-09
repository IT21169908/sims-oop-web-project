/**
 * 
 */
package com.sims.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sims.configs.ConnectionProvider;
import com.sims.models.User;
import com.sims.services.interfaces.UserInterface;
import com.sims.utils.QueryBuilder;

/**
 * This is the User Service class
 * 
 * @author maneesh, nishadi
 */
public class UserService implements UserInterface {

   /** Initialize logger */
   public static final Logger log = Logger.getLogger(LeaveRequestService.class.getName());

   private static Connection connection;

   private PreparedStatement preparedStatement;

   @Override
   public User getUserByEmail(String email) {

      User emailUser = null;
      Connection con = null;

      try {
         con = ConnectionProvider.getConnection();
         ResultSet rSet = QueryBuilder.readData(con, "SELECT * FROM users WHERE email='" + email + "'");

         if (rSet != null) {
            if (rSet.next()) {
               int id = rSet.getInt(1);
               emailUser = new User(id);
            }
         }
         ConnectionProvider.close(con);

      } catch (Exception e) {
         e.printStackTrace();
      }

      return emailUser;
   }

   @Override
   public ArrayList<User> all() {
      ArrayList<User> userList = new ArrayList<User>();

      try {
         connection = ConnectionProvider.getConnection();

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
            if (connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage());
         }
      }
      return userList;
   }

}
