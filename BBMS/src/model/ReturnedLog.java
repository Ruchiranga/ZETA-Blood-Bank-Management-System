/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author ruchiranga
 */
public class ReturnedLog {

    private String returnedID;
    private Date returnedDate;
    private String packetID;
    private String reason;
    private String patientIssueID;
    private String bulkIssueID;

    public ReturnedLog() {
    }

    public ReturnedLog(String returnedID, Date returnedDate, String packetID, String reason, String patientIssueID, String bulkIssueID) {
        this.returnedID = returnedID;
        this.returnedDate = returnedDate;
        this.packetID = packetID;
        this.reason = reason;
        this.patientIssueID = patientIssueID;
        this.bulkIssueID = bulkIssueID;
    }

    /**
     * @return the returnedID
     */
    public String getReturnedID() {
        return returnedID;
    }

    /**
     * @param ReturnedID the returnedID to set
     */
    public void setReturnedID(String ReturnedID) {
        this.returnedID = ReturnedID;
    }

    /**
     * @return the returnedDate
     */
    public Date getReturnedDate() {
        return returnedDate;
    }

    /**
     * @param ReturnedDate the returnedDate to set
     */
    public void setReturnedDate(Date ReturnedDate) {
        this.returnedDate = ReturnedDate;
    }

    /**
     * @return the packetID
     */
    public String getPacketID() {
        return packetID;
    }

    /**
     * @param PacketID the packetID to set
     */
    public void setPacketID(String PacketID) {
        this.packetID = PacketID;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param Reason the reason to set
     */
    public void setReason(String Reason) {
        this.reason = Reason;
    }

    /**
     * @return the patientIssueID
     */
    public String getPatientIssueID() {
        return patientIssueID;
    }

    /**
     * @param PatientIssueID the patientIssueID to set
     */
    public void setPatientIssueID(String PatientIssueID) {
        this.patientIssueID = PatientIssueID;
    }

    /**
     * @return the bulkIssueID
     */
    public String getBulkIssueID() {
        return bulkIssueID;
    }

    /**
     * @param BulkIssueID the bulkIssueID to set
     */
    public void setBulkIssueID(String BulkIssueID) {
        this.bulkIssueID = BulkIssueID;
    }
    
    
}
