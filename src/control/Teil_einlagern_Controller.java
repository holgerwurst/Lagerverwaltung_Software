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
import model.Select_Allgemein;
import model.Select_Lagerbestandskonto;
import model.Select_Stammdaten;
import model.WarteKlasse;
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
    private Select_Allgemein sa = new Select_Allgemein();
    private Select_Lagerbestandskonto lagerbes;
    private Select_Lagerbestandskonto lagerbes2;
   

    public Teil_einlagern_Controller(Übersicht_Lagerverwaltung lv) {
        this.lv = lv;
        
    }

    public void einlagern(int id) {
        table = lv.table_einlagern;
        String max = "";

        try {

            lagerbes = new Select_Lagerbestandskonto();
            String[] fach = lagerbes.get_Fachnummer_ausDB(id);
            //  String[] a = new String[0];
            String[] menge = new String[0];
            int flag = 0;

            for (int i = 0; i < fach.length; i++) {

                if (fach[i].endsWith("K")) {
              
                    flag = 1;
                    System.out.println("kleines fach " + fach[i]);
                    // System.out.println("Menge " + menge[i]);

                } else if (fach[i].endsWith("M")) {

                    flag = 2;

                } else if (fach[i].endsWith("G")) {

                    flag = 3;
                }

            }

            model.addColumn("Fach", fach);


            if (flag == 1) {
                String[] array = new String[1];

                menge = sa.get_menge_ausDB("max_anz_klein", id);
                max = st.get_max_anzahl_klein_ausDB(id);


                for (int i = 0; i < 1; i++) {
                    array[i] = max;
                    System.out.println("Array   " + array[i]);
                }
                model.addColumn("aktuelle Menge", menge);
                model.addColumn("Maximale Anzahl kleines Fach", array);


            } else if (flag == 2) {
                String[] array = new String[1];

                menge = sa.get_menge_ausDB("max_anz_mittel", id);
                max = st.get_max_anzahl_mittel_ausDB(id);

                for (int i = 0; i < 1; i++) {
                    array[i] = max;
                    //     System.out.println("Array   " + array[i]);
                }
                model.addColumn("aktuelle Menge", menge);
                model.addColumn("Maximale Anzahl mittleres Fach", array);

            } else if (flag == 3) {
                String[] array = new String[1];

                menge = sa.get_menge_ausDB("max_anz_gross", id);
                max = st.get_max_anzahl_gross_ausDB(id);

                for (int i = 0; i < 1; i++) {
                    array[i] = max;
                    //   System.out.println("Array   " + array[i]);
                }
                model.addColumn("aktuelle Menge", menge);
                model.addColumn("Maximale Anzahl großes Fach", array);
                
            }







            //  a = sa.get_fachnummer_ausDB("max_anz_klein");
            //   System.out.println("kleines fach   " + a);
            //   }               
            //    model.addColumn("Fach", a);

            String anz = lv.lagerTextfield1.getText();


            /*
             * if (anz.endsWith("K")) {
             *
             * max = st.get_max_anzahl_klein_ausDB(id);
             *
             * // for(int i=0;i<1;i++){
             *
             * // String[] menge = lagerbes.get_Menge_ausDB("FH2Z13S04K"); //
             * System.out.println("Menge "+menge[i]); // } } else if
             * (anz.endsWith("M")) {
             *
             * max = st.get_max_anzahl_mittel_ausDB(id);
             *
             * } else if (anz.endsWith("G")) {
             *
             * max = st.get_max_anzahl_gross_ausDB(id);
             *
             * }
             */
            int zahl = cv.StringTOint(max);

            System.out.println("Eingelagert: Teil mit ID " + id + " ins Fach " + anz + "  und  Menge  " + zahl + "   eingelagert.");
            fachtest(max, id);
            //  String a = lv.menge_textfeld_einlagern.getText();
            //  int einzulagern = cv.StringTOint(a);

            //   System.out.println("zahl"+a);

            // int neueMenge = einzulagern - zahl;
            //    String text = String.valueOf(neueMenge);
            //    lv.label_menge_übrig.setText(text);

            //  Lagerbestandskonto lbk = new Lagerbestandskonto(anz, id, zahl, null, null);
            //   dbs.insert_lagerbestandskonto(lbk);
            // dbs.update_lagerfachstamm(anz, true);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void add(String text) {
    }

    public void fachtest(String max, int id) {

        /*
         * try {
         *
         * lagerbes = new Select_Lagerbestandskonto();
         *
         * String[] lager = lagerbes.get_Fachnummer_ausDB(id); String[] menge =
         * new String[0]; // ArrayList<String> mengear = new
         * ArrayList<String>(0);
         *
         * String[] menge2 = new String[0]; model.addColumn("fachnummer",
         * lager);
         *
         * for (int i = 0; i < lager.length; i++) { System.out.println("Fach " +
         * lager[i]); // System.out.println("Länge " + lager.length); lagerbes =
         * new Select_Lagerbestandskonto(); lagerbes2 = new
         * Select_Lagerbestandskonto();
         *
         * try { // lagerbes = new Select_Lagerbestandskonto(); menge = new
         * String[lager.length]; menge2 = new String[lager.length];
         *
         * menge = lagerbes.get_Menge_ausDB(lager[0]); menge2 =
         * lagerbes2.get_Menge_ausDB(lager[1]);
         *
         * // mengear.add(menge[0]); //System.out.println("Mengearraylist " +
         * mengear.get(i)); //System.out.println("Menge " + menge[0]); //
         * System.out.println("Menge2 " + menge2[0]);
         *
         * // lagerbes = new Select_Lagerbestandskonto(); // for (int j = 0; j
         * < mengear.size(); j++) { // menge[j] = mengear.get(0); // } * } catch
         * (SQLException ex) { }
         *
         * }
         * String[] test = new String[2];
         *
         * for (int j = 0; j < 2; j++) { test[0] = menge[0]; test[1] =
         * menge2[0]; }
         *
         * model.addColumn("Menge", test);
         *
         * } catch (Exception e) { System.out.println("Teil noch nicht
         * eingelagert"); }
         */


        //  model.addColumn("Fachnummer", max.toCharArray());
        //model.addColumn("Fachnummer",(a) ta.getfachnummern());
        // model.addColumn("Menge", ta.getaktuelle_menge());

        table.setModel(model);

        create_tabel();

    }

    public void create_tabel() {
        model = new javax.swing.table.DefaultTableModel(0, 0) {

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 2) {
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };

    }
}
