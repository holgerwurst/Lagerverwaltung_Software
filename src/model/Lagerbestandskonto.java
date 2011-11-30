//..begin "File Description"
/*--------------------------------------------------------------------------------*
   Filename:  Lagerbestandskonto.java
   Tool:      objectiF, 
 *--------------------------------------------------------------------------------*/
//..end "File Description"

package Model;

//..begin "Imports"

import java.util.GregorianCalendar;


//..end "Imports"


public class Lagerbestandskonto {

    //Deklaration der Variablen
        private int id;
	private String fachnummer;
	private int menge;
	private String anschaffungsgrund;
	private GregorianCalendar haltbarkeitsdatum;
         
        //Konstruktor
	public Lagerbestandskonto(String fachnummer,int id, int menge, String anschaffungsgrund,
                GregorianCalendar datum) {
        
        this.id = id;
        this.fachnummer = fachnummer;
        this.menge = menge;
        this.anschaffungsgrund = anschaffungsgrund;
        this.haltbarkeitsdatum = datum;
	
	}
        
        // getter Methoden
        
	public int get_TeileID() {
		return id;
	}
	
	public String get_Fachnummer() {
		return fachnummer;
	}
	
	public int get_Menge() {
		return menge;
	}
	
	public String get_Anschaffungsgrund() {
		return anschaffungsgrund;
	}
	
	public GregorianCalendar get_Haltbarkeitsdatum() {
		return haltbarkeitsdatum;
	}
	
	// setter Methoden
	
	public void set_id(int n_id) {
                id=n_id;
	}
	
	public void set_Fachnummer(String n_fachnummer) {
                fachnummer=n_fachnummer;
	}
	
	public void set_Menge(int n_menge) {
                menge=n_menge;
	}
	
	public void set_Anschaffungsgrund(String n_asg) {
                anschaffungsgrund=n_asg;
	}
	
	public void set_Haltbarkeitsdatum(GregorianCalendar n_date) {
                haltbarkeitsdatum=n_date;
	}
	
	
	

}

