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
public class HospitalController {

    public static int addRequestor(Requester requestor) throws ClassNotFoundException, SQLException {
        String query = "Insert into Hospital(Name) values ('" +requestor.getRequesterName() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getAllHospitals() throws ClassNotFoundException, SQLException {
        String query = "Select * From Hospital";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int updateHospital(Requester oldHospital, Requester newHospital) throws ClassNotFoundException, SQLException {
        String query = "update Hospital set Name='" + newHospital.getRequesterName() + "' where Name='" + oldHospital.getRequesterName() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

    public static int deleteHospital(Requester hospital) throws ClassNotFoundException, SQLException {

        String query = "Delete from Hospital where Name='" + hospital.getRequesterName() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

}
