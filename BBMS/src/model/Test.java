/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author ruchiranga
 */
public class Test {
    private String testID;
    private String name;

    public Test(String testID, String name) {
        this.testID = testID;
        this.name = name;
    }

    /**
     * @return the testID
     */
    public String getTestID() {
        return testID;
    }

    /**
     * @param testID the testID to set
     */
    public void setTestID(String testID) {
        this.testID = testID;
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
    
    
    
}
