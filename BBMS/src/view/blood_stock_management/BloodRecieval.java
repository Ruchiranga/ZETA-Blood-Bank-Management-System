/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BloodRecieval.java
 *
 * Created on Sep 4, 2014, 9:57:34 PM
 */
package view.blood_stock_management;

import view.blood_stock_management.UpdateRecievedBloodPacket;
import view.blood_stock_management.BloodPacketForm;
import view.blood_stock_management.BloodReturn;
import view.blood_stock_management.RecievedBloodPacket;
import view.users.Nurse;
import controller.blood_stock_management.BloodPacketController;
import controller.blood_stock_management.BloodRecievedDetailController;
import controller.blood_stock_management.BloodRecievedLogController;
import controller.donor_management.DonorController;
import controller.camp_management.EmployeeController;
import controller.blood_stock_management.RequesteeController;
import controller.testing.TestController;
import controller.testing.TestResultController;
import controller.common.IDGenerator;
import controller.common.TableResizer;
import connection.NotifierConnection;
import controller.common.SearchableCombo;
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
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BloodPacket;
import model.BloodReceivedLog;
import model.BloodrecievedDetail;
import model.TestResult;

/**
 *
 * @author Anuradha
 */
public class BloodRecieval extends javax.swing.JInternalFrame {

    Nurse nurseForm = null;

    String[] title = {"Unit No", "Donor's Name", "Blood Group", "Blood Type", "Date of collection", "Date of Expiry", "TTI Results", "Remarks"};
    DefaultTableModel dtm = new DefaultTableModel(title, 0);
    DefaultTableModel searchDtm = new DefaultTableModel(title, 0);

    BloodStockUpdateNotifier notifier = null;

    /**
     * Creates new form BloodRecieval
     */
    public BloodRecieval(Nurse nurseForm) throws IOException {
        initComponents();
        this.nurseForm = nurseForm;

        FileInputStream imgStream = null;
        File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
        imgStream = new FileInputStream(imgfile);
        BufferedImage bi = ImageIO.read(imgStream);
        ImageIcon myImg = new ImageIcon(bi);
        this.setFrameIcon(myImg);
        setTitle("Blood Recieval");

        Calendar currenttime = Calendar.getInstance();
        java.util.Date today = new java.util.Date((currenttime.getTime()).getTime());
        recievedDateCalendar.setDate(today);
        searchRecievedDateCalendar.setDate(today);

        setRequesteeCombo(requesteeCombo);
        setEmpCombo(empNamesCombo);
        setEmpCombo(searchEmpNamesCombo);

        notifier = NotifierConnection.getNotifierConnection(null);
        new SearchableCombo().setSearchableCombo(requesteeCombo, true);
        new SearchableCombo().setSearchableCombo(empNamesCombo, true);
        new SearchableCombo().setSearchableCombo(searchEmpNamesCombo, true);

    }

    private void clear() {
        tempText.setText("");
        sendingOfficerText.setText("");
        String[] title = {"Unit No", "Donor's Name", "Blood Group", "Blood Type", "Date of collection", "Date of Expiry", "TTI Results", "Remarks"};
        dtm = new DefaultTableModel(title, 0);
        recievalTable.setModel(dtm);
    }

    private void setSearchSenderComboItems(java.sql.Date sqlDateC) throws ClassNotFoundException, SQLException {
        ResultSet rst = BloodRecievedLogController.getSenders(sqlDateC);
        searchRequesteeCombo.removeAllItems();
        while (rst.next()) {

            boolean exists = false;
            for (int i = 0; i < searchRequesteeCombo.getItemCount(); i++) {
                if (("" + searchRequesteeCombo.getItemAt(i)).equalsIgnoreCase("" + rst.getString("Requestee"))) {
                    exists = true;
                }
            }
            if(!exists){
                searchRequesteeCombo.addItem("" + rst.getString("Requestee"));
            }
            
        }
        new SearchableCombo().setSearchableCombo(searchRequesteeCombo, true);

    }

