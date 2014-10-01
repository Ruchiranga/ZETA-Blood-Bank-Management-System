/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Ruchi;

import connection.DBConnection;
import connection.DBHandler;
import idgenerator.IDGenerator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Employee;
import model.Test;
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
        String query = "Insert INTO testresult (ResultID,TestID,packetID,Result,Comment,Date,DoneBy,CheckedBy) Values ('"+resID+"','"+result.getTestID()+"','"+result.getPacketID()+"','"+result.getResult()+"','"+result.getComment()+"','"+result.getDate()+"','"+result.getDoneBy()+"','"+result.getCheckedBy()+"')";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);

    }
    
}
