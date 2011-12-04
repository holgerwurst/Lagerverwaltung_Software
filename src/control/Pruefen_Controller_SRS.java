/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Select_Stammdaten;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Arthas
 */
public class Pruefen_Controller_SRS {

    private Select_Stammdaten std = new Select_Stammdaten();

    public void pruefe_id(int id) {
        try {
            String vergleich;
            vergleich = std.get_ID_ausDB(id);
            System.out.println(vergleich);
        } catch (SQLException e) {
            System.out.println("Kein Teil vorhanden");
        }
    }
    
      public void pruefe_bezeichnung(String bezeichnung) {
        try {
            String vergleich;
            vergleich = std.get_Bezeichnung_ausDB(bezeichnung);
            System.out.println(vergleich);
        } catch (SQLException e) {
            System.out.println("Kein Teil vorhanden");
        }
    }
    /*
     * public static void main(String[] args) throws Exception{
     * Pruefen_Controller_SRS co = new Pruefen_Controller_SRS(); co.pruefe_id(2);
   }
     */
}
