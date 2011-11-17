/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Arthas
 */
public class Datenbankverbindung {
    
    Connection connection = null;
    
     public void connect() throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");

    
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
   
      }
     
     public void disconnect() throws ClassNotFoundException{
          try{
              
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
         System.err.println(e);
     }
     
     }
    
  }


