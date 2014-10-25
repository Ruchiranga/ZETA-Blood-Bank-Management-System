/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet; 
import java.sql.SQLException;
import model.BloodGroup;

/**
 *
 * @author Anuradha
 */
public class BloodGroupController {

    public static int addGroup(BloodGroup group) throws ClassNotFoundException, SQLException {
        String query = "Insert into BloodGroup(groupName) values ('" +group.getBloodgroup() + "')";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

    public static int updateGroup(BloodGroup oldGroup, BloodGroup newGroup) throws ClassNotFoundException, SQLException {

        String query = "update BloodGroup set GroupName='" + newGroup.getBloodgroup() + "' where GroupName='" + oldGroup.getBloodgroup() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
        
    }

    public static int deleteGroup(BloodGroup group) throws ClassNotFoundException, SQLException {

        String query = "Delete from BloodGroup where GroupName='" + group.getBloodgroup() + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;

    }

    public static ResultSet getAllGroups() throws ClassNotFoundException, SQLException {
        String query = "Select * From BloodGroup";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }
    
    public static int getGroupCount() throws ClassNotFoundException, SQLException {
        String query = "Select count(*) From BloodGroup";
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        while(rst.next()){
            return rst.getInt("count(*)");
        }
        return -1;
    }

}
