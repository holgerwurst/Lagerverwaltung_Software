/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Arthas
 */
public class Datenbankverbindung {

    Connection connection = null;
    ResultSet rs = null;
    /**
     * 
     * Methode für die Datenbankverbindung mit Selects von einzelnen Werten.
     * 
     * 
     * @param tabelle
     * @param spalte
     * @param woist
     * @param select
     * @return
     * @throws ClassNotFoundException 
     */
    
    public void connect(String select) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

  
       // String ergebnis = "";

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            rs = statement.executeQuery(select);
            //ergebnis = rs.getString(spalte);
            //System.out.println(ergebnis);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
       // return ergebnis;
    }

    /**
     * 
     * Methode für die Verbindung zur Datenbank um den Inhalt einer ganzen Spalte zu bekommen.
     * 
     * @param tabelle
     * @param spalte
     * @return
     * @throws ClassNotFoundException 
     */
    public String[] connect_ganze_spalte(String tabelle, String spalte) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        ResultSet rs = null;
        String temp = "";
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
                temp = rs.getString("" + spalte + "");
                val.add(temp);
            }
            ar = new String[val.size()];
            for (int i = 0; i <= val.size() - 1; i++) {
                ar[i] = val.get(i);

            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        return ar;
    }

    /**
     * 
     * Methode für das Insert und Update im Teilestamm
     * 
     * @param kommando
     * @param teil
     * @throws ClassNotFoundException 
     */
    public void connect_schreiben_teilestamm(String kommando, Teil_Stammdaten teil) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        ResultSet rs = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            if ("insert".equals(kommando)) {
                rs = statement.executeQuery("" + kommando + " into Teilestammdaten (id,typ, zeichnungsnummer,"
                        + "materialgruppe, preis, bezeichnung, baugruppe, bemerkung, max_anz_klein, "
                        + "max_anz_mittel, max_anz_gross) values (" + teil.get_id() + ",'" + teil.get_Teiletyp() + "',"
                        + "'" + teil.get_Zeichnungsnummer() + "','" + teil.get_Materialgruppe() + "'," + teil.get_Preis() + ","
                        + "'" + teil.get_Bezeichnung() + "','" + teil.get_Baugruppe() + "','" + teil.get_Bemerkung() + "',"
                        + "" + teil.get_max_anz_klein() + "," + teil.get_max_anz_mittel() + "," + teil.get_max_anz_gross() + ");");
            } else if ("update".equals(kommando)) {
                rs = statement.executeQuery("" + kommando + " Teilestammdaten SET typ='" + teil.get_Teiletyp() + "',"
                        + "zeichnungsnummer='" + teil.get_Zeichnungsnummer() + "', materialgruppe='" + teil.get_Materialgruppe() + "',"
                        + "preis=" + teil.get_Preis() + ", bezeichnung='" + teil.get_Bezeichnung() + "',baugruppe='" + teil.get_Baugruppe() + "',"
                        + "bemerkung='" + teil.get_Bemerkung() + "',max_anz_klein=" + teil.get_max_anz_klein() + ", max_anz_mittel=" + teil.get_max_anz_mittel() + ""
                        + ",max_anz_gross=" + teil.get_max_anz_gross() + ""
                        + " WHERE id=" + teil.get_id() + ";");
            }

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    /**
     * 
     * Methode für das Insert und Update im Lagerbestandskonto
     * 
     * @param kommando
     * @param lbk
     * @throws ClassNotFoundException 
     */
    public void connect_schreiben_lagerbestandskonto(String kommando, Lagerbestandskonto lbk) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        ResultSet rs = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            if ("insert".equals(kommando)) {
                rs = statement.executeQuery("" + kommando + " into Lagerbestandskonto (fachnummer,teile_ID, menge,"
                        + "anschaffungsgrund,haltbarkeitsdatum) values ('" + lbk.get_Fachnummer() + "'," + lbk.get_TeileID() + "," + lbk.get_Menge() + ""
                        + ",'" + lbk.get_Anschaffungsgrund() + "','" + lbk.get_Haltbarkeitsdatum() + "');");
            } else if ("update".equals(kommando)) {
                rs = statement.executeQuery("" + kommando + " Lagerbestandskonto SET menge= " + lbk.get_Menge() + ","
                        + "anschaffungsgrund='" + lbk.get_Anschaffungsgrund() + "', haltbarkeitsdatum='" + lbk.get_Haltbarkeitsdatum() + "' "
                        + " WHERE teile_ID=" + lbk.get_TeileID() + " AND fachnummer='" + lbk.get_Fachnummer() + "';");
            }

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    /**
     * 
     * Methode zum Insert in die Tabelle FreieIDs
     * 
     * @param freieid
     * @throws ClassNotFoundException 
     */
    public void connect_schreiben_freieid(int freieid) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        ResultSet rs = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            rs = statement.executeQuery("insert into FreieIDs (IDs) values (" + freieid + ");");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    /**
     * 
     * Methode zum Update in der Tabelle Lagerfachstamm
     * 
     * @param fach
     * @param belegung
     * @throws ClassNotFoundException 
     */
    public void connect_schreiben_lagerfachstamm(String fach, boolean belegung) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        ResultSet rs = null;

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            rs = statement.executeQuery("UPDATE Lagerfachstamm SET belegt ='" + belegung + "' WHERE fachnummer='" + fach + "';");

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    /**
     * 
     * Allgemeine Methode zum Löschen aus irgendeiner Tabelle
     * 
     * @param loesch_id
     * @param tabelle
     * @param loesch_fachnummer
     * @throws ClassNotFoundException 
     */
    public void connect_löschen(int loesch_id, String tabelle, String loesch_fachnummer) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        ResultSet rs = null;
        //String ergebnis = "";

        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            if (loesch_fachnummer == null) {
                rs = statement.executeQuery("DELETE FROM " + tabelle + " WHERE id=" + loesch_id + "; ");
            } else if (loesch_fachnummer != null) {
                rs = statement.executeQuery("DELETE FROM " + tabelle + " WHERE teile_ID=" + loesch_id + " AND fachnummer='" + loesch_fachnummer + "'; ");
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    /**
     * Eine einfache Methode die einfach alle Werte einer Tabelle zurückgibt in einem zweidimensionalen Array
     * @return 
     */
    public Object[][] connect_ganze_tabelle(String tabelle) {
        basic_connect();
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery("SELECT * FROM " + tabelle + ";");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    /**
     * 
     * @param tabelle
     * @param spaltenwerte
     * @return 
     */
    public Object[][] connect_suchen(String tabelle, String[][] spaltenwerte) {
        String query = "SELECT * FROM " + tabelle + "WHERE";
        int i = 0;
        for (String[] wert : spaltenwerte) {
            if (i == 0) {
                query = query + " " + wert[0] + "=" + wert[1];
                i++;
            }
            else {
                query = query + " OR " + wert[0] + "=" + wert[1]; 
            }
        }

        ResultSet rs = null;

        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        ArrayList<Object[]> zeilen = new ArrayList();
        ResultSetMetaData rsmd = rs.getMetaData();
        
        while rs.next() {
            int count = 0;
            while (count < rsmd.getColumnCount())
            if (== rs.getMetaData().getColumnTypeName(count)) { //typbezeichnung fehlt
                
            }
            
        }
        
        
        
        
    }
        /**
         *
         * Einfache Erstellung einer Verbindung ohne die Erstellung eines
         * SQLStatements
         */
    

    public void basic_connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
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
