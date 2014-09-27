/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ReagentRequest;

/**
 *
 * @author Anuradha
 */
public class ReagentRequestDA {

    public static int addReceivedLog(ReagentRequest request) throws ClassNotFoundException, SQLException {
        String query = "Insert into ReagentRequest(requestID,itemID,date,qty,reason,requestingOfficerID) values ('" + request.getRequestID() + "','" + request.getItemID() + "','" + request.getDate() + "','"+ request.getQty() + "','"+ request.getReason() + "','"+request.getOfficerID()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getRequest(String requestID) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReagentRequest WHERE requestID='"+requestID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getAllRecords(String requestID) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReagentRequest WHERE requestID='"+requestID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

}
