/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.issueing;

import connection.DBConnection;
import connection.DBHandler;
import controller.common.RecordCounter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.SampleDetail;

/**
 *
 * @author Nands
 */
public class SampleDetailsController {
    
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
    
    public static int addSample(SampleDetail sample) throws ClassNotFoundException, SQLException {
        String query = "Insert into sampledetail(requestno,patientname,bhtno,age,gender,bloodgroup,ward,weight,hospital,diagnosis, reactionhistory,collecteddate,collectedtime,collectedby,priority,date) values ('" + sample.getRequestNo() + "','" + sample.getPatientName() + "','" + sample.getBHTNo() + "','" + sample.getAge() +"','" + sample.getGender() + "','" + sample.getBloodGroup() + "','" + sample.getWard() + "','" + sample.getWeight() + "','"+ sample.getHospital()+ "','"+ sample.getDiagnosis()+ "','" + sample.getReactionHistory() + "','"+sample.getCollectedDate() + "','"+sample.getCollectedTime() + "','"+ sample.getCollectedBy() + "','"+ sample.getPriority()+  "','"+ sample.getRecievedDate() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static ResultSet getAllHospitals() throws ClassNotFoundException, SQLException {
        String query = "Select * From hospital";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getAllRequestNos() throws ClassNotFoundException, SQLException {
        String query = "Select RequestNo From sampledetail";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getAllFilteredRequestNos() throws ClassNotFoundException, SQLException {
        String query = "Select RequestNo From sampledetail where IssueID is NULL AND isCrossmatched = 1";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getAllWards() throws ClassNotFoundException, SQLException {
        String query = "Select * From Ward";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getAllTypes() throws ClassNotFoundException, SQLException{
        String query = "Select * From bloodtype";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    
    
}
