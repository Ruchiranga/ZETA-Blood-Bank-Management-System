/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Ruchi;

import Controller.RecordCounter;
import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ruchiranga
 */
public class BloodTypeController {

    public String[] getComponentList() throws SQLException, ClassNotFoundException {
        ResultSet rst;
        String query = "Select BloodType From bloodtype order by BloodType";
        Connection connection = DBConnection.getConnectionToDB();
        rst =  DBHandler.getData(connection, query);
        int count = RecordCounter.getRecordCount(rst);
        String[] comps = new String[count];
        for (int i = 0; rst.next(); i++) {
            comps[i] = rst.getString(1);
        }
        return comps;
    }
    
}
