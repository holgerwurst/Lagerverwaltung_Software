/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.awt.Color;
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
              fach = lagerbes.get_Fachnummer_ausDB(id);
            cv = new convert();

            System.out.println("fach  " + fach.length);

            if (fach.length == 0) {
                erste_einlagerung(id);
            } else {
                parse(id);

                for (int i = 0; i < fach.length; i++) {

                    System.out.println("inhalt  " + fach[i]);

                    if (fach[i].endsWith("K")) {
                        int menge = cv.StringTOint(lagerbes.get_Menge_aktuell_ausDB(fach[i]));
                        if (menge < zahlklein) {

                            String mengeS = String.valueOf(menge);

                            menge_aktuell.add(mengeS);
                            werte.add(fach[i]);
                            System.out.println("K  " + fach[i]);
                        }
                    } else if (fach[i].endsWith("M")) {

                        int menge = cv.StringTOint(lagerbes.get_Menge_aktuell_ausDB(fach[i]));
                        if (menge < zahlmittel) {

                            String mengeS = String.valueOf(menge);
                            menge_aktuell.add(mengeS);
                            werte.add(fach[i]);
                            System.out.println("M  " + fach[i]);
                        }
                    } else if (fach[i].endsWith("G")) {

                        int menge = cv.StringTOint(lagerbes.get_Menge_aktuell_ausDB(fach[i]));
                        if (menge < zahlgross) {

                            String mengeS = String.valueOf(menge);
                            menge_aktuell.add(mengeS);
                            werte.add(fach[i]);
                            System.out.println("G  " + fach[i]);
                        }
                    }

                }


                if (zahlmittel == 0 || zahlklein == 0 || zahlgross == 0) {


                    if (zahlklein == 0 && zahlmittel == 0) {

                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "G");
                        freieFaecher(belegung);
                        lv.label_mindestgroesse2.setText("Einzige Fachgröße:");
                        lv.label_anzeige_mindestgroesse2.setText("Große Fächer");

                    } else if (zahlklein == 0 && zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M");
                        freieFaecher(belegung);
                        lv.label_mindestgroesse2.setText("Einzige Fachgröße:");
                        lv.label_anzeige_mindestgroesse2.setText("Mittlere Fächer");

                    } else if (zahlmittel == 0 && zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K");
                        freieFaecher(belegung);
                        lv.label_mindestgroesse2.setText("Einzige Fachgröße:");
                        lv.label_anzeige_mindestgroesse2.setText("Kleine Fächer");

                    } else if (zahlklein == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','G");
                        freieFaecher(belegung);
                        lv.label_anzeige_mindestgroesse2.setText("Mittlere Fächer");
                    } else if (zahlmittel == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K','G");
                        freieFaecher(belegung);
                        lv.label_mindestgroesse2.setText("Fachgrößen:");
                        lv.label_anzeige_mindestgroesse2.setText("Kleine und große Fächer");
                    } else if (zahlgross == 0) {
                        String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','K");
                        freieFaecher(belegung);
                        lv.label_mindestgroesse2.setText("Fachgrößen:");
                        lv.label_anzeige_mindestgroesse2.setText("Kleine und mittlere Fächer");
                    }
                } else {
                    String[] belegung = lf.get_fachnummer_ausDB(false);
                    freieFaecher(belegung);
                    lv.label_anzeige_mindestgroesse2.setText("Kleine Fächer");
                }
                lv.label_mindestgroesse2.setVisible(true);
                lv.label_anzeige_mindestgroesse2.setVisible(true);


                menge = new String[menge_aktuell.size()];
                for (int i = 0; i < menge_aktuell.size(); i++) {
                    menge[i] = menge_aktuell.get(i);
                    System.out.println("Menge " + menge[i]);
                }


                lv.label_klein_einlagern.setText(maxklein);
                lv.label_mittel_einlagern.setText(maxmittel);
                lv.label_gross_einlagern.setText(maxgross);

                model.addColumn("Aktuelle Menge", menge);

            }

            table.setModel(model);


        } catch (SQLException e) {
            System.out.println(e);
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
                lv.label_anzeige_mindestgroesse2.setText("Keine passenden Fächer");

                //   System.out.println("Für dieses Teil gibt es kein passendes Fach");
            } else if (zahlklein == 0 && zahlmittel == 0) {

                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "G");
                fach(id, belegung);
                lv.label_mindestgroesse2.setText("Einzige Fachgröße:");
                lv.label_anzeige_mindestgroesse2.setText("Große Fächer");
            } else if (zahlklein == 0 && zahlgross == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M");
                fach(id, belegung);
                lv.label_mindestgroesse2.setText("Einzige Fachgröße:");
                lv.label_anzeige_mindestgroesse2.setText("Mittlere Fächer");
            } else if (zahlmittel == 0 && zahlgross == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K");
                fach(id, belegung);
                lv.label_mindestgroesse2.setText("Einzige Fachgröße:");
                lv.label_anzeige_mindestgroesse2.setText("Kleine Fächer");
            } else if (zahlklein == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','G");
                fach(id, belegung);
                lv.label_anzeige_mindestgroesse2.setText("Mittlere Fächer");
            } else if (zahlmittel == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "K','G");
                fach(id, belegung);
                lv.label_mindestgroesse2.setText("Fachgrößen:");
                lv.label_anzeige_mindestgroesse2.setText("Kleine und große Fächer");
            } else if (zahlgross == 0) {
                String[] belegung = lf.get_fachnummer_2groessen_ausDB(false, "M','K");
                fach(id, belegung);
                lv.label_mindestgroesse2.setText("Fachgrößen:");
                lv.label_anzeige_mindestgroesse2.setText("Kleine und mittlere Fächer");
            }

        } else {
            String[] belegung = lf.get_fachnummer_ausDB(false);
            fach(id, belegung);
            lv.label_anzeige_mindestgroesse2.setText("Kleine Fächer");
        }
        lv.label_mindestgroesse2.setVisible(true);
        lv.label_anzeige_mindestgroesse2.setVisible(true);

        lv.label_auswahl.setText("Dieses Teil befindet sich noch nicht im Lager.");
        lv.label_auswahl.setForeground(Color.BLUE);
        lv.label_auswahl.setVisible(true);




        for (int i = 0; i < 1; i++) {

            maxarrayklein[i] = maxklein;
            maxarraymittel[i] = maxmittel;
            maxarraygross[i] = maxgross;
        }

        lv.label_klein_einlagern.setText(maxklein);
        lv.label_mittel_einlagern.setText(maxmittel);
        lv.label_gross_einlagern.setText(maxgross);

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
        }
        for (int i = 0; i < belegung.length; i++) {
            ergebnis[i + werte.size()] = belegung[i];
            //         System.out.println("Belegung  " +belegung[i]);      
        }


        model.addColumn("Fächer mit genügend Kapazität", ergebnis);
        lv.label_auswahl.setText("Dieses Teil ist bereits im Lager vorhanden.");
        lv.label_auswahl.setForeground(Color.BLUE);
        lv.label_auswahl.setVisible(true);

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

    public void einlagern(String fachnummer, int id) {
        try {
            lagerbes = new Select_Lagerbestandskonto();
            st = new Select_Stammdaten();
            cv = new convert();

            parse(id);
            int menge_eingelagert = cv.StringTOint(lagerbes.get_Menge_aktuell_ausDB(fachnummer));
            int max_menge = 0;
            int neueMenge = 0;

            // String inhalt = lv.menge_textfeld_einlagern.getText();

            //    lv.label_menge_übrig.setText(inhalt);
            int einzulagern = cv.StringTOint(lv.label_menge_übrig.getText());

            // System.out.println("zahl  " + inhalt);

            //String mar = (String) (table.getValueAt(table.getSelectedRow(), 0));
            System.out.println("Fachnummer " + fachnummer);

            if (fachnummer.endsWith("K")) {
                max_menge = zahlklein;

            } else if (fachnummer.endsWith("M")) {

                max_menge = zahlmittel;

            } else if (fachnummer.endsWith("G")) {

                max_menge = zahlgross;
            }

//if(menge_eingelagert!=0){
            String text = "";
       
                if (einzulagern <= max_menge) { 

                    if (menge_eingelagert != 0) {
                        if (einzulagern <= max_menge - menge_eingelagert) {
                            neueMenge = einzulagern + menge_eingelagert;
                            text = String.valueOf(einzulagern - einzulagern);
                            Lagerbestandskonto lbk = new Lagerbestandskonto(fachnummer, id, neueMenge, null, null);
                            dbs.delete_lagerbestandskonto(id, fachnummer);
                            dbs.insert_lagerbestandskonto(lbk);
                            if (einzulagern == max_menge - menge_eingelagert) {
                                lv.TextArea_einlagern.append("Das Teil mit der ID " + id + " wurde erfolgreich in das Fach " + fachnummer + " mit der Menge "+neueMenge+" eingelagert. Es steht nun nicht mehr zur Verfügung\n");
                            } else {
                                lv.TextArea_einlagern.append("Das Teil mit der ID " + id + " wurde erfolgreich in das Fach " + fachnummer + " mit der Menge "+neueMenge+" eingelagert.\n");
                            }
                            // lv.TextArea_einlagern.setVisible(true);
                        } else {
                            neueMenge = max_menge;
                            text = String.valueOf(einzulagern - (max_menge - menge_eingelagert));
                            Lagerbestandskonto lbk = new Lagerbestandskonto(fachnummer, id, neueMenge, null, null);
                            dbs.delete_lagerbestandskonto(id, fachnummer);
                            dbs.insert_lagerbestandskonto(lbk);
                            dbs.update_lagerbestand(lbk);
                            lv.TextArea_einlagern.append("Das Teil mit der ID " + id + " wurde erfolgreich in das Fach " + fachnummer + " mit der Menge "+neueMenge+" eingelagert.  Es steht nun nicht mehr zur Verfügung\n");
                        }

                    } else {
                        neueMenge = einzulagern;
                        Lagerbestandskonto lbk = new Lagerbestandskonto(fachnummer, id, neueMenge, null, null);
                        text = String.valueOf(einzulagern - neueMenge);
                        dbs.insert_lagerbestandskonto(lbk);
                        dbs.update_lagerfachstamm(fachnummer, true);
                        System.out.println("geringer");
                        if (einzulagern == max_menge - menge_eingelagert) {
                                lv.TextArea_einlagern.append("Das Teil mit der ID " + id + " wurde erfolgreich in das Fach " + fachnummer + " mit der Menge "+neueMenge+" eingelagert. Es steht nun nicht mehr zur Verfügung\n");
                            } else {
                                lv.TextArea_einlagern.append("Das Teil mit der ID " + id + " wurde erfolgreich in das Fach " + fachnummer + " mit der Menge "+neueMenge+" eingelagert.\n");
                            }
                    }
                } else {
                    neueMenge = max_menge; 
                    text = String.valueOf(einzulagern - (max_menge - menge_eingelagert));
                    Lagerbestandskonto lbk = new Lagerbestandskonto(fachnummer, id, neueMenge, null, null);
                    dbs.delete_lagerbestandskonto(id, fachnummer);
                    dbs.insert_lagerbestandskonto(lbk);
                    dbs.update_lagerfachstamm(fachnummer, true);
                    System.out.println("hier");
                    lv.TextArea_einlagern.append("Das Teil mit der ID " + id + " wurde erfolgreich in das Fach " + fachnummer + " mit der Menge "+neueMenge+" eingelagert. Es steht nun nicht mehr zur Verfügung\n");

                }
               // JOptionPane.showMessageDialog(lv.label_auswahl, "Teil erfolgreich eingelagert", "Teil eingelagert", 2);
               
                lv.label_menge_übrig.setVisible(true);
                lv.label_menge_übrig.setText(text);
             
            } 
            //     }
        catch (Exception e) {
        }

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
    public String manuell_einlagern(String f1, int m1, int id, String anschaffungsgrund) throws ClassNotFoundException, SQLException, Exception {
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

        Lagerbestandskonto einlagern_bestand = new Lagerbestandskonto(); //neues objekt vom typ Lagerbestandskonto zum einlagern
        DB_schreiben writer = new DB_schreiben();

        //Deklaration von variablen
        int menge = m1; //konvertieren der menge zu int
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
        boolean einlagern_neu = false;
        boolean einlagern_update = false;


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
                } else {
                    i++;                                        //wenn nicht wird i hochgezählt und weitergesucht
                }
            } else {
                return "Das Fach " + f1 + " ist nicht vorhanden.\n";   //wurde das fach nicht gefunden, wird abgebrochen und ein String zurückgegeben
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
                                return "Das Fach " + f1 + " ist von einem anderen Teil belegt.\n";

                            }//ende bestandsdatenvergleich                          
                        } else {
                            k++;
                        }//ende Fachnummer vergleich
                    }
                }//ende whileschleife


                if (menge <= freier_platz) {        //letzter vergleich bei einem bereits belegtem fach
                    einlagern = true;               //einlagern wird true wenn die einzulagernde menge kleiner gleich dem freien platz ist
                    einlagern_update = true;
                } else {
                    return "In dem Fach " + f1 + " ist nicht genug platz frei. Sie wollen " + menge + " einlagern. Die Kapazität reicht aber nur für " + freier_platz + ".\n";
                }


            } else if (fachstamm.get(pif).get_Belegung() == false) {            //wenn das Fach noch nicht belegt ist 
                //wird nachgesehen ob die Menge in das Fach passt
                if ("K".equals(groesse)) {
                    if (menge <= stammdaten.get(pis).get_max_anz_klein()) {
                        einlagern = true;
                        einlagern_neu = true;
                    } else {
                        return "Die Menge passt nicht in das Fach die maximale Menge für ein Fach der Größe " + groesse + " ist " + stammdaten.get(pis).get_max_anz_klein() + ".\n";
                    }
                } else if ("M".equals(groesse)) {
                    if (menge <= stammdaten.get(pis).get_max_anz_mittel()) {
                        einlagern = true;
                        einlagern_neu = true;
                    } else {
                        return "Die Menge passt nicht in das Fach die maximale Menge für ein Fach der Größe " + groesse + " ist " + stammdaten.get(pis).get_max_anz_mittel() + ".\n";
                    }
                } else if ("G".equals(groesse)) {
                    if (menge <= stammdaten.get(pis).get_max_anz_gross()) {
                        einlagern = true;
                        einlagern_neu = true;
                    } else {
                        return "Die Menge passt nicht in das Fach die maximale Menge für ein Fach der Größe " + groesse + " ist " + stammdaten.get(pis).get_max_anz_gross() + ".\n";
                    }
                }

            }
            if (einlagern == true) {
                if (einlagern_neu == true) {
                    einlagern_bestand.set_Fachnummer(fachstamm.get(pif).get_Fachnummer());
                    einlagern_bestand.set_Anschaffungsgrund(anschaffungsgrund);
                    einlagern_bestand.set_id(id);
                    einlagern_bestand.set_Menge(menge);
                    einlagern_bestand.set_Haltbarkeitsdatum(null);
                    writer.insert_lagerbestandskonto(einlagern_bestand);
                    writer.update_lagerfachstamm(f1, true);
                } else if (einlagern_update == true) {
                    einlagern_bestand.set_Fachnummer(bestandsdaten.get(pib).get_Fachnummer());
                    einlagern_bestand.set_Anschaffungsgrund(bestandsdaten.get(pib).get_Anschaffungsgrund());
                    einlagern_bestand.set_id(bestandsdaten.get(pib).get_TeileID());
                    int neue_menge = bestandsdaten.get(pib).get_Menge() + menge;
                    einlagern_bestand.set_Menge(neue_menge);
                    einlagern_bestand.set_Haltbarkeitsdatum(bestandsdaten.get(pib).get_Haltbarkeitsdatum());
                    writer.update_lagerbestand(einlagern_bestand);
                }

            }//ende einlagern

        }//ende 1. if

        return "OK";
    }

    public manuell_einlagern_strings[] einlagern_manuell_check(manuell_einlagern_strings[] mes, int id) throws Exception {
        for (int i = 0; i < mes.length; i++) {
            //String returnstring;
            String mer;
            if (!"".equals(mes[i].get_fachnummer())) {      //Prüfung ob Fachnummer nicht leer ist
                if (mes[i].get_menge() > 0) {

                    mer = manuell_einlagern(mes[i].get_fachnummer(), mes[i].get_menge(), id, mes[i].get_anschaffungsgrund());
                    if (!"OK".equals(mer)) {
                        mes[i].set_fnr_gui(i + 1);
                        mes[i].set_fehler(2);  //2 = richtiger Fehler
                        mes[i].set_fehler_text(mer);
                    } else {
                        mes[i].set_fnr_gui(i + 1);
                        mes[i].set_fehler(1); //1 = kein Fehler
                    }
                } else {
                    mes[i].set_fnr_gui(i + 1);
                    mes[i].set_fehler(2);  //2 = richtiger Fehler
                    mes[i].set_fehler_text("Die Menge muss mindestens 1 sein. \n");
                }
            } else {
                mes[i].set_fnr_gui(i + 1);
                mes[i].set_fehler(0);  // 0 = Fach ist nicht ausgefüllt
            }
        }

        //return fehlerrueckgabestring;
        return mes;

    }

    public String fehlerausgabe(manuell_einlagern_strings[] mes) {
        String fehlerliste = "";
        for (int i = 0; i < mes.length; i++) {
            if (mes[i].get_fehler() == 2) {
                fehlerliste = fehlerliste + "Fach " + mes[i].get_fnr_gui() + ": " + mes[i].get_fehler_text();
            }
        }

        return fehlerliste;
    }
}
