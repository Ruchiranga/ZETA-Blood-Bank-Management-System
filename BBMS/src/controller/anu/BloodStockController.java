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
import model.Balance;
import model.BloodStockHistory;
import model.ComponentStockHistory; 

/**
 *
 * @author Anuradha
 */
public class BloodStockController {

    public static int addBloodStockHistory(BloodStockHistory history) throws ClassNotFoundException, SQLException {
        String query;
        if (history.getSpec_res() >= 0 && history.getUnderobs() >= 0 && history.getUnx() >= 0 && history.getX() >= 0) {
            query = "Insert into BloodStockHistory(StockDate,BloodGroup,Unx,x,Spec_res,Underobs,total) values ('" + history.getStockDate() + "','" + history.getBloodGroup() + "','" + history.getUnx() + "','" + history.getX() + "','" + history.getSpec_res() + "','" + history.getUnderobs() + "','" + history.getTotal() + "')";
        } else {
            query = "Insert into BloodStockHistory(StockDate,BloodGroup,total) values ('" + history.getStockDate() + "','" + history.getBloodGroup() + "','" + history.getTotal() + "')";
        }
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static int updateBloodStockHistory(BloodStockHistory history) throws ClassNotFoundException, SQLException {
        String query;
        if(history.getSpec_res() >= 0 && history.getUnderobs() >= 0 && history.getUnx() >= 0 && history.getX() >= 0){
            query = "Update BloodStockHistory set Unx = '"+history.getUnx()+"', x='"+history.getX()+"', Spec_res = '"+history.getSpec_res()+"', Underobs = '"+history.getUnderobs()+"', total='"+history.getTotal()+"' where StockDate ='" +history.getStockDate() + "' AND BloodGroup = '"+history.getBloodGroup()+"'";
        }
        else{
            query = "Update BloodStockHistory set total = '"+history.getTotal()+"'  where StockDate ='" +history.getStockDate() + "' AND BloodGroup = '"+history.getBloodGroup()+"'";        
        }
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int addComponentStockHistory(ComponentStockHistory history) throws ClassNotFoundException, SQLException {
        String query = "Insert into ComponentStockHistory(StockDate,BloodType,BloodGroup,qty) values ('" + history.getStockDate() + "','" + history.getBloodType() + "','" + history.getBloodGroup() + "','" + history.getQty() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateComponentStockHistory(ComponentStockHistory history) throws ClassNotFoundException, SQLException {
        String query = "Update ComponentStockHistory set qty ='"+history.getQty()+"' where StockDate ='" +history.getStockDate() + "' AND BloodGroup = '"+history.getBloodGroup()+"' AND BloodType ='"+history.getBloodType()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int addBalanceHistory(Balance balance) throws ClassNotFoundException, SQLException {
        String query = "Insert into Balance(StockDate,BloodType,Balance) values ('" + balance.getStockDate() + "','" + balance.getBloodType() + "','" + balance.getBalance() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateBalanceHistory(Balance balance) throws ClassNotFoundException, SQLException {
        String query = "Update Balance set Balance ='"+balance.getBalance()+"' where StockDate ='" +balance.getStockDate() + "' AND BloodType ='"+balance.getBloodType()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static ResultSet getBloodStockHistory(Date stockdate) throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodStockHistory where StockDate = '" + stockdate + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);

    }

    public static ResultSet getComponentStockHistory(Date stockdate) throws ClassNotFoundException, SQLException {
        String query = "Select * From ComponentStockHistory where StockDate = '" + stockdate + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);

    }
    
    public static int getDailyComponentStockHistory(Date stockdate, String type, String group) throws ClassNotFoundException, SQLException {
        String query = "Select * From ComponentStockHistory where StockDate = '" + stockdate + "' AND BloodType = '"+type+"' AND BloodGroup='"+group+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        
        while (rst.next()) {
            int qty = rst.getInt("qty");
            return qty;
        }
        return -1;

    }

    public static ResultSet getBalanceStockHistory(Date stockdate) throws ClassNotFoundException, SQLException {
        String query = "Select * From Balance where StockDate = '" + stockdate + "'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int getBalanceStockHistoryByType(Date stockdate, String type) throws ClassNotFoundException, SQLException {
        String query = "Select * From Balance where StockDate = '" + stockdate + "' AND BloodType = '" + type + "'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("Balance");
        }
        return -1;
    }

    

    

    public static boolean isBloodStockEntered(java.sql.Date date) throws ClassNotFoundException, SQLException {
        String query1 = "Select * From BloodStockHistory where StockDate = '" + date + "'";
        String query2 = "Select * From ComponentStockHistory where StockDate = '" + date + "'";
        String query3 = "Select * from Balance where StockDate ='" + date + "'";

        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst1 = DBHandler.getData(connection, query1);
        ResultSet rst2 = DBHandler.getData(connection, query2);
        ResultSet rst3 = DBHandler.getData(connection, query3);

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        while (rst1.next()) {
            count1++;
        }
        while (rst2.next()) {
            count2++;
        }
        while (rst3.next()) {
            count3++;
        }
        if (count1 > 0 && count2 > 0 && count3 > 0) {
            return true;
        } else {
            return false;
        }

    }

    public static int getComponenetPacketCount(String bloodType, String bloodGroup) throws ClassNotFoundException, SQLException {
        String query = "Select count(*) From BloodPacket B NATURAL JOIN BloodGroup G NATURAL JOIN BloodType T where B.BloodGroup = '" + bloodGroup + "' AND B.BloodType = '" + bloodType + "' AND B.isDiscarded = 0 AND B.PatientIssueID IS NULL AND B.BulkIssueID is NULL AND B.BloodGroup = G.GroupName AND B.BloodType = T.BloodType";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;

    }

    public static int getUnX(String bloodGroup, String bloodType) throws ClassNotFoundException, SQLException {
        String query = "Select count(*) From BloodPacket B NATURAL JOIN BloodGroup G where B.BloodGroup = '" + bloodGroup + "' AND B.BloodType = '" + bloodType + "' AND B.isSpecialReservation = 0 AND B.isUnderObservation = 0 AND B.IsCrossmatched = 0 AND B.isDiscarded = 0 AND B.PatientIssueID IS NULL AND B.BulkIssueID is NULL AND B.BloodGroup = G.GroupName";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;

    }

    public static int getX(String bloodGroup, String bloodType) throws ClassNotFoundException, SQLException {
        String query = "Select count(*) From BloodPacket B NATURAL JOIN BloodGroup G where B.BloodGroup = '" + bloodGroup + "' AND B.BloodType = '" + bloodType + "' AND B.isSpecialReservation = 0 AND B.isUnderObservation = 0 AND B.IsCrossmatched = 1 AND B.isDiscarded = 0 AND B.PatientIssueID IS NULL AND B.BulkIssueID is NULL AND B.BloodGroup = G.GroupName";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }

    public static int getSpecialReservation(String bloodGroup, String bloodType) throws ClassNotFoundException, SQLException {
        String query = "Select count(*) From BloodPacket B NATURAL JOIN BloodGroup G where B.BloodGroup = '" + bloodGroup + "' AND B.BloodType = '" + bloodType + "' AND B.isSpecialReservation = 1 AND B.isDiscarded = 0 AND B.PatientIssueID IS NULL AND B.BulkIssueID is NULL AND B.BloodGroup = G.GroupName";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }

    public static int getUnderObservation(String bloodGroup, String bloodType) throws ClassNotFoundException, SQLException {
        String query = "Select count(*) From BloodPacket B NATURAL JOIN BloodGroup G where B.BloodGroup = '" + bloodGroup + "' AND B.BloodType = '" + bloodType + "' AND B.isUnderObservation = 1 AND B.isDiscarded = 0 AND B.PatientIssueID IS NULL AND B.BulkIssueID is NULL AND B.BloodGroup = G.GroupName";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }

    public static int getUntestedTotal(String bloodGroup, String bloodType) throws ClassNotFoundException, SQLException {
        String query = "Select count(*) From BloodPacket B NATURAL JOIN BloodGroup G where B.BloodGroup = '" + bloodGroup + "' AND B.BloodType = '" + bloodType + "' AND B.isDiscarded = 0 AND B.PatientIssueID IS NULL AND B.BulkIssueID is NULL AND B.BloodGroup = G.GroupName";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }
    
    public static int getInhouseBloodCount(String bloodType, Date date) throws ClassNotFoundException, SQLException{
        String query = "Select count(*) From BloodPacket where BloodType = '" + bloodType + "' AND recievedID IS NULL AND DateOfDonation = '"+date+"' AND CampID IS NULL AND isDiscarded = 0 AND ReturnID IS NULL AND PatientIssueID IS NULL AND BulkIssueID IS NULL";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }
    
    public static int getMobileBloodCount(String bloodType, Date date) throws ClassNotFoundException, SQLException{
        String query = "Select count(*) From BloodPacket where BloodType = '" + bloodType + "' AND recievedID IS NULL AND DateOfDonation = '"+date+"' AND CampID IS NOT NULL AND isDiscarded = 0 AND ReturnID IS NULL AND PatientIssueID IS NULL AND BulkIssueID IS NULL";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }
    
    public static int getReturnBloodCount(String bloodType, Date date) throws ClassNotFoundException, SQLException{
        String query = "Select count(*) From BloodPacket B INNER JOIN ReturnedLog R where BloodType = '" + bloodType + "' AND isDiscarded = 0 AND ReturnID IS NOT NULL AND B.PatientIssueID IS NULL AND B.BulkIssueID IS NULL AND R.ReturnedDate = '"+date+"' AND B.ReturnID = R.ReturnedID";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }
    
    public static int getRecievedBloodCount(String bloodType, Date date) throws ClassNotFoundException, SQLException{
        String query = "Select count(*) From BloodPacket B NATURAL JOIN BloodRecievedLog R where BloodType = '" + bloodType + "' AND isDiscarded = 0 AND ReturnID IS NULL AND B.PatientIssueID IS NULL AND B.BulkIssueID IS NULL AND R.RecievedDate = '"+date+"' AND B.RecievedID = R.RecievedID";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }
    
    public static int getPatientIssuedBloodCount(String bloodType, Date date) throws ClassNotFoundException, SQLException{
        
        //Select * from BloodPacket B NATURAL JOIN IssueDetail I NATURAL JOIN Issue S where B.PacketID = I.PacketID AND I.IssueID = S.IssueID;
        String query = "Select count(*) From BloodPacket B NATURAL JOIN IssueDetail I NATURAL JOIN Issue S where BloodType = '" + bloodType + "' AND isDiscarded = 0 AND B.PatientIssueID IS NOT NULL AND B.BulkIssueID IS NULL AND S.Date = '"+date+"' AND B.PacketID = I.PacketID AND I.IssueID = S.IssueID";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }
    
    public static int getBulkIssuedBloodCount(String bloodType, Date date) throws ClassNotFoundException, SQLException{
        
        //Select * from BloodPacket B NATURAL JOIN IssueDetail I NATURAL JOIN Issue S where B.PacketID = I.PacketID AND I.IssueID = S.IssueID;
        String query = "Select count(*) From BloodPacket B NATURAL JOIN BulkIssueDetail I NATURAL JOIN BulkIssue S where BloodType = '" + bloodType + "' AND isDiscarded = 0 AND B.PatientIssueID IS NULL AND B.BulkIssueID IS NOT NULL AND S.Date = '"+date+"' AND B.PacketID = I.PacketID AND I.IssueID = S.IssueID";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }
    
    public static int getDiscardedBloodCount(String bloodType, Date date) throws ClassNotFoundException, SQLException{
        String query = "Select count(*) From BloodPacket where BloodType = '" + bloodType + "' AND isDiscarded = 1 AND DiscardedDate = '"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while (rst.next()) {
            return rst.getInt("count(*)");
        }
        return -1;
    }

}
