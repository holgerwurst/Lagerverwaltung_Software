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
    String[] ergebnis = new String[0];
    ArrayList<String> werte = new ArrayList<String>();
    ArrayList<String> menge_aktuell = new ArrayList<String>();
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
            cv = new convert();

            if (fach.length == 0) {
                erste_einlagerung(id);
            } else {
                parse(id);

                for (int i = 0; i < fach.length; i++) {

                    if (fach[i].endsWith("K")) {
                        int menge = cv.StringTOint(lagerbes.get_Menge_aktuell_ausDB(fach[i]));
                        if (menge < zahlklein) {

                            String mengeS = String.valueOf(menge);

                            menge_aktuell.add(mengeS);
                            werte.add(fach[i]);

                        }
                    } else if (fach[i].endsWith("M")) {

                        int menge = cv.StringTOint(lagerbes.get_Menge_aktuell_ausDB(fach[i]));
                        if (menge < zahlmittel) {

                            String mengeS = String.valueOf(menge);
                            menge_aktuell.add(mengeS);
                            werte.add(fach[i]);
                        }
                    } else if (fach[i].endsWith("G")) {

                        int menge = cv.StringTOint(lagerbes.get_Menge_aktuell_ausDB(fach[i]));
                        if (menge < zahlgross) {

                            String mengeS = String.valueOf(menge);
                            menge_aktuell.add(mengeS);
                            werte.add(fach[i]);
                        }
                    }

                }


                if (zahlmittel == 0 || zahlklein == 0 || zahlgross == 0) {


                    if (zahlklein == 0 && zahlmittel == 0) {

                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "G");
                        freieFaecher(belegung);
                    } else if (zahlklein == 0 && zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M");
                        freieFaecher(belegung);

                    } else if (zahlmittel == 0 && zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K");
                        freieFaecher(belegung);
                    } else if (zahlklein == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','G");
                        freieFaecher(belegung);

                    } else if (zahlmittel == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K','G");
                        freieFaecher(belegung);
                    } else if (zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','K");
                        freieFaecher(belegung);
                    }
                } else {
                    String[] belegung = lf.get_fachnummer_ausDB(false);
                    freieFaecher(belegung);


                }
                menge = new String[menge_aktuell.size()];
                for (int i = 0; i < menge_aktuell.size(); i++) {
                    menge[i] = menge_aktuell.get(i);
                }
                model.addColumn("Aktuelle Menge", menge);

            }

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
            parse(id);
            int menge = 0;
            int neueMenge = 0;

            String inhalt = lv.menge_textfeld_einlagern.getText();

            //    lv.label_menge_übrig.setText(inhalt);
            int einzulagern = cv.StringTOint(inhalt);

            System.out.println("zahl  " + inhalt);

            //String mar = (String) (table.getValueAt(table.getSelectedRow(), 0));
            System.out.println("Fachnummer " + fachnummer);

            if (fachnummer.endsWith("K")) {
                menge = zahlklein;

            } else if (fachnummer.endsWith("M")) {

                menge = zahlmittel;

            } else if (fachnummer.endsWith("G")) {

                menge = zahlgross;
            }
            //int zahl = cv.StringTOint(menge);

            //   int menge_uebrig = cv.StringTOint(lv.label_menge_übrig.getText());


            if (einzulagern < menge && einzulagern != 0) {
                //zahl = einzulagern;
                neueMenge = einzulagern;
            } else if (einzulagern == 0) {
                System.out.println("0");
                //  neueMenge = einzulagern;
            } else {
                neueMenge = einzulagern - menge;
            }

            String text = String.valueOf(neueMenge);
            //  int neueMenge = einzulagern - zahl;
            //String text = String.valueOf(neueMenge);
            lv.label_menge_übrig.setText(text);


            Lagerbestandskonto lbk = new Lagerbestandskonto(fachnummer, id, neueMenge, null, null);
            dbs.insert_lagerbestandskonto(lbk);
            dbs.update_lagerfachstamm(fachnummer, true);

            einlagern_vorbereiten(id);

            JOptionPane.showMessageDialog(lv.label_auswahl, "Teil erfolgreich eingelagert", "Teil eingelagert", 2);
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

    public void freieFaecher(String[] belegung) throws SQLException {

        ergebnis = new String[werte.size() + belegung.length];
        for (int j = 0; j < werte.size(); j++) {
            if (werte.get(j) != null) {
                ergebnis[j] = werte.get(j);
            }
            for (int i = 0; i < belegung.length; i++) {
                ergebnis[i + werte.size()] = belegung[i];
            }
        }


        model.addColumn("Freie Fächer", ergebnis);

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
        //Anlegen neuer Controller 
        Pruefen_Controller pr = new Pruefen_Controller();
        cv = new convert();
        //neue Datenbankverbindung
        Datenbankverbindung dbv = new Datenbankverbindung();
        //Arraylists der jeweiligen tabellen
        ArrayList<Lagerfachstamm> fachstamm = new ArrayList<Lagerfachstamm>();
        fachstamm = dbv.resultset_to_lagerfachstamm();
        ArrayList<Teil_Stammdaten> stammdaten = new ArrayList<Teil_Stammdaten>();
        stammdaten = dbv.resultset_to_teil_stammdaten();
        ArrayList<Lagerbestandskonto> bestandsdaten = new ArrayList<Lagerbestandskonto>();
        bestandsdaten = dbv.resultset_to_lagerbestandskontos();

        //Deklaration von variablen
        int menge = cv.StringTOint(m1); //konvertieren der menge zu int
        int pif = 0; //position in dem Lagerfachstammarray 
        int pib = 0;  //position in dem Bestandskontoarray
        int pis = 0; //position in dem Stammdatenarray
        int i = 0;   //Variable zum hochzählen
        boolean eqls = false;//Variable ob Prüfung des Faches erfolgreich war
        boolean chk_lbk = false; //Variable für die Prüfung ob das Fach im bestandskonto von der gleichen id belegt ist
        int freier_platz = 0; //Variable wieviel platz in einem Fach noch frei ist (wird nur genutzt falls das fach bereits
        //von der gleichen id belegt ist)
        String groesse = ""; //Variable zum speichern der größe
        boolean einlagern = false; //variable die festlegt ob das fach eingelagert werden kann

        //suchen der id in der Stammdaten arraylist
        for (int a = 0; a < stammdaten.size(); a++) {
            if (id == stammdaten.get(a).get_Id()) {
                pis = a;
            }
        }

        //Prüfen ob das Fach richtig eingegeben wurde und im array ist
        while (eqls == false) {                                     //solange eqls false ist
            if (i < fachstamm.size()) {                             //und i kleiner als die größe der array list
                if (f1.equals(fachstamm.get(i).get_Fachnummer())) { //wird verglichen ob die fachnummer in dem array vor kommt
                    eqls = true;                                    //wenn ja wird sich der index gemerkt an der stelle wo das fach steht
                    pif = i;                                        //und es wird die eqls variable auf true gesetzt
                    groesse = fachstamm.get(pif).get_groesse();
                    JOptionPane.showMessageDialog(lv.button_manuell_einlagern, "Fach gefunden gefunden"); //wieder entfernen
                } else {
                    i++;                                        //wenn nicht wird i hochgezählt und weitergesucht
                }
            } else {
                JOptionPane.showMessageDialog(lv.button_manuell_einlagern, "Teil nicht vorhanden");
                break;                                          //wurde das fach nicht gefunden, wird abgebrochen
            }
        }
        //Prüfen ob das Fach Frei oder Belegt ist

        if (eqls == true) {                                             //wenn die Fachprüfung erfolgreich war
            if (fachstamm.get(pif).get_Belegung() == true) {            //wird die belegung geprüft, ist das fach schon belegt
                int k = 0;
                while (chk_lbk == false) {                              //wird das Fach in den Bestandsdaten gesucht und die id geprüft
                    if (k < bestandsdaten.size()) {                     //durchlauf durch das Bestandsdatenarray
                        if (f1.equals(bestandsdaten.get(k).get_Fachnummer())) {     // wird die Fachnummer in den bestandsdaten gefunden
                            pib = k; //merken der Position in den bestandsdaten     // wird sich die position im array gemerkt
                            if (bestandsdaten.get(k).get_TeileID() == id) {         // und es wird die id des einzulagernden teils mit dem eingelagerten verglichen
                                chk_lbk = true;                                     // die prüfvariable wird true damit die schleife endet
                                if ("K".equals(groesse)) { // danach wird ein größenvergleich durchgeführt und die freie menge im fach gespeichert
                                    freier_platz = stammdaten.get(pis).get_max_anz_klein() - bestandsdaten.get(k).get_Menge();
                                } else if ("M".equals(groesse)) {
                                    freier_platz = stammdaten.get(pis).get_max_anz_mittel() - bestandsdaten.get(k).get_Menge();
                                } else if ("G".equals(groesse)) {
                                    freier_platz = stammdaten.get(pis).get_max_anz_gross() - bestandsdaten.get(k).get_Menge();
                                }
                            } else {
                                JOptionPane.showMessageDialog(lv.button_manuell_einlagern, "Das Fach ist von einem anderen Teil belegt");
                                break;
                            }//ende bestandsdatenvergleich                          
                        } else {
                            k++;
                        }//ende Fachnummer vergleich
                    }
                }//ende whileschleife

                if (menge <= freier_platz) {        //letzter vergleich bei einem bereits belegtem fach
                    einlagern = true;               //einlagern wird true wenn die einzulagernde menge kleiner gleich dem freien platz ist
                } else {
                    JOptionPane.showMessageDialog(lv.button_manuell_einlagern, "In dem Fach ist nicht mehr genug platz frei");
                }


            } else if (fachstamm.get(pif).get_Belegung() == false) {            //wenn das Fach noch nicht belegt ist 
                                                                                //wird nachgesehen ob die Menge in das Fach passt
                if ("K".equals(groesse)) {
                    if (menge <= stammdaten.get(pis).get_max_anz_klein()) {
                        einlagern = true;
                    } else {
                        JOptionPane.showMessageDialog(lv.button_manuell_einlagern, "Die Menge passt nicht ins fach");
                    }
                } else if ("M".equals(groesse)) {
                    if (menge <= stammdaten.get(pis).get_max_anz_mittel()) {
                        einlagern = true;
                    } else {
                        JOptionPane.showMessageDialog(lv.button_manuell_einlagern, "Die Menge passt nicht ins fach");
                    }
                } else if ("G".equals(groesse)) {
                    if (menge <= stammdaten.get(pis).get_max_anz_gross()) {
                        einlagern = true;
                    } else {
                        JOptionPane.showMessageDialog(lv.button_manuell_einlagern, "Die Menge passt nicht ins fach");
                    }
                }

            }
            if(einlagern=true)
            {
                
            }
            
        }

    }
}
