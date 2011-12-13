/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

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
    * 
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
    public void nutzeranforderung(String Bezeichnung, model.TeileTypET Typ, String Materialgruppe, String Zeichnungsnummer, String BaugruppeString, String Anschaffungsgrund, String PreisString, String MAKString, String MAMString, String MAGString, String Bemerkung)
   {
       
       //Semantische Prüfung der Variablen entsprchend DD begin
       double Preis= converter.StringTOdouble(PreisString);
       int MAK = converter.StringTOint(MAGString);
       int MAM = converter.StringTOint(MAGString);
       int MAG = converter.StringTOint(MAGString);   
       //Semantische Prüfung der Variablen entsprchend DD end
       
       //prüfe ob ein teil mit gleicher bezeichnung schon vorhanden ist begin
       if(bezSchonDa(Bezeichnung)){
           JOptionPane.showMessageDialog(null, "ein Teil mit der Bezeichnung: "+Bezeichnung+" ist bereits vorhanden.", "Konsitenz", 1);
       }else{
            
                //prüfe ob ein teil mit gleicher bezeichnung schon vorhanden ist end

                
                //ID aus der DB (freie id tabelle) entfernen begin
                int id =popnewIDfromTable();
                //ID aus der DB (freie id tabelle) entfernen end
                
                
                //Teil mit allen attributen in die DB schreiben und zur verwendung freigeben begin
                model.Teil_Stammdaten neuTeil = new model.Teil_Stammdaten(id, Typ, Zeichnungsnummer, Materialgruppe, Preis, Bezeichnung, BaugruppeString, Bemerkung, MAK, MAM, MAG);
                
                //Teil mit allen attributen in die DB schreiben und zur verwendung freigeben end
           try {     
                //Teil in die db stopfen begin
                db_s.insert_teilestamm(neuTeil);
                //Teil in die db stopfen end
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TeileStamm_erweitern_controller.class.getName()).log(Level.SEVERE, null, ex);
                           JOptionPane.showMessageDialog(null, "geprüftes Teil konnte nocht zur db hinzugefügt werden", "Fehlschlag", 1);

            }
       }
   }
    
   private boolean bezSchonDa(String bez){
       String bezAUSdb[]=db_s.getSpalteAusTabelle("bezeichnung", "Teilestammdaten");
       for(int i=bezAUSdb.length-1;i>=0;i--)
       {
           if(!bez.equals(bezAUSdb[i])){
               return false;
           }
           
       }
       return true;
       
   }
   
    
    /**Entfernt die erste ID in der freien id datenbank aus der tabelle und returnt diese.
     * @return verwendbare ID aus der db
     */
    private int popnewIDfromTable()
   {
       int returnval;
       idarr=new model.DB_schreiben().get_alle_freie_IDs();      
       returnval=converter.StringTOint(idarr[0]);
        try {
            new model.DB_schreiben().delete_freie_id(returnval);
        } catch (ClassNotFoundException ex) {
            System.out.println("keine id mit dem wert "+returnval+"in der Datenbank gefunden.\n"+ex);
            JOptionPane.showMessageDialog(null, "keine id mit dem wert "+returnval+"in der Datenbank gefunden.\n"+ex);
           // Logger.getLogger(TeileStamm_erweitern_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
       return returnval;
   }
    /**Zeigt die erste ID in der freien id datenbank aus der tabelle und returnt diese.
     * die ID bleibt als frei erhalten
     * @return 
     */
    private int getEXAMPLEid(){
        int returnval;
       idarr=new model.DB_schreiben().get_alle_freie_IDs();      
       returnval=converter.StringTOint(idarr[0]);
       return returnval;
    }
    /**Setzt eine beispielid ins textfeld
     * 
     * @param fake true: die id ist 1337, false: es wird nach einer validen id gesucht.
     * Hat keinen Einfluss darauf welche id das Teil tatsächlich erhält
     * 
     *
    public void setEXAMPLEid(boolean fake){
        
        if(fake){
            Hauptfenster.PATRICKsettextfeld_id(1337);
        }else{
            
        
        int exampleID;
       //Belibige freie id von der db beziehen und als beispiel ins gui schreinen begin
       exampleID=getEXAMPLEid();
       Hauptfenster.PATRICKsettextfeld_id(exampleID);
       //Belibige freie id von der db beziehen und als beispiel ins gui schreinen end
       
       }
        
    }*/
    
    
    /*!das hier muss noch in Übersicht_Lagerverwaltung und wird von der methode public void setEXAMPLEid(bool) benutzt
     public void PATRICKsettextfeld_id(int id){
        textfeld_id.setText(""+id);
    }
    */
}
