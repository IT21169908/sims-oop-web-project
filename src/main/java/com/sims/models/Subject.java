package com.sims.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sims.configs.ConnectionProvider;
import com.sims.utils.QueryBuilder;

/**
 * This is the User Model class
 * 
 * @author maneesh
 */

public class Subject {
   
   private int id;
   private String code;
   private String title;
   private String created_at;
   private String updated_at;
   
   
   public Subject() {
      super();
   }
   
   public Subject(String code, String title, String created_at, String updated_at) {
      super();
      this.code = code;
      this.title = title;
      this.created_at = created_at;
      this.updated_at = updated_at;
   }
   
   public Subject(ResultSet rSet) {
      try {
         mapResultSetToPrivetProperty(rSet);
      }
      catch (SQLException e) {
         e.printStackTrace();
      } 
   }

   public Subject(int id) {
         super();
       
         Connection con = null;
         
         try {
             con = ConnectionProvider.getConnection();
             ResultSet rSet = QueryBuilder.readData(con, "SELECT * FROM subjects WHERE id='"+ id +"'");
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
         finally {
            try {
               ConnectionProvider.close(con);
            } 
            catch (SQLException e) {
               e.printStackTrace();
            }
         }
      
   }
   
   public Subject(String code) {
      super();
    
      Connection con = null;
      
      try {
          con = ConnectionProvider.getConnection();
          ResultSet rSet = QueryBuilder.readData(con, "SELECT * FROM subjects WHERE code='"+ code +"'");
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
      finally {
         try {
            ConnectionProvider.close(con);
         } 
         catch (SQLException e) {
            e.printStackTrace();
         }
      }
}
    
   private void mapResultSetToPrivetProperty(ResultSet rSet) throws SQLException { 
       this.id = rSet.getInt(1);
       this.code = rSet.getString(2);
       this.title = rSet.getString(3);
       this.created_at = rSet.getString(4);
       this.updated_at = rSet.getString(5); 
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
    * @return the code
    */
   public String getCode() {
      return code;
   }

   /**
    * @param code the code to set
    */
   public void setCode(String code) {
      this.code = code;
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
