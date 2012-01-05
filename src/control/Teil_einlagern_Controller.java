/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.*;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Matthias Sommer
 */
public class Teil_einlagern_Controller {

    private Übersicht_Lagerverwaltung lv;
    private convert cv = new convert();
    private Select_Stammdaten st = new Select_Stammdaten();
    private DB_schreiben dbs = new DB_schreiben();
    private JTable table = new JTable();
    private DefaultTableModel model = new DefaultTableModel();
    private Select_Allgemein sa;
    private Select_Lagerbestandskonto lagerbes;
    private Select_Lagerfachstamm lf = new Select_Lagerfachstamm();

    public Teil_einlagern_Controller(Übersicht_Lagerverwaltung lv) {
        this.lv = lv;
    }
    String[] komplett = new String[0];
    String[] fach = new String[0];
    String[] menge = new String[0];
    //   String[] mengemittel = new String[0];
    //   String[] mengegross = new String[0];
    String maxklein = "";
    String maxmittel = "";
    String maxgross = "";
    int zahlklein = 0;
    int zahlmittel = 0;
    int zahlgross = 0;

    public void teil_auswaehlen(String bez) {
        table = lv.table_einlagern;

        String[] id = new String[0];
        String bezeichnung = "";
        String[] bezeichnungarray = new String[0];
        //st= new Select_Stammdaten();

        try {
            id = st.get_ID_array_ausDB(bez);
            bezeichnungarray = new String[id.length];
            for (int i = 0; i < id.length; i++) {

                int zahl = cv.StringTOint(id[i]);
                System.out.println("zahl  " + zahl);
                bezeichnung = st.get_Bezeichnung_ausDB(zahl);
                System.out.println(bezeichnung);

                for (int j = 0; j < 1; j++) {

                    bezeichnungarray[i] = bezeichnung;
                }
            }

            model.addColumn("ID", id);
            model.addColumn("Bezeichnung", bezeichnungarray);
            table.setModel(model);
            //String mar = (String) (table.getValueAt(table.getSelectedRow(), 0));

            //    System.out.println(mar);
        } catch (SQLException e) {
            System.out.println(e);
        }
        //   te.einlagern(id);
    }

