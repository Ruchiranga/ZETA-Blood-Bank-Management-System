/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Ward;
 
/**
 *
 * @author Anuradha
 */
public class WardController {

    public static int addWard(Ward ward) throws ClassNotFoundException, SQLException {
        String query = "Insert into Ward(Name) values ('" + ward.getName()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getAllWards() throws ClassNotFoundException, SQLException {
        String query = "Select * From Ward";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int updateWard(Ward oldWard, Ward newWard) throws ClassNotFoundException, SQLException {
        String query = "update Ward set Name='" + newWard.getName() + "' where Name='" + oldWard.getName() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

    public static int deleteWard(Ward ward) throws ClassNotFoundException, SQLException {

        String query = "Delete from Ward where Name='" +  ward.getName()+ "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

}
