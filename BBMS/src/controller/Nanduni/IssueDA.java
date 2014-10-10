/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Nanduni;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueDA {
    
    public static ResultSet getIssueInfo(String year,String month) throws ClassNotFoundException, SQLException {
        int monthNo=-1;
        if(month.equals("January")){
            monthNo =1;
        }
        else if(month.equals("February")){
            monthNo =2;
        }
        else if(month.equals("March")){
            monthNo =3;
        }
        else if(month.equals("April")){
            monthNo =4;
        }
        else if(month.equals("May")){
            monthNo =5;
        }
        else if(month.equals("June")){
            monthNo =6;
        }
        else if(month.equals("July")){
            monthNo =7;
        }
        else if(month.equals("August")){
            monthNo =8;
        }
        else if(month.equals("September")){
            monthNo =9;
        }
        else if(month.equals("October")){
            monthNo =10;
        }
        else if(month.equals("November")){
            monthNo =11;
        }
        else if(month.equals("December")){
            monthNo =12;
        }
        String query = "SELECT  * FROM issue INNER JOIN issuedetail ON issue.IssueID = issuedetail.IssueID INNER JOIN bloodpacket ON issuedetail.PacketID = bloodpacket.PacketID WHERE Date> '" + year+ "-" + Integer.toString(monthNo) + "-01' AND Date < '" + year+ "-" + Integer.toString(monthNo) + "-31'";
        Connection connection = DBConnection.getConnectionToDB();

        return DBHandler.getData(connection, query);

    }


    public static ResultSet getCrossMatchedInfo(String requestNo) throws ClassNotFoundException, SQLException {

        String query = "SELECT  * FROM crossmatchdetail INNER JOIN bloodpacket ON crossmatchdetail.PacketID = bloodpacket.PacketID INNER JOIN sampledetail ON crossmatchdetail.RequestNo = sampledetail.RequestNo WHERE crossmatchdetail.RequestNo='" + requestNo + "' and bloodpacket.PatientIssueId is NULL";
        Connection connection = DBConnection.getConnectionToDB();

        return DBHandler.getData(connection, query);

    }

    public static ResultSet getSelectedInfo(String packetID) throws ClassNotFoundException, SQLException {

        String query = "SELECT  * FROM crossmatchdetail INNER JOIN bloodpacket ON crossmatchdetail.PacketID = bloodpacket.PacketID INNER JOIN sampledetail ON crossmatchdetail.RequestNo = sampledetail.RequestNo WHERE crossmatchdetail.packetID='" + packetID + "' and bloodpacket.PatientIssueId is NULL";
        Connection connection = DBConnection.getConnectionToDB();

        return DBHandler.getData(connection, query);

    }

    public static ResultSet getPreviousInfo(String requestNo, String packetID) throws ClassNotFoundException, SQLException {
        String query = "SELECT  * FROM crossmatchdetail INNER JOIN bloodpacket ON crossmatchdetail.PacketID = bloodpacket.PacketID INNER JOIN sampledetail ON crossmatchdetail.RequestNo = sampledetail.RequestNo WHERE crossmatchdetail.RequestNo='" + requestNo + "' and bloodpacket.packetID='" + packetID + "' and bloodpacket.PatientIssueId is NULL";
        Connection connection = DBConnection.getConnectionToDB();

        return DBHandler.getData(connection, query);
    }

    public static ResultSet getAllEmployers() throws ClassNotFoundException, SQLException {
        String query = "Select * From Employee";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getResultIDs() throws ClassNotFoundException, SQLException{
        String query = "Select * From issue order by issueID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    
    
    public static void updateIssue(String requestNo, String packetID, String issueID,String employeeid,String date, String time) throws ClassNotFoundException, SQLException {
               
        String query = "Insert into Issue(IssueID,EmpID,Time,Date, sampleID) values ('" + issueID + "','" + employeeid  + "','" + time + "','" + date +"','" + requestNo + "')";
        Connection connection = DBConnection.getConnectionToDB();
        DBHandler.setData(connection, query);
        
        query = "Insert into IssueDetail(IssueID,PacketID) values ('" + issueID + "','" + packetID  + "')";
        connection = DBConnection.getConnectionToDB();
        DBHandler.setData(connection, query);
        
        query = "Update BloodPacket set PatientIssueID = '"+ issueID + "' ,ReturnID = NULL where packetID='" + packetID + "'";
        connection = DBConnection.getConnectionToDB();
        DBHandler.setData(connection, query);        
        
        
    }

    public static ResultSet getEmployeeId(String employer) throws ClassNotFoundException, SQLException{
        String query = "SELECT  * FROM employee WHERE employee.Name='" + employer + "'";
        Connection connection = DBConnection.getConnectionToDB();

        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getIssueInfo() throws ClassNotFoundException, SQLException{
        String query = "SELECT  * FROM bloodpacket INNER JOIN crossmatchdetail ON  crossmatchdetail.packetID = bloodpacket.packetID INNER JOIN Issue ON bloodpacket.patientIssueID = Issue.IssueID INNER JOIN employee ON employee.empID = Issue.empID";
        Connection connection = DBConnection.getConnectionToDB();

        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getPacketInfo() throws ClassNotFoundException, SQLException {

        String query = "SELECT  * FROM bloodpacket";
        Connection connection = DBConnection.getConnectionToDB();

        return DBHandler.getData(connection, query);

    }


    public static ResultSet getRequesteeInfo(String year, String month) throws ClassNotFoundException, SQLException{
        int monthNo=-1;
        if(month.equals("January")){
            monthNo =1;
        }
        else if(month.equals("February")){
            monthNo =2;
        }
        else if(month.equals("March")){
            monthNo =3;
        }
        else if(month.equals("April")){
            monthNo =4;
        }
        else if(month.equals("May")){
            monthNo =5;
        }
        else if(month.equals("June")){
            monthNo =6;
        }
        else if(month.equals("July")){
            monthNo =7;
        }
        else if(month.equals("August")){
            monthNo =8;
        }
        else if(month.equals("September")){
            monthNo =9;
        }
        else if(month.equals("October")){
            monthNo =10;
        }
        else if(month.equals("November")){
            monthNo =11;
        }
        else if(month.equals("December")){
            monthNo =12;
        }
        String query = "SELECT  * FROM issue INNER JOIN sampledetail ON issue.sampleID = sampledetail.requestNo WHERE Date> '" + year+ "-" + Integer.toString(monthNo) + "-01' AND Date < '" + year+ "-" + Integer.toString(monthNo) + "-31'";
        Connection connection = DBConnection.getConnectionToDB();

        return DBHandler.getData(connection, query);

    }
    
    

}
