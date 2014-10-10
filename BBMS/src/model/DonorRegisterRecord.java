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
public class DonorRegisterRecord {
    private Date date;
    private int serialNo;
    private String unitNo;
    private String donorName;
    private String donorAddress;
    private String nic;
    private int telNo;
    private int age;
    private String sex;
    private int weight;
    private int noOfDonations;
    private String bloodGroup;

    public DonorRegisterRecord(Date date, int serialNo, String unitNo, String donorName, String donorAddress, String nic, int telNo, int age, String sex, int weight, int noOfDonations, String bloodGroup) {
        this.date = date;
        this.serialNo = serialNo;
        this.unitNo = unitNo;
        this.donorName = donorName;
        this.donorAddress = donorAddress;
        this.nic = nic;
        this.telNo = telNo;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
        this.noOfDonations = noOfDonations;
        this.bloodGroup = bloodGroup;
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
     * @return the serialNo
     */
    public int getSerialNo() {
        return serialNo;
    }

    /**
     * @param serialNo the serialNo to set
     */
    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * @return the unitNo
     */
    public String getUnitNo() {
        return unitNo;
    }

    /**
     * @param unitNo the unitNo to set
     */
    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    /**
     * @return the donorName
     */
    public String getDonorName() {
        return donorName;
    }

    /**
     * @param donorName the donorName to set
     */
    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    /**
     * @return the donorAddress
     */
    public String getDonorAddress() {
        return donorAddress;
    }

    /**
     * @param donorAddress the donorAddress to set
     */
    public void setDonorAddress(String donorAddress) {
        this.donorAddress = donorAddress;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the telNo
     */
    public int getTelNo() {
        return telNo;
    }

    /**
     * @param telNo the telNo to set
     */
    public void setTelNo(int telNo) {
        this.telNo = telNo;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the noOfDonations
     */
    public int getNoOfDonations() {
        return noOfDonations;
    }

    /**
     * @param noOfDonations the noOfDonations to set
     */
    public void setNoOfDonations(int noOfDonations) {
        this.noOfDonations = noOfDonations;
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
    
    
    
    
    
}
