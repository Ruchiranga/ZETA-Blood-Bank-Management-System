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
import model.ReturnedLog; 

/**
 *
 * @author Anuradha
 */
public class ReturnedLogController {

    public static int addReturnedLog(ReturnedLog log) throws ClassNotFoundException, SQLException {
        String query;
        
        if(log.getPatientIssueID()!=null){
            query = "Insert into ReturnedLog(returnedID,returnedDate,packetID,reason,PatientIssueID) values ('" + log.getReturnedID() + "','" + log.getReturnedDate() + "','" + log.getPacketID() + "','" + log.getReason() + "','" + log.getPatientIssueID() + "')";
            System.out.println("===============");
            System.out.println(log.getReturnedID());
            System.out.println(""+log.getReturnedDate());
            System.out.println(""+log.getPacketID());
            System.out.println(""+ log.getReason());
            System.out.println(""+log.getPatientIssueID());
            
            System.out.println("===============");

        }else{
           query = "Insert into ReturnedLog(returnedID,returnedDate,packetID,reason,BulkIssueID) values ('" + log.getReturnedID() + "','" + log.getReturnedDate() + "','" + log.getPacketID() + "','" + log.getReason() + "','" + log.getBulkIssueID() +"')"; 
        }
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int deleteReturnedLog(String returnID) throws ClassNotFoundException, SQLException {
        String query = "Delete from ReturnedLog where ReturnedID = '"+returnID+"'";
            
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getResultIDs() throws ClassNotFoundException, SQLException {
        String query = "Select * From ReturnedLog order by returnedID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getReturnedLogbyID(String packetID) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReturnedLog where packetID='"+packetID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getReturnedLogbyDate(java.sql.Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReturnedLog R INNER JOIN BloodPacket B where ReturnedDate='"+date+"' AND R.PacketID=B.PacketID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int updateReturnedLog(ReturnedLog log) throws ClassNotFoundException, SQLException {
        String query = "Update ReturnedLog set returnedDate='" + log.getReturnedDate() + "', reason='"+log.getReason()+"' where returnedID='" + log.getReturnedID() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

}
