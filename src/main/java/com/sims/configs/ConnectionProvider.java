/**
 * 
 */
package com.sims.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is a ConnectionProvider class that provides database connections
 * 
 * @author MANEESH
 * @used SINGLETON DESIGN PATTERN
 */

public class ConnectionProvider {

   // "volatile" modifier used because thread-safe
   private static volatile ConnectionProvider connectionProvider;
   private volatile Connection connection;

   private String mysqlUrl = Config.getMySqlUrl();
   private String dbName = Config.getDbName();
   private String dbUserName = Config.getDbUserName();
   private String dbPassword = Config.getDbPassword();
   private String driverName = Config.getDriverName();

   private ConnectionProvider() throws SQLException {
//      if ( connectionProvider != null ) {
//         throw new RuntimeException( "Please use getConnectionProvider() method.");
//      }
      try {
         Class.forName(driverName);
         this.connection = DriverManager.getConnection(mysqlUrl + "/" + dbName, dbUserName, dbPassword);

      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
         System.out.println("Database connection creation failed: " + e.getMessage());
      }
      
   }

   public Connection getConnection() {
      return connection;
   }

   public static ConnectionProvider getConnectionProvider() throws SQLException {
      if (connectionProvider == null) {
         // Thread-safe used - (Thread-safe - Approach of Singleton Design pattern)
         synchronized (ConnectionProvider.class) {
            if (connectionProvider == null) {
               connectionProvider = new ConnectionProvider();
            }
         } 
      } 
      else if (connectionProvider.getConnection().isClosed()) {
         connectionProvider = new ConnectionProvider();
      }
      return connectionProvider;
   }
   

   public static void close(Connection con) throws SQLException {
      if (con != null) {
         con.close();
      }
   }

}
