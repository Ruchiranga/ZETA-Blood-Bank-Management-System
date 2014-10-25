/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.BloodReceivedLog;

/**
 *
 * @author Anuradha
 */
public class BloodRecievedLogController {

    public static ResultSet getResultIDs() throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodRecievedLog order by RecievedID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int addLog(BloodReceivedLog log) throws ClassNotFoundException, SQLException {
        String query = "Insert into BloodRecievedLog(RecievedID,Requestee,RecievedDate,RecievedTime,SendingOfficer,RecievingOfficerID,Tempereture,IcePacketCobdition) values ('" + log.getReceivedID() + "','" + log.getRequestee() + "','" + log.getReceivedDate() + "','" + log.getReceivedTime() + "','" + log.getSendignOfficer() + "','" + log.getReceivingOfficer() + "'," + log.getTemperature() + ",'" + log.getIcePaceketCondition() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateLog(BloodReceivedLog log) throws ClassNotFoundException, SQLException {
        String query = "Update BloodRecievedLog set Requestee = '"+log.getRequestee()+"',RecievedDate='"+log.getReceivedDate()+"',RecievedTime='"+log.getReceivedTime()+"',SendingOfficer='"+log.getSendignOfficer()+"',RecievingOfficerID='"+log.getReceivingOfficer()+"',Tempereture='"+log.getTemperature()+"',IcePacketCobdition='"+log.getIcePaceketCondition()+"' where RecievedID='"+log.getReceivedID()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int deleteRecieval(String recievalID) throws ClassNotFoundException, SQLException {
        String query = "Delete from BloodRecievedLog where RecievedID='" + recievalID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static int deletePacketRecievalData(String packetID) throws ClassNotFoundException, SQLException {
        String queryTest = "Delete from TestResult where PacketID='" + packetID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int resultTest = DBHandler.setData(connection, queryTest);
        
        String queryRecievedDetail = "Delete from BloodRecievedDetail where PacketID='" + packetID + "'";
        int resQueryRecievedDetail = DBHandler.setData(connection, queryRecievedDetail);

        String donorNic = BloodPacketController.getDonorNic(packetID);

        int resPacket = 0;
        boolean packetCantDelete = false;
        try{
        String queryPacket = "Delete from BloodPacket where PacketID='" + packetID + "'";
        resPacket = DBHandler.setData(connection, queryPacket);
        }catch(SQLException e){
            System.out.println("Packet exists in other records");
            packetCantDelete = true;
        }

        int resDonor = 0;
        boolean donorCantDelete = false;
        try {
            String queryDonor = "Delete from Donor where Nic='" + donorNic + "'";
            resDonor = DBHandler.setData(connection, queryDonor);
        } catch (SQLException e) {
            System.out.println("Donor exists in other records");
            donorCantDelete = true;
            
        }
        
        System.out.println("RecievedDetail : "+resQueryRecievedDetail);
        System.out.println("Packet : "+resPacket);
        System.out.println("Donor : "+resDonor);
        
        if(packetCantDelete && donorCantDelete){
            if ((resQueryRecievedDetail) == 1) {
                return 1;
            } else {
                return -1;
            }
        }else if(packetCantDelete){
            if ((resQueryRecievedDetail + resDonor) == 2) {
                return 1;
            } else {
                return -1;
            }
        }else if(donorCantDelete){
            if ((resQueryRecievedDetail + resPacket) == 2) {
                return 1;
            } else {
                return -1;
            }
        }else{
            if ((resQueryRecievedDetail + resPacket + resDonor) == 3) {
                return 1;
            } else {
                return -1;
            }
        }
        
        

        
    }

    public static ResultSet getSenders(Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodRecievedLog where RecievedDate='" + date + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getRecievedIDsByDateAndSender(Date date, String sender) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodRecievedLog where RecievedDate='" + date + "' AND Requestee='" + sender + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getDetails(String id) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodRecievedLog R NATURAL JOIN Employee E where RecievedID = '" + id + "' AND R.RecievingOfficerID = E.EmpID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

}
