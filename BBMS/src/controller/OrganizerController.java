/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Organizer;

/**
 *
 * @author Anushika
 */
public class OrganizerController {

    public static int InsertOrganizerData(Organizer organizer) throws ClassNotFoundException, SQLException {

        String query = "INSERT INTO Organizer VALUES('" + organizer.getNic() + "','" + organizer.getName() + "','" + organizer.getAddress() + "','" + organizer.getTp() + "', '" + organizer.getCampCount() + "')";
        Connection connection = returnconnection();
        return DBHandler.setData(DBConnection.getConnectionToDB(), query);
    }

    public static void updateOrganizer(Organizer organizer) throws ClassNotFoundException, SQLException {
        String query = "Update organizer set CampCount = '" + organizer.getCampCount() + "' where nIC = '" + organizer.getNic() + "'";
        DBHandler.setData(DBConnection.getConnectionToDB(), query);
    }

    public static ResultSet getResultedNIC(String Nic) throws ClassNotFoundException, SQLException {
        String query = ("SELECT * FROM organizer HAVING NIC = '" + Nic + "'");
        Connection connection = returnconnection();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getOrganizerDetails() throws ClassNotFoundException, SQLException {
        String query = "Select * from Organizer";
        Connection connection = returnconnection();
        return DBHandler.getData(connection, query);
    }
    
    //get connectiion result
    public static Connection returnconnection() throws ClassNotFoundException, SQLException {
        return DBConnection.getConnectionToDB();

    }

}
