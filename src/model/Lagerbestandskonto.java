//..begin "File Description"
/*--------------------------------------------------------------------------------*
   Filename:  Lagerbestandskonto.java
   Tool:      objectiF, 
 *--------------------------------------------------------------------------------*/
//..end "File Description"

package Model;

//..begin "Imports"

//..end "Imports"


public class Lagerbestandskonto {

        private int id;
	private String fachnummer;
	private int menge;
	private String anschaffungsgrund;
	private java.util.GregorianCalendar haltbarkeitsdatum;
	public Lagerbestandskonto(int id, String fachnummer, int menge, String anschaffungsgrund,
                java.util.GregorianCalendar datum) {
        
        this.id = id;
        this.fachnummer = fachnummer;
        this.menge = menge;
        this.anschaffungsgrund = anschaffungsgrund;
        this.haltbarkeitsdatum = haltbarkeitsdatum;
	
	}
	public int get_TeileID() {
		return 0;
	}
	
	public String get_Fachnummer() {
		return "";
	}
	
	public int get_Menge() {
		return 0;
	}
	
	public String get_Anschaffungsgrund() {
		return "";
	}
	
	public java.util.GregorianCalendar get_Haltbarkeitsdatum() {
		return null;
	}
	
	
	
	public void set_id() {
	
	}
	
	public void set_Fachnummer() {
	
	}
	
	public void set_Menge() {
	
	}
	
	public void set_Anschaffungsgrund() {
	
	}
	
	public void set_Haltbarkeitsdatum() {
	
	}
	
	
	

}

