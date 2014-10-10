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
public class BloodStockHistory {

    private Date stockDate;
    private String bloodGroup;
    private int unx;
    private int x;
    private int spec_res;
    private int underobs;
    private int total;

    public BloodStockHistory(Date stockDate, String bloodGroup, int unx, int x, int spec_res, int underobs, int total) {
        this.stockDate = stockDate;
        this.bloodGroup = bloodGroup;
        this.unx = unx;
        this.x = x;
        this.spec_res = spec_res;
        this.underobs = underobs;
        this.total = total;
    }

    /**
     * @return the stockDate
     */
    public Date getStockDate() {
        return stockDate;
    }

    /**
     * @param stockDate the stockDate to set
     */
    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    /**
     * @return the bloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param bloodGroup the bloodGroup to set
     */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /**
     * @return the unx
     */
    public int getUnx() {
        return unx;
    }

    /**
     * @param unx the unx to set
     */
    public void setUnx(int unx) {
        this.unx = unx;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the spec_res
     */
    public int getSpec_res() {
        return spec_res;
    }

    /**
     * @param spec_res the spec_res to set
     */
    public void setSpec_res(int spec_res) {
        this.spec_res = spec_res;
    }

    /**
     * @return the underobs
     */
    public int getUnderobs() {
        return underobs;
    }

    /**
     * @param underobs the underobs to set
     */
    public void setUnderobs(int underobs) {
        this.underobs = underobs;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    
    
}
