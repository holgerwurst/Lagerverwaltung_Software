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
    
    /**Methode Wandelt string zu int, fängt die Exception ab und leitet falls der string null ist dies einfach weiter
     * sollte es zu problemen geben callt diese Methode den entsprechenden Nutzerhinweis
     * @param st übergebener string
     * @return umgewandelte zu int
     */
    public int StringZUintwandler(String st)
    {
        int returnstatement=new Integer(null);
        if(st==null){return returnstatement;}        
        try{
        returnstatement = Integer.parseInt(st);
        }catch(NumberFormatException nfe)
        {
            System.out.println(nfe.getMessage());
            //nutzerhinweis gui starten
        }
        return returnstatement;
    }
    
    /**Methode Wandelt string zu double, fängt die Exception ab und leitet falls der string null ist dies einfach weiter
     * sollte es zu problemen geben callt diese Methode den entsprechenden Nutzerhinweis
     * @param st übergebener string
     * @return umgewandelte zu double
     */
    public double StringZUdoublewandler(String st)
    {
        double returnstatement=new Double(null);
        if(st==null){return returnstatement;}        
        try{
        returnstatement = Double.parseDouble(st);
        }catch(NumberFormatException nfe)
        {
            System.out.println(nfe.getMessage());
            //nutzerhinweis gui starten
            view.Nutzerhinweis1 nwh =new view.Nutzerhinweis1(null, true);
            nwh.setHinweisfeld("Der Preis darf nur aus Ziffern bestehen. \nBuchstaben sind nicht erlaubt.");
        }
        if(returnstatement%0.01!=0)
        {
            view.Nutzerhinweis1 nwhB =new view.Nutzerhinweis1(null, true);
            nwhB.setHinweisfeld("Es sind nur 2 Nachkommastellen erlaubt.");
            
        }
        return returnstatement;
    }
    
   public void nutzeranforderung(String Bezeichnung, model.TeileTypET Typ, String Materialgruppe, String Zeichnungsnummer, String BaugruppeString, String Anschaffungsgrund, String PreisString, String MAKString, String MAMString, String MAGString, String Bemerkung)
   {
       
       //Semantische Prüfung der Variablen entsprchend DD begin
       double Preis=StringZUdoublewandler(PreisString);
       int MAK = StringZUintwandler(MAKString);
       int MAM = StringZUintwandler(MAMString);
       int MAG = StringZUintwandler(MAGString);
       
       
       
       //Semantische Prüfung der Variablen entsprchend DD end
   }
    
}
