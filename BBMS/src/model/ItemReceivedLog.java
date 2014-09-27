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
public class ItemReceivedLog {
    private String recievedID;
    private Date recievedDate;
    private String recievedFrom;

    public ItemReceivedLog(String recievedID, Date recievedDate, String recievedFrom) {
        this.recievedID = recievedID;
        this.recievedDate = recievedDate;
        this.recievedFrom = recievedFrom;
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

    /**
     * @return the recievedDate
     */
    public Date getRecievedDate() {
        return recievedDate;
    }

    /**
     * @param recievedDate the recievedDate to set
     */
    public void setRecievedDate(Date recievedDate) {
        this.recievedDate = recievedDate;
    }

    /**
     * @return the recievedFrom
     */
    public String getRecievedFrom() {
        return recievedFrom;
    }

    /**
     * @param recievedFrom the recievedFrom to set
     */
    public void setRecievedFrom(String recievedFrom) {
        this.recievedFrom = recievedFrom;
    }

    
    
}
