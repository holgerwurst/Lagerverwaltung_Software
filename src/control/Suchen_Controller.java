/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import model.Select_Lagerfachstamm;
import model.Select_Stammdaten;
import model.TeileTypET;

/**
 *
 * @author felix
 */
public class Suchen_Controller {

    Select_Stammdaten select_stammdaten = null;
    Select_Lagerfachstamm select_lagerfachstamm = null;

    public void alle_teile_ausgeben() throws ClassNotFoundException {
        if (select_stammdaten == null) {
            select_stammdaten = new Select_Stammdaten();
        }
        System.out.println(select_stammdaten.get_ganze_tabelle_teilestammdaten());
    }

    public void teile_suchen() {
        if (select_stammdaten == null) {
            select_stammdaten = new Select_Stammdaten();
        }
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
