/**
 * 
 */
package com.sims.services.interfaces;

import java.util.ArrayList;

import com.sims.models.User;

/**
 * This is the User Service interface
 * 
 * @author maneesh,nishadi
 */
public interface UserInterface {

   /**
    * Get User by using email address
    * 
    * @param email
    * @return List<User>
    */
   public User getUserByEmail(String email);

   public ArrayList<User> all();

   boolean update(User user) throws Exception;

   public boolean create(User user) throws Exception;

   public boolean destroy(int user_id) throws Exception;
}
