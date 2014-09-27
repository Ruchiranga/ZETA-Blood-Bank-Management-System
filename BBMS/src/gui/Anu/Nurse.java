/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Nurse.java
 *
 * Created on Jul 28, 2014, 9:26:48 PM
 */
package gui.Anu;

import controller.anu.BloodPacketDA;
import gui.ChangePassword;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BloodPacket;

/**
 *
 * @author Anuradha
 */
public class Nurse extends javax.swing.JFrame {

    String[] title = {"PacketID", "Blood Type", "Blood Group"};
    DefaultTableModel dtm = new DefaultTableModel(title, 0);
    Calendar currenttime = Calendar.getInstance();
    java.sql.Date sqldate = new java.sql.Date((currenttime.getTime()).getTime());
    String[] bloodTitle = {"Arribite", "Count"};
    Object[][] bloodData = {{"Balance", null},
    {"Inhouse", null},
    {"Mobile", null},
    {"", null},
    {"Recieved", null},
    {"Returned", null},
    {"", null},
    {"Issued", null},
    {"", null},
    {"Discarded", null},
    {"Total", null}};
    DefaultTableModel freshBloodDtm = new DefaultTableModel(bloodData, bloodTitle);
    String[] bloodStockTitle = {"Blood Group", "Un X matched", "X matched", "Special Reservations", "Under Observation", "Total"};
    Object[][] bloodStockData = {{"A +ve", null, null, null, null, null},
    {"B +ve", null, null, null, null, null},
    {"AB +ve", null, null, null, null, null},
    {"O +ve", null, null, null, null, null},
    {"A neg", null, null, null, null, null},
    {"B neg", null, null, null, null, null},
    {"AB neg", null, null, null, null, null},
    {"O neg", null, null, null, null, null},
    {"Total", null, null, null, null, null},
    {"Untested", null, null, null, null, null},
    {null, null, null, null, "Grand Total", null}};
    DefaultTableModel bloodStockDtm = new DefaultTableModel(bloodStockData, bloodStockTitle);
    String[] componentStockTitle = {"Component", "A +ve", "A neg", "B +ve", "B neg", "AB +ve", "AB neg", "O +ve", "O neg", "UG", "Total"};
    Object[][] componenetStockData = {{"Platelets", null, null, null, null, null, null, null, null, null, null},
    {"FFP", null, null, null, null, null, null, null, null, null, null},
    {"CRYO", null, null, null, null, null, null, null, null, null, null},
    {"Plasma/CSP", null, null, null, null, null, null, null, null, null, null}};
    DefaultTableModel componenetStockDtm = new DefaultTableModel(componenetStockData, componentStockTitle);

