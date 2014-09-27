/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PatientRequestsIn.java
 *
 * Created on Sep 8, 2014, 12:33:32 PM
 */
package gui.Anu;

import com.toedter.calendar.JDateChooser;
import data_access.anu.BloodGroupDA;
import data_access.anu.BloodTypeDA;
import data_access.anu.PatientRequestInDA;
import data_access.anu.RequestorDA;
import data_access.anu.WardDA;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import model.PatientRequestIn;

/**
 *
 * @author Anuradha
 */
public class PatientRequestsIn extends javax.swing.JInternalFrame {

    Nurse nurseForm = null;
    SpinnerModel sm1 = new SpinnerNumberModel(1, 1, 10000, 1);
    SpinnerModel sm2 = new SpinnerNumberModel(1, 1, 10000, 1);

    /** Creates new form PatientRequestsIn */
    public PatientRequestsIn(Nurse nurseForm) {
        initComponents();
        setHospitalComboItems(addHospitalCombo);
        setHospitalComboItems(updateHospitalcombo);
        setWardComboItems(addWardCombo);
        setWardComboItems(updateWardCombo);
        setBloodTypeComboItems(addBloodTypeCombo);
        setBloodGroupComboItems(addBloodGroupCombo);
        setBloodTypeComboItems(updateBloodTypeCombo);
        setBloodGroupComboItems(updateBloodGroupCombo);
        this.nurseForm = nurseForm;
    }

    public JComboBox getAddHospitalCombo() {
        return addHospitalCombo;
    }

    public JComboBox getUpdateHospitalcombo() {
        return updateHospitalcombo;
    }

    public JComboBox getAddWardCombo() {
        return addWardCombo;
    }

    public JComboBox getUpdateWardCombo() {
        return updateWardCombo;
    }

    private void emptyAddFields() {
        addRequestIDText.setText("");
        addPatientNameCombo.setText("");
        addAgeCombo.setText("");
        addBHTText.setText("");
        addIndicationOfTransfusionText.setText("");
        addSendingOfficerText.setText("");
        specialRemarksText.setText("");
    }

    private void emptySearchFields() {
        searchHospitalText.setText("");
        searchWardText.setText("");
        searchPatientText.setText("");
        searcgAgeText.setText("");

        searchMaleRadioBtn.setSelected(false);
        searchFemaleRadioBtn.setSelected(false);


        searchBHTText.setText("");
        seachIndicationOfTransfusionText.setText("");
        searchSendingOfficerText.setText("");
        searchTimeText.setText("");
        searchBloodTypeText.setText("");
        searchBloodGroupText.setText("");
        searchNoOfPacketsText.setText("");
        searchSpecialRemarksText.setText("");
    }

    private void emptyUpdateFields() {
        updatePatientText.setText("");
        updateAgeText.setText("");
        updateBHTText.setText("");
        updateIndicationOfTransfusionText.setText("");
        updateSendingOfficerText.setText("");
        updateSpecialRemarksText.setText("");
    }

