/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.ItemReceivedLog;
import model.ItemRecievedDetail;

/**
 *
 * @author Anuradha
 */
public class ItemRecievedLogController {
    
    public static ResultSet getRecievedIDs() throws ClassNotFoundException, SQLException {
        String query = "Select * From ItemRecievedLog order by RecievedID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getRecievedIDsByDate(Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From ItemRecievedLog where RecievedDate='"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getRecievalByID(String id) throws ClassNotFoundException, SQLException {
        String query = "Select * From ItemRecievedLog where RecievedID='"+id+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getRecievedDetail(String id) throws ClassNotFoundException, SQLException {
        String query = "Select * From ItemRecievedDetail D INNER JOIN Item I where RecievedID='"+id+"' AND D.ItemID = I.ItemID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static int addItemRecieval(ItemReceivedLog log) throws ClassNotFoundException, SQLException {
        String query = "Insert into ItemRecievedLog(RecievedID,RecievedDate,RecievedFrom) values ('" + log.getRecievedID()+ "','" +log.getRecievedDate()+"','"+log.getRecievedFrom()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int addItemRecievalDetail(ItemRecievedDetail detail) throws ClassNotFoundException, SQLException {
        String query = "Insert into ItemRecievedDetail(RecievedID,ItemID,Qty) values ('" + detail.getRecievedID()+ "','" +detail.getItemID()+"','"+detail.getQty()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int deleteRecievalDetail(String recievedID, String itemID) throws ClassNotFoundException, SQLException {
        String query = "Delete from ItemRecievedDetail where recievedID='"+recievedID+"' AND ItemID='"+itemID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int deleteRecieval(String recievedID) throws ClassNotFoundException, SQLException {
        String query = "Delete from ItemRecievedLog where recievedID='"+recievedID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateRecievedDetail(ItemRecievedDetail detail) throws ClassNotFoundException, SQLException {
        String query = "Update ItemRecievedDetail set Qty='"+detail.getQty()+"' where recievedID='"+detail.getRecievedID()+"' AND ItemID='"+detail.getItemID()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateItemRecieval(ItemReceivedLog log) throws ClassNotFoundException, SQLException {
        String query = "Update ItemRecievedLog set RecievedFrom='"+log.getRecievedFrom()+"' where recievedID='"+log.getRecievedID()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
}
