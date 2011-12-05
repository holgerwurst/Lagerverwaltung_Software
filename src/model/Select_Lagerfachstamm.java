/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Datenbankverbindung;

/**
 *
 * @author Wayne
 */
public class Select_Lagerfachstamm {

    Datenbankverbindung db = new Datenbankverbindung();

    public String get_Lagerort_ausDB(String fnr) throws ClassNotFoundException {

        String temp = "";
    //    temp = db.connect("Lagerfachstamm", "lagerort", "fachnummer", fnr);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Hochregalnummer_ausDB(String fnr) throws Exception {
        String ergebnis = "";
       db.connect("Select hochregalnummer from Lagerfachstamm where fachnummer='"+fnr+"'");
        ergebnis = db.rs.getString("hochregalnummer");    
        db.disconnect();
        
        //System.out.println(temp);
        return ergebnis;
        
    }

    public String get_Zeile_ausDB(String fnr) throws ClassNotFoundException {
        String temp = "";
       // temp = db.connect("Lagerfachstamm", "zeile", "fachnummer", fnr);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Spalte_ausDB(String fnr) throws ClassNotFoundException {
        String temp = "";
     //   temp = db.connect("Lagerfachstamm", "spalte", "fachnummer", fnr);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Groesse_ausDB(String fnr) throws ClassNotFoundException {
        String temp = "";
     //   temp = db.connect("Lagerfachstamm", "groesse", "fachnummer", fnr);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Belegung_ausDB(String fnr) throws ClassNotFoundException {
        String temp = "";
     //   temp = db.connect("Lagerfachstamm", "belegt", "fachnummer", fnr);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    /*
	public String get_Fachnummer_ausDB(String fnr) throws ClassNotFoundException {
            String temp="";
            temp = db.connect("Lagerfachstamm","*","fachnummer",fnr);
            db.disconnect();
            //System.out.println(temp);
            return temp;
	}
	*/
    public String[] get_spalte_fachnummer_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerfachstamm", "fachnummer");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_lagerort_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerfachstamm", "lagerort");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_hochregalnummer_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerfachstamm", "hochregalnummer");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_zeile_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerfachstamm", "zeile");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_spalte_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerfachstamm", "spalte");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_groesse_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerfachstamm", "groesse");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_belegung_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerfachstamm", "belegt");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public static void main(String[] args) throws Exception {
        Select_Lagerfachstamm sf = new Select_Lagerfachstamm();
        
        String a = sf.get_Hochregalnummer_ausDB("FH1Z01S01K");
        System.out.println(a);
        
        /*try {
            String[] temp1 = sf.get_spalte_fachnummer_ausDB();
            for (int i = 0; i < temp1.length; i++) {
                System.out.println(temp1[i]);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Select_Lagerfachstamm.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
