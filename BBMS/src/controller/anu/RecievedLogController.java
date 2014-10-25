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
/**
 *
 * @author Anuradha
 */
public class RecievedLogController {
    
    public static ResultSet getAllLogs(Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodRecievedLog WHERE dateRecieved='"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
}