    /**
     * Creates new form Nurse
     */
    public Nurse() throws IOException {

        try {
            this.setName("Blood Bank Management System");
            initComponents();
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            FileInputStream imgStream = new FileInputStream(imgfile);
            BufferedImage bi = ImageIO.read(imgStream);
            ImageIcon myImg = new ImageIcon(bi);
            this.setIconImage(myImg.getImage());

            ResultSet rst = BloodPacketDA.getExpiredBloodPackets(sqldate);
            String packetID = null;
            String bloodType = null;
            String bloodGroup = null;
            int count = 0;
            while (rst.next()) {
                packetID = rst.getString("packetID");
                bloodType = rst.getString("bloodType");
                bloodGroup = rst.getString("bloodGroup");
                String[] ar = {packetID, bloodType, bloodGroup};
                dtm.addRow(ar);
                count++;
            }
            totalTxt.setText("" + count);

            setDailyStockBalance();

            setLocationRelativeTo(null);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JDesktopPane getDesktop() {
        return this.NurseDesktop;
    }

    private void setDailyStockBalance() {
        try {

            /* Updating Components Table */
            //platelets
            int platelet_A_plus_count = BloodPacketDA.getBloodComponentCount("Platelets", "A+");
            componenetStockDtm.setValueAt("" + platelet_A_plus_count, 0, 1);
            int platelet_A_min_count = BloodPacketDA.getBloodComponentCount("Platelets", "A-");
            componenetStockDtm.setValueAt("" + platelet_A_min_count, 0, 2);
            int platelet_B_plus_count = BloodPacketDA.getBloodComponentCount("Platelets", "B+");
            componenetStockDtm.setValueAt("" + platelet_B_plus_count, 0, 3);
            int platelet_B_min_count = BloodPacketDA.getBloodComponentCount("Platelets", "B-");
            componenetStockDtm.setValueAt("" + platelet_B_min_count, 0, 4);
            int platelet_AB_plus_count = BloodPacketDA.getBloodComponentCount("Platelets", "AB+");
            componenetStockDtm.setValueAt("" + platelet_AB_plus_count, 0, 5);
            int platelet_AB_min_count = BloodPacketDA.getBloodComponentCount("Platelets", "AB-");
            componenetStockDtm.setValueAt("" + platelet_AB_min_count, 0, 6);
            int platelet_O_plus_count = BloodPacketDA.getBloodComponentCount("Platelets", "O+");
            componenetStockDtm.setValueAt("" + platelet_O_plus_count, 0, 7);
            int platelet_O_min_count = BloodPacketDA.getBloodComponentCount("Platelets", "O-");
            componenetStockDtm.setValueAt("" + platelet_O_min_count, 0, 8);
            int platelet_unknown_count = BloodPacketDA.getBloodComponentCount("Platelets", null);
            componenetStockDtm.setValueAt("" + platelet_unknown_count, 0, 9);

            //ffp
            int ffp_A_plus_count = BloodPacketDA.getBloodComponentCount("FFP", "A+");
            componenetStockDtm.setValueAt("" + ffp_A_plus_count, 1, 1);
            int ffp_A_min_count = BloodPacketDA.getBloodComponentCount("FFP", "A-");
            componenetStockDtm.setValueAt("" + ffp_A_min_count, 1, 2);
            int ffp_B_plus_count = BloodPacketDA.getBloodComponentCount("FFP", "B+");
            componenetStockDtm.setValueAt("" + ffp_B_plus_count, 1, 3);
            int ffp_B_min_count = BloodPacketDA.getBloodComponentCount("FFP", "B-");
            componenetStockDtm.setValueAt("" + ffp_B_min_count, 1, 4);
            int ffp_AB_plus_count = BloodPacketDA.getBloodComponentCount("FFP", "AB+");
            componenetStockDtm.setValueAt("" + ffp_AB_plus_count, 1, 5);
            int ffp_AB_min_count = BloodPacketDA.getBloodComponentCount("FFP", "AB-");
            componenetStockDtm.setValueAt("" + ffp_AB_min_count, 1, 6);
            int ffp_O_plus_count = BloodPacketDA.getBloodComponentCount("FFP", "O+");
            componenetStockDtm.setValueAt("" + ffp_O_plus_count, 1, 7);
            int ffp_O_min_count = BloodPacketDA.getBloodComponentCount("FFP", "O-");
            componenetStockDtm.setValueAt("" + ffp_O_min_count, 1, 8);
            int ffp_unknown_count = BloodPacketDA.getBloodComponentCount("FFP", null);
            componenetStockDtm.setValueAt("" + ffp_unknown_count, 1, 9);

            //cryo
            int cryo_A_plus_count = BloodPacketDA.getBloodComponentCount("CRYO", "A+");
            componenetStockDtm.setValueAt("" + cryo_A_plus_count, 2, 1);
            int cryo_A_min_count = BloodPacketDA.getBloodComponentCount("CRYO", "A-");
            componenetStockDtm.setValueAt("" + cryo_A_min_count, 2, 2);
            int cryo_B_plus_count = BloodPacketDA.getBloodComponentCount("CRYO", "B+");
            componenetStockDtm.setValueAt("" + cryo_B_plus_count, 2, 3);
            int cryo_B_min_count = BloodPacketDA.getBloodComponentCount("CRYO", "B-");
            componenetStockDtm.setValueAt("" + cryo_B_min_count, 2, 4);
            int cryo_AB_plus_count = BloodPacketDA.getBloodComponentCount("CRYO", "AB+");
            componenetStockDtm.setValueAt("" + cryo_AB_plus_count, 2, 5);
            int cryo_AB_min_count = BloodPacketDA.getBloodComponentCount("CRYO", "AB-");
            componenetStockDtm.setValueAt("" + cryo_AB_min_count, 2, 6);
            int cryo_O_plus_count = BloodPacketDA.getBloodComponentCount("CRYO", "O+");
            componenetStockDtm.setValueAt("" + cryo_O_plus_count, 2, 7);
            int cryo_O_min_count = BloodPacketDA.getBloodComponentCount("CRYO", "O-");
            componenetStockDtm.setValueAt("" + cryo_O_min_count, 2, 8);
            int cryo_unknown_count = BloodPacketDA.getBloodComponentCount("CRYO", null);
            componenetStockDtm.setValueAt("" + cryo_unknown_count, 2, 9);

            //plasma
            int plasma_A_plus_count = BloodPacketDA.getBloodComponentCount("Plasma/CSP", "A+");
            componenetStockDtm.setValueAt("" + plasma_A_plus_count, 3, 1);
            int plasma_A_min_count = BloodPacketDA.getBloodComponentCount("Plasma/CSP", "A-");
            componenetStockDtm.setValueAt("" + plasma_A_min_count, 3, 2);
            int plasma_B_plus_count = BloodPacketDA.getBloodComponentCount("Plasma/CSP", "B+");
            componenetStockDtm.setValueAt("" + plasma_B_plus_count, 3, 3);
            int plasma_B_min_count = BloodPacketDA.getBloodComponentCount("Plasma/CSP", "B-");
            componenetStockDtm.setValueAt("" + plasma_B_min_count, 3, 4);
            int plasma_AB_plus_count = BloodPacketDA.getBloodComponentCount("Plasma/CSP", "AB+");
            componenetStockDtm.setValueAt("" + plasma_AB_plus_count, 3, 5);
            int plasma_AB_min_count = BloodPacketDA.getBloodComponentCount("Plasma/CSP", "AB-");
            componenetStockDtm.setValueAt("" + plasma_AB_min_count, 3, 6);
            int plasma_O_plus_count = BloodPacketDA.getBloodComponentCount("Plasma/CSP", "O+");
            componenetStockDtm.setValueAt("" + plasma_O_plus_count, 3, 7);
            int plasma_O_min_count = BloodPacketDA.getBloodComponentCount("Plasma/CSP", "O-");
            componenetStockDtm.setValueAt("" + plasma_O_min_count, 3, 8);
            int plasma_unknown_count = BloodPacketDA.getBloodComponentCount("Plasma/CSP", null);
            componenetStockDtm.setValueAt("" + plasma_unknown_count, 3, 9);

            /* Calculate component total */
            for (int i = 0; i < 4; i++) {
                int total = 0;
                int j = 1;
                for (; j < 10; j++) {
                    total += Integer.parseInt("" + componenetStockDtm.getValueAt(i, j));
                }
                componenetStockDtm.setValueAt("" + total, i, j);
            }


            /*Fresh Blood Stock Balance Table */
            //A+
            int fresh_blood_A_plus_UnX = BloodPacketDA.getUncrossmatchedFreshBloodCount("A+");
            bloodStockDtm.setValueAt(fresh_blood_A_plus_UnX, 0, 1);
            int fresh_blood_A_plus_X = BloodPacketDA.getCrossmatchedFreshBloodCount("A+");
            bloodStockDtm.setValueAt(fresh_blood_A_plus_X, 0, 2);
            int fresh_blood_A_plus_SR = BloodPacketDA.getSpecialReservationFreshBloodCount("A+");
            bloodStockDtm.setValueAt(fresh_blood_A_plus_SR, 0, 3);
            int fresh_blood_A_plus_UO = BloodPacketDA.getUnderObservationFreshBloodCount("A+");
            bloodStockDtm.setValueAt(fresh_blood_A_plus_UO, 0, 4);

            //B+
            int fresh_blood_B_plus_UnX = BloodPacketDA.getUncrossmatchedFreshBloodCount("B+");
            bloodStockDtm.setValueAt(fresh_blood_B_plus_UnX, 1, 1);
            int fresh_blood_B_plus_X = BloodPacketDA.getCrossmatchedFreshBloodCount("B+");
            bloodStockDtm.setValueAt(fresh_blood_B_plus_X, 1, 2);
            int fresh_blood_B_plus_SR = BloodPacketDA.getSpecialReservationFreshBloodCount("B+");
            bloodStockDtm.setValueAt(fresh_blood_B_plus_SR, 1, 3);
            int fresh_blood_B_plus_UO = BloodPacketDA.getUnderObservationFreshBloodCount("B+");
            bloodStockDtm.setValueAt(fresh_blood_B_plus_UO, 1, 4);

            //AB+
            int fresh_blood_AB_plus_UnX = BloodPacketDA.getUncrossmatchedFreshBloodCount("AB+");
            bloodStockDtm.setValueAt(fresh_blood_AB_plus_UnX, 2, 1);
            int fresh_blood_AB_plus_X = BloodPacketDA.getCrossmatchedFreshBloodCount("AB+");
            bloodStockDtm.setValueAt(fresh_blood_AB_plus_X, 2, 2);
            int fresh_blood_AB_plus_SR = BloodPacketDA.getSpecialReservationFreshBloodCount("AB+");
            bloodStockDtm.setValueAt(fresh_blood_AB_plus_SR, 2, 3);
            int fresh_blood_AB_plus_UO = BloodPacketDA.getUnderObservationFreshBloodCount("AB+");
            bloodStockDtm.setValueAt(fresh_blood_AB_plus_UO, 2, 4);

            //O+
            int fresh_blood_O_plus_UnX = BloodPacketDA.getUncrossmatchedFreshBloodCount("O+");
            bloodStockDtm.setValueAt(fresh_blood_O_plus_UnX, 3, 1);
            int fresh_blood_O_plus_X = BloodPacketDA.getCrossmatchedFreshBloodCount("O+");
            bloodStockDtm.setValueAt(fresh_blood_O_plus_X, 3, 2);
            int fresh_blood_O_plus_SR = BloodPacketDA.getSpecialReservationFreshBloodCount("O+");
            bloodStockDtm.setValueAt(fresh_blood_O_plus_SR, 3, 3);
            int fresh_blood_O_plus_UO = BloodPacketDA.getUnderObservationFreshBloodCount("O+");
            bloodStockDtm.setValueAt(fresh_blood_O_plus_UO, 3, 4);

            //A-
            int fresh_blood_A_min_UnX = BloodPacketDA.getUncrossmatchedFreshBloodCount("A-");
            bloodStockDtm.setValueAt(fresh_blood_A_min_UnX, 4, 1);
            int fresh_blood_A_min_X = BloodPacketDA.getCrossmatchedFreshBloodCount("A-");
            bloodStockDtm.setValueAt(fresh_blood_A_min_X, 4, 2);
            int fresh_blood_A_min_SR = BloodPacketDA.getSpecialReservationFreshBloodCount("A-");
            bloodStockDtm.setValueAt(fresh_blood_A_min_SR, 4, 3);
            int fresh_blood_A_min_UO = BloodPacketDA.getUnderObservationFreshBloodCount("A-");
            bloodStockDtm.setValueAt(fresh_blood_A_min_UO, 4, 4);

            //B-
            int fresh_blood_B_min_UnX = BloodPacketDA.getUncrossmatchedFreshBloodCount("B-");
            bloodStockDtm.setValueAt(fresh_blood_B_min_UnX, 5, 1);
            int fresh_blood_B_min_X = BloodPacketDA.getCrossmatchedFreshBloodCount("B-");
            bloodStockDtm.setValueAt(fresh_blood_B_min_X, 5, 2);
            int fresh_blood_B_min_SR = BloodPacketDA.getSpecialReservationFreshBloodCount("B-");
            bloodStockDtm.setValueAt(fresh_blood_B_min_SR, 5, 3);
            int fresh_blood_B_min_UO = BloodPacketDA.getUnderObservationFreshBloodCount("B-");
            bloodStockDtm.setValueAt(fresh_blood_B_min_UO, 5, 4);

            //AB-
            int fresh_blood_AB_min_UnX = BloodPacketDA.getUncrossmatchedFreshBloodCount("AB-");
            bloodStockDtm.setValueAt(fresh_blood_AB_min_UnX, 6, 1);
            int fresh_blood_AB_min_X = BloodPacketDA.getCrossmatchedFreshBloodCount("AB-");
            bloodStockDtm.setValueAt(fresh_blood_AB_min_X, 6, 2);
            int fresh_blood_AB_min_SR = BloodPacketDA.getSpecialReservationFreshBloodCount("AB-");
            bloodStockDtm.setValueAt(fresh_blood_AB_min_SR, 6, 3);
            int fresh_blood_AB_min_UO = BloodPacketDA.getUnderObservationFreshBloodCount("AB-");
            bloodStockDtm.setValueAt(fresh_blood_AB_min_UO, 6, 4);

            //O-
            int fresh_blood_O_min_UnX = BloodPacketDA.getUncrossmatchedFreshBloodCount("O-");
            bloodStockDtm.setValueAt(fresh_blood_O_min_UnX, 7, 1);
            int fresh_blood_O_min_X = BloodPacketDA.getCrossmatchedFreshBloodCount("O-");
            bloodStockDtm.setValueAt(fresh_blood_O_min_X, 7, 2);
            int fresh_blood_O_min_SR = BloodPacketDA.getSpecialReservationFreshBloodCount("O-");
            bloodStockDtm.setValueAt(fresh_blood_O_min_SR, 7, 3);
            int fresh_blood_O_min_UO = BloodPacketDA.getUnderObservationFreshBloodCount("O-");
            bloodStockDtm.setValueAt(fresh_blood_O_min_UO, 7, 4);

            /* Calculate blood total */
            int netTotal_Tested = 0;
            int i = 0;
            int j = 1;
            for (; i < 8; i++) {
                int total = 0;
                j = 1;
                for (; j < 5; j++) {
                    total += Integer.parseInt("" + bloodStockDtm.getValueAt(i, j));
                }
                bloodStockDtm.setValueAt("" + total, i, j);
                netTotal_Tested += total;
            }
            bloodStockDtm.setValueAt("" + netTotal_Tested, i, j);

            int fresh_blood_untested = BloodPacketDA.getUntestedFreshBloodCount("");
            bloodStockDtm.setValueAt(fresh_blood_untested, i + 1, j);

            int grantTotal = netTotal_Tested + fresh_blood_untested;
            bloodStockDtm.setValueAt(grantTotal, i + 2, j);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
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

        jButton5 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        NurseDesktop = new javax.swing.JDesktopPane();
        jButton17 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        expiredBloodPacketsTable = new javax.swing.JTable();
        refreshBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        totalTxt = new javax.swing.JTextField();
        discardBtn = new javax.swing.JButton();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        bloodStockTable = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        componentStockTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        bloodReturnBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        addRequesteeBtn = new javax.swing.JButton();
        addRequestorBtn = new javax.swing.JButton();
        addBloodTypesBtn = new javax.swing.JButton();
        addBloodGroupBtn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton34 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton41 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jButton36 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Candy Vivian Icon 02.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.setBackground(new java.awt.Color(204, 204, 204));

        NurseDesktop.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/images/red-blood-cells.png")))); // NOI18N

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Candy Vivian Icon 30.png"))); // NOI18N
        NurseDesktop.add(jButton17);
        jButton17.setBounds(1280, 420, 90, 90);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Candy Vivian Icon 30.png"))); // NOI18N
        NurseDesktop.add(jButton19);
        jButton19.setBounds(1280, 420, 90, 90);

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Candy Vivian Icon 30.png"))); // NOI18N
        NurseDesktop.add(jButton20);
        jButton20.setBounds(1280, 420, 90, 90);

        jPanel1.setBackground(new java.awt.Color(255, 0, 51));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Packets to be discarded"));

        expiredBloodPacketsTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        expiredBloodPacketsTable.setModel(dtm);
        jScrollPane1.setViewportView(expiredBloodPacketsTable);

        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Total");

        totalTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        totalTxt.setEnabled(false);

        discardBtn.setText("Discard");
        discardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(discardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(refreshBtn)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(discardBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18))
        );

        NurseDesktop.add(jPanel1);
        jPanel1.setBounds(900, 160, 270, 480);

        jCalendar1.setBackground(new java.awt.Color(255, 0, 0));
        jCalendar1.setForeground(new java.awt.Color(255, 0, 0));
        NurseDesktop.add(jCalendar1);
        jCalendar1.setBounds(902, 0, 270, 160);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 51, 255), new java.awt.Color(102, 255, 255), new java.awt.Color(51, 153, 255)), "Daily Blood and Component Stock Balance Register"));

        bloodStockTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bloodStockTable.setModel(bloodStockDtm);
        bloodStockTable.setEnabled(false);
        jScrollPane12.setViewportView(bloodStockTable);

        componentStockTable.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        componentStockTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        componentStockTable.setModel(componenetStockDtm);
        componentStockTable.setEnabled(false);
        jScrollPane15.setViewportView(componentStockTable);

        jButton1.setText("View Stock Balance");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        NurseDesktop.add(jPanel2);
        jPanel2.setBounds(220, 0, 690, 450);

        jLayeredPane1.add(NurseDesktop);
        NurseDesktop.setBounds(200, 60, 1170, 650);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Vijaya", 1, 36)); // NOI18N
        jLabel1.setText("Karapitiya Blood Bank Management System - Nurse Form");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(0, 153, 255)));
        jLayeredPane1.add(jLabel1);
        jLabel1.setBounds(360, 0, 710, 60);

        jButton22.setText("Inhouse/Mobile");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton22);
        jButton22.setBounds(0, 100, 200, 30);

        jButton37.setBackground(new java.awt.Color(255, 0, 51));
        jButton37.setText("Blood Recieval");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton37);
        jButton37.setBounds(0, 130, 200, 30);

        bloodReturnBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I03.png"))); // NOI18N
        bloodReturnBtn.setToolTipText("Mark returned blood packets");
        bloodReturnBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(0, 153, 255)));
        bloodReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodReturnBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bloodReturnBtn);
        bloodReturnBtn.setBounds(300, 0, 60, 60);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/final1.png"))); // NOI18N
        jLabel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(102, 255, 255), new java.awt.Color(51, 153, 255)));
        jLayeredPane1.add(jLabel6);
        jLabel6.setBounds(1310, 0, 60, 76);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I05.jpg"))); // NOI18N
        jButton8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(0, 153, 255)));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton8);
        jButton8.setBounds(240, 0, 60, 60);

        addRequesteeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I04.png"))); // NOI18N
        addRequesteeBtn.setToolTipText("Requestees");
        addRequesteeBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(0, 153, 255)));
        addRequesteeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRequesteeBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(addRequesteeBtn);
        addRequesteeBtn.setBounds(180, 0, 60, 60);

        addRequestorBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I07.jpg"))); // NOI18N
        addRequestorBtn.setToolTipText("Requestors");
        addRequestorBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(0, 153, 255)));
        addRequestorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRequestorBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(addRequestorBtn);
        addRequestorBtn.setBounds(120, 0, 60, 60);

        addBloodTypesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I08.png"))); // NOI18N
        addBloodTypesBtn.setToolTipText("Blood Types");
        addBloodTypesBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(0, 153, 255)));
        addBloodTypesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBloodTypesBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(addBloodTypesBtn);
        addBloodTypesBtn.setBounds(60, 0, 60, 60);

        addBloodGroupBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I02.png"))); // NOI18N
        addBloodGroupBtn.setToolTipText("Blood Groups");
        addBloodGroupBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(0, 153, 255)));
        addBloodGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBloodGroupBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(addBloodGroupBtn);
        addBloodGroupBtn.setBounds(0, 0, 60, 60);

        jButton2.setFont(new java.awt.Font("Monotype Corsiva", 1, 20)); // NOI18N
        jButton2.setText("View Daily Issue/Requests");
        jButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 0, 255), new java.awt.Color(153, 255, 255), new java.awt.Color(0, 102, 255)));
        jLayeredPane1.add(jButton2);
        jButton2.setBounds(1070, 0, 240, 60);

        jButton33.setBackground(new java.awt.Color(255, 0, 51));
        jButton33.setText("Blood Discard");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton33);
        jButton33.setBounds(0, 430, 200, 30);

        jLabel5.setFont(new java.awt.Font("Monotype Corsiva", 3, 18)); // NOI18N
        jLabel5.setText("Statistics");
        jLayeredPane1.add(jLabel5);
        jLabel5.setBounds(60, 280, 140, 21);
        jLayeredPane1.add(jSeparator1);
        jSeparator1.setBounds(0, 300, 200, 2);

        jButton34.setText("Blood Return");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton34);
        jButton34.setBounds(0, 340, 200, 30);

        jButton39.setBackground(new java.awt.Color(255, 0, 51));
        jButton39.setText("Blood Issue");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton39);
        jButton39.setBounds(0, 370, 200, 30);

        jButton40.setText("Discarded Blood Graph");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton40);
        jButton40.setBounds(0, 630, 200, 30);

        jLabel7.setFont(new java.awt.Font("Monotype Corsiva", 3, 18)); // NOI18N
        jLabel7.setText("Charts");
        jLayeredPane1.add(jLabel7);
        jLabel7.setBounds(60, 600, 140, 21);
        jLayeredPane1.add(jSeparator2);
        jSeparator2.setBounds(0, 620, 200, 2);

        jButton41.setText("Blood Recieval");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton41);
        jButton41.setBounds(0, 400, 200, 30);

        jButton25.setText("Blood Return");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton25);
        jButton25.setBounds(0, 160, 200, 30);
        jLayeredPane1.add(jSeparator3);
        jSeparator3.setBounds(0, 230, 200, 2);

        jLabel8.setFont(new java.awt.Font("Monotype Corsiva", 3, 18)); // NOI18N
        jLabel8.setText("Items");
        jLayeredPane1.add(jLabel8);
        jLabel8.setBounds(70, 210, 130, 21);

        jButton36.setText("Item Stock");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton36);
        jButton36.setBounds(0, 240, 200, 30);

        jButton18.setBackground(new java.awt.Color(255, 0, 51));
        jButton18.setText("Blood Stock");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton18);
        jButton18.setBounds(0, 310, 200, 30);
        jLayeredPane1.add(jSeparator4);
        jSeparator4.setBounds(0, 500, 200, 2);

        jLabel9.setFont(new java.awt.Font("Monotype Corsiva", 3, 18)); // NOI18N
        jLabel9.setText("Other");
        jLayeredPane1.add(jLabel9);
        jLabel9.setBounds(70, 480, 130, 21);

        jButton35.setText("Issue Register");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton35);
        jButton35.setBounds(0, 520, 200, 30);
        jLayeredPane1.add(jSeparator5);
        jSeparator5.setBounds(0, 90, 200, 2);

        jLabel10.setFont(new java.awt.Font("Monotype Corsiva", 3, 18)); // NOI18N
        jLabel10.setText("Data Entry");
        jLayeredPane1.add(jLabel10);
        jLabel10.setBounds(50, 70, 140, 21);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1370, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        try {
            StockBalance stock = new StockBalance();
            stock.setClosable(true);
            NurseDesktop.add(stock);
            stock.setBounds(0, 0, 1190, 650);
            NurseDesktop.setRequestFocusEnabled(true);
            stock.setMaximum(true);
            Dimension desktopSize = NurseDesktop.getSize();
            Dimension jInternalFrameSize = stock.getSize();
            stock.setLocation((desktopSize.width - jInternalFrameSize.width) / 2, 0);
            stock.show();
        } catch (PropertyVetoException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }

}//GEN-LAST:event_jButton18ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        BloodReturn returnedBlood = new BloodReturn();
        returnedBlood.setClosable(true);
        NurseDesktop.add(returnedBlood);
        NurseDesktop.setRequestFocusEnabled(true);
        returnedBlood.show();


}//GEN-LAST:event_jButton25ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        IssueRegister issue = new IssueRegister(NurseDesktop.getSize());

        issue.setClosable(true);
        issue.setMaximizable(true);
        NurseDesktop.add(issue);
        NurseDesktop.setRequestFocusEnabled(true);
        issue.show();
}//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        ItemManagement items = new ItemManagement();

        items.setClosable(true);
        NurseDesktop.add(items);
        NurseDesktop.setRequestFocusEnabled(true);
        items.show();
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        BloodPacketForm packetForm = new BloodPacketForm();

        packetForm.setClosable(true);
        packetForm.setMaximizable(true);
        NurseDesktop.add(packetForm);
        NurseDesktop.setRequestFocusEnabled(true);
        packetForm.show();

    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        BloodRecieval donors = new BloodRecieval(this);
        donors.setClosable(true);
        donors.setMaximizable(true);
        NurseDesktop.add(donors);
        NurseDesktop.setRequestFocusEnabled(true);
        donors.show();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void bloodReturnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodReturnBtnActionPerformed
        try {
            ChangePassword p = new ChangePassword();
            p.setClosable(true);
            NurseDesktop.add(p);
            p.show();
        } catch (IOException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bloodReturnBtnActionPerformed

    private void addRequesteeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRequesteeBtnActionPerformed
        AddRequestee requestee = new AddRequestee();
        requestee.setClosable(true);
        NurseDesktop.add(requestee);
        NurseDesktop.setRequestFocusEnabled(true);
        requestee.show();
    }//GEN-LAST:event_addRequesteeBtnActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        AddRequester requester = new AddRequester();
        requester.setClosable(true);
        NurseDesktop.add(requester);
        NurseDesktop.setRequestFocusEnabled(true);
        requester.show();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void addBloodTypesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBloodTypesBtnActionPerformed
        AddBloodTypes bloodTypeForm = new AddBloodTypes();
        bloodTypeForm.setClosable(true);
        NurseDesktop.add(bloodTypeForm);
        NurseDesktop.setRequestFocusEnabled(true);
        bloodTypeForm.show();
    }//GEN-LAST:event_addBloodTypesBtnActionPerformed

    private void addRequestorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRequestorBtnActionPerformed
        AddHospitalWard requestor = new AddHospitalWard();
        requestor.setClosable(true); 
        NurseDesktop.add(requestor);
        NurseDesktop.setRequestFocusEnabled(true);
        requestor.show();
    }//GEN-LAST:event_addRequestorBtnActionPerformed

    private void addBloodGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBloodGroupBtnActionPerformed
        AddBloodGroups bloodGroupForm = new AddBloodGroups();
        bloodGroupForm.setClosable(true);
        NurseDesktop.add(bloodGroupForm);
        NurseDesktop.setRequestFocusEnabled(true);
        bloodGroupForm.show();
    }//GEN-LAST:event_addBloodGroupBtnActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        DiscardedBlood discardedBlood = new DiscardedBlood();
        discardedBlood.setClosable(true);
        discardedBlood.setMaximizable(true);
        NurseDesktop.add(discardedBlood);
        NurseDesktop.setRequestFocusEnabled(true);
        discardedBlood.show();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        dtm = new DefaultTableModel(title, 0);
        expiredBloodPacketsTable.setModel(dtm);

        try {
            ResultSet rst = BloodPacketDA.getExpiredBloodPackets(sqldate);
            String packetID = null;
            String bloodType = null;
            String bloodGroup = null;
            int count = 0;
            while (rst.next()) {
                packetID = rst.getString("packetID");
                bloodType = rst.getString("bloodType");
                bloodGroup = rst.getString("bloodGroup");
                String[] ar = {packetID, bloodType, bloodGroup};
                dtm.addRow(ar);
                count++;
            }
            totalTxt.setText("" + count);
            setDailyStockBalance();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void discardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardBtnActionPerformed
        int row = expiredBloodPacketsTable.getSelectedRow();

        if (row >= 0) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to discard the selected Blood Packet?", "Discard Blood Packet", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    String packID = "" + dtm.getValueAt(row, 0);
                    int discarded = BloodPacketDA.discardPacket(packID, sqldate);
                    if (discarded == 1) {
                        JOptionPane.showMessageDialog(null, "Blood packet discarded succesfully!");
                        dtm = new DefaultTableModel(title, 0);
                        expiredBloodPacketsTable.setModel(dtm);

                        try {
                            ResultSet rst = BloodPacketDA.getExpiredBloodPackets(sqldate);
                            String packetID = null;
                            String bloodType = null;
                            String bloodGroup = null;
                            int count = 0;
                            while (rst.next()) {
                                packetID = rst.getString("packetID");
                                bloodType = rst.getString("bloodType");
                                bloodGroup = rst.getString("bloodGroup");
                                String[] ar = {packetID, bloodType, bloodGroup};
                                dtm.addRow(ar);
                                count++;
                            }
                            totalTxt.setText("" + count);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        setDailyStockBalance();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error while discarding blood packet!");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }

    }//GEN-LAST:event_discardBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ComponentsStock stock = new ComponentsStock();
        stock.setClosable(true);
        NurseDesktop.add(stock);
        NurseDesktop.setRequestFocusEnabled(true);
        stock.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        BloodCollectionChartInterface chart = new BloodCollectionChartInterface();
        chart.setClosable(true);
        NurseDesktop.add(chart);
        NurseDesktop.setRequestFocusEnabled(true);
        chart.show();
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    //                try {
                    //                    //UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                    //                    UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
                    //                    //MedicalOfficer bbms = new MedicalOfficer();
                    //                    //bbms.setVisible(true);
                    //                    new Nurse().setVisible(true);
                    //                } catch (ParseException ex) {
                    //                    Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
                    //                } catch (UnsupportedLookAndFeelException ex) {
                    //                    Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
                    //                } catch (IOException ex) {
                    //                    Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
                    //                }
                    new Nurse().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane NurseDesktop;
    private javax.swing.JButton addBloodGroupBtn;
    private javax.swing.JButton addBloodTypesBtn;
    private javax.swing.JButton addRequesteeBtn;
    private javax.swing.JButton addRequestorBtn;
    private javax.swing.JButton bloodReturnBtn;
    private javax.swing.JTable bloodStockTable;
    private javax.swing.JTable componentStockTable;
    private javax.swing.JButton discardBtn;
    private javax.swing.JTable expiredBloodPacketsTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JTextField totalTxt;
    // End of variables declaration//GEN-END:variables
}
