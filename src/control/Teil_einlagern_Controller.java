/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import model.DB_schreiben;
import model.Lagerbestandskonto;
import model.Lagerfachstamm;
import model.Select_Allgemein;
import model.Select_Lagerbestandskonto;
import model.Select_Lagerfachstamm;
import model.Select_Stammdaten;
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
    private Select_Allgemein sa = new Select_Allgemein();
    private Select_Lagerbestandskonto lagerbes;
    private Select_Lagerfachstamm lf = new Select_Lagerfachstamm();

    public Teil_einlagern_Controller(Übersicht_Lagerverwaltung lv) {
        this.lv = lv;
    }
    String[] komplett = new String[0];
    String[] fach = new String[0];
    String[] menge = new String[0];
    String maxklein = "";
    String maxmittel = "";
    String maxgross = "";
    int zahlklein = 0;
    int zahlmittel = 0;
    int zahlgross = 0;

    public void einlagern(int id) {
        table = lv.table_einlagern;

        try {

            lagerbes = new Select_Lagerbestandskonto();
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
                    }
                    if (fach[i].endsWith("M")) {

                        flag = 2;

                    }
                    if (fach[i].endsWith("G")) {

                        flag = 3;
                    }
                }

            }

            if (flag == 1) {
                String[] max = new String[1];
                menge = sa.get_menge_ausDB("max_anz_klein", id);
                parse(id);

                if (zahlmittel == 0 || zahlgross == 0) {

                    if (zahlmittel == 0 && zahlgross == 0) {

                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K");
                        fach(id, belegung);
                    } else if (zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','K");
                        fach(id, belegung);

                    } else {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K','G");
                        fach(id, belegung);
                    }

                } else {
                    String[] belegung = lf.get_fachnummer_ausDB(false);
                    fach(id, belegung);
                }

                for (int i = 0; i < 1; i++) {
                    max[i] = maxklein;
                    System.out.println("Array   " + max[i]);
                }

                model.addColumn("aktuelle Menge", menge);
                model.addColumn("Maximale Anzahl kleines Fach", max);
            }

            if (flag == 2) {
                String[] max = new String[1];

                menge = sa.get_menge_ausDB("max_anz_mittel", id);

                if (zahlklein == 0 || zahlgross == 0) {

                    if (zahlklein == 0 && zahlgross == 0) {

                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M");
                        fach(id, belegung);
                    } else if (zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','K");
                        fach(id, belegung);

                    } else {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','G");
                        fach(id, belegung);
                    }

                } else {
                    String[] belegung = lf.get_fachnummer_ausDB(false);
                    fach(id, belegung);
                }

                for (int i = 0; i < 1; i++) {
                    max[i] = maxmittel;
                    //     System.out.println("Array   " + array[i]);
                }

                model.addColumn("aktuelle Menge", menge);
                model.addColumn("Maximale Anzahl mittleres Fach", max);

            }
            if (flag == 3) {

                String[] max = new String[1];

                menge = sa.get_menge_ausDB("max_anz_gross", id);
                if (zahlmittel == 0 || zahlklein == 0) {

                    if (zahlmittel == 0 && zahlklein == 0) {

                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "G");
                        fach(id, belegung);
                    } else if (zahlklein == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','G");
                        fach(id, belegung);

                    } else {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K','G");
                        fach(id, belegung);
                    }

                } else {
                    String[] belegung = lf.get_fachnummer_ausDB(false);
                    fach(id, belegung);
                }
                for (int i = 0; i < 1; i++) {
                    max[i] = maxgross;

                }

                model.addColumn("aktuelle Menge", menge);
                model.addColumn("Maximale Anzahl großes Fach", max);

            }

            String anz = lv.lagerTextfield1.getText();


            // System.out.println("Eingelagert: Teil mit ID " + id + " ins Fach " + anz + "  und  Menge  " + zahl + "   eingelagert.");
            fachtest();
            //  String a = lv.menge_textfeld_einlagern.getText();
            //  int einzulagern = cv.StringTOint(a);

            //   System.out.println("zahl"+a);

            // int neueMenge = einzulagern - zahl;
            //    String text = String.valueOf(neueMenge);
            //    lv.label_menge_übrig.setText(text);

            //  Lagerbestandskonto lbk = new Lagerbestandskonto(anz, id, zahl, null, null);
            //   dbs.insert_lagerbestandskonto(lbk);
            // dbs.update_lagerfachstamm(anz, true);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void erste_einlagerung(int id) throws SQLException {

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

    public void fach(int id, String[] belegung) throws SQLException {
        lagerbes = new Select_Lagerbestandskonto();
        boolean flag = false;

        String[] fach = lagerbes.get_Fachnummer_ausDB(id);

        int laengeinsgesamt = belegung.length + fach.length;
        komplett = new String[laengeinsgesamt];

        if (fach.length != 0) {

            for (int j = 0; j < fach.length; j++) {
                komplett[j] = fach[j];
                System.out.println("inhalt  " + komplett[j]);
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
}
