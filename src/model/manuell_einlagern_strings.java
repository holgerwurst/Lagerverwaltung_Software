/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.convert;

/**
 *
 * @author Wayne
 */
public class manuell_einlagern_strings {
    
    convert cv = new convert();
    
    String fachnummer="";
    int menge=0;
    String anschaffungsgrund="";
    
    public manuell_einlagern_strings(String fnr, String menge, String anschaffungsgrund)
    {
        this.fachnummer=fnr;
        this.menge=cv.StringTOint(menge);
        this.anschaffungsgrund=anschaffungsgrund;
    }
    
    public void set_fachnummer(String fnr)
    {
        fachnummer=fnr;
    }
    public void set_menge(String mge)
    {
        menge=cv.StringTOint(mge);
    }
    public void set_anschaffungsgrund(String asg)
    {
        anschaffungsgrund=asg;
    }
    public String get_fachnummer()
    {
        return fachnummer;
    }
    public int get_menge()
    {
        return menge;
    }
    public String get_anschaffungsgrund()
    {
        return anschaffungsgrund;
    }
    
    
}
