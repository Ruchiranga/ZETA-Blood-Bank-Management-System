/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Anuradha
 */
public class CampController {
    
    public static String getCampID(String campName) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodCamp where Place='"+campName+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        if(rst.next()){
            return rst.getString("CampID");
        }else{
            return null;
        }
    }
    
    public static String getCampName(String campID) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodCamp where CampID = '"+campID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        if(rst.next()){
            return rst.getString("Place");
        }else{
            return null;
        }
    }
    
    public static ResultSet getCamps() throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodCamp";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        return rst;
    }
    
}
