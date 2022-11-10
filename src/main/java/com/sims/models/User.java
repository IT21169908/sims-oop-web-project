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
 * This is the User Model class
 * 
 * @author maneesh, Nishadi
 */

public class User {

   private int id;
   private String name;
   private String email;
   private String mobile_number;
   private String password;
   private String nic;
   private String gender;
   private String dob;
   private String profile_photo;
   private String type;
   private String email_verified;
   private String email_Verify_code;
   private String password_reset_code;
   private String created_at;
   private String updated_at;

   public User() {
      super();
   }

   public User(int id) {
      super();

      Connection con = null;

      try {
         con = ConnectionProvider.getConnection();
         ResultSet rSet = QueryBuilder.readData(con, "SELECT * FROM users WHERE id='" + id + "'");

         if (rSet != null) {
            if (rSet.next()) {
               this.id = rSet.getInt(1);
               this.name = rSet.getString(2);
               this.email = rSet.getString(3);
               this.mobile_number = rSet.getString(4);
               this.password = "";
               this.nic = rSet.getString(6);
               this.gender = rSet.getString(7);
               this.dob = rSet.getString(8);
               this.profile_photo = rSet.getString(9);
               this.type = rSet.getString(10);
               this.email_verified = rSet.getString(11);
               this.email_Verify_code = rSet.getString(12);
               this.password_reset_code = rSet.getString(13);
               this.created_at = rSet.getString(14);
               this.updated_at = rSet.getString(15);
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public User(ResultSet rSet) {
      try {
         this.id = rSet.getInt(1);
         this.name = rSet.getString(2);
         this.email = rSet.getString(3);
         this.mobile_number = rSet.getString(4);
         this.password = "";
         this.nic = rSet.getString(6);
         this.gender = rSet.getString(7);
         this.dob = rSet.getString(8);
         this.profile_photo = rSet.getString(9);
         this.type = rSet.getString(10);
         this.email_verified = rSet.getString(11);
         this.email_Verify_code = rSet.getString(12);
         this.password_reset_code = rSet.getString(13);
         this.created_at = rSet.getString(14);
         this.updated_at = rSet.getString(15);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
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

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getNic() {
      return nic;
   }

   public void setNic(String nic) {
      this.nic = nic;
   }

   public String getProfile_photo() {
      return profile_photo;
   }

   public void setProfile_photo(String profile_photo) {
      this.profile_photo = profile_photo;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getEmail_verified() {
      return email_verified;
   }

   public void setEmail_verified(String email_verified) {
      this.email_verified = email_verified;
   }

   public String getEmail_Verify_code() {
      return email_Verify_code;
   }

   public void setEmail_Verify_code(String email_Verify_code) {
      this.email_Verify_code = email_Verify_code;
   }

   public String getPassword_reset_code() {
      return password_reset_code;
   }

   public void setPassword_reset_code(String password_reset_code) {
      this.password_reset_code = password_reset_code;
   }

   public String getCreated_at() {
      return created_at;
   }

   public void setCreated_at(String created_at) {
      this.created_at = created_at;
   }

   public String getUpdated_at() {
      return updated_at;
   }

   public void setUpdated_at(String updated_at) {
      this.updated_at = updated_at;
   }

   public String getGender() {
      return gender;
   }

   public void setGender(String gender) {
      this.gender = gender;
   }

   public String getDob() {
      return dob;
   }

   public void setDob(String dob) {
      this.dob = dob;
   }

   public String getMobile_number() {
      return mobile_number;
   }

   public void setMobile_number(String mobile_number) {
      this.mobile_number = mobile_number;
   }

}
