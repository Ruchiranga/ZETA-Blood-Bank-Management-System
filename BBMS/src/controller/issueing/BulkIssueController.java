/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.issueing;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException; 

/**
 *
 * @author Anuradha
 */
public class BulkIssueController {
    
    public static ResultSet getBulkIssueDetails(String issueID) throws ClassNotFoundException, SQLException {
        
        String query = "Select * From BulkIssue where IssueID = '"+issueID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
        
    }
    
}
