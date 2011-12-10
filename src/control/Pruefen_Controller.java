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
    
    
    
    

    public boolean pruefe_id(int id) {

        try {

            String[] vergleich = std.get_ID_ausDB(id);
            for (int i = 0; i < vergleich.length; i++) {
                
               System.out.println("Teil mit ID " + vergleich[i] + " vorhanden");
                
                
            }

            if (vergleich.length == 0) {
                System.out.println("Kein Teil vorhanden");
                
                return false;
            }

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());
        }
        
        return true;

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

        } catch (ClassNotFoundException e) {
            System.out.println("Kein Teil vorhanden");
        }
    }
    
    
    public boolean auszulagernde_menge_pruefen(int mengeaktuell, int mengeneu) {
        if (mengeneu <= 0) {

            System.out.println("Auszulagernde Menge muss grösser als 0 sein");
            return false;
        }

        if (mengeaktuell < mengeneu) {
            System.out.println("Auszulagernde Menge kann max eingelagerter Menge entsprechen");
            return false;
        }

        if (mengeaktuell == mengeneu) {
            System.out.println("Teil löschen nach Auslagervorgang anbieten");
            return true;
        }

        return true;
    }
    
    
    
    public int fachnummerkorrekt(String[] fnrs, String fnr) {
       
        for (int i = 0; i < fnrs.length; i++) {
            
            if (fnr.equals(fnrs[i])) {
                
                
                return i;
                
            }
        }
        return 99999;
    }
    /*
     * public static void main(String[] args) throws Exception{
     * Pruefen_Controller co = new Pruefen_Controller(); co.pruefe_id(2); }
     */
}
