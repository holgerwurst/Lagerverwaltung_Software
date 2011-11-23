/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
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

    public String connect(String tabelle, String spalte, String woist, String select) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        ResultSet rs = null;
        String ergebnis = "";

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            rs = statement.executeQuery("select " + spalte + " from " + tabelle + " where " + woist + "='" + select + "' ");
            ergebnis = rs.getString(spalte);
            //System.out.println(ergebnis);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        return ergebnis;

    }

    public String[] connect_ganze_spalte(String tabelle,String spalte) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        ResultSet rs = null;
        String temp="";
        String[] ar = new String[0];
        ArrayList<String> val = new ArrayList<String>(0);

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            rs = statement.executeQuery("select * from " + tabelle + "");
            while (rs.next()) {
                // read the result set
                // System.out.println("name = " + rs.getString("fachnummer"));
                temp = rs.getString(""+spalte+"");
                val.add(temp);
                //table(fnr, i);
                //   System.out.println(fnr);
                // System.out.println(val.size());

            }
            ar = new String[val.size()];
            for (int i = 0; i <= val.size() - 1; i++) {
                ar[i] = val.get(i);

                //  System.out.println(ar[i]);
            }
            //System.out.println(ergebnis);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }

        return ar;

    }

    public void disconnect() throws ClassNotFoundException {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

    }
}
