
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Nands
 */
public class Issue {
    private String issueID;
    private String EmpID;
    private Time time;
    private Date date;

    public Issue(String issueID, String EmpID, Time time, Date date) {
        this.issueID = issueID;
        this.EmpID = EmpID;
        this.time = time;
        this.date = date;
    }

    
    /**
     * @return the issueID
     */
    public String getIssueID() {
        return issueID;
    }

    /**
     * @param issueID the issueID to set
     */
    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    /**
     * @return the EmpID
     */
    public String getEmpID() {
        return EmpID;
    }

    /**
     * @param EmpID the EmpID to set
     */
    public void setEmpID(String EmpID) {
        this.EmpID = EmpID;
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
    
    
}
