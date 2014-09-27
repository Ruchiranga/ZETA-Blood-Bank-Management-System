/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Nands
 */
public class BulkIssue {
    private String issueID;
    private String requester;
    private Date date;
    private Time time;
    private String sendingOfficer;
    private String vehicleNo;
    private String driver;
    private float temperature;
    private byte areIcePacketsSent;

    public BulkIssue(String issueID, String requester, Date date, Time time, String sendingOfficer, String vehicleNo, String driver, float temperature, byte areIcePacketsSent) {
        this.issueID = issueID;
        this.requester = requester;
        this.date = date;
        this.time = time;
        this.sendingOfficer = sendingOfficer;
        this.vehicleNo = vehicleNo;
        this.driver = driver;
        this.temperature = temperature;
        this.areIcePacketsSent = areIcePacketsSent;
    }

    /**
     * @return the issueID
     */
    public String getIssueID() {
        return issueID;
    }

    /**
     * @param issueID the issueID to set
     */
    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    /**
     * @return the requester
     */
    public String getRequester() {
        return requester;
    }

    /**
     * @param requester the requester to set
     */
    public void setRequester(String requester) {
        this.requester = requester;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public Time getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * @return the sendingOfficer
     */
    public String getSendingOfficer() {
        return sendingOfficer;
    }

    /**
     * @param sendingOfficer the sendingOfficer to set
     */
    public void setSendingOfficer(String sendingOfficer) {
        this.sendingOfficer = sendingOfficer;
    }

    /**
     * @return the vehicleNo
     */
    public String getVehicleNo() {
        return vehicleNo;
    }

    /**
     * @param vehicleNo the vehicleNo to set
     */
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
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
     * @return the areIcePacketsSent
     */
    public byte getAreIcePacketsSent() {
        return areIcePacketsSent;
    }

    /**
     * @param areIcePacketsSent the areIcePacketsSent to set
     */
    public void setAreIcePacketsSent(byte areIcePacketsSent) {
        this.areIcePacketsSent = areIcePacketsSent;
    }
    
    
    
    
    
    
}
