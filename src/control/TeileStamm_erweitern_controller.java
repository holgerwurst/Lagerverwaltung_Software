/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Keleseth
 */
public class TeileStamm_erweitern_controller {
    
    //declare variables begin
    private convert converter =new convert();
    private model.DB_schreiben dbwriter=new model.DB_schreiben();
    private String[] idarr;
    private view.Übersicht_Lagerverwaltung Hauptfenster;
    
    private model.DB_schreiben db_s =new model.DB_schreiben();
   
    //declare variables end

    
    
    
    /**Konstruktor
     * 
     * @param Hauptfenster das GUI mit dem gearbeitet wird. 
     */
    public TeileStamm_erweitern_controller(view.Übersicht_Lagerverwaltung Hauptfenster){
        this.Hauptfenster=Hauptfenster;
    }
   /**Hauptmethode: hier wird die Nutzeranforderung ein neues Teil anzulegen aufgenommen, geprüft und weiterverarbeitet
    * Methode wird aufgerufen, wenn der anlegen-button gedrückt wird.
    * @param Bezeichnung
    * @param Typ Hier wird gleich ein Enum aus model.TeileTypET angenommen und nicht weiter geprüft
    * @param Materialgruppe
    * @param Zeichnungsnummer
    * @param BaugruppeString
    * @param Anschaffungsgrund
    * @param PreisString
    * @param MAKString
    * @param MAMString
    * @param MAGString
    * @param Bemerkung 
    */
    public void nutzeranforderung(String Bezeichnung, model.TeileTypET Typ, String Materialgruppe, 
            String Zeichnungsnummer, String BaugruppeString, String PreisString, 
            String MAKString, String MAMString, String MAGString, String Bemerkung)
   {
       
       // umwandeln in den richtigen Datentyp begin
       double Preis= converter.StringTOdouble(PreisString);
       int MAK = converter.StringTOint(MAKString);
       int MAM = converter.StringTOint(MAMString);
       int MAG = converter.StringTOint(MAGString);   
       // umwandeln in den richtigen Datentyp end
       
       //prüfe ob ein teil mit gleicher bezeichnung schon vorhanden ist begin
       if(bezSchonDa(Bezeichnung)){
           //JOptionPane.showMessageDialog(null, "Ein Teil mit der Bezeichnung: "+Bezeichnung+" ist bereits vorhanden.\nBezeichnungen müssen eindeutig sein.", "Konsitenz", 1);
           //Behandlung verschoben, die fehlermeldung kommt jetzt beim focus lost wenn der button beim drücken den focus requestet (focusLostevent vom  Bez.Feld wurde bereits ausgelöst... Fall wurde schon bearbeitet.
           
       }else{
            
                //prüfe ob ein teil mit gleicher bezeichnung schon vorhanden ist end

                
                //ID aus der DB (freie id tabelle) entfernen begin mit <<<<<<<<<<WORKAROUD>>>>>>>>>
                int id =popnewIDfromTable();
                //int id=getWAid();//<<<<<<<<<<WORKAROUD>>>>>>>>>
                //ID aus der DB (freie id tabelle) entfernen end
                
                
                //Teil mit allen attributen in die DB schreiben und zur verwendung freigeben begin
                model.Teil_Stammdaten neuTeil = new model.Teil_Stammdaten(id, Typ, Zeichnungsnummer, Materialgruppe, Preis, Bezeichnung, BaugruppeString, Bemerkung, MAK, MAM, MAG);
                //System.out.println("stopfe neues Teil in die DB neues Teil:\nID:"+neuTeil.get_Id()+"\nBaugruppe:"+neuTeil.get_Baugruppe()+"\nBezeichnung: "+neuTeil.get_Bezeichnung()+"\nMatgrp:"+neuTeil.get_Materialgruppe()+"\nZnr"+neuTeil.get_Zeichnungsnummer()+"\ntyp"+neuTeil.get_Teiletyp()+"\nPreis"+neuTeil.get_Preis()+"€\nmag"+neuTeil.get_max_anz_gross()+"\nmam"+neuTeil.get_max_anz_mittel()+"\nmak"+neuTeil.get_max_anz_klein());
                
           try {     
                //Teil in die db stopfen begin
                db_s.insert_teilestamm(neuTeil);
                //Teil in die db stopfen end
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TeileStamm_erweitern_controller.class.getName()).log(Level.SEVERE, null, ex);
                           JOptionPane.showMessageDialog(null, "geprüftes Teil konnte nicht zur Datenbank hinzugefügt werden!", "Fehlschlag", 1);

            }
           Hauptfenster.setpTSEStatusleiste1(neuTeil.get_Bezeichnung()+
                   " wurde erfolgreich mit der ID "+neuTeil.get_Id()+
                   " angelegt. Sie können das neue Teil nun einlagern.",Color.BLACK);//Statusleiste1 
           Hauptfenster.setpTSEBezeichnung("");//Bezeichnungsfeld leeren
           Hauptfenster.setpTSEStatusleiste2("Die freie ID "
                   +getEXAMPLEid()+" wurde aus der Datenbank bezogen und wird"
                   + " dem neuen Teil beim anlegen zugewiesen.", Color.BLACK); //Statusleiste2
       //Teil mit allen attributen in die DB schreiben und zur verwendung freigeben end
       }
   }
   /**Testmethode für eindeutige Bezeichnungen
    * 
    * @param bez die zu prüfende Bezeichnung.
    * @return Returnt nur true, wenn bereits ein Teil mit der Übergebenen bezeichnung in der DB steht. sonst false
    */ 
   public boolean bezSchonDa(String bez){
       boolean returnval=false;
       
       String bezAUSdb[]=db_s.getSpalteAusTabelle("bezeichnung", "Teilestammdaten");//bezeichnungen aus der DB beziehen
       for(int i=bezAUSdb.length-1;i>=0;i--)//durchlauf, ob dier Bez schon da 
       {
           //System.out.println(bezAUSdb[i]);
           if(bez.equals(bezAUSdb[i])){
               returnval= true;
           }
           
       }
       
       return returnval;
       
   }
   
    
    /**Entfernt die erste ID in der freien id datenbank aus der Tabelle und returnt diese.
     * Die ID ist für ein Neuteil verwendbar.
     * <disabled> Fix: wenn die id tabelle leer ist werden die ids von 100-200 in die id tabelle reingeschrieben
     * @return verwendbare ID aus der db
     */
    private int popnewIDfromTable()
   {
       //System.out.println("popnewIDfromTable() ...begin");
       int returnval;
       idarr=dbwriter.get_alle_freie_IDs(); 
       
      //fix start
       if(idarr.length==0){ 
           //System.out.println("keine ids in der tabelle fixe 100-200 hinzu");
           //generierefreieIDS(100,200); 
                   JOptionPane.showMessageDialog(null, "Keine IDs mehr verfügbar! \nWenden Sie sich bitte an den Hersteller zur generierung neuer IDs.");
}
       //fix end
       
       //System.out.println("gefundene freie ID: "+idarr[0]);
       returnval=converter.StringTOint(idarr[0]);
        try {
              //ID aus der Freie IDs Tabelle entfernen:     

            db_s.delete_freie_id(returnval);
                 

        } catch (ClassNotFoundException ex) {
            System.out.println("keine id mit dem wert "+returnval+"in der Datenbank gefunden.\n"+ex);
            JOptionPane.showMessageDialog(null, "keine id mit dem wert "+returnval+"in der Datenbank gefunden.\n"+ex);
           // Logger.getLogger(TeileStamm_erweitern_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("popnewIDfromTable() ...end");
       return returnval;
   }
    
    
    
    /**Zeigt die erste ID in der freien id datenbank aus der tabelle und returnt diese.
     * die ID bleibt als frei erhalten
     * @return eien freie id
     */
    public int getEXAMPLEid(){
        int returnval=-1;
        String[] idarr2=dbwriter.get_alle_freie_IDs(); 
       
      try{
       if(idarr2.length==0){ 
                              JOptionPane.showMessageDialog(null, 
                                      "Keine IDs mehr verfügbar! Das Limit verfügbarer Teilestammdaten ist erreicht oder die Datenbank ist nicht verfügbar."
                                      + "\nWenden Sie sich bitte an den Hersteller zur Generierung neuer IDs.\n"
                                      + "Sollten noch keine TeileStammdaten angelegt sein müssen neue IDs geschrieben werden.\n");
      
                                      
                                      
           //fix start                   
           //System.out.println("keine ids in der tabelle! fixe 0-200 hinzu");
           //generierefreieIDS(0,200); 
           //generierefreieIDS(201,20000); 
            //fix end
       }
      
        
       idarr=new model.DB_schreiben().get_alle_freie_IDs();      
       returnval=converter.StringTOint(idarr[0]);
      }catch(java.lang.ArrayIndexOutOfBoundsException ex){
          JOptionPane.showMessageDialog(null, 
                                      "Kein Datenbankzugriff mehr verfügbar! "
                  + "\n Die Datei Lagerverwaltung.db könnte beschädigt sein.");
          Hauptfenster.button_anlegen_sperren();
          
      }
       return returnval;
    }
    
    /**Methode zum generieren neuer IDs die param geben das Intervall an in dnenen die neuen neuen ids erzeugt werden 
     * 
     * @param untergrenze ...kleinste neu generierte ID
     * @param obergrenze ...großte neu generierte ID
     */
    private void generierefreieIDS(int untergrenze, int obergrenze){
        for(int i=untergrenze;i<=obergrenze;i++)
        {
            try {
                dbwriter.insert_freieId(i);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TeileStamm_erweitern_controller.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("generierefreieIDS "+ex);
            }
        }
        
    }
    /**Workaroud: durchsucht die bestehenden Teilestammdaten und ermittelt eine noch nicht vergebene ID
     * wird nicht verwendet! alt:im Übersicht_lagerveraltung line33,line2216, this.nuteranforderung line76
     * umgehen des alten id Problems
     * @return die nocht nicht vergebene ID
     */
    public int getWAid(){
        //bezihe das maximum aus den bestehenden IDs
        //konvertieren
        //+1
        int returnval=converter.StringTOint(new model.DB_schreiben().getSpalteAusTabelle("max(id)", "Teilestammdaten")[0])+1;
        
        
        
        return returnval;
    }
    
    

}
