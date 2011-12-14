/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import model.Select_Lagerbestandskonto;
import model.Lagerbestandskonto;
import model.DB_schreiben;
import javax.swing.*;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Herold
 */
public class Teil_auslagern_controller {

    int id;
    String[] fachnummern;
    String[] aktuelle_menge;
    String[] anschaffungsgrund;
    int auszulagernde_menge;
    

    public void auslagern_vorbereitung(int idt) throws ClassNotFoundException {

        id = idt;

        Select_Lagerbestandskonto sl = new Select_Lagerbestandskonto();
        Select_Lagerbestandskonto sl2 = new Select_Lagerbestandskonto();
        Select_Lagerbestandskonto sl3 = new Select_Lagerbestandskonto();

        fachnummern = sl.get_Fachnummer_ausDB(id);

        aktuelle_menge = new String[fachnummern.length];

        for (int i = 0; i < fachnummern.length; i++) {


            aktuelle_menge = sl2.get_Menge_ausDB(fachnummern[i]);

            anschaffungsgrund = sl3.get_Anschaffungsgrund_ausDB(fachnummern[i]);

            //System.out.println("Fachnummern: " + fachnummern[i] + ", Menge:" + aktuelle_menge[i] + ", Ans.grund: " + anschaffungsgrund[i]);
        }
    }
/**
 * 
 * Kontrolle Anzahl vorhandener Faecher.
 * Update des Lagerfachstammes, der Faecher, in denen Menge auf 0 fiel.
 * 
 * retunr 0 - letztes Fach fiel auf 0 -> Konto löschen und Teil entfernen anbieten.
 * return 1 - 
 * 
 * @param id
 * @param laenge
 * @param fnr
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

    public String[] getfachnummern() {
        return fachnummern;
    }

    public int getID() {
        return id;
    }

    public String[] getaktuelle_menge() {
        return aktuelle_menge;
    }

    public String[] getanschaffungsgrund() {

        return anschaffungsgrund;
    }

    public Lagerbestandskonto erstelle_lbk(String fnr, int idt, int neue_menge, String ansgrund) {
        
        Lagerbestandskonto lbk = new Lagerbestandskonto(fnr, idt, neue_menge, ansgrund, null);

        return lbk;

    }

    public void auslagern_durchfuehrung(Lagerbestandskonto lbk) throws ClassNotFoundException {

        DB_schreiben dbs = new DB_schreiben();

        //System.out.println("LBK wurde mit folgenden Werten geschrieben: ");
        //System.out.println(lbk.get_Fachnummer());
        //System.out.println(lbk.get_TeileID());
        //System.out.println(lbk.get_Menge());
        //System.out.println(lbk.get_Anschaffungsgrund());
        //System.out.println(lbk.get_Haltbarkeitsdatum());

        dbs.update_lagerbestand(lbk);
    }

    
    public static void main(String[] args) throws ClassNotFoundException {

        Teil_auslagern_controller ta = new Teil_auslagern_controller();

        Pruefen_Controller pc = new Pruefen_Controller();

        //System.out.println(pc.pruefe_id(55));

        //ta.auslagern_vorbereitung(1);

        //ta.auslagern_durchfuehrung("FH1Z1S1K", 66);


    }
}
