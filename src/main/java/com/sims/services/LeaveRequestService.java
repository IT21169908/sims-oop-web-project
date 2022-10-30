package com.sims.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sims.configs.ConnectionProvider;
import com.sims.models.LeaveRequest;
import com.sims.services.interfaces.LeaveRequestInterface;

public class LeaveRequestService implements LeaveRequestInterface {

   /** Initialize logger */
   public static final Logger log = Logger.getLogger(LeaveRequestService.class.getName());
   
   private static Connection connection;

   private PreparedStatement preparedStatement;
    
   
   @Override
   public boolean create(LeaveRequest leave_req) {
      
      try {
          connection = ConnectionProvider.getConnection();
          /*
           * Query is available in EmployeeQuery.xml file and use
           * insert_employee key to extract value of it
           */
          preparedStatement = connection
                  .prepareStatement("INSERT INTO `leave_requests`(`user_id`, `date`, `days_count`, `reason`, `status` ) VALUES (?,?,?,?,?)");
          connection.setAutoCommit(false);
          
          preparedStatement.setInt(1, leave_req.getUser_id());
          preparedStatement.setString(2, leave_req.getDate());
          preparedStatement.setString(3, leave_req.getDays_count());
          preparedStatement.setString(4, leave_req.getReason()); 
          preparedStatement.setString(5, leave_req.getStatus()); 
          
          // Add employee
          preparedStatement.execute();
          connection.commit();
          
          log.log(Level.INFO,"==================== SQL EXECUTED SUCCESSFULLY ============================="); 
          return true;
      } catch (Exception e) {
          log.log(Level.SEVERE, "==================== LOG LeaveRequestService preparedStatement Exception ============================="); 
          log.log(Level.SEVERE, e.getMessage()); 
          return false;
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
              log.log(Level.INFO, "==================== LeaveRequestService preparedStatement DB CONNECTION CLOSED ============================="); 
          } catch (SQLException e) {
              log.log(Level.SEVERE, "================= LeaveRequestService preparedStatement DB CONNECTION CLOSE FAILED =========================="); 
              log.log(Level.SEVERE, e.getMessage());
          }
      }
   }

   public ArrayList<LeaveRequest> all(){
    
      ArrayList<LeaveRequest> LeaveRequestList = new ArrayList<LeaveRequest>();
      
      try {
         connection = ConnectionProvider.getConnection();
         
         preparedStatement = connection.prepareStatement("SELECT * FROM `leave_requests`");
          
         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {
           LeaveRequest lRequest = new LeaveRequest(resultSet);
           LeaveRequestList.add(lRequest);
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
      return LeaveRequestList;
   }
}
