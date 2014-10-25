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

/**
 *
 * @author Anuradha
 */
public class TestController {
    
    public static ResultSet getAllTests() throws ClassNotFoundException, SQLException {
        String query = "Select * From Test";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    
    
    public static int getTestCount() throws ClassNotFoundException, SQLException {
        String query = "Select count(*) From Test";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while(rst.next()){
            return rst.getInt("count(*)");
        }
        return -1;
    }
    
    public static String getTestID(String testName) throws ClassNotFoundException, SQLException {
        String query = "Select * from Test where Name='"+testName+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while(rst.next()){
            return rst.getString("TestID");
        }
        return null;
    }
    
}
