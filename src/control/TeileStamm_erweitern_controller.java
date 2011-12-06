/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

/**
 *
 * @author Keleseth
 */
public class TeileStamm_erweitern_controller {
    
    private convert converter =new convert();
    private model.DB_schreiben dbwriter=new model.DB_schreiben();
    private String[] idarr;
    
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
       //Belibige freie id von der db beziehen und als beispiel ins gui schreinen begin
       
       //Belibige freie id von der db beziehen und als beispiel ins gui schreinen end
       
       //Semantische Prüfung der Variablen entsprchend DD begin
       double Preis= converter.StringTOdouble(PreisString);
       int MAK = converter.StringTOint(MAGString);
       int MAM = converter.StringTOint(MAGString);
       int MAG = converter.StringTOint(MAGString);   
       //Semantische Prüfung der Variablen entsprchend DD end
       
       
       //ID aus der DB (freie id tabelle) entfernen begin
       int id =popnewIDfromTable();
       //ID aus der DB (freie id tabelle) entfernen end
       
       
       //Teil mit allen attributen in die DB schreiben und zur verwendung freigeben begin
       model.Teil_Stammdaten neuTeil = new model.Teil_Stammdaten(id, Typ, Zeichnungsnummer, Materialgruppe, Preis, Bezeichnung, BaugruppeString, Bemerkung, MAK, MAM, MAG);
       
       //Teil mit allen attributen in die DB schreiben und zur verwendung freigeben end
       
   }
    
   
    /**!!! BETAMETHODE !!!
     * return 6;
     * @return 
     */
    private int popnewIDfromTable()
   {
       idarr=new model.DB_schreiben().get_alle_freie_IDs();
       
       
       
       //gepoppte id noch aus der db löschen!
       return converter.StringTOint(idarr[0]);
       
       
       
     
   }
    
}
