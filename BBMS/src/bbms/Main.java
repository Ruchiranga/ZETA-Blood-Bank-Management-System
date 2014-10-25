/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbms;

import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import gui.Login2;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException; 

/**
 *
 * @author Anuradha
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
            //UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
            UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());

            //MedicalOfficer bbms = new MedicalOfficer();
            //bbms.setVisible(true);
            Login2 bbms = new Login2();
            bbms.setVisible(true);
        }catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
