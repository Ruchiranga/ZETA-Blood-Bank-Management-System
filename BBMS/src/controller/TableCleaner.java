/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ruchiranga
 */
public class TableCleaner {
    public static void clearTable(DefaultTableModel availabilityTable) {
        while(availabilityTable.getRowCount()>0){
            availabilityTable.removeRow(availabilityTable.getRowCount()-1);
        }
    }
    
}
