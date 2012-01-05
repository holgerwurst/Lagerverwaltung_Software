/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import javax.swing.JOptionPane;

/**
 *
 * @author Keleseth
 */
public class convert {
    /**Methode Wandelt string zu int, fängt die Exception ab und spuckt sie aus 
     * Die Fehlermeldung ist für euch und nicht für den nutzer gedacht
     * @param st übergebener string
     * @return umgewandelte zu int
     */
    public int StringTOint(String st)
    {
        
        int returnstatement=0;
        if(st==null){return returnstatement;}        
        try{
        returnstatement = Integer.parseInt(st);
        }catch(NumberFormatException nfe)
        {
            //System.out.println(nfe.getMessage());
            //nutzerhinweis gui starten
            //JOptionPane.showMessageDialog(null, nfe+"\n Konvertierungsfehler:Integer");
        }
        return returnstatement;
    }
    
    /**Methode Wandelt string zu double, fängt die Exception ab 
     * Die Fehlermeldung ist für euch und nicht für den nutzer gedacht
     * 
     * @param st übergebener string
     * @return umgewandelte zu double
     */
    public double StringTOdouble(String st)
    {
        st=st.replace(',', '.');
       
        double returnstatement=0;
        if(st==null){return returnstatement;}        
        try{
        returnstatement = Double.parseDouble(st);
        }catch(NumberFormatException nfe)
        {
            //System.out.println(nfe.getMessage());
            //nutzerhinweis gui starten
            //view.Nutzerhinweis1 nwh =new view.Nutzerhinweis1(null, true);
            //nwh.setHinweisfeld("Der Preis darf nur aus Ziffern bestehen. \nBuchstaben sind nicht erlaubt.");
            //JOptionPane.showMessageDialog(null, nfe+"\n Konvertierungsfehler:Double");
        }
       
        return returnstatement;
    }
    /**wandelt einen Sring in den entsprechenden teiletypET
     * !beachte groß/Klein-schreibung
     * @param st
     * @return wenn der sting nicht genau passt wird model.TeileTypET.kaufteile returnt
     */
    public model.TeileTypET StringTOET(String st){
        model.TeileTypET returnval= model.TeileTypET.kaufteile;
        if(st=="Kaufteile");
        else if(st=="Werkzeuge") returnval=model.TeileTypET.werkzeuge;
        else if(st=="Vorrichtungen")returnval=model.TeileTypET.vorrichtungen;
        else if(st=="unfertige Baugruppen")returnval=model.TeileTypET.unfertige_Baugruppen;
        else if(st=="Vorratsteile")returnval=model.TeileTypET.vorratsteile;
        return returnval;
        
        
    }
    
     /**wandelt einen Object in den entsprechenden teiletypET
     * !beachte groß/Klein-schreibung
     * @param st
     * @return wenn das OBJECT nicht genau passt wird model.TeileTypET.kaufteile returnt
     */
    public model.TeileTypET ObjectTOET(Object st){
        model.TeileTypET returnval= model.TeileTypET.kaufteile;
        if(st==model.TeileTypET.kaufteile);
        else if(st==model.TeileTypET.werkzeuge) returnval=model.TeileTypET.werkzeuge;
        else if(st==model.TeileTypET.vorrichtungen)returnval=model.TeileTypET.vorrichtungen;
        else if(st==model.TeileTypET.unfertige_Baugruppen)returnval=model.TeileTypET.unfertige_Baugruppen;
        else if(st==model.TeileTypET.vorratsteile)returnval=model.TeileTypET.vorratsteile;
        return returnval;
        
        
    }
    
}
