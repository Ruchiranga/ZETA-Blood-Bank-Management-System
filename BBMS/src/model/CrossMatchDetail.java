/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Upekka
 */
public class CrossMatchDetail {
    private String requestNo;
    private String packetID;
    private Date date;
    private String doneBY;

    public CrossMatchDetail(String requestNo, String packetID, Date date, String doneBY) {
        this.requestNo = requestNo;
        this.packetID = packetID;
        this.date = date;
        this.doneBY = doneBY;
    }

    /**
     * @return the requestNo
     */
    public String getRequestNo() {
        return requestNo;
    }

    /**
     * @param requestNo the requestNo to set
     */
    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
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
     * @return the doneBY
     */
    public String getDoneBY() {
        return doneBY;
    }

    /**
     * @param doneBY the doneBY to set
     */
    public void setDoneBY(String doneBY) {
        this.doneBY = doneBY;
    }
    
}
