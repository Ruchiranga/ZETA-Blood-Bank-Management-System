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
import model.Requester;

/**
 *
 * @author Anuradha
 */
public class RequestorDA {

    public static int addRequestor(Requester requestor) throws ClassNotFoundException, SQLException {
        String query = "Insert into Requestor(Hospital) values ('" +requestor.getHospital() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getAllHospitals() throws ClassNotFoundException, SQLException {
        String query = "Select * From Requestor";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int updateHospital(Requester oldHospital, Requester newHospital) throws ClassNotFoundException, SQLException {
        String query = "update Requestor set hospital='" + newHospital.getHospital() + "' where hospital='" + oldHospital.getHospital() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

    public static int deleteHospital(Requester hospital) throws ClassNotFoundException, SQLException {

        String query = "Delete from Requestor where hospital='" + hospital.getHospital() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

}
