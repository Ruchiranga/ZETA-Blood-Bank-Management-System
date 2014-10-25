/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Ruchi;

import controller.RecordCounter;
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
        String query = "Select RequestNo From sampledetail where isCrossmatched = 0";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        int recordCount = RecordCounter.getRecordCount(rst);

        String[] nos = new String[recordCount];
        for (int i = 0; rst.next(); i++) {
            nos[i] = rst.getString("RequestNo");
        }
        return nos;
    }

    public int markCrossmatched(String requestNo) throws ClassNotFoundException, SQLException {

        String query = "UPDATE bbms.sampledetail SET `IsCrossmatched` = true WHERE `RequestNo` = '" + requestNo + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }

    public ResultSet getDetailsOf(String requestNo) throws ClassNotFoundException, SQLException {
        String query = "Select * From sampledetail where RequestNo = '" + requestNo + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        return rst;
    }

    public int[][] getYearlyRequestCountsOf(int month) throws ClassNotFoundException, SQLException {
        String query = "select Year(Date) as Year, Month(Date) as Month, count(Date) as Count from sampledetail Group by Year(date),Month(Date) Having Month = " + month;
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);
        int recordCount = RecordCounter.getRecordCount(data);

        if (recordCount == 0) {
            return null;
        }

        int[] years = new int[recordCount];
        int[] counts = new int[recordCount];

        for (int i = 0; data.next(); i++) {
            years[i] = data.getInt("Year");
            counts[i] = data.getInt("Count");
        }
        return new int[][]{years, counts};
    }
}
