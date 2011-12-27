/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package model;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author felix
 *
 * In dieser Klasse
 */
public class Select_Allgemein {

    Datenbankverbindung db = new Datenbankverbindung();

    /**
     *
     * @param tabelle
     * @return
     * @throws ClassNotFoundException
     */
    public static String create_select_ganze_tabelle(String tabelle) throws ClassNotFoundException {
        String query = "SELECT * FROM " + tabelle + ";";
        return query;
    }

    /**
     *
     * @param tabelle
     * @param spaltenwerte
     * @return
     */
    public static String create_select_suchen(String tabelle, String[][] spaltenwerte) {
        String query = "SELECT * FROM " + tabelle;
        int i = 0;

        for (String[] wert : spaltenwerte) {
            {
                if (!wert[1].equals("")) {
                    if (i == 0) {
                        query = query + " WHERE " + wert[0] + " LIKE \"%" + wert[1] + "%\"";
                        i++;
                    } else {
                        query = query + " AND " + wert[0] + " LIKE \"%" + wert[1] + "%\"";
                    }
                }
            }
        }
        return query + ";";
    }

    public void test_rmsd() throws SQLException, ClassNotFoundException {
        db.basic_connect("SELECT * FROM Teilestammdaten");
        ResultSetMetaData rmsd = db.rs.getMetaData();
        ArrayList<ArrayList> teilestammdaten = db.resultset_to_arraylist();
        System.out.println(teilestammdaten);


        //int columnType = rmsd.getColumnType(4);
        //String columnname = rmsd.getColumnName(4);
        //while (db.rs.next()) {
        //String value = db.rs.getString(1);
        //    System.out.println(value);
        //}
        //System.out.println(columnname + ": " + columnType);
        db.disconnect();
    }
    
    
    public String[] get_fachnummer_ausDB(String groesse) throws SQLException {
          try {
            db.connect("Select lf.fachnummer from Lagerfachstamm lf, Lagerbestandskonto lbk where lf.fachnummer=lbk.fachnummer and lbk.menge<(Select "+ groesse+" from Teilestammdaten) or belegt ='false'", "fachnummer");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }
    
    
    
    
    

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Select_Allgemein sa = new Select_Allgemein();

        sa.test_rmsd();
    }
}