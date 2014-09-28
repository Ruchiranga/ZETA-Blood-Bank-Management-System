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
            if (packet.getBloodGroup().equals(group)) {
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
            if (packet.getBloodType().equals(component)) {
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
            if (packet.getNic().equals(nic)) {
                results.add(packet);
            }
        }
        BloodPacket[] res = new BloodPacket[results.size()];
        for (int i = 0; i < results.size(); i++) {
            res[i] = results.get(i);
        }
        return res;
    }

}
