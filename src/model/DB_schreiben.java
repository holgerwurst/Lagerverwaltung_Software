/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package model;

/**
 *
 * @author Franz Selent
 */
public class DB_schreiben {

    Datenbankverbindung db = new Datenbankverbindung();

    /**
     *  Alle Methoden die zum schreiben, löschen oder aktualisieren in
     *  der DB notwendig sind.
     */
    
    public void insert_teilestamm(Teil_Stammdaten teil) throws ClassNotFoundException {
        db.connect_schreiben_teilestamm("insert", teil);
        db.disconnect();
    }

    public void update_teilestamm(Teil_Stammdaten teil) throws ClassNotFoundException {
        db.connect_schreiben_teilestamm("update", teil);
        db.disconnect();
    }

    public void delete_teilestamm(int id) throws ClassNotFoundException {
        db.connect_löschen(id, "Teilestammdaten", null);
        db.disconnect();
    }

    public void insert_lagerbestandskonto(Lagerbestandskonto lagerbestand) throws ClassNotFoundException {
        db.connect_schreiben_lagerbestandskonto("insert", lagerbestand);
        db.disconnect();
    }

    public void delete_lagerbestandskonto(int id, String fachnummer) throws ClassNotFoundException {
        db.connect_löschen(id, "Lagerbestandskonto", fachnummer);
        db.disconnect();
    }

    public void update_lagerbestand(Lagerbestandskonto lagerbestand) throws ClassNotFoundException {
        db.connect_schreiben_lagerbestandskonto("update", lagerbestand);
        db.disconnect();
    }

    public void insert_freieId(int id) throws ClassNotFoundException {
        db.connect_schreiben_freieid(id);
        db.disconnect();
    }
    
    public String[] get_alle_freie_IDs(){
            try {
            db.connect("Select IDs from FreieIDs", "IDs");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }
        
    

    public void delete_freie_id(int id) throws ClassNotFoundException {
        db.connect_löschen(id, "FreieIDs", null);
        db.disconnect();
    }

    public void update_lagerfachstamm(String fach, boolean belegung) throws ClassNotFoundException {
        db.connect_schreiben_lagerfachstamm(fach, belegung);
        db.disconnect();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Teil_Stammdaten ts = new Teil_Stammdaten(14, TeileTypET.kaufteile, "zeich1", "matgrp1", 12.98, "hugo", "bier", "erstes testteil", 12, 24, 36);
        Teil_Stammdaten ts2 = new Teil_Stammdaten(12, TeileTypET.unfertige_Baugruppen, "zeich2", "matgrp2", 22.98, "h2u2go", "2bier", "2 erstes testteil", 122, 242, 362);
        DB_schreiben dbs = new DB_schreiben();
        //dbs.insert_teilestamm(ts);
        //dbs.update_teilestamm(ts2);
        //dbs.delete_teilestamm(13);
        //dbs.delete_teilestamm(14);

        Lagerbestandskonto lbk = new Lagerbestandskonto("FH1Z1S1K", 13, 50, "brauchen wir", null);
        Lagerbestandskonto lbk2 = new Lagerbestandskonto("FH1Z1S2K", 13, 50, "brauchen asdwir", null);
        Lagerbestandskonto lbk3 = new Lagerbestandskonto("FH1Z1S2K", 13, 1000, "brauchen sdfwir", null);
        //dbs.insert_lagerbestandskonto(lbk);
        //dbs.insert_lagerbestandskonto(lbk2);
        //dbs.insert_lagerbestandskonto(lbk3);
        //dbs.delete_lagerbestandskonto(13, "FH1Z1S2K");
        //dbs.update_lagerbestand(lbk3);
        //dbs.update_lagerfachstamm("FH1Z1S1K", false);
    }
}
