/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BloodPacketForm.java
 *
 * Created on Sep 3, 2014, 4:05:48 PM
 */
package view.blood_stock_management;

import view.users.Nurse;
import controller.blood_stock_management.BloodGroupController;
import controller.blood_stock_management.BloodPacketController;
import controller.blood_stock_management.BloodTypeController;
import controller.camp_management.CampController;
import controller.donor_management.DonorController;
import connection.NotifierConnection;
import controller.common.SearchableCombo;
import controller.blood_stock_management.BloodStockUpdateNotifier;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.BloodPacket;

/**
 *
 * @author Anuradha
 */
public class BloodPacketForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form BloodPacketForm
     */
    Calendar currenttime = Calendar.getInstance();
    java.util.Date today = new java.util.Date((currenttime.getTime()).getTime());

    BloodStockUpdateNotifier notifier = null;

    public BloodPacketForm() {
        try {
            initComponents();
            FileInputStream imgStream = null;
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            imgStream = new FileInputStream(imgfile);
            BufferedImage bi = null;
            try {
                bi = ImageIO.read(imgStream);
            } catch (IOException ex) {
                Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageIcon myImg = new ImageIcon(bi);
            this.setFrameIcon(myImg);
            setTitle("Blood Packet Form");
            notifier = NotifierConnection.getNotifierConnection(null);
            
            setPacketIDCombo(searchPacketIDCombo);
            setBloodGroupCombo(groupCombo);
            setBloodGroupCombo(searchBloodGroupCombo);
            setBloodTypeCombo(bloodTypeCombo);
            setBloodTypeCombo(searchBloodTypeCombo);
            setDonorNicCombo(donorNicCombo);
            setDonorNicCombo(searchDonorNicCombo);
            setCampNameCombo(CampNameCombo);
            setCampNameCombo(searchCampNameCombo);
            
            dateOfCollectionCalendar.setDate(today);
            dateOfExpiryCalendar.setDate(today);
            
            clearSearchFields();
            new SearchableCombo().setSearchableCombo(searchPacketIDCombo, true);
            packIDText.requestFocus(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void resetAdd() {
        packIDText.setText("");
        inhouseRadioBtn.setSelected(true);
        MobileRadioBtn.setSelected(false);
        commentTxt.setText("");
    }

    private void setPacketIDCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = BloodPacketController.getAllBloodPackets();

            while (rst.next()) {
                String packetID = rst.getString("PacketID");
                if (packetID.startsWith("KP")) {
                    combo.addItem(packetID);
                }

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

    private void setDonorNicCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = DonorController.getAllDonors();

            while (rst.next()) {
                String nic = rst.getString("nic");
                if (!nic.endsWith("-")) {
                    combo.addItem(nic);
                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setCampNameCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = CampController.getCamps();

            while (rst.next()) {
                combo.addItem(rst.getString("Place"));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void makeUpdateFieldsActive() {
        searchDonorNicCombo.setEnabled(true);
        searchBloodTypeCombo.setEnabled(true);
        searchBloodGroupCombo.setEnabled(true);
        searchDateOfCollectionCalendar.setEnabled(true);
        searchDateOfExpiryCalendar.setEnabled(true);
        searchInhouseRadioBtn.setEnabled(true);
        searchMobileRadioBtn.setEnabled(true);
        searchCampNameCombo.setEnabled(true);
        searchCommentText.setEnabled(true);
        updateBtn.setEnabled(true);
    }

    private void makeUpdateFieldsInactive() {
        searchDonorNicCombo.setEnabled(false);
        searchBloodTypeCombo.setEnabled(false);
        searchBloodGroupCombo.setEnabled(false);
        searchDateOfCollectionCalendar.setEnabled(false);
        searchDateOfExpiryCalendar.setEnabled(false);
        searchInhouseRadioBtn.setEnabled(false);
        searchMobileRadioBtn.setEnabled(false);
        searchCampNameCombo.setEnabled(false);
        searchCommentText.setEnabled(false);
        updateBtn.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        packIDText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        donorNameText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        groupCombo = new javax.swing.JComboBox();
        addBtn = new javax.swing.JButton();
        dateOfCollectionCalendar = new com.toedter.calendar.JDateChooser();
        dateOfExpiryCalendar = new com.toedter.calendar.JDateChooser();
        CampNameCombo = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        bloodTypeCombo = new javax.swing.JComboBox();
        donorNicCombo = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        inhouseRadioBtn = new javax.swing.JRadioButton();
        MobileRadioBtn = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        commentTxt = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        searchDonorText = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        campNameLabel = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        searchBloodGroupCombo = new javax.swing.JComboBox();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        searchDateOfCollectionCalendar = new com.toedter.calendar.JDateChooser();
        searchDateOfExpiryCalendar = new com.toedter.calendar.JDateChooser();
        searchCampNameCombo = new javax.swing.JComboBox();
        jLabel31 = new javax.swing.JLabel();
        searchBloodTypeCombo = new javax.swing.JComboBox();
        searchDonorNicCombo = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();
        searchInhouseRadioBtn = new javax.swing.JRadioButton();
        searchMobileRadioBtn = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        searchCommentText = new javax.swing.JTextField();
        editBtn = new javax.swing.JButton();
        searchPacketIDCombo = new javax.swing.JComboBox();

        setMinimumSize(new java.awt.Dimension(844, 589));

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Add blood packet details"));

        jLabel3.setText("Packet ID*");

        packIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packIDTextActionPerformed(evt);
            }
        });
        packIDText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                packIDTextKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                packIDTextKeyTyped(evt);
            }
        });

        jLabel4.setText("Donor NIC*");

        jLabel6.setText("Date of expiry*");

        jLabel7.setText("Donor Name");

        donorNameText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        donorNameText.setEnabled(false);
        donorNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donorNameTextActionPerformed(evt);
            }
        });

        jLabel8.setText("Date of collection*");

        jLabel10.setText("Campaign Name");

        jLabel11.setText("Blood Group*");

        groupCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blood Groups" }));
        groupCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupComboActionPerformed(evt);
            }
        });
        groupCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                groupComboKeyReleased(evt);
            }
        });

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        dateOfCollectionCalendar.setForeground(new java.awt.Color(255, 255, 255));
        dateOfCollectionCalendar.setDateFormatString("yyyy-MM-dd");
        dateOfCollectionCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateOfCollectionCalendarPropertyChange(evt);
            }
        });
        dateOfCollectionCalendar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateOfCollectionCalendarKeyReleased(evt);
            }
        });

        dateOfExpiryCalendar.setForeground(new java.awt.Color(255, 255, 255));
        dateOfExpiryCalendar.setDateFormatString("yyyy-MM-dd");
        dateOfExpiryCalendar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dateOfExpiryCalendarKeyReleased(evt);
            }
        });

        CampNameCombo.setEnabled(false);
        CampNameCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampNameComboActionPerformed(evt);
            }
        });
        CampNameCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CampNameComboKeyReleased(evt);
            }
        });

        jLabel22.setText("Blood Type*");

        bloodTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blood types" }));
        bloodTypeCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bloodTypeComboKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bloodTypeComboKeyTyped(evt);
            }
        });

        donorNicCombo.setEditable(true);
        donorNicCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Donor NICs" }));
        donorNicCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donorNicComboActionPerformed(evt);
            }
        });
        donorNicCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                donorNicComboPropertyChange(evt);
            }
        });
        donorNicCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                donorNicComboKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                donorNicComboKeyTyped(evt);
            }
        });

        jLabel9.setText("Inhouse/Mobile");

        buttonGroup1.add(inhouseRadioBtn);
        inhouseRadioBtn.setSelected(true);
        inhouseRadioBtn.setText("Inhouse");
        inhouseRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inhouseRadioBtnActionPerformed(evt);
            }
        });
        inhouseRadioBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inhouseRadioBtnKeyReleased(evt);
            }
        });

        buttonGroup1.add(MobileRadioBtn);
        MobileRadioBtn.setText("Mobile");
        MobileRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MobileRadioBtnActionPerformed(evt);
            }
        });
        MobileRadioBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MobileRadioBtnKeyReleased(evt);
            }
        });

        jLabel21.setText("Comment");

        commentTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentTxtActionPerformed(evt);
            }
        });
        commentTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                commentTxtKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CampNameCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(packIDText))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(dateOfExpiryCalendar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                                .addComponent(dateOfCollectionCalendar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(donorNicCombo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 47, Short.MAX_VALUE))
                                    .addComponent(commentTxt, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(84, 84, 84)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                        .addComponent(bloodTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(inhouseRadioBtn)
                                .addGap(18, 18, 18)
                                .addComponent(MobileRadioBtn))
                            .addComponent(donorNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(packIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bloodTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(donorNicCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(donorNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateOfCollectionCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateOfExpiryCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inhouseRadioBtn)
                            .addComponent(MobileRadioBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(commentTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addBtn)
                .addGap(21, 21, 21))
        );

        jLabel51.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel51.setText("Add Blood Packet");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Add Blood Packet", jPanel11);

        jLabel52.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel52.setText("Search/Update Blood Packet");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Add blood packet details"));

        jLabel5.setText("Packet ID*");

        jLabel25.setText("Donor NIC*");

        jLabel26.setText("Date of expiry*");

        jLabel27.setText("Donor Name");

        searchDonorText.setCaretColor(new java.awt.Color(255, 255, 255));
        searchDonorText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchDonorText.setEnabled(false);
        searchDonorText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDonorTextActionPerformed(evt);
            }
        });

        jLabel28.setText("Date of collection*");

        campNameLabel.setText("Campaign Name");

        jLabel30.setText("Blood Group*");

        searchBloodGroupCombo.setForeground(new java.awt.Color(255, 255, 255));
        searchBloodGroupCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blood Groups" }));
        searchBloodGroupCombo.setEnabled(false);
        searchBloodGroupCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBloodGroupComboActionPerformed(evt);
            }
        });

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        searchDateOfCollectionCalendar.setBackground(new java.awt.Color(255, 255, 255));
        searchDateOfCollectionCalendar.setDateFormatString("yyyy-MM-dd");
        searchDateOfCollectionCalendar.setEnabled(false);

        searchDateOfExpiryCalendar.setBackground(new java.awt.Color(255, 255, 255));
        searchDateOfExpiryCalendar.setDateFormatString("yyyy-MM-dd");
        searchDateOfExpiryCalendar.setEnabled(false);

        searchCampNameCombo.setForeground(new java.awt.Color(255, 255, 255));
        searchCampNameCombo.setEnabled(false);
        searchCampNameCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCampNameComboActionPerformed(evt);
            }
        });

        jLabel31.setText("Blood Type*");

        searchBloodTypeCombo.setForeground(new java.awt.Color(255, 255, 255));
        searchBloodTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blood types" }));
        searchBloodTypeCombo.setEnabled(false);

        searchDonorNicCombo.setEditable(true);
        searchDonorNicCombo.setForeground(new java.awt.Color(255, 255, 255));
        searchDonorNicCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Donor NICs" }));
        searchDonorNicCombo.setEnabled(false);
        searchDonorNicCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDonorNicComboActionPerformed(evt);
            }
        });
        searchDonorNicCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                searchDonorNicComboPropertyChange(evt);
            }
        });

        jLabel32.setText("Inhouse/Mobile");

        buttonGroup2.add(searchInhouseRadioBtn);
        searchInhouseRadioBtn.setText("Inhouse");
        searchInhouseRadioBtn.setEnabled(false);
        searchInhouseRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInhouseRadioBtnActionPerformed(evt);
            }
        });

        buttonGroup2.add(searchMobileRadioBtn);
        searchMobileRadioBtn.setText("Mobile");
        searchMobileRadioBtn.setEnabled(false);
        searchMobileRadioBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMobileRadioBtnMouseClicked(evt);
            }
        });
        searchMobileRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMobileRadioBtnActionPerformed(evt);
            }
        });

        jLabel33.setText("Comment");

        searchCommentText.setCaretColor(new java.awt.Color(255, 255, 255));
        searchCommentText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchCommentText.setEnabled(false);
        searchCommentText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCommentTextActionPerformed(evt);
            }
        });
        searchCommentText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchCommentTextKeyTyped(evt);
            }
        });

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        searchPacketIDCombo.setEditable(true);
        searchPacketIDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPacketIDComboActionPerformed(evt);
            }
        });
        searchPacketIDCombo.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                searchPacketIDComboPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(searchDonorText, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchBloodTypeCombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchBloodGroupCombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(searchInhouseRadioBtn)
                        .addGap(18, 18, 18)
                        .addComponent(searchMobileRadioBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(searchCampNameCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(searchCommentText, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(searchDateOfExpiryCalendar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                                .addComponent(searchDateOfCollectionCalendar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(searchDonorNicCombo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(24, 24, 24))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(searchPacketIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(188, 188, 188))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchDateOfCollectionCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchDateOfExpiryCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchInhouseRadioBtn)
                            .addComponent(searchMobileRadioBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchCampNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(editBtn)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(searchPacketIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(searchBloodTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(searchBloodGroupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(searchDonorNicCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(searchDonorText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addComponent(searchCommentText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBtn)
                    .addComponent(updateBtn))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Search/Update Blood Packet", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 783, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 504, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void donorNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donorNameTextActionPerformed

}//GEN-LAST:event_donorNameTextActionPerformed

    private void packIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packIDTextActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_packIDTextActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed

        try {
            if (packIDText.getText().length() < 1 ) {
                JOptionPane.showMessageDialog(null, "Error: Please fill the required fields!");
            } else {
                String packetID = packIDText.getText();
                String nic = "" + donorNicCombo.getSelectedItem();
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

                String campName = "";
                String campID = null;

                if (MobileRadioBtn.isSelected()) {
                    campName = "" + CampNameCombo.getSelectedItem();
                    campID = CampController.getCampID(campName);
                }

                String comment = commentTxt.getText();

                BloodPacket newPacket = new BloodPacket(packetID, nic, null, sqlDateC, sqlDateE, bloodType, (byte) 0, (byte) 0, (byte) 0, campID, (byte) 0, bloodGroup, null, null, null, comment, null);

                int added = BloodPacketController.addPacket(newPacket);
                if (added == 1) {
                    notifier.notifyUpdateBloodStock();
                    JOptionPane.showMessageDialog(null, "Added Succesfully");
                    resetAdd();
                    setPacketIDCombo(searchPacketIDCombo);
                } else {
                    JOptionPane.showMessageDialog(null, "Error!");
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: The blood packet ID already exists!");
        }


}//GEN-LAST:event_addBtnActionPerformed

    private void CampNameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampNameComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampNameComboActionPerformed

    private void commentTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_commentTxtActionPerformed

    private void searchDonorTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDonorTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchDonorTextActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected Blood Packet?", "Delete Blood Packet", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                String pakcketID = ("" + searchPacketIDCombo.getSelectedItem()).trim();
                int res = BloodPacketController.deletePacket(pakcketID);
                if (res == 1) {
                    notifier.notifyUpdateBloodStock();
                    JOptionPane.showMessageDialog(null, "Blood Packet Deleted Successfully..");
                    setPacketIDCombo(searchPacketIDCombo);
                } else {
                    JOptionPane.showMessageDialog(null, "Error occured while deleting blood packet");

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Nurse.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cannot delete this Blood Packet as it has other records associated with it. Delete those records first to delete this packet from the system.");
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try {
            String packetID = ("" + searchPacketIDCombo.getSelectedItem()).trim();
            String nic = ("" + searchDonorNicCombo.getSelectedItem()).trim();
            String bloodGroup = "" + searchBloodGroupCombo.getSelectedItem();
            String bloodType = "" + searchBloodTypeCombo.getSelectedItem();

            /*Collection date*/
            java.util.Date dateC = searchDateOfCollectionCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateCollection = df.format(dateC);
            java.sql.Date sqlDateC = new java.sql.Date(dateC.getTime());

            /*Expiry date*/
            java.util.Date dateE = searchDateOfExpiryCalendar.getDate();
            String dateExpiry = df.format(dateE);
            java.sql.Date sqlDateE = new java.sql.Date(dateE.getTime());

            String campName = "";
            String campID = null;

            if (searchMobileRadioBtn.isSelected()) {
                campName = "" + searchCampNameCombo.getSelectedItem();
                campID = CampController.getCampID(campName);
            }

            String comment = searchCommentText.getText();

            BloodPacket newPacket = new BloodPacket(packetID, nic, null, sqlDateC, sqlDateE, bloodType, (byte) 0, (byte) 0, (byte) 0, campID, (byte) 0, bloodGroup, null, null, null, comment, null);

            int updated = BloodPacketController.updatePacket(newPacket);
            if (updated == 1) {
                notifier.notifyUpdateBloodStock();
                JOptionPane.showMessageDialog(null, "Updated successfully");
                setPacketIDCombo(searchPacketIDCombo);
            } else {
                JOptionPane.showMessageDialog(null, "Error occured while updating blood packet");
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error occured while updating blood packet");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error occured while updating blood packet");
        }
    }//GEN-LAST:event_updateBtnActionPerformed

    private void searchCampNameComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCampNameComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCampNameComboActionPerformed

    private void searchCommentTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCommentTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCommentTextActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        makeUpdateFieldsActive();
    }//GEN-LAST:event_editBtnActionPerformed

    private void searchBloodGroupComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBloodGroupComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBloodGroupComboActionPerformed

    private void donorNicComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donorNicComboActionPerformed

        String donorName;
        try {
            System.out.println("" + donorNicCombo.getSelectedItem());
            donorName = DonorController.getDonorName("" + donorNicCombo.getSelectedItem());
            
            if (donorName != null) {
                donorName = donorName.trim();
                donorNameText.setText(donorName);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_donorNicComboActionPerformed

    private void searchDonorNicComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDonorNicComboActionPerformed
        String donorName;
        try {
            System.out.println("" + searchDonorNicCombo.getSelectedItem());
            donorName = DonorController.getDonorName("" + searchDonorNicCombo.getSelectedItem());
            
            if (donorName != null) {
                donorName = donorName.trim();
                searchDonorText.setText(donorName);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchDonorNicComboActionPerformed

    private void donorNicComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_donorNicComboPropertyChange

    }//GEN-LAST:event_donorNicComboPropertyChange

    private void searchMobileRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMobileRadioBtnActionPerformed
        searchCampNameCombo.setVisible(true);
        campNameLabel.setVisible(true);
    }//GEN-LAST:event_searchMobileRadioBtnActionPerformed

    private void searchInhouseRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInhouseRadioBtnActionPerformed
        searchCampNameCombo.setVisible(false);
        campNameLabel.setVisible(false);
    }//GEN-LAST:event_searchInhouseRadioBtnActionPerformed

    private void MobileRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MobileRadioBtnActionPerformed
        CampNameCombo.setEnabled(true);
    }//GEN-LAST:event_MobileRadioBtnActionPerformed

    private void searchMobileRadioBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMobileRadioBtnMouseClicked

    }//GEN-LAST:event_searchMobileRadioBtnMouseClicked

    private void inhouseRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inhouseRadioBtnActionPerformed
        CampNameCombo.setEnabled(false);
    }//GEN-LAST:event_inhouseRadioBtnActionPerformed

    private void searchPacketIDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPacketIDComboActionPerformed
        makeUpdateFieldsInactive();
        ResultSet rst;
        try {
            clearSearchFields();
            String packetID = ("" + searchPacketIDCombo.getSelectedItem()).trim();
            rst = BloodPacketController.getBloodPackets(packetID);

            while (rst.next()) {
                searchDonorNicCombo.setSelectedItem(rst.getString("nic"));
                searchDonorText.setText(rst.getString("name"));
                searchBloodTypeCombo.setSelectedItem(rst.getString("BloodType"));
                searchBloodGroupCombo.setSelectedItem(rst.getString("BloodGroup"));

                java.sql.Date sqlDateOfDonation = rst.getDate("DateOfDonation");
                java.util.Date utilDateOfDonation = new java.util.Date(sqlDateOfDonation.getTime());
                searchDateOfCollectionCalendar.setDate(utilDateOfDonation);

                java.sql.Date sqlDateOfExpiry = rst.getDate("DateOfExpiry");
                java.util.Date utilDateOfExpiry = new java.util.Date(sqlDateOfExpiry.getTime());
                searchDateOfExpiryCalendar.setDate(utilDateOfExpiry);

                if (rst.getString("CampID") == null) {
                    searchInhouseRadioBtn.setSelected(true);
                    searchMobileRadioBtn.setSelected(false);
                    searchCampNameCombo.setVisible(false);
                    campNameLabel.setVisible(false);
                } else {
                    searchInhouseRadioBtn.setSelected(false);
                    searchMobileRadioBtn.setSelected(true);
                    searchCampNameCombo.setVisible(true);
                    campNameLabel.setVisible(true);
                    String campID = rst.getString("CampID");
                    String campName = CampController.getCampName(campID);
                    if (campName != null) {
                        searchCampNameCombo.setSelectedItem(campName);
                    }

                }

                searchCommentText.setText(rst.getString("Comment"));

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchPacketIDComboActionPerformed

    private void searchPacketIDComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_searchPacketIDComboPropertyChange
        makeUpdateFieldsInactive();
        ResultSet rst;
        try {
            clearSearchFields();
            rst = BloodPacketController.getBloodPackets("" + searchPacketIDCombo.getSelectedItem());

            while (rst.next()) {
                searchDonorNicCombo.setSelectedItem(rst.getString("nic"));
                searchDonorText.setText(rst.getString("name"));
                searchBloodTypeCombo.setSelectedItem(rst.getString("BloodType"));
                searchBloodGroupCombo.setSelectedItem(rst.getString("BloodGroup"));

                java.sql.Date sqlDateOfDonation = rst.getDate("DateOfDonation");
                java.util.Date utilDateOfDonation = new java.util.Date(sqlDateOfDonation.getTime());
                searchDateOfCollectionCalendar.setDate(utilDateOfDonation);

                java.sql.Date sqlDateOfExpiry = rst.getDate("DateOfExpiry");
                java.util.Date utilDateOfExpiry = new java.util.Date(sqlDateOfExpiry.getTime());
                searchDateOfExpiryCalendar.setDate(utilDateOfExpiry);

                if (rst.getString("CampID") == null) {
                    searchInhouseRadioBtn.setSelected(true);
                    searchMobileRadioBtn.setSelected(false);
                    searchCampNameCombo.setVisible(false);
                    campNameLabel.setVisible(false);
                } else {
                    searchInhouseRadioBtn.setSelected(false);
                    searchMobileRadioBtn.setSelected(true);
                    searchCampNameCombo.setVisible(true);
                    campNameLabel.setVisible(true);
                    String campID = rst.getString("CampID");
                    String campName = CampController.getCampName(campID);
                    if (campName != null) {
                        searchCampNameCombo.setSelectedItem(campName);
                    }
                }

                searchCommentText.setText(rst.getString("Comment"));

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchPacketIDComboPropertyChange

    private void searchDonorNicComboPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_searchDonorNicComboPropertyChange
        String donorName;
        try {
            System.out.println("" + searchDonorNicCombo.getSelectedItem());
            donorName = DonorController.getDonorName("" + searchDonorNicCombo.getSelectedItem());

            if (donorName != null) {
                searchDonorText.setText(donorName);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchDonorNicComboPropertyChange

    private void packIDTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_packIDTextKeyReleased

    }//GEN-LAST:event_packIDTextKeyReleased

    private void bloodTypeComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bloodTypeComboKeyReleased

    }//GEN-LAST:event_bloodTypeComboKeyReleased

    private void donorNicComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_donorNicComboKeyReleased

    }//GEN-LAST:event_donorNicComboKeyReleased

    private void groupComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_groupComboKeyReleased
        char c = evt.getKeyChar();
        if (c == java.awt.event.KeyEvent.VK_ENTER) {
            dateOfCollectionCalendar.requestFocus(true);
        }
    }//GEN-LAST:event_groupComboKeyReleased

    private void dateOfCollectionCalendarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateOfCollectionCalendarKeyReleased

    }//GEN-LAST:event_dateOfCollectionCalendarKeyReleased

    private void dateOfExpiryCalendarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dateOfExpiryCalendarKeyReleased

    }//GEN-LAST:event_dateOfExpiryCalendarKeyReleased

    private void inhouseRadioBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inhouseRadioBtnKeyReleased

    }//GEN-LAST:event_inhouseRadioBtnKeyReleased

    private void MobileRadioBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MobileRadioBtnKeyReleased

    }//GEN-LAST:event_MobileRadioBtnKeyReleased

    private void CampNameComboKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampNameComboKeyReleased

    }//GEN-LAST:event_CampNameComboKeyReleased

    private void packIDTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_packIDTextKeyTyped
        char c = evt.getKeyChar();
        if (packIDText.getText().length() < 20) {
            if (c == java.awt.event.KeyEvent.VK_ENTER) {
                donorNicCombo.requestFocus(true);
            }
        } else {
            evt.consume();
        }
    }//GEN-LAST:event_packIDTextKeyTyped

    private void groupComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupComboActionPerformed


    }//GEN-LAST:event_groupComboActionPerformed

    private void donorNicComboKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_donorNicComboKeyTyped
        char c = evt.getKeyChar();
        if (c == java.awt.event.KeyEvent.VK_ENTER) {
            bloodTypeCombo.requestFocus(true);
        }
    }//GEN-LAST:event_donorNicComboKeyTyped

    private void bloodTypeComboKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bloodTypeComboKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_bloodTypeComboKeyTyped

    private void dateOfCollectionCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateOfCollectionCalendarPropertyChange

    }//GEN-LAST:event_dateOfCollectionCalendarPropertyChange

    private void commentTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_commentTxtKeyTyped
        if (commentTxt.getText().length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_commentTxtKeyTyped

    private void searchCommentTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchCommentTextKeyTyped
        if (searchCommentText.getText().length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_searchCommentTextKeyTyped

    private void clearSearchFields() {
        searchDonorNicCombo.setSelectedItem(searchDonorNicCombo.getItemAt(0));
        searchDonorText.setText("");
        searchBloodTypeCombo.setSelectedItem(searchBloodTypeCombo.getItemAt(0));
        searchBloodGroupCombo.setSelectedItem(searchBloodGroupCombo.getItemAt(0));

        searchDateOfCollectionCalendar.setDate(today);
        searchDateOfExpiryCalendar.setDate(today);

        searchInhouseRadioBtn.setSelected(false);
        searchMobileRadioBtn.setSelected(false);
        searchCampNameCombo.setSelectedItem(searchCampNameCombo.getItemAt(0));

        searchCommentText.setText("");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CampNameCombo;
    private javax.swing.JRadioButton MobileRadioBtn;
    private javax.swing.JButton addBtn;
    private javax.swing.JComboBox bloodTypeCombo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel campNameLabel;
    private javax.swing.JTextField commentTxt;
    private com.toedter.calendar.JDateChooser dateOfCollectionCalendar;
    private com.toedter.calendar.JDateChooser dateOfExpiryCalendar;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField donorNameText;
    private javax.swing.JComboBox donorNicCombo;
    private javax.swing.JButton editBtn;
    private javax.swing.JComboBox groupCombo;
    private javax.swing.JRadioButton inhouseRadioBtn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField packIDText;
    private javax.swing.JComboBox searchBloodGroupCombo;
    private javax.swing.JComboBox searchBloodTypeCombo;
    private javax.swing.JComboBox searchCampNameCombo;
    private javax.swing.JTextField searchCommentText;
    private com.toedter.calendar.JDateChooser searchDateOfCollectionCalendar;
    private com.toedter.calendar.JDateChooser searchDateOfExpiryCalendar;
    private javax.swing.JComboBox searchDonorNicCombo;
    private javax.swing.JTextField searchDonorText;
    private javax.swing.JRadioButton searchInhouseRadioBtn;
    private javax.swing.JRadioButton searchMobileRadioBtn;
    private javax.swing.JComboBox searchPacketIDCombo;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables

}