    public void einlagern_vorbereiten(int id) {
        table = lv.table_einlagern;

        try {
            lagerbes = new Select_Lagerbestandskonto();
            sa = new Select_Allgemein();
            fach = lagerbes.get_Fachnummer_ausDB(id);

            int flag = 0;
            if (fach.length == 0) {
                erste_einlagerung(id);
            } else {

                for (int i = 0; i < fach.length; i++) {

                    if (fach[i].endsWith("K")) {

                        flag = 1;
                        System.out.println("kleines fach " + fach[i]);
                        // System.out.println("Menge " + menge[i]);

                    } else if (fach[i].endsWith("M")) {
                        System.out.println("mittleres fach " + fach[i]);
                        flag = 2;

                    } else if (fach[i].endsWith("G")) {
                        flag = 3;
                    }
                }
            }

            if (flag == 1) {
                String[] maxK = new String[1];
                menge = sa.get_menge_ausDB("'max_anz_klein'", id);

                parse(id);

                if (zahlmittel == 0 || zahlgross == 0) {

                    if (zahlmittel == 0 && zahlgross == 0) {

                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K");
                        freieFaecher(id, belegung, "max_anz_klein");
                    } else if (zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','K");
                        freieFaecher(id, belegung, "max_anz_klein");

                    } else {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K','G");
                        freieFaecher(id, belegung, "max_anz_klein");
                    }

                } else {
                    String[] belegung = lf.get_fachnummer_ausDB(false);
                    freieFaecher(id, belegung, "max_anz_klein");
                }

                for (int i = 0; i < 1; i++) {
                    maxK[i] = maxklein;
                    //      System.out.println("Array   " + maxK[i]);
                    //    System.out.println("max " + maxK[i]);
                }

                model.addColumn("aktuelle Menge", menge);
                model.addColumn("Maximale Anzahl kleines Fach", maxK);

            }

            if (flag == 2) {
                String[] maxM = new String[1];
                menge = sa.get_menge_ausDB("'max_anz_mittel'", id);
                parse(id);

                if (zahlklein == 0 || zahlgross == 0) {

                    if (zahlklein == 0 && zahlgross == 0) {

                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M");
                        freieFaecher(id, belegung, "max_anz_mittel");
                    } else if (zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','K");
                        freieFaecher(id, belegung, "max_anz_mittel");

                    } else {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','G");
                        freieFaecher(id, belegung, "max_anz_mittel");
                    }

                } else {
                    String[] belegung = lf.get_fachnummer_ausDB(false);
                    freieFaecher(id, belegung, "max_anz_mittel");
                }

                for (int i = 0; i < 1; i++) {
                    maxM[i] = maxmittel;
                    //     System.out.println("Array   " + array[i]);
                    System.out.println("max  " + maxM[i]);
                }

                model.addColumn("aktuelle Menge", menge);
                model.addColumn("Maximale Anzahl mittleres Fach", maxM);

            }
            if (flag == 3) {

                String[] maxG = new String[1];
                menge = sa.get_menge_ausDB("max_anz_gross", id);
                parse(id);

                if (zahlmittel == 0 || zahlklein == 0) {

                    if (zahlmittel == 0 && zahlklein == 0) {

                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "G");
                        freieFaecher(id, belegung, "max_anz_gross");
                    } else if (zahlklein == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','G");
                        freieFaecher(id, belegung, "max_anz_gross");

                    } else {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K','G");
                        freieFaecher(id, belegung, "max_anz_gross");
                    }

                } else {
                    String[] belegung = lf.get_fachnummer_ausDB(false);
                    freieFaecher(id, belegung, "max_anz_gross");
                }
                for (int i = 0; i < 1; i++) {
                    maxG[i] = maxgross;
                }

                model.addColumn("aktuelle Menge", menge);
                model.addColumn("Maximale Anzahl großes Fach", maxG);
            }

            // System.out.println("Eingelagert: Teil mit ID " + id + " ins Fach " + anz + "  und  Menge  " + zahl + "   eingelagert.");
            fachtest();


        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void einlagern(String fachnummer, int id) {
        try {
            lagerbes = new Select_Lagerbestandskonto();
            st = new Select_Stammdaten();
            cv = new convert();

            String menge = "";

            // System.out.println("ID " + ids);
            // int id = cv.StringTOint(ids);

            String inhalt = lv.menge_textfeld_einlagern.getText();

            int einzulagern = cv.StringTOint(inhalt);

            System.out.println("zahl  " + inhalt);

            //String mar = (String) (table.getValueAt(table.getSelectedRow(), 0));
            System.out.println("Fachnummer " + fachnummer);

            if (fachnummer.endsWith("K")) {
                menge = st.get_max_anzahl_klein_ausDB(id);

            } else if (fachnummer.endsWith("M")) {

                menge = st.get_max_anzahl_mittel_ausDB(id);

            } else if (fachnummer.endsWith("G")) {

                menge = st.get_max_anzahl_gross_ausDB(id);
            }
            int zahl = cv.StringTOint(menge);

            int neueMenge = einzulagern - zahl;
            String text = String.valueOf(neueMenge);
            lv.label_menge_übrig.setText(text);

            Lagerbestandskonto lbk = new Lagerbestandskonto(fachnummer, id, zahl, null, null);
            dbs.insert_lagerbestandskonto(lbk);
            dbs.update_lagerfachstamm(fachnummer, true);

        } catch (Exception e) {
        }
    }

    public void erste_einlagerung(int id) throws SQLException {
        lf = new Select_Lagerfachstamm();

        String[] maxarrayklein = new String[1];
        String[] maxarraymittel = new String[1];
        String[] maxarraygross = new String[1];
        parse(id);

        if (zahlklein == 0 || zahlmittel == 0 || zahlgross == 0) {

            if (zahlklein == 0 && zahlmittel == 0 && zahlgross == 0) {
                JOptionPane.showMessageDialog(lv.bestaetigen_button2, "Für dieses Teil gibt es kein passendes Fach.", "Hinweis", 1);
                //   System.out.println("Für dieses Teil gibt es kein passendes Fach");
            } else if (zahlklein == 0 && zahlmittel == 0) {

                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "G");
                fach(id, belegung);
            } else if (zahlklein == 0 && zahlgross == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M");
                fach(id, belegung);

            } else if (zahlmittel == 0 && zahlgross == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K");
                fach(id, belegung);
            } else if (zahlklein == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','G");
                fach(id, belegung);

            } else if (zahlmittel == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K','G");
                fach(id, belegung);
            } else if (zahlgross == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','K");
                fach(id, belegung);
            }

        } else {
            String[] belegung = lf.get_fachnummer_ausDB(false);
            fach(id, belegung);
        }

        for (int i = 0; i < 1; i++) {

            maxarrayklein[i] = maxklein;
            maxarraymittel[i] = maxmittel;
            maxarraygross[i] = maxgross;
        }

        model.addColumn("Maximale Anzahl kleines Fach", maxarrayklein);
        model.addColumn("Maximale Anzahl mittleres Fach", maxarraymittel);
        model.addColumn("Maximale Anzahl großes Fach", maxarraygross);

    }

