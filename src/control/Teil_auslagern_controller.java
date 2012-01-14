/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.sql.SQLException;
import model.Select_Lagerbestandskonto;
import model.Lagerbestandskonto;
import model.DB_schreiben;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Select_Stammdaten;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Herold
 */
public class Teil_auslagern_controller {

    int id;
    String[] fachnummern;           //enthaelt alle Fachnummern, welche die id enthalten
    String[] aktuelle_menge;        //enthaelt die Mengen der Fachnr. aus fachnummern[]
    String[] anschaffungsgrund;     //enthaelt Anschaffungsgrund der Fachnr, aus fachnummern[]
    int auszulagernde_menge;        //Menge, die Anwender in GUI zum Auslagern eintraegt

    /**
     * Fuehrt abfragen in der Datenbank zur uebergebenen ID durch. Fuellt das
     * fachnummern[], das aktuelle_menge[] und das anschaffungsgrund[]
     *
     * @param idt = uebergebene ID
     * @throws SQLException
     */
    public void auslagern_vorbereitung(int idt) throws SQLException {

        id = idt;

        Select_Lagerbestandskonto sl = new Select_Lagerbestandskonto();
        Select_Lagerbestandskonto sl2 = new Select_Lagerbestandskonto();
        Select_Lagerbestandskonto sl3 = new Select_Lagerbestandskonto();

        fachnummern = sl.get_Fachnummer_ausDB(id);

        aktuelle_menge = new String[fachnummern.length];

        for (int i = 0; i < fachnummern.length; i++) {


            aktuelle_menge = sl2.get_Menge_ausDB(fachnummern[i]);

            anschaffungsgrund = sl3.get_Anschaffungsgrund_ausDB(fachnummern[i]);

        }
    }

    /**
     * Freigeben, der Faecher, in denen die Menge auf 0 fiel.
     *
     * Rueckgabewerte, je nach Laenge des fachnummern[]
     *
     * return 0 - fachnummer[].length = 1 (gleich 1, letztes Fach, welches ID
     * eingelagert hat. -> User Option:Teil loeschen) return 1 -
     * fachnummer[].length > 1 (größer 1, bedeutet, noch min. ein Fach
     * vorhanden) return 2 - fachnummer[].length < 1 (kleiner 1, kein Fach
     * vorhanden)
     *

     *
     * @param id = uebergene ID
     * @param laenge = uebergebene Laenge des fachnummer[]
     * @param fnr = Fachnummer
     * @throws ClassNotFoundException
     */
    public int fachfreigeben(int id, int laenge, String fnr) throws ClassNotFoundException {

        DB_schreiben dbs = new DB_schreiben();

        if (laenge == 1) {

            dbs.delete_lagerbestandskonto(id, fnr);
            dbs.update_lagerfachstamm(fnr, false);
            return 0;


        } else if (laenge > 1) {

            dbs.delete_lagerbestandskonto(id, fnr);
            dbs.update_lagerfachstamm(fnr, false);
            return 1;
        }

        return 2;
    }

    /**
     * Gibt Array mit den aktuellen Fachnummern zurück
     *
     * @return
     */
    public String[] getfachnummern() {
        return fachnummern;
    }

    /**
     * Gibt die aktuelle ID zurück
     *
     * @return String[] fachnummern
     */
    public int getID() {
        return id;
    }

    /**
     * Gibt Array mit den aktuellen Mengen der Faecher zurueck
     *
     * @return String[] aktuelle_menge
     */
    public String[] getaktuelle_menge() {
        return aktuelle_menge;
    }

    /**
     * Gibt Array mit den aktuellen Anschaffungsgruenden der Teile zurueck
     *
     * @return String[] anschaffungsgrund
     */
    public String[] getanschaffungsgrund() {

        return anschaffungsgrund;
    }

    /**
     *
     * Es wird ein Objekt vom Typ Lagerbestandskonto aus den uebergebenen Werten
     * erstellt.
     *
     * @param fnr = Fachnummer
     * @param idt = ID
     * @param neue_menge = Menge
     * @param ansgrund = Anschaffungsgrund
     * @return lbk (Laberbestandskonto - Objekt)
     */
    public Lagerbestandskonto erstelle_lbk(String fnr, int idt, int neue_menge, String ansgrund) {

        Lagerbestandskonto lbk = new Lagerbestandskonto(fnr, idt, neue_menge, ansgrund, null);

        return lbk;

    }

