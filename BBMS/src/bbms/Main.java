/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bbms;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import gui.Login;
import java.io.IOException;
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
            UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
            //MedicalOfficer bbms = new MedicalOfficer();
            //bbms.setVisible(true);
            Login bbms = new Login();
            bbms.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
