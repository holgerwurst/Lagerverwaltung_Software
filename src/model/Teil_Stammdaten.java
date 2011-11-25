package Model;

//..begin "Imports"
import model.TeileTypET;
//..end "Imports"

public class Teil_Stammdaten {

    //Deklaration der Variablen
    private String materialgruppe;
    private String zeichnungsnummer;
    private double preis;
    private String bezeichnung;
    private String bemerkung;
    private int max_anzahl_klein;
    private int max_anzahl_mittel;
    private int max_anzahl_gross;
    private String baugruppe;
    private TeileTypET typ;
    private int id;

    //Konstruktor
    public Teil_Stammdaten(int id, TeileTypET typ, String baugruppe, String zeichnungsnummer,
            double preis, String bezeichnung, String bemerkung, int max_anzahl_klein, int max_anzahl_mittel, int max_anzahl_gross, String materialgruppe) {

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

    // getter Methoden
    public int get_id() {
        return id;
    }

    public TeileTypET get_Teiletyp() {
        return typ;
    }

    public String get_Materialgruppe() {
        return materialgruppe;
    }

    public String get_Zeichnungsnummer() {
        return zeichnungsnummer;
    }

    public double get_Preis() {
        return preis;
    }

    public String get_Bezeichnung() {
        return bezeichnung;
    }

    public String get_Bemerkung() {
        return bemerkung;
    }

    public int get_max_anz_klein() {
        return max_anzahl_klein;
    }

    public int get_max_anz_mittel() {
        return max_anzahl_mittel;
    }

    public int get_max_anz_gross() {
        return max_anzahl_gross;
    }

    public String get_Baugruppe() {
        return baugruppe;
    }

    // setter Methoden
    public void set_id(int neue_id) {

        id = neue_id;
    }

    public void set_Teiletyp(TeileTypET neuer_typ) {
        typ = neuer_typ;
    }

    public void set_Materialgruppe(String neue_msgrp) {
        materialgruppe = neue_msgrp;
    }

    public void set_zeichnungsnummer(String neue_znr) {
        zeichnungsnummer = neue_znr;
    }

    public void set_preis(double neuer_preis) {
        preis = neuer_preis;
    }

    public void set_Bezeichnung(String neue_bez) {
        bezeichnung = neue_bez;
    }

    public void set_bemerkung(String neue_bem) {
        bemerkung = neue_bem;
    }

    public void set_max_anzahl_klein(int n_mak) {
        max_anzahl_klein = n_mak;
    }

    public void set_max_anzahl_mittel(int n_mam) {
        max_anzahl_mittel = n_mam;
    }

    public void set_max_anzahl_gross(int n_mag) {
        max_anzahl_gross = n_mag;
    }

    public void set_Baugruppe(String neue_bgrp) {
        baugruppe = neue_bgrp;
    }
}