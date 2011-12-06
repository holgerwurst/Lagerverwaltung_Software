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
    
    /*public void id_eingelagert(int idt) throws ClassNotFoundException
    {
        Select_Lagerbestandskonto sl3 = new Select_Lagerbestandskonto();
        
        if(sl3.get_Fachnummer_ausDB(idt).length == 0)
        {      
           System.out.println("Teil ist nicht eingelagert");
        }
       
    }*/
    
    public String [] getfachnummern()
    {
        return fachnummern;
    }
    
    public int getID()
    {
        return id;
    }
    
     public String [] getaktuelle_menge()
    {
        return aktuelle_menge;
    }
     
      public String [] getanschaffungsgrund()
    {
     
        return anschaffungsgrund;
    }
    
      
    public void auszulagernde_menge_pruefen(int mengeaktuell, int mengeneu)
    {
        if(mengeneu<=0)
        {
            System.out.println("Auszulagernde Menge muss grösser als 0 sein");
        }
        
        if(mengeaktuell<mengeneu)
        {
            System.out.println("Auszulagernde Menge kann max eingelagerter Menge entsprechen");
        }
        
        if(mengeaktuell==mengeneu)
        {
            System.out.println("Möchten Sie das Teil löschen?");
            
        }
        
    }   
    
    
    
    public void auslagern_durchfuehrung(String fachnummer, int auszulagernde_menge) throws ClassNotFoundException {



        DB_schreiben dbs = new DB_schreiben();

        int neue_menge = 0;
        
        String ansgrd="";
        
        for (int i = 0; i < fachnummern.length; i++) {
            if (fachnummern[i].toString().compareTo(fachnummer) == 0) {
                                
                int temp = Integer.parseInt(aktuelle_menge[i]);

                auszulagernde_menge_pruefen(temp, auszulagernde_menge);
                
                neue_menge = temp - auszulagernde_menge;
                
                ansgrd=anschaffungsgrund[i];
            }
        }


        Lagerbestandskonto lbk = new Lagerbestandskonto(fachnummer, id, neue_menge, ansgrd, null);


        //dbs.update_lagerbestand(lbk);

    }

    public static void main(String[] args) throws ClassNotFoundException {

        Teil_auslagern_controller ta = new Teil_auslagern_controller();
        
        Pruefen_Controller pc = new Pruefen_Controller();
        
        //System.out.println(pc.pruefe_id(55));
        
        //ta.auslagern_vorbereitung(1);

        //ta.auslagern_durchfuehrung("FH1Z1S1K", 66);


    }
}
