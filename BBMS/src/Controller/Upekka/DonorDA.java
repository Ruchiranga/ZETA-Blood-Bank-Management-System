/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Upekka;
import connection.DBConnection;
import connection.DBHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Donor;
import model.DonorRegisterRecord;

/**
 *
 * @author Upekka
 */
public class DonorDA {
    public static int addDonor(Donor donor) throws ClassNotFoundException, SQLException {
        String query = "Insert into Donor(nic, name,dob, gender, age,weight, homeAddress, officeAddress, homeTp, officeTp, mobileTp, email, previouslyDonated, difficultiesAfterDonation, goodHealth, diseases, usingMedicine, surgeries, heavyWork, pregnantLactationAbortion, immunized, piercedTatooed, imprisoned, youOrSpouceGoneAbroad, youOrSpouceTakenBlood, sufferedFromYelowFeverHepatitis, sufferedFromTuberculosis, sufferedFromMalaria, sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue, dentalSurgeryUsedAntibioticsMedicine) values ('"+donor.getNic()+"','"+donor.getName()+"','"+donor.getDob()+"','"+donor.getGender()+"',"+donor.getAge()+","+donor.getWeight()+",'"+donor.getHomeAddress()+"','"+donor.getOfficeAddress()+"',"+donor.getHomeTp()+","+donor.getOfficeTp()+","+donor.getMobileTp()+",'"+donor.getEmail()+"',"+donor.getPreviouslyDonated()+",'"+donor.getDifficultiesAfterDonation()+"',"+donor.getGoodHealth()+",'"+donor.getDiseases()+"',"+donor.getUsingMedicine()+","+donor.getSurgeries()+","+donor.getHeavyWork()+","+donor.getPregnantLactationAbortion()+","+donor.getImmunized()+","+donor.getPiercedTatooed()+","+donor.getImprisone()+","+donor.getYouOrSpouceGoneAbroad()+","+donor.getYouOrSpouceTakenBlood()+","+donor.getSufferedFromYelowFeverHepatitis()+","+donor.getSufferedFromTuberculosis()+","+donor.getSufferedFromMalaria()+","+donor.getSufferedFromChickenpoxMeaselsRubellaDiarrheaDengue()+","+donor.getDentalSurgeryUsedAntibioticsMedicine()+")";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    
    }
    
    
    public static int updateDonor(Donor donor) throws ClassNotFoundException, SQLException {
        String query = "Update Donor set name='"+donor.getName()+"',dob='"+donor.getDob()+"', gender='"+donor.getGender()+"', age="+donor.getAge()+",weight="+donor.getWeight()+", homeAddress='"+donor.getHomeAddress()+"', officeAddress='"+donor.getOfficeAddress()+"', homeTp="+donor.getHomeTp()+", officeTp="+donor.getOfficeTp()+", mobileTp="+donor.getMobileTp()+", email='"+donor.getEmail()+"', previouslyDonated="+donor.getPreviouslyDonated()+", difficultiesAfterDonation='"+donor.getDifficultiesAfterDonation()+"', goodHealth="+donor.getGoodHealth()+", diseases='"+donor.getDiseases()+"', usingMedicine="+donor.getUsingMedicine()+", surgeries="+donor.getSurgeries()+", heavyWork="+donor.getHeavyWork()+", pregnantLactationAbortion="+donor.getPregnantLactationAbortion()+", immunized="+donor.getImmunized()+", piercedTatooed="+donor.getPiercedTatooed()+", imprisoned="+donor.getImprisone()+", youOrSpouceGoneAbroad="+donor.getYouOrSpouceGoneAbroad()+", youOrSpouceTakenBlood="+donor.getYouOrSpouceTakenBlood()+", sufferedFromYelowFeverHepatitis="+donor.getSufferedFromYelowFeverHepatitis()+", sufferedFromTuberculosis="+donor.getSufferedFromTuberculosis()+", sufferedFromMalaria="+donor.getSufferedFromMalaria()+", sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue="+donor.getSufferedFromChickenpoxMeaselsRubellaDiarrheaDengue()+", dentalSurgeryUsedAntibioticsMedicine="+donor.getDentalSurgeryUsedAntibioticsMedicine()+" where nic = '"+donor.getNic()+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    
    }
    
    public static Donor searchDonor(String nic) throws ClassNotFoundException, SQLException{
        
            Connection connection = DBConnection.getConnectionToDB();
            Statement stm = connection.createStatement();
            String sql = "Select * From Donor where nic='" + nic + "'";
            ResultSet rst = stm.executeQuery(sql);
            if (rst.next()) {
                Donor newDonor=new Donor(nic, rst.getString("name"),rst.getDate("dob"), rst.getString("gender"), rst.getInt("age"), rst.getInt("weight"), rst.getString("homeAddress"), rst.getString("officeAddress"), rst.getInt("homeTp"), rst.getInt("officeTp"), rst.getInt("mobileTp"), rst.getString("email"), rst.getInt("previouslyDonated"), rst.getString("difficultiesAfterDonation"), rst.getInt("goodHealth"), rst.getString("diseases"), rst.getInt("usingMedicine"), rst.getInt("surgeries"), rst.getInt("heavyWork"), rst.getInt("pregnantLactationAbortion"), rst.getInt("immunized"), rst.getInt("piercedTatooed"), rst.getInt("imprisoned"), rst.getInt("youOrSpouceGoneAbroad"), rst.getInt("youOrSpouceTakenBlood"), rst.getInt("sufferedFromYelowFeverHepatitis"), rst.getInt("sufferedFromTuberculosis"), rst.getInt("sufferedFromMalaria"), rst.getInt("sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue"), rst.getInt("dentalSurgeryUsedAntibioticsMedicine"));
                return newDonor;
            } else {
            return null;
            }   
        
    }
    
    public static int deleteDonor(String nic) throws ClassNotFoundException, SQLException {
        String query = "Delete from Donor  where nic = '"+nic+"'";
        Connection connection = DBConnection.getConnectionToDB();
        int res = DBHandler.setData(connection, query);
        return res;
    
    }
    
    //I have to implement this method
    public static List<DonorRegisterRecord> getListOfDonors() throws ClassNotFoundException, SQLException{
        String query1="Select pkt.DateOfDonation as Date, pkt.PacketID as UnitNo, d.name as DonorName, d.homeAddress as DonorAddress,pkt.Nic as NICNo, d.mobileTp as TelNo,d.age, d.gender as Sex, d.weight, pkt.BloodGroup From Donor d NATURAL JOIN bloodpacket pkt;";
        String query2="select d.nic , count(*) as NoOfDonations From Donor d NATURAL JOIN bloodpacket pkt GROUP BY d.nic;";
        Connection connection = DBConnection.getConnectionToDB();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery(query1);
        List<DonorRegisterRecord> donorRegister = new ArrayList<DonorRegisterRecord>();
        int serialNo=1;
        int noOfDonations=0;
        while (rst.next()) {
                DonorRegisterRecord record=new DonorRegisterRecord(rst.getDate("Date"), serialNo, rst.getString("UnitNo"), rst.getString("DonorName"), rst.getString("DonorAddress"), rst.getString("NICNo"), rst.getInt("TelNo"), rst.getInt("age"), rst.getString("Sex"), rst.getInt("weight"), noOfDonations, rst.getString("BloodGroup"));
                serialNo++; 
                donorRegister.add(record);
            }
        return donorRegister;
    }
    
}
