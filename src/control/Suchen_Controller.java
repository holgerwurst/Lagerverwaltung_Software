/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import model.Select_Lagerfachstamm;
import model.Select_Stammdaten;
import model.Teil_Stammdaten;
import model.TeileTypET;
import view.Teil_Suchen;

/**
 *
 * @author felix
 */
public class Suchen_Controller {

    Select_Stammdaten select_stammdaten = null;
    Select_Lagerfachstamm select_lagerfachstamm = null;
    public String[] table_column_names = {
        "Teile ID", "Teiletyp", "Zeichnungsnummer"
        , "Preis", "Bezeichnung", "Baugruppe", "Materialgruppe", "Bemerkung"
        , "Maximale Anzahl Klein", "Maximale Anzahl Mittel"
        , "Maximale Anzahl Groß"
    };
    
    /**
     * ruft ganze Teilestammtabelle aus Datenbank ab
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

    public ArrayList<Teil_Stammdaten> teile_suchen(String[][] spaltenwerte) throws ClassNotFoundException, Exception {
        if (select_stammdaten == null) {
            select_stammdaten = new Select_Stammdaten();
        }
        return select_stammdaten.teile_suchen(spaltenwerte);
    }

    public void fach_suchen() {
        if (select_lagerfachstamm == null) {
            select_lagerfachstamm = new Select_Lagerfachstamm();
        }
    }
    
    public DefaultComboBoxModel get_TeileTyp_combo_box_model() {
        ArrayList<String> teiletypen = new ArrayList<String>();
        for (TeileTypET typ: TeileTypET.values()) {
            teiletypen.add(typ.toString());
        }
        System.out.println(teiletypen);
        DefaultComboBoxModel model = new DefaultComboBoxModel(teiletypen.toArray());
        System.out.println(TeileTypET.values());
        System.out.println(model);
        return model;
    }
    public static void main(String[] args) throws ClassNotFoundException {
        Suchen_Controller suchen_controller = new Suchen_Controller();
        //suchen_controller.alle_teile_ausgeben();
        suchen_controller.get_TeileTyp_combo_box_model();
    }
}
