/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.sql.SQLException;
import model.Select_Lagerbestandskonto;
import model.Lagerbestandskonto;
import model.DB_schreiben;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Select_Stammdaten;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Herold
 */
public class Teil_auslagern_controller {

    int id;
    String[] fachnummern;
    String[] aktuelle_menge;
    String[] anschaffungsgrund;
    int auszulagernde_menge;
    

    public void auslagern_vorbereitung(int idt) throws SQLException {

        id = idt;

        Select_Lagerbestandskonto sl = new Select_Lagerbestandskonto();
        Select_Lagerbestandskonto sl2 = new Select_Lagerbestandskonto();
        Select_Lagerbestandskonto sl3 = new Select_Lagerbestandskonto();

        fachnummern = sl.get_Fachnummer_ausDB(id);

        aktuelle_menge = new String[fachnummern.length];

        for (int i = 0; i < fachnummern.length; i++) {


            aktuelle_menge = sl2.get_Menge_ausDB(fachnummern[i]);

            anschaffungsgrund = sl3.get_Anschaffungsgrund_ausDB(fachnummern[i]);

            //System.out.println("Fachnummern: " + fachnummern[i] + ", Menge:" + aktuelle_menge[i] + ", Ans.grund: " + anschaffungsgrund[i]);
        }
    }
/**
 * 
 * Kontrolle Anzahl vorhandener Faecher.
 * Update des Lagerfachstammes, der Faecher, in denen Menge auf 0 fiel.
 * 
 * retunr 0 - letztes Fach fiel auf 0 -> Konto löschen und Teil entfernen anbieten.
 * return 1 - 
 * 
 * @param id
 * @param laenge
 * @param fnr
 * @throws ClassNotFoundException 
 */
    public int fachfreigeben(int id, int laenge, String fnr) throws ClassNotFoundException {
        
        DB_schreiben dbs = new DB_schreiben();

        if (laenge == 1) {
            
            dbs.delete_lagerbestandskonto(id, fnr);
            dbs.update_lagerfachstamm(fnr, false);
            return 0;
            
  
        } else if (laenge > 1) {
            
            dbs.delete_lagerbestandskonto(id, fnr);
            dbs.update_lagerfachstamm(fnr, false);
            return 1;
        }
        
        return 2;
    }

    /**
     * gibt ein Array mit den aktuellen Fachnummern zurück
     * @return 
     */
    public String[] getfachnummern() {
        return fachnummern;
    }
/**
 * gibt die aktuelle ID zurück
 * @return 
 */
    public int getID() {
        return id;
    }
/**
 * gibt ein Array mit den aktuellen Mengen der Faecher zurueck
 * @return 
 */
    public String[] getaktuelle_menge() {
        return aktuelle_menge;
    }
/**
 * gibt ein Array mit den aktuellen Anschaffungsgruenden der Teile zurueck
 * @return 
 */
    public String[] getanschaffungsgrund() {

        return anschaffungsgrund;
    }

    /**
     * 
     * Es wird ein Objekt vom Typ Lagerbestandskonto 
     * aus den uebergebenen Werten erstellt.
     * 
     * @param fnr
     * @param idt
     * @param neue_menge
     * @param ansgrund
     * @return 
     */
    public Lagerbestandskonto erstelle_lbk(String fnr, int idt, int neue_menge, String ansgrund) {
        
        Lagerbestandskonto lbk = new Lagerbestandskonto(fnr, idt, neue_menge, ansgrund, null);

        return lbk;

    }

    /**
     * 
     * Das uebergebenen Lagerbestandskonto wird in die Datenbank geschrieben.
     * 
     * @param lbk
     * @throws ClassNotFoundException 
     */
    public void auslagern_durchfuehrung(Lagerbestandskonto lbk) throws ClassNotFoundException {

        DB_schreiben dbs = new DB_schreiben();

        dbs.update_lagerbestand(lbk);
    }

    
    
   
    /**
     * 
     * Die statische Methode fuehrt beim Ausloesen des ausfuehren_button
     * das Auslagern aus und aktualiesiert die jTable.
     * 
     * 
     * @param ul 
     */
    
