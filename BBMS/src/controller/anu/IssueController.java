/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 

/**
 *
 * @author Anuradha
 */
public class IssueController {
    
    public static String getIssuedTo(String issueID) throws ClassNotFoundException, SQLException {
        
        String query = "Select * From Issue I NATURAL JOIN SampleDetail S where I.IssueID = '"+issueID+"' AND I.SampleID=S.RequestNo";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while(rst.next()){
            return rst.getString("Hospital");
        }
        return null;
    }
    
    public static ResultSet getIssuedDetails(String issueID) throws ClassNotFoundException, SQLException {
        
        String query = "Select * From Issue I NATURAL JOIN SampleDetail S where I.IssueID = '"+issueID+"' AND I.SampleID=S.RequestNo";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
        
    }
    
}
