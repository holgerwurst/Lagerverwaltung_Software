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
 * @author Wayne
 */
public class Select_Stammdaten {

    Datenbankverbindung db = new Datenbankverbindung();

    public String get_ID_ausDB(int id)  throws SQLException {
     
        try {
            db.connect("Select id from Teilestammdaten where id=" + id + "", "id");
            // ergebnis = db.rs.getString("id");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }
    
    public String get_Bezeichnung_ausDB(String bez) throws SQLException {
       
        try {
            db.connect("Select bezeichnung from Teilestammdaten where bezeichnung ='" + bez + "'", "bezeichnung");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }
 
    public String get_Bezeichnung_ausDB(int id)throws ClassNotFoundException {
       
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
            db.connect("Select preis from Teilestammdaten where id =" +id + "", "preis");
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

    public String get_max_anzahl_klein_ausDB(int id) throws ClassNotFoundException {
        try {
            db.connect("Select max_anz_klein from Teilestammdaten where id =" + id + "", "max_anz_klein");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
         return db.ergebnis;
    }

    public String get_max_anzahl_mittel_ausDB(int id) throws ClassNotFoundException {
       try {
            db.connect("Select max_anz_mittel from Teilestammdaten where id =" + id + "", "max_anz_mittel");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
          return db.ergebnis;
    }

    public String get_max_anzahl_gross_ausDB(int id) throws ClassNotFoundException {
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
            db.connect("Select baugruppe from Teilestammdaten where id =" +id + "", "baugruppe");
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
    public ArrayList<ArrayList> get_ganze_tabelle_teilestammdaten() throws ClassNotFoundException {
        String query = Select_Allgemein.create_select_ganze_tabelle("Teilestammdaten");
        db.basic_connect(query);
        ArrayList ganze_tabelle = db.resultset_to_arraylist();
        db.disconnect();
        return ganze_tabelle;
    }
}
