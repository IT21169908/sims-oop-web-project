package com.sims.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sims.configs.ConnectionProvider;
import com.sims.utils.QueryBuilder;

/**
 * This is the TeacherSubject Model class
 * 
 * @author maneesh
 */

public class TeacherSubject {

   private String teacher_name;
   private String subject_title;
   private int teacher_id;
   private int subject_id;

   public TeacherSubject() {
      super();
   }

   public TeacherSubject(int teacher_id, int subject_id) {
      super();
      this.teacher_id = teacher_id;
      this.subject_id = subject_id;
   }

   public TeacherSubject(ResultSet rSet) {
      try {
         mapResultSetToPrivetProperty(rSet);
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public TeacherSubject(int teacher_id) {
      super();

      Connection connection = null;

      try {

         ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
         connection = connectionProvider.getConnection();

         ResultSet rSet = QueryBuilder.readData(connection,
               "SELECT * FROM teacher_subjects WHERE teacher_id='" + teacher_id + "'");
         if (rSet != null) {
            try {
               if (rSet.next()) {
                  mapResultSetToPrivetProperty(rSet);
               }
            } catch (SQLException e) {
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
//      finally {
//         try {
//            if (connection != null) {
//               connection.close();
//            }
//         } catch (SQLException e) {
//            e.printStackTrace();
//         }
//      }

   }

   private void mapResultSetToPrivetProperty(ResultSet rSet) throws SQLException {
      this.teacher_id = rSet.getInt(1);
      this.subject_id = rSet.getInt(2);

      User user = new User(teacher_id);
      Subject Subject = new Subject(subject_id);

      teacher_name = user.getName();
      subject_title = Subject.getTitle();
   }

   /**
    * @return the teacher_id
    */
   public int getTeacher_id() {
      return teacher_id;
   }

   /**
    * @param teacher_id the teacher_id to set
    */
   public void setTeacher_id(int teacher_id) {
      this.teacher_id = teacher_id;
   }

   /**
    * @return the subject_id
    */
   public int getSubject_id() {
      return subject_id;
   }

   /**
    * @param subject_id the subject_id to set
    */
   public void setSubject_id(int subject_id) {
      this.subject_id = subject_id;
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

}
