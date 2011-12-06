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
            System.out.println(nfe.getMessage());
            //nutzerhinweis gui starten
            JOptionPane.showMessageDialog(null, nfe+"\n Konvertierungsfehler:Integer");
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
        st.replace(',', '.');
       
        double returnstatement=0;
        if(st==null){return returnstatement;}        
        try{
        returnstatement = Double.parseDouble(st);
        }catch(NumberFormatException nfe)
        {
            System.out.println(nfe.getMessage());
            //nutzerhinweis gui starten
            //view.Nutzerhinweis1 nwh =new view.Nutzerhinweis1(null, true);
            //nwh.setHinweisfeld("Der Preis darf nur aus Ziffern bestehen. \nBuchstaben sind nicht erlaubt.");
            JOptionPane.showMessageDialog(null, nfe+"\n Konvertierungsfehler:Double");
        }
       
        return returnstatement;
    }
    
}
