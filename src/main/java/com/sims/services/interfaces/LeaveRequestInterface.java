package com.sims.services.interfaces;
 
import java.util.ArrayList;
import java.util.logging.Logger;

import com.sims.models.LeaveRequest;

public interface LeaveRequestInterface {
  
   /** Initialize logger */
   public static final Logger log = Logger.getLogger(LeaveRequestInterface.class.getName());
   
   public boolean create(LeaveRequest leave_req) throws Exception;

   public ArrayList<LeaveRequest> all();
   
   public boolean destroy(String leave_id);

   boolean update(LeaveRequest leave) throws Exception;

   public ArrayList<LeaveRequest> allByUser(int user_id);
   
}
