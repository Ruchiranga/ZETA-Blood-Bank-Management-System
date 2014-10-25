/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BloodPacket;

import java.sql.Date;
import java.sql.Time;
import junit.framework.TestCase;
import model.Donor;
import model.Issue;
import org.junit.Test;

/**
 *
 * @author ruchiranga
 */
public class IssueTest {
    
    @Test
    public void getIssueID(){
        System.out.println("getIssueID");
        Issue issue = new Issue("IS000000001", "E00000003", new Time(85324736), new Date(836285749));
        String result = issue.getIssueID();
        String expected = "IS000000001";
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void getEmployeeID(){
        System.out.println("getEmployeeID");
        Issue issue = new Issue("IS000000001", "E00000003", new Time(85324736), new Date(836285749));
        String result = issue.getEmpID();
        String expected = "E00000003";
        TestCase.assertEquals(expected,result);
    }
     
    @Test
    public void geCreatedTime(){
        System.out.println("getCreatedTime");
        Issue issue = new Issue("IS000000001", "E00000003", new Time(85324736), new Date(836285749));
        String result = issue.getTime().toString();
        String expected = new Time(85324736).toString();
        TestCase.assertEquals(expected,result);
    }
    
    @Test
    public void getCreatedDate(){
        System.out.println("getCreatedDate");
        Issue issue = new Issue("IS000000001", "E00000003", new Time(85324736), new Date(836285749));
        String result = issue.getDate().toString();
        String expected = new Date(836285749).toString();
        TestCase.assertEquals(expected,result);
    }
}
