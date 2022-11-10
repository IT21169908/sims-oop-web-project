package com.sims.services.interfaces;

import java.util.ArrayList;

public interface CrudUtils<Model, ID> {

   public boolean create(Model student) throws Exception;
   
   public ArrayList<Model> all() throws Exception;

   public boolean update(Model student) throws Exception;

   public boolean destroy(ID id) throws Exception;

   public Model search(ID id) throws Exception;

   

}
