/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BloodReturn.java
 *
 * Created on Sep 7, 2014, 6:20:33 PM
 */
package view.blood_stock_management;

import controller.common.IDGenerator;
import controller.common.TableResizer;
import controller.blood_stock_management.BloodPacketController;
import controller.issueing.BulkIssueController;
import controller.issueing.IssueController;
import controller.blood_stock_management.ReturnedLogController;
import connection.NotifierConnection;
import controller.blood_stock_management.BloodPacketController;
import controller.common.SearchableCombo;
import controller.blood_stock_management.BloodStockUpdateNotifier;
import controller.blood_stock_management.BloodStockUpdateNotifier;
import controller.issueing.BulkIssueController;
import controller.common.IDGenerator;
import controller.issueing.IssueController;
import controller.blood_stock_management.ReturnedLogController;
import controller.common.SearchableCombo;
import controller.common.TableResizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
import javax.swing.table.DefaultTableModel;
import model.ReturnedLog;

/**
 *
 * @author Anuradha
 */
public class BloodReturn extends javax.swing.JInternalFrame {

    ResultSet rst = null;

    String[] title = {"Unit No", "Blood group", "Blood Type", "IssuedTo", "Issue mode", "IssuedID", "Issued Date", "Issued Time", "Reason for return"};
    DefaultTableModel dtm = new DefaultTableModel(title, 0);
    String[] searchTitle = {"ID", "Unit No", "Blood group", "Blood Type", "IssuedTo", "Issue mode", "IssuedID", "Issued Date", "Issued Time", "Reason for return"};
    DefaultTableModel searchDtm = new DefaultTableModel(searchTitle, 0);

    /**
     * Creates new form BloodReturn
     */
    Calendar currenttime = Calendar.getInstance();
    java.util.Date today = new java.util.Date((currenttime.getTime()).getTime());
    
    BloodStockUpdateNotifier notifier = null;

