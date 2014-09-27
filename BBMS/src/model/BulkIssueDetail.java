/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Nands
 */
public class BulkIssueDetail {
    private String issueID;
    private String packetID;

    public BulkIssueDetail(String issueID, String packetID) {
        this.issueID = issueID;
        this.packetID = packetID;
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
    
    
    
}
