package com.sims.services.interfaces;
 
import java.util.ArrayList;
import java.util.logging.Logger;

import com.sims.models.LeaveRequest;

public interface LeaveRequestInterface {
  
   /** Initialize logger */
   public static final Logger log = Logger.getLogger(LeaveRequestInterface.class.getName());
   
   /**
    * Add employees for employee table
    * @param employee
    * @return 
    * @throws Exception 
    */
   public boolean create(LeaveRequest leave_req);

   public ArrayList<LeaveRequest> all();
   
   public boolean destroy(String leave_id);

   boolean update(LeaveRequest leave);
}
