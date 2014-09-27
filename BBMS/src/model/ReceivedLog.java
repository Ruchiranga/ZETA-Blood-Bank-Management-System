/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Anuradha
 */
public class ReceivedLog {
    private String receivedID;
    private String requestee;
    private Date receivedDate;
    private Time receivedTime;
    private String sendignOfficer;
    private String receivingOfficer;
    private float temperature;
    private String icePaceketCondition;

    public ReceivedLog(String receivedID, String requestee, Date receivedDate, Time receivedTime, String sendignOfficer, String receivingOfficer, float temperature, String icePaceketCondition) {
        this.receivedID = receivedID;
        this.requestee = requestee;
        this.receivedDate = receivedDate;
        this.receivedTime = receivedTime;
        this.sendignOfficer = sendignOfficer;
        this.receivingOfficer = receivingOfficer;
        this.temperature = temperature;
        this.icePaceketCondition = icePaceketCondition;
    }   

    /**
     * @return the receivedID
     */
    public String getReceivedID() {
        return receivedID;
    }

    /**
     * @param receivedID the receivedID to set
     */
    public void setReceivedID(String receivedID) {
        this.receivedID = receivedID;
    }

    /**
     * @return the requestee
     */
    public String getRequestee() {
        return requestee;
    }

    /**
     * @param requestee the requestee to set
     */
    public void setRequestee(String requestee) {
        this.requestee = requestee;
    }

    /**
     * @return the receivedDate
     */
    public Date getReceivedDate() {
        return receivedDate;
    }

    /**
     * @param receivedDate the receivedDate to set
     */
    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    /**
     * @return the receivedTime
     */
    public Time getReceivedTime() {
        return receivedTime;
    }

    /**
     * @param receivedTime the receivedTime to set
     */
    public void setReceivedTime(Time receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * @return the sendignOfficer
     */
    public String getSendignOfficer() {
        return sendignOfficer;
    }

    /**
     * @param sendignOfficer the sendignOfficer to set
     */
    public void setSendignOfficer(String sendignOfficer) {
        this.sendignOfficer = sendignOfficer;
    }

    /**
     * @return the receivingOfficer
     */
    public String getReceivingOfficer() {
        return receivingOfficer;
    }

    /**
     * @param receivingOfficer the receivingOfficer to set
     */
    public void setReceivingOfficer(String receivingOfficer) {
        this.receivingOfficer = receivingOfficer;
    }

    /**
     * @return the temperature
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the icePaceketCondition
     */
    public String getIcePaceketCondition() {
        return icePaceketCondition;
    }

    /**
     * @param icePaceketCondition the icePaceketCondition to set
     */
    public void setIcePaceketCondition(String icePaceketCondition) {
        this.icePaceketCondition = icePaceketCondition;
    }

    
}
