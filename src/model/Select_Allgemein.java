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
    public static String create_select_teile_suchen(String[][] spaltenwerte) {
        String query = "SELECT * FROM Teilestammdaten";
        int i = 0;

        for (String[] wert : spaltenwerte) {
            {
                if (!wert[1].equals("")) {
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

                    } else {
                        if (wert[0].equals("id")) {
                            query = query + " AND " + wert[0] + "=" + wert[1];
                        } else if (wert[0].equals("max_anz_klein") || wert[0].equals("max_anzahl_mittel")
                                || wert[0].equals("max_anz_gross")) {
                            query = query + " AND " + wert[0] + "<=" + wert[1];
                        } else {
                            query = query + " AND " + wert[0] + " LIKE \"%" + wert[1] + "%\"";
                        }
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

    public String[] get_menge_ausDB(String groesse, int id) throws SQLException {
        try {
            db.connect("Select menge from Lagerbestandskonto where teile_ID=" + id + " and menge<(Select " + groesse + " from Teilestammdaten where id=" + id + ")", "menge");
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