/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.item_management;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ReagentRequest;
import model.ReagentRequestDetail; 

/**
 *
 * @author Anuradha
 */
public class ReagentRequestController {

    public static int addReagentRequest(ReagentRequest request) throws ClassNotFoundException, SQLException {
        String query = "Insert into ReagentRequest(requestID,requestDate,requestingOfficerID) values ('" + request.getRequestID() + "','" +request.getRequestDate()+"','"+request.getRequestingOfficerID()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int deleteRequestDetail(String requestID, String itemID) throws ClassNotFoundException, SQLException {
        String query = "Delete from ReagentRequestDetail where requestID='"+requestID+"' AND ItemID='"+itemID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateRequestDetail(ReagentRequestDetail requestDetail) throws ClassNotFoundException, SQLException {
        String query = "Update ReagentRequestDetail set Qty='"+requestDetail.getQty()+"', Reason='"+requestDetail.getReason()+"'  where requestID='"+requestDetail.getRequestID()+"' AND ItemID='"+requestDetail.getItemID()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateRequest(ReagentRequest request) throws ClassNotFoundException, SQLException {
        String query = "Update ReagentRequest set RequestingOfficerID='"+request.getRequestingOfficerID()+"' where requestID='"+request.getRequestID()+"'";
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
        String query = "Select * From ReagentRequest R INNER JOIN Employee E WHERE R.requestID='"+requestID+"' AND R.RequestingOfficerID=E.EmpID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getRequestIDs() throws ClassNotFoundException, SQLException {
        String query = "Select * From ReagentRequest";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getRequestDetails(String requestID) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReagentRequestDetail R INNER JOIN Item I where RequestID='"+requestID+"' AND R.ItemID=I.ItemID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getRequestIDsByDate(Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReagentRequest where RequestDate='"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getAllRecords(String requestID) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReagentRequest WHERE requestID='"+requestID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

}
