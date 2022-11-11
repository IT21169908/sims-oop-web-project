package com.sims.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sims.configs.ConnectionProvider;
import com.sims.utils.QueryBuilder;

/**
 * This is the Grade Model class
 * 
 * @author maneesh
 */

public class Grade {
   
   private int id;
   private String title;
   private String created_at;
   private String updated_at;
   
   
   public Grade() {
      super();
   }
   
   public Grade(String title, String created_at, String updated_at) {
      super();
      this.title = title;
      this.created_at = created_at;
      this.updated_at = updated_at;
   }
   
   public Grade(ResultSet rSet) {
      try {
         mapResultSetToPrivetProperty(rSet);
      }
      catch (SQLException e) {
         e.printStackTrace();
      } 
   }

   public Grade(int id) {
         super();
       
         Connection connection = null;
         
         try {

            ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
            connection = connectionProvider.getConnection();
            
             ResultSet rSet = QueryBuilder.readData(connection, "SELECT * FROM grades WHERE id='"+ id +"'");
             if (rSet != null) {  
                try {
                   if (rSet.next()) { 
                      mapResultSetToPrivetProperty(rSet); 
                   }
                } 
                catch (SQLException e) {
                   e.printStackTrace();
                } 
             }
         } 
         catch (Exception e) {
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
//               e.printStackTrace();
//            }
//         }
      
   }
   
   private void mapResultSetToPrivetProperty(ResultSet rSet) throws SQLException { 
       this.id = rSet.getInt(1);
       this.title = rSet.getString(2);
       this.created_at = rSet.getString(3);
       this.updated_at = rSet.getString(4); 
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
    * @return the title
    */
   public String getTitle() {
      return title;
   }

   /**
    * @param title the title to set
    */
   public void setTitle(String title) {
      this.title = title;
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