    public void parse(int id) throws SQLException {

        maxklein = st.get_max_anzahl_klein_ausDB(id);
        maxmittel = st.get_max_anzahl_mittel_ausDB(id);
        maxgross = st.get_max_anzahl_gross_ausDB(id);

        zahlklein = cv.StringTOint(maxklein);
        zahlmittel = cv.StringTOint(maxmittel);
        zahlgross = cv.StringTOint(maxgross);

    }

    public void freieFaecher(int id, String[] belegung, String groesse) throws SQLException {
        lagerbes = new Select_Lagerbestandskonto();
        boolean flag = false;

        String[] fach = lagerbes.get_Fachnummer_gueltig_ausDB(id, groesse);

        int laengeinsgesamt = belegung.length + fach.length;
        komplett = new String[laengeinsgesamt];

        if (fach.length != 0) {

            for (int j = 0; j < fach.length; j++) {
                komplett[j] = fach[j];
                //   System.out.println("inhalt  " + komplett[j]);
                flag = true;
            }

        } else {
            flag = true;
        }

        if (flag == true) {
            for (int i = 0; i < belegung.length; i++) {
                int laenge = fach.length;
                //System.out.println("länge" + fach.length);        
                komplett[i + laenge] = belegung[i];
            }

            model.addColumn("Freie Fächer", komplett);
        }
    }

    public void fach(int id, String[] belegung) throws SQLException {
        lagerbes = new Select_Lagerbestandskonto();

        String[] fach = lagerbes.get_Fachnummer_ausDB(id);
        komplett = new String[belegung.length];

        {
            for (int i = 0; i < belegung.length; i++) {

                komplett[i] = belegung[i];
            }

            model.addColumn("Freie Fächer", komplett);
        }
    }

    public void fachtest() {

        //  model.addColumn("einlagern?");
        //  create_table(komplett.length, 5);
        table.setModel(model);

        /*
         * for (int i = 0; i <komplett.length; i++) { for (int j = 0; j < 4;
         * j++) { // trinken[i] = domi.getName(i);
         *
         * if (j == 0) { // data[i][j] =test[i]; model.setValueAt(komplett[i],
         * i, j);
         *
         * }
         *
         * if (j == 1) { // data[i][j] =test[i]; for(int k=0; k<fach.length;k++)
         * model.setValueAt(menge[k], k, j); //
         * tabelle.getColumn(domi.getHeadlines(1)).setPreferredWidth(100); } } }
         *
         */

        //   table.setModel(model);

    }

    /*
     * public void create_tabel(int zeile, int spalte) { model = new
     * javax.swing.table.DefaultTableModel(zeile, spalte) {
     *
     * @Override public Class<?> getColumnClass(int column) { if (column == 4) {
     * return Boolean.class; } return super.getColumnClass(column); } };
     *
     * }
     */
    public void create_table(int zeile, int spalte) {

        model = new javax.swing.table.DefaultTableModel(zeile, spalte) {

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 4) {
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };

    }

    /*
     * public JCheckBox getColumnClass(int column) { if (column == 4) {
     * JCheckBox box = new JCheckBox(); box.setVisible(true); return box;
     *
     * }
     * return getColumnClass(column); }
     */
    /**
     *
     */
    public void manuell_einlagern_fachcheck(String f1, String m1, int id) throws ClassNotFoundException, SQLException, Exception {
        Pruefen_Controller pr = new Pruefen_Controller();
        cv = new convert();
        Datenbankverbindung db = new Datenbankverbindung();
        Lagerbestandskonto[] lb = db.resultset_to_lagerbestandskontos();
        Lagerfachstamm[] lfs = db.resultset_to_lagerfachstamm();
        Teil_Stammdaten[] ts = (Teil_Stammdaten[]) db.resultset_to_teil_stammdaten().toArray();
        int f;
        int i = 0;
        boolean eqls = false;
        while (eqls == false) {
            if (i < lfs.length) {
                if (f1.equals(lfs[i].get_Fachnummer())) {
                    eqls = true;
                    f = i;
                } else {
                    i++;
                }
            } else {
                JOptionPane.showMessageDialog(lv.button_manuell_einlagern, "Teil nicht vorhanden");
                break;
            }
        }



        /**
         * for (int i = 0; i < lfs.length; i++) { if
         * (f1.equals(lfs[i].get_Fachnummer())) { f = i; } }
         *
         * if (i != 99999) { slf = new Select_Lagerfachstamm(); String bel =
         * slf.get_Belegung_ausDB(slf_arr[i]); Lagerbestandskonto[] lb =
         * db.resultset_to_lagerbestandskontos(); if ("true".equals(bel)) { int
         * chk_id = lb[1].get_TeileID(); if (chk_id == id) {
         * lb[i].get_Fachnummer(); } } System.out.println(bel);
         *
         * //if()
         *
         * //belegung prüfen
         *
         * //
        }
         */
        //System.out.println(slf_arr[i]);
    }
}
