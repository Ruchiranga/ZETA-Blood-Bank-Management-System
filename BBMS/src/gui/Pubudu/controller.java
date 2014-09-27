/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Pubudu;

import connection.DBHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Anushika
 */
public class controller {

    //create connection 
    public Connection createConnection() {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/bloodbank";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "zeta");
            return conn;
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }
    
    public ResultSet executeQuery(Connection conn, String query) throws SQLException{
        
       return DBHandler.getData(conn, query);
    }

}
