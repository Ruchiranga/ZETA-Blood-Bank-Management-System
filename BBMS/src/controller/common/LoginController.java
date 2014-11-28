/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.common;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ruchiranga
 */
public class LoginController {

    public boolean isValidPassword(int user, char[] password) throws ClassNotFoundException, SQLException {
        String query = "Select Password as StoredPwd,Password('"+String.valueOf(password)+"') as EnteredPwd from user where username = "+user;
        Connection connection = DBConnection.getConnectionToDB();
        ResultSet rst = DBHandler.getData(connection, query);
        rst.next();
        String inDB = rst.getString("StoredPwd");
        if(inDB.isEmpty() && String.valueOf(password).isEmpty()){
            return true;
        }
        String enteredPwd = rst.getString("EnteredPwd");
        return inDB.equals(enteredPwd);
    }

    public int resetPassword(int user, String newPwd) throws SQLException, ClassNotFoundException {
        String query = "UPDATE bbms.`user` SET `Password` = PASSWORD('"+newPwd+"') WHERE `Username` = '"+user+"'";
        Connection connection = DBConnection.getConnectionToDB();
        return DBHandler.setData(connection, query);
    }
    
}
