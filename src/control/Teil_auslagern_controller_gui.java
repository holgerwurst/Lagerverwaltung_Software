/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Select_Stammdaten;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Stephan
 */
public class Teil_auslagern_controller_gui {

   
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
