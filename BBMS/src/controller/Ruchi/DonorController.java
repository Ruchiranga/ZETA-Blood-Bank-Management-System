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
public class DonorController {

    public String[] getDonorList() throws SQLException, ClassNotFoundException {
        String query = "Select name From donor order by name";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        int count = RecordCounter.getRecordCount(rst);
        String[] donors = new String[count];
        for (int i = 0; rst.next(); i++) {
            donors[i] = rst.getString(1);
        }
        return donors;
    }

    public String getDonorNameOf(String nic) throws ClassNotFoundException, SQLException {
        String query = "select name from donor where nic = '" + nic + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);
        data.next();
        return data.getString("name");
    }
    
}
