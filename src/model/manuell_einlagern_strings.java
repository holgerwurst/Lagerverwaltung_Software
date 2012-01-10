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
    int fnr_gui=0;
    int fehler=0;
    String fehler_text="";
    
    public manuell_einlagern_strings(String fnr, String menge, String anschaffungsgrund, int fnr_gui, int fehler,String fehler_text)
    {
        this.fachnummer=fnr;
        this.menge=cv.StringTOint(menge);
        this.anschaffungsgrund=anschaffungsgrund;
        this.fnr_gui=fnr_gui;
        this.fehler=fehler;
        this.fehler_text=fehler_text;
    }
    
    public void set_fachnummer(String fnr)
    {
        this.fachnummer=fnr;
    }
    public void set_menge(String mge)
    {
        this.menge=cv.StringTOint(mge);
    }
    public void set_anschaffungsgrund(String asg)
    {
        this.anschaffungsgrund=asg;
    }
    public void set_fnr_gui(int fnr_gui)
    {
        this.fnr_gui=fnr_gui;
    }
    public void set_fehler(int fehler)
    {
        this.fehler=fehler;
    }
    public void set_fehler_text(String txt)
    {
        this.fehler_text=txt;
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
    public int get_fnr_gui()
    {
        return fnr_gui;
    }
    public int get_fehler()
    {
        return fehler;
    }
    public String get_fehler_text()
    {
        return fehler_text;
    }
    
    
}
