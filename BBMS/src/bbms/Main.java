/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bbms;

import connection.DBConnection;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.common.Login;

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

            try {
                UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Connection connection = DBConnection.getConnectionToDB();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Failed to load the MySQL Driver. Please make sure the necessary files are available.", "Driver not found", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Failed to connect to the MySQL Server. Please make sure the server is up and running.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            Login bbms = new Login();
            bbms.setVisible(true);

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
