/**
 * 
 */
package com.sims.services;

import java.util.ArrayList;
import java.util.List;

import com.sims.models.User;
import com.sims.services.interfaces.UserInterface;
import com.sims.utils.Validations;

/**
 * This is the Validation Service class
 * 
 * @author maneesh
 */
public class ValidationService {
	
	public static List<String> loginValidator(String email, String password) {
		
		try {
			List<String> errorsList = new ArrayList<>();
			
			UserInterface userService = new UserService();
			User user = userService.getUserByEmail(email);
			
			if (user == null) {
				errorsList.add( "User not found by this email.!" );
			}
			
			if (email.equals("") || email == null) {
				errorsList.add( "Email is required.!" );
			}
			
			if (password.equals("") || email == null) {
				errorsList.add( "Password is required.!" );
			}
			
			if ( !(Validations.isValidEmail(email)) ) {
				errorsList.add( "Invalid email address.!" );
			}
			
			return errorsList;
			
		} catch (Exception e) {
            e.printStackTrace();
        }
				
		return null;
		
	}

}
