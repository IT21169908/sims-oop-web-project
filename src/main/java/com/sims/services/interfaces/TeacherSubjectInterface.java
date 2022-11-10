package com.sims.services.interfaces;
 
import java.util.ArrayList;
import java.util.logging.Logger;

import com.sims.models.TeacherSubject;

public interface TeacherSubjectInterface {
  
   /** Initialize logger */
   public static final Logger log = Logger.getLogger(TeacherSubjectInterface.class.getName());

   /**
    * CREATE - Add teacher subjects
    * @param teacher_subject
    * @return boolean
    * @throws Exception 
    */
   public boolean create(TeacherSubject teacher_subject) throws Exception;
   
   
   /**
    * READ - Get all from teacher subject table
    * @param
    * @return ArrayList<TeacherSubject>
    * @throws Exception 
    */
   public ArrayList<TeacherSubject> all() throws Exception;
   
   
   /**
    * DELETE - Delete teacher subject by teacher_id, subject_id
    * @param teacher_id, subject_id
    * @return boolean
    * @throws Exception 
    */
   public boolean destroy(String teacher_id, String subject_id) throws Exception;
   
   
   /**
    * DELETE - Delete teacher subject by id
    * @param subject_id
    * @return boolean
    * @throws Exception 
    */
   public boolean destroy(String subject_id) throws Exception;

   
   /**
    * UPDATE - Update teacher subject by id
    * @param teacher_subject
    * @return boolean
    * @throws Exception 
    */
   public boolean update(TeacherSubject teacher_subject) throws Exception;
   
   
   /**
    * Get all from teacher subject table by subject
    * @param subject_id
    * @return ArrayList<TeacherSubject>
    * @throws Exception 
    */
   public ArrayList<TeacherSubject> allBySubject(int subject_id) throws Exception;
   
}
