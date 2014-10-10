/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ReagentRequest;
import model.ReagentRequestDetail;

/**
 *
 * @author Anuradha
 */
public class ReagentRequestDA {

    public static int addReagentRequest(ReagentRequest request) throws ClassNotFoundException, SQLException {
        String query = "Insert into ReagentRequest(requestID,requestDate,requestingOfficerID) values ('" + request.getRequestID() + "','" +request.getRequestDate()+"','"+request.getRequestingOfficerID()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int addReagentRequestDetails(ReagentRequestDetail requestDetail) throws ClassNotFoundException, SQLException {
        String query = "Insert into ReagentRequestDetail(requestID,itemID,Qty,Reason) values ('" + requestDetail.getRequestID() + "','" +requestDetail.getItemID()+"','"+requestDetail.getQty()+"','"+requestDetail.getReason()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getRequest(String requestID) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReagentRequest WHERE requestID='"+requestID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getRequestIDs() throws ClassNotFoundException, SQLException {
        String query = "Select * From ReagentRequest";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getAllRecords(String requestID) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReagentRequest WHERE requestID='"+requestID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

}
