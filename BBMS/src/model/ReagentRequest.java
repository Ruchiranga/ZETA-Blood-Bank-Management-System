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
public class ReagentRequest {

    private String requestID;
    private Date requestDate;
    private String requestingOfficerID;

    public ReagentRequest(String requestID, Date requestDate, String requestingOfficerID) {
        this.requestID = requestID;
        this.requestDate = requestDate;
        this.requestingOfficerID = requestingOfficerID;
    }

    /**
     * @return the requestID
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * @param requestID the requestID to set
     */
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    /**
     * @return the requestDate
     */
    public Date getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the requestingOfficerID
     */
    public String getRequestingOfficerID() {
        return requestingOfficerID;
    }

    /**
     * @param requestingOfficerID the requestingOfficerID to set
     */
    public void setRequestingOfficerID(String requestingOfficerID) {
        this.requestingOfficerID = requestingOfficerID;
    }

       

}
