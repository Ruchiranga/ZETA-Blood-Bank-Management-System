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
public class Organizer {
    
    private String nic;
    private String name;
    private String address;
    private String tp;
    private int campCount;

    public Organizer(String nic, String name, String address, String tp, int campCount) {
        this.nic = nic;
        this.name = name;
        this.address = address;
        this.tp = tp;
        this.campCount = campCount;
    }

   
    public Organizer(int campCount, String nic) {
        this.nic = nic;
        this.campCount = campCount;
    }

    
    public String getNic() {
        return nic;
    }

    
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return the campCount
     */
    public int getCampCount() {
        return campCount;
    }

    /**
     * @param campCount the campCount to set
     */
    public void setCampCount(int campCount) {
        this.campCount = campCount;
    }
    
    
    
}
