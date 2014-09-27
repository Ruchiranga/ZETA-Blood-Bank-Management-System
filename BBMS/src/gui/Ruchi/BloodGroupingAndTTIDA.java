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
public class BloodGroupingAndTTIDA {

    public ResultSet getAllUntestedPackets() throws ClassNotFoundException, SQLException {

        String query = "Select packetID, nic, bloodGroup, bloodType,  dateOfDonation, dateOfExpiry,isCrossmatched,isUnderObservation From BloodPacket where bloodGroup = 'UG' AND isDiscarded = 0";

        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public ResultSet getDonorName(String donorID) throws ClassNotFoundException, SQLException {
        String query = "Select name From donor where nic = '"+donorID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public int blackListDonor(String name) throws SQLException, ClassNotFoundException {
        
        String query = "UPDATE donor SET blacklisted=1 where name = '"+name+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }
    
    public ResultSet getDonors() throws ClassNotFoundException, SQLException{
        String query = "Select nic, name, dob, gender, age, homeAddress, blacklisted From donor";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public ResultSet getBlacklistStatus(String donorID) throws ClassNotFoundException, SQLException {
        String query = "Select blacklisted From donor where nic = '"+donorID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public ResultSet getBloodGroups() throws SQLException, ClassNotFoundException {
        String query = "Select GroupName From bloodgroup";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public ResultSet getTests() throws SQLException, ClassNotFoundException {
        String query = "Select * From test";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    ResultSet getEmployees() throws SQLException, ClassNotFoundException {
        String query = "Select * From employee";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    ResultSet getResultIDs() throws SQLException, ClassNotFoundException {
        String query = "Select * From testresult";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    int setBloodGroup(String packetID, String group, String groupComment) throws SQLException, ClassNotFoundException {
        String query = "UPDATE bloodpacket SET bloodGroup='"+group+"',Comment='"+groupComment+"' where packetID = '"+packetID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }

    int addPacketResult(String resID, String testID, String packetID, String result, String comment, String date, String doneByID, String checkedByID) throws SQLException, ClassNotFoundException {
        String query = "Insert INTO testresult (ResultID,TestID,packetID,Result,Comment,Date,DoneBy,CheckedBy) Values ('"+resID+"','"+testID+"','"+packetID+"','"+result+"','"+comment+"','"+date+"','"+doneByID+"','"+checkedByID+"')";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }


    int discardPacket(String packetID,String date) throws SQLException, ClassNotFoundException {
        String query = "UPDATE bloodpacket SET isDiscarded=1,discardedDate='"+date+"' where packetID = '"+packetID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }
    
}
