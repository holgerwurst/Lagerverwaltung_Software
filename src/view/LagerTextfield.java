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
            this.seq=seq;
            this.Zeichenfolge = seq.getSequence();
            this.Fehlerbeschreibung = seq.getDesc();
            this.Sender = Sender;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if (Zeichenfolge == null) {
                return;
            }
            char key = e.getKeyChar();
            boolean isValid = false;
            for (int i = 0; i < Zeichenfolge.length(); i++) {
                if (key == Zeichenfolge.charAt(i) || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_BACK_SPACE) {
                    isValid = true;
                    break;
                }
            }
            //nachbehandling spezialfälle begin
            switch(seq)
            {
                case PREIS:
                {
                    for(int i=Sender.getText().length()-1;i>=0;i--)//rückwärtiger durchlauf durch den text im aufrufenden textfeld
                    {
                        if(Sender.getText().length()-i>2
                                &&(','==Sender.getText().charAt(i) 
                                    ||'.'==Sender.getText().charAt(i)))
                        {
                            isValid=false;//mehr als 2 nachkomma erkannt => nicht valide
                            break; //for break
                        }else
                        {
                            isValid=true;
                            break;//for break
                        }
                    }
                    break;//case break           
                    
                }
                        
                case ZIFFERN:
                {
                    break;
                }
                    
                default: break;
                                                
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

    public enum AllowedSequences {

        ALL(null, null),
        ALPHA("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ", "nur Buchstaben erlaubt"),
        NUM("0123456789,.", "nur zahlen erlaubt"),
        PREIS("1234567890,.","nur Zahlen und höchstends 2 Nachkommastellen erlaubt"),
        ZIFFERN("0987654321","nur ganze Zahlen erlaubt");
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
        public void setRule(AllowedSequences seq){
            this.sequence=seq.sequence;
            this.desc=seq.desc;
        }
    }
    private LagerKeyListener akl;
    
    /**Legt nachträglich fest um welche Art LagerTextfield es sich handlen soll. zb. Preisfeld
     * Diese Methode sollte benutzt werden nachdem der leere Konstruktor LagerTextfield() aufgerufen wurde.
     * 
     * @param seq Kann folgende werte annehmen:ZIFFERN =>0987654321, PREIS, NUM, ALPHA, ALL
     * das sind halt die enums...
     */
    public void setRegeln(AllowedSequences seq){
        akl.seq.setRule(seq);
    }

    /**Einfacher Konstruktor 
     * benutze setRegeln(AllowedSequences seq) um das Verhalten festzulegen.
     * 
     */
    public LagerTextfield() {
        this(AllowedSequences.ALL);
    }

    public void BLABLUBB(String bla) {
    }

    public LagerTextfield(AllowedSequences seq) {
        super();
        akl = new LagerKeyListener(seq, this);
        this.addKeyListener(akl);
    }
}
