/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Datenbankverbindung;
import model.Select_Stammdaten;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Arthas
 */
public class Pruefen_Controller {

    private Select_Stammdaten std = new Select_Stammdaten();

    public void pruefe_id(int id) {

        try {

            String[] vergleich = std.get_ID_ausDB(id);
            for (int i = 0; i < vergleich.length; i++) {
                System.out.println(vergleich[i]);
            }

            if (vergleich.length == 0) {
                System.out.println("Kein Teil vorhanden");
            }

        } catch (SQLException e) {

            System.out.println(e);
        }

    }

    public void pruefe_bezeichnung(String bezeichnung) {
        try {
            String[] vergleich = std.get_Bezeichnung_ausDB(bezeichnung);

            for (int i = 0; i < vergleich.length; i++) {
                System.out.println(vergleich[i]);
            }

            if (vergleich.length == 0) {
                System.out.println("Kein Teil vorhanden");
            }

        } catch (SQLException e) {
            System.out.println("Kein Teil vorhanden");
        }
    }
    /*
     * public static void main(String[] args) throws Exception{
     * Pruefen_Controller co = new Pruefen_Controller(); co.pruefe_id(2); }
     */
}