    public void setHospitalComboItems(JComboBox combo) {
        combo.removeAllItems();
        try {
            ResultSet rst = RequestorDA.getAllHospitals();
            while (rst.next()) {
                combo.addItem(rst.getString("Hospital"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setWardComboItems(JComboBox combo) {
        combo.removeAllItems();
        try {
            ResultSet rst = WardDA.getAllWards();
            while (rst.next()) {
                combo.addItem(rst.getString("WardName"));
            }
            combo.addItem("None");
        } catch (SQLException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setBloodTypeComboItems(JComboBox combo) {
        combo.removeAllItems();
        try {
            ResultSet rst = BloodTypeDA.getAllTypes();
            while (rst.next()) {
                combo.addItem(rst.getString("BloodType"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setBloodGroupComboItems(JComboBox combo) {
        combo.removeAllItems();
        try {
            ResultSet rst = BloodGroupDA.getAllGroups();
            while (rst.next()) {
                combo.addItem(rst.getString("GroupName"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setSearch(JComboBox combo, JDateChooser calendar) {
        try {
            combo.removeAllItems();
            /*Date*/
            java.util.Date date = calendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateOfRequest = "";
            if (date != null) {
                dateOfRequest = df.format(date);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                ResultSet rst = PatientRequestInDA.getAllRequests(sqlDate);
                while (rst.next()) {
                    combo.addItem(rst.getString("RequestID"));
                }
            }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        addRequestIDText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        addHospitalCombo = new javax.swing.JComboBox();
        addWardCombo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addPatientNameCombo = new javax.swing.JTextField();
        addAgeCombo = new javax.swing.JTextField();
        addMaleRadioBtn = new javax.swing.JRadioButton();
        addFemaleRadioBtn = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        addBHTText = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        addDateCalendar = new com.toedter.calendar.JDateChooser();
        addBloodTypeCombo = new javax.swing.JComboBox();
        addBloodGroupCombo = new javax.swing.JComboBox();
        addNoOfPacketsSpinner = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        addIndicationOfTransfusionText = new javax.swing.JTextField();
        addNewHospitalBtn = new javax.swing.JButton();
        addNewWardBtn = new javax.swing.JButton();
        addRequestBtn = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        addSendingOfficerText = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        specialRemarksText = new javax.swing.JTextField();
        addTime = new lu.tudor.santec.jtimechooser.JTimeChooser();
        jLabel50 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        searchPatientText = new javax.swing.JTextField();
        searcgAgeText = new javax.swing.JTextField();
        searchMaleRadioBtn = new javax.swing.JRadioButton();
        searchFemaleRadioBtn = new javax.swing.JRadioButton();
        jLabel26 = new javax.swing.JLabel();
        searchBHTText = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        seachIndicationOfTransfusionText = new javax.swing.JTextField();
        DeleteBtn = new javax.swing.JButton();
        searchWardText = new javax.swing.JTextField();
        searchHospitalText = new javax.swing.JTextField();
        searchNoOfPacketsText = new javax.swing.JTextField();
        searchBloodTypeText = new javax.swing.JTextField();
        searchBloodGroupText = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        searchSendingOfficerText = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        searchSpecialRemarksText = new javax.swing.JTextField();
        searchTimeText = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        searchDateCalendarCombo = new com.toedter.calendar.JDateChooser();
        jLabel34 = new javax.swing.JLabel();
        searchRequestIDCombo = new javax.swing.JComboBox();
        jLabel54 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        updateDateCalendarCombo = new com.toedter.calendar.JDateChooser();
        jLabel36 = new javax.swing.JLabel();
        updateRequestIDCombo = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        updateHospitalcombo = new javax.swing.JComboBox();
        updateWardCombo = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        updatePatientText = new javax.swing.JTextField();
        updateAgeText = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        updateMaleRadioBtn = new javax.swing.JRadioButton();
        updateFemaleRadioBtn = new javax.swing.JRadioButton();
        updateBHTText = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        updateIndicationOfTransfusionText = new javax.swing.JTextField();
        updateHospitalBtn = new javax.swing.JButton();
        updateWardBtn = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        updateDateCombo = new com.toedter.calendar.JDateChooser();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        updateBloodTypeCombo = new javax.swing.JComboBox();
        updateBloodGroupCombo = new javax.swing.JComboBox();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        updateNoOfPacketsSpinner = new javax.swing.JSpinner();
        updateBtn = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        updateSendingOfficerText = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        updateSpecialRemarksText = new javax.swing.JTextField();
        updateTimeText = new lu.tudor.santec.jtimechooser.JTimeChooser();
        jLabel55 = new javax.swing.JLabel();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter blood request details"));

        jLabel1.setText("Request ID");

        jLabel2.setText("Hospital");

        jLabel3.setText("Ward");

        jLabel4.setText("Patient Name");

        jLabel5.setText("Age");

        jLabel6.setText("Sex");

        buttonGroup1.add(addMaleRadioBtn);
        addMaleRadioBtn.setSelected(true);
        addMaleRadioBtn.setText("Male");

        buttonGroup1.add(addFemaleRadioBtn);
        addFemaleRadioBtn.setText("Female");

        jLabel13.setText("Bed Head Ticket");

        jLabel14.setText("Blood Group");

        jLabel15.setText("No. of packets");

        jLabel16.setText("Blood Type");

        jLabel17.setText("Date");

        jLabel18.setText("Time");

        addDateCalendar.setDateFormatString("yyyy-MM-dd");

        addNoOfPacketsSpinner.setModel(sm1);

        jLabel19.setText("Indication of Transfusion");

        addNewHospitalBtn.setText("Add/Update Hospital");
        addNewHospitalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewHospitalBtnActionPerformed(evt);
            }
        });

        addNewWardBtn.setText("Add/Update Ward");
        addNewWardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewWardBtnActionPerformed(evt);
            }
        });

        addRequestBtn.setText("Add Request");
        addRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRequestBtnActionPerformed(evt);
            }
        });

        jLabel47.setText("Name of sending officer");

        jLabel51.setText("Special Remarks");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addAgeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addPatientNameCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(addMaleRadioBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(addFemaleRadioBtn))
                                    .addComponent(addBHTText, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(addWardCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addIndicationOfTransfusionText, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
                            .addComponent(addHospitalCombo, 0, 206, Short.MAX_VALUE)
                            .addComponent(addRequestIDText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(addNewWardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addNewHospitalBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(94, 94, 94)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(addDateCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(38, 38, 38)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(addBloodGroupCombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 107, Short.MAX_VALUE)
                                            .addComponent(addBloodTypeCombo, 0, 107, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(addRequestBtn))
                                            .addComponent(specialRemarksText, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(addNoOfPacketsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(addTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(36, 36, 36))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addSendingOfficerText, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(420, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(addRequestIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(addNewHospitalBtn))
                            .addComponent(addHospitalCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(addWardCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(addPatientNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(addAgeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(addNewWardBtn)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(addTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(addBloodTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addBloodGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(addNoOfPacketsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(specialRemarksText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addMaleRadioBtn)
                        .addComponent(addFemaleRadioBtn)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addBHTText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(addIndicationOfTransfusionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(addSendingOfficerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addRequestBtn))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel50.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel50.setText("Add Blood Request");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(365, Short.MAX_VALUE)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(360, 360, 360))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Enter Blood Request", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Blood request details"));

        jLabel21.setText("Hospital");

        jLabel22.setText("Ward");

        jLabel23.setText("Patient Name");

        jLabel24.setText("Age");

        jLabel25.setText("Sex");

        searchPatientText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchPatientText.setEnabled(false);

        searcgAgeText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searcgAgeText.setEnabled(false);

        buttonGroup2.add(searchMaleRadioBtn);
        searchMaleRadioBtn.setText("Male");
        searchMaleRadioBtn.setEnabled(false);

        buttonGroup2.add(searchFemaleRadioBtn);
        searchFemaleRadioBtn.setText("Female");
        searchFemaleRadioBtn.setEnabled(false);

        jLabel26.setText("Bed Head Ticket");

        searchBHTText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchBHTText.setEnabled(false);

        jLabel27.setText("Blood Group");

        jLabel28.setText("No. of packets");

        jLabel29.setText("Blood Type");

        jLabel31.setText("Time");

        jLabel32.setText("Indication of Transfusion");

        seachIndicationOfTransfusionText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        seachIndicationOfTransfusionText.setEnabled(false);

        DeleteBtn.setText("Delete Request");
        DeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBtnActionPerformed(evt);
            }
        });

        searchWardText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchWardText.setEnabled(false);

        searchHospitalText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchHospitalText.setEnabled(false);

        searchNoOfPacketsText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchNoOfPacketsText.setEnabled(false);

        searchBloodTypeText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchBloodTypeText.setEnabled(false);

        searchBloodGroupText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchBloodGroupText.setEnabled(false);

        jLabel48.setText("Name of sending officer");

        searchSendingOfficerText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchSendingOfficerText.setEnabled(false);

        jLabel52.setText("Special Remarks");

        searchSpecialRemarksText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchSpecialRemarksText.setEnabled(false);

        searchTimeText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchTimeText.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(searchSendingOfficerText, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchBHTText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(searcgAgeText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(seachIndicationOfTransfusionText, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(searchWardText)
                                            .addComponent(searchHospitalText)
                                            .addComponent(searchPatientText))
                                        .addGap(227, 227, 227))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(searchMaleRadioBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(searchFemaleRadioBtn)
                                        .addGap(272, 272, 272)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGap(146, 146, 146)
                                            .addComponent(searchSpecialRemarksText))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(38, 38, 38)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(searchBloodTypeText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                                            .addComponent(searchBloodGroupText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                                            .addComponent(searchTimeText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                                                        .addGap(72, 72, 72))
                                                    .addComponent(DeleteBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addComponent(searchNoOfPacketsText, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(36, 36, 36))))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(searchTimeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchBloodTypeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchHospitalText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(searchWardText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchPatientText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(searchBloodGroupText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(searcgAgeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchNoOfPacketsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(searchMaleRadioBtn)
                            .addComponent(searchFemaleRadioBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(searchBHTText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(searchSpecialRemarksText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(seachIndicationOfTransfusionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(searchSendingOfficerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Date and RequestID"));

        jLabel33.setText("Date");

        searchDateCalendarCombo.setDateFormatString("yyyy-MM-dd");
        searchDateCalendarCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                searchDateCalendarComboPropertyChange(evt);
            }
        });

        jLabel34.setText("Request ID");

        searchRequestIDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchRequestIDComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchRequestIDCombo, 0, 165, Short.MAX_VALUE)
                    .addComponent(searchDateCalendarCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchDateCalendarCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchRequestIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jLabel54.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel54.setText("Search/Delete Blood Request");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(17, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Search Blood Request", jPanel4);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Date and RequestID"));

        jLabel35.setText("Date");

        updateDateCalendarCombo.setDateFormatString("yyyy-MM-dd");
        updateDateCalendarCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                updateDateCalendarComboPropertyChange(evt);
            }
        });

        jLabel36.setText("Request ID");

        updateRequestIDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRequestIDComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateRequestIDCombo, 0, 165, Short.MAX_VALUE)
                    .addComponent(updateDateCalendarCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateDateCalendarCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateRequestIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Blood request details"));

        jLabel20.setText("Hospital");

        jLabel30.setText("Ward");

        jLabel37.setText("Patient Name");

        jLabel38.setText("Age");

        jLabel39.setText("Sex");

        buttonGroup3.add(updateMaleRadioBtn);
        updateMaleRadioBtn.setText("Male");

        buttonGroup3.add(updateFemaleRadioBtn);
        updateFemaleRadioBtn.setText("Female");

        jLabel40.setText("Bed Head Ticket");

        jLabel41.setText("Indication of Transfusion");

        updateHospitalBtn.setText("Add new Hospital");
        updateHospitalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateHospitalBtnActionPerformed(evt);
            }
        });

        updateWardBtn.setText("Add new Ward");
        updateWardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateWardBtnActionPerformed(evt);
            }
        });

        jLabel42.setText("Date");

        updateDateCombo.setDateFormatString("yyyy-MM-dd");

        jLabel43.setText("Time");

        jLabel44.setText("Blood Type");

        jLabel45.setText("Blood Group");

        jLabel46.setText("No. of packets");

        updateNoOfPacketsSpinner.setModel(sm2);

        updateBtn.setText("Update Request");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        jLabel49.setText("Name of sending officer");

        jLabel53.setText("Special Remarks");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(updateSendingOfficerText, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                .addComponent(updateBHTText, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(184, 184, 184))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(updateMaleRadioBtn)
                                    .addGap(18, 18, 18)
                                    .addComponent(updateFemaleRadioBtn)
                                    .addGap(178, 178, 178))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(updateAgeText, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updateHospitalcombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(updateWardCombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(updatePatientText)
                                        .addComponent(updateIndicationOfTransfusionText))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(updateWardBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(updateHospitalBtn)))))))
                .addGap(110, 110, 110)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(updateBtn)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateTimeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateSpecialRemarksText, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(582, 582, 582)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateNoOfPacketsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(updateBloodGroupCombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(updateBloodTypeCombo, javax.swing.GroupLayout.Alignment.TRAILING, 0, 91, Short.MAX_VALUE))
                                .addGap(72, 72, 72)))
                        .addComponent(updateDateCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                    .addGap(27, 27, 27)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(updateHospitalcombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateHospitalBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(updateWardCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateWardBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updatePatientText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(updateAgeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(updateTimeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(updateMaleRadioBtn)
                    .addComponent(updateFemaleRadioBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(updateBHTText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53)
                    .addComponent(updateSpecialRemarksText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(updateIndicationOfTransfusionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(updateSendingOfficerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(jLabel43))
                        .addComponent(jLabel42)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(updateDateCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(45, 45, 45)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(updateBloodTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateBloodGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel46)
                                .addComponent(updateNoOfPacketsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(125, Short.MAX_VALUE)))
        );

        jLabel55.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel55.setText("Update Blood Request");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(128, 128, 128))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Update Blood Request", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addNewHospitalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewHospitalBtnActionPerformed
        AddRequestor requestor = new AddRequestor(this);
        requestor.setClosable(true);
        nurseForm.getDesktop().add(requestor);
        nurseForm.getDesktop().setRequestFocusEnabled(true);
        requestor.show();
    }//GEN-LAST:event_addNewHospitalBtnActionPerformed

    private void addNewWardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewWardBtnActionPerformed
        AddRequestor requestor = new AddRequestor(this);
        requestor.setClosable(true);
        nurseForm.getDesktop().add(requestor);
        nurseForm.getDesktop().setRequestFocusEnabled(true);
        requestor.show();
    }//GEN-LAST:event_addNewWardBtnActionPerformed

    private void addRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRequestBtnActionPerformed
        try {
            String requestID = addRequestIDText.getText();
            String hospital = "" + addHospitalCombo.getSelectedItem();
            String ward = "" + addWardCombo.getSelectedItem();
            if (ward.equalsIgnoreCase("none")) {
                ward = "";
            }
            String patientName = addPatientNameCombo.getText();
            int age = Integer.parseInt(addAgeCombo.getText());
            String gender = "";
            if (addMaleRadioBtn.isSelected()) {
                gender = "Male";
            } else {
                gender = "Female";
            }
            String bht = addBHTText.getText();
            String bloodType = "" + addBloodTypeCombo.getSelectedItem();
            String bloodGroup = "" + addBloodGroupCombo.getSelectedItem();
            int noOfPackets = Integer.parseInt("" + addNoOfPacketsSpinner.getValue());
            String indicationOfTransfusion = addIndicationOfTransfusionText.getText();
            /*Date*/
            java.util.Date date = addDateCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateOfRequest = df.format(date);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            /*Time*/
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            java.sql.Time sqlTime = new java.sql.Time(formatter.parse(addTime.getFormatedTime()).getTime());
            System.out.println(addTime.getFormatedTime());
            String sendingOfficer = addSendingOfficerText.getText();
            String specialRemarks = specialRemarksText.getText();
            PatientRequestIn newRequest = new PatientRequestIn(requestID, hospital, ward, patientName, age, gender, bht, bloodType, bloodGroup, noOfPackets, indicationOfTransfusion, sqlDate, sqlTime, sendingOfficer, specialRemarks);
            int res = PatientRequestInDA.addPatientRequest(newRequest);

            if (res == 1) {
                JOptionPane.showMessageDialog(null, "Added Succesfully");
                emptyAddFields();
                setSearch(searchRequestIDCombo, searchDateCalendarCombo);
                setSearch(updateRequestIDCombo, updateDateCalendarCombo);
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_addRequestBtnActionPerformed

    private void searchDateCalendarComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_searchDateCalendarComboPropertyChange
        emptySearchFields();
        setSearch(searchRequestIDCombo, searchDateCalendarCombo);


    }//GEN-LAST:event_searchDateCalendarComboPropertyChange

    private void updateDateCalendarComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_updateDateCalendarComboPropertyChange
        emptyUpdateFields();
        setSearch(updateRequestIDCombo, updateDateCalendarCombo);
    }//GEN-LAST:event_updateDateCalendarComboPropertyChange

    private void searchRequestIDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchRequestIDComboActionPerformed
        try {

            searchMaleRadioBtn.setSelected(false);
            searchFemaleRadioBtn.setSelected(false);

            String requestID = "" + searchRequestIDCombo.getSelectedItem();

            if (requestID != null) {
                ResultSet rst = PatientRequestInDA.searchRequest(requestID);

                while (rst.next()) {
                    searchHospitalText.setText(rst.getString("Hospital"));
                    searchWardText.setText(rst.getString("Ward"));
                    searchPatientText.setText(rst.getString("PatientName"));
                    searcgAgeText.setText("" + rst.getInt("Age"));

                    String gender = rst.getString("Gender");
                    if (gender.equalsIgnoreCase("Male")) {
                        searchMaleRadioBtn.setSelected(true);
                    } else {
                        searchFemaleRadioBtn.setSelected(true);
                    }


                    searchBHTText.setText(rst.getString("BHT"));
                    seachIndicationOfTransfusionText.setText(rst.getString("IndicationOfTransfusion"));
                    searchSendingOfficerText.setText(rst.getString("SendingOfficer"));
                    searchTimeText.setText("" + rst.getTime("Time"));
                    searchBloodTypeText.setText(rst.getString("BloodType"));
                    searchBloodGroupText.setText(rst.getString("BloodGroup"));
                    searchNoOfPacketsText.setText("" + rst.getInt("NoOfPackets"));
                    searchSpecialRemarksText.setText(rst.getString("SpecialRemarks"));
                }
            } else {
                emptySearchFields();
            }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchRequestIDComboActionPerformed

    private void updateRequestIDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateRequestIDComboActionPerformed
        updateMaleRadioBtn.setSelected(false);
        updateFemaleRadioBtn.setSelected(false);

        String requestID = "" + updateRequestIDCombo.getSelectedItem();

        if (requestID != null) {
            try {
                ResultSet rst = PatientRequestInDA.searchRequest(requestID);
                while (rst.next()) {
                    updateHospitalcombo.setSelectedItem(rst.getString("Hospital"));
                    updateWardCombo.setSelectedItem(rst.getString("Ward"));
                    updatePatientText.setText(rst.getString("PatientName"));
                    updateAgeText.setText("" + rst.getInt("Age"));
                    String gender = rst.getString("Gender");
                    if (gender.equalsIgnoreCase("Male")) {
                        updateMaleRadioBtn.setSelected(true);
                    } else {
                        updateFemaleRadioBtn.setSelected(true);
                    }
                    updateBHTText.setText(rst.getString("BHT"));
                    updateIndicationOfTransfusionText.setText(rst.getString("IndicationOfTransfusion"));
                    updateSendingOfficerText.setText(rst.getString("SendingOfficer"));
                    updateDateCombo.setDate(rst.getDate("Date"));
                    updateTimeText.setTime(rst.getTime("Time"));
                    updateBloodTypeCombo.setSelectedItem(rst.getString("BloodType"));
                    updateBloodGroupCombo.setSelectedItem(rst.getString("BloodGroup"));
                    updateNoOfPacketsSpinner.setValue(new Integer(rst.getInt("NoOfPackets")));
                    updateSpecialRemarksText.setText(rst.getString("SpecialRemarks"));
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_updateRequestIDComboActionPerformed

    private void updateHospitalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateHospitalBtnActionPerformed
        AddRequestor requestor = new AddRequestor(this);
        requestor.setClosable(true);
        nurseForm.getDesktop().add(requestor);
        nurseForm.getDesktop().setRequestFocusEnabled(true);
        requestor.show();
    }//GEN-LAST:event_updateHospitalBtnActionPerformed

    private void updateWardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateWardBtnActionPerformed
        AddRequestor requestor = new AddRequestor(this);
        requestor.setClosable(true);
        nurseForm.getDesktop().add(requestor);
        nurseForm.getDesktop().setRequestFocusEnabled(true);
        requestor.show();
    }//GEN-LAST:event_updateWardBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try {
            String requestID = "" + updateRequestIDCombo.getSelectedItem();
            String hospital = "" + updateHospitalcombo.getSelectedItem();
            String ward = "" + updateWardCombo.getSelectedItem();
            if (ward.equalsIgnoreCase("none")) {
                ward = "";
            }
            String patientName = updatePatientText.getText();
            int age = Integer.parseInt(updateAgeText.getText());
            String gender = "";
            if (updateMaleRadioBtn.isSelected()) {
                gender = "Male";
            } else {
                gender = "Female";
            }
            String bht = updateBHTText.getText();
            String bloodType = "" + updateBloodTypeCombo.getSelectedItem();
            String bloodGroup = "" + updateBloodGroupCombo.getSelectedItem();
            int noOfPackets = Integer.parseInt("" + updateNoOfPacketsSpinner.getValue());
            String indicationOfTransfusion = updateIndicationOfTransfusionText.getText();
            /*Date*/
            java.util.Date date = updateDateCombo.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateOfRequest = df.format(date);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            /*Time*/
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            java.sql.Time sqlTime = new java.sql.Time(formatter.parse(updateTimeText.getFormatedTime()).getTime());
            String sendingOfficer = updateSendingOfficerText.getText();
            String specialRemarks = updateSpecialRemarksText.getText();
            PatientRequestIn newRequest = new PatientRequestIn(requestID, hospital, ward, patientName, age, gender, bht, bloodType, bloodGroup, noOfPackets, indicationOfTransfusion, sqlDate, sqlTime, sendingOfficer, specialRemarks);
            int res = PatientRequestInDA.updatePatientRequest(newRequest);
            if (res == 1) {
                JOptionPane.showMessageDialog(null, "Updated Succesfully");
                emptyUpdateFields();
                setSearch(searchRequestIDCombo, searchDateCalendarCombo);
                setSearch(updateRequestIDCombo, updateDateCalendarCombo);
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void DeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBtnActionPerformed
        try {
            String requestID = "" + searchRequestIDCombo.getSelectedItem();
            int res = PatientRequestInDA.deletePatientRequest(requestID);
            if (res == 1) {
                JOptionPane.showMessageDialog(null, "Deleted Succesfully");
                emptySearchFields();
                setSearch(searchRequestIDCombo, searchDateCalendarCombo);
                setSearch(updateRequestIDCombo, updateDateCalendarCombo);
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PatientRequestsIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DeleteBtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteBtn;
    private javax.swing.JTextField addAgeCombo;
    private javax.swing.JTextField addBHTText;
    private javax.swing.JComboBox addBloodGroupCombo;
    private javax.swing.JComboBox addBloodTypeCombo;
    private com.toedter.calendar.JDateChooser addDateCalendar;
    private javax.swing.JRadioButton addFemaleRadioBtn;
    private javax.swing.JComboBox addHospitalCombo;
    private javax.swing.JTextField addIndicationOfTransfusionText;
    private javax.swing.JRadioButton addMaleRadioBtn;
    private javax.swing.JButton addNewHospitalBtn;
    private javax.swing.JButton addNewWardBtn;
    private javax.swing.JSpinner addNoOfPacketsSpinner;
    private javax.swing.JTextField addPatientNameCombo;
    private javax.swing.JButton addRequestBtn;
    private javax.swing.JTextField addRequestIDText;
    private javax.swing.JTextField addSendingOfficerText;
    private lu.tudor.santec.jtimechooser.JTimeChooser addTime;
    private javax.swing.JComboBox addWardCombo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField seachIndicationOfTransfusionText;
    private javax.swing.JTextField searcgAgeText;
    private javax.swing.JTextField searchBHTText;
    private javax.swing.JTextField searchBloodGroupText;
    private javax.swing.JTextField searchBloodTypeText;
    private com.toedter.calendar.JDateChooser searchDateCalendarCombo;
    private javax.swing.JRadioButton searchFemaleRadioBtn;
    private javax.swing.JTextField searchHospitalText;
    private javax.swing.JRadioButton searchMaleRadioBtn;
    private javax.swing.JTextField searchNoOfPacketsText;
    private javax.swing.JTextField searchPatientText;
    private javax.swing.JComboBox searchRequestIDCombo;
    private javax.swing.JTextField searchSendingOfficerText;
    private javax.swing.JTextField searchSpecialRemarksText;
    private javax.swing.JTextField searchTimeText;
    private javax.swing.JTextField searchWardText;
    private javax.swing.JTextField specialRemarksText;
    private javax.swing.JTextField updateAgeText;
    private javax.swing.JTextField updateBHTText;
    private javax.swing.JComboBox updateBloodGroupCombo;
    private javax.swing.JComboBox updateBloodTypeCombo;
    private javax.swing.JButton updateBtn;
    private com.toedter.calendar.JDateChooser updateDateCalendarCombo;
    private com.toedter.calendar.JDateChooser updateDateCombo;
    private javax.swing.JRadioButton updateFemaleRadioBtn;
    private javax.swing.JButton updateHospitalBtn;
    private javax.swing.JComboBox updateHospitalcombo;
    private javax.swing.JTextField updateIndicationOfTransfusionText;
    private javax.swing.JRadioButton updateMaleRadioBtn;
    private javax.swing.JSpinner updateNoOfPacketsSpinner;
    private javax.swing.JTextField updatePatientText;
    private javax.swing.JComboBox updateRequestIDCombo;
    private javax.swing.JTextField updateSendingOfficerText;
    private javax.swing.JTextField updateSpecialRemarksText;
    private lu.tudor.santec.jtimechooser.JTimeChooser updateTimeText;
    private javax.swing.JButton updateWardBtn;
    private javax.swing.JComboBox updateWardCombo;
    // End of variables declaration//GEN-END:variables
}
