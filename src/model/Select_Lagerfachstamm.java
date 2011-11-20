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
   
        public String get_Lagerort_ausDB() throws ClassNotFoundException {
        
            db.connect();
            
            return "";
	}
	
	public String get_Hochregalnummer_ausDB() {
		return "";
	}
	
	public String get_Zeile_ausDB() {
		return "";
	}
	
	public String get_Spalte_ausDB() {
		return "";
	}
	
	public String get_Groesse_ausDB() {
		return "";
	}
	
	public boolean get_Belegung_ausDB() {
		return false;
	}
	
	public String get_Fachnummer_ausDB() {
		return "";
	}
	
	public ResultSet get_Object_ausDB() {
		return null;
	}
}
