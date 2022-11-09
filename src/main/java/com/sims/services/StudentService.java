package com.sims.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sims.configs.ConnectionProvider;
import com.sims.models.Student;
import com.sims.services.interfaces.StudentInterface;

public class StudentService implements StudentInterface {

   @Override
   public boolean add(Student student) throws Exception {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean update(Student student) throws Exception {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean delete(Integer id) throws Exception {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public Student search(Integer id) throws Exception {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ArrayList<Student> getAll() throws Exception {
      Connection con = ConnectionProvider.getConnection();
      PreparedStatement pstm = con.prepareStatement("SELECT * FROM Student");

      ResultSet rst = pstm.executeQuery();

      ArrayList<Student> studentList = new ArrayList<>();

      while (rst.next()) {

         Student st = new Student(rst.getInt(1),rst.getInt(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6),
               rst.getString(7), rst.getString(8),rst.getString(9));

         studentList.add(st);

      }

      return studentList;
      
   }

  

}
