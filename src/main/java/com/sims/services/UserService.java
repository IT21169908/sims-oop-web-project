/**
 * 
 */
package com.sims.services;

import java.sql.Connection;
import java.sql.ResultSet;

import com.sims.configs.ConnectionProvider;
import com.sims.models.User;
import com.sims.services.interfaces.UserInterface;
import com.sims.utils.QueryBuilder;

/**
 * This is the User Service class
 * 
 * @author maneesh
 */
public class UserService implements UserInterface {

	@Override
	public User getUserByEmail(String email) {
		
		User emailUser = null;
		Connection con = null;
		
		try {
	        con = ConnectionProvider.getConnection();
			ResultSet rSet = QueryBuilder.readData(con, "SELECT * FROM users WHERE email='"+email+"'");
			
			if (rSet != null) {
			    if (rSet.next()) {
		            int id = rSet.getInt(1);
		            emailUser = new User(id);
			    }
			}
			ConnectionProvider.close(con);

		} catch (Exception e) {
            e.printStackTrace();
        }
				
		return emailUser;
	}
	
}
