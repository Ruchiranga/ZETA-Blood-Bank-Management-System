/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BloodPacket;

import java.sql.Date;
import junit.framework.TestCase;
import model.BloodPacket;
import org.junit.Test;

/**
 *
 * @author ruchiranga
 */
public class BloodPacketTest {
    
    @Test
    public void getBloodPacketGroup(){
        System.out.println("getPacketBloodGroup");
        BloodPacket bloodPacket = new BloodPacket("KP00000000002","A+","FFP","922733442V", new Date(new Long(40341243)),new Date(new Long(50341243)),(byte)0,(byte)1);
        String result = bloodPacket.getBloodGroup();
        String expected = "A+";
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void getBloodPacketType(){
        System.out.println("getPacketBloodType");
        BloodPacket bloodPacket = new BloodPacket("KP00000000002","A+","FFP","922733442V", new Date(new Long(40341243)),new Date(new Long(50341243)),(byte)0,(byte)1);
        String result = bloodPacket.getBloodType();
        String expected = "FFP";
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void getBloodPacketDonor(){
        System.out.println("getPacketDonorNIC");
        BloodPacket bloodPacket = new BloodPacket("KP00000000002","A+","FFP","922733442V", new Date(new Long(40341243)),new Date(new Long(50341243)),(byte)0,(byte)1);
        String result = bloodPacket.getNic();
        String expected = "922733442V";
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void getBloodPacketDateOfDonation(){
        System.out.println("getPacketDateOfDonation");
        BloodPacket bloodPacket = new BloodPacket("KP00000000002","A+","FFP","922733442V", new Date(new Long(40341243)),new Date(new Long(50341243)),(byte)0,(byte)1);
        String result = bloodPacket.getDateOfDonation().toString();
        String expected = new Date(40341243).toString();
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void getBloodPacketDateOfExpiry(){
        System.out.println("getPacketDateOfExpiry");
        BloodPacket bloodPacket = new BloodPacket("KP00000000002","A+","FFP","922733442V", new Date(new Long(40341243)),new Date(new Long(50341243)),(byte)0,(byte)1);
        String result = bloodPacket.getDateOfExpiry().toString();
        String expected = new Date(50341243).toString();
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void isBloodPacketCrossMatched(){
        System.out.println("getIsPacketCrossMatched");
        BloodPacket bloodPacket = new BloodPacket("KP00000000002","A+","FFP","922733442V", new Date(new Long(40341243)),new Date(new Long(50341243)),(byte)0,(byte)1);
        byte result = bloodPacket.isIsCrossmatched();
        byte expected = 0;
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void isBloodPacketUnderObservation(){
        System.out.println("getIsPacketUnderObservation");
        BloodPacket bloodPacket = new BloodPacket("KP00000000002","A+","FFP","922733442V", new Date(new Long(40341243)),new Date(new Long(50341243)),(byte)0,(byte)1);
        byte result = bloodPacket.isIsUnderObservation();
        byte expected = 1;
        TestCase.assertEquals(expected,result);
    }
}
