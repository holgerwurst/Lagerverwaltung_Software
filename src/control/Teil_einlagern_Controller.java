/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.DB_schreiben;
import model.Lagerbestandskonto;
import model.Select_Lagerbestandskonto;
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
    private Select_Lagerbestandskonto lagerbes;
    private Select_Lagerbestandskonto lagerbes2;

    public Teil_einlagern_Controller(Übersicht_Lagerverwaltung lv) {
        this.lv = lv;
    }

    public void einlagern(int id) {
        table = lv.table_einlagern;
        String max = "";

        model = new javax.swing.table.DefaultTableModel(5, 3) {

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 2) {
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };



        try {

            String anz = lv.lagerTextfield1.getText();


            if (anz.endsWith("K")) {

                max = st.get_max_anzahl_klein_ausDB(id);

                //     for(int i=0;i<1;i++){

                //    String[] menge = lagerbes.get_Menge_ausDB("FH2Z13S04K");
                //             System.out.println("Menge  "+menge[i]);
                //      }
            } else if (anz.endsWith("M")) {

                max = st.get_max_anzahl_mittel_ausDB(id);

            } else if (anz.endsWith("G")) {

                max = st.get_max_anzahl_gross_ausDB(id);

            }
            int zahl = cv.StringTOint(max);

            System.out.println("Eingelagert: Teil mit ID " + id + " ins Fach " + anz + "  und  Menge  " + zahl + "   eingelagert.");

            //  String a = lv.menge_textfeld_einlagern.getText();
            //  int einzulagern = cv.StringTOint(a);

            //   System.out.println("zahl"+a);

            // int neueMenge = einzulagern - zahl;
            //    String text = String.valueOf(neueMenge);
            //    lv.label_menge_übrig.setText(text);

            //  Lagerbestandskonto lbk = new Lagerbestandskonto(anz, id, zahl, null, null);
            //   dbs.insert_lagerbestandskonto(lbk);            
        } catch (SQLException e) {
        }

        try {
            lagerbes = new Select_Lagerbestandskonto();

            String[] lager = lagerbes.get_Fachnummer_ausDB(id);
            String[] menge = new String[0];
            ArrayList<String> mengear = new ArrayList<String>(0);

            String[] menge2 = new String[0];
            model.addColumn("fachnummer", lager);

            for (int i = 0; i < lager.length; i++) {
                System.out.println("Fach  " + lager[i]);
                //           System.out.println("Länge  " + lager.length);
                lagerbes = new Select_Lagerbestandskonto();
                lagerbes2 = new Select_Lagerbestandskonto();

                try {
                    // lagerbes = new Select_Lagerbestandskonto();
                    menge = new String[lager.length];
                    menge2 = new String[lager.length];
                    
                    menge = lagerbes.get_Menge_ausDB(lager[0]);
                    menge2 = lagerbes2.get_Menge_ausDB(lager[1]);
                  
                   // mengear.add(menge[0]);

                    System.out.println("Menge  " + menge[0]);
                      System.out.println("Menge2  " + menge2[0]);

                    // lagerbes = new Select_Lagerbestandskonto();
                //    for (int j = 0; j < mengear.size(); j++) {
               //         menge[j] = mengear.get(0);
               //     }          
                    
                    
                } catch (SQLException ex) {
                }

            }
String[] test = new String[2];

for (int j = 0; j < 2; j++) {
                     test[0] = menge[0];
                     test[1] = menge2[0];
                }

            model.addColumn("Menge", test);

        } catch (SQLException e) {
        }







        String[] array = new String[1];

        for (int i = 0; i < 1; i++) {
            array[i] = max;
            System.out.println("Array   " + array[i]);

            //  model.setValueAt(array[i], i, 0);
        }


        model.addColumn("max", array);


        //  model.addColumn("Fachnummer", max.toCharArray());
        //model.addColumn("Fachnummer",(a) ta.getfachnummern());
        // model.addColumn("Menge", ta.getaktuelle_menge());

        table.setModel(model);


    }
}