    public BloodReturn() {

        try {
            initComponents();
            
            FileInputStream imgStream = null;
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            imgStream = new FileInputStream(imgfile);
            BufferedImage bi = ImageIO.read(imgStream);
            ImageIcon myImg = new ImageIcon(bi);
            this.setFrameIcon(myImg);
            setTitle("Blood Return");
            
            initPacketIDCombo(packetIDCombo);
            searchReturnDateCaendar.setDate(today);
            
            notifier = NotifierConnection.getNotifierConnection(null);
            new SearchableCombo().setSearchableCombo(packetIDCombo, true);
        } catch (IOException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearUpdateFields() {
        searchReturnIDTxt.setText("");
        searchPacketIDText.setText("");
        searchBloodGroupText.setText("");
        searchBloodTypeText.setText("");
        searchIssuedToText.setText("");

        seachpatientIssueCheckBox.setSelected(false);
        searchBulkIssueTextBox.setSelected(false);

        searchIssueIDText.setText("");
        searchIssuedDateText.setText("");
        searchIssuedTimeText.setText("");
        searchReasonText.setText("");

        java.util.Date date = searchReturnDateCaendar.getDate();
        searchReturnedDateCalendar.setDate(date);
    }

    private void setSearchTableData() {

        try {
            searchDtm = new DefaultTableModel(searchTitle, 0);
            searchReturnTable.setModel(searchDtm);

            /*Return date*/
            java.util.Date utilReturnDate = searchReturnDateCaendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.format(utilReturnDate);
            java.sql.Date sqlReturnDate = new java.sql.Date(utilReturnDate.getTime());

            ResultSet rst = ReturnedLogController.getReturnedLogbyDate(sqlReturnDate);

            while (rst.next()) {
                //"ID", "Unit No", "Blood group", "Blood Type", "IssuedTo", "Issue mode", "IssuedID", "Issued Date", "Issued Time", "Reason for return"
                String[] row = {"", "", "", "", "", "", "", "", "", ""};
                row[0] = rst.getString("ReturnedID");
                row[1] = rst.getString("PacketID");
                row[2] = rst.getString("BloodGroup");
                row[3] = rst.getString("BloodType");
                row[9] = rst.getString("Reason");

                String bulkIssueID = rst.getString("BulkIssueID");
                String patientIssueID = rst.getString("PatientIssueID");

                try {
                    //Bulk Issue
                    bulkIssueID.equalsIgnoreCase("");
                    ResultSet rstIssue = BulkIssueController.getBulkIssueDetails(rst.getString("BulkIssueID"));
                    while (rstIssue.next()) {
                        row[4] = rstIssue.getString("Requester");
                        row[5] = "Bulk Issue";
                        row[6] = rstIssue.getString("IssueID");
                        row[7] = rstIssue.getString("Date");
                        row[8] = rstIssue.getString("Time");
                    }

                } catch (NullPointerException npe) {
                    //Patient issue
                    ResultSet rstIssue = IssueController.getIssuedDetails(rst.getString("PatientIssueID"));
                    while (rstIssue.next()) {
                        row[4] = rstIssue.getString("Hospital");
                        row[5] = "Patient Issue";
                        row[6] = rstIssue.getString("IssueID");
                        row[7] = rstIssue.getString("Date");
                        row[8] = rstIssue.getString("Time");
                    }

                }

                searchDtm.addRow(row);
                TableResizer.resizeColumnWidth(searchReturnTable);
            }
            int count = searchDtm.getRowCount();
            totalText.setText("" + count);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clearAddFields() {
        issueIDText.setText("");
        issuedToText.setText("");
        issueDateText.setText("");
        timeText.setText("");
        bloodGroupTxt.setText("");
        bloodTypeTxt.setText("");
        reasonTxt.setText("");
        patientIssueCheckBox.setSelected(false);
        bulkIssueCheckBox.setSelected(false);
    }

    private void clear() {
        reasonTxt.setText("");
    }

    private void initPacketIDCombo(JComboBox combo) {
        combo.removeAllItems();
        try {
            rst = BloodPacketController.getAllIssuedBloodPackets();
            while (rst.next()) {
                combo.addItem(rst.getString("packetID"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        packetIDCombo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        bloodGroupTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        bloodTypeTxt = new javax.swing.JTextField();
        returnedDateCalendar = new com.toedter.calendar.JCalendar();
        jLabel6 = new javax.swing.JLabel();
        reasonTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        issueDateText = new javax.swing.JTextField();
        issueIDText = new javax.swing.JTextField();
        addToListBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        timeText = new javax.swing.JTextField();
        patientIssueCheckBox = new javax.swing.JCheckBox();
        bulkIssueCheckBox = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        issuedToText = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        returnedTable = new javax.swing.JTable();
        markAsReturnedBtn = new javax.swing.JButton();
        removeResultBtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        searchReturnTable = new javax.swing.JTable();
        editReturnBtn = new javax.swing.JButton();
        unmarkReturnBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        totalText = new javax.swing.JTextField();
        searchReturnDateCaendar = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        searchBloodGroupText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        searchBloodTypeText = new javax.swing.JTextField();
        searchReturnedDateCalendar = new com.toedter.calendar.JCalendar();
        jLabel11 = new javax.swing.JLabel();
        searchReasonText = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        searchIssuedDateText = new javax.swing.JTextField();
        searchIssueIDText = new javax.swing.JTextField();
        updateReturnBtn = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        searchIssuedTimeText = new javax.swing.JTextField();
        seachpatientIssueCheckBox = new javax.swing.JCheckBox();
        searchBulkIssueTextBox = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        searchIssuedToText = new javax.swing.JTextField();
        searchPacketIDText = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        searchReturnIDTxt = new javax.swing.JTextField();

        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mark returned packet"));

        jLabel1.setText("Packet ID*");

        jLabel3.setText("Issue ID");

        packetIDCombo.setEditable(true);
        packetIDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packetIDComboActionPerformed(evt);
            }
        });

        jLabel4.setText("Blood Group");

        bloodGroupTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodGroupTxt.setEnabled(false);
        bloodGroupTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodGroupTxtActionPerformed(evt);
            }
        });

        jLabel5.setText("Blood Type");

        bloodTypeTxt.setForeground(new java.awt.Color(255, 255, 255));
        bloodTypeTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodTypeTxt.setEnabled(false);
        bloodTypeTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodTypeTxtActionPerformed(evt);
            }
        });

        returnedDateCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                returnedDateCalendarPropertyChange(evt);
            }
        });

        jLabel6.setText("Reason");

        reasonTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        reasonTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reasonTxtActionPerformed(evt);
            }
        });
        reasonTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reasonTxtKeyTyped(evt);
            }
        });

        jLabel12.setText("Issued Date");

        issueDateText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        issueDateText.setEnabled(false);
        issueDateText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueDateTextActionPerformed(evt);
            }
        });

        issueIDText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        issueIDText.setEnabled(false);
        issueIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueIDTextActionPerformed(evt);
            }
        });

        addToListBtn.setText("Add to list");
        addToListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToListBtnActionPerformed(evt);
            }
        });

        jLabel14.setText("Issued Time");

        timeText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        timeText.setEnabled(false);
        timeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeTextActionPerformed(evt);
            }
        });

        patientIssueCheckBox.setText("Patient Issue");
        patientIssueCheckBox.setEnabled(false);

        bulkIssueCheckBox.setText("Bulk Issue");
        bulkIssueCheckBox.setEnabled(false);

        jLabel15.setText("Issued To");

        issuedToText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        issuedToText.setEnabled(false);
        issuedToText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issuedToTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(4, 4, 4)))
                            .addGap(32, 32, 32)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(reasonTxt)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bloodGroupTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bloodTypeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(packetIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(addToListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(patientIssueCheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(bulkIssueCheckBox))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(timeText))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(issueDateText))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(issuedToText))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(issueIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(returnedDateCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(returnedDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(packetIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(issueIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bloodGroupTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(issuedToText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bloodTypeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(reasonTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(issueDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(timeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addToListBtn)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(patientIssueCheckBox)
                                .addComponent(bulkIssueCheckBox)))))
                .addGap(42, 42, 42))
        );

        jLabel51.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel51.setText("Add Returned Packet");

        returnedTable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(0, 102, 255)));
        returnedTable.setModel(dtm);
        jScrollPane1.setViewportView(returnedTable);

        markAsReturnedBtn.setText("Mark as returned");
        markAsReturnedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markAsReturnedBtnActionPerformed(evt);
            }
        });

        removeResultBtn.setText("Remove from list");
        removeResultBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeResultBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 371, Short.MAX_VALUE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(332, 332, 332))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removeResultBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(markAsReturnedBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(markAsReturnedBtn)
                    .addComponent(removeResultBtn))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Returned Packets", jPanel11);

        jLabel13.setText("Returned Date");

        jLabel53.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel53.setText("Search/Update Returned Log");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Blood return details"));

        searchReturnTable.setModel(searchDtm);
        jScrollPane2.setViewportView(searchReturnTable);

        editReturnBtn.setText("Edit Record");
        editReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editReturnBtnActionPerformed(evt);
            }
        });

        unmarkReturnBtn.setText("Unmark Blood Return");
        unmarkReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unmarkReturnBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Total");

        totalText.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(totalText)
                        .addGap(31, 31, 31)
                        .addComponent(unmarkReturnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(editReturnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editReturnBtn)
                    .addComponent(unmarkReturnBtn)
                    .addComponent(jLabel2)
                    .addComponent(totalText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(243, 243, 243))
        );

        searchReturnDateCaendar.setDateFormatString("yyyy-MM-dd");
        searchReturnDateCaendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                searchReturnDateCaendarPropertyChange(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Mark returned packet"));

        jLabel7.setText("Packet ID");

        jLabel8.setText("Issue ID");

        jLabel9.setText("Blood Group");

        searchBloodGroupText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchBloodGroupText.setEnabled(false);
        searchBloodGroupText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBloodGroupTextActionPerformed(evt);
            }
        });

        jLabel10.setText("Blood Type");

        searchBloodTypeText.setForeground(new java.awt.Color(255, 255, 255));
        searchBloodTypeText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchBloodTypeText.setEnabled(false);
        searchBloodTypeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBloodTypeTextActionPerformed(evt);
            }
        });

        searchReturnedDateCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                searchReturnedDateCalendarPropertyChange(evt);
            }
        });

        jLabel11.setText("Reason");

        searchReasonText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchReasonText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchReasonTextActionPerformed(evt);
            }
        });
        searchReasonText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchReasonTextKeyTyped(evt);
            }
        });

        jLabel16.setText("Issued Date");

        searchIssuedDateText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchIssuedDateText.setEnabled(false);
        searchIssuedDateText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIssuedDateTextActionPerformed(evt);
            }
        });

        searchIssueIDText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchIssueIDText.setEnabled(false);
        searchIssueIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIssueIDTextActionPerformed(evt);
            }
        });

        updateReturnBtn.setText("Update Record");
        updateReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateReturnBtnActionPerformed(evt);
            }
        });

        jLabel17.setText("Issued Time");

        searchIssuedTimeText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchIssuedTimeText.setEnabled(false);
        searchIssuedTimeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIssuedTimeTextActionPerformed(evt);
            }
        });

        seachpatientIssueCheckBox.setText("Patient Issue");
        seachpatientIssueCheckBox.setEnabled(false);

        searchBulkIssueTextBox.setText("Bulk Issue");
        searchBulkIssueTextBox.setEnabled(false);

        jLabel18.setText("Issued To");

        searchIssuedToText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchIssuedToText.setEnabled(false);
        searchIssuedToText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchIssuedToTextActionPerformed(evt);
            }
        });

        searchPacketIDText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchPacketIDText.setEnabled(false);
        searchPacketIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPacketIDTextActionPerformed(evt);
            }
        });

        jLabel19.setText("Return ID");

        searchReturnIDTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchReturnIDTxt.setEnabled(false);
        searchReturnIDTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchReturnIDTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchReturnIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(searchReasonText)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(searchBloodGroupText, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchBloodTypeText, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchPacketIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(seachpatientIssueCheckBox)
                        .addGap(18, 18, 18)
                        .addComponent(searchBulkIssueTextBox))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchIssuedTimeText))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchIssuedDateText))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchIssuedToText))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchIssueIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateReturnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchReturnedDateCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchReturnIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchIssueIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchIssuedToText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchIssuedDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchIssuedTimeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(seachpatientIssueCheckBox)
                                    .addComponent(searchBulkIssueTextBox)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchPacketIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchBloodGroupText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchBloodTypeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchReasonText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchReturnedDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateReturnBtn)))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 344, Short.MAX_VALUE)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(278, 278, 278))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchReturnDateCaendar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchReturnDateCaendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Search Returned Log by date", jPanel3);

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

    private void packetIDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packetIDComboActionPerformed
        if (packetIDCombo.getSelectedItem() == null) {
            addToListBtn.setEnabled(false);
            clearAddFields();
        } else {
            addToListBtn.setEnabled(true);
        }
        ResultSet rst;
        try {
            String packetID = ("" + packetIDCombo.getSelectedItem()).trim();
            rst = BloodPacketController.getIssuedPacketDetails(packetID);
            while (rst.next()) {
                bloodGroupTxt.setText(rst.getString("bloodGroup"));
                bloodTypeTxt.setText(rst.getString("bloodType"));
                String patientIssueId = rst.getString("PatientIssueID");
                String bulkIssueId = rst.getString("BulkIssueID");
                if (bulkIssueId != null) {
                    issueIDText.setText(bulkIssueId);
                    patientIssueCheckBox.setSelected(false);
                    bulkIssueCheckBox.setSelected(true);
                    ResultSet bulkIssue = BulkIssueController.getBulkIssueDetails(bulkIssueId);
                    while (bulkIssue.next()) {
                        issuedToText.setText(rst.getString("Requester"));
                        issueDateText.setText("" + rst.getDate("Date"));
                        timeText.setText("" + rst.getTime("Time"));
                    }
                } else {
                    issueIDText.setText(patientIssueId);
                    patientIssueCheckBox.setSelected(true);
                    bulkIssueCheckBox.setSelected(false);
                    String issuedTo = IssueController.getIssuedTo(patientIssueId);
                    if (issuedTo != null) {
                        issuedToText.setText(issuedTo);
                    }
                    issueDateText.setText("" + rst.getDate("Date"));
                    timeText.setText("" + rst.getTime("Time"));
                }

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        }


}//GEN-LAST:event_packetIDComboActionPerformed

    private void bloodGroupTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodGroupTxtActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_bloodGroupTxtActionPerformed

    private void markAsReturnedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markAsReturnedBtnActionPerformed
        try {
            for (int i = 0; i < dtm.getRowCount(); i++) {
                String returnID = "";
                ResultSet rst;

                rst = ReturnedLogController.getResultIDs();
                rst.last();
                String curID = null;
                try {
                    curID = rst.getString("returnedID");
                    try {
                        returnID = IDGenerator.generateNextID(curID);
                    } catch (Exception ex) {
                        Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (SQLException e) {
                    returnID = "RET0000001";
                }

                java.util.Date returnedDate = returnedDateCalendar.getDate();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dateReturned = df.format(returnedDate);
                java.sql.Date sqlRetunedDate = new java.sql.Date(returnedDate.getTime());

                //{packetID, bloodGroup, bloodType, issuedTo,issueMode, issueID, issueDate, issueTime, reason};
                String packetID = "" + dtm.getValueAt(i, 0);
                String reason = "" + dtm.getValueAt(i, 8);

                String patientIssueID = null;
                String bulkIssueID = null;

                ReturnedLog log = new ReturnedLog(returnID, sqlRetunedDate, packetID, reason, patientIssueID, bulkIssueID);

                if (("" + dtm.getValueAt(i, 4)).equalsIgnoreCase("To patient")) {
                    patientIssueID = "" + dtm.getValueAt(i, 5);
                    log.setPatientIssueID(patientIssueID);
                } else {
                    bulkIssueID = "" + dtm.getValueAt(i, 5);
                    log.setBulkIssueID(bulkIssueID);
                }

                int res1 = 0;
                int res2 = 0;

                res1 = ReturnedLogController.addReturnedLog(log);
                if (res1 == 1) {
                    res2 = BloodPacketController.markReturnedPacket(packetID, returnID);
                    if (res2 == 1) {
                        notifier.notifyUpdateBloodStock();
                        JOptionPane.showMessageDialog(null, "Added Succesfully");
                        initPacketIDCombo(packetIDCombo);
                        dtm = new DefaultTableModel(title, 0);
                        returnedTable.setModel(dtm);
                        setSearchTableData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error!");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error!");

                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_markAsReturnedBtnActionPerformed

    private void returnedDateCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_returnedDateCalendarPropertyChange
        //setRecievedTableItems();
}//GEN-LAST:event_returnedDateCalendarPropertyChange

    private void reasonTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reasonTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reasonTxtActionPerformed

    private void issueDateTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueDateTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_issueDateTextActionPerformed

    private void issueIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueIDTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_issueIDTextActionPerformed

    private void bloodTypeTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodTypeTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bloodTypeTxtActionPerformed

    private void timeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeTextActionPerformed

    private void addToListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToListBtnActionPerformed
        String packetID = "" + packetIDCombo.getSelectedItem();
        String bloodGroup = bloodGroupTxt.getText();
        String bloodType = bloodTypeTxt.getText();
        String issueID = issueIDText.getText();
        String issuedTo = issuedToText.getText();
        String issueMode;
        if (patientIssueCheckBox.isSelected()) {
            issueMode = "To patient";
        } else {
            issueMode = "Bulk";
        }
        String issueDate = issueDateText.getText();
        String issueTime = timeText.getText();
        String reason = reasonTxt.getText();

        String[] row = {packetID, bloodGroup, bloodType, issuedTo, issueMode, issueID, issueDate, issueTime, reason};
        dtm.addRow(row);
        TableResizer.resizeColumnWidth(returnedTable);
        packetIDCombo.removeItem(packetIDCombo.getSelectedItem());
        clear();
    }//GEN-LAST:event_addToListBtnActionPerformed

    private void removeResultBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeResultBtnActionPerformed
        int row = returnedTable.getSelectedRow();

        if (row >= 0) {
            packetIDCombo.addItem(dtm.getValueAt(row, 0));
            dtm.removeRow(row);

        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_removeResultBtnActionPerformed

    private void issuedToTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issuedToTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_issuedToTextActionPerformed

    private void searchBloodGroupTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBloodGroupTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBloodGroupTextActionPerformed

    private void searchBloodTypeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBloodTypeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBloodTypeTextActionPerformed

    private void searchReturnedDateCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_searchReturnedDateCalendarPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_searchReturnedDateCalendarPropertyChange

    private void searchReasonTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchReasonTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchReasonTextActionPerformed

    private void searchIssuedDateTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchIssuedDateTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchIssuedDateTextActionPerformed

    private void searchIssueIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchIssueIDTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchIssueIDTextActionPerformed

    private void updateReturnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateReturnBtnActionPerformed
        String returnID = searchReturnIDTxt.getText();

        java.util.Date returnedDate = searchReturnedDateCalendar.getDate();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateReturned = df.format(returnedDate);
        java.sql.Date sqlRetunedDate = new java.sql.Date(returnedDate.getTime());

        //{packetID, bloodGroup, bloodType, issuedTo,issueMode, issueID, issueDate, issueTime, reason};
        String packetID = searchPacketIDText.getText();
        String reason = searchReasonText.getText();

        String patientIssueID = null;
        String bulkIssueID = null;

        ReturnedLog log = new ReturnedLog(returnID, sqlRetunedDate, packetID, reason, patientIssueID, bulkIssueID);

        int res = 0;

        try {
            res = ReturnedLogController.updateReturnedLog(log);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (res == 1) {
            notifier.notifyUpdateBloodStock();
            JOptionPane.showMessageDialog(null, "Updated successfully!");
            setSearchTableData();
            clearUpdateFields();
        } else {
            JOptionPane.showMessageDialog(null, "Error occured while updating blood packet");
        }
    }//GEN-LAST:event_updateReturnBtnActionPerformed

    private void searchIssuedTimeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchIssuedTimeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchIssuedTimeTextActionPerformed

    private void searchIssuedToTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchIssuedToTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchIssuedToTextActionPerformed

    private void searchPacketIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPacketIDTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPacketIDTextActionPerformed

    private void searchReturnIDTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchReturnIDTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchReturnIDTxtActionPerformed

    private void searchReturnDateCaendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_searchReturnDateCaendarPropertyChange
        setSearchTableData();
    }//GEN-LAST:event_searchReturnDateCaendarPropertyChange

    private void editReturnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editReturnBtnActionPerformed
        int row = searchReturnTable.getSelectedRow();

        if (row >= 0) {
            //{"ID",packetID, bloodGroup, bloodType, issuedTo,issueMode, issueID, issueDate, issueTime, reason};
            searchReturnIDTxt.setText("" + searchDtm.getValueAt(row, 0));
            searchPacketIDText.setText("" + searchDtm.getValueAt(row, 1));
            searchBloodGroupText.setText("" + searchDtm.getValueAt(row, 2));
            searchBloodTypeText.setText("" + searchDtm.getValueAt(row, 3));
            searchIssuedToText.setText("" + searchDtm.getValueAt(row, 4));
            ////
            if (("" + searchDtm.getValueAt(row, 5)).equalsIgnoreCase("Patient Issue")) {
                seachpatientIssueCheckBox.setSelected(true);
                searchBulkIssueTextBox.setSelected(false);
            } else {
                seachpatientIssueCheckBox.setSelected(false);
                searchBulkIssueTextBox.setSelected(true);
            }

            searchIssueIDText.setText("" + searchDtm.getValueAt(row, 6));
            searchIssuedDateText.setText("" + searchDtm.getValueAt(row, 7));
            searchIssuedTimeText.setText("" + searchDtm.getValueAt(row, 8));
            searchReasonText.setText("" + searchDtm.getValueAt(row, 9));

            java.util.Date date = searchReturnDateCaendar.getDate();
            searchReturnedDateCalendar.setDate(date);

        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_editReturnBtnActionPerformed

    private void unmarkReturnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unmarkReturnBtnActionPerformed

        int row = searchReturnTable.getSelectedRow();

        if (row >= 0) {
            try {
                //{"ID",packetID, bloodGroup, bloodType, issuedTo,issueMode, issueID, issueDate, issueTime, reason};
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Unmark returned blood packet", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    String returnID = "" + searchDtm.getValueAt(row, 0);
                    String packetID = "" + searchDtm.getValueAt(row, 1);
                    String issueID = "" + searchDtm.getValueAt(row, 6);
                    String issueMode = "" + searchDtm.getValueAt(row, 5);

                    boolean isPatientIssue;
                    if (issueMode.equalsIgnoreCase("Patient Issue")) {
                        isPatientIssue = true;
                    } else {
                        isPatientIssue = false;
                    }

                    int res1 = BloodPacketController.unmarkReturnedBloodPacket(packetID, isPatientIssue, issueID);

                    if (res1 == 1) {

                        int res2 = ReturnedLogController.deleteReturnedLog(returnID);
                        if (res2 == 1) {
                            notifier.notifyUpdateBloodStock();
                            JOptionPane.showMessageDialog(null, "Deleted record successfully!");
                            initPacketIDCombo(packetIDCombo);
                            setSearchTableData();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error occured while deleting returned log record");

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Error occured while updating blood packet");
                    }

                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_unmarkReturnBtnActionPerformed

    private void reasonTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reasonTxtKeyTyped
        if (reasonTxt.getText().length() >= 500) {
            evt.consume();
        }
    }//GEN-LAST:event_reasonTxtKeyTyped

    private void searchReasonTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchReasonTextKeyTyped
        if (searchReasonText.getText().length() >= 500) {
            evt.consume();
        }
    }//GEN-LAST:event_searchReasonTextKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToListBtn;
    private javax.swing.JTextField bloodGroupTxt;
    private javax.swing.JTextField bloodTypeTxt;
    private javax.swing.JCheckBox bulkIssueCheckBox;
    private javax.swing.JButton editReturnBtn;
    private javax.swing.JTextField issueDateText;
    private javax.swing.JTextField issueIDText;
    private javax.swing.JTextField issuedToText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JButton markAsReturnedBtn;
    private javax.swing.JComboBox packetIDCombo;
    private javax.swing.JCheckBox patientIssueCheckBox;
    private javax.swing.JTextField reasonTxt;
    private javax.swing.JButton removeResultBtn;
    private com.toedter.calendar.JCalendar returnedDateCalendar;
    private javax.swing.JTable returnedTable;
    private javax.swing.JCheckBox seachpatientIssueCheckBox;
    private javax.swing.JTextField searchBloodGroupText;
    private javax.swing.JTextField searchBloodTypeText;
    private javax.swing.JCheckBox searchBulkIssueTextBox;
    private javax.swing.JTextField searchIssueIDText;
    private javax.swing.JTextField searchIssuedDateText;
    private javax.swing.JTextField searchIssuedTimeText;
    private javax.swing.JTextField searchIssuedToText;
    private javax.swing.JTextField searchPacketIDText;
    private javax.swing.JTextField searchReasonText;
    private com.toedter.calendar.JDateChooser searchReturnDateCaendar;
    private javax.swing.JTextField searchReturnIDTxt;
    private javax.swing.JTable searchReturnTable;
    private com.toedter.calendar.JCalendar searchReturnedDateCalendar;
    private javax.swing.JTextField timeText;
    private javax.swing.JTextField totalText;
    private javax.swing.JButton unmarkReturnBtn;
    private javax.swing.JButton updateReturnBtn;
    // End of variables declaration//GEN-END:variables

}
