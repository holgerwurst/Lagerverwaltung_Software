/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package model;

/**
 *
 * @author Wayne
 */
public class Select_Lagerbestandskonto {

    Datenbankverbindung db = new Datenbankverbindung();
    
    public String get_ID_ausDB(String fachnummer) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "teile_ID", "fachnummer", fachnummer);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Fachnummer_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "fachnummer", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Menge_ausDB(String fachnummer) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "menge", "fachnummer", fachnummer);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Anschaffungsgrund_ausDB(String fachnummer) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "anschaffungsgrund", "fachnummer", fachnummer);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Haltbarkeitsdatum_ausDB(String fachnummer) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "haltbarkeitsdatum", "fachnummer", fachnummer);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String[] get_spalte_fachnummer_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerbestandskonto", "fachnummer");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_teile_ID_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerbestandskonto", "teile_ID");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_menge_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerbestandskonto", "menge");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_anschaffungsgrund_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerbestandskonto", "anschaffungsgrund");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }

    public String[] get_spalte_haltbarkeitsdatum_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Lagerbestandskonto", "haltbarkeitsdatum");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
}
