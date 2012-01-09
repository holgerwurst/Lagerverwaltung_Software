//..begin "File Description"
/*--------------------------------------------------------------------------------*
   Filename:  Lagerbestandskonto.java
   Tool:      objectiF, 
 *--------------------------------------------------------------------------------*/
//..end "File Description"

package model;

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
        private Object[] attribute;
        
        public Lagerbestandskonto() {
            
        }
        
        //Konstruktor
	public Lagerbestandskonto(String fachnummer,int id, int menge, String anschaffungsgrund,
                GregorianCalendar datum) {
        
        this.id = id;
        this.fachnummer = fachnummer;
        this.menge = menge;
        this.anschaffungsgrund = anschaffungsgrund;
        this.haltbarkeitsdatum = datum;
	
	}
        
        
/*        public Lagerbestandskonto(Object[] attribute) {
            this.id = Integer.parseInt(attribute[0].toString()); // Eine komische Lösung
            this.fachnummer = (String) attribute[1];
            this.menge = Integer.parseInt(attribute[2].toString());
            this.anschaffungsgrund = attribute[3];
            this.haltbarkeitsdatum = attribute[4];
        }*/
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
	
	public void toArray() {
            this.attribute = new Object[5];
            attribute[0] = get_Fachnummer();
            attribute[1] = get_TeileID();
            attribute[2] = get_Menge();
            attribute[3] = get_Anschaffungsgrund();
            attribute[4] = get_Haltbarkeitsdatum();            
        }
        
        public Object[] get_Attribute() {
            return attribute;
        }
}

