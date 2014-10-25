

package model;

import java.sql.Date;


public class BloodCamp {
    private String campID;
    private String place;
    private Date date;
    private int expDonars;
    private String organizer;

    public BloodCamp(String campID, String place, Date date, int expDonars, String organizer) {
        this.campID = campID;
        this.place = place;
        this.date = date;
        this.expDonars = expDonars;
        this.organizer = organizer;
    }
    
        /**
     * @return the campID
     */
    public String getCampID() {
        return campID;
    }

    /**
     * @param campID the campID to set
     */
    public void setCampID(String campID) {
        this.campID = campID;
    }

    /**
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(String place) {
        this.place = place;
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
     * @return the expDonars
     */
    public int getExpDonars() {
        return expDonars;
    }

    /**
     * @param expDonars the expDonars to set
     */
    public void setExpDonars(int expDonars) {
        this.expDonars = expDonars;
    }

    /**
     * @return the organizer
     */
    public String getOrganizer() {
        return organizer;
    }

    /**
     * @param organizer the organizer to set
     */
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }
    
    
}

