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
public class TestResult {

    private String ResultID;
    private String TestID;
    private String PacketID;
    private String Result;
    private String Comment;
    private String date;
    private String DoneBy;
    private String CheckedBy;
    private String LabeledBy;

    public TestResult(String ResultID, String TestID, String PacketID, String Result, String Comment, String date, String DoneBy, String CheckedBy, String LabeledBy) {
        this.ResultID = ResultID;
        this.TestID = TestID;
        this.PacketID = PacketID;
        this.Result = Result;
        this.Comment = Comment;
        this.date = date;
        this.DoneBy = DoneBy;
        this.CheckedBy = CheckedBy;
        this.LabeledBy = LabeledBy;
    }

    
    /**
     * @return the ResultID
     */
    public String getResultID() {
        return ResultID;
    }

    /**
     * @param ResultID the ResultID to set
     */
    public void setResultID(String ResultID) {
        this.ResultID = ResultID;
    }

    /**
     * @return the TestID
     */
    public String getTestID() {
        return TestID;
    }

    /**
     * @param TestID the TestID to set
     */
    public void setTestID(String TestID) {
        this.TestID = TestID;
    }

    /**
     * @return the PacketID
     */
    public String getPacketID() {
        return PacketID;
    }

    /**
     * @param PacketID the PacketID to set
     */
    public void setPacketID(String PacketID) {
        this.PacketID = PacketID;
    }

    /**
     * @return the Result
     */
    public String getResult() {
        return Result;
    }

    /**
     * @param Result the Result to set
     */
    public void setResult(String Result) {
        this.Result = Result;
    }

    /**
     * @return the Comment
     */
    public String getComment() {
        return Comment;
    }

    /**
     * @param Comment the Comment to set
     */
    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the DoneBy
     */
    public String getDoneBy() {
        return DoneBy;
    }

    /**
     * @param DoneBy the DoneBy to set
     */
    public void setDoneBy(String DoneBy) {
        this.DoneBy = DoneBy;
    }

    /**
     * @return the CheckedBy
     */
    public String getCheckedBy() {
        return CheckedBy;
    }

    /**
     * @param CheckedBy the CheckedBy to set
     */
    public void setCheckedBy(String CheckedBy) {
        this.CheckedBy = CheckedBy;
    }

    /**
     * @return the LabeledBy
     */
    public String getLabeledBy() {
        return LabeledBy;
    }

    /**
     * @param LabeledBy the LabeledBy to set
     */
    public void setLabeledBy(String LabeledBy) {
        this.LabeledBy = LabeledBy;
    }
}
