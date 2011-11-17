package Model;

//..begin "Imports"
import TeileTypET;
//..end "Imports"


public class Teil_Stammdaten {
   
        
	private String materialgruppe;
	private String zeichnungsnummer;
        private int preis;
	private String bezeichnung;
      	private String bemerkung;
	private int max_anzahl_klein;
	private int max_anzahl_mittel;
	private int max_anzahl_gross;
	private String baugruppe;
        private String typ;
        private int id;
        
public Teil_Stammdaten(int id, String typ, String baugruppe, String zeichnungsnummer,
        int preis, String bezeichnung, String bemerkung, int max_anzahl_klein, int max_anzahl_mittel, int max_anzahl_gross, String materialgruppe) {
    
        this.zeichnungsnummer = zeichnungsnummer;
        this.preis = preis;
        this.bezeichnung = bezeichnung;
        this.bemerkung = bemerkung;
        this.max_anzahl_klein = max_anzahl_klein;
        this.max_anzahl_mittel = max_anzahl_mittel;
        this.max_anzahl_gross = max_anzahl_gross;
        this.typ = typ;
        this.baugruppe = baugruppe;
        this.id = id;
        this.materialgruppe = materialgruppe;	
	}
	public int get_id() {
		return 0;
	}
	
	public TeileTypET get_Teiletyp() {
		return null;
	}
	
	public String get_Materialgruppe() {
		return "";
	}
	
	public String get_Zeichnungsnummer() {
		return "";
	}
	
	public double get_Preis() {
		return 0;
	}
	
	public String get_Bezeichnung() {
		return "";
	}
	
	public String get_Bemerkung() {
		return "";
	}
	
	public int get_max_anz_klein() {
		return 0;
	}
	
	public int get_max_anz_mittel() {
		return 0;
	}
	
	public int get_max_anz_gross() {
		return 0;
	}
	
	public String get_Baugruppe() {
		return "";
	}
	
	
	
	public void set_id() {
	
	}
	
	public void set_Teiletyp() {
	
	}
	
	public void set_Materialgruppe() {
	
	}
	
	public void set_zeichnungsnummer() {
	
	}
	
	public void set_preis() {
	
	}
	
	public void set_Bezeichnung() {
	
	}
	
	public void set_bemerkung() {
	
	}
	
	public void set_max_anzahl_klein() {
	
	}
	
	public void set_max_anzahl_mittel() {
	
	}
	
	public void set_max_anzahl_gross() {
	
	}
	
	public void set_Baugruppe() {
	
	}
	
	
	

}