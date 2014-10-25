/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ruchiranga
 */
public class Predictions {

    public static int getPredictedRequestsOf(int year, int month) throws ClassNotFoundException, SQLException {
        String query = "select Year(Date) as Year, Month(Date) as Month, count(Date) as Count from sampledetail Group by Year(date),Month(Date) Having Month = " + month;
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);
        int recordCount = RecordCounter.getRecordCount(data);
        if (recordCount == 0) {
            return 0;
        }
        int[] years = new int[recordCount];
        int[] counts = new int[recordCount];

        for (int i = 0; data.next(); i++) {
            years[i] = data.getInt("Year");
            counts[i] = data.getInt("Count");
        }

        int n = recordCount;
        int sumxy = 0;
        int sumx = 0;
        int sumy = 0;
        int sumxsqrd = 0;

        for (int i = 0; i < n; i++) {
            sumxy += years[i] * counts[i];
            sumx += years[i];
            sumy += counts[i];
            sumxsqrd += years[i] * years[i];
        }

        double a = (n * sumxy - sumx * sumy) / (double) (n * sumxsqrd - (sumx * sumx));
        double b = (double) (sumy / n) - a * (sumx / n);

        double expected = a * year + b;

        return (int) Math.round(expected);
    }
}
