/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.DB_schreiben;
import model.Lagerbestandskonto;
import model.Select_Stammdaten;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Matthias Sommer
 */
public class Teil_einlagern_Controller {

    private Übersicht_Lagerverwaltung lv;
    private convert cv = new convert();
    private Select_Stammdaten st = new Select_Stammdaten();
    private DB_schreiben dbs = new DB_schreiben();
    private JTable table = new JTable();
    private DefaultTableModel model = new DefaultTableModel();
    
    public Teil_einlagern_Controller(Übersicht_Lagerverwaltung lv) {
        this.lv = lv;
    }
    

    public void einlagern(int id) {
            table = lv.table_einlagern;
        try {
              
            String anz = lv.lagerTextfield1.getText();
            String max="";
            
            if(anz.endsWith("K")){
                
            max= st.get_max_anzahl_klein_ausDB(id);
            
            }
            
            
              if(anz.endsWith("M")){
                  
           max= st.get_max_anzahl_mittel_ausDB(id);
        
            }
            
            
             if(anz.endsWith("G")){
                 
            max= st.get_max_anzahl_gross_ausDB(id);
            
            }
              int zahl = cv.StringTOint(max);
             
            System.out.println("Eingelagert: Teil mit ID " + id + " ins Fach "   +anz+   "  und  Menge  " + zahl+"   eingelagert.");
  
            String a = lv.menge_textfeld_einlagern.getText();
            int einzulagern = cv.StringTOint(lv.menge_textfeld_einlagern.getText());
           
          //   System.out.println("zahl"+a);
     
           int neueMenge = einzulagern-zahl;
           String text = String.valueOf(neueMenge);
           lv.label_menge_übrig.setText(text);
             
        Lagerbestandskonto lbk = new Lagerbestandskonto(anz, id, zahl, null, null);
        dbs.insert_lagerbestandskonto(lbk);
        
        model = new javax.swing.table.DefaultTableModel(5, 5) {

         @Override
    public Class<?> getColumnClass(int column) {
        if(column == 4){
            return Boolean.class;
        }
        return super.getColumnClass(column);
    }

    };

     table.setModel(model);
        DefaultTableModel deft = (DefaultTableModel) table.getModel();
        
        
        } catch (ClassNotFoundException e) {
        }


    }
}
