/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Anuradha
 */
public class ReagentRequestDetail {
    
    private String requestID;
    private String itemID;
    private float qty;
    private String reason;

    public ReagentRequestDetail(String requestID, String itemID, float qty, String reason) {
        this.requestID = requestID;
        this.itemID = itemID;
        this.qty = qty;
        this.reason = reason;
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
     * @return the itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the qty
     */
    public float getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(float qty) {
        this.qty = qty;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    
    
    
    
}
