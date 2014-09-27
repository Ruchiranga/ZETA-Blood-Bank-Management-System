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
import model.Requestee;

/**
 *
 * @author Anuradha
 */
public class RequesteeDA {

    public static int addRequestee(Requestee requestee) throws ClassNotFoundException, SQLException {
        String query = "Insert into Requestee(RequesteeName) values ('" + requestee.getRequesteeName()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getAllRequestees() throws ClassNotFoundException, SQLException {
        String query = "Select * From Requestee";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int updateRequestee(Requestee oldRequestee, Requestee newRequestee) throws ClassNotFoundException, SQLException {
        String query = "update Requestee set RequesteeName='" + newRequestee.getRequesteeName() + "' where RequesteeName='" + oldRequestee.getRequesteeName() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

    public static int deleteRequestee(Requestee requestee) throws ClassNotFoundException, SQLException {

        String query = "Delete from Requestee where RequesteeName='" +  requestee.getRequesteeName()+ "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

}
