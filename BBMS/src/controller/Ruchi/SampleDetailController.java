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
public class SampleDetailController {

    public String[] getRequestNos() throws ClassNotFoundException, SQLException {
        String query = "Select RequestNo From sampledetail";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        int recordCount = RecordCounter.getRecordCount(rst);

        String[] nos = new String[recordCount];
        for (int i = 0; rst.next(); i++) {
            nos[i] = rst.getString("RequestNo");
        }
        return nos;
    }

    public ResultSet getDetailsOf(String requestNo) throws ClassNotFoundException, SQLException {
        String query = "Select * From sampledetail where RequestNo = '"+requestNo+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        rst.next();
        return rst;
    }
}
