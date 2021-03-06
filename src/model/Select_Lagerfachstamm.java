/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Wayne
 */
public class Select_Lagerfachstamm {

    Datenbankverbindung db = new Datenbankverbindung();

    public String[] get_Lagerort_ausDB(String fnr) throws ClassNotFoundException {
        try {
            db.connect("Select lagerort from Lagerfachstamm where fachnummer ='" + fnr + "'", "lagerort");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_Hochregalnummer_ausDB(String fnr) throws Exception {
        try {
            db.connect("Select hochregalnummer from Lagerfachstamm where fachnummer ='" + fnr + "'", "hochregalnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_Zeile_ausDB(String fnr) throws ClassNotFoundException {
        try {
            db.connect("Select zeile from Lagerfachstamm where fachnummer ='" + fnr + "'", "zeile");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_Spalte_ausDB(String fnr) throws ClassNotFoundException {
        try {
            db.connect("Select spalte from Lagerfachstamm where fachnummer ='" + fnr + "'", "spalte");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_Groesse_ausDB(String fnr) throws ClassNotFoundException {
        try {
            db.connect("Select groesse from Lagerfachstamm where fachnummer ='" + fnr + "'", "groesse");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String get_Belegung_ausDB(String fnr) throws SQLException {
        try {
            db.connect("Select belegt from Lagerfachstamm where fachnummer ='" + fnr + "'", "belegt");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String[] get_fachnummer_ausDB(Boolean belegung) throws SQLException {
        try {
            db.connect("Select fachnummer from Lagerfachstamm where belegt ='" + belegung + "'", "fachnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_fachnummer_2groessen_ausDB(Boolean belegung, String groesse) {
        try {
            db.connect("Select fachnummer from Lagerfachstamm where groesse in ('" + groesse + "') and belegt='" + belegung + "'", "fachnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }


    /*
     * public String get_Fachnummer_ausDB(String fnr) throws
     * ClassNotFoundException { String temp=""; temp =
     * db.connect("Lagerfachstamm","*","fachnummer",fnr); db.disconnect();
     * //System.out.println(temp); return temp; }
     */
    public String[] get_spalte_fachnummer_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerfachstamm", "fachnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_lagerort_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerfachstamm", "lagerort");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_hochregalnummer_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerfachstamm", "hochregalnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_zeile_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerfachstamm", "zeile");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_spalte_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerfachstamm", "spalte");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_groesse_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerfachstamm", "groesse");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_belegung_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerfachstamm", "belegung");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }
    
    
    /**
     * SELECT der erstellt wird um nach einem fach zu suchen
     * @param suchwerte (Array mit {{Spaltenname, Suchwert}, ... }
     * @return SELECT für Fach suchen
     */
            private static String create_select_fach_suchen(String[][] suchwerte) {
        String query = "SELECT * FROM Lagerfachstamm";
        int i = 0;

        for (String[] wert : suchwerte) {
            // Wenn der Suchwert leer ist soll nichts zu dem Select hinzugefügt werden
            if (!wert[1].equals("")) {
                // Wenn es der erste Wert ist muss im Select WHERE vorher eingefügt werden nicht AND
                if (i == 0) {
                    query = query + " WHERE " + wert[0] + "=\"" + wert[1] + "\"";
                    i++;
                    System.out.println(query);

                } else {
                    query = query + " AND " + wert[0] + "=\"" + wert[1] + "\"";
                    System.out.println(query);
                }
            }
        }
        return query;
    }
            
    /**
     * Sucht in der Datenbank nach Fächern
     * @param suchwerte Array mit {{Spaltenname, Suchwert}, ... }
     * @return Array von Lagerfachstamm Objekten
     * @throws ClassNotFoundException
     * @throws Exception 
     */
    public ArrayList<Lagerfachstamm> fach_suchen(String[][] suchwerte) throws ClassNotFoundException, Exception {
        // SELECT wird erstellt
        String query = Select_Lagerfachstamm.create_select_fach_suchen(suchwerte);
        // Aus der Datenbank wird LagerfachstammArray gelesen
        ArrayList<Lagerfachstamm> lagerfachstamm_array = db.resultset_to_lagerfachstamm(query);
        // Verbindung muss geschlossen werden damit die Datenbank nicht blockiert wird
        db.disconnect();
        return lagerfachstamm_array;
    }
}
/*
 * public static void main(String[] args) throws Exception {
 * Select_Lagerfachstamm sf = new Select_Lagerfachstamm();
 *
 * String a = sf.get_Hochregalnummer_ausDB("FH1Z01S01K"); System.out.println(a);
 *
 * try { String[] temp1 = sf.get_spalte_fachnummer_ausDB(); for (int i = 0; i <
 * temp1.length; i++) { System.out.println(temp1[i]); } } catch
 * (ClassNotFoundException ex) {
 * Logger.getLogger(Select_Lagerfachstamm.class.getName()).log(Level.SEVERE,
 * null, ex); } }
 */
