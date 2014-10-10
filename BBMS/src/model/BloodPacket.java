/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Anuradha
 */
public class BloodPacket {

    private String packetID;
    private String nic;    
    private Date dateOfDonation;
    private Date dateOfExpiry;
    private String bloodType; //FreshBlood, FFP, CRYO, Plasma, Platelets
    private byte isCrossmatched;
    private byte isSpecialReservation;
    private byte isUnderObservation;  
    private String campID;      
    private byte isDiscarded;
    private String bloodGroup;
    private Date discardedDate;
    private String returnID;
    private String patientIssueID;
    private String comment;
    private String bullkissueID;
    private String recievedID;

    public BloodPacket(String packetID, String nic, Date dateOfDonation, Date dateOfExpiry, String bloodType, byte isCrossmatched, byte isSpecialReservation, byte isUnderObservation, String campID, byte isDiscarded, String bloodGroup, Date discardedDate, String returnID, String patientIssueID, String comment, String bullkissueID, String recievedID) {
        this.packetID = packetID;
        this.nic = nic;
        this.dateOfDonation = dateOfDonation;
        this.dateOfExpiry = dateOfExpiry;
        this.bloodType = bloodType;
        this.isCrossmatched = isCrossmatched;
        this.isSpecialReservation = isSpecialReservation;
        this.isUnderObservation = isUnderObservation;
        this.campID = campID;
        this.isDiscarded = isDiscarded;
        this.bloodGroup = bloodGroup;
        this.discardedDate = discardedDate;
        this.returnID = returnID;
        this.patientIssueID = patientIssueID;
        this.comment = comment;
        this.bullkissueID = bullkissueID;
        this.recievedID = recievedID;
    }
    
    public BloodPacket(String packetID, String bloodGroup, String bloodType, String nic, Date dateOfExpiry, Date dateOfDonation, byte isCrossmatched, byte isUnderObservation) {
        this.packetID = packetID;
        this.nic = nic;
        this.dateOfDonation = dateOfDonation;
        this.dateOfExpiry = dateOfExpiry;
        this.bloodType = bloodType;
        this.isCrossmatched = isCrossmatched;
        this.isUnderObservation = isUnderObservation;
        this.bloodGroup = bloodGroup;
    }
    
    public BloodPacket(String packetID, String nic, String recievedID, Date dateOfDonation, Date dateOfExpiry, String bloodType, byte isCrossmatched, byte isSpecialReservation, byte isUnderObservation, String campID, byte isDiscarded, String bloodGroup, Date discardedDate, String returnID, String patientIssueID, String comment, String bullkissueID) {
        this.packetID = packetID;
        this.nic = nic;
        this.recievedID = recievedID;
        this.dateOfDonation = dateOfDonation;
        this.dateOfExpiry = dateOfExpiry;
        this.bloodType = bloodType;
        this.isCrossmatched = isCrossmatched;
        this.isSpecialReservation = isSpecialReservation;
        this.isUnderObservation = isUnderObservation;
        this.campID = campID;
        this.isDiscarded = isDiscarded;
        this.bloodGroup = bloodGroup;
        this.discardedDate = discardedDate;
        this.returnID = returnID;
        this.patientIssueID = patientIssueID;
        this.comment = comment;
        this.bullkissueID = bullkissueID;
    }
    
    

    /**
     * @return the packetID
     */
    public String getPacketID() {
        return packetID;
    }

    /**
     * @param packetID the packetID to set
     */
    public void setPacketID(String packetID) {
        this.packetID = packetID;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the dateOfDonation
     */
    public Date getDateOfDonation() {
        return dateOfDonation;
    }

    /**
     * @param dateOfDonation the dateOfDonation to set
     */
    public void setDateOfDonation(Date dateOfDonation) {
        this.dateOfDonation = dateOfDonation;
    }

    /**
     * @return the dateOfExpiry
     */
    public Date getDateOfExpiry() {
        return dateOfExpiry;
    }

    /**
     * @param dateOfExpiry the dateOfExpiry to set
     */
    public void setDateOfExpiry(Date dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    /**
     * @return the bloodType
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * @param bloodType the bloodType to set
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * @return the isCrossmatched
     */
    public byte isIsCrossmatched() {
        return isCrossmatched;
    }

    /**
     * @param isCrossmatched the isCrossmatched to set
     */
    public void setIsCrossmatched(byte isCrossmatched) {
        this.isCrossmatched = isCrossmatched;
    }

    /**
     * @return the isSpecialReservation
     */
    public byte isIsSpecialReservation() {
        return isSpecialReservation;
    }

    /**
     * @param isSpecialReservation the isSpecialReservation to set
     */
    public void setIsSpecialReservation(byte isSpecialReservation) {
        this.isSpecialReservation = isSpecialReservation;
    }

    /**
     * @return the isUnderObservation
     */
    public byte isIsUnderObservation() {
        return isUnderObservation;
    }

    /**
     * @param isUnderObservation the isUnderObservation to set
     */
    public void setIsUnderObservation(byte isUnderObservation) {
        this.isUnderObservation = isUnderObservation;
    }

    /**
     * @return the campID
     */
    public String getCampID() {
        return campID;
    }

    /**
     * @param campID the campID to set
     */
    public void setCampID(String campID) {
        this.campID = campID;
    }

    /**
     * @return the isDiscarded
     */
    public byte isIsDiscarded() {
        return isDiscarded;
    }

    /**
     * @param isDiscarded the isDiscarded to set
     */
    public void setIsDiscarded(byte isDiscarded) {
        this.isDiscarded = isDiscarded;
    }

    /**
     * @return the bloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param bloodGroup the bloodGroup to set
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * @return the discardedDate
     */
    public Date getDiscardedDate() {
        return discardedDate;
    }

    /**
     * @param discardedDate the discardedDate to set
     */
    public void setDiscardedDate(Date discardedDate) {
        this.discardedDate = discardedDate;
    }

    /**
     * @return the returnID
     */
    public String getReturnID() {
        return returnID;
    }

    /**
     * @param returnID the returnID to set
     */
    public void setReturnID(String returnID) {
        this.returnID = returnID;
    }

    /**
     * @return the patientIssueID
     */
    public String getPatientIssueID() {
        return patientIssueID;
    }

    /**
     * @param patientIssueID the patientIssueID to set
     */
    public void setPatientIssueID(String patientIssueID) {
        this.patientIssueID = patientIssueID;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the bullkissueID
     */
    public String getBullkissueID() {
        return bullkissueID;
    }

    /**
     * @param bullkissueID the bullkissueID to set
     */
    public void setBullkissueID(String bullkissueID) {
        this.bullkissueID = bullkissueID;
    }

    /**
     * @return the recievedID
     */
    public String getRecievedID() {
        return recievedID;
    }

    /**
     * @param recievedID the recievedID to set
     */
    public void setRecievedID(String recievedID) {
        this.recievedID = recievedID;
    }

   
}
