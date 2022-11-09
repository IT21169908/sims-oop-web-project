/**
 * 
 */
package com.sims.services.interfaces;

import com.sims.models.User;

/**
 * This is the Auth Service interface
 * 
 * @author maneesh
 */
public interface AuthInterface {

	/**
	 * User login using email address
	 * @param email
	 * @param password
	 * @return List<User>
	 */
	public User userLoginByEmail(String email, String password);
	
}
