/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Ruchi;

import connection.DBConnection;
import connection.DBHandler;
import controller.IDGenerator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.TestResult;

/**
 *
 * @author ruchiranga
 */
public class TestResultController {

    private String getNextResultID() throws SQLException, Exception {
        String query = "Select * From testresult";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        rst.last();
        String curID = null;
        try {
            curID = rst.getString("ResultID");
        } catch (SQLException e) {
            return "RS00000001";
        }

        return IDGenerator.generateNextID(curID);

    }

    public int addTestResult(TestResult result) throws Exception {
        String resID = getNextResultID();
        String query = "Insert INTO testresult (ResultID,TestID,packetID,Result,Comment,Date,DoneBy,CheckedBy) Values ('" + resID + "','" + result.getTestID() + "','" + result.getPacketID() + "','" + result.getResult() + "','" + result.getComment() + "','" + result.getDate() + "','" + result.getDoneBy() + "','" + result.getCheckedBy() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);

    }
    
    private String getLabeledByOf(String resultID) throws ClassNotFoundException, SQLException{
        String query = "select labeledBy from testResult where resultID = 'R00002'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        String returnVal = null;
        while (rst.next()) {            
            returnVal = rst.getString("labeledBy");
        }
        return returnVal;
    }

    public String[][] getTestResultRecords(int columnCount) throws ClassNotFoundException, SQLException {
        String query = "SELECT resultID,Date,testresult.PacketID,t.`TestID`,t.`Name` as TestName,Result,testresult.Comment,b.`BloodGroup`,DoneBY,e1.`Name` as DoneByName,CheckedBy, e2.`Name` as CheckedByName FROM testresult INNER JOIN test t ON testresult.TestID=t.TestId INNER JOIN employee e1 ON testresult.DoneBy=e1.`EmpID` INNER JOIN employee e2 ON testresult.CheckedBy=e2.EmpID INNER JOIN bloodpacket b ON testresult.PacketID=b.`PacketID` order by Date,PacketID,t.`TestID`";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
//        int recordCount = RecordCounter.getRecordCount(rst);
        ArrayList<String[]> temprows;
        temprows = new ArrayList<String[]>();

        String[] row;
        row = new String[columnCount];
        String curPacket = null;
        for (int i = 0; rst.next(); i++) {
            if (curPacket == null) {
                curPacket = rst.getString("PacketID");
            }
            if (curPacket.equals(rst.getString("PacketID"))) {
                row[0] = rst.getString("Date");
                row[1] = rst.getString("PacketID");
                int testpos = Integer.parseInt(rst.getString("TestID").substring(1));
                row[1 + testpos] = rst.getString("Result");
                row[columnCount - 4] = rst.getString("BloodGroup");
                row[columnCount - 3] = rst.getString("CheckedByName");
                row[columnCount - 2] = rst.getString("DoneByName");
                row[columnCount - 1] = /*"Anonymous"*/getLabeledByOf(rst.getString("resultID"));
            } else {

                for (int j = 0; j < row.length; j++) {
                    if (row[j] == null) {
                        row[j] = "N/A";
                    }
                }
                temprows.add(row);

                row = new String[columnCount];
                curPacket = rst.getString("PacketID");
                row[0] = rst.getString("Date");
                row[1] = rst.getString("PacketID");
                int testpos = Integer.parseInt(rst.getString("TestID").substring(1));
                row[1 + testpos] = rst.getString("Result");
                row[columnCount - 4] = rst.getString("BloodGroup");
                row[columnCount - 3] = rst.getString("CheckedByName");
                row[columnCount - 2] = rst.getString("DoneByName");
                row[columnCount - 1] = getLabeledByOf(rst.getString("resultID"))/*"anonymous"rst.getString("LabeledByName")*/;
            }
        }
        for (int j = 0; j < row.length; j++) {
            if (row[j] == null) {
                row[j] = "N/A";
            }
        }
        temprows.add(row);

        String[][] rows = new String[temprows.size()][columnCount];
        
        
        for (int i = 0; i < temprows.size(); i++) {
            for (int j = 0; j < columnCount; j++) {
                rows[i][j] = temprows.get(i)[j];
            }
        }
        return rows;

    }

}
