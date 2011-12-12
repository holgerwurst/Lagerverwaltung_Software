/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package control;

import model.Lagerbestandskonto;
import model.Select_Stammdaten;
import view.Übersicht_Lagerverwaltung;

/**
 *
 * @author Matthias Sommer
 */
public class Teil_einlagern_Controller {

    private Übersicht_Lagerverwaltung lv = new Übersicht_Lagerverwaltung();
    private convert cv = new convert();
    private Select_Stammdaten st = new Select_Stammdaten();

    public void einlagern(int id) {

       // System.out.println("ID =" + id);
        try {

            String max = st.get_max_anzahl_klein_ausDB(id);
            int zahl = cv.StringTOint(max);

            //System.out.println(zahl);
            System.out.println("Eingelagert   " + id + "    Menge  " + zahl);

         /*  if(lv.menge_textfeld_einlagern.getText().isEmpty()){
               System.out.println("ssss");
           }*/
            String a = lv.menge_textfeld_einlagern.getText();
         //   int einzulagern = cv.StringTOint(lv.menge_textfeld_einlagern.getText());
           
             System.out.println("zahl"+a);
       //     System.out.println(einzulagern);
            
       ///     int neueMenge = einzulagern-zahl;
       //       String text = String.valueOf(neueMenge);
          //   lv.label_menge_übrig.setText(text);

            // Lagerbestandskonto lbk = new Lagerbestandskonto(fnr, idt, neue_menge, ansgrund, null);

        } catch (ClassNotFoundException e) {
        }


    }
}
