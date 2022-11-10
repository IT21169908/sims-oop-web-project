/**
 * 
 */
package com.sims.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sims.configs.ConnectionProvider;
import com.sims.utils.QueryBuilder;

/**
 * @author Indudini, Nishadi
 *
 */
public class Student {

   private int user_id;

   private int id;

   private String name;

   private String dob;

   private String parent_name;

   private String mobile_number;

   private String address;

   private String created_at;

   private String update_at;

   // default constructor
   public Student() {

   }

   public Student(int user_id, int id, String name, String dob, String parent_name, String mobile_number,
         String address, String created_at, String update_at) {
      super();
      this.user_id = user_id;
      this.id = id;
      this.name = name;
      this.dob = dob;
      this.parent_name = parent_name;
      this.mobile_number = mobile_number;
      this.address = address;
      this.created_at = created_at;
      this.update_at = update_at;
   }

   public Student(int id) {
      super();

      Connection con = null;

      try {
         con = ConnectionProvider.getConnection();
         ResultSet rSet = QueryBuilder.readData(con, "SELECT * FROM students WHERE id='" + id + "'");

         if (rSet != null) {
            if (rSet.next()) {
               this.id = rSet.getInt(1);
               this.user_id = rSet.getInt(2);
               this.name = rSet.getString(3);
               this.dob = rSet.getString(4);
               this.parent_name = rSet.getString(5);
               this.mobile_number = rSet.getString(6);
               this.address = rSet.getString(7);
               this.created_at = rSet.getString(8);
               this.update_at = rSet.getString(9);
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public Student(ResultSet rSet) {
      try {
         this.id = rSet.getInt(1);
         this.user_id = rSet.getInt(2);
         this.name = rSet.getString(3);
         this.dob = rSet.getString(4);
         this.parent_name = rSet.getString(5);
         this.mobile_number = rSet.getString(6);
         this.address = rSet.getString(7);
         this.created_at = rSet.getString(8);
         this.update_at = rSet.getString(9);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public int getUser_id() {
      return user_id;
   }

   public void setUser_id(int user_id) {
      this.user_id = user_id;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDob() {
      return dob;
   }

   public void setDob(String dob) {
      this.dob = dob;
   }

   public String getParent_name() {
      return parent_name;
   }

   public void setParent_name(String parent_name) {
      this.parent_name = parent_name;
   }

   public String getMobile_number() {
      return mobile_number;
   }

   public void setMobile_number(String mobile_number) {
      this.mobile_number = mobile_number;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getCreated_at() {
      return created_at;
   }

   public void setCreated_at(String created_at) {
      this.created_at = created_at;
   }

   public String getUpdate_at() {
      return update_at;
   }

   public void setUpdate_at(String update_at) {
      this.update_at = update_at;
   }

}
