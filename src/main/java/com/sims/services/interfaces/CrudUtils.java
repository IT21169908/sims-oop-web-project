package com.sims.services.interfaces;

import java.util.ArrayList;

import com.sims.models.Student;

public interface CrudUtils<E, ID> {

   public boolean create(E student) throws Exception;
   
   public ArrayList<E> all() throws Exception;

   public boolean update(E student) throws Exception;

   public boolean destroy(ID id) throws Exception;

   public E search(ID id) throws Exception;

   

}
