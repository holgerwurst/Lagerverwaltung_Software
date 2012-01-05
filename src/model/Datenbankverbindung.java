/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthas
 */
public class Datenbankverbindung {

    Connection connection = null;
    ResultSet rs = null;
    String[] ar = new String[0];
    ArrayList<String> val = new ArrayList<String>(0);
    String ergebnis = "";

    /*
     * *
     * Methode für die Verbindung zur Datenbank um den Inhalt einer ganzen
     * Spalte zu bekommen.
     *
     *
     * @param spalte @return @throws ClassNotFoundException
     */
    public void connect(String select, String spalte) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        // ResultSet rs = null;



        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            rs = statement.executeQuery(select);
            while (rs.next()) {
                ergebnis = rs.getString(spalte);
                val.add(ergebnis);
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
        //    return ar;
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
                /*alte query mit zeichnNr Fehler begin
                statement.executeQuery("" + kommando + " into Teilestammdaten (id,typ, zeichnungsnummer,"
                        + "materialgruppe, preis, bezeichnung, baugruppe, bemerkung, max_anz_klein, "
                        + "max_anz_mittel, max_anz_gross) values (" + teil.get_Id() + ",'" + teil.get_Teiletyp() + "',"
                        + "'connect_schreiben_lagerbestandskonto" + teil.get_Zeichnungsnummer() + "','" + teil.get_Materialgruppe() + "'," + teil.get_Preis() + ","
                        + "'" + teil.get_Bezeichnung() + "','" + teil.get_Baugruppe() + "','" + teil.get_Bemerkung() + "',"
                        + "" + teil.get_max_anz_klein() + "," + teil.get_max_anz_mittel() + "," + teil.get_max_anz_gross() + ");");
                alte query mit zeichnNr Fehler end*/
                
                
                
                statement.executeQuery("" + kommando + " into Teilestammdaten (id,typ, zeichnungsnummer,"
                        + "materialgruppe, preis, bezeichnung, baugruppe, bemerkung, max_anz_klein, "
                        + "max_anz_mittel, max_anz_gross) values (" + teil.get_Id() + ",'" + teil.get_Teiletyp() + "','"
                        + teil.get_Zeichnungsnummer() + "','" + teil.get_Materialgruppe() + "'," + teil.get_Preis() + ","
                        + "'" + teil.get_Bezeichnung() + "','" + teil.get_Baugruppe() + "','" + teil.get_Bemerkung() + "',"
                        + "" + teil.get_max_anz_klein() + "," + teil.get_max_anz_mittel() + "," + teil.get_max_anz_gross() + ");");
            } else if ("update".equals(kommando)) {
                statement.executeQuery("" + kommando + " Teilestammdaten SET typ='" + teil.get_Teiletyp() + "',"
                        + "zeichnungsnummer='" + teil.get_Zeichnungsnummer() + "', materialgruppe='" + teil.get_Materialgruppe() + "',"
                        + "preis=" + teil.get_Preis() + ", bezeichnung='" + teil.get_Bezeichnung() + "',baugruppe='" + teil.get_Baugruppe() + "',"
                        + "bemerkung='" + teil.get_Bemerkung() + "',max_anz_klein=" + teil.get_max_anz_klein() + ", max_anz_mittel=" + teil.get_max_anz_mittel() + ""
                        + ",max_anz_gross=" + teil.get_max_anz_gross() + ""
                        + " WHERE id=" + teil.get_Id() + ";");
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
                statement.executeQuery("" + kommando + " into Lagerbestandskonto (fachnummer,teile_ID, menge,"
                        + "anschaffungsgrund,haltbarkeitsdatum) values ('" + lbk.get_Fachnummer() + "'," + lbk.get_TeileID() + "," + lbk.get_Menge() + ""
                        + ",'" + lbk.get_Anschaffungsgrund() + "','" + lbk.get_Haltbarkeitsdatum() + "');");
            } else if ("update".equals(kommando)) {
                statement.executeQuery("" + kommando + " Lagerbestandskonto SET menge= " + lbk.get_Menge() + ","
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
            statement.executeQuery("insert into FreieIDs (IDs) values (" + freieid + ");");
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
            statement.executeQuery("UPDATE Lagerfachstamm SET belegt ='" + belegung + "' WHERE fachnummer='" + fach + "';");

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
                statement.executeQuery("DELETE FROM " + tabelle + " WHERE IDs=" + loesch_id + "; ");
            } else if (loesch_fachnummer != null) {
                statement.executeQuery("DELETE FROM " + tabelle + " WHERE teile_ID=" + loesch_id + " AND fachnummer='" + loesch_fachnummer + "'; ");
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    /**
     *
     * Einfache Erstellung einer Verbindung ohne die Erstellung eines
     * SQLStatements
     */
    public void basic_connect(String select) throws ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Lagerverwaltung.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            rs = statement.executeQuery(select);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Sollte ein Array von Teil_Stammdaten Objekte anhand einer query zurückgeben
     * @param query Eine Query welche die Tabelle Teilestammdaten abfragen muss
     * @return
     * @throws ClassNotFoundException
     * @throws Exception 
     */
    public ArrayList<Teil_Stammdaten> connect_Teil_Stammdaten(String query) throws ClassNotFoundException, Exception {
        basic_connect(query);
        //System.out.println(resultset_to_arraylist());
        //ArrayList<Teil_Stammdaten> teil_stammdaten_array = new ArrayList<Teil_Stammdaten>();
        ArrayList<Teil_Stammdaten> teil_stammdaten_array = resultset_to_teil_stammdaten();
        disconnect();
        return teil_stammdaten_array;
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

    /**
     * Methode die aus einem ResultSet ein zweidimensionales Array generiert. Im
     * Moment werden nur die Strings, Integers, Double und Null Werte beachtet.
     * Für Blob Werte fehlt die Erkennung des typs. Beispielarray: [ [1,
     * schraube, null, metall, 2.5, schraube_m3, null, kleinteile, 100, 200,
     * 300], [2, mutter, null, metall, 1.5, mutter_m3, null, kleinteile, 80,
     * 170, 260] ]
     */
    public ArrayList<ArrayList> resultset_to_arraylist() {
        // ArrayList von Zeilen(enthält ArrayList Objekte die die Zeilen darstellen)
        ArrayList<ArrayList> zeilen = new ArrayList();
        try {
            // Object das aus dem die Spaltentypen und Anzahl der Spalten gewonnen werden kann
            ResultSetMetaData rmsd = rs.getMetaData();

            while (rs.next()) {
                // ArrayList die eine Zeile darstellt
                ArrayList zeile = new ArrayList();

                for (int i = 1; i <= rmsd.getColumnCount(); i++) {
                    switch (rmsd.getColumnType(i)) {
                        case java.sql.Types.INTEGER:
                            zeile.add(rs.getInt(i));
                            break;
                        case java.sql.Types.VARCHAR:
                            zeile.add(rs.getString(i));
                            break;
                        case java.sql.Types.FLOAT:
                            zeile.add(rs.getDouble(i));
                            break;
                        case java.sql.Types.NULL:
                            zeile.add(null);
                            break;
                    }
                }
                zeilen.add(zeile);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Datenbankverbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zeilen;
    }

    /**
     * Methode die versucht ein ResultSet zu einem Array von Lagerfachstamm
     * Objekten umzuwandeln
     *
     * @return Array von Lagerstammobjekten
     */
    public Lagerfachstamm[] resultset_to_lagerfachstamm() throws Exception {
        // ArrayList von Zeilen(enthält ArrayList Objekte die die Zeilen darstellen)
        ArrayList<Lagerfachstamm> lagerfachstammarray = new ArrayList<Lagerfachstamm>();
        try {
            // Object das aus dem die Spaltentypen und Anzahl der Spalten gewonnen werden kann
            ResultSetMetaData rmsd = rs.getMetaData();

            while (rs.next()) {
                // ArrayList die eine Zeile darstellt
                ArrayList zeile = new ArrayList();

                for (int i = 1; i <= rmsd.getColumnCount(); i++) {
                    switch (rmsd.getColumnType(i)) {
                        case java.sql.Types.INTEGER:
                            zeile.add(rs.getInt(i));
                            break;
                        case java.sql.Types.VARCHAR:
                            zeile.add(rs.getString(i));
                            break;
                        case java.sql.Types.FLOAT:
                            zeile.add(rs.getDouble(i));
                            break;
                        case java.sql.Types.NULL:
                            zeile.add(null);
                            break;
                    }
                }
                lagerfachstammarray.add(new Lagerfachstamm(zeile.toArray()));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Datenbankverbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (Lagerfachstamm[]) lagerfachstammarray.toArray();
    }

    /**
     * Aus einem ResultSet wird versucht ein Array von Teil_Stammdaten zu
     * erzeugen.
     *
     * @return
     * @throws Exception
     */
    public ArrayList<Teil_Stammdaten> resultset_to_teil_stammdaten() throws Exception {
        // ArrayList von Zeilen(enthält ArrayList Objekte die die Zeilen darstellen)
        ArrayList<Teil_Stammdaten> teil_stammdaten_array = new ArrayList<Teil_Stammdaten>();
        try {
            // Object das aus dem die Spaltentypen und Anzahl der Spalten gewonnen werden kann
            ResultSetMetaData rmsd = rs.getMetaData();
            if (rmsd.getColumnCount() > 11) {
                throw new Exception("Too much elements in ResultSet to turn it into teil_stammdaten");
            }
            Teil_Stammdaten teil_stamm = new Teil_Stammdaten();
            while (rs.next()) {

                teil_stamm.set_id(rs.getInt(1));
                teil_stamm.set_Teiletyp(TeileTypET.valueOf(rs.getString(2)));
                teil_stamm.set_zeichnungsnummer(rs.getString(3));
                teil_stamm.set_Materialgruppe(rs.getString(4));
                teil_stamm.set_preis(rs.getDouble(5));
                teil_stamm.set_Bezeichnung(rs.getString(6));
                teil_stamm.set_Baugruppe(rs.getString(7));
                teil_stamm.set_bemerkung(rs.getString(8));
                teil_stamm.set_max_anzahl_klein(rs.getInt(9));
                teil_stamm.set_max_anzahl_mittel(rs.getInt(10));
                teil_stamm.set_max_anzahl_gross(rs.getInt(11));
                teil_stammdaten_array.add(teil_stamm);
                teil_stamm = new Teil_Stammdaten();

            }

        } catch (SQLException ex) {
            Logger.getLogger(Datenbankverbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teil_stammdaten_array;

    }
   
    /**
     * Versucht ein ResultSet zu ein Array von Lagerbestandskonten Objekten  konvertieren
     * Achtung!: Das Haltbarkeitsdatum wird nicht aus der Datenbank gelesen sondern immer null gesetzt
     * @return
     * @throws Exception 
     */
    public Lagerbestandskonto[] resultset_to_lagerbestandskontos() throws Exception {
        // ArrayList von Zeilen(enthält ArrayList Objekte die die Zeilen darstellen)
        ArrayList<Lagerbestandskonto> bestandskonto_array = new ArrayList<Lagerbestandskonto>();
        try {
          
            // Object das aus dem die Spaltentypen und Anzahl der Spalten gewonnen werden kann
            ResultSetMetaData rmsd = rs.getMetaData();
            if (rmsd.getColumnCount() > 5) {
                throw new Exception("Too much elements in ResultSet to turn it into Lagerbestandskonto");
            }
            Lagerbestandskonto bestandskonto = new Lagerbestandskonto();
            while (rs.next()) {

                bestandskonto.set_Fachnummer(rs.getString(1));
                bestandskonto.set_id(rs.getInt(2));
                bestandskonto.set_Menge(rs.getInt(3));
                bestandskonto.set_Anschaffungsgrund(rs.getString(4));
                bestandskonto.set_Haltbarkeitsdatum(null); //TODO: Richtigen Wert aus der Datenbank holen
                bestandskonto_array.add(bestandskonto);
                bestandskonto = new Lagerbestandskonto();

            }
            

        } catch (SQLException ex) {
            Logger.getLogger(Datenbankverbindung.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (Lagerbestandskonto[]) bestandskonto_array.toArray();

    }
}
