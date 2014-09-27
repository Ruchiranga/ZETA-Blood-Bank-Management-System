/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Anuradha
 */
public class PatientRequestIn {

    private String requestID;
    private String hospital;
    private String ward;
    private String patientName;
    private int age;
    private String gender;
    private String bht;
    private String bloodType;
    private String bloodGroup;
    private int noOfPackets;
    private String indicationOfTransfusion;
    private Date date;
    private Time time;
    private String sendingOfficer;
    private String specialRemarks;

    public PatientRequestIn(String requestID, String hospital, String ward, String patientName, int age, String gender, String bht, String bloodType, String bloodGroup, int noOfPackets, String indicationOfTransfusion, Date date, Time time, String sendingOfficer, String specialRemarks) {
        this.requestID = requestID;
        this.hospital = hospital;
        this.ward = ward;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.bht = bht;
        this.bloodType = bloodType;
        this.bloodGroup = bloodGroup;
        this.noOfPackets = noOfPackets;
        this.indicationOfTransfusion = indicationOfTransfusion;
        this.date = date;
        this.time = time;
        this.sendingOfficer = sendingOfficer;
        this.specialRemarks = specialRemarks;
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
     * @return the hospital
     */
    public String getHospital() {
        return hospital;
    }

    /**
     * @param hospital the hospital to set
     */
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    /**
     * @return the ward
     */
    public String getWard() {
        return ward;
    }

    /**
     * @param ward the ward to set
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /**
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param patientName the patientName to set
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
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
     * @return the bht
     */
    public String getBht() {
        return bht;
    }

    /**
     * @param bht the bht to set
     */
    public void setBht(String bht) {
        this.bht = bht;
    }

    /**
     * @return the bloodType
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * @param bloodType the bloodType to set
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
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
     * @return the noOfPackets
     */
    public int getNoOfPackets() {
        return noOfPackets;
    }

    /**
     * @param noOfPackets the noOfPackets to set
     */
    public void setNoOfPackets(int noOfPackets) {
        this.noOfPackets = noOfPackets;
    }

    /**
     * @return the indicationOfTransfusion
     */
    public String getIndicationOfTransfusion() {
        return indicationOfTransfusion;
    }

    /**
     * @param indicationOfTransfusion the indicationOfTransfusion to set
     */
    public void setIndicationOfTransfusion(String indicationOfTransfusion) {
        this.indicationOfTransfusion = indicationOfTransfusion;
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
     * @return the time
     */
    public Time getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * @return the sendingOfficer
     */
    public String getSendingOfficer() {
        return sendingOfficer;
    }

    /**
     * @param sendingOfficer the sendingOfficer to set
     */
    public void setSendingOfficer(String sendingOfficer) {
        this.sendingOfficer = sendingOfficer;
    }

    /**
     * @return the specialRemarks
     */
    public String getSpecialRemarks() {
        return specialRemarks;
    }

    /**
     * @param specialRemarks the specialRemarks to set
     */
    public void setSpecialRemarks(String specialRemarks) {
        this.specialRemarks = specialRemarks;
    }

    
    

}
