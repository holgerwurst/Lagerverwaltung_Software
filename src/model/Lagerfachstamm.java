//..begin "File Description"
/*--------------------------------------------------------------------------------*
   Filename:  Lagerfachstamm.java
   Tool:      objectiF, 
 *--------------------------------------------------------------------------------*/
//..end "File Description"

package Model;

//..begin "Imports"

//..end "Imports"


public class Lagerfachstamm {
        
        //Deklaration der Variablen
    
        private String fachnummer;
	private String hochregalnummer;
	private String zeile;
	private String spalte;
	private String groesse;
	private boolean belegung;
        private String lagerort;
        
        //Konstruktor
	public Lagerfachstamm(String ort, String hochregalnummer, String zeile, String spalte, String groesse,
		   Boolean belegung, String fach ) {
	
        this.lagerort=ort;
        this.fachnummer = fach;
        this.hochregalnummer = hochregalnummer;
        this.zeile = zeile;
        this.spalte = spalte;
        this.groesse = groesse;
        this.belegung = belegung;
	
	}
        
        // getter Methoden
	public String get_Lagerort() {
		return lagerort;
	}
	
	public String get_Hochregalnr() {
		return hochregalnummer;
	}
	
	public String get_Zeile() {
		return zeile;
	}
	
	public String get_Spalte() {
		return spalte;
	}
	
	public String get_groesse() {
		return groesse;
	}
	
	public boolean get_Belegung() {
		return belegung;
	}
	
	public String get_Fachnummer() {
		return fachnummer;
	}
	
        // setter Methoden
	public void set_Lagerort(String ort) {
            	lagerort=ort;
	}
	
	public void set_Hochregalnr(String nummer) {
	hochregalnummer=nummer;
	}
	
	public void set_Zeile(String n_zeile) {
	zeile=n_zeile;
	}
	
	public void set_Spalte(String n_spalte) {
	spalte=n_spalte;
	}
	
	public void set_groesse(String n_groesse) {
	groesse=n_groesse;
	}
	
	public void set_Belegung(boolean n_bel) {
	belegung=n_bel;
	}
	
	public void set_Fachnummer(String fnr) {
	fachnummer=fnr;
	}
	
	
	

}

