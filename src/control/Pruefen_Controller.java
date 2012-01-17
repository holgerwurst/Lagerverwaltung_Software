/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Datenbankverbindung;
import model.Select_Stammdaten;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Matthias
 * Hier wird geprüft, ob die ID vorhanden ist. Falls ja wird true als return-Wert zurückgegeben. Ansonsten false.
 */
public class Pruefen_Controller {

    private Select_Stammdaten std = new Select_Stammdaten();
    private Übersicht_Lagerverwaltung lv = new Übersicht_Lagerverwaltung();
    private convert cv = new convert();

    public boolean pruefe_id(int id) {


        try {

            String vergleich = std.get_ID_ausDB(id);

            if (vergleich.isEmpty()) {

                JOptionPane.showMessageDialog(lv.auslagern_BestaetigenidButton, "Teil nicht vorhanden");
                lv.label_auswahl.setText("");

                return false;

            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }

        return true;


    }
/**
 * Hier wird geprüft, ob die Bezeichnung vorhanden ist. Falls ja wird true als return wert zurückgegeben. Ansonsten false.
 * @param bezeichnung
 * @return 
 */
    public Boolean pruefe_bezeichnung(String bezeichnung) {
        try {
            String[] vergleich = std.get_ID_array_ausDB(bezeichnung);

            if (vergleich.length == 0) {

                JOptionPane.showMessageDialog(lv.auslagern_BestaetigenidButton, "Teil nicht vorhanden");
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }

    /**
     * Auszulagernde Menge wird geprüft
     *
     * return 0 - Menge kleiner 0;
     * return 1 - Menge größer als alte Menge;
     * return 2 - Menge entspricht alter Menge - Fach muss nach auslagern freigeben werden;
     * return 3 - Menge ok;
     *
     * @param mengeaktuell - Megne, die aktuell im Fach eingelagert ist
     * @param mengeneu - neue vom User eingebener Wert
     * @return
     */
    public int auszulagernde_menge_pruefen(int mengeaktuell, int mengeneu) {

        if (mengeneu <= 0) {
            return 0;
        }

        if (mengeaktuell < mengeneu) {

            return 1;
        }

        if (mengeaktuell == mengeneu) {

            return 2;
        }
        return 3;
    }

    /**
     * Pruefung - ist die Fachnummer fnr im String[] fnrs enthalten? Wenn ja,
     * wird die Position i im Array zurückgegeben. Wenn nein, wird 99999
     * zurückgegeben.
     *
     * @param fnrs - fachnummern[]
     * @param fnr - Fachnummer String
     * @return
     */
    public int fachnummerkorrekt(String[] fnrs, String fnr) {

        for (int i = 0; i < fnrs.length; i++) {

            if (fnr.equals(fnrs[i])) {

                return i;
            }
        }
        return 99999;
    }

    /**
     * Prüfmethode zur logisch richtigen angabe Eines Teils zur belegung der
     * Boxgrößen
     *
     * @param maks klein
     * @param mams mittel
     * @param mags groß
     * @return 0: Alles ok, 1:alle felder sind 0, 2: es passen weniger Teile in
     * große boxen als in kleinere
     */
    public int boxlogo(String maks, String mams, String mags) {
        int mak = cv.StringTOint(maks);
        int mam = cv.StringTOint(mams);
        int mag = cv.StringTOint(mags);
        if (mak > mam) {
            return 2;
        } else {
            if (mam > mag) {
                return 2;
            }

        }


        if (mak == 0 && mam == 0 && mag == 0) {
            return 1;
        } else {
            return 0;
        }
    }
    /*
     * public static void main(String[] args) throws Exception{
     * Pruefen_Controller co = new Pruefen_Controller(); co.pruefe_id(2); }
     */
}
