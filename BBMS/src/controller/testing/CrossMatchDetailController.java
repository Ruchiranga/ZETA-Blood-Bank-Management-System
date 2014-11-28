/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.testing;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.SQLException;
import model.CrossMatchDetail;

/**
 *
 * @author ruchiranga
 */
public class CrossMatchDetailController {
    public  int addDetail(CrossMatchDetail detail) throws SQLException, ClassNotFoundException{
        String query = "Insert INTO crossmatchdetail Values ('"+detail.getRequestNo()+"','"+detail.getPacketID()+"','"+detail.getDate()+"','"+detail.getDoneBY()+"')";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }
}
