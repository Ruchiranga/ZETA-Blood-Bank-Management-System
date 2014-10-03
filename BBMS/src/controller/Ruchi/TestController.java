/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.Ruchi;

import connection.DBConnection;
import connection.DBHandler;
import idgenerator.IDGenerator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Employee;
import model.Test;

/**
 *
 * @author ruchiranga
 */
public class TestController {
    public int addTest(String testName) throws ClassNotFoundException, SQLException, Exception{
        String curTestID = getLastTestID();
        String newTestID = IDGenerator.generateNextID(curTestID);
        String query = "Insert into test values('"+newTestID+"','"+testName+"')"; 
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    private String getLastTestID() throws ClassNotFoundException, SQLException{
        String query = "Select * from test"; 
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);
        data.last();
        return data.getString("TestID");
    }
    
    public String[] getTestList() throws SQLException, ClassNotFoundException {
        String query = "Select * From test Order By TestID";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        
        ArrayList<Test> tests = new ArrayList<Test>();
        for (int i = 0; rst.next(); i++) {
            tests.add(new Test(rst.getString(1), rst.getString(2)));
        }
        String[] list = new String[tests.size()];
        for (int i = 0; i < tests.size(); i++) {
            list[i] = tests.get(i).getName();
        }
        return list;
    }
    
    
    public String getTestIDOF(String testName) throws ClassNotFoundException, SQLException{
        String query = "select TestID from test where Name = '" + testName + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);
        String id = null;
        if(data.next()){
            id = data.getString("TestID");
        }
        return id;
    }

    public int updateTest(String oldValue, String input) throws ClassNotFoundException, SQLException {
        String query = "Update test Set Name = '"+input+"' where Name = '"+oldValue+"'"; 
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public int deleteTest(String name) throws ClassNotFoundException, SQLException {
        String query = "DELETE from test where Name = '"+name+"'"; 
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
}
