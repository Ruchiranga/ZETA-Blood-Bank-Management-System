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
public class EmployeeController {
    
    public static ResultSet getAllEmployees() throws ClassNotFoundException, SQLException {
        String query = "Select * From Employee";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static String getEmpID(String empName) throws ClassNotFoundException, SQLException {
        String query = "Select * From Employee where Name='"+empName+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while(rst.next()){
            return rst.getString("EmpID");
        }
        return null;
    }
    
}
