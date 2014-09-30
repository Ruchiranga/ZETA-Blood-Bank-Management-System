/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Ruchi;

import idgenerator.IDGenerator;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.BloodPacket;
import model.Donor;
import model.Employee;
import model.Test;

/**
 *
 * @author ruchiranga
 */
public class BloodGroupingAndTTIHandler {

    BloodGroupingAndTTIDA dataAccess;
    ArrayList<BloodPacket> packets;
    ArrayList<Donor> donors;
    ArrayList<Employee> employees;
    ArrayList<Test> tests;

    public BloodGroupingAndTTIHandler() throws SQLException, ClassNotFoundException {
        packets = new ArrayList<BloodPacket>();
        dataAccess = new BloodGroupingAndTTIDA();
        donors = new ArrayList<Donor>();
        employees = new ArrayList<Employee>();
        tests = new ArrayList<Test>();
        

        ResultSet rst = dataAccess.getAllUntestedPackets();

        for (int i = 0; rst.next(); i++) {
            packets.add(new BloodPacket(rst.getString("packetID"), rst.getString("bloodGroup"), rst.getString("bloodType"), rst.getString("nic"), rst.getDate("dateOFExpiry"), rst.getDate("dateOfDonation"), (byte)rst.getInt("isCrossmatched"), (byte)rst.getInt("isUnderObservation")));

        }
        

        rst = dataAccess.getDonors();

        for (int i = 0; rst.next(); i++) {
            donors.add(new Donor(rst.getString(1), rst.getString(2), rst.getDate(3), rst.getString(4), rst.getInt(5), rst.getString(6), rst.getBoolean(7)));
        }

        rst = dataAccess.getEmployees();
        for (int i = 0; rst.next(); i++) {
            employees.add(new Employee(rst.getString(1), rst.getString(2)));
        }

        rst = dataAccess.getTests();
        for (int i = 0; rst.next(); i++) {
            tests.add(new Test(rst.getString(1), rst.getString(2)));
        }

    }

    public String[] getPacketIDList() throws ClassNotFoundException, SQLException {

        String[] list = new String[packets.size()];
        for (int i = 0; i < packets.size(); i++) {
            list[i] = packets.get(i).getPacketID();
        }
        return list;
    }

    public String getDonorNameOf(String packetID) throws SQLException, ClassNotFoundException {
        String donorID = getDonorIDOF(packetID);
        String donorName = null;
        for (Donor donor : donors) {
            if (donor.getNic().equals(donorID)) {
                donorName = donor.getName();
            }
        }

        return donorName;
    }

    public String getDonorIDOF(String packetID) {
        String donorID = null;
        for (BloodPacket pack : packets) {
            if (pack.getPacketID().equals(packetID)) {
                donorID = pack.getNic();
            }
        }
        return donorID;
    }

    public boolean isDonorBlacklisted(String donorID) throws SQLException, ClassNotFoundException {
        boolean status = false;
        for (Donor donor : donors) {
            if (donor.getNic().equals(donorID)) {
                status = donor.isBlacklisted();
            }
        }

        return status;
    }

    private int getRecordCount(ResultSet rst) throws SQLException {
        int count = 0;
        while (rst.next()) {
            count++;
        }
        rst.beforeFirst();
        return count;
    }

    int blacklistDonor(String name) throws SQLException, ClassNotFoundException {
        return dataAccess.blackListDonor(name);
    }

    String[] getGroupList() throws SQLException, ClassNotFoundException {
        ResultSet rst;
        rst = dataAccess.getBloodGroups();
        int count = getRecordCount(rst);
        String[] groups = new String[count];
        for (int i = 0; rst.next(); i++) {
            groups[i] = rst.getString(1);
        }
        return groups;

    }

    String[] getTestList() throws SQLException, ClassNotFoundException {
        String[] list = new String[tests.size()];
        for (int i = 0; i < tests.size(); i++) {
            list[i] = tests.get(i).getName();
        }
        return list;
    }

    String[] getEmployeeList() throws SQLException, ClassNotFoundException {
        String[] list = new String[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            list[i] = employees.get(i).getName();
        }
        return list;
    }

    private String getNextResultID() throws SQLException, Exception {
        ResultSet rst = dataAccess.getResultIDs();
        rst.last();
        String curID = null;
        try {
            curID = rst.getString(1);
        } catch (SQLException e) {
            return "RS00000001";
        }

        return IDGenerator.generateNextID(curID);

    }

    int addBloodGroupResult(String packetID, String group, String groupComment) throws SQLException, ClassNotFoundException {
        int res = dataAccess.setBloodGroup(packetID, group, groupComment);
        return res;
    }

    int addTestResult(String testName, String packetID, String result, String comment, String date, String doneBy, String checkedBy) throws Exception {
        String resID = getNextResultID();
        String testID = null;
        for (Test test : tests) {
            if (test.getName().equals(testName)) {
                testID = test.getTestID();
            }
        }
        
        String doneByID = null;
        for (Employee emp : employees) {
            if (emp.getName().equals(doneBy)) {
                doneByID=emp.getEmpID();
            }
        }
        
        String checkedByID = null;
        for (Employee emp : employees) {
            if (emp.getName().equals(checkedBy)) {
                checkedByID=emp.getEmpID();
            }
        }
             
        int res = dataAccess.addPacketResult(resID,testID,packetID,result,comment,date,doneByID,checkedByID);
        return res;
    }

    void setPacketDiscarded(String packetID,String date) throws SQLException, ClassNotFoundException {
        dataAccess.discardPacket(packetID,date);
    }

}
