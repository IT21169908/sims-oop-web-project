package com.sims.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sims.configs.ConnectionProvider;
import com.sims.utils.QueryBuilder;

/**
 * This is the LeaveRequest Model class
 * 
 * @author M.M.N.H.Fonseka
 */
public class LeaveRequest {
   
   private int id;
   private int user_id;
   private String date;
   private String days_count;
   private String reason;
   private String cancel_reason;
   private String status;
   private String requested_at;
   private String created_at;
   private String updated_at;
   
   
   
   public LeaveRequest() {
      super();
   }
   
   public LeaveRequest(int user_id, String date, String days_count, String reason, String cancel_reason,
         String status, String requested_at, String updated_at) {
      super();
      this.user_id = user_id;
      this.date = date;
      this.days_count = days_count;
      this.reason = reason;
      this.cancel_reason = cancel_reason;
      this.status = status;
      this.requested_at = requested_at;
      this.updated_at = updated_at;
   }
   
   public LeaveRequest(ResultSet rSet) {
      try {
         mapResultSetToPrivetProperty(rSet);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } 
   }

   public LeaveRequest(int id) {
         super(); 
         
         // System.out.println("===================LOG START============================"); 
         // System.out.println("ID: " + id); 
         
         Connection connection = null;
         
         try {

            ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
            connection = connectionProvider.getConnection();

             ResultSet rSet = QueryBuilder.readData(connection, "SELECT * FROM leave_requests WHERE id='"+ id +"'");
             if (rSet != null) {  
                try {
                   if (rSet.next()) { 
                      mapResultSetToPrivetProperty(rSet); 
                   }
                } catch (SQLException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                } 
             }
         } catch (Exception e) {
             e.printStackTrace();
         } 
         /** 
          ** No need to close the connection for "SELECT queries" 
          ** when using the singleton pattern for "ConnectionProvider"
         **/
//       finally {
//          try {
//             if (connection != null) {
//                connection.close();
//             }
//            catch (SQLException e) {
//               // TODO Auto-generated catch block
//               e.printStackTrace();
//            } 
//            // System.out.println("====================LOG END============================="); 
//         }
   }
    

   private void mapResultSetToPrivetProperty(ResultSet rSet) throws SQLException { 
       this.id = rSet.getInt(1);
       this.user_id = rSet.getInt(2);
       this.date = rSet.getString(3);
       this.days_count = rSet.getString(4);
       this.reason = rSet.getString(5);
       this.cancel_reason = rSet.getString(6);
       this.status = rSet.getString(7);
       this.requested_at = rSet.getString(8);
       this.created_at = rSet.getString(9);
       this.updated_at = rSet.getString(10);  
   }

   /**
    * @return the id
    */
   public int getId() {
      return id;
   }
   /**
    * @param id the id to set
    */
   public void setId(int id) {
      this.id = id;
   }
   /**
    * @return the user_id
    */
   public int getUser_id() {
      return user_id;
   }
   /**
    * @param user_id the user_id to set
    */
   public void setUser_id(int user_id) {
      this.user_id = user_id;
   }
   /**
    * @return the date
    */
   public String getDate() {
      return date;
   }
   /**
    * @param date the date to set
    */
   public void setDate(String date) {
      this.date = date;
   }
   /**
    * @return the days_count
    */
   public String getDays_count() {
      return days_count;
   }
   /**
    * @param days_count the days_count to set
    */
   public void setDays_count(String days_count) {
      this.days_count = days_count;
   }
   /**
    * @return the reason
    */
   public String getReason() {
      return reason;
   }
   /**
    * @param reason the reason to set
    */
   public void setReason(String reason) {
      this.reason = reason;
   }
   /**
    * @return the cancel_reason
    */
   public String getCancel_reason() {
      return cancel_reason;
   }
   /**
    * @param cancel_reason the cancel_reason to set
    */
   public void setCancel_reason(String cancel_reason) {
      this.cancel_reason = cancel_reason;
   }
   /**
    * @return the status
    */
   public String getStatus() {
      return status;
   }
   /**
    * @param status the status to set
    */
   public void setStatus(String status) {
      this.status = status;
   }
   /**
    * @return the requested_at
    */
   public String getRequested_at() {
      return requested_at;
   }
   /**
    * @param requested_at the requested_at to set
    */
   public void setRequested_at(String requested_at) {
      this.requested_at = requested_at;
   }
   /**
    * @return the created_at
    */
   public String getCreated_at() {
      return created_at;
   }
   /**
    * @param created_at the created_at to set
    */
   public void setCreated_at(String created_at) {
      this.created_at = created_at;
   }
   /**
    * @return the updated_at
    */
   public String getUpdated_at() {
      return updated_at;
   }
   /**
    * @param updated_at the updated_at to set
    */
   public void setUpdated_at(String updated_at) {
      this.updated_at = updated_at;
   }
}
