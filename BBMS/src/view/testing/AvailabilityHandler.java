/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.testing;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.BloodPacket;
import model.Test;

/**
 *
 * @author ruchiranga
 */
public class AvailabilityHandler {

    AvailabilityController dataAccess;
    ArrayList<BloodPacket> packets;
    ArrayList<BloodPacket> results;

    public AvailabilityHandler() {
        dataAccess = new AvailabilityController();
        packets = new ArrayList<BloodPacket>();

        ResultSet rst;
        try {
            rst = dataAccess.getAvailablePackets();
            int count = getRecordCount(rst);
            for (int i = 0; rst.next(); i++) {
                packets.add(new BloodPacket(rst.getString("packetID"), rst.getString("bloodGroup"), rst.getString("bloodType"), rst.getString("nic"), rst.getDate("dateOFExpiry"), rst.getDate("dateOfDonation"), (byte)rst.getInt("isCrossmatched"), (byte)rst.getInt("isUnderObservation")));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AvailabilityHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AvailabilityHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    String[] getGroupList() throws SQLException, ClassNotFoundException {
        ResultSet rst;
        rst = dataAccess.getBloodGroups();
        int count = getRecordCount(rst);
        String[] groups = new String[count];
        for (int i = 0; rst.next(); i++) {
            groups[i] = rst.getString(1);
        }
        return groups;

    }

    private int getRecordCount(ResultSet rst) throws SQLException {
        int count = 0;
        while (rst.next()) {
            count++;
        }
        rst.beforeFirst();
        return count;
    }

    String[] getComponentList() throws SQLException, ClassNotFoundException {
        ResultSet rst;
        rst = dataAccess.getComponents();
        int count = getRecordCount(rst);
        String[] comps = new String[count];
        for (int i = 0; rst.next(); i++) {
            comps[i] = rst.getString(1);
        }
        return comps;
    }

    String[] getDonorList() throws ClassNotFoundException, SQLException {
        ResultSet rst;
        rst = dataAccess.getDonors();
        int count = getRecordCount(rst);
        String[] donors = new String[count];
        for (int i = 0; rst.next(); i++) {
            donors[i] = rst.getString(1);
        }
        return donors;
    }

    BloodPacket[] searchByGroup(String group) {

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

    String getDonorNameOf(String nic) throws SQLException, ClassNotFoundException {
        String query = "select name from donor where nic = '" + nic + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);
        data.next();
        return data.getString("name");
    }
    
    String getDonorNICOf(String name) throws SQLException, ClassNotFoundException {
        String query = "select nic from donor where name = '" + name + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet data = DBHandler.getData(connection, query);
        data.next();
        return data.getString("nic");
    }

    void clearTable(DefaultTableModel availabilityTable) {
        while(availabilityTable.getRowCount()>0){
            availabilityTable.removeRow(availabilityTable.getRowCount()-1);
        }
    }

    BloodPacket[] searchByComponent(String component) {
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

    BloodPacket[] searchByDonor(String donor) throws SQLException, ClassNotFoundException {
        results = new ArrayList<BloodPacket>();
        for (BloodPacket packet : packets) {
            String nic = getDonorNICOf(donor);
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

    int setAsCrossMatched(String packetID) throws ClassNotFoundException, SQLException {
        String query = "update bloodpacket set isCrossmatched = 1 where packetID = '"+packetID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    int setAsUncrossMatched(String packetID) throws SQLException, ClassNotFoundException {
        String query = "update bloodpacket set isCrossmatched = 0 where packetID = '"+packetID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }


}
