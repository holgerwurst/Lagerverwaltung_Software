/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import model.Select_Lagerfachstamm;
import model.Select_Stammdaten;

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
    public static void main(String[] args) throws ClassNotFoundException {
        Suchen_Controller suchen_controller = new Suchen_Controller();
        suchen_controller.alle_teile_ausgeben();
    }
}
