/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Anuradha
 */
public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost/Bloodbank";//BBMS_Test";

    private static final String USER = "root";
    private static final String PASSWORD = "zeta";
    private static DBConnection dbconnection = null;
    private static Connection con = null;

    public static Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static DBConnection getDBConnection() throws ClassNotFoundException, SQLException {
        if (dbconnection == null) {
            dbconnection = new DBConnection();
        }
        return dbconnection;
    }

    public static Connection getConnectionToDB() throws ClassNotFoundException, SQLException {
        return getDBConnection().con;
    }
}
