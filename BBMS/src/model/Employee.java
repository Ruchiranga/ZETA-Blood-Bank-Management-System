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
public class Employee {
    private String empID;
    private String name;
    private Date dob;
    private String homeAddress;
    private String tp;
    private Date dateOfRecruitment;

    public Employee(String empID, String name, Date dob, String homeAddress, String tp, Date dateOfRecruitment) {
        this.empID = empID;
        this.name = name;
        this.dob = dob;
        this.homeAddress = homeAddress;
        this.tp = tp;
        this.dateOfRecruitment = dateOfRecruitment;
    }

    public Employee(String empID, String name) {
        this.empID = empID;
        this.name = name;
    }
   
    /**
     * @return the empID
     */
    public String getEmpID() {
        return empID;
    }

    /**
     * @param empID the empID to set
     */
    public void setEmpID(String empID) {
        this.empID = empID;
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
     * @return the tp
     */
    public String getTp() {
        return tp;
    }

    /**
     * @param tp the tp to set
     */
    public void setTp(String tp) {
        this.tp = tp;
    }

    /**
     * @return the dateOfRecruitment
     */
    public Date getDateOfRecruitment() {
        return dateOfRecruitment;
    }

    /**
     * @param dateOfRecruitment the dateOfRecruitment to set
     */
    public void setDateOfRecruitment(Date dateOfRecruitment) {
        this.dateOfRecruitment = dateOfRecruitment;
    }
    
    
    
}
