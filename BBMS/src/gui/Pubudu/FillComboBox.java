/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.Pubudu;
import connection.DBConnection;
import connection.DBHandler;
import controller.OrganizerController;
import javax.swing.JComboBox;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
*
 * @author Amila Karunathilaka
 */
public class FillComboBox {
    
    
  /*  public static void fillComboComboDatabaseWithSomeItem(JComboBox combo,String table,String column,String... otherItems){
    	combo.removeAllItems();
        for (String string : otherItems) {
            combo.addItem(string);
        }
        try {
            ResultSet rst = DBHandle.getData(DBConn.getConnectionToDB(),"Select * from "+table);
            while (rst.next()) {
                Object ob = rst.getString(column);
                combo.addItem(ob);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public static void fillComboComboDatabaseNextCombo(JComboBox combo,String table,String searchcolumn,String column,JComboBox nextCmb){
        
    	String value=combo.getSelectedItem()+"";
        try {
            ResultSet rst = DBHandle.getData(DBConn.getConnectionToDB(),"Select * from "+table+" where "+searchcolumn+"='"+value+"'");
            if (rst.next()) {
                nextCmb.setSelectedItem(rst.getString(column));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    public static void fillComboComboDatabaseFillter(JComboBox combo,String table,String filltertable,String column,String filltercolumn,JComboBox cmbFillter){
    	combo.removeAllItems();
        String value=cmbFillter.getSelectedItem()+"";
        try {
            ResultSet rst = DBHandle.getData(DBConn.getConnectionToDB(),"Select a."+column+" from "+table+" a NATURAL JOIN "+filltertable+" b where b."+filltercolumn+"='"+value+"'");
            while (rst.next()) {
                Object ob = rst.getString(column);
                combo.addItem(ob);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }*/
}
