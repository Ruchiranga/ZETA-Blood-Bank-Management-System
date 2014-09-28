/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Ruchi;

import Controller.RecordCounter;
import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Employee;

/**
 *
 * @author ruchiranga
 */
public class EmployeeController {
    ArrayList<Employee> results;

    public String[] getEmployeeList() throws ClassNotFoundException, SQLException {
        String query = "Select * From employee";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst =  DBHandler.getData(connection, query);
        int count = RecordCounter.getRecordCount(rst);
        
        String[] names = new String[count];
        for (int i = 0; rst.next(); i++) {
            names[i]= rst.getString("Name");
        }
        return names;
    }
    
}
