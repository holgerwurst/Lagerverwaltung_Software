/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wayne
 */
public class Select_Stammdaten {

    Datenbankverbindung db = new Datenbankverbindung();

    public String get_ID_ausDB(int id) throws SQLException {

        try {
            db.connect("Select id from Teilestammdaten where id=" + id + "", "id");
            // ergebnis = db.rs.getString("id");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String[] get_ID_array_ausDB(String bez) throws SQLException {

        try {
            db.connect("Select id from Teilestammdaten where bezeichnung like '" + bez + "%" + "'", "id");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String get_Bezeichnung_ausDB(int id) throws SQLException {

        try {
            db.connect("Select bezeichnung from Teilestammdaten where id=" + id + "", "bezeichnung");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String get_Teiletyp_ausDB(int id) throws ClassNotFoundException {

        try {
            db.connect("Select teiletyp from Teilestammdaten where id =" + id + "", "teiletyp");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;

    }

    public String get_Materialgruppe_ausDB(int id) throws ClassNotFoundException {
        try {
            db.connect("Select materialgruppe from Teilestammdaten where id =" + id + "", "materialgruppe");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String get_Zeichnungsnummer_ausDB(int id) throws ClassNotFoundException {
        try {
            db.connect("Select zeichnungsnummer from Teilestammdaten where id =" + id + "", "zeichnungsnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String get_Preis_ausDB(int id) throws ClassNotFoundException {
        try {
            db.connect("Select preis from Teilestammdaten where id =" + id + "", "preis");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String get_Bemerkung_ausDB(int id) throws ClassNotFoundException {
        try {
            db.connect("Select bemerkung from Teilestammdaten where id =" + id + "", "bemerkung");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String get_max_anzahl_klein_ausDB(int id) throws SQLException {
        try {
            db.connect("Select max_anz_klein from Teilestammdaten where id =" + id + "", "max_anz_klein");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String get_max_anzahl_mittel_ausDB(int id) throws SQLException {
        try {
            db.connect("Select max_anz_mittel from Teilestammdaten where id =" + id + "", "max_anz_mittel");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String get_max_anzahl_gross_ausDB(int id) throws SQLException {
        try {
            db.connect("Select max_anz_gross from Teilestammdaten where id =" + id + "", "max_anz_gross");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String get_Baugruppe_ausDB(int id) throws ClassNotFoundException {
        try {
            db.connect("Select baugruppe from Teilestammdaten where id =" + id + "", "baugruppe");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String[] get_spalte_id_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "id");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_Teiletyp_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "teiletyp");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_zeichnungsnummer_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "zeichnungsnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_preis_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "preis");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_bezeichnung_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "bezeichnung");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_materialgruppe_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "materialgruppe");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_baugruppe_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "baugruppe");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_bemerkung_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "bemerkung");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_max_anzahl_klein_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "max_anz_klein");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_max_anzahl_mittel_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "max_anz_mittel");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_max_anzahl_gross_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Teilestammdaten", "max_anz_gross");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }
    // gibt zweidimensionale tabelle aller zeilen zurück

    public ArrayList<Teil_Stammdaten> get_ganze_tabelle_teilestammdaten() throws ClassNotFoundException, Exception {
        ArrayList<Teil_Stammdaten> ganze_tabelle = db.resultset_to_teil_stammdaten();
        db.disconnect();
        return ganze_tabelle;

    }

    /**
     * Erstellung des selcetes aus den werten und herausholen der Daten
     *
     * @param spaltenwerte
     * @return
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public ArrayList<Teil_Stammdaten> teile_suchen(String[][] suchwerte) throws ClassNotFoundException, Exception {
        // Erstellt den SELECT
        String query = create_select_teile_suchen(suchwerte);
        // TeilstammdatenArray wird aus der Datenbank gelesen
        ArrayList<Teil_Stammdaten> teile_stammdaten_array = db.resultset_to_teil_stammdaten(query);
        // Damit die Datenbank nicht blockiert wird muss die Verbindung disconnected werden
        db.disconnect();
        return teile_stammdaten_array;
    }

    /**
     * Erstellt den Select für die Suche in den Teilestammdaten
     * @param suchwerte Zweidimensionales Array {{spaltenname, suchwert}, ... }
     * @return Select mit AND verknüpften Suchwerten nach WHERE
     */
    public static String create_select_teile_suchen(String[][] suchwerte) {
        String query = "SELECT * FROM Teilestammdaten";
        int i = 0;

        for (String[] wert : suchwerte) {

            // Wenn der Suchwert leer ist soll nichts zu dem Select hinzugefügt werden
            if (!wert[1].equals("")) {
                // Wenn es der erste Wert ist muss im Select WHERE vorher eingefügt werden nicht AND
                if (i == 0) {
                    // IDs sollen exakt gesucht werden
                    if (wert[0].equals("id")) {
                        query = query + " WHERE " + wert[0] + "=" + wert[1];

                    } // Es soll nach allen Teilen gesucht werden die in ein Fach reinpassen
                    else if (wert[0].equals("max_anz_klein") || wert[0].equals("max_anzahl_mittel")
                            || wert[0].equals("max_anz_gross")) {

                        query = query + " WHERE " + wert[0] + "<=" + wert[1];
                    } else {
                        query = query + " WHERE " + wert[0] + " LIKE \"%" + wert[1] + "%\"";
                    }
                    i++;
                    // Ab dem zweiten eintrag in Suchwerte wird alles mit AND hinzugefügt
                } else {
                    if (wert[0].equals("id")) {
                        query = query + " AND " + wert[0] + "=" + wert[1];
                    } else if (wert[0].equals("max_anz_klein") || wert[0].equals("max_anz_mittel")
                            || wert[0].equals("max_anz_gross")) {
                        query = query + " AND " + wert[0] + "<=" + wert[1];
                    } else {
                        query = query + " AND " + wert[0] + " LIKE \"%" + wert[1] + "%\"";
                    }
                }

            }
        }
        return query + ";";
    }
}
