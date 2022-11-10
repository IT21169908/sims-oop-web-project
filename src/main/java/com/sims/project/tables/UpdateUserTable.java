/**
 * 
 */
package com.sims.project.tables;

import java.sql.SQLException;
import java.sql.Statement;

import com.sims.project.db_environment_initializer.DatabaseEnvironment;

/**
 *
 * @author Nishadi
 */
public class UpdateUserTable {

   public UpdateUserTable(Statement stmt) throws SQLException {

      String tableName = "users";

      try {
         String addGender = "IF NOT EXISTS( SELECT NULL "
               + " FROM INFORMATION_SCHEMA.COLUMNS "
               + " WHERE table_name = '" + tableName + "' "
               + " AND table_schema = '" + DatabaseEnvironment.getDB_NAME() + "' "
               + " AND column_name = 'gender') THEN "
               + " ALTER TABLE `" + tableName + "` ADD `gender` ENUM('male','female') NULL AFTER `nic`; "
               + " END IF;";

         String addDob = "IF NOT EXISTS( SELECT NULL "
               + " FROM INFORMATION_SCHEMA.COLUMNS "
               + " WHERE table_name = '" + tableName + "' "
               + " AND table_schema = '" + DatabaseEnvironment.getDB_NAME() + "' "
               + " AND column_name = 'dob') THEN "
               + " ALTER TABLE `" + tableName + "` ADD `dob` DATE NULL AFTER `gender`; "
               + " END IF;";

         String addMobile = "IF NOT EXISTS( SELECT NULL "
               + " FROM INFORMATION_SCHEMA.COLUMNS "
               + " WHERE table_name = '" + tableName + "' "
               + " AND table_schema = '" + DatabaseEnvironment.getDB_NAME() + "' "
               + " AND column_name = 'mobile_number') THEN "
               + " ALTER TABLE `" + tableName + "` ADD `mobile_number` VARCHAR(20) NULL AFTER `email`;"
               + " ALTER TABLE `" + tableName + "` ADD UNIQUE(`mobile_number`);"
               + " END IF;";

         stmt.executeUpdate(addGender);
         stmt.executeUpdate(addDob);
         stmt.executeUpdate(addMobile);

         System.out.println(tableName + " table alter successfully.!");

      } catch (SQLException e) {
         e.printStackTrace();
         throw e;

      }

   }

}
