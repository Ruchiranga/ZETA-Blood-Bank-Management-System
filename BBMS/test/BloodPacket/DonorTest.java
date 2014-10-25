/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BloodPacket;

import java.sql.Date;
import junit.framework.TestCase;
import model.BloodPacket;
import model.Donor;
import org.junit.Test;

/**
 *
 * @author ruchiranga
 */
public class DonorTest {
    @Test
    public void getDonorName(){
        System.out.println("getDonorName");
        Donor donor = new Donor("922833233V", "Dimuthu Ramawickrama", new Date(98002212), "Male", 56, "326 F, Poddala Cross Rd, Ambalangoda.",false);
        String result = donor.getName();
        String expected = "Dimuthu Ramawickrama";
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void getDonorDOB(){
        System.out.println("getDonorDOB");
        Donor donor = new Donor("922833233V", "Dimuthu Ramawickrama", new Date(98002212), "Male", 56, "326 F, Poddala Cross Rd, Ambalangoda.",false);
        String result = donor.getDob().toString();
        String expected = new Date(98002212).toString();
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void getDonorGender(){
        System.out.println("getDonorGender");
        Donor donor = new Donor("922833233V", "Dimuthu Ramawickrama", new Date(98002212), "Male", 56, "326 F, Poddala Cross Rd, Ambalangoda.",false);
        String result = donor.getGender();
        String expected = "Male";
        TestCase.assertEquals(expected,result);
    }

    @Test
    public void getDonorAge(){
        System.out.println("getDonorAge");
        Donor donor = new Donor("922833233V", "Dimuthu Ramawickrama", new Date(98002212), "Male", 56, "326 F, Poddala Cross Rd, Ambalangoda.",false);
        int result = donor.getAge();
        int expected = 56;
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void getDonorAddress(){
        System.out.println("getDonorAddress");
        Donor donor = new Donor("922833233V", "Dimuthu Ramawickrama", new Date(98002212), "Male", 56, "326 F, Poddala Cross Rd, Ambalangoda.",false);
        String result = donor.getHomeAddress();
        String expected = "326 F, Poddala Cross Rd, Ambalangoda.";
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void isDonorBlacklisted(){
        System.out.println("isDonorBlacklisted");
        Donor donor = new Donor("922833233V", "Dimuthu Ramawickrama", new Date(98002212), "Male", 56, "326 F, Poddala Cross Rd, Ambalangoda.",false);
        boolean result = donor.isBlacklisted();
        boolean expected = false;
        TestCase.assertEquals(expected,result);
    }
    
    
}