    /**
     *
     * Ein uebergebenes Lagerbestandskonto wird in die SQLite Datenbank
     * geschrieben.
     *
     * @param lbk - Lagerbestandskonto- Objekt
     * @throws ClassNotFoundException
     */
    public void auslagern_durchfuehrung(Lagerbestandskonto lbk) throws ClassNotFoundException {

        DB_schreiben dbs = new DB_schreiben();

        dbs.update_lagerbestand(lbk);
    }

    /**
     *
     * Die statische Methode fuehrt beim Ausloesen des
     * "auslager_ausfuehren_button" das Auslagern durch und aktualisiert danach
     * die jTable mit den neuen Werten.
     *
     *
     * @param ul - uebergebenes Übersicht_Lagerverwaltungsobjekt
     */
    public static void auslagern_ausfuehren_button(Übersicht_Lagerverwaltung ul) {


        ul.pr = new Pruefen_Controller();           // neuen Pruefen_Controller anlegen

        String strtemp = ul.auslagerTextfield_fnr.getText();        //Fachnummer ermitteln

        ul.cv = new convert();  //neuen int/String Konverter anlegen

        int inttemp = ul.cv.StringTOint(ul.auslagerTextfield_mengeauslagern.getText()); //auszulagernde Menge von String in int wandeln und in inttemp schreiben

        int fachnrstelle = ul.pr.fachnummerkorrekt(ul.ta.getfachnummern(), strtemp); //Pruefung, ob Fachnummer aus strtemp in fachnummern[] enthalten ist und Position in fachnrstelle schreiben

        if (ul.pr.fachnummerkorrekt(ul.ta.getfachnummern(), strtemp) != 99999) {        //nochmalige Prufung, der Fachnr. auf Korrektheit

            if (ul.pr.auszulagernde_menge_pruefen(ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]), inttemp) == 3) { //Pruefen der auszulagernden Menge, Vergleich uebergebener Meenge des Users mit Menge im aktuelle_menge[] an der Position der zugehoerigen Fachnummer; ==3: neue Menge < als alte Menge

                int neumenge = ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]) - inttemp; //neue Menge in neumenge schreiben: eingelagerte Menge - eingetragene Menge des Users

                try {
                    //Abfangen SQL Exception
                    //durchfuehren des Auslagerns(erstellen und uebergeben Lagerbestanskonto mit aktuellen Werten)

                    ul.ta.auslagern_durchfuehrung(ul.ta.erstelle_lbk(ul.ta.getfachnummern()[fachnrstelle], ul.ta.getID(), neumenge, ul.ta.getanschaffungsgrund()[fachnrstelle]));

                    //aktuelle Werte der eingelagerten Fachnummern ermitteln
                    //jTable neu fuellen und anzeigen
                    int tmp = ul.ta.getID();

                    ul.ta.auslagern_vorbereitung(tmp);

                    DefaultTableModel newmodel = new DefaultTableModel();

                    newmodel.addColumn("Fachnummer", ul.ta.getfachnummern());
                    newmodel.addColumn("Menge", ul.ta.getaktuelle_menge());

                    ul.auslager_jtable.setModel(newmodel);

                    //Anzeige, wieviele Teile ausgelagert wurden
                    ul.auslagerLabel_Anzeige.setText("Es wurde(n) " + ul.auslagerTextfield_mengeauslagern.getText() + " Teil(e) ausgelagert.");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (ul.pr.auszulagernde_menge_pruefen(ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]), inttemp) == 2) { // ==2: neue Menge==alter Menge -> Fach freigeben nach auslagern

                int neumenge = ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]) - inttemp;  //neue Menge ermitteln

                try {
                    //SQL Exception abfangen
                    //Auslagern durchfuehren
                    ul.ta.auslagern_durchfuehrung(ul.ta.erstelle_lbk(ul.ta.getfachnummern()[fachnrstelle], ul.ta.getID(), neumenge, ul.ta.getanschaffungsgrund()[fachnrstelle]));

                    if (ul.ta.fachfreigeben(ul.ta.getID(), ul.ta.getfachnummern().length, ul.ta.getfachnummern()[fachnrstelle]) == 1) { //noch min. 1 Fach mit diesem Teil vorhanden, nur Fach freigeben

                        //neue Werte ermitteln und jTable akutalisieren
                        int tmp = ul.ta.getID();


                        ul.ta.auslagern_vorbereitung(tmp);

                        DefaultTableModel newmodel = new DefaultTableModel();

                        newmodel.addColumn("Fachnummer", ul.ta.getfachnummern());
                        newmodel.addColumn("Menge", ul.ta.getaktuelle_menge());

                        ul.auslager_jtable.setModel(newmodel);

                        ul.auslagerLabel_Anzeige.setText("Es wurde(n) " + ul.auslagerTextfield_mengeauslagern.getText() + " Teil(e) ausgelagert.");

                    } else if (ul.ta.fachfreigeben(ul.ta.getID(), ul.ta.getfachnummern().length, ul.ta.getfachnummern()[fachnrstelle]) == 0) { //kein Fach mehr mit diesem Teil vorhanden -> Fach freigeben& User Teil loeschen anbieten

                        //neue Werte ermitteln und jTable akutalisieren
                        int tmp = ul.ta.getID();


                        ul.ta.auslagern_vorbereitung(tmp);

                        DefaultTableModel newmodel = new DefaultTableModel();

                        newmodel.addColumn("Fachnummer", ul.ta.getfachnummern());
                        newmodel.addColumn("Menge", ul.ta.getaktuelle_menge());

                        ul.auslager_jtable.setModel(newmodel);

                        ul.auslagerLabel_Anzeige.setText("Es wurde(n) " + ul.auslagerTextfield_mengeauslagern.getText() + " Teil(e) ausgelagert.");

                        int reply = JOptionPane.showConfirmDialog(ul.auslagern_AusfuehrenButton, "Möchten Sie das Teil löschen?", "Meldung", JOptionPane.YES_NO_OPTION);

                        if (reply == JOptionPane.YES_OPTION) {
                            ul.entfernenIDTextfield.setText(ul.ta.getID() + "");

                        }


                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if (ul.pr.auszulagernde_menge_pruefen(ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]), inttemp) == 1) { //Pruefung Menge, neue Menge> alter Menge -> Hinweis an User
                JOptionPane.showMessageDialog(ul.auslagern_BestaetigenidButton, "Eingetragende Menge zu groß.");
            }


        }


    }

    /**
     *
     * Die statische Methode zeigt alle belegten Faecher der gewaehlten ID mit
     * Mengen in der jTable an, wenn "auslagern_bestaetigen_button" oder
     * "auslager_textfield" bestaetigt wurde.
     *
     *
     * @param ul - uebergebenes Übersicht_Lagerverwaltung- Objekt
     */
    public static void auslagern_bestaetigen_button(Übersicht_Lagerverwaltung ul) {


        ul.pr = new Pruefen_Controller(); //Pruefen Controller anlegen

        int id = Integer.parseInt(ul.auslagerTextfield_ID.getText()); //ID aus Auslagernfeld uebergeben

        if (ul.pr.pruefe_id(id) == true) {  //ID auf Korrektheit pruefen
            try { //SQL Exception abfangen
                ul.ta.auslagern_vorbereitung(id);  //mengen, fachnummern und anschaffungsgrund Array fuellen

                ul.sst = new Select_Stammdaten(); //neues Select_Stammdaten- Objekt erstellen
                ul.auslager_bez_ausgabeLabel.setText(ul.sst.get_Bezeichnung_ausDB(id)); //Bezeichnung zu ID ermitteln

                ul.sst = new Select_Stammdaten(); //ueberschreiben des Select_Stammdaten- Objekts fuer neue Abfrage
                ul.ausl_BemerkungTextArea.setText(ul.sst.get_Bemerkung_ausDB(id)); //Bemerkung zu ID ermitteln

                if (ul.ta.getfachnummern().length == 0) {  //Preufung Laenge fachnummern[]: wenn 0 kein Teil eingelagert -> Teil loeschen anbieten
                    int reply = JOptionPane.showConfirmDialog(ul.auslagern_AusfuehrenButton, "Teil nicht mehr eingelagert. Möchten Sie das Teil löschen?", "Meldung", JOptionPane.YES_NO_OPTION);

                    if (reply == JOptionPane.YES_OPTION) {
                        ul.entfernenIDTextfield.setText(ul.ta.getID() + "");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            //Anzeige der jTable mit aktuellen Werten
            DefaultTableModel newmodel = new DefaultTableModel();

            newmodel.addColumn("Fachnummer", ul.ta.getfachnummern());
            newmodel.addColumn("Menge", ul.ta.getaktuelle_menge());

            ul.auslager_jtable.setModel(newmodel);
        }
    }
}