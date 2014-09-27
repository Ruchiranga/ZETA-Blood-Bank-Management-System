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
import model.upekka.DonorNew;

/**
 *
 * @author Upekka
 */
public class DonorDA {
    public static int addDonor(DonorNew donor) throws ClassNotFoundException, SQLException {
        String query = "Insert into Donor(nic, name,dob, gender, age, homeAddress, officeAddress, homeTp, officeTp, mobileTp, email, previouslyDonated, difficultiesAfterDonation, goodHealth, diseases, usingMedicine, surgeries, heavyWork, pregnantLactationAbortion, immunized, piercedTatooed, imprisoned, youOrSpouceGoneAbroad, youOrSpouceTakenBlood, sufferedFromYelowFeverHepatitis, sufferedFromTuberculosis, sufferedFromMalaria, sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue, dentalSurgeryUsedAntibioticsMedicine) values ('"+donor.getNic()+"','"+donor.getName()+"','"+donor.getDob()+"','"+donor.getGender()+"',"+donor.getAge()+",'"+donor.getHomeAddress()+"','"+donor.getOfficeAddress()+"',"+donor.getHomeTp()+","+donor.getOfficeTp()+","+donor.getMobileTp()+",'"+donor.getEmail()+"',"+donor.getPreviouslyDonated()+",'"+donor.getDifficultiesAfterDonation()+"',"+donor.getGoodHealth()+",'"+donor.getDiseases()+"',"+donor.getUsingMedicine()+","+donor.getSurgeries()+","+donor.getHeavyWork()+","+donor.getPregnantLactationAbortion()+","+donor.getImmunized()+","+donor.getPiercedTatooed()+","+donor.getImprisone()+","+donor.getYouOrSpouceGoneAbroad()+","+donor.getYouOrSpouceTakenBlood()+","+donor.getSufferedFromYelowFeverHepatitis()+","+donor.getSufferedFromTuberculosis()+","+donor.getSufferedFromMalaria()+","+donor.getSufferedFromChickenpoxMeaselsRubellaDiarrheaDengue()+","+donor.getDentalSurgeryUsedAntibioticsMedicine()+")";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    
    }
        
}
