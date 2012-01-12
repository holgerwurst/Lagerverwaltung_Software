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
public class Select_Lagerbestandskonto {

    Datenbankverbindung db = new Datenbankverbindung();

    public String get_ID_ausDB(String fachnummer) throws SQLException {
        try {
            db.connect("Select teile_id from Lagerbestandskonto where fachnummer ='" + fachnummer + "'", "teile_id");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String[] get_Fachnummer_ausDB(int id) throws SQLException {
        try {
            db.connect("Select fachnummer from Lagerbestandskonto where teile_ID =" + id + "", "fachnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_Menge_ausDB(String fachnummer) throws SQLException {
        try {
            db.connect("Select menge from Lagerbestandskonto where fachnummer ='" + fachnummer + "'", "menge");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String get_Menge_aktuell_ausDB(String fachnummer) throws SQLException {
        try {
            db.connect("Select menge from Lagerbestandskonto where fachnummer ='" + fachnummer + "'", "menge");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ergebnis;
    }

    public String[] get_Anschaffungsgrund_ausDB(String fachnummer) throws SQLException {
        try {
            db.connect("Select anschaffungsgrund from Lagerbestandskonto where fachnummer ='" + fachnummer + "'", "anschaffungsgrund");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_Haltbarkeitsdatum_ausDB(String fachnummer) throws ClassNotFoundException {
        try {
            db.connect("Select haltbarkeitsdatum from Lagerbestandskonto where fachnummer ='" + fachnummer + "'", "haltbarkeitsdatum");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_fachnummer_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerbestandskonto", "fachnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_teile_ID_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerbestandskonto", "teile_id");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_menge_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerbestandskonto", "mange");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_anschaffungsgrund_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerbestandskonto", "anschaffungsgrund");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_haltbarkeitsdatum_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerbestandskonto", "haltbarkeitsdatum");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    /**
     * Es soll in der Datenbank nach Lagerbeständen gesucht werden
     *
     * @param suchwerte (Array mit {{Spaltenname, Suchwert}, ... }
     * @return Eine ArrayList von Lagerbestandkonto Objekten die gefunden wurden
     * @throws Exception
     */
    public ArrayList<Lagerbestandskonto> teile_im_fach_suchen(String[][] suchwerte) throws Exception {
        // SELECT wird erstellt
        String query = create_select_teil_im_fach_suchen(suchwerte);
        // Eine ArrayList von Lagerbestandkontos wird aus der Datenbank gelesen
        ArrayList<Lagerbestandskonto> lagerbestandskontos = db.resultset_to_lagerbestandskontos(query);
        // Verbindung muss geschlossen werden da sie sonst die DB blockiert
        db.disconnect();
        return lagerbestandskontos;

    }

    public static String create_select_teil_im_fach_suchen(String[][] suchwerte) {
        String query = "SELECT * FROM Lagerbestandskonto";
        int i = 0;

        for (String[] wert : suchwerte) {
            // Wenn der Suchwert leer ist soll nichts zu dem Select hinzugefügt werden
            if (!wert[1].equals("")) {
                // Wenn es der erste Wert ist muss im Select WHERE vorher eingefügt werden nicht AND
                if (i == 0) {
                    // Navh ids, und der Menge soll exakt gesucht werden
                    if (wert[0].equals("teile_id") || wert[0].equals("menge")) {
                        query = query + " WHERE " + wert[0] + "=" + wert[1];
                    } // Auch nach der Fachnummer soll exakt gesucht werden, sie isst aber ein String
                    else if (wert[0].equals("fachnummer")) {
                        query = query + " WHERE " + wert[0] + "=\"" + wert[1] + "\"";
                        System.out.println(query);
                    } // Es wird gesucht nach Fächern die bis zu einer bestimmten Anzahl gefüllt sind
                    else if (wert[0].equals("max_menge")) {
                        query = query + " WHERE menge" + "<=" + wert[1];
                    } // Es wird gesucht nach Fächern wo mindestens eine Anzahl von Teilen eingelagert sind
                    else if (wert[0].equals("mind_menge")) {
                        query = query + " WHERE menge" + ">=" + wert[1];
                    } else {

                        query = query + " WHERE " + wert[0] + " LIKE \"%" + wert[1] + "%\"";
                    }
                    i++;
                    // Hier das selbe wie oben nur anstatt WHERE wird AND genutzt als verknüpfung der Suchteile
                } else {
                    // Nach Fachnummern, ids, und der Menge soll exakt gesucht werden
                    if (wert[0].equals("teile_id") || wert[0].equals("menge")) {
                        query = query + " AND " + wert[0] + "=" + wert[1];
                    } // Auch nach der Fachnummer soll exakt gesucht werden, sie isst aber ein String
                    else if (wert[0].equals("fachnummer")) {
                        query = query + " AND " + wert[0] + "=\"" + wert[1] + "\"";
                        System.out.println(query);
                    } // Es wird gesucht nach Fächern die bis zu einer bestimmten Anzahl gefüllt sind
                    else if (wert[0].equals("max_menge")) {
                        query = query + " AND menge" + "<=" + wert[1];
                    } // Es wird gesucht nach Fächern wo mindestens eine Anzahl von Teilen eingelagert sind
                    else if (wert[0].equals("mind_menge")) {
                        query = query + " AND menge" + ">=" + wert[1];
                    } else {
                        query = query + " AND " + wert[0] + " LIKE \"%" + wert[1] + "%\"";
                    }
                }
            }
        }
        return query;
    }
}
