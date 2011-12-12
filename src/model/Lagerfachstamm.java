//..begin "File Description"
/*--------------------------------------------------------------------------------*
 Filename:  Lagerfachstamm.java
 Tool:      objectiF, 
 *--------------------------------------------------------------------------------*/
//..end "File Description"
package model;

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
    private Object[] attribute;

    //Konstruktor
    public Lagerfachstamm(String ort, String hochregalnummer, String zeile, String spalte, String groesse,
            Boolean belegung, String fach) {

        this.lagerort = ort;
        this.fachnummer = fach;
        this.hochregalnummer = hochregalnummer;
        this.zeile = zeile;
        this.spalte = spalte;
        this.groesse = groesse;
        this.belegung = belegung;
        this.attribute = new Object[]{
            this.lagerort, this.fachnummer, this.hochregalnummer, this.zeile,
            this.spalte, this.groesse, this.belegung
        };
    }
    /**
     * Einfacher Konstruktor der nur ein Array der Attribute als Parameter braucht.
     * @param attribute [lagerort, fachnummer, hochregalnummer, zeile, spalte, groesse, belegung]
     */
    public Lagerfachstamm(Object[] attribute) throws Exception {
        if (attribute.length > 7) {
            throw new Exception("Array has too many members.");
        }
        this.attribute = attribute;
        
        this.lagerort = (String) attribute[0];
        this.fachnummer = (String) attribute[1];
        this.hochregalnummer = (String) attribute[2];
        this.zeile = (String) attribute[3];
        this.spalte = (String) attribute[4];
        this.groesse = (String) attribute[5];
        this.belegung = (Boolean) attribute[6];



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
        lagerort = ort;
    }

    public void set_Hochregalnr(String nummer) {
        hochregalnummer = nummer;
    }

    public void set_Zeile(String n_zeile) {
        zeile = n_zeile;
    }

    public void set_Spalte(String n_spalte) {
        spalte = n_spalte;
    }

    public void set_groesse(String n_groesse) {
        groesse = n_groesse;
    }

    public void set_Belegung(boolean n_bel) {
        belegung = n_bel;
    }

    public void set_Fachnummer(String fnr) {
        fachnummer = fnr;
    }
}
