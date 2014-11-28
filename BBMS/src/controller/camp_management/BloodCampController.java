package controller.camp_management;

import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.BloodCamp;

public class BloodCampController {

    public static int addBloodCamp(BloodCamp bloodCamp) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO bloodcamp (CampID,PLACE, DATE, ExpDonors, Organizer) VALUES('" + bloodCamp.getCampID() + "','" + bloodCamp.getPlace() + "','" + bloodCamp.getDate() + "','" + bloodCamp.getExpDonars() + "','" + bloodCamp.getOrganizer() + "')";
        //Connection connection = returnconnection();
        return DBHandler.setData(DBConnection.getConnectionToDB(), query);

    }

    public static ResultSet addBloodCamp(String pre_month) throws ClassNotFoundException, SQLException {
        String query = "SELECT * from bloodcamp HAVING date >= '" + pre_month + "'";
        return DBHandler.getData(DBConnection.getConnectionToDB(), query);
    }

    public static ResultSet getResultedCampID() throws ClassNotFoundException, SQLException {
        String query = "Select * From bloodcamp order by CampID";
        Connection connection = returnconnection();
        return DBHandler.getData(connection, query);
    }

    public static ResultSet returnSelectedDate(String selectDate, int i) throws SQLException, ClassNotFoundException {
        //0,1 are assing to the passe i sofar
        String query;
        if (i == 0) {
            query = "select DATE(DATE) AS date from bloodcamp HAVING date='" + selectDate + "'";

        } else {
            //query = "select * from bloodcamp bc NATURAL JOIN organizer o where bc.date = '"+selectDate+"'";
            //"select * from bloodcamp left join organizer on bloodcamp.organizer = organizer.Nic where date = '"+selectDate+"'";
            query = "select * from bloodcamp inner join organizer on bloodcamp.organizer = organizer.Nic where date = '" + selectDate + "'";
        }
        return DBHandler.getData(DBConnection.getConnectionToDB(), query);
    }

    //get connectiion result
    public static Connection returnconnection() throws ClassNotFoundException, SQLException {
        return DBConnection.getConnectionToDB();
    }

}
