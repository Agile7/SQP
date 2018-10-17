/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vilosh_na
 */
public class ConnectionFactory {
    
    //public static final String URL = "jdbc:mysql://localhost:3306/code_review_db";
    public static final String URL = "jdbc:mysql://localhost/agileseven?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "jinjin123";
    
    public static Connection getConnection()
    {
      Connection connection = null;
      try {
          connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
      
     return connection;
    }
    
}
