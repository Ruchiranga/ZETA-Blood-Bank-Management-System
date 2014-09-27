/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.upekka;

//import java.sql.Date;

import java.sql.Date;
/**
 *
 * @author Upekka
 */
public class DonorNew {
    private String nic;
    private String name;
    private Date dob;
    private String gender;
    private int age;
    private int weight;
    private String homeAddress;
    private String officeAddress;
    private int homeTp;
    private int officeTp;
    private int mobileTp;
    private String email;
    private int previouslyDonated;
    private String difficultiesAfterDonation;
    private int goodHealth;
    private String diseases;
    private int usingMedicine;
    private int surgeries;
    private int heavyWork;
    private int pregnantLactationAbortion;
    private int immunized;
    private int piercedTatooed;
    private int imprisone;
    private int youOrSpouceGoneAbroad;
    private int youOrSpouceTakenBlood;
    private int sufferedFromYelowFeverHepatitis;
    private int sufferedFromTuberculosis;
    private int sufferedFromMalaria;
    private int sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue;
    private int dentalSurgeryUsedAntibioticsMedicine;
    private boolean blacklisted;

    public DonorNew(String nic, String name, Date dob, String gender, int age,int weight, String homeAddress, String officeAddress, int homeTp, int officeTp, int mobileTp, String email, int previouslyDonated, String difficultiesAfterDonation, int goodHealth, String diseases, int usingMedicine, int surgeries, int heavyWork, int pregnantLactationAbortion, int immunized, int piercedTatooed, int imprisone, int youOrSpouceGoneAbroad, int youOrSpouceTakenBlood,int sufferedFromYelowFeverHepatitis, int sufferedFromTuberculosis, int sufferedFromMalaria, int sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue, int dentalSurgeryUsedAntibioticsMedicine) {
        this.nic = nic;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.age = age;
        this.weight=weight;
        this.homeAddress = homeAddress;
        this.officeAddress = officeAddress;
        this.homeTp = homeTp;
        this.officeTp = officeTp;
        this.mobileTp = mobileTp;
        this.email = email;
        this.previouslyDonated = previouslyDonated;
        this.difficultiesAfterDonation = difficultiesAfterDonation;
        this.goodHealth = goodHealth;
        this.diseases = diseases;
        this.usingMedicine = usingMedicine;
        this.surgeries = surgeries;
        this.heavyWork = heavyWork;
        this.pregnantLactationAbortion = pregnantLactationAbortion;
        this.immunized = immunized;
        this.piercedTatooed = piercedTatooed;
        this.imprisone = imprisone;
        this.youOrSpouceGoneAbroad = youOrSpouceGoneAbroad;
        this.youOrSpouceTakenBlood = youOrSpouceTakenBlood;
        this.sufferedFromYelowFeverHepatitis=sufferedFromYelowFeverHepatitis;
        this.sufferedFromTuberculosis = sufferedFromTuberculosis;
        this.sufferedFromMalaria = sufferedFromMalaria;
        this.sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue = sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue;
        this.dentalSurgeryUsedAntibioticsMedicine = dentalSurgeryUsedAntibioticsMedicine;
        this.blacklisted = false;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the homeAddress
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * @param homeAddress the homeAddress to set
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * @return the officeAddress
     */
    public String getOfficeAddress() {
        return officeAddress;
    }

    /**
     * @param officialAddress the officeAddress to set
     */
    public void setOfficeAddress(String officialAddress) {
        this.officeAddress = officialAddress;
    }

    /**
     * @return the homeTp
     */
    public int getHomeTp() {
        return homeTp;
    }

    /**
     * @param homeTp the homeTp to set
     */
    public void setHomeTp(int homeTp) {
        this.homeTp = homeTp;
    }

    /**
     * @return the officeTp
     */
    public int getOfficeTp() {
        return officeTp;
    }

    /**
     * @param officeTp the officeTp to set
     */
    public void setOfficeTp(int officeTp) {
        this.officeTp = officeTp;
    }

    /**
     * @return the mobileTp
     */
    public int getMobileTp() {
        return mobileTp;
    }

    /**
     * @param mobileTp the mobileTp to set
     */
    public void setMobileTp(int mobileTp) {
        this.mobileTp = mobileTp;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the previouslyDonated
     */
    public int getPreviouslyDonated() {
        return previouslyDonated;
    }

    /**
     * @param previouslyDonated the previouslyDonated to set
     */
    public void setPreviouslyDonated(int previouslyDonated) {
        this.previouslyDonated = previouslyDonated;
    }

    /**
     * @return the difficultiesAfterDonation
     */
    public String getDifficultiesAfterDonation() {
        return difficultiesAfterDonation;
    }

    /**
     * @param difficultiesAfterDonation the difficultiesAfterDonation to set
     */
    public void setDifficultiesAfterDonation(String difficultiesAfterDonation) {
        this.difficultiesAfterDonation = difficultiesAfterDonation;
    }

    /**
     * @return the goodHealth
     */
    public int getGoodHealth() {
        return goodHealth;
    }

    /**
     * @param goodHealth the goodHealth to set
     */
    public void setGoodHealth(int goodHealth) {
        this.goodHealth = goodHealth;
    }

    /**
     * @return the diseases
     */
    public String getDiseases() {
        return diseases;
    }

    /**
     * @param diseases the diseases to set
     */
    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    /**
     * @return the usingMedicine
     */
    public int getUsingMedicine() {
        return usingMedicine;
    }

    /**
     * @param usingMedicine the usingMedicine to set
     */
    public void setUsingMedicine(int usingMedicine) {
        this.usingMedicine = usingMedicine;
    }

    /**
     * @return the surgeries
     */
    public int getSurgeries() {
        return surgeries;
    }

    /**
     * @param surgeries the surgeries to set
     */
    public void setSurgeries(int surgeries) {
        this.surgeries = surgeries;
    }

    /**
     * @return the heavyWork
     */
    public int getHeavyWork() {
        return heavyWork;
    }

    /**
     * @param heavyWork the heavyWork to set
     */
    public void setHeavyWork(int heavyWork) {
        this.heavyWork = heavyWork;
    }

    /**
     * @return the pregnantLactationAbortion
     */
    public int getPregnantLactationAbortion() {
        return pregnantLactationAbortion;
    }

    /**
     * @param pregnantLactationAbortion the pregnantLactationAbortion to set
     */
    public void setPregnantLactationAbortion(int pregnantLactationAbortion) {
        this.pregnantLactationAbortion = pregnantLactationAbortion;
    }

    /**
     * @return the immunized
     */
    public int getImmunized() {
        return immunized;
    }

    /**
     * @param immunized the immunized to set
     */
    public void setImmunized(int immunized) {
        this.immunized = immunized;
    }

    /**
     * @return the piercedTatooed
     */
    public int getPiercedTatooed() {
        return piercedTatooed;
    }

    /**
     * @param piercedTatooed the piercedTatooed to set
     */
    public void setPiercedTatooed(int piercedTatooed) {
        this.piercedTatooed = piercedTatooed;
    }

    /**
     * @return the imprisone
     */
    public int getImprisone() {
        return imprisone;
    }

    /**
     * @param imprisone the imprisone to set
     */
    public void setImprisone(int imprisone) {
        this.imprisone = imprisone;
    }

    /**
     * @return the youOrSpouceGoneAbroad
     */
    public int getYouOrSpouceGoneAbroad() {
        return youOrSpouceGoneAbroad;
    }

    /**
     * @param youOrSpouceGoneAbroad the youOrSpouceGoneAbroad to set
     */
    public void setYouOrSpouceGoneAbroad(int youOrSpouceGoneAbroad) {
        this.youOrSpouceGoneAbroad = youOrSpouceGoneAbroad;
    }

    /**
     * @return the youOrSpouceTakenBlood
     */
    public int getYouOrSpouceTakenBlood() {
        return youOrSpouceTakenBlood;
    }

    /**
     * @param youOrSpouceTakenBlood the youOrSpouceTakenBlood to set
     */
    public void setYouOrSpouceTakenBlood(int youOrSpouceTakenBlood) {
        this.youOrSpouceTakenBlood = youOrSpouceTakenBlood;
    }

    /**
     * @return the sufferedFromTuberculosis
     */
    public int getSufferedFromTuberculosis() {
        return sufferedFromTuberculosis;
    }

    /**
     * @param sufferedFromTuberculosis the sufferedFromTuberculosis to set
     */
    public void setSufferedFromTuberculosis(int sufferedFromTuberculosis) {
        this.sufferedFromTuberculosis = sufferedFromTuberculosis;
    }

    /**
     * @return the sufferedFromMalaria
     */
    public int getSufferedFromMalaria() {
        return sufferedFromMalaria;
    }

    /**
     * @param sufferedFromMalaria the sufferedFromMalaria to set
     */
    public void setSufferedFromMalaria(int sufferedFromMalaria) {
        this.sufferedFromMalaria = sufferedFromMalaria;
    }

    /**
     * @return the sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue
     */
    public int getSufferedFromChickenpoxMeaselsRubellaDiarrheaDengue() {
        return sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue;
    }

    /**
     * @param sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue the sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue to set
     */
    public void setSufferedFromChickenpoxMeaselsRubellaDiarrheaDengue(int sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue) {
        this.sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue = sufferedFromChickenpoxMeaselsRubellaDiarrheaDengue;
    }

    /**
     * @return the dentalSurgeryUsedAntibioticsMedicine
     */
    public int getDentalSurgeryUsedAntibioticsMedicine() {
        return dentalSurgeryUsedAntibioticsMedicine;
    }

    /**
     * @param dentalSurgeryUsedAntibiotics_Medicine the dentalSurgeryUsedAntibioticsMedicine to set
     */
    public void setDentalSurgeryUsedAntibioticsMedicine(int dentalSurgeryUsedAntibiotics_Medicine) {
        this.dentalSurgeryUsedAntibioticsMedicine = dentalSurgeryUsedAntibiotics_Medicine;
    }

    /**
     * @return the blacklisted
     */
    public boolean isBlacklisted() {
        return blacklisted;
    }

    /**
     * @param blacklisted the blacklisted to set
     */
    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }

    /**
     * @return the sufferedFromYelowFeverHepatitis
     */
    public int getSufferedFromYelowFeverHepatitis() {
        return sufferedFromYelowFeverHepatitis;
    }

    /**
     * @param sufferedFromYelowFeverHepatitis the sufferedFromYelowFeverHepatitis to set
     */
    public void setSufferedFromYelowFeverHepatitis(int sufferedFromYelowFeverHepatitis) {
        this.sufferedFromYelowFeverHepatitis = sufferedFromYelowFeverHepatitis;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    
}
