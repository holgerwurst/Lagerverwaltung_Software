/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolTip;

/**
 *
 * @author Arthas
 */
public class LagerTextfield extends JTextField {

    class LagerKeyListener implements KeyListener {

        private String Zeichenfolge;
        private String Fehlerbeschreibung;
        private LagerTextfield Sender;
        private JToolTip falscheEingabe = new JToolTip();
        private AllowedSequences seq;

        public LagerKeyListener(AllowedSequences seq, LagerTextfield Sender) {
            this.seq = seq;
            this.Zeichenfolge = seq.getSequence();
            this.Fehlerbeschreibung = seq.getDesc();
            this.Sender = Sender;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            char key = e.getKeyChar();
            if(key=='\'')e.setKeyChar('"');
            
            if (Zeichenfolge == null) {
                return;
            }
            boolean isValid = false;
            
            if (isCharInString(key, Zeichenfolge) || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE) {
                isValid = true;
            }
            //nachbehandling spezialfälle begin
            if (isValid) {
                switch (seq) {
                    case PREIS: {
                        int i2=0;//durchlaufzähler der for-schleife die ja rückwärts zählen muss
                        boolean tz=false;//war schon ein trennzeichen im string?
                        for (int i = Sender.getText().length() - 1; i >= 0; i--)//rückwärtiger durchlauf durch den text im aufrufenden textfeld
                        {
                            //System.out.println(i2+""+tz);
                            i2++;
                            if((',' == Sender.getText().charAt(i)
                                    || '.' == Sender.getText().charAt(i))){
                                if(tz==true){
                                    isValid=false;
                                    break;//for break
                                }else{
                                    tz=true;
                                }
                            }
                            if (i2>2
                                    && (',' == Sender.getText().charAt(i)
                                    || '.' == Sender.getText().charAt(i))) {
                                isValid = false;//mehr als 2 nachkomma erkannt => nicht valide
                            //System.out.println(""+i2+isValid);
                                break; //for break
                            }                                                      
                        }
                        //System.out.println("-");
                        i2=0;
                        break;//case break           

                    }

                    case ZIFFERN: {
                        break;
                    }
                    case ALL: {
                        
                        break;
                    }


                    default:
                        break;

                }
            }
            //nachbehandlung spezialfälle end


            if (!isValid) {
                e.setKeyChar(KeyEvent.CHAR_UNDEFINED);
                /*
                 * ToolTipManager.sharedInstance().setInitialDelay(0);
                 * falscheEingabe = Sender.createToolTip();
                 * falscheEingabe.setTipText("Falsche Eingabe");
                 * falscheEingabe.setToolTipText("BLA");
                 * falscheEingabe.setVisible(true); Sender.add(falscheEingabe);
                 * falscheEingabe.setComponent(Sender);
                 */
                JOptionPane.showMessageDialog(Sender, Fehlerbeschreibung, "Falsche Eingabe", 1);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    /**
     * Die Methode in String ist zu blöd
     *
     * @param c
     * @param s
     * @return giebt true wenn der char im string enthalten ist
     */
    private boolean isCharInString(char c, String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (c == s.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public enum AllowedSequences {

          ALL(null, null),
        ALPHA("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ", "Bitte nur Buchstaben eingeben."),
        PREIS("1234567890,.", "Hier sind nur Zahlen und hoechstens 2 Nachkommastellen erlaubt."),
        ZIFFERN("0987654321", "Bitte nur ganze Zahlen eingeben.");
        private String sequence;
        private String desc;

        AllowedSequences(String sequence, String desc) {
            this.sequence = sequence;
            this.desc = desc;
        }

        public String getSequence() {
            return sequence;
        }

        public String getDesc() {
            return desc;
        }

        public void setRule(AllowedSequences seq) {
            this.sequence = seq.sequence;
            this.desc = seq.desc;
        }
    }
    private LagerKeyListener akl;

    /**
     * Legt nachträglich fest um welche Art LagerTextfield es sich handlen soll.
     * zb. Preisfeld Diese Methode sollte benutzt werden nachdem der leere
     * Konstruktor LagerTextfield() aufgerufen wurde.
     *
     * @param seq Kann folgende werte annehmen:ZIFFERN =>0987654321, PREIS, NUM,
     * ALPHA, ALL das sind halt die enums...
     */
    public void setRegeln(AllowedSequences seq) {
        akl.seq.setRule(seq);
    }

    /**
     * Einfacher Konstruktor benutze setRegeln(AllowedSequences seq) um das
     * Verhalten festzulegen.
     *
     */
    public LagerTextfield() {
        this(AllowedSequences.ALL);
    }

    public LagerTextfield(AllowedSequences seq) {
        super();
        akl = new LagerKeyListener(seq, this);
        this.addKeyListener(akl);
    }
    
    
    
    
            
}
