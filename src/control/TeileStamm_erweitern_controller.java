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
       int MAK = converter.StringTOint(MAKString);
       int MAM = converter.StringTOint(MAMString);
       int MAG = converter.StringTOint(MAGString);   
       //Semantische Prüfung der Variablen entsprchend DD end
       
       //prüfe ob ein teil mit gleicher bezeichnung schon vorhanden ist begin
       if(bezSchonDa(Bezeichnung)){
           //JOptionPane.showMessageDialog(null, "Ein Teil mit der Bezeichnung: "+Bezeichnung+" ist bereits vorhanden.\nBezeichnungen müssen eindeutig sein.", "Konsitenz", 1);
           //mache nix, die fehlermeldung kommt schon beim focus lost wenn der button beim drücken den focus requestet
       }else{
            
                //prüfe ob ein teil mit gleicher bezeichnung schon vorhanden ist end

                
                //ID aus der DB (freie id tabelle) entfernen begin mit <<<<<<<<<<WORKAROUD>>>>>>>>>
                int id =popnewIDfromTable();
                //int id=getWAid();//<<<<<<<<<<WORKAROUD>>>>>>>>>
                //ID aus der DB (freie id tabelle) entfernen end
                
                
                //Teil mit allen attributen in die DB schreiben und zur verwendung freigeben begin
                model.Teil_Stammdaten neuTeil = new model.Teil_Stammdaten(id, Typ, Zeichnungsnummer, Materialgruppe, Preis, Bezeichnung, BaugruppeString, Bemerkung, MAK, MAM, MAG);
                //System.out.println("stopfe neues Teil in die DB neues Teil:\nID:"+neuTeil.get_Id()+"\nBaugruppe:"+neuTeil.get_Baugruppe()+"\nBezeichnung: "+neuTeil.get_Bezeichnung()+"\nMatgrp:"+neuTeil.get_Materialgruppe()+"\nZnr"+neuTeil.get_Zeichnungsnummer()+"\ntyp"+neuTeil.get_Teiletyp()+"\nPreis"+neuTeil.get_Preis()+"€\nmag"+neuTeil.get_max_anz_gross()+"\nmam"+neuTeil.get_max_anz_mittel()+"\nmak"+neuTeil.get_max_anz_klein());
                //Teil mit allen attributen in die DB schreiben und zur verwendung freigeben end
           try {     
                //Teil in die db stopfen begin
                db_s.insert_teilestamm(neuTeil);
                //Teil in die db stopfen end
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TeileStamm_erweitern_controller.class.getName()).log(Level.SEVERE, null, ex);
                           JOptionPane.showMessageDialog(null, "geprüftes Teil konnte nocht zur db hinzugefügt werden", "Fehlschlag", 1);

            }
           Hauptfenster.setpTSEStatusleiste(neuTeil.get_Bezeichnung()+" wurde erfolgreich angelegt. Sie können nun "+neuTeil.get_Bezeichnung()+" einlagern.",Color.green);
       }
   }
    
   public boolean bezSchonDa(String bez){
       boolean returnval=false;
       String bezAUSdb[]=db_s.getSpalteAusTabelle("bezeichnung", "Teilestammdaten");
       for(int i=bezAUSdb.length-1;i>=0;i--)
       {
           //System.out.println(bezAUSdb[i]);
           if(bez.equals(bezAUSdb[i])){
               returnval= true;
           }
           
       }
       
       return returnval;
       
   }
   
    
    /**Entfernt die erste ID in der freien id datenbank aus der tabelle und returnt diese.
     * Fix: wenn die id tabelle leer ist werden die ids von 100-200 in die id tabelle reingeschrieben
     * @return verwendbare ID aus der db
     */
    private int popnewIDfromTable()
   {
       //System.out.println("popnewIDfromTable() ...begin");
       int returnval;
       idarr=dbwriter.get_alle_freie_IDs(); 
       
      //fix start
       if(idarr.length==0){ 
           System.out.println("keine ids in der tabelle fixe 100-200 hinzu");
           generierefreieIDS(100,200); }
       //fix end
       
       //System.out.println("gefundene freie ID: "+idarr[0]);
       returnval=converter.StringTOint(idarr[0]);
        try {
                   //System.out.println(" new model.DB_schreiben().delete_freie_id(returnval); ...begin");

            db_s.delete_freie_id(returnval);
                  // System.out.println(" new model.DB_schreiben().delete_freie_id(returnval); ...end");

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
     * @return 
     */
    public int getEXAMPLEid(){
        int returnval;
        String[] idarr2=dbwriter.get_alle_freie_IDs(); 
       
      //fix start
       if(idarr2.length==0){ 
           System.out.println("keine ids in der tabelle fixe 0-200 hinzu");
           generierefreieIDS(0,200); }
       //fix end
        
       idarr=new model.DB_schreiben().get_alle_freie_IDs();      
       returnval=converter.StringTOint(idarr[0]);
       return returnval;
    }
    
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
     * wird verwendet im Übersicht_lagerveraltung line33,line2216, this.nuteranforderung line76
     * 
     * @return die nocht nicht vergebene ID
     */
    public int getWAid(){
        int returnval=converter.StringTOint(new model.DB_schreiben().getSpalteAusTabelle("max(id)", "Teilestammdaten")[0])+1;
        
        
        
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
