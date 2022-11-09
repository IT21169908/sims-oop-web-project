/**
 * 
 */
package com.sims.services.interfaces;

import com.sims.models.User;

/**
 * This is the User Service interface
 * 
 * @author maneesh
 */
public interface UserInterface {

	/**
	 * Get User by using email address
	 * @param email
	 * @return List<User>
	 */
	public User getUserByEmail(String email);
}
