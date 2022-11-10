package com.sims.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.sims.models.User;
import com.sims.services.interfaces.RegistrationManagerInterface;
import com.sims.services.interfaces.UserInterface;

public class RegistrationManagerService implements RegistrationManagerInterface {

   /** Initialize logger */
   public static final Logger log = Logger.getLogger(RegistrationManagerService.class.getName());
 
   public boolean create(User user) throws Exception {
      UserInterface userService = new UserService();
      return userService.create(user);
      // TODO REQUIRED TO INSERT RECORD FOR STUDENT OR TEACHER PROFILE ACORDING TO ROLE
   }

   public boolean update(User user) throws Exception {
      UserInterface userService = new UserService();
      return userService.update(user);
      // TODO REQUIRED TO UPDATE RECORD FOR STUDENT OR TEACHER PROFILE ACORDING TO ROLE
   }
 
   @Override
   public ArrayList<User> all() {
      UserInterface user = new UserService();
      ArrayList<User> userList = user.all();
      return userList;
   }

   @Override
   public boolean destroy(Integer user_id) throws Exception {
      UserInterface userService = new UserService();
      return userService.destroy(user_id); 
   }

   @Override
   public User search(Integer id) throws Exception {
      // TODO Auto-generated method stub
      return null;
   }
 

}
