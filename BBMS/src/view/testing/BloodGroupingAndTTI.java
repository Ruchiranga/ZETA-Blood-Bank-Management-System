/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.testing;

import controller.blood_stock_management.BloodGroupController;
import controller.blood_stock_management.BloodPacketController;
import controller.donor_management.DonorController;
import controller.camp_management.EmployeeController;
import controller.testing.TestController;
import controller.testing.TestResultController;
import controller.common.SearchableCombo;
import controller.common.TableCleaner;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.TestResult;

/**
 *
 * @author ruchiranga
 */
public class BloodGroupingAndTTI extends javax.swing.JInternalFrame {

//    BloodGroupingAndTTIHandler handler;
    DefaultTableModel dtm;
    JDesktopPane pane;
    BloodPacketController packetController;
    BloodGroupController groupController;
    TestController testController;
    EmployeeController employeeController;
    DonorController donorController;
    TestResultController testResultController;

    /**
     * Creates new form BloodGrouping
     *
     */
    public BloodGroupingAndTTI(JDesktopPane pane) throws IOException {
        initComponents();
        
        FileInputStream imgStream = null;
        File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
        imgStream = new FileInputStream(imgfile);
        BufferedImage bi = ImageIO.read(imgStream);
        ImageIcon myImg = new ImageIcon(bi);
        this.setFrameIcon(myImg);
        setTitle("Blood Grouping and TTI");
        
        this.pane = pane;
        packetController = new BloodPacketController();
        groupController = new BloodGroupController();
        testController = new TestController();
        employeeController = new EmployeeController();
        donorController = new DonorController();
        testResultController = new TestResultController();

        String[] packetList = null;
        try {
            packetList = packetController.getPacketIDList();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String id : packetList) {
            packetIDListCombo.addItem(id);
        }

        String[] groupList = null;

        try {
            groupList = groupController.getGroupList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String group : groupList) {
            bloodGroupCombo.addItem(group);
        }

        String[] testList = null;

        try {
            testList = testController.getTestList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String test : testList) {
            testListCombo.addItem(test);
        }

        ButtonGroup group = new ButtonGroup();
        group.add(negativeRadioButton);
        group.add(positiveRadioButton);
        negativeRadioButton.setSelected(true);

        String[] columns = {"Test Name", "Result", "Comments"};
        dtm = new DefaultTableModel(columns, 0);
        testTable.setModel(dtm);

        String[] emp = null;

        try {
            emp = employeeController.getEmployeeList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String name : emp) {
            doneByComboBox.addItem(name);
            checkedByComboBox.addItem(name);
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dateText.setText(dateFormat.format(date));

        new SearchableCombo().setSearchableCombo(packetIDListCombo, true);
        new SearchableCombo().setSearchableCombo(testListCombo, true);
        new SearchableCombo().setSearchableCombo(doneByComboBox, true);
        new SearchableCombo().setSearchableCombo(checkedByComboBox, true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        bloodGroupCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        groupCommentTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        commentsTextField = new javax.swing.JTextField();
        testListCombo = new javax.swing.JComboBox();
        negativeRadioButton = new javax.swing.JRadioButton();
        positiveRadioButton = new javax.swing.JRadioButton();
        addToListButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        testTable = new javax.swing.JTable();
        deleteRowButton = new javax.swing.JButton();
        addNewTestButton = new javax.swing.JButton();
        checkedByComboBox = new javax.swing.JComboBox();
        dateText = new javax.swing.JTextField();
        OKButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        doneByComboBox = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        donorTextField = new javax.swing.JTextField();
        blacklistdonerButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        unblacklistButton = new javax.swing.JButton();
        discardPacketCheckBox = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        packetIDListCombo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();

        setTitle("Blood Grouping and TTI");

        jPanel2.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Blood Grouping and TTI");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(210, 20, 250, 30);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SmallFormHeader.png"))); // NOI18N
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel2.add(jLabel13);
        jLabel13.setBounds(0, 0, 607, 73);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("ABO Typing Test"));

        bloodGroupCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodGroupComboActionPerformed(evt);
            }
        });
        bloodGroupCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bloodGroupComboKeyTyped(evt);
            }
        });

        jLabel2.setText("Blood Group");

        jLabel12.setText("Special Comment");

        groupCommentTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                groupCommentTextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(groupCommentTextField)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bloodGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 112, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bloodGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(groupCommentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel3.setText("Tests done by");

        jLabel4.setText("Results checked by");

        jLabel5.setText("Date");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Tests"));

        jLabel6.setText("Test name");

        jLabel7.setText("Result");

        jLabel8.setText("Special Comments");

        commentsTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                commentsTextFieldKeyTyped(evt);
            }
        });

        testListCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testListComboActionPerformed(evt);
            }
        });
        testListCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                testListComboKeyTyped(evt);
            }
        });

        negativeRadioButton.setText("Negative");
        negativeRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                negativeRadioButtonActionPerformed(evt);
            }
        });

        positiveRadioButton.setText("Positive");
        positiveRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positiveRadioButtonActionPerformed(evt);
            }
        });

        addToListButton.setText("Add to list");
        addToListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToListButtonActionPerformed(evt);
            }
        });

        testTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Test Name", "Result", "Comments"
            }
        ));
        jScrollPane1.setViewportView(testTable);

        deleteRowButton.setText("Delete row");
        deleteRowButton.setEnabled(false);
        deleteRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRowButtonActionPerformed(evt);
            }
        });

        addNewTestButton.setText("Add New Test ");
        addNewTestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewTestButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(commentsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(testListCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                                .addComponent(negativeRadioButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(positiveRadioButton)))
                                        .addGap(31, 31, 31)
                                        .addComponent(addNewTestButton))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(addToListButton)
                                .addGap(18, 18, 18)
                                .addComponent(deleteRowButton)))
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(testListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addNewTestButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(negativeRadioButton)
                    .addComponent(positiveRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(commentsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToListButton)
                    .addComponent(deleteRowButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        checkedByComboBox.setEditable(true);
        checkedByComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                checkedByComboBoxKeyTyped(evt);
            }
        });

        dateText.setEnabled(false);

        OKButton.setText("OK");
        OKButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        doneByComboBox.setEditable(true);
        doneByComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                doneByComboBoxKeyTyped(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Donor"));

        donorTextField.setEnabled(false);

        blacklistdonerButton.setText("Blacklist Donor");
        blacklistdonerButton.setFocusable(false);
        blacklistdonerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blacklistdonerButtonActionPerformed(evt);
            }
        });

        jLabel11.setText("Donor");

        unblacklistButton.setText("Unblacklist Donor");
        unblacklistButton.setEnabled(false);
        unblacklistButton.setFocusable(false);
        unblacklistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unblacklistButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(blacklistdonerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(unblacklistButton))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(donorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(donorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(blacklistdonerButton)
                    .addComponent(unblacklistButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        discardPacketCheckBox.setText("Discard Packet");
        discardPacketCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardPacketCheckBoxActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Packet"));

        packetIDListCombo.setEditable(true);
        packetIDListCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packetIDListComboActionPerformed(evt);
            }
        });
        packetIDListCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                packetIDListComboKeyTyped(evt);
            }
        });

        jLabel9.setText("Blood Packet ID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(packetIDListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(packetIDListCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(doneByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(discardPacketCheckBox)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(checkedByComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(OKButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(discardPacketCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(doneByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkedByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OKButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bloodGroupComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodGroupComboActionPerformed

    }//GEN-LAST:event_bloodGroupComboActionPerformed

    private void bloodGroupComboKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bloodGroupComboKeyTyped
        char c = evt.getKeyChar();
        if (c == java.awt.event.KeyEvent.VK_ENTER) {
            groupCommentTextField.requestFocus(true);
        }
    }//GEN-LAST:event_bloodGroupComboKeyTyped

    private void groupCommentTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_groupCommentTextFieldKeyTyped
        //        char c = evt.getKeyChar();
        //        if (groupCommentTextField.getText().length() < 10) {
            //            if (c == java.awt.event.KeyEvent.VK_ENTER) {
                //                nameText.requestFocus(true);
                //            }
            //        } else {
            //            evt.consume();
            //        }

        char c = evt.getKeyChar();
        if (c == java.awt.event.KeyEvent.VK_ENTER) {
            testListCombo.requestFocus(true);
        }
        if (groupCommentTextField.getText().length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_groupCommentTextFieldKeyTyped

    private void commentsTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_commentsTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (c == java.awt.event.KeyEvent.VK_ENTER) {
            addToListButton.requestFocus(true);
        }
        if (commentsTextField.getText().length() >= 75) {
            evt.consume();
        }
    }//GEN-LAST:event_commentsTextFieldKeyTyped

    private void testListComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testListComboActionPerformed
        if (testListCombo.getItemCount() == 0) {
            addToListButton.setEnabled(false);
            testListCombo.setEnabled(false);
            negativeRadioButton.requestFocus(true);
        } else {
            addToListButton.setEnabled(true);
            testListCombo.setEnabled(true);
        }
    }//GEN-LAST:event_testListComboActionPerformed

    private void testListComboKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_testListComboKeyTyped
        char c = evt.getKeyChar();
        if (c == java.awt.event.KeyEvent.VK_ENTER) {
            if (testListCombo.getItemCount() == 0) {
                negativeRadioButton.requestFocus(true);
            }
        }
    }//GEN-LAST:event_testListComboKeyTyped

    private void negativeRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_negativeRadioButtonActionPerformed
        commentsTextField.requestFocus(true);
    }//GEN-LAST:event_negativeRadioButtonActionPerformed

    private void positiveRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positiveRadioButtonActionPerformed
        commentsTextField.requestFocus();
    }//GEN-LAST:event_positiveRadioButtonActionPerformed

    private void addToListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToListButtonActionPerformed
        if (testListCombo.getItemCount() > 0) {
            testListCombo.setEnabled(true);
            String testName = (String) testListCombo.getSelectedItem();
            String result = null;
            String comments = null;

            if (negativeRadioButton.isSelected()) {
                result = "Negative";
            } else {
                result = "Positive";
            }

            if (commentsTextField.getText().isEmpty()) {
                comments = "None";
            } else {
                comments = commentsTextField.getText();
            }
            String[] row = {testName, result, comments};
            dtm.addRow(row);

            testListCombo.removeItemAt(testListCombo.getSelectedIndex());

            if (dtm.getRowCount() > 0) {
                deleteRowButton.setEnabled(true);
            } else {
                deleteRowButton.setEnabled(false);
            }

            commentsTextField.setText("");
            testListCombo.requestFocus(true);
        } else {
            testListCombo.setEnabled(false);
        }
        if (testListCombo.getItemCount() == 0) {
            discardPacketCheckBox.setRequestFocusEnabled(true);
        }
    }//GEN-LAST:event_addToListButtonActionPerformed

    private void deleteRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRowButtonActionPerformed
        int selectedRow = testTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select row to delete!", "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String test = (String) dtm.getValueAt(selectedRow, 0);
            dtm.removeRow(selectedRow);
            testListCombo.addItem(test);
            if (dtm.getRowCount() > 0) {
                deleteRowButton.setEnabled(true);
            } else {
                deleteRowButton.setEnabled(false);
            }
        }
        if (testListCombo.getItemCount() > 0) {
            testListCombo.setEnabled(true);
        } else {
            testListCombo.setEnabled(false);
        }
    }//GEN-LAST:event_deleteRowButtonActionPerformed

    private void addNewTestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewTestButtonActionPerformed
        this.hide();
        AddNewTestType newTestForm = null;

        try {
            newTestForm = new AddNewTestType(this);
        } catch (IOException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        newTestForm.setClosable(true);
        newTestForm.setMaximizable(true);
        pane.add(newTestForm);
    }//GEN-LAST:event_addNewTestButtonActionPerformed

    private void checkedByComboBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkedByComboBoxKeyTyped
        char c = evt.getKeyChar();
        if (c == java.awt.event.KeyEvent.VK_ENTER) {
            OKButton.requestFocus();
        }
    }//GEN-LAST:event_checkedByComboBoxKeyTyped

    private void OKButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKButtonActionPerformed

        if (!discardPacketCheckBox.isSelected() && dtm.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please specify the test results!", "", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String packetID = (String) packetIDListCombo.getSelectedItem();
        packetID = packetID.trim();
        String group = (String) bloodGroupCombo.getSelectedItem();
        String groupComment = groupCommentTextField.getText();
        int groupres = 0;
        try {
            groupres = packetController.setBloodGroup(packetID, group, groupComment);
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        String date = dateText.getText();
        String doneBy = (String) doneByComboBox.getSelectedItem();
        String checkedBy = (String) checkedByComboBox.getSelectedItem();

        int testCount = dtm.getRowCount();
        int resultres = 1;
        for (int i = 0; i < testCount; i++) {
            String testName = (String) dtm.getValueAt(i, 0);
            String result = (String) dtm.getValueAt(i, 1);
            String comment = (String) dtm.getValueAt(i, 2);
            int res;
            try {
                String testID = testController.getTestIDOF(testName);
                String doneByNic = employeeController.getIDOf(doneBy.trim());
                String checkedByNic = employeeController.getIDOf(checkedBy.trim());
                res = testResultController.addTestResult(new TestResult(null, testID, packetID, result, comment, date, doneByNic, checkedByNic, null));
                if (res == 0) {
                    resultres = 0;
                }
                //                handler.addTestResult(testName, packetID, result, comment, date, doneBy, checkedBy);
            } catch (Exception ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        int disres = 0;
        if (discardPacketCheckBox.isSelected()) {
            try {
                disres = packetController.discardPacket(packetID, date);
            } catch (SQLException ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (resultres == 1 && groupres == 1) {
            JOptionPane.showMessageDialog(this, "Results recorded successfully.", "", JOptionPane.INFORMATION_MESSAGE);
            packetIDListCombo.removeItemAt(packetIDListCombo.getSelectedIndex());
            bloodGroupCombo.setSelectedIndex(0);
            groupCommentTextField.setText("");
            String[] testList = null;

            try {
                testList = testController.getTestList();
            } catch (SQLException ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            }
            testListCombo.removeAllItems();
            for (String test : testList) {
                testListCombo.addItem(test);
            }
            TableCleaner.clearTable(dtm);
            addToListButton.setEnabled(true);
            deleteRowButton.setEnabled(false);

            doneByComboBox.setSelectedIndex(0);
            checkedByComboBox.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "Error! Failed to record results.", "", JOptionPane.ERROR_MESSAGE);
        }
        if (discardPacketCheckBox.isSelected() && disres == 1) {
            JOptionPane.showMessageDialog(this, "Packet marked as discarded.", "", JOptionPane.INFORMATION_MESSAGE);
            discardPacketCheckBox.setSelected(false);
        } else if (discardPacketCheckBox.isSelected() && disres == 0) {
            JOptionPane.showMessageDialog(this, "Error! Failed to mark packet as discarded.", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_OKButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void doneByComboBoxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doneByComboBoxKeyTyped
        char c = evt.getKeyChar();
        if (c == java.awt.event.KeyEvent.VK_ENTER) {
            checkedByComboBox.requestFocus();
        }
    }//GEN-LAST:event_doneByComboBoxKeyTyped

    private void blacklistdonerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blacklistdonerButtonActionPerformed

        int choice;
        choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to blacklist this donor?", "Are you Sure?", JOptionPane.YES_NO_OPTION);
        if (choice == 0) {
            String name = donorTextField.getText();
            int res = 0;
            try {
                res = donorController.blackListDonor(name);

            } catch (SQLException ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (res == 1) {
                blacklistdonerButton.setEnabled(false);
                unblacklistButton.setEnabled(true);
            } else {
                JOptionPane.showInternalMessageDialog(this, "Failed to blacklist donor", "Error!", JOptionPane.ERROR_MESSAGE);
                blacklistdonerButton.setEnabled(true);
                unblacklistButton.setEnabled(false);
            }
        }
    }//GEN-LAST:event_blacklistdonerButtonActionPerformed

    private void unblacklistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unblacklistButtonActionPerformed
        int choice;
        choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to unblacklist this donor?", "Are you Sure?", JOptionPane.YES_NO_OPTION);
        if (choice == 0) {
            String name = donorTextField.getText();
            int res = 0;
            try {
                res = donorController.unblackListDonor(name);

            } catch (SQLException ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (res == 1) {
                blacklistdonerButton.setEnabled(true);
                unblacklistButton.setEnabled(false);
            } else {
                JOptionPane.showInternalMessageDialog(this, "Failed to blacklist donor", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_unblacklistButtonActionPerformed

    private void discardPacketCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardPacketCheckBoxActionPerformed
        if (discardPacketCheckBox.isSelected()) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to discard this packet?", "Are You sure?", JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                OKButton.setText("      OK      ");
            } else {
                discardPacketCheckBox.setSelected(false);
            }
        } else {
            OKButton.setText("Generate Packet Label");
        }
        doneByComboBox.requestFocus();
    }//GEN-LAST:event_discardPacketCheckBoxActionPerformed

    private void packetIDListComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packetIDListComboActionPerformed
        String packetID = (String) packetIDListCombo.getSelectedItem();

        try {
            try {
                donorTextField.setText(packetController.getDonorNameOf(packetID));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }
        String donorID = null;
        try {
            donorID = packetController.getDonorIDOF(packetID);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean isBlackListed = false;
        try {
            isBlackListed = donorController.isDonorBlacklisted(donorID);
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isBlackListed) {
            blacklistdonerButton.setEnabled(false);
            unblacklistButton.setEnabled(true);
        } else {
            blacklistdonerButton.setEnabled(true);
            unblacklistButton.setEnabled(false);
        }
    }//GEN-LAST:event_packetIDListComboActionPerformed

    private void packetIDListComboKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_packetIDListComboKeyTyped
        char c = evt.getKeyChar();
        if (c == java.awt.event.KeyEvent.VK_ENTER) {
            bloodGroupCombo.requestFocus(true);
        }
    }//GEN-LAST:event_packetIDListComboKeyTyped

    public void updateTestListCombo() {
        testListCombo.removeAllItems();
        String[] testList = null;

        try {
            testList = testController.getTestList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String test : testList) {
            boolean added = false;
            for (int i = 0; i < dtm.getRowCount(); i++) {
                if (((String) dtm.getValueAt(i, 0)).equals(test)) {
                    added = true;
                }
            }
            if (!added) {
                testListCombo.addItem(test);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OKButton;
    private javax.swing.JButton addNewTestButton;
    private javax.swing.JButton addToListButton;
    private javax.swing.JButton blacklistdonerButton;
    private javax.swing.JComboBox bloodGroupCombo;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox checkedByComboBox;
    private javax.swing.JTextField commentsTextField;
    private javax.swing.JTextField dateText;
    private javax.swing.JButton deleteRowButton;
    private javax.swing.JCheckBox discardPacketCheckBox;
    private javax.swing.JComboBox doneByComboBox;
    private javax.swing.JTextField donorTextField;
    private javax.swing.JTextField groupCommentTextField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton negativeRadioButton;
    private javax.swing.JComboBox packetIDListCombo;
    private javax.swing.JRadioButton positiveRadioButton;
    private javax.swing.JComboBox testListCombo;
    private javax.swing.JTable testTable;
    private javax.swing.JButton unblacklistButton;
    // End of variables declaration//GEN-END:variables
}
