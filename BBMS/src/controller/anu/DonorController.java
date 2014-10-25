/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Donor; 

/**
 *
 * @author Upekka
 */
public class DonorController {
    
    public static ResultSet getAllDonors() throws ClassNotFoundException, SQLException {
        String query = "Select * From Donor";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static String getDonorName(String nic) throws ClassNotFoundException, SQLException {
        String query = "Select * From Donor where nic='"+nic+"'";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        if(rst.next()){
            return rst.getString("name");
        }else{
            return null;
        }
    }
    
    public static int addDonorFromOtherBloodBank(String nic, String name) throws ClassNotFoundException, SQLException {
        String query = "Insert into Donor(nic, name) values ('"+nic+"','"+name+"')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateDonorFromOtherBloodBank(String nic, String name) throws ClassNotFoundException, SQLException {
        String query = "Update Donor set name='"+name+"' where nic='"+nic+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static boolean isNicDuplicate(int nic) throws ClassNotFoundException, SQLException{
        String query = "Select * From Donor";
        String donor_nic = nic+"-";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while(rst.next()){
            if(donor_nic.equalsIgnoreCase(rst.getString("nic")))
                return true;
        }
        return false;
    }
        
}
