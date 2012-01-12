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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Select_Allgemein sa = new Select_Allgemein();

        sa.test_rmsd();
    }
}
