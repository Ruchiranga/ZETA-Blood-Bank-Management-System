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
public class Item {
    
    private String itemID;
    private String itemType;
    private String description;
    private byte isReagent;

    public Item(String itemID, String itemType, String description, byte isReagent) {
        this.itemID = itemID;
        this.itemType = itemType;
        this.description = description;
        this.isReagent = isReagent;
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
     * @return the itemType
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the isReagent
     */
    public byte getIsReagent() {
        return isReagent;
    }

    /**
     * @param isReagent the isReagent to set
     */
    public void setIsReagent(byte isReagent) {
        this.isReagent = isReagent;
    }
    
    
    
}
