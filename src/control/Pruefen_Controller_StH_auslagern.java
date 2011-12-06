/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import model.Select_Lagerbestandskonto;
import model.Lagerbestandskonto;
import model.DB_schreiben;


/**
 *
 * @author Herold
 */
public class Pruefen_Controller_StH_auslagern {

    int id;
    String[] fachnummern;
    String[] aktuelle_menge;
    int auszulagernde_menge;

    public void auslagern_vorbereitung(int idt) throws ClassNotFoundException {

        id = idt;

        Select_Lagerbestandskonto sl = new Select_Lagerbestandskonto();
        Select_Lagerbestandskonto sl2 = new Select_Lagerbestandskonto();


        fachnummern = sl.get_Fachnummer_ausDB(id);

        aktuelle_menge = new String[fachnummern.length];

        for (int i = 0; i < fachnummern.length; i++) {


            aktuelle_menge = sl2.get_Menge_ausDB(fachnummern[i]);



            System.out.println("Fachnummern: " + fachnummern[i] + ", Menge:" + aktuelle_menge[i]);

        }




    }
    
    public void id_eingelagert(int idt) throws ClassNotFoundException
    {
        Select_Lagerbestandskonto sl3 = new Select_Lagerbestandskonto();
        
        if(sl3.get_Fachnummer_ausDB(idt).length == 0)
        {    
     
           System.out.println("Teil ist nicht eingelagert");
        }
       
    }
    
    
    
    public void menge_pruefen(int alt, int neu)
    {
        if(neu<=0)
        {
            System.out.println("Auszulagernde Menge muss grösser als 0 sein");
        }
        
        if(alt<neu)
        {
            System.out.println("Auszulagernde Menge kann max eingelagerter Menge entsprechen");
        }
        
        if(alt==neu)
        {
            System.out.println("Möchten Sie das Teil löschen");
            
        }
        
    }
    
    
    
    
    public void auslagern_durchfuehrung(String fachnummer, int auszulagernde_menge) throws ClassNotFoundException {



        DB_schreiben dbs = new DB_schreiben();

        int neue_menge = 0;

        for (int i = 0; i < fachnummern.length; i++) {
            if (fachnummern[i].toString().compareTo(fachnummer) == 0) {
                                
                int temp = Integer.parseInt(aktuelle_menge[i]);

                menge_pruefen(temp, auszulagernde_menge);
                
                neue_menge = temp - auszulagernde_menge;
            }
        }


        Lagerbestandskonto lbk = new Lagerbestandskonto(fachnummer, id, neue_menge, "anschaffungsgrund", null);


        //dbs.update_lagerbestand(lbk);

    }

    public static void main(String[] args) throws ClassNotFoundException {

        Pruefen_Controller_StH_auslagern ta = new Pruefen_Controller_StH_auslagern();
        
        ta.id_eingelagert(1);
        
        ta.auslagern_vorbereitung(1);

        ta.auslagern_durchfuehrung("FH1Z1S1K", 66);


    }
}
