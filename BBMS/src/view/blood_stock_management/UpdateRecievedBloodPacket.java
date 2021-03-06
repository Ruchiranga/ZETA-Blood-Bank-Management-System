/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.blood_stock_management;

import controller.common.IDGenerator;
import controller.blood_stock_management.BloodGroupController;
import controller.blood_stock_management.BloodPacketController;
import controller.blood_stock_management.BloodTypeController;
import controller.donor_management.DonorController;
import controller.testing.TestController;
import controller.testing.TestResultController;
import connection.NotifierConnection;
import controller.blood_stock_management.BloodStockUpdateNotifier;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BloodPacket;
import model.TestResult;

/**
 *
 * @author Anuradha
 */
public class UpdateRecievedBloodPacket extends javax.swing.JInternalFrame {

    BloodRecieval recievalForm;
    String[] row;
    int rowNo;
    String[] title = {"TTI Results"};
    DefaultTableModel dtm = new DefaultTableModel(title, 0);
    
    BloodStockUpdateNotifier notifier = null;

    /**
     * Creates new form SearchRecievedBloodPacket
     */
    public UpdateRecievedBloodPacket(BloodRecieval recievalForm, String[] row, int rowNo) {

        try {
            initComponents();
            
            FileInputStream imgStream = null;
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            imgStream = new FileInputStream(imgfile);
            BufferedImage bi = ImageIO.read(imgStream);
            ImageIcon myImg = new ImageIcon(bi);
            this.setFrameIcon(myImg);
            setTitle("Updated Recieved Blood Packet");
            
            notifier = NotifierConnection.getNotifierConnection(null);
            
            this.recievalForm = recievalForm;
            this.row = row;
            this.rowNo = rowNo;
            setBloodTypeCombo(bloodTypeCombo);
            setBloodGroupCombo(groupCombo);
            setTestCombo(TestsCombo);
            
            Calendar currenttime = Calendar.getInstance();
            java.util.Date today = new java.util.Date((currenttime.getTime()).getTime());
            dateOfCollectionCalendar.setDate(today);
            dateOfExpiryCalendar.setDate(today);
            
            setData();
        } catch (IOException ex) {
            Logger.getLogger(UpdateRecievedBloodPacket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setData() {
        //"Unit No", "Donor's Name", "Blood Group", "Blood Type", "Date of collection", "Date of Expiry", "TTI Results", "Remarks"
        try {
            packIDText.setText(row[0]);
            donorNameText.setText(row[1]);
            groupCombo.setSelectedItem(row[2]);
            bloodTypeCombo.setSelectedItem(row[3]);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date convertedDate = dateFormat.parse(row[4]);
            dateOfCollectionCalendar.setDate(convertedDate);
            convertedDate = dateFormat.parse(row[5]);
            dateOfExpiryCalendar.setDate(convertedDate);

            commentText.setText(row[7]);

            String[] tests = row[6].split(",");

            for (int i = 0; i < tests.length; i++) {
                String[] test = {""};
                test[0] = tests[i];
                dtm.addRow(test);
                TestsCombo.removeItem(tests[i]);
            }

        } catch (ParseException ex) {
            Logger.getLogger(UpdateRecievedBloodPacket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setTestCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = TestController.getAllTests();

            while (rst.next()) {
                combo.addItem(rst.getString("Name"));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setBloodTypeCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = BloodTypeController.getAllTypes();

            while (rst.next()) {
                combo.addItem(rst.getString("BloodType"));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setBloodGroupCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = BloodGroupController.getAllGroups();

            while (rst.next()) {
                combo.addItem(rst.getString("GroupName"));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        packIDText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        groupCombo = new javax.swing.JComboBox();
        donorNameText = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        dateOfCollectionCalendar = new com.toedter.calendar.JDateChooser();
        dateOfExpiryCalendar = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        bloodTypeCombo = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        TestsCombo = new javax.swing.JComboBox();
        addResultBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        commentText = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ttiTable = new javax.swing.JTable();
        removeResultBtn = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();

        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Add blood packet details"));

        jLabel3.setText("Packet ID*");

        packIDText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        packIDText.setEnabled(false);
        packIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packIDTextActionPerformed(evt);
            }
        });

        jLabel4.setText("Donor Name*");

        jLabel6.setText("Date of expiry*");

        jLabel8.setText("Date of collection*");

        jLabel11.setText("Blood Group*");

        groupCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blood Groups" }));

        addBtn.setText("Update Blood Packet");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        dateOfCollectionCalendar.setDateFormatString("yyyy-MM-dd");

        dateOfExpiryCalendar.setDateFormatString("yyyy-MM-dd");

        jLabel22.setText("Blood Type*");

        bloodTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blood Types" }));

        jLabel7.setText("TTI Results");

        TestsCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTI Tests" }));

        addResultBtn.setText("Add Result");
        addResultBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addResultBtnActionPerformed(evt);
            }
        });

        jLabel5.setText("Remarks");

        ttiTable.setModel(dtm);
        jScrollPane1.setViewportView(ttiTable);

        removeResultBtn.setText("Remove Result");
        removeResultBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeResultBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(donorNameText)
                                    .addComponent(packIDText)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(commentText)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(dateOfExpiryCalendar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dateOfCollectionCalendar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 87, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(TestsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bloodTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(removeResultBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addResultBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(packIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bloodTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(donorNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateOfCollectionCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateOfExpiryCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TestsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addResultBtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeResultBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(commentText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addBtn))))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jLabel51.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel51.setText("Update Recieved Blood Packet");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Update Blood Packet", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void packIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packIDTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_packIDTextActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        try {
            String packetID = packIDText.getText();
            String name = donorNameText.getText();
            String bloodGroup = "" + groupCombo.getSelectedItem();
            String bloodType = "" + bloodTypeCombo.getSelectedItem();

            /*Collection date*/
            java.util.Date dateC = dateOfCollectionCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateCollection = df.format(dateC);
            java.sql.Date sqlDateC = new java.sql.Date(dateC.getTime());

            /*Expiry date*/
            java.util.Date dateE = dateOfExpiryCalendar.getDate();
            String dateExpiry = df.format(dateE);
            java.sql.Date sqlDateE = new java.sql.Date(dateE.getTime());

            String comment = commentText.getText();

            int testCount;
            String testResults = "";

            if (dtm.getRowCount() >= 0) {
                testResults = testResults + dtm.getValueAt(0, 0);
                for (int i = 1; i < dtm.getRowCount(); i++) {
                    testResults = testResults + "," + dtm.getValueAt(i, 0);
                }
            }

            String donorNic = BloodPacketController.getDonorNic(packetID);

            BloodPacket newPacket = new BloodPacket(packetID, donorNic, null, sqlDateC, sqlDateE, bloodType, (byte) 0, (byte) 0, (byte) 0, null, (byte) 0, bloodGroup, null, null, null, comment, null);

            int resDonor = DonorController.updateDonorFromOtherBloodBank(donorNic, name);
            int resPacket = BloodPacketController.updateRecievedBloodPacket(newPacket);

            if (resDonor == 1 && resPacket == 1) {
                notifier.notifyUpdateBloodStock();
                JOptionPane.showMessageDialog(null, "Updated successfully!");
                recievalForm.updateData(rowNo, packetID, name, bloodGroup, bloodType, dateCollection, dateExpiry, testResults, comment);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error occured while updating!");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateRecievedBloodPacket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateRecievedBloodPacket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void addResultBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addResultBtnActionPerformed
        try {
            if (TestsCombo.getSelectedItem() != null) {
                String packetID = packIDText.getText();
                String[] tests = new String[1];
                tests[0] = "" + TestsCombo.getSelectedItem();
                //finding testResultID
                String testResultID = "";
                ResultSet rst_testResult;

                rst_testResult = TestResultController.getAllTests();

                rst_testResult.last();
                String curTestResultID = null;
                try {
                    curTestResultID = rst_testResult.getString("ResultID");
                    try {
                        testResultID = IDGenerator.generateNextID(curTestResultID);
                    } catch (Exception ex) {
                        Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException e) {
                    testResultID = "RS00000001";
                }

                String test = tests[0];
                int res_Test = 0;
                String testId = TestController.getTestID(test);
                if (testId != null) {
                    TestResult result = new TestResult(testResultID, testId, packetID, "negative", "None", null, null, null, null);
                    res_Test += TestResultController.addTestResultOfRecievedBloodPackets(result);
                    if (res_Test == 1) {
                        TestsCombo.removeItem(TestsCombo.getSelectedItem());
                        dtm.addRow(tests);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occured!");
                    }
                }

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateRecievedBloodPacket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateRecievedBloodPacket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addResultBtnActionPerformed

    private void removeResultBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeResultBtnActionPerformed
        try {
            String packetID = packIDText.getText();
            int row = ttiTable.getSelectedRow();

            if (row >= 0) {

                String test = "" + dtm.getValueAt(row, 0);
                String testId = TestController.getTestID(test);
                int res = TestResultController.removeTestResultOfRecievedBloodPacket(testId,packetID);
                if(res == 1){
                    TestsCombo.addItem(test);
                    dtm.removeRow(row);
                }else{
                    JOptionPane.showMessageDialog(null, "An error occured!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Select a row");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateRecievedBloodPacket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateRecievedBloodPacket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_removeResultBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox TestsCombo;
    private javax.swing.JButton addBtn;
    private javax.swing.JButton addResultBtn;
    private javax.swing.JComboBox bloodTypeCombo;
    private javax.swing.JTextField commentText;
    private com.toedter.calendar.JDateChooser dateOfCollectionCalendar;
    private com.toedter.calendar.JDateChooser dateOfExpiryCalendar;
    private javax.swing.JTextField donorNameText;
    private javax.swing.JComboBox groupCombo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField packIDText;
    private javax.swing.JButton removeResultBtn;
    private javax.swing.JTable ttiTable;
    // End of variables declaration//GEN-END:variables
}
