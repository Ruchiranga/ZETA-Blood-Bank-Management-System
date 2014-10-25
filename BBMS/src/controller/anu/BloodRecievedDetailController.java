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
import model.BloodrecievedDetail; 

/**
 *
 * @author Anuradha
 */
public class BloodRecievedDetailController {
    
    public static int addDetail(BloodrecievedDetail detail) throws ClassNotFoundException, SQLException {
        String query = "Insert into BloodRecievedDetail(recievedID,packetID) values ('" + detail.getReceivedID() + "','" + detail.getPacketID() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static ResultSet getDetails(String id)throws ClassNotFoundException, SQLException{
        String query = "Select * From BloodRecievedDetail R INNER JOIN BloodPacket B INNER JOIN Donor D where R.RecievedID = '"+id+"' AND R.PacketID = B.PacketID AND B.nic=D.nic";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
}
