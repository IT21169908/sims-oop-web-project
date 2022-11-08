package com.sims.services.interfaces;

import java.util.ArrayList;

import com.sims.models.Student;

public interface CrudUtils<E, ID> {

   public boolean addStudent(E student) throws Exception;

   public boolean updateStudent(E student) throws Exception;

   public boolean deleteStudent(ID id) throws Exception;

   public Student searchStudent(ID id) throws Exception;

   public ArrayList<Student> getAllStudents() throws Exception;

}
