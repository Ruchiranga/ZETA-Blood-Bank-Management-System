/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Date;

/**
 *
 * @author ruchiranga
 */
public class Donor {
    private String nic;
    private String name;
    private Date dob;
    private String gender;
    private int age;
    private String homeAddress;
    private boolean blacklisted;

    public Donor(String nic, String name, Date dob, String gender, int age, String homeAddress, boolean blacklisted) {
        this.nic = nic;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.age = age;
        this.homeAddress = homeAddress;
        this.blacklisted = blacklisted;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
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
     * @return the homeAddress
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * @param homeAddress the homeAddress to set
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * @return the blacklisted
     */
    public boolean isBlacklisted() {
        return blacklisted;
    }

    /**
     * @param blacklisted the blacklisted to set
     */
    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }
    
    
    
}
