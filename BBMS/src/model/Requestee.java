/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Anuradha
 */
public class Requestee {

    private String requesteeName;

    public Requestee(String requesteeName) {
        this.requesteeName = requesteeName;
    }

    /**
     * @return the requesteeName
     */
    public String getRequesteeName() {
        return requesteeName;
    }

    /**
     * @param requesteeName the requesteeName to set
     */
    public void setRequesteeName(String requesteeName) {
        this.requesteeName = requesteeName;
    }

}