    private void setSearchRecievedIDsComboItems(java.sql.Date sqlDateC, String sender) throws ClassNotFoundException, SQLException {
        ResultSet rst = BloodRecievedLogController.getRecievedIDsByDateAndSender(sqlDateC, sender);
        searchRecievedIDCombo.removeAllItems();
        while (rst.next()) {
            searchRecievedIDCombo.addItem("" + rst.getString("RecievedID"));
        }
        new SearchableCombo().setSearchableCombo(searchRecievedIDCombo, true);
    }

    private void setSearchRecievedData(String id) throws ClassNotFoundException, SQLException {
        searchDtm = new DefaultTableModel(title, 0);
        searchRecievalTable.setModel(searchDtm);

        ResultSet rst = BloodRecievedLogController.getDetails(id);
        int count = 0;
        while (rst.next()) {
            count++;
            searchTempText.setText("" + rst.getFloat("Tempereture"));
            searchIcePacketsCombo.setSelectedItem(rst.getString("IcePacketCobdition"));
            searchSendingOfficerText.setText(rst.getString("SendingOfficer"));
            searchEmpNamesCombo.setSelectedItem(rst.getString("Name"));

            /*Time*/
            java.sql.Time sqlTime = rst.getTime("RecievedTime");
            searchRecievedTimer.setTime(sqlTime);

        }

        if (count == 0) {
            clearUpdateFields();
        }

        ResultSet rst2 = BloodRecievedDetailController.getDetails(id);
        while (rst2.next()) {

            String[] row = {"", "", "", "", "", "", "", ""};
            row[0] = rst2.getString("PacketID");
            row[1] = rst2.getString("Name");
            row[2] = rst2.getString("BloodGroup");
            row[3] = rst2.getString("BloodType");
            row[4] = "" + rst2.getDate("DateOfDonation");
            row[5] = "" + rst2.getDate("DateOfExpiry");

            ResultSet testResult = TestResultController.getTestResults(row[0]);

            String tests = "";
            int i = 0;
            while (testResult.next()) {
                tests += ("" + testResult.getString("Name"));
                i++;
                if (i == 1) {
                    break;
                }
            }
            while (testResult.next()) {
                tests += ("," + testResult.getString("Name"));
            }

            row[6] = tests;
            row[7] = rst2.getString("Comment");
            searchDtm.addRow(row);
            TableResizer.resizeColumnWidth(searchRecievalTable);
        }

    }

