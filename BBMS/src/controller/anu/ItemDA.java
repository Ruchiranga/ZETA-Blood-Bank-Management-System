/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.anu;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Item;

/**
 *
 * @author Anuradha
 */
public class ItemDA {

    public static int addItem(Item item) throws ClassNotFoundException, SQLException {
        String query = "Insert into Item(itemID,itemType,description,quantity,units,isReagent) values ('" + item.getItemID() + "','" + item.getItemType() + "','" + item.getDescription() + "','" + item.getQty() +"','" + item.getUnits()+ "',"+item.getIsReagent()+ ")";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int updateItem(Item item) throws ClassNotFoundException, SQLException {
        String query = "Update Item set itemType = '"+item.getItemType()+"', description = '"+item.getDescription()+"', quantity='"+item.getQty()+"', units='"+item.getUnits()+"', isReagent="+item.getIsReagent()+" where ItemID = '"+item.getItemID()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static int deleteItem(String itemID) throws ClassNotFoundException, SQLException {
        String query = "Delete from Item where ItemID = '"+itemID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }
    
    public static ResultSet getResultIDs() throws ClassNotFoundException, SQLException {
        String query = "Select * From Item order by ItemID";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getAllItems() throws ClassNotFoundException, SQLException {
        String query = "Select * From Item";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getAllReagentIDs() throws ClassNotFoundException, SQLException {
        String query = "Select * From Item where isReagent="+1;
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet getItem(String itemID) throws ClassNotFoundException, SQLException {
        String query = "Select * From Item where itemID='"+itemID+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.getData(connection, query);
    }

    public static int updateItemQty(String itemID, double qty) throws ClassNotFoundException, SQLException {

        ResultSet rst = getItem(itemID);
        double currentQty = 0;

        while(rst.next()){
            currentQty = rst.getDouble("quantity");
        }

        qty += currentQty;

        String query = "Update Item set quantity='" + qty + "' where itemID='" + itemID + "'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    }

}
