/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.Ruchi;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ruchiranga
 */
public class AvailabilityDA {
    
    public ResultSet getBloodGroups() throws SQLException, ClassNotFoundException {
        String query = "Select GroupName From bloodgroup";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    ResultSet getComponents() throws ClassNotFoundException, SQLException {
        String query = "Select BloodType From bloodtype order by BloodType";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public ResultSet getDonors() throws ClassNotFoundException, SQLException{
        String query = "Select name From donor order by name";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public ResultSet getAvailablePackets() throws ClassNotFoundException, SQLException{
        String query = "select * from bloodpacket where isDiscarded = 0 AND patientIssueID is NULL AND bulkIssueID is NULL AND bloodGroup is not NULL";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    
}
