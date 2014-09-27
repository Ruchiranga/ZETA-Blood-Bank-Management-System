/*
 * To change this template, choose Tools | Templates
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

/**
 *
 * @author Anuradha
 */
public class RecievedLogDA {

    public static int addReceivedLog(ItemReceivedLog log) throws ClassNotFoundException, SQLException {
        String query = "Insert into RecievedLog(itemID,dateRecieved,qtyRecieved,recievedFrom) values ('" + log.getItemID() + "','" + log.getRecievedDate() + "','" + log.getQty() + "','"+log.getFrom()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getAllLogs(Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From RecievedLog WHERE dateRecieved='"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    

}
