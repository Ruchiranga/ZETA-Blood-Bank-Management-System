/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ComponentsStock.java
 *
 * Created on Sep 7, 2014, 5:43:16 PM
 */
package gui.Anu;

import data_access.anu.BloodPacketDA;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anuradha
 */
public class ComponentsStock extends javax.swing.JInternalFrame {

    Calendar currenttime = Calendar.getInstance();
    java.sql.Date sqldate = new java.sql.Date((currenttime.getTime()).getTime());
    /** Creates new form ComponentsStock */
    public ComponentsStock() {
        initComponents();
        setData();
    }

    private void setData() {
        try {
            /* Inhouse blood and components */
            int freshBloodInhouse = BloodPacketDA.getInhouseCollectedBlood("Fresh blood", sqldate);
            bloodInhouseTxt.setText("" + freshBloodInhouse);
            int plateletsInhouse = BloodPacketDA.getInhouseCollectedBlood("Platelets", sqldate);
            plateletsInhouseTxt.setText("" + plateletsInhouse);
            int ffpInhouse = BloodPacketDA.getInhouseCollectedBlood("FFP", sqldate);
            ffpInhouseTxt.setText("" + ffpInhouse);
            int cryoInhouse = BloodPacketDA.getInhouseCollectedBlood("CRYO", sqldate);
            cryoInhouseStock.setText("" + cryoInhouse);
            int plasmaInhouse = BloodPacketDA.getInhouseCollectedBlood("Plasma/CSP", sqldate);
            plasmaInhouseTxt.setText("" + plasmaInhouse);
            /* Mobile blood and components */
            int freshBloodMobile = BloodPacketDA.getMobileCollectedBlood("Fresh blood", sqldate);
            bloodMobileTxt.setText("" + freshBloodMobile);
            int plateletsMobile = BloodPacketDA.getMobileCollectedBlood("Platelets", sqldate);
            plateletsMobileTxt.setText("" + plateletsMobile);
            int ffpMobile = BloodPacketDA.getMobileCollectedBlood("FFP", sqldate);
            ffpMobileTxt.setText("" + ffpMobile);
            int cryoMobile = BloodPacketDA.getMobileCollectedBlood("CRYO", sqldate);
            cryoMobileTxt.setText("" + cryoMobile);
            int plasmaMobile = BloodPacketDA.getMobileCollectedBlood("Plasma/CSP", sqldate);
            plasmaMobileTxt.setText("" + plasmaMobile);
            /* Recieved blood and components */
            int freshBloodRecieved = BloodPacketDA.getRecievedBlood("Fresh blood", sqldate);
            bloodRecievedTxt.setText("" + freshBloodRecieved);
            int plateletsRecieved = BloodPacketDA.getRecievedBlood("Platelets", sqldate);
            palateletsRecievedText.setText("" + plateletsRecieved);
            int ffpRecieved = BloodPacketDA.getRecievedBlood("FFP", sqldate);
            ffpRecievedtext.setText("" + ffpRecieved);
            int cryoRecieved = BloodPacketDA.getRecievedBlood("CRYO", sqldate);
            cryoRecievedText.setText("" + cryoRecieved);
            int plasmaRecieved = BloodPacketDA.getRecievedBlood("Plasma/CSP", sqldate);
            plasmarecievedText.setText("" + plasmaRecieved);
            /* Returned blood and components */
            int freshBloodReturned = BloodPacketDA.getReturnedBlood("Fresh blood", sqldate);
            bloodReturnTxt.setText("" + freshBloodReturned);
            int plateletsReturned = BloodPacketDA.getReturnedBlood("Platelets", sqldate);
            pleteletsReturnTxt.setText("" + plateletsReturned);
            int ffpRetruned = BloodPacketDA.getReturnedBlood("FFP", sqldate);
            ffpReturnTxt.setText("" + ffpRetruned);
            int cryoReturned = BloodPacketDA.getReturnedBlood("CRYO", sqldate);
            cryoReturnTxt.setText("" + cryoReturned);
            int plasmaReturned = BloodPacketDA.getReturnedBlood("Plasma/CSP", sqldate);
            plasmaReturnTxt.setText("" + plasmaReturned);
            /* Issued blood and components */
            int freshBloodIssued = BloodPacketDA.getIssuedBlood("Fresh blood", sqldate);
            bloodIssueTxt.setText("" + freshBloodIssued);
            int plateletsIssued = BloodPacketDA.getIssuedBlood("Platelets", sqldate);
            plateletsIssueTxt.setText("" + plateletsIssued);
            int ffpIssued = BloodPacketDA.getIssuedBlood("FFP", sqldate);
            ffpIssueTxt.setText("" + ffpIssued);
            int cryoIssued = BloodPacketDA.getIssuedBlood("CRYO", sqldate);
            cryoIssueTxt.setText("" + cryoIssued);
            int plasmaIssued = BloodPacketDA.getIssuedBlood("Plasma/CSP", sqldate);
            plasmaIssueTxt.setText("" + plasmaIssued);
            /* Discarded blood and components */
            int freshBloodDiscarded = BloodPacketDA.getDiscardedBlood("Fresh blood", sqldate);
            bloodDiscardTxt.setText("" + freshBloodDiscarded);
            int plateletsDiscarded = BloodPacketDA.getDiscardedBlood("Platelets", sqldate);
            plateletsDiscardTxt.setText("" + plateletsDiscarded);
            int ffpDiscarded = BloodPacketDA.getDiscardedBlood("FFP", sqldate);
            ffpDiscardTxt.setText("" + ffpDiscarded);
            int cryoDiscarded = BloodPacketDA.getDiscardedBlood("CRYO", sqldate);
            cryoDiscardTxt.setText("" + cryoDiscarded);
            int plasmaDiscarded = BloodPacketDA.getDiscardedBlood("Plasma/CSP", sqldate);
            plasmaDiscardTxt.setText("" + plasmaDiscarded);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComponentsStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ComponentsStock.class.getName()).log(Level.SEVERE, null, ex);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        bloodBalanceTxt = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        bloodInhouseTxt = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        bloodMobileTxt = new javax.swing.JTextField();
        bloodTot1Txt = new javax.swing.JTextField();
        bloodReturnTxt = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        bloodTot2Txt = new javax.swing.JTextField();
        bloodIssueTxt = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        bloodTot3Txt = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        bloodDiscardTxt = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        bloodTotalTxt = new javax.swing.JTextField();
        bloodRecievedTxt = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        plateletsBanlanceTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        plateletsInhouseTxt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        plateletsMobileTxt = new javax.swing.JTextField();
        pleteletsTot1Txt = new javax.swing.JTextField();
        pleteletsReturnTxt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        plateletsTot2Txt = new javax.swing.JTextField();
        plateletsIssueTxt = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        plateletsTot3Txt = new javax.swing.JTextField();
        plateletsDiscardTxt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        plateletsTotalTxt = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        palateletsRecievedText = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        ffpBalanceTxt = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        ffpInhouseTxt = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        ffpMobileTxt = new javax.swing.JTextField();
        ffpTot1Txt = new javax.swing.JTextField();
        ffpReturnTxt = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        ffpTot2Txt = new javax.swing.JTextField();
        ffpIssueTxt = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        ffpTot3Txt = new javax.swing.JTextField();
        ffpDiscardTxt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        ffpTotalTxt = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        ffpRecievedtext = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        cryoBalanceStock = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        cryoInhouseStock = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        cryoMobileTxt = new javax.swing.JTextField();
        cryoTot1txt = new javax.swing.JTextField();
        cryoReturnTxt = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        cryoTot2Txt = new javax.swing.JTextField();
        cryoIssueTxt = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        cryoTot3Txt = new javax.swing.JTextField();
        cryoDiscardTxt = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        cryoTotalTxt = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel36 = new javax.swing.JLabel();
        cryoRecievedText = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        plasmaBalanceTxt = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        plasmaInhouseTxt = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        plasmaMobileTxt = new javax.swing.JTextField();
        plasmaTot1Txt = new javax.swing.JTextField();
        plasmaReturnTxt = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        plasmaTot2Txt = new javax.swing.JTextField();
        plasmaIssueTxt = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        plasmaTot3Txt = new javax.swing.JTextField();
        plasmaDiscardTxt = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        plasmaTotalTxt = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel51 = new javax.swing.JLabel();
        plasmarecievedText = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();

        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Fresh Blood Stock Balance"));

        jLabel42.setText("Balance");

        bloodBalanceTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodBalanceTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodBalanceTxt.setEnabled(false);

        jLabel43.setText("Inhouse");

        bloodInhouseTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodInhouseTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodInhouseTxt.setEnabled(false);

        jLabel44.setText("Mobile");

        bloodMobileTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodMobileTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodMobileTxt.setEnabled(false);

        bloodTot1Txt.setBackground(new java.awt.Color(204, 204, 204));
        bloodTot1Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodTot1Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodTot1Txt.setEnabled(false);

        bloodReturnTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodReturnTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodReturnTxt.setEnabled(false);

        jLabel45.setText("Recieve");

        bloodTot2Txt.setBackground(new java.awt.Color(204, 204, 204));
        bloodTot2Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodTot2Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodTot2Txt.setEnabled(false);

        bloodIssueTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodIssueTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodIssueTxt.setEnabled(false);

        jLabel46.setText("Issue");

        bloodTot3Txt.setBackground(new java.awt.Color(204, 204, 204));
        bloodTot3Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodTot3Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodTot3Txt.setEnabled(false);
        bloodTot3Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodTot3TxtActionPerformed(evt);
            }
        });

        bloodDiscardTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodDiscardTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodDiscardTxt.setEnabled(false);

        jLabel48.setText("Discard");

        jLabel47.setText("Total");

        bloodTotalTxt.setBackground(new java.awt.Color(102, 102, 102));
        bloodTotalTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodTotalTxt.setForeground(new java.awt.Color(255, 255, 255));
        bloodTotalTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodTotalTxt.setEnabled(false);

        bloodRecievedTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        bloodRecievedTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        bloodRecievedTxt.setEnabled(false);

        jLabel49.setText("Return");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(bloodReturnTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bloodTot1Txt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bloodMobileTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bloodInhouseTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bloodBalanceTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                    .addComponent(bloodRecievedTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                    .addComponent(bloodTot2Txt)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(bloodTot3Txt)
                                    .addComponent(bloodIssueTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bloodTotalTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                    .addComponent(bloodDiscardTxt, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(29, 29, 29)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap(262, Short.MAX_VALUE)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(170, 170, 170))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(101, 101, 101)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(69, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(312, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(bloodBalanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bloodInhouseTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bloodMobileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bloodTot1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bloodReturnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bloodRecievedTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bloodTot2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bloodIssueTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bloodTot3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bloodDiscardTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(bloodTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(162, 162, 162)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(218, Short.MAX_VALUE)))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Platelets Stock Balance"));

        jLabel10.setText("Balance");

        plateletsBanlanceTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plateletsBanlanceTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plateletsBanlanceTxt.setEnabled(false);

        jLabel12.setText("Inhouse");

        plateletsInhouseTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plateletsInhouseTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plateletsInhouseTxt.setEnabled(false);

        jLabel14.setText("Mobile");

        plateletsMobileTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plateletsMobileTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plateletsMobileTxt.setEnabled(false);

        pleteletsTot1Txt.setBackground(new java.awt.Color(204, 204, 204));
        pleteletsTot1Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        pleteletsTot1Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pleteletsTot1Txt.setEnabled(false);

        pleteletsReturnTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        pleteletsReturnTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pleteletsReturnTxt.setEnabled(false);

        jLabel17.setText("Return");

        plateletsTot2Txt.setBackground(new java.awt.Color(204, 204, 204));
        plateletsTot2Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plateletsTot2Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plateletsTot2Txt.setEnabled(false);

        plateletsIssueTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plateletsIssueTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plateletsIssueTxt.setEnabled(false);

        jLabel18.setText("Issue");

        plateletsTot3Txt.setBackground(new java.awt.Color(204, 204, 204));
        plateletsTot3Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plateletsTot3Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plateletsTot3Txt.setEnabled(false);

        plateletsDiscardTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plateletsDiscardTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plateletsDiscardTxt.setEnabled(false);

        jLabel19.setText("Discard");

        plateletsTotalTxt.setBackground(new java.awt.Color(102, 102, 102));
        plateletsTotalTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plateletsTotalTxt.setForeground(new java.awt.Color(255, 255, 255));
        plateletsTotalTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plateletsTotalTxt.setEnabled(false);

        jLabel20.setText("Total");

        palateletsRecievedText.setFont(new java.awt.Font("Times New Roman", 1, 14));
        palateletsRecievedText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        palateletsRecievedText.setEnabled(false);

        jLabel28.setText("Recieve");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(96, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel12))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addGap(23, 23, 23)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(14, 14, 14)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(plateletsTotalTxt)
                            .addComponent(plateletsDiscardTxt)
                            .addComponent(plateletsTot3Txt)
                            .addComponent(plateletsIssueTxt)
                            .addComponent(plateletsTot2Txt, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(palateletsRecievedText)
                            .addComponent(pleteletsReturnTxt)
                            .addComponent(pleteletsTot1Txt)
                            .addComponent(plateletsMobileTxt)
                            .addComponent(plateletsInhouseTxt)
                            .addComponent(plateletsBanlanceTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                        .addGap(43, 43, 43))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(plateletsBanlanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plateletsInhouseTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plateletsMobileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pleteletsTot1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pleteletsReturnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(palateletsRecievedText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plateletsTot2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plateletsIssueTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plateletsTot3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plateletsDiscardTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(plateletsTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        ffpBalanceTxt.setBorder(javax.swing.BorderFactory.createTitledBorder("FFP Stock Balance"));

        jLabel21.setText("Balance");

        jTextField21.setFont(new java.awt.Font("Times New Roman", 1, 14));
        jTextField21.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField21.setEnabled(false);

        jLabel22.setText("Inhouse");

        ffpInhouseTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpInhouseTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpInhouseTxt.setEnabled(false);

        jLabel23.setText("Mobile");

        ffpMobileTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpMobileTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpMobileTxt.setEnabled(false);

        ffpTot1Txt.setBackground(new java.awt.Color(204, 204, 204));
        ffpTot1Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpTot1Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpTot1Txt.setEnabled(false);

        ffpReturnTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpReturnTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpReturnTxt.setEnabled(false);

        jLabel24.setText("Return");

        ffpTot2Txt.setBackground(new java.awt.Color(204, 204, 204));
        ffpTot2Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpTot2Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpTot2Txt.setEnabled(false);

        ffpIssueTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpIssueTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpIssueTxt.setEnabled(false);

        jLabel25.setText("Issue");

        ffpTot3Txt.setBackground(new java.awt.Color(204, 204, 204));
        ffpTot3Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpTot3Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpTot3Txt.setEnabled(false);

        ffpDiscardTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpDiscardTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpDiscardTxt.setEnabled(false);

        jLabel26.setText("Discard");

        ffpTotalTxt.setBackground(new java.awt.Color(102, 102, 102));
        ffpTotalTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpTotalTxt.setForeground(new java.awt.Color(255, 255, 255));
        ffpTotalTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpTotalTxt.setEnabled(false);

        jLabel27.setText("Total");

        ffpRecievedtext.setFont(new java.awt.Font("Times New Roman", 1, 14));
        ffpRecievedtext.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ffpRecievedtext.setEnabled(false);

        jLabel29.setText("Recieve");

        javax.swing.GroupLayout ffpBalanceTxtLayout = new javax.swing.GroupLayout(ffpBalanceTxt);
        ffpBalanceTxt.setLayout(ffpBalanceTxtLayout);
        ffpBalanceTxtLayout.setHorizontalGroup(
            ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ffpBalanceTxtLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(ffpBalanceTxtLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ffpBalanceTxtLayout.createSequentialGroup()
                            .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(jLabel21))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ffpBalanceTxtLayout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ffpBalanceTxtLayout.createSequentialGroup()
                            .addComponent(jLabel25)
                            .addGap(23, 23, 23)))
                    .addGroup(ffpBalanceTxtLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(25, 25, 25)))
                .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ffpTotalTxt)
                    .addComponent(ffpDiscardTxt)
                    .addComponent(ffpTot3Txt)
                    .addComponent(ffpIssueTxt)
                    .addComponent(ffpTot2Txt)
                    .addComponent(ffpRecievedtext)
                    .addComponent(ffpReturnTxt)
                    .addComponent(ffpTot1Txt)
                    .addComponent(ffpMobileTxt)
                    .addComponent(ffpInhouseTxt)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        ffpBalanceTxtLayout.setVerticalGroup(
            ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ffpBalanceTxtLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ffpBalanceTxtLayout.createSequentialGroup()
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ffpInhouseTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ffpMobileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ffpTot1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ffpReturnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ffpRecievedtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ffpTot2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ffpIssueTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ffpTot3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ffpDiscardTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ffpBalanceTxtLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(ffpBalanceTxtLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(ffpTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(94, 94, 94)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("CRYO Stock Balance"));

        jLabel30.setText("Balance");

        cryoBalanceStock.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoBalanceStock.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoBalanceStock.setEnabled(false);

        jLabel31.setText("Inhouse");

        cryoInhouseStock.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoInhouseStock.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoInhouseStock.setEnabled(false);

        jLabel32.setText("Mobile");

        cryoMobileTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoMobileTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoMobileTxt.setEnabled(false);

        cryoTot1txt.setBackground(new java.awt.Color(204, 204, 204));
        cryoTot1txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoTot1txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoTot1txt.setEnabled(false);

        cryoReturnTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoReturnTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoReturnTxt.setEnabled(false);

        jLabel33.setText("Return");

        cryoTot2Txt.setBackground(new java.awt.Color(204, 204, 204));
        cryoTot2Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoTot2Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoTot2Txt.setEnabled(false);

        cryoIssueTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoIssueTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoIssueTxt.setEnabled(false);

        jLabel34.setText("Issue");

        cryoTot3Txt.setBackground(new java.awt.Color(204, 204, 204));
        cryoTot3Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoTot3Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoTot3Txt.setEnabled(false);

        cryoDiscardTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoDiscardTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoDiscardTxt.setEnabled(false);

        jLabel35.setText("Discard");

        cryoTotalTxt.setBackground(new java.awt.Color(102, 102, 102));
        cryoTotalTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoTotalTxt.setForeground(new java.awt.Color(255, 255, 255));
        cryoTotalTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoTotalTxt.setEnabled(false);

        jLabel36.setText("Total");

        cryoRecievedText.setFont(new java.awt.Font("Times New Roman", 1, 14));
        cryoRecievedText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        cryoRecievedText.setEnabled(false);

        jLabel52.setText("Recieve");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addContainerGap())
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                    .addComponent(jLabel30))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(23, 23, 23))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cryoRecievedText, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cryoReturnTxt)
                                .addComponent(cryoTot1txt)
                                .addComponent(cryoMobileTxt)
                                .addComponent(cryoInhouseStock)
                                .addComponent(cryoBalanceStock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cryoIssueTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cryoTot2Txt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cryoDiscardTxt)
                                .addComponent(cryoTot3Txt, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                .addComponent(cryoTotalTxt, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(41, 41, 41))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(cryoBalanceStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cryoInhouseStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cryoMobileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cryoTot1txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cryoReturnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cryoRecievedText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cryoTot2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cryoIssueTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cryoTot3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cryoDiscardTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cryoTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Plasma/CSP Stock Balance"));

        jLabel37.setText("Balance");

        plasmaBalanceTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaBalanceTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaBalanceTxt.setEnabled(false);

        jLabel38.setText("Inhouse");

        plasmaInhouseTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaInhouseTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaInhouseTxt.setEnabled(false);

        jLabel39.setText("Mobile");

        plasmaMobileTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaMobileTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaMobileTxt.setEnabled(false);

        plasmaTot1Txt.setBackground(new java.awt.Color(204, 204, 204));
        plasmaTot1Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaTot1Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaTot1Txt.setEnabled(false);

        plasmaReturnTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaReturnTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaReturnTxt.setEnabled(false);

        jLabel40.setText("Return");

        plasmaTot2Txt.setBackground(new java.awt.Color(204, 204, 204));
        plasmaTot2Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaTot2Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaTot2Txt.setEnabled(false);

        plasmaIssueTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaIssueTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaIssueTxt.setEnabled(false);

        jLabel41.setText("Issue");

        plasmaTot3Txt.setBackground(new java.awt.Color(204, 204, 204));
        plasmaTot3Txt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaTot3Txt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaTot3Txt.setEnabled(false);

        plasmaDiscardTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaDiscardTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaDiscardTxt.setEnabled(false);

        jLabel50.setText("Discard");

        plasmaTotalTxt.setBackground(new java.awt.Color(102, 102, 102));
        plasmaTotalTxt.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmaTotalTxt.setForeground(new java.awt.Color(255, 255, 255));
        plasmaTotalTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmaTotalTxt.setEnabled(false);

        jLabel51.setText("Total");

        plasmarecievedText.setFont(new java.awt.Font("Times New Roman", 1, 14));
        plasmarecievedText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        plasmarecievedText.setEnabled(false);

        jLabel53.setText("Recieve");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(plasmaTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(32, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(plasmaTot3Txt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(plasmaIssueTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(plasmaTot2Txt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(plasmarecievedText, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(plasmaReturnTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(plasmaTot1Txt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(plasmaMobileTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(plasmaInhouseTxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(plasmaBalanceTxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                            .addGap(32, 32, 32))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                            .addComponent(plasmaDiscardTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(plasmaBalanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(plasmaInhouseTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plasmaMobileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plasmaTot1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plasmaReturnTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plasmarecievedText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plasmaTot2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plasmaIssueTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plasmaTot3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plasmaDiscardTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(plasmaTotalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ffpBalanceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ffpBalanceTxt, javax.swing.GroupLayout.Alignment.LEADING, 0, 409, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Daily Blood and Components Stock Balance", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1097, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bloodTot3TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodTot3TxtActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_bloodTot3TxtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bloodBalanceTxt;
    private javax.swing.JTextField bloodDiscardTxt;
    private javax.swing.JTextField bloodInhouseTxt;
    private javax.swing.JTextField bloodIssueTxt;
    private javax.swing.JTextField bloodMobileTxt;
    private javax.swing.JTextField bloodRecievedTxt;
    private javax.swing.JTextField bloodReturnTxt;
    private javax.swing.JTextField bloodTot1Txt;
    private javax.swing.JTextField bloodTot2Txt;
    private javax.swing.JTextField bloodTot3Txt;
    private javax.swing.JTextField bloodTotalTxt;
    private javax.swing.JTextField cryoBalanceStock;
    private javax.swing.JTextField cryoDiscardTxt;
    private javax.swing.JTextField cryoInhouseStock;
    private javax.swing.JTextField cryoIssueTxt;
    private javax.swing.JTextField cryoMobileTxt;
    private javax.swing.JTextField cryoRecievedText;
    private javax.swing.JTextField cryoReturnTxt;
    private javax.swing.JTextField cryoTot1txt;
    private javax.swing.JTextField cryoTot2Txt;
    private javax.swing.JTextField cryoTot3Txt;
    private javax.swing.JTextField cryoTotalTxt;
    private javax.swing.JPanel ffpBalanceTxt;
    private javax.swing.JTextField ffpDiscardTxt;
    private javax.swing.JTextField ffpInhouseTxt;
    private javax.swing.JTextField ffpIssueTxt;
    private javax.swing.JTextField ffpMobileTxt;
    private javax.swing.JTextField ffpRecievedtext;
    private javax.swing.JTextField ffpReturnTxt;
    private javax.swing.JTextField ffpTot1Txt;
    private javax.swing.JTextField ffpTot2Txt;
    private javax.swing.JTextField ffpTot3Txt;
    private javax.swing.JTextField ffpTotalTxt;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField palateletsRecievedText;
    private javax.swing.JTextField plasmaBalanceTxt;
    private javax.swing.JTextField plasmaDiscardTxt;
    private javax.swing.JTextField plasmaInhouseTxt;
    private javax.swing.JTextField plasmaIssueTxt;
    private javax.swing.JTextField plasmaMobileTxt;
    private javax.swing.JTextField plasmaReturnTxt;
    private javax.swing.JTextField plasmaTot1Txt;
    private javax.swing.JTextField plasmaTot2Txt;
    private javax.swing.JTextField plasmaTot3Txt;
    private javax.swing.JTextField plasmaTotalTxt;
    private javax.swing.JTextField plasmarecievedText;
    private javax.swing.JTextField plateletsBanlanceTxt;
    private javax.swing.JTextField plateletsDiscardTxt;
    private javax.swing.JTextField plateletsInhouseTxt;
    private javax.swing.JTextField plateletsIssueTxt;
    private javax.swing.JTextField plateletsMobileTxt;
    private javax.swing.JTextField plateletsTot2Txt;
    private javax.swing.JTextField plateletsTot3Txt;
    private javax.swing.JTextField plateletsTotalTxt;
    private javax.swing.JTextField pleteletsReturnTxt;
    private javax.swing.JTextField pleteletsTot1Txt;
    // End of variables declaration//GEN-END:variables
}
