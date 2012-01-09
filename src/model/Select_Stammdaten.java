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
     * @param spaltenwerte
     * @return
     * @throws ClassNotFoundException
     * @throws Exception 
     */
    public ArrayList<Teil_Stammdaten> teile_suchen(String[][] suchwerte) throws ClassNotFoundException, Exception {
        String query = Select_Allgemein.create_select_teile_suchen(suchwerte);
        ArrayList<Teil_Stammdaten> teile_stammdaten_array = db.resultset_to_teil_stammdaten(query);
        db.disconnect();
        return teile_stammdaten_array;
    }
}
