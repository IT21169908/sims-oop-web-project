package com.sims.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sims.models.User;
import com.sims.services.interfaces.RegistrationManagerInterface;
import com.sims.services.interfaces.UserInterface;

public class RegistrationManagerService implements RegistrationManagerInterface {

   /** Initialize logger */
   public static final Logger log = Logger.getLogger(RegistrationManagerService.class.getName());

   private static Connection connection;

   private PreparedStatement preparedStatement;

   public boolean create(User user) {
      return false;
   }

   public boolean update(User user) {
      return false;

   }

   public boolean destroy(String user_id) {
      return false;

   }

   @Override
   public ArrayList<User> all() {
      UserInterface user = new UserService();
      ArrayList<User> userList = user.all();
      return userList;
   }

}
