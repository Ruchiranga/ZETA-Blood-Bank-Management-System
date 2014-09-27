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
public class ItemRecievedDetail {
    
    private String recievedID;
    private String itemID;

    public ItemRecievedDetail(String recievedID, String itemID) {
        this.recievedID = recievedID;
        this.itemID = itemID;
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
    
    
    
}
