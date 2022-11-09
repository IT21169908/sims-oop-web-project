package com.sims.services.interfaces;
 
import java.util.ArrayList;
import java.util.logging.Logger;

import com.sims.models.Subject;

public interface SubjectInterface {
  
   /** Initialize logger */
   public static final Logger log = Logger.getLogger(SubjectInterface.class.getName());

   /**
    * CREATE - Add Subjects for employee table
    * @param subject
    * @return boolean
    * @throws Exception 
    */
   public boolean create(Subject subject) throws Exception;
   
   
   /**
    * READ - Get all from subject table
    * @param
    * @return ArrayList<Subject>
    * @throws Exception 
    */
   public ArrayList<Subject> all() throws Exception;
   
   
   /**
    * DELETE - Delete subject by id
    * @param subject_id
    * @return boolean
    * @throws Exception 
    */
   public boolean destroy(String subject_id) throws Exception;

   
   /**
    * UPDATE - Update subject by id
    * @param subject
    * @return boolean
    * @throws Exception 
    */
   public boolean update(Subject subject) throws Exception;
   
}
