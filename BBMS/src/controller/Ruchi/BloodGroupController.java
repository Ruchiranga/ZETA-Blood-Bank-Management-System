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
public class BloodGroupController {

    public String[] getGroupList() throws SQLException, ClassNotFoundException {
        ResultSet rst;
        String query = "Select GroupName From bloodgroup";
        Connection connection = DBConnection.getConnectionToDB();
        rst = DBHandler.getData(connection, query);
        int count = RecordCounter.getRecordCount(rst);
        String[] groups = new String[count-1];
        for (int i = 0; rst.next(); i++) {
            String group = rst.getString(1);
            if (!group.equals("UG")) {
                groups[i] = group;
            }else{
                i--;
                continue;
            }
        }
        return groups;
    }

}