     public static void auslagern_ausfuehren_button(Übersicht_Lagerverwaltung ul) 
    {
   
        ul.pr = new Pruefen_Controller();
        String strtemp = ul.auslagerTextfield_fnr.getText();

        ul.cv = new convert();
    
        int inttemp = ul.cv.StringTOint(ul.auslagerTextfield_mengeauslagern.getText());

        int fachnrstelle = ul.pr.fachnummerkorrekt(ul.ta.getfachnummern(), strtemp);

        if (ul.pr.fachnummerkorrekt(ul.ta.getfachnummern(), strtemp) != 99999) {

            if (ul.pr.auszulagernde_menge_pruefen(ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]), inttemp) == 3) {

                int neumenge = ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]) - inttemp;

                try {

                    ul.ta.auslagern_durchfuehrung(ul.ta.erstelle_lbk(ul.ta.getfachnummern()[fachnrstelle], ul.ta.getID(), neumenge, ul.ta.getanschaffungsgrund()[fachnrstelle]));

                    int tmp = ul.ta.getID();

                    ul.ta.auslagern_vorbereitung(tmp);

                    DefaultTableModel newmodel = new DefaultTableModel();

                    newmodel.addColumn("Fachnummer", ul.ta.getfachnummern());
                    newmodel.addColumn("Menge", ul.ta.getaktuelle_menge());

                    ul.auslager_jtable.setModel(newmodel);
                    
                    ul.auslagerLabel_Anzeige.setText("Es wurde(n) " + ul.auslagerTextfield_mengeauslagern.getText() + " Teil(e) ausgelagert.");

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (ul.pr.auszulagernde_menge_pruefen(ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]), inttemp) == 2) {
                
                int neumenge = ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]) - inttemp;

                try {

                    ul.ta.auslagern_durchfuehrung(ul.ta.erstelle_lbk(ul.ta.getfachnummern()[fachnrstelle], ul.ta.getID(), neumenge, ul.ta.getanschaffungsgrund()[fachnrstelle]));

                    if (ul.ta.fachfreigeben(ul.ta.getID(), ul.ta.getfachnummern().length, ul.ta.getfachnummern()[fachnrstelle]) == 1) {
                        
                        int tmp = ul.ta.getID();
                           

                        ul.ta.auslagern_vorbereitung(tmp);

                        DefaultTableModel newmodel = new DefaultTableModel();

                        newmodel.addColumn("Fachnummer", ul.ta.getfachnummern());
                        newmodel.addColumn("Menge", ul.ta.getaktuelle_menge());

                        ul.auslager_jtable.setModel(newmodel);

                    } else if (ul.ta.fachfreigeben(ul.ta.getID(), ul.ta.getfachnummern().length, ul.ta.getfachnummern()[fachnrstelle]) == 0) {
                        
                        int tmp = ul.ta.getID();
                        
                                              
                        ul.ta.auslagern_vorbereitung(tmp);

                        DefaultTableModel newmodel = new DefaultTableModel();

                        newmodel.addColumn("Fachnummer", ul.ta.getfachnummern());
                        newmodel.addColumn("Menge", ul.ta.getaktuelle_menge());

                        ul.auslager_jtable.setModel(newmodel);
                        
                        ul.auslagerLabel_Anzeige.setText("Es wurde(n) " + ul.auslagerTextfield_mengeauslagern.getText() + " Teil(e) ausgelagert.");
                        
                       int reply = JOptionPane.showConfirmDialog(ul.auslagern_AusfuehrenButton,"Möchten Sie das Teil löschen?","Meldung",JOptionPane.YES_NO_OPTION);
                       
                       if(reply == JOptionPane.YES_OPTION)
                       {
                           ul.entfernenIDTextfield.setText(ul.ta.getID() + "");
                           //entfernenJTabbedPane.
                           
                       }
                           
                                
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
            }
            else if(ul.pr.auszulagernde_menge_pruefen(ul.cv.StringTOint(ul.ta.getaktuelle_menge()[fachnrstelle]), inttemp) == 1)
            {
                JOptionPane.showMessageDialog(ul.auslagern_BestaetigenidButton, "Eingetragende Menge zu groß.");
            }
          
        
    }
        
        
    }
    
     /**
      * 
      * Die statische Methode zeigt alle Faecher und Mengenbelegungen
      * der gewaehlten ID in der jTable an.
      * 
      * 
      * @param ul 
      */
    public static void auslagern_bestaetigen_button(Übersicht_Lagerverwaltung ul)
    {
            ul.pr = new Pruefen_Controller();
        int id = Integer.parseInt( ul.auslagerTextfield_ID.getText());

        if ( ul.pr.pruefe_id(id) == true) {
            try {
                 ul.ta.auslagern_vorbereitung(id);

                 ul.sst = new Select_Stammdaten();
                 ul.auslager_bez_ausgabeLabel.setText( ul.sst.get_Bezeichnung_ausDB(id));

                 ul.sst = new Select_Stammdaten();
                 ul.ausl_BemerkungTextArea.setText( ul.sst.get_Bemerkung_ausDB(id));
                
                if( ul.ta.getfachnummern().length==0)
                {
                    int reply = JOptionPane.showConfirmDialog( ul.auslagern_AusfuehrenButton,"Teil nicht mehr eingelagert. Möchten Sie das Teil löschen?","Meldung",JOptionPane.YES_NO_OPTION);
                       
                       if(reply == JOptionPane.YES_OPTION)
                       {
                            ul.entfernenIDTextfield.setText( ul.ta.getID() + "");
                           //entfernenJTabbedPane. 
                       }
                }    
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }       
            DefaultTableModel newmodel = new DefaultTableModel();
             
            newmodel.addColumn("Fachnummer",  ul.ta.getfachnummern());
            newmodel.addColumn("Menge",  ul.ta.getaktuelle_menge());
 
             ul.auslager_jtable.setModel(newmodel); 
        }
    }
    
    
}