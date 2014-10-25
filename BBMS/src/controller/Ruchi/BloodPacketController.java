/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Ruchi;

import connection.DBConnection;
import connection.DBHandler;
import gui.Ruchi.AvailabilityHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BloodPacket;
import model.Donor;

/**
 *
 * @author ruchiranga
 */
public class BloodPacketController {

    ArrayList<BloodPacket> packets;
    ArrayList<BloodPacket> results;

    public BloodPacketController() {
        packets = new ArrayList<BloodPacket>();

        ResultSet rst;
        try {
            String query = "select * from bloodpacket where isDiscarded = 0 AND patientIssueID is NULL AND bulkIssueID is NULL AND bloodGroup is not NULL";
            Connection connection = DBConnection.getConnectionToDB();
            rst = DBHandler.getData(connection, query);
            int count = getRecordCount(rst);
            for (int i = 0; rst.next(); i++) {
                packets.add(new BloodPacket(rst.getString("packetID"), rst.getString("bloodGroup"), rst.getString("bloodType"), rst.getString("nic"), rst.getDate("dateOFExpiry"), rst.getDate("dateOfDonation"), (byte) rst.getInt("isCrossmatched"), (byte) rst.getInt("isUnderObservation")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvailabilityHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AvailabilityHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getRecordCount(ResultSet rst) throws SQLException {
        int count = 0;
        while (rst.next()) {
            count++;
        }
        rst.beforeFirst();
        return count;
    }

    public ArrayList<BloodPacket> getAvailableUnxPackets() {
        packets = new ArrayList<BloodPacket>();

        ResultSet rst;
        try {
            String query = "select * from bloodpacket where isDiscarded = 0 AND isCrossmatched = 0 AND isUnderObservation = 0 AND patientIssueID is NULL AND bulkIssueID is NULL AND bloodGroup is not NULL";
            Connection connection = DBConnection.getConnectionToDB();
            rst = DBHandler.getData(connection, query);
            int count = getRecordCount(rst);
            for (int i = 0; rst.next(); i++) {
                packets.add(new BloodPacket(rst.getString("packetID"), rst.getString("bloodGroup"), rst.getString("bloodType"), rst.getString("nic"), rst.getDate("dateOFExpiry"), rst.getDate("dateOfDonation"), (byte) rst.getInt("isCrossmatched"), (byte) rst.getInt("isUnderObservation")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvailabilityHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AvailabilityHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return packets;
    }

    public BloodPacket[] searchByGroup(String group) {
        results = new ArrayList<BloodPacket>();
        for (BloodPacket packet : packets) {
            if (packet.getBloodGroup().equals(group) && packet.isIsCrossmatched() == 0 && !packet.getBloodGroup().equals("UG")) {
                results.add(packet);
            }
        }
        BloodPacket[] res = new BloodPacket[results.size()];
        for (int i = 0; i < results.size(); i++) {
            res[i] = results.get(i);
        }
        return res;
    }

    public BloodPacket[] searchByComponent(String component) {
        results = new ArrayList<BloodPacket>();
        for (BloodPacket packet : packets) {
            if (packet.getBloodType().equals(component) && packet.isIsCrossmatched() == 0 && !packet.getBloodGroup().equals("UG")) {
                results.add(packet);
            }
        }
        BloodPacket[] res = new BloodPacket[results.size()];
        for (int i = 0; i < results.size(); i++) {
            res[i] = results.get(i);
        }
        return res;
    }

    public BloodPacket[] searchByDonor(String name) throws SQLException, ClassNotFoundException {
        results = new ArrayList<BloodPacket>();
        for (BloodPacket packet : packets) {
            String query = "select nic from donor where name = '" + name + "'";
            Connection connection = DBConnection.getConnectionToDB();
            ResultSet data = DBHandler.getData(connection, query);
            data.next();
            String nic = data.getString("nic");
            if (packet.getNic().equals(nic) && packet.isIsCrossmatched() == 0 && !packet.getBloodGroup().equals("UG")) {
                results.add(packet);
            }
        }
        BloodPacket[] res = new BloodPacket[results.size()];
        for (int i = 0; i < results.size(); i++) {
            res[i] = results.get(i);
        }
        return res;
    }

    public int markCrossMatched(String packetID) throws ClassNotFoundException, SQLException {
        String query = "UPDATE bloodpacket SET `IsCrossmatched` = true WHERE `PacketID` = '" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }
    
    public int markUnCrossMatched(String packetID) throws ClassNotFoundException, SQLException {
        String query = "UPDATE bloodpacket SET `IsCrossmatched` = false WHERE `PacketID` = '" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }

    public int markSpecialReservation(String packetID) throws ClassNotFoundException, SQLException {
        String query = "UPDATE bloodpacket SET `IsSpecialReservation` = true WHERE `PacketID` = '" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }

    public String[] getPacketIDList() throws ClassNotFoundException, SQLException {
        String query = "Select packetID, nic, bloodGroup, bloodType,  dateOfDonation, dateOfExpiry,isCrossmatched,isUnderObservation From BloodPacket where bloodGroup = 'UG' AND isDiscarded = 0";

        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);
        int count = getRecordCount(data);
        String[] list = new String[count];
        for (int i = 0; data.next(); i++) {
            list[i] = data.getString("PacketID");
        }
        return list;
    }

    public String getDonorNameOf(String packetID) throws SQLException, ClassNotFoundException {
        String query = "select packetID,nic, Name from BloodPacket natural join donor where packetID = '" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);

        String donorName = null;
        if (data.next()) {
            donorName = data.getString("Name");
        }

        return donorName;
    }

    public String getDonorIDOF(String packetID) throws ClassNotFoundException, SQLException {
        String query = "select nic from bloodpacket where packetID = '" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);
        String nic = null;
        if (data.next()) {
            nic = data.getString("nic");
        }
        return nic;
    }

    public int setBloodGroup(String packetID, String group, String groupComment) throws SQLException, ClassNotFoundException {
        String query = "UPDATE bloodpacket SET bloodGroup='" + group + "',Comment='" + groupComment + "' where packetID = '" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }

    public int discardPacket(String packetID, String date) throws SQLException, ClassNotFoundException {
        String query = "UPDATE bloodpacket SET isDiscarded=1,discardedDate='" + date + "' where packetID = '" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }

}
