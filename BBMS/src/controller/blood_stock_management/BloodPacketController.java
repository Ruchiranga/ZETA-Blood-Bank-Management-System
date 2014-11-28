/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.blood_stock_management;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import connection.DBConnection;
import connection.DBHandler;
import view.testing.AvailabilityHandler;
import java.sql.Connection;
import java.sql.Date; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BloodPacket;

/**
 *
 * @author Anuradha
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
    
    //================================================================

    public static int addPacket(BloodPacket packet) throws ClassNotFoundException, SQLException {
        String query;
        if (packet.getCampID() != null) {
            query = "Insert into BloodPacket(packetID,nic,dateOfDonation,dateOfExpiry,BloodType,campID,BloodGroup,Comment) values ('" + packet.getPacketID() + "','" + packet.getNic() + "','" + packet.getDateOfDonation() + "','" + packet.getDateOfExpiry() + "','" + packet.getBloodType() + "','" + packet.getCampID() + "','" + packet.getBloodGroup() + "','" + packet.getComment() + "')";

        } else {
            query = "Insert into BloodPacket(packetID,nic,dateOfDonation,dateOfExpiry,BloodType,BloodGroup,Comment) values ('" + packet.getPacketID() + "','" + packet.getNic() + "','" + packet.getDateOfDonation() + "','" + packet.getDateOfExpiry() + "','" + packet.getBloodType() + "','" + packet.getBloodGroup() + "','" + packet.getComment() + "')";
        }

        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updatePacket(BloodPacket packet) throws ClassNotFoundException, SQLException {
        String query;
        
        if (packet.getCampID() == null) {
            query = "Update BloodPacket set nic = '"+packet.getNic()+"', dateOfDonation = '"+packet.getDateOfDonation()+"', dateOfExpiry = '"+packet.getDateOfExpiry()+"', BloodType = '"+packet.getBloodType()+"', campID=NULL, BloodGroup='"+packet.getBloodGroup()+"', Comment = '"+packet.getComment()+"' where packetID = '"+packet.getPacketID()+"'";
        }else{
            query = "Update BloodPacket set nic = '"+packet.getNic()+"', dateOfDonation = '"+packet.getDateOfDonation()+"', dateOfExpiry = '"+packet.getDateOfExpiry()+"', BloodType = '"+packet.getBloodType()+"', campID='"+packet.getCampID()+"', BloodGroup='"+packet.getBloodGroup()+"', Comment = '"+packet.getComment()+"' where packetID = '"+packet.getPacketID()+"'";

        }
        
        
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateRecievedBloodPacket(BloodPacket packet) throws ClassNotFoundException, SQLException {
        String query = "Update BloodPacket set dateOfDonation = '"+packet.getDateOfDonation()+"', dateOfExpiry = '"+packet.getDateOfExpiry()+"', BloodType = '"+packet.getBloodType()+"', BloodGroup='"+packet.getBloodGroup()+"', Comment = '"+packet.getComment()+"' where packetID = '"+packet.getPacketID()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int addRecievedPacket(BloodPacket packet) throws ClassNotFoundException, SQLException {
        String query = "Insert into BloodPacket(packetID,nic,recievedID,dateOfDonation,dateOfExpiry,BloodType,BloodGroup,Comment) values ('" + packet.getPacketID() + "','" + packet.getNic() +"','" + packet.getRecievedID() + "','" + packet.getDateOfDonation() + "','" + packet.getDateOfExpiry() + "','" + packet.getBloodType() + "','" + packet.getBloodGroup() + "','" + packet.getComment() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int unmarkReturnedBloodPacket(String packetID, boolean isPatientIssue, String issueID) throws ClassNotFoundException, SQLException {
        String query;
        if (isPatientIssue) {
            query = "Update BloodPacket set PatientIssueID = '"+issueID+"', ReturnID = NULL where PacketID='"+packetID+"'";
        }else{
            query = "Update BloodPacket set BulkIssueID = '"+issueID+"', ReturnID = NULL where PacketID='"+packetID+"'";

        }
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getAllBloodPackets() throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static String getDonorNic(String packetID) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where PacketID='"+packetID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        String donorNic = "";
        while(rst.next()){
            donorNic = rst.getString("Nic");
        }
        return donorNic;
    }
    
    public static ResultSet getBloodPackets(String packetID) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket B NATURAL JOIN Donor D where packetID = '"+packetID+"' AND B.Nic = D.nic";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getAllIssuedBloodPackets() throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where PatientIssueID is not null OR BulkIssueID is not null";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getIssuedPacketDetails(String packetID) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket NATURAL JOIN Issue NATURAL JOIN IssueDetail where packetID = '" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getExpiredBloodPackets(Date today) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where dateOfExpiry <= '" + today + "' AND isDiscarded = " + 0 + "";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getReturnedBloodPackets() throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where isReturned = 1";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static int deletePacket(String packetID) throws ClassNotFoundException, SQLException {
        String query = "Delete from BloodPacket where packetID = '" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static int discardPacket(String packetID, Date discardedDate) throws ClassNotFoundException, SQLException {
        String query = "Update BloodPacket set isDiscarded=1, discardedDate='" + discardedDate + "' where packetID='" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static int markReturnedPacket(String packetID, String returnedID) throws ClassNotFoundException, SQLException {
        String query = "Update BloodPacket set PatientIssueID = NULL, BulkIssueID = NULL, ReturnID='" + returnedID + "' where packetID='" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getDiscardedBloodPacketsByDuration(Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where discardedDate BETWEEN '" + startDate + "' AND '" + endDate + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getDiscardedBloodPacketsByMonth(int month) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where month(discardedDate)='" + month + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getDiscardedBloodPacketsByYear(int year) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where year(discardedDate)='" + year + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
//    public static ResultSet getDiscardedBloodPacketsByMonth(int month) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where month(discardedDate)='" + month + "'";
//        Connection connection = DBConnection.getConnectionToDB();
//        return DBHandler.getData(connection, query);
//    }
//
//    public static ResultSet getDiscardedBloodPacketsByYear(int year) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where year(discardedDate)='" + year + "'";
//        Connection connection = DBConnection.getConnectionToDB();
//        return DBHandler.getData(connection, query);
//    }
    
    public static ResultSet getRecievedBloodPacketsByDuration(Date sqlDateS, Date sqlDateE) throws ClassNotFoundException, SQLException {
        String query = "Select packet.packetID,log.requestee,log.recievedDate,log.recievedTime,packet.bloodType,packet.bloodGroup From bloodrecieveddetail detail inner join BloodPacket packet ON detail.packetID=packet.packetID inner join bloodrecievedlog log ON detail.recievedID = log.recievedID where recievedDate BETWEEN '"+sqlDateS+"' AND '"+sqlDateE+"'";;
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getRecievedBloodPacketsByMonth(int i) throws ClassNotFoundException, SQLException {
        String query = "Select packet.packetID,log.requestee,log.recievedDate,log.recievedTime,packet.bloodType,packet.bloodGroup From bloodrecieveddetail detail inner join BloodPacket packet ON detail.packetID=packet.packetID inner join bloodrecievedlog log ON detail.recievedID = log.recievedID where Month(recievedDate)="+i;
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getRecievedBloodPacketsByYear(int year) throws ClassNotFoundException, SQLException {
        String query = "Select packet.packetID,log.requestee,log.recievedDate,log.recievedTime,packet.bloodType,packet.bloodGroup From bloodrecieveddetail detail inner join BloodPacket packet ON detail.packetID=packet.packetID inner join bloodrecievedlog log ON detail.recievedID = log.recievedID where Year(recievedDate)="+year;
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

//    public static int getInhouseCollectedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where packetFrom = 'Inhouse' AND bloodType='" + bloodType + "' AND dateOfDonation = '" + date + "'";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

//    public static int getMobileCollectedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where packetFrom = 'Campaign' AND bloodType='" + bloodType + "' AND dateOfDonation = '" + date + "'";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

//    public static int getRecievedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where packetFrom = 'Received' AND bloodType='" + bloodType + "' AND dateOfDonation = '" + date + "'";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

//    public static int getReturnedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where isReturned = 1 AND bloodType='" + bloodType + "' AND returnedDate = '" + date + "'";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

//    public static int getIssuedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where isIssued = 1 AND bloodType='" + bloodType + "' AND issuedDate = '" + date + "'";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

//    public static int getDiscardedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where isDiscarded = 1 AND bloodType='" + bloodType + "' AND discardedDate = '" + date + "'";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

    public static int getBloodComponentCount(String bloodType, String bloodGroup) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where bloodType='" + bloodType + "' AND bloodGroup = '" + bloodGroup + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while (rst.next()) {
            count++;
        }
        return count;
    }

//    public static int getUncrossmatchedFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '" + bloodGroup + "' AND isCrossmatched = 0 AND isSpecialReservation = 0 AND isUnderObservation = 0";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

//    public static int getCrossmatchedFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '" + bloodGroup + "' AND isCrossmatched = 1 AND isSpecialReservation = 0 AND isUnderObservation = 0";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

//    public static int getSpecialReservationFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '" + bloodGroup + "' AND isSpecialReservation = 1 AND isUnderObservation = 0";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

//    public static int getUnderObservationFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '" + bloodGroup + "' AND isSpecialReservation = 0 AND isUnderObservation = 1";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        return count;
//    }

//    public static int getUntestedFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
//        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '" + bloodGroup + "'";
//        Connection connection = DBConnection.getConnectionToDB();
//        ResultSet rst = DBHandler.getData(connection, query);
//
//        int count = 0;
//
//        while (rst.next()) {
//            count++;
//        }
//        System.out.println("" + count);
//        return count;
//    }

    public static ResultSet getPacketDetails(String packetID) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where packetID='" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        return rst;
    }

}
