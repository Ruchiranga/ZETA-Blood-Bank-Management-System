/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess.upekka;
import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.upekka.DonorNew;

/**
 *
 * @author Upekka
 */
public class DonorDA {
    public static int addDonor(DonorNew donor) throws ClassNotFoundException, SQLException {
        String query = "Insert into Donor(nic, name,dob, gender, age,weight, homeAddress, officeAddress, homeTp, officeTp, mobileTp, email, previouslyDonated, difficultiesAfterDonation, goodHealth, diseases, usingMedicine, surgeries, heavyWork, pregnantLactationAbortion, immunized, piercedTatooed, imprisoned, youOrSpouceGoneAbroad, youOrSpouceTakenBlood, sufferedFromYelowFeverHepatitis, sufferedFromTuberculosis, sufferedFromMalaria, sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue, dentalSurgeryUsedAntibioticsMedicine) values ('"+donor.getNic()+"','"+donor.getName()+"','"+donor.getDob()+"','"+donor.getGender()+"',"+donor.getAge()+","+donor.getWeight()+",'"+donor.getHomeAddress()+"','"+donor.getOfficeAddress()+"',"+donor.getHomeTp()+","+donor.getOfficeTp()+","+donor.getMobileTp()+",'"+donor.getEmail()+"',"+donor.getPreviouslyDonated()+",'"+donor.getDifficultiesAfterDonation()+"',"+donor.getGoodHealth()+",'"+donor.getDiseases()+"',"+donor.getUsingMedicine()+","+donor.getSurgeries()+","+donor.getHeavyWork()+","+donor.getPregnantLactationAbortion()+","+donor.getImmunized()+","+donor.getPiercedTatooed()+","+donor.getImprisone()+","+donor.getYouOrSpouceGoneAbroad()+","+donor.getYouOrSpouceTakenBlood()+","+donor.getSufferedFromYelowFeverHepatitis()+","+donor.getSufferedFromTuberculosis()+","+donor.getSufferedFromMalaria()+","+donor.getSufferedFromChickenpoxMeaselsRubellaDiarrheaDengue()+","+donor.getDentalSurgeryUsedAntibioticsMedicine()+")";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    
    }
    
    public static DonorNew searchDonor(String nic) throws ClassNotFoundException, SQLException{
        
            Connection connection = DBConnection.getConnectionToDB();
            Statement stm = connection.createStatement();
            String sql = "Select * From Donor where nic='" + nic + "'";
            ResultSet rst = stm.executeQuery(sql);
            if (rst.next()) {
                DonorNew newDonor=new DonorNew(nic, rst.getString("name"),rst.getDate("dob"), rst.getString("gender"), rst.getInt("age"), rst.getInt("weight"), rst.getString("homeAddress"), rst.getString("officeAddress"), rst.getInt("homeTp"), rst.getInt("officeTp"), rst.getInt("mobileTp"), rst.getString("email"), rst.getInt("previouslyDonated"), rst.getString("difficultiesAfterDonation"), rst.getInt("goodHealth"), rst.getString("diseases"), rst.getInt("usingMedicine"), rst.getInt("surgeries"), rst.getInt("heavyWork"), rst.getInt("pregnantLactationAbortion"), rst.getInt("immunized"), rst.getInt("piercedTatooed"), rst.getInt("imprisoned"), rst.getInt("youOrSpouceGoneAbroad"), rst.getInt("youOrSpouceTakenBlood"), rst.getInt("sufferedFromYelowFeverHepatitis"), rst.getInt("sufferedFromTuberculosis"), rst.getInt("sufferedFromMalaria"), rst.getInt("sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue"), rst.getInt("dentalSurgeryUsedAntibioticsMedicine"));
                return newDonor;
            } else {
            return null;
            }   
        
    }
}
