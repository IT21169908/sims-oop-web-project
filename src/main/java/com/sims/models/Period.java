package com.sims.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sims.configs.ConnectionProvider;
import com.sims.utils.QueryBuilder;

/**
 * This is the Period Model class
 * 
 * @author M.M.N.H.Fonseka
 */
public class Period {

   private int id;
   private String title;
   private String subject_title;
   private String teacher_name;
   private String grade_title;
   private int subject_id;
   private int grade_id;
   private int teacher_id;
   private String start_time;
   private String end_time;
   private String day;
   private String start_date;
   private String end_date;
   private String updated_at;
   private String created_at;

   public Period() {
      // TODO Auto-generated constructor stub
   }

   public Period(int id) {
      super(); 
      try {
         
         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         Connection con =  connectionProvider.getConnection();
         
         ResultSet rSet = QueryBuilder.readData(
               con,
               "SELECT "
                     + "periods.*, subjects.title subject_title, grades.title grade_title, users.name teacher_name "
                     + "FROM `periods`, subjects, grades, users "
                     + "WHERE periods.subject_id = subjects.id AND periods.grade_id = grades.id AND periods.teacher_id = users.id AND "
                     + "periods.id='" + id + "'");
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

   }

   public Period(ResultSet rSet) {
      try {
         mapResultSetToPrivetProperty(rSet);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void mapResultSetToPrivetProperty(ResultSet rSet) throws SQLException {
      try {
         id = rSet.getInt(1);
         title = rSet.getString(2);
         subject_id = rSet.getInt(3);
         grade_id = rSet.getInt(4);
         teacher_id = rSet.getInt(5);
         start_time = rSet.getString(6);
         end_time = rSet.getString(7);
         day = rSet.getString(8);
         start_date = rSet.getString(9);
         end_date = rSet.getString(10);
         updated_at = rSet.getString(11);
         created_at = rSet.getString(12);

         subject_title = rSet.getString(13);
         grade_title = rSet.getString(14);
         teacher_name = rSet.getString(15);
      } catch (Exception e) {
         e.printStackTrace();
      }
       
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public int getSubject_id() {
      return subject_id;
   }

   public void setSubject_id(int subject_id) {
      this.subject_id = subject_id;
   }

   public int getGrade_id() {
      return grade_id;
   }

   public void setGrade_id(int grade_id) {
      this.grade_id = grade_id;
   }

   public int getTeacher_id() {
      return teacher_id;
   }

   public void setTeacher_id(int teacher_id) {
      this.teacher_id = teacher_id;
   }

   public String getStart_time() {
      return start_time;
   }

   public void setStart_time(String start_time) {
      this.start_time = start_time;
   }

   public String getEnd_time() {
      return end_time;
   }

   public void setEnd_time(String end_time) {
      this.end_time = end_time;
   }

   public String getDay() {
      return day;
   }

   public void setDay(String day) {
      this.day = day;
   }

   public String getStart_date() {
      return start_date;
   }

   public void setStart_date(String start_date) {
      this.start_date = start_date;
   }

   public String getEnd_date() {
      return end_date;
   }

   public void setEnd_date(String end_date) {
      this.end_date = end_date;
   }

   public String getUpdated_at() {
      return updated_at;
   }

   public void setUpdated_at(String updated_at) {
      this.updated_at = updated_at;
   }

   public String getCreated_at() {
      return created_at;
   }

   public void setCreated_at(String created_at) {
      this.created_at = created_at;
   }

   public String getTeacher_name() {
      return teacher_name;
   }

   public void setTeacher_name(String teacher_name) {
      this.teacher_name = teacher_name;
   }

   public String getSubject_title() {
      return subject_title;
   }

   public void setSubject_title(String subject_title) {
      this.subject_title = subject_title;
   }

   public String getGrade_title() {
      return grade_title;
   }

   public void setGrade_title(String grade_title) {
      this.grade_title = grade_title;
   }

}
