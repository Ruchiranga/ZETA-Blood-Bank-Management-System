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
        String query = "Insert into ReturnedLog(returnedID,returnedDate,packetID,reason) values ('" + log.getReturnID() + "','" + log.getReturnedDate() + "','" + log.getPacketID() + "','" + log.getReason() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getResultIDs() throws ClassNotFoundException, SQLException {
        String query = "Select * From ReturnedLog order by returnedID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getReturnedLog(String packetID) throws ClassNotFoundException, SQLException {
        String query = "Select * From ReturnedLog where packetID='"+packetID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int updateReturnedLog(ReturnedLog log) throws ClassNotFoundException, SQLException {
        String query = "Update ReturnedLog set returnedDate='" + log.getReturnedDate() + "', packetID = '"+log.getPacketID()+"', reason='"+log.getReason()+"' where returnedID='" + log.getReturnID() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

}
