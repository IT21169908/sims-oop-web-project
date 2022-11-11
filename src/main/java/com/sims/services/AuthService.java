/**
 * 
 */
package com.sims.services;

import java.sql.Connection;
import java.sql.ResultSet;

import org.mindrot.jbcrypt.BCrypt;

import com.sims.configs.ConnectionProvider;
import com.sims.models.User;
import com.sims.services.interfaces.AuthInterface;
import com.sims.utils.QueryBuilder;

/**
 * This is the Auth Service class
 * 
 * @author maneesh
 */
public class AuthService implements AuthInterface {
	
	@Override
	public User userLoginByEmail(String email, String password) {
		
	    User user = new User();
		Connection connection = null;
		String emailUserPassword = null;
		int userId = 0;
		
		try {

		   ConnectionProvider connectionProvider = ConnectionProvider.getConnectionProvider();
           connection = connectionProvider.getConnection();
             
			ResultSet rSet = QueryBuilder.readData(connection, "SELECT * FROM users WHERE email='" + email + "'");
			
			if (rSet != null) {
				
				if (rSet.next()) {
				    userId = rSet.getInt(1);
					emailUserPassword = rSet.getString(5);
				}
				
				// Check password with BCrypt
				if (emailUserPassword != null && BCrypt.checkpw(password, emailUserPassword)) {
					user = new User(userId);
				}
			}

		} catch (Exception e) {
			System.out.println("+===== AuthService Exception =====+");
			e.printStackTrace();
		} 
         /** 
          ** No need to close the connection for "SELECT queries" 
          ** when using the singleton pattern for "ConnectionProvider"
         **/
//       finally {
//          try {
//             if (connection != null) {
//                connection.close();
//             } 
//            catch (SQLException e) {
//               e.printStackTrace();
//            }
//         }
		
		return user;
	}

}
