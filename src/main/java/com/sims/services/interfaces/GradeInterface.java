package com.sims.services.interfaces;
 
import java.util.ArrayList;
import java.util.logging.Logger;

import com.sims.models.Grade;

public interface GradeInterface {
  
   /** Initialize logger */
   public static final Logger log = Logger.getLogger(GradeInterface.class.getName());

   /**
    * CREATE - Add Grades for employee table
    * @param grade
    * @return boolean
    * @throws Exception 
    */
   public boolean create(Grade grade) throws Exception;
   
   
   /**
    * READ - Get all from grade table
    * @param
    * @return ArrayList<Grade>
    * @throws Exception 
    */
   public ArrayList<Grade> all() throws Exception;
   
   
   /**
    * DELETE - Delete grade by id
    * @param grade_id
    * @return boolean
    * @throws Exception 
    */
   public boolean destroy(String grade_id) throws Exception;

   
   /**
    * UPDATE - Update grade by id
    * @param grade
    * @return boolean
    * @throws Exception 
    */
   public boolean update(Grade grade) throws Exception;
   
}
