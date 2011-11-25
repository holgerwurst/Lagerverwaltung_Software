/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package model;

/**
 *
 * @author Wayne
 */
public class Select_Stammdaten {

    Datenbankverbindung db = new Datenbankverbindung();

       public String get_ID_ausDB(String bez) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "id", "bezeichnung", bez);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Teiletyp_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "typ", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Materialgruppe_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        //String id_temp= String.valueOf(id);
        temp = db.connect("Teilestammdaten", "materialgruppe", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Zeichnungsnummer_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "zeichnungsnummer", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Preis_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "preis", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Bezeichnung_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "bezeichnung", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Bemerkung_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "bemerkung", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_max_anzahl_klein_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "max_anz_klein", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_max_anzahl_mittel_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "max_anz_mittel", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_max_anzahl_gross_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "max_anz_gross", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String get_Baugruppe_ausDB(String id) throws ClassNotFoundException {
        String temp = "";
        temp = db.connect("Teilestammdaten", "bemerkung", "id", id);
        db.disconnect();
        //System.out.println(temp);
        return temp;
    }

    public String[] get_spalte_id_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "id");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
    
    public String[] get_spalte_Teiletyp_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "typ");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
    
    public String[] get_spalte_zeichnungsnummer_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "zeichnungsnummer");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
    
    public String[] get_spalte_preis_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "preis");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
    
    public String[] get_spalte_bezeichnung_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "bezeichnung");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
   
    public String[] get_spalte_materialgruppe_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "materialgruppe");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
    
    public String[] get_spalte_baugruppe_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "baugruppe");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
    
    public String[] get_spalte_bemerkung_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "bemerkung");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
    
    public String[] get_spalte_max_anzahl_klein_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "max_anz_klein");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
    
    public String[] get_spalte_max_anzahl_mittel_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "max_anz_mittel");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
    
    public String[] get_spalte_max_anzahl_gross_ausDB() throws ClassNotFoundException {
        String[] tempar;
        tempar = db.connect_ganze_spalte("Teilestammdaten", "max_anz_gross");
        db.disconnect();
        //System.out.println(temp);
        return tempar;
    }
}
