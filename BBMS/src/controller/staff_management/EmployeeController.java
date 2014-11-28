/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.staff_management;

import controller.common.RecordCounter;
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

    public String getIDOf(String doneByName) throws ClassNotFoundException, SQLException {
        String query = "Select EmpID From employee where Name = '"+doneByName+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst =  DBHandler.getData(connection, query);
        rst.next();
        return rst.getString("EmpID");
    }
    
    public static int addEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        String query = "Insert into Employee(EmpID,Name,Dob,HomeAddress,Tp,DateOfRecruitment) values ('"+employee.getEmpID()+"','"+employee.getName()+"','"+employee.getDob()+"','"+employee.getHomeAddress()+"','"+employee.getTp()+"','"+employee.getDateOfRecruitment()+"')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    
    }
    
}
