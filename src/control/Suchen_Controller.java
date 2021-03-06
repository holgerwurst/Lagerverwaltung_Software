/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import model.*;
import view.Teil_Suchen;

/**
 *
 * @author felix
 */
public class Suchen_Controller {

    Select_Stammdaten select_stammdaten = null;
    Select_Lagerfachstamm select_lagerfachstamm = null;
    Select_Lagerbestandskonto select_lagerbestandskonto = null;
    public String[] table_column_names_teilestamm = {
        "Teile ID", "Teiletyp", "Zeichnungsnummer", "Preis", "Bezeichnung", "Baugruppe", "Materialgruppe", "Bemerkung", "Maximale Anzahl Klein", "Maximale Anzahl Mittel", "Maximale Anzahl Groß"
    };
    public String[] table_column_names_lagerbestandskonto = {
        "Fachnummer", "Teile ID", "Menge", "Anschaffungsgrund", "Haltbarkeitsdatum"
    };

    public String[] tabble_column_names_lagerfachstamm = {
        "Fachnummer", "Lagerort", "Hochregalnummer", "Zeile", "Spalte", "Größe", "Belegt"
    
    };
    /**
     * ruft ganze Teilestammtabelle aus Datenbank ab
     *
     * @return Eine ArrayListe aller Teilestammdaten aus der Datenbank
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public ArrayList<Teil_Stammdaten> alle_teile_ausgeben() throws ClassNotFoundException, Exception {
        if (select_stammdaten == null) {
            select_stammdaten = new Select_Stammdaten();
        }
        ArrayList<Teil_Stammdaten> ganze_tabelle = select_stammdaten.get_ganze_tabelle_teilestammdaten();
        System.out.println(ganze_tabelle);
        return ganze_tabelle;
    }

    public ArrayList<Teil_Stammdaten> teile_suchen(String[][] suchwerte) throws ClassNotFoundException, Exception {
        if (select_stammdaten == null) {
            select_stammdaten = new Select_Stammdaten();
        }
        return select_stammdaten.teile_suchen(suchwerte);
    }

    public ArrayList<Lagerbestandskonto> teile_im_fach_suchen(String[][] suchwerte) throws Exception {
        if (select_lagerbestandskonto == null) {
            select_lagerbestandskonto = new Select_Lagerbestandskonto();
        }
        return select_lagerbestandskonto.teile_im_fach_suchen(suchwerte);
    }

    public ArrayList<Lagerfachstamm> fach_suchen(String[][] suchwerte) throws ClassNotFoundException, Exception {
        if (select_lagerfachstamm == null) {
            select_lagerfachstamm = new Select_Lagerfachstamm();
        }
        return select_lagerfachstamm.fach_suchen(suchwerte);
    }
}
