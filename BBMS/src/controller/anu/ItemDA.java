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
import model.Item;

/**
 *
 * @author Anuradha
 */
public class ItemDA {

    public static int addItem(Item item) throws ClassNotFoundException, SQLException {
        String query = "Insert into Item(itemID,itemType,description,quantity,units,isReagent) values ('" + item.getItemID() + "','" + item.getItemType() + "','" + item.getDescription() + "','" + item.getQuantity() +"','" + item.getUnits()+ "',"+item.getIsReagent()+ ")";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
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
