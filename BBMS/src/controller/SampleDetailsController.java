/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.SampleDetail;

/**
 *
 * @author Nands
 */
public class SampleDetailsController {
    
    public static int addSample(SampleDetail sample) throws ClassNotFoundException, SQLException {
        String query = "Insert into sampledetail(requestno,patientname,bhtno,age,gender,bloodgroup,ward,weight,hospital,diagnosis, reactionhistory,collecteddate,collectedtime,collectedby,priority,date) values ('" + sample.getRequestNo() + "','" + sample.getPatientName() + "','" + sample.getBHTNo() + "','" + sample.getAge() +"','" + sample.getGender() + "','" + sample.getBloodGroup() + "','" + sample.getWard() + "','" + sample.getWeight() + "','"+ sample.getHospital()+ "','"+ sample.getDiagnosis()+ "','" + sample.getReactionHistory() + "','"+sample.getCollectedDate() + "','"+sample.getCollectedTime() + "','"+ sample.getCollectedBy() + "','"+ sample.getPriority()+  "','"+ sample.getRecievedDate() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static ResultSet getAllHospitals() throws ClassNotFoundException, SQLException {
        String query = "Select * From hospital";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getAllRequestNos() throws ClassNotFoundException, SQLException {
        String query = "Select RequestNo From sampledetail";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getAllFilteredRequestNos() throws ClassNotFoundException, SQLException {
        String query = "Select RequestNo From sampledetail where IssueID is NULL AND isCrossmatched = 1";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static ResultSet getAllWards() throws ClassNotFoundException, SQLException {
        String query = "Select * From Ward";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getAllTypes() throws ClassNotFoundException, SQLException{
        String query = "Select * From bloodtype";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    
    
}