    private void clearUpdateFields() {
        int rows = searchDtm.getRowCount();
        for (int i = 0; i < rows; i++) {
            try {
                searchDtm.removeRow(i);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

        }
        searchTempText.setText("");
        searchSendingOfficerText.setText("");
        java.sql.Time sqlTime = java.sql.Time.valueOf("00:00:00");
        searchRecievedTimer.setTime(sqlTime);
    }

    private void enableUpdateFields() {
        searchRecievedTimer.setEnabled(true);
        searchIcePacketsCombo.setEnabled(true);
        searchSendingOfficerText.setEnabled(true);
        searchTempText.setEnabled(true);
        searchEmpNamesCombo.setEnabled(true);
        searchRecievalTable.setEnabled(true);
        AddPacketBtn1.setEnabled(true);
        deletePacketBtn1.setEnabled(true);
        editPacketBtn1.setEnabled(true);
        updateBloodRecievalBtn.setEnabled(true);
    }

    private void disableUpdateFields() {
        searchRecievedTimer.setEnabled(false);
        searchIcePacketsCombo.setEnabled(false);
        searchSendingOfficerText.setEnabled(false);
        searchTempText.setEnabled(false);
        searchEmpNamesCombo.setEnabled(false);
        searchRecievalTable.setEnabled(false);
        AddPacketBtn1.setEnabled(false);
        deletePacketBtn1.setEnabled(false);
        editPacketBtn1.setEnabled(false);
        updateBloodRecievalBtn.setEnabled(false);
    }

    //recievalForm.setData(packetID,name,bloodGroup,bloodType,sqlDateC,sqlDateE,testResults,comment);
    public void setData(String packetID, String name, String bloodGroup, String bloodType, String dateC, String dateE, String testResults, String comment) {
        String[] row = {packetID, name, bloodGroup, bloodType, dateC, dateE, testResults, comment};
        dtm.addRow(row);
        TableResizer.resizeColumnWidth(recievalTable);
    }

    public void setUpdateNewPacketData(String packetID, String name, String bloodGroup, String bloodType, String dateC, String dateE, String testResults, String comment) {
        String[] row = {packetID, name, bloodGroup, bloodType, dateC, dateE, testResults, comment};
        searchDtm.addRow(row);
        TableResizer.resizeColumnWidth(searchRecievalTable);
    }

    public void updateData(int rowNo, String packetID, String name, String bloodGroup, String bloodType, String dateC, String dateE, String testResults, String comment) {
        String[] row = {packetID, name, bloodGroup, bloodType, dateC, dateE, testResults, comment};
        searchDtm.setValueAt(packetID, rowNo, 0);
        searchDtm.setValueAt(name, rowNo, 1);
        searchDtm.setValueAt(bloodGroup, rowNo, 2);
        searchDtm.setValueAt(bloodType, rowNo, 3);
        searchDtm.setValueAt(dateC, rowNo, 4);
        searchDtm.setValueAt(dateE, rowNo, 5);
        searchDtm.setValueAt(testResults, rowNo, 6);
        searchDtm.setValueAt(comment, rowNo, 7);
    }

    public JComboBox getRequesteeCombo() {
        return this.requesteeCombo;
    }

    public void setRequesteeCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = RequesteeController.getAllRequestees();

            while (rst.next()) {
                combo.addItem(rst.getString("RequesteeName"));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setEmpCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = EmployeeController.getAllEmployees();

            while (rst.next()) {
                combo.addItem(rst.getString("Name"));
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
        jPanel19 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tempText = new javax.swing.JTextField();
        recievedDateCalendar = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        icePacketsCombo = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        empNamesCombo = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        AddPacketBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        recievalTable = new javax.swing.JTable();
        editPacketBtn = new javax.swing.JButton();
        addRecievalBtn = new javax.swing.JButton();
        deletePacketBtn = new javax.swing.JButton();
        requesteeCombo = new javax.swing.JComboBox();
        addSenderBtn = new javax.swing.JButton();
        recievedTimer = new lu.tudor.santec.jtimechooser.JTimeChooser();
        jLabel8 = new javax.swing.JLabel();
        sendingOfficerText = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        searchTempText = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        searchIcePacketsCombo = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        searchEmpNamesCombo = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        AddPacketBtn1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        searchRecievalTable = new javax.swing.JTable();
        editPacketBtn1 = new javax.swing.JButton();
        updateBloodRecievalBtn = new javax.swing.JButton();
        deletePacketBtn1 = new javax.swing.JButton();
        searchRecievedTimer = new lu.tudor.santec.jtimechooser.JTimeChooser();
        jLabel18 = new javax.swing.JLabel();
        searchSendingOfficerText = new javax.swing.JTextField();
        editRecievalBtn = new javax.swing.JButton();
        deleteRecievalBtn = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        searchRecievedDateCalendar = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        searchRequesteeCombo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        searchRecievedIDCombo = new javax.swing.JComboBox();

        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Blood Recieval"));

        jLabel3.setText("Recieved Date*");

        jLabel4.setText("Recieved Time*");

        jLabel5.setText("Sent From*");

        jLabel6.setText("Temperature inside the container at the time of recieval");

        tempText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempTextActionPerformed(evt);
            }
        });
        tempText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tempTextKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tempTextKeyTyped(evt);
            }
        });

        recievedDateCalendar.setDateFormatString("yyyy-MM-dd");

        jLabel11.setText("Ice Packets");

        icePacketsCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Frozen", "Dissolved", "Not found" }));

        jLabel12.setText("C");

        jLabel7.setText("Recieving Officer*");

        empNamesCombo.setEditable(true);
        empNamesCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Emp Names" }));

        AddPacketBtn.setText("Add Blood Packet");
        AddPacketBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPacketBtnActionPerformed(evt);
            }
        });

        recievalTable.setModel(dtm);
        jScrollPane1.setViewportView(recievalTable);

        editPacketBtn.setText("Edit Blood Packet");
        editPacketBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPacketBtnActionPerformed(evt);
            }
        });

        addRecievalBtn.setText("Add Blood Recieval");
        addRecievalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRecievalBtnActionPerformed(evt);
            }
        });

        deletePacketBtn.setText("Delete Blood Packet");
        deletePacketBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePacketBtnActionPerformed(evt);
            }
        });

        requesteeCombo.setEditable(true);
        requesteeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Requestee Names" }));

        addSenderBtn.setText("Add new sender");
        addSenderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSenderBtnActionPerformed(evt);
            }
        });

        jLabel8.setText("Sending Officer");

        sendingOfficerText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sendingOfficerTextKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(icePacketsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(tempText, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(AddPacketBtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(deletePacketBtn)
                                .addGap(18, 18, 18)
                                .addComponent(editPacketBtn)
                                .addGap(18, 18, 18)
                                .addComponent(addRecievalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addComponent(requesteeCombo, 0, 225, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(addSenderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(421, 421, 421))
                                    .addGroup(jPanel19Layout.createSequentialGroup()
                                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(recievedTimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(recievedDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendingOfficerText, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(empNamesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(requesteeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSenderBtn))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(icePacketsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(recievedDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recievedTimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tempText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sendingOfficerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(empNamesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AddPacketBtn)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editPacketBtn)
                    .addComponent(addRecievalBtn)
                    .addComponent(deletePacketBtn))
                .addContainerGap())
        );

        jLabel51.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel51.setText("Add Blood Recieval");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(353, 353, 353))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Add Blood Recieval", jPanel11);

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Blood Recieval"));

        jLabel10.setText("Recieved Time*");

        jLabel14.setText("Temperature inside the container at the time of recieval");

        searchTempText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchTempText.setEnabled(false);
        searchTempText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTempTextActionPerformed(evt);
            }
        });
        searchTempText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTempTextKeyTyped(evt);
            }
        });

        jLabel15.setText("Ice Packets");

        searchIcePacketsCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Frozen", "Dissolved", "Not found" }));
        searchIcePacketsCombo.setEnabled(false);

        jLabel16.setText("C");

        jLabel17.setText("Recieving Officer*");

        searchEmpNamesCombo.setEditable(true);
        searchEmpNamesCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Emp Names" }));
        searchEmpNamesCombo.setEnabled(false);

        AddPacketBtn1.setText("Add Blood Packet");
        AddPacketBtn1.setEnabled(false);
        AddPacketBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPacketBtn1ActionPerformed(evt);
            }
        });

        searchRecievalTable.setModel(searchDtm);
        searchRecievalTable.setEnabled(false);
        jScrollPane2.setViewportView(searchRecievalTable);

        editPacketBtn1.setText("Edit Blood Packet");
        editPacketBtn1.setEnabled(false);
        editPacketBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPacketBtn1ActionPerformed(evt);
            }
        });

        updateBloodRecievalBtn.setText("Update Blood Recieval");
        updateBloodRecievalBtn.setEnabled(false);
        updateBloodRecievalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBloodRecievalBtnActionPerformed(evt);
            }
        });

        deletePacketBtn1.setText("Delete Blood Packet");
        deletePacketBtn1.setEnabled(false);
        deletePacketBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePacketBtn1ActionPerformed(evt);
            }
        });

        searchRecievedTimer.setEnabled(false);

        jLabel18.setText("Sending Officer");

        searchSendingOfficerText.setCaretColor(new java.awt.Color(255, 255, 255));
        searchSendingOfficerText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        searchSendingOfficerText.setEnabled(false);
        searchSendingOfficerText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchSendingOfficerTextKeyTyped(evt);
            }
        });

        editRecievalBtn.setText("Edit Recieval");
        editRecievalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRecievalBtnActionPerformed(evt);
            }
        });

        deleteRecievalBtn.setText("Delete Recieval");
        deleteRecievalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRecievalBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(AddPacketBtn1)
                        .addGap(18, 18, 18)
                        .addComponent(deletePacketBtn1)
                        .addGap(18, 18, 18)
                        .addComponent(editPacketBtn1)
                        .addGap(18, 18, 18)
                        .addComponent(updateBloodRecievalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchSendingOfficerText, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchIcePacketsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 37, Short.MAX_VALUE)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(searchEmpNamesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(searchTempText, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 8, Short.MAX_VALUE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(searchRecievedTimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editRecievalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deleteRecievalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editRecievalBtn)
                        .addComponent(deleteRecievalBtn))
                    .addComponent(searchRecievedTimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchTempText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchIcePacketsCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchSendingOfficerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchEmpNamesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editPacketBtn1)
                    .addComponent(updateBloodRecievalBtn)
                    .addComponent(deletePacketBtn1)
                    .addComponent(AddPacketBtn1))
                .addContainerGap())
        );

        jLabel52.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel52.setText("Search Blood Recieval");

        jLabel9.setText("Recieved Date");

        searchRecievedDateCalendar.setDateFormatString("yyyy-MM-dd");
        searchRecievedDateCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                searchRecievedDateCalendarPropertyChange(evt);
            }
        });

        jLabel13.setText("Sent From");

        searchRequesteeCombo.setEditable(true);
        searchRequesteeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Requestee Names" }));
        searchRequesteeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchRequesteeComboActionPerformed(evt);
            }
        });

        jLabel1.setText("Recieval ID");

        searchRecievedIDCombo.setEditable(true);
        searchRecievedIDCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RecievedIDs" }));
        searchRecievedIDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchRecievedIDComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchRecievedDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchRequesteeCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(57, 57, 57)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchRecievedIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(340, 340, 340))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchRecievedDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchRequesteeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(searchRecievedIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Search Blood Recieval", jPanel1);

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

    private void tempTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tempTextActionPerformed

    private void AddPacketBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPacketBtnActionPerformed
        RecievedBloodPacket bloodPacket = new RecievedBloodPacket("Add", this, "" + searchRecievedIDCombo.getSelectedItem());
        bloodPacket.setClosable(true);
        bloodPacket.setMaximizable(false);
        nurseForm.getDesktop().add(bloodPacket);
        nurseForm.getDesktop().setRequestFocusEnabled(true);
        bloodPacket.show();
    }//GEN-LAST:event_AddPacketBtnActionPerformed

    private void addSenderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSenderBtnActionPerformed
        AddRequestee requesteeForm = null;
        try {
            requesteeForm = new AddRequestee(this);
        } catch (IOException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        }
        requesteeForm.setClosable(true);
        nurseForm.getDesktop().add(requesteeForm);
        nurseForm.getDesktop().setRequestFocusEnabled(true);
        requesteeForm.show();
    }//GEN-LAST:event_addSenderBtnActionPerformed

    private void addRecievalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRecievalBtnActionPerformed

        try {

            //===============================================================================================
            String recievedID = "";
            ResultSet rst;
            rst = BloodRecievedLogController.getResultIDs();
            rst.last();
            String curID = null;
            try {
                curID = rst.getString("recievedID");
                try {
                    recievedID = IDGenerator.generateNextID(curID);
                } catch (Exception ex) {
                    Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                recievedID = "RE00000001";
            }

            String requestee = "" + requesteeCombo.getSelectedItem();
            requestee = requestee.trim();

            /*Recieved date*/
            java.util.Date dateR = recievedDateCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateRecieved = df.format(dateR);
            java.sql.Date sqlDateC = new java.sql.Date(dateR.getTime());

            /*Time*/
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            java.sql.Time sqlTime = null;
            try {
                sqlTime = new java.sql.Time(formatter.parse(recievedTimer.getFormatedTime()).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
            }

            String sendingOfficer = sendingOfficerText.getText();
            float temp = Float.parseFloat(tempText.getText());
            String icePacketCondition = "" + icePacketsCombo.getSelectedItem();
            String recievingOfficerID = EmployeeController.getEmpID("" + empNamesCombo.getSelectedItem());

            BloodReceivedLog log = new BloodReceivedLog(recievedID, requestee, sqlDateC, sqlTime, sendingOfficer, recievingOfficerID, temp, icePacketCondition);
            int res1 = BloodRecievedLogController.addLog(log);

            //=============================================================================================== BloodRecivedLog
            if (res1 == 1) {

                int res_bloodPacket = 0;
                int res_bloodPacketRecievedDetail = 0;
                int res_donor = 0;
                int res_Test = 0;

                BloodrecievedDetail detail;

                for (int i = 0; i < dtm.getRowCount(); i++) {
                    String packetID = "" + dtm.getValueAt(i, 0);

                    detail = new BloodrecievedDetail(recievedID, packetID);

                    //================================================================================== BloodRecivedDetail
                    //{packetID, name, bloodGroup, bloodType, dateC, dateE, testResults, comment};
                    String donorName = "" + dtm.getValueAt(i, 1);   //Have to generate a (-) nic

                    int donor_nic;

                    do {

                        Random r = new Random();
                        donor_nic = Math.abs((r.nextInt() % 1000000000)) + 1;

                    } while (DonorController.isNicDuplicate(donor_nic));

                    String donorNic = "" + donor_nic + "-";
                    System.out.println("NIC : " + donorNic);

                    String bloodGroup = "" + dtm.getValueAt(i, 2);
                    String bloodType = "" + dtm.getValueAt(i, 3);

                    //Collection date
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsed = null;
                    try {
                        parsed = format.parse("" + dtm.getValueAt(i, 4));
                    } catch (ParseException ex) {
                        Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.sql.Date sqlDateCollection = new java.sql.Date(parsed.getTime());

                    //Expiry date
                    try {
                        parsed = format.parse("" + dtm.getValueAt(i, 5));
                    } catch (ParseException ex) {
                        Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    java.sql.Date sqlDateExpiry = new java.sql.Date(parsed.getTime());

                    String ttiResults = "" + dtm.getValueAt(i, 6);;
                    String comment = "" + dtm.getValueAt(i, 7);

                    res_donor += DonorController.addDonorFromOtherBloodBank(donorNic, donorName);

                    BloodPacket newPacket = new BloodPacket(packetID, donorNic, recievedID, sqlDateCollection, sqlDateExpiry, bloodType, (byte) 0, (byte) 0, (byte) 0, null, (byte) 0, bloodGroup, null, null, null, comment, null);

                    res_bloodPacket += BloodPacketController.addRecievedPacket(newPacket);
                    res_bloodPacketRecievedDetail += BloodRecievedDetailController.addDetail(detail);

                    //=========================================================================================
                    String[] tests = ttiResults.split(",");   //Tests want to update test result table also

                    for (int j = 0; j < tests.length; j++) {

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

                        String test = tests[j];
                        String testId = TestController.getTestID(test);
                        if (testId != null) {
                            TestResult result = new TestResult(testResultID, testId, packetID, "Negative", "None", null, null, null, null);
                            res_Test += TestResultController.addTestResultOfRecievedBloodPackets(result);
                        }

                    }

                }

                if ((res_donor + res_bloodPacket + res_bloodPacketRecievedDetail) == 3 * dtm.getRowCount() && res_Test > 0) {
                    notifier.notifyUpdateBloodStock();
                    JOptionPane.showMessageDialog(null, "Successfully updated");
                    clear();
                    setSenderComboData();
                } else {
                    JOptionPane.showMessageDialog(null, "An error occured!");
                }

            } else {
                JOptionPane.showMessageDialog(null, "An error occured!");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_addRecievalBtnActionPerformed

    private void editPacketBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPacketBtnActionPerformed
        int row = recievalTable.getSelectedRow();
        if (row >= 0) {
            String[] result = new String[8];

            for (int i = 0; i < 8; i++) {
                result[i] = "" + dtm.getValueAt(row, i);
            }

            UpdateRecievedBloodPacket updatePacketForm = new UpdateRecievedBloodPacket(this, result, row);
            updatePacketForm.setClosable(true);
            updatePacketForm.setMaximizable(false);
            nurseForm.getDesktop().add(updatePacketForm);
            nurseForm.getDesktop().setRequestFocusEnabled(true);
            updatePacketForm.show();

        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_editPacketBtnActionPerformed

    private void searchTempTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTempTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTempTextActionPerformed

    private void AddPacketBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPacketBtn1ActionPerformed
        RecievedBloodPacket bloodPacket = new RecievedBloodPacket("Update", this, "" + searchRecievedIDCombo.getSelectedItem());
        bloodPacket.setClosable(true);
        bloodPacket.setMaximizable(false);
        nurseForm.getDesktop().add(bloodPacket);
        nurseForm.getDesktop().setRequestFocusEnabled(true);
        bloodPacket.show();
    }//GEN-LAST:event_AddPacketBtn1ActionPerformed

    private void editPacketBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPacketBtn1ActionPerformed
        int row = searchRecievalTable.getSelectedRow();
        if (row >= 0) {
            String[] result = new String[8];

            for (int i = 0; i < 8; i++) {
                result[i] = "" + searchDtm.getValueAt(row, i);
            }

            UpdateRecievedBloodPacket updatePacketForm = new UpdateRecievedBloodPacket(this, result, row);
            updatePacketForm.setClosable(true);
            updatePacketForm.setMaximizable(false);
            nurseForm.getDesktop().add(updatePacketForm);
            nurseForm.getDesktop().setRequestFocusEnabled(true);
            updatePacketForm.show();

        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_editPacketBtn1ActionPerformed

    private void updateBloodRecievalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBloodRecievalBtnActionPerformed
        try {

            //===============================================================================================
            String recievedID = "" + searchRecievedIDCombo.getSelectedItem();

            String requestee = "" + searchRequesteeCombo.getSelectedItem();

            /*Recieved date*/
            java.util.Date dateR = searchRecievedDateCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateRecieved = df.format(dateR);
            java.sql.Date sqlDateC = new java.sql.Date(dateR.getTime());

            /*Time*/
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
            java.sql.Time sqlTime = null;
            try {
                sqlTime = new java.sql.Time(formatter.parse(searchRecievedTimer.getFormatedTime()).getTime());
            } catch (ParseException ex) {
                Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
            }

            String sendingOfficer = searchSendingOfficerText.getText();
            float temp = Float.parseFloat(searchTempText.getText());
            String icePacketCondition = "" + searchIcePacketsCombo.getSelectedItem();
            String recievingOfficerID = EmployeeController.getEmpID("" + searchEmpNamesCombo.getSelectedItem());
            recievingOfficerID = recievingOfficerID.trim();
            System.out.println("emp ID : " + recievingOfficerID);

            BloodReceivedLog log = new BloodReceivedLog(recievedID, requestee, sqlDateC, sqlTime, sendingOfficer, recievingOfficerID, temp, icePacketCondition);

            int res1 = BloodRecievedLogController.updateLog(log);

            if (res1 == 1) {
                notifier.notifyUpdateBloodStock();
                JOptionPane.showMessageDialog(null, "Updated successfully!");
                setSenderComboData();
            } else {
                JOptionPane.showMessageDialog(null, "An error occured!");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateBloodRecievalBtnActionPerformed

    private void setSenderComboData() {
        try {
            /*Date*/
            java.util.Date dateC = searchRecievedDateCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.format(dateC);
            java.sql.Date sqlDateC = new java.sql.Date(dateC.getTime());

            setSearchSenderComboItems(sqlDateC);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void searchRecievedDateCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_searchRecievedDateCalendarPropertyChange

        setSenderComboData();

    }//GEN-LAST:event_searchRecievedDateCalendarPropertyChange

    private void searchRequesteeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchRequesteeComboActionPerformed
        try {
            /*Date*/
            java.util.Date dateC = searchRecievedDateCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.format(dateC);
            java.sql.Date sqlDateC = new java.sql.Date(dateC.getTime());

            String sender = "" + searchRequesteeCombo.getSelectedItem();
            sender = sender.trim();
            setSearchRecievedIDsComboItems(sqlDateC, sender);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchRequesteeComboActionPerformed

    private void searchRecievedIDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchRecievedIDComboActionPerformed
        try {
            disableUpdateFields();
            String id = null;
            id = "" + searchRecievedIDCombo.getSelectedItem();
            id = id.trim();
            setSearchRecievedData(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchRecievedIDComboActionPerformed

    private void deletePacketBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePacketBtnActionPerformed
        int row = recievalTable.getSelectedRow();
        if (row >= 0) {
            dtm.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_deletePacketBtnActionPerformed

    private void deleteRecievalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRecievalBtnActionPerformed
        try {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanantly delete the entire Blood Recieval Record?", "Delete Blood Recieval", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                int rowCount = searchRecievalTable.getRowCount();
                int resPacket = 0;
                for (int i = 0; i < rowCount; i++) {
                    resPacket += BloodRecievedLogController.deletePacketRecievalData("" + searchDtm.getValueAt(i, 0));
                }
                String recievedID = "" + searchRecievedIDCombo.getSelectedItem();
                int resRecieval = BloodRecievedLogController.deleteRecieval(recievedID);
                if (resRecieval == 1) {
                    notifier.notifyUpdateBloodStock();
                    JOptionPane.showMessageDialog(null, "Recival records deleted successfully!");
                    searchRecievedIDCombo.removeItem("" + recievedID);
                    setSenderComboData();
                } else {
                    JOptionPane.showMessageDialog(null, "Error occured while deleting records");
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deleteRecievalBtnActionPerformed

    private void editRecievalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRecievalBtnActionPerformed
        enableUpdateFields();
    }//GEN-LAST:event_editRecievalBtnActionPerformed

    private void deletePacketBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePacketBtn1ActionPerformed
        int row = searchRecievalTable.getSelectedRow();
        if (row >= 0) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected blood packet?", "Delete Blood Packet", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                int resPacket = 0;
                try {
                    resPacket = BloodRecievedLogController.deletePacketRecievalData("" + searchDtm.getValueAt(row, 0));
                    if (resPacket == 1) {
                        notifier.notifyUpdateBloodStock();
                        JOptionPane.showMessageDialog(null, "Blood Packet deleted successfully!");
                        setSenderComboData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occured while deleting blood packet!");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(BloodRecieval.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_deletePacketBtn1ActionPerformed

    private void tempTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tempTextKeyReleased

    }//GEN-LAST:event_tempTextKeyReleased

    private void tempTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tempTextKeyTyped
        char c = evt.getKeyChar();
        if (tempText.getText().length() >= 12) {
            evt.consume();
        }
        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == '.') || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_tempTextKeyTyped

    private void searchTempTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTempTextKeyTyped
        char c = evt.getKeyChar();
        if (searchTempText.getText().length() >= 12) {
            evt.consume();
        }
        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == '.') || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_searchTempTextKeyTyped

    private void sendingOfficerTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sendingOfficerTextKeyTyped
        if (sendingOfficerText.getText().length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_sendingOfficerTextKeyTyped

    private void searchSendingOfficerTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchSendingOfficerTextKeyTyped
        if (searchSendingOfficerText.getText().length() >= 200) {
            evt.consume();
        }
    }//GEN-LAST:event_searchSendingOfficerTextKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddPacketBtn;
    private javax.swing.JButton AddPacketBtn1;
    private javax.swing.JButton addRecievalBtn;
    private javax.swing.JButton addSenderBtn;
    private javax.swing.JButton deletePacketBtn;
    private javax.swing.JButton deletePacketBtn1;
    private javax.swing.JButton deleteRecievalBtn;
    private javax.swing.JButton editPacketBtn;
    private javax.swing.JButton editPacketBtn1;
    private javax.swing.JButton editRecievalBtn;
    private javax.swing.JComboBox empNamesCombo;
    private javax.swing.JComboBox icePacketsCombo;
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
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable recievalTable;
    private com.toedter.calendar.JDateChooser recievedDateCalendar;
    private lu.tudor.santec.jtimechooser.JTimeChooser recievedTimer;
    private javax.swing.JComboBox requesteeCombo;
    private javax.swing.JComboBox searchEmpNamesCombo;
    private javax.swing.JComboBox searchIcePacketsCombo;
    private javax.swing.JTable searchRecievalTable;
    private com.toedter.calendar.JDateChooser searchRecievedDateCalendar;
    private javax.swing.JComboBox searchRecievedIDCombo;
    private lu.tudor.santec.jtimechooser.JTimeChooser searchRecievedTimer;
    private javax.swing.JComboBox searchRequesteeCombo;
    private javax.swing.JTextField searchSendingOfficerText;
    private javax.swing.JTextField searchTempText;
    private javax.swing.JTextField sendingOfficerText;
    private javax.swing.JTextField tempText;
    private javax.swing.JButton updateBloodRecievalBtn;
    // End of variables declaration//GEN-END:variables

}
