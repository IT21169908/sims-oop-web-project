/**
 * 
 */
package com.sims.services.interfaces;

import java.util.ArrayList;

import com.sims.models.User;

/**
 * This is the User Service interface
 * 
 * @author maneesh,nishadi, M.M.N.H.Fonseka
 */
public interface UserInterface {



   public ArrayList<User> all();

   boolean update(User user) throws Exception;

   public boolean create(User user) throws Exception;

   public boolean destroy(int user_id) throws Exception;

	/**
	 * Get User by using email address
	 * @param email
	 * @return List<User>
	 */
	public User getUserByEmail(String email);

	/**
	 * Get all from user table by type
	 * @param usser_type
	 * @return ArrayList<User>
	 * @throws Exception 
	 */
    public ArrayList<User> allByType(String usser_type) throws Exception;

   public boolean changePassword(User user) throws Exception;
}
