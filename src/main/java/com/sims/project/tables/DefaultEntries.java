/**
 * 
 */
package com.sims.project.tables;

import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

/**
*
* @author maneesh
*/
public class DefaultEntries {
	
	/**
	 * 		@do Create new execute tables functions below
	 * 		@do And make new class instance in InsertDefaultEntries class -> executeEntries() method
	 */
	
	public static void usersTableEntries(Statement stmt) throws SQLException {
	   
	   String hashedPassword = BCrypt.hashpw("12345678", BCrypt.gensalt());
	   
//	   String passwordHash = "$2a$10$Rb0yLoQAGch6W/.K7quB5uPC1/gAF3vKmxeCv8JN2712r/iD7x8Bu"; // 12345678
        
        stmt.executeUpdate("INSERT INTO users"
        		+ "(id, name, email, password, nic, profile_photo, type, email_verified, email_verify_code, password_reset_code, created_at, updated_at) "
                + "VALUES(1, 'Admin', 'admin@hims.lk', '" + hashedPassword + "', NULL, NULL, 'admin', 0, NULL, NULL, '2022-11-06 23:19:45', '2022-11-06 20:19:45')");
        
        stmt.executeUpdate("INSERT INTO users"
        		+ "(id, name, email, password, nic, profile_photo, type, email_verified, email_verify_code, password_reset_code, created_at, updated_at) "
                + "VALUES(2, 'Teacher', 'teacher@hims.lk', '" + hashedPassword + "', NULL, NULL, 'teacher', 0, NULL, NULL, '2022-11-06 23:20:08', '2022-11-06 23:20:08')");

        stmt.executeUpdate("INSERT INTO users"
                + "(id, name, email, password, nic, profile_photo, type, email_verified, email_verify_code, password_reset_code, created_at, updated_at) "
                + "VALUES(3, 'Student', 'student@hims.lk', '" + hashedPassword + "', NULL, NULL, 'student', 0, NULL, NULL, '2022-11-06 23:20:08', '2022-11-06 23:20:08')");
	}
	
	public static void ___TableEntries(Statement stmt) throws SQLException {
		//Example block
	}

}
