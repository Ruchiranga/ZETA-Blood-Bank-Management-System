/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data_access.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.BloodPacket;

/**
 *
 * @author Anuradha
 */
public class BloodPacketDA {

    public static int addPacket(BloodPacket packet) throws ClassNotFoundException, SQLException {
        String query = "Insert into BloodPacket(packetID,nic,bloodGroup,bloodType,dateOfDonation,dateOfExpiry,packetFrom,bloodBank,campID) values ('" + packet.getPacketID() + "','" + packet.getNic() + "','" + packet.getBloodGroup() + "','" + packet.getBloodType() +"','" + packet.getDateOfDonation() + "','" + packet.getDateOfExpiry() + "','" + packet.getPacketFrom() + "','" + packet.getBloodBank() + "','"+ packet.getCampID()+ "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getExpiredBloodPackets(Date today) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where dateOfExpiry = '"+today+"' AND isDiscarded = "+0+"";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int discardPacket(String packetID, Date discardedDate) throws ClassNotFoundException, SQLException {
        String query = "Update BloodPacket set isDiscarded=" + 1 + ", discardedDate='" +discardedDate + "' where packetID='" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static ResultSet getDiscardedBloodPacketsByDuration(Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where discardedDate BETWEEN '"+startDate+"' AND '"+endDate+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getDiscardedBloodPacketsByMonth(int month) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where month(discardedDate)='"+month+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getDiscardedBloodPacketsByYear(int year) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where year(discardedDate)='"+year+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int getInhouseCollectedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where packetFrom = 'Inhouse' AND bloodType='"+bloodType+"' AND dateOfDonation = '"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getMobileCollectedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where packetFrom = 'Campaign' AND bloodType='"+bloodType+"' AND dateOfDonation = '"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getRecievedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where packetFrom = 'Received' AND bloodType='"+bloodType+"' AND dateOfDonation = '"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getReturnedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where isReturned = 1 AND bloodType='"+bloodType+"' AND returnedDate = '"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getIssuedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where isIssued = 1 AND bloodType='"+bloodType+"' AND issuedDate = '"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getDiscardedBlood(String bloodType, Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where isDiscarded = 1 AND bloodType='"+bloodType+"' AND discardedDate = '"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getBloodComponentCount(String bloodType, String bloodGroup) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where bloodType='"+bloodType+"' AND bloodGroup = '"+bloodGroup+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getUncrossmatchedFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '"+bloodGroup+"' AND isCrossmatched = 0 AND isSpecialReservation = 0 AND isUnderObservation = 0";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getCrossmatchedFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '"+bloodGroup+"' AND isCrossmatched = 1 AND isSpecialReservation = 0 AND isUnderObservation = 0";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getSpecialReservationFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '"+bloodGroup+"' AND isSpecialReservation = 1 AND isUnderObservation = 0";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getUnderObservationFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '"+bloodGroup+"' AND isSpecialReservation = 0 AND isUnderObservation = 1";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        return count;
    }

    public static int getUntestedFreshBloodCount(String bloodGroup) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodPacket where bloodType='Fresh blood' AND bloodGroup = '"+bloodGroup+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);

        int count = 0;

        while(rst.next()){
            count++;
        }
        System.out.println(""+count);
        return count;
    }

}
