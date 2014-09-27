/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author ruchiranga
 */
public class SampleDetail {

    private String requestNo;
    private String patientName;
    private String bhtNo;
    private int age;
    private String gender;
    private String bloodGroup;
    private String ward;
    private String weight;
    private String hospital;
    private String diagnosis;
    private String reactionHistory;
    private Date collectedDate;
    private Time collectedTime;
    private String collectedBy;
    private String priority;

    public SampleDetail() {
    }

    public SampleDetail(String requestNo, String patientName, String bhtNo, int age, String gender, String bloodGroup, String ward, String weight, String hospital, String diagnosis, String reactionHistory, Date collectedDate, Time collectedTime, String collectedBy, String priority) {
        this.requestNo = requestNo;
        this.patientName = patientName;
        this.bhtNo = bhtNo;
        this.age = age;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.ward = ward;
        this.weight = weight;
        this.hospital = hospital;
        this.diagnosis = diagnosis;
        this.reactionHistory = reactionHistory;
        this.collectedDate = collectedDate;
        this.collectedTime = collectedTime;
        this.collectedBy = collectedBy;
        this.priority = priority;
    }

    /**
     * @return the requestNo
     */
    public String getRequestNo() {
        return requestNo;
    }

    /**
     * @param RequestNo the requestNo to set
     */
    public void setRequestNo(String RequestNo) {
        this.requestNo = RequestNo;
    }

    /**
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param PatientName the patientName to set
     */
    public void setPatientName(String PatientName) {
        this.patientName = PatientName;
    }

    /**
     * @return the bhtNo
     */
    public String getBHTNo() {
        return bhtNo;
    }

    /**
     * @param BHTNo the bhtNo to set
     */
    public void setBHTNo(String BHTNo) {
        this.bhtNo = BHTNo;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param Age the age to set
     */
    public void setAge(int Age) {
        this.age = Age;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param Gender the gender to set
     */
    public void setGender(String Gender) {
        this.gender = Gender;
    }

    /**
     * @return the bloodGroup
     */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /**
     * @param BloodGroup the bloodGroup to set
     */
    public void setBloodGroup(String BloodGroup) {
        this.bloodGroup = BloodGroup;
    }

    /**
     * @return the ward
     */
    public String getWard() {
        return ward;
    }

    /**
     * @param Ward the ward to set
     */
    public void setWard(String Ward) {
        this.ward = Ward;
    }

    /**
     * @return the weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param Weight the weight to set
     */
    public void setWeight(String Weight) {
        this.weight = Weight;
    }

    /**
     * @return the hospital
     */
    public String getHospital() {
        return hospital;
    }

    /**
     * @param Hospital the hospital to set
     */
    public void setHospital(String Hospital) {
        this.hospital = Hospital;
    }

    /**
     * @return the diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * @param Diagnosis the diagnosis to set
     */
    public void setDiagnosis(String Diagnosis) {
        this.diagnosis = Diagnosis;
    }

    /**
     * @return the reactionHistory
     */
    public String getReactionHistory() {
        return reactionHistory;
    }

    /**
     * @param ReactionHistory the reactionHistory to set
     */
    public void setReactionHistory(String ReactionHistory) {
        this.reactionHistory = ReactionHistory;
    }

    /**
     * @return the collectedDate
     */
    public Date getCollectedDate() {
        return collectedDate;
    }

    /**
     * @param CollectedDate the collectedDate to set
     */
    public void setCollectedDate(Date CollectedDate) {
        this.collectedDate = CollectedDate;
    }

    /**
     * @return the collectedTime
     */
    public Time getCollectedTime() {
        return collectedTime;
    }

    /**
     * @param CollectedTime the collectedTime to set
     */
    public void setCollectedTime(Time CollectedTime) {
        this.collectedTime = CollectedTime;
    }

    /**
     * @return the collectedBy
     */
    public String getCollectedBy() {
        return collectedBy;
    }

    /**
     * @param CollectedBy the collectedBy to set
     */
    public void setCollectedBy(String CollectedBy) {
        this.collectedBy = CollectedBy;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param Priority the priority to set
     */
    public void setPriority(String Priority) {
        this.priority = Priority;
    }
    
    
    
}
