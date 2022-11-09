/**
 * 
 */
package com.sims.models;

/**
 * @author Indudini
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
