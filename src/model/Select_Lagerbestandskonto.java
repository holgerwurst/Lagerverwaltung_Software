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
    
    public String[] get_ID_ausDB(String fachnummer) throws ClassNotFoundException {
        try {
            db.connect("Select teile_id from Lagerbestandskonto where fachnummer ='" + fachnummer + "'", "teile_id");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }
    
    
    public String[] get_Fachnummer_ausDB(int id) throws ClassNotFoundException {
        try {
            db.connect("Select fachnummer from Lagerbestandskonto where teile_id =" + id + "", "fachnummer");
            db.disconnect();

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_Menge_ausDB(String fachnummer) throws ClassNotFoundException {
          try {
            db.connect("Select menge from Lagerbestandskonto where fachnummer ='" + fachnummer + "'", "menge");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_Anschaffungsgrund_ausDB(String fachnummer) throws ClassNotFoundException {
          try {
            db.connect("Select anschaffungsgrund from Lagerbestandskonto where fachnummer ='" + fachnummer + "'", "anschaffungsgrund");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_Haltbarkeitsdatum_ausDB(String fachnummer) throws ClassNotFoundException {
           try {
            db.connect("Select haltbarkeitsdatum from Lagerbestandskonto where fachnummer ='" + fachnummer + "'", "haltbarkeitsdatum");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_fachnummer_ausDB() throws ClassNotFoundException {
         try {
            db.connect("Select * from Lagerbestandskonto", "fachnummer");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_teile_ID_ausDB() throws ClassNotFoundException {
          try {
            db.connect("Select * from Lagerbestandskonto", "teile_id");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_menge_ausDB() throws ClassNotFoundException {
        try {
            db.connect("Select * from Lagerbestandskonto", "mange");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_anschaffungsgrund_ausDB() throws ClassNotFoundException {
           try {
            db.connect("Select * from Lagerbestandskonto", "anschaffungsgrund");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }

    public String[] get_spalte_haltbarkeitsdatum_ausDB() throws ClassNotFoundException {
           try {
            db.connect("Select * from Lagerbestandskonto", "haltbarkeitsdatum");
            db.disconnect();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC-Treiber nicht vorhanden");
        }
        return db.ar;
    }
}
