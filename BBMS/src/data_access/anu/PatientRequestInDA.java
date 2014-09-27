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
import model.PatientRequestIn;

/**
 *
 * @author Anuradha
 */
public class PatientRequestInDA {

    public static int addPatientRequest(PatientRequestIn request) throws ClassNotFoundException, SQLException {
        String query = "Insert into PatientRequestIn(RequestID,Hospital,Ward,PatientName,Age,Gender,BHT,BloodType,BloodGroup,NoOfPackets,IndicationOfTransfusion,Date,Time,SendingOfficer,SpecialRemarks) values ('" + request.getRequestID() + "','" + request.getHospital() + "','" + request.getWard() + "','" + request.getPatientName() +"','" + request.getAge() + "','" + request.getGender() + "','" + request.getBht() + "','" + request.getBloodType() + "','"+ request.getBloodGroup() +"','" + request.getNoOfPackets() + "','" + request.getIndicationOfTransfusion()+ "','" + request.getDate() + "','" + request.getTime() +"','"+ request.getSendingOfficer()+ "','"+ request.getSpecialRemarks()+"')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static int updatePatientRequest(PatientRequestIn request) throws ClassNotFoundException, SQLException {
        String query = "Update PatientRequestIn set hospital='" + request.getHospital() + "',ward='" + request.getWard() + "',PatientName='" + request.getPatientName() +"',Age='" + request.getAge() + "',Gender='" + request.getGender() + "',BHT='" + request.getBht() + "',BloodType='" + request.getBloodType() + "',BloodGroup='"+ request.getBloodGroup() +"',NoOfPackets='" + request.getNoOfPackets() + "',IndicationOfTransfusion='" + request.getIndicationOfTransfusion()+ "',Date='" + request.getDate() + "',Time='" + request.getTime() +"',SendingOfficer='"+ request.getSendingOfficer()+ "',SpecialRemarks='"+ request.getSpecialRemarks()+"' where RequestID = '"+request.getRequestID()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static int deletePatientRequest(String requestID) throws ClassNotFoundException, SQLException {

        String query = "Delete from PatientRequestIn where RequestID='" + requestID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

    public static ResultSet getAllRequests(Date date) throws ClassNotFoundException, SQLException {
        String query = "Select * From PatientRequestIn where Date='"+date+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet searchRequest(String requestID) throws ClassNotFoundException, SQLException {
        String query = "Select * From PatientRequestIn where RequestID='"+requestID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

}
