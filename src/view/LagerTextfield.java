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

        public LagerKeyListener(String Zeichenfolge, String Fehlerbeschreibung, LagerTextfield Sender) {
            this.Zeichenfolge = Zeichenfolge;
            this.Fehlerbeschreibung = Fehlerbeschreibung;
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
        NUM("0123456789,.", "nur zahlen erlaubt");
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
    }
    private LagerKeyListener akl;

    public LagerTextfield() {
        this(AllowedSequences.ALL);
    }

    public void BLABLUBB(String bla) {
    }

    public LagerTextfield(AllowedSequences seq) {
        super();
        akl = new LagerKeyListener(seq.sequence, seq.desc, this);
        this.addKeyListener(akl);
    }
}
