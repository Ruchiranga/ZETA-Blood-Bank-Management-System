/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DiscardedBlood.java
 *
 * Created on Sep 4, 2014, 10:19:02 AM
 */
package view.statistics;

import controller.common.TableResizer;
import controller.blood_stock_management.BloodPacketController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anuradha
 */
public class DiscardedBlood extends javax.swing.JInternalFrame {

    String[] title = {"PacketID", "Blood Type", "Blood Group", "Discarded Date"};
    DefaultTableModel dtm = new DefaultTableModel(title, 0);

    /** Creates new form DiscardedBlood */
    public DiscardedBlood() {
        
        try {
            initComponents();
            
            FileInputStream imgStream = null;
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            imgStream = new FileInputStream(imgfile);
            BufferedImage bi = ImageIO.read(imgStream);
            ImageIcon myImg = new ImageIcon(bi);
            this.setFrameIcon(myImg);
            setTitle("Item Management");
            
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
            durationRadioBtn.setSelected(false);
            Calendar currenttime = Calendar.getInstance();
            java.util.Date today = new java.util.Date((currenttime.getTime()).getTime());
            
            
            
            startDateCalendar.setDate(today);
            endDateCalendar.setDate(today);
            dtm = new DefaultTableModel(title, 0);
            discardedBloodTable.setModel(dtm);
            yearRadioBtn.setSelected(true);
            yearCalendar.setEnabled(true);
            
            startDateCalendar.setEnabled(false);
            endDateCalendar.setEnabled(false);
        } catch (IOException ex) {
            Logger.getLogger(DiscardedBlood.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void displayDiscardedBloodPacketsByDuration() {
        java.util.Date dateS = startDateCalendar.getDate();
        java.util.Date dateE = endDateCalendar.getDate();

        if (dateS != null && dateE != null) {
            try {
                dtm = new DefaultTableModel(title, 0);
                discardedBloodTable.setModel(dtm);
                /*Start date*/
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String startDate = df.format(dateS);
                java.sql.Date sqlDateS = new java.sql.Date(dateS.getTime());
                /*End date*/
                String endDate = df.format(dateE);
                java.sql.Date sqlDateE = new java.sql.Date(dateE.getTime());
                ResultSet rst = BloodPacketController.getDiscardedBloodPacketsByDuration(sqlDateS, sqlDateE);

                String packetID = null;
                String bloodType = null;
                String bloodGroup = null;
                Date discardedDate = null;
                int count = 0;
                while (rst.next()) {
                    packetID = rst.getString("packetID");
                    bloodType = rst.getString("bloodType");
                    bloodGroup = rst.getString("bloodGroup");
                    discardedDate = rst.getDate("discardedDate");
                    String[] ar = {packetID, bloodType, bloodGroup, "" + discardedDate};
                    dtm.addRow(ar);
                    count++;
                }
                totalTxt.setText("" + count);
                TableResizer.resizeColumnWidth(discardedBloodTable);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DiscardedBlood.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DiscardedBlood.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void displayDiscardedBloodPacketsByMonth() {
        int month = monthCalendar.getMonth();

        if (month >= 0) {
            try {
                dtm = new DefaultTableModel(title, 0);
                discardedBloodTable.setModel(dtm);

                ResultSet rst = BloodPacketController.getDiscardedBloodPacketsByMonth(month + 1);

                String packetID = null;
                String bloodType = null;
                String bloodGroup = null;
                Date discardedDate = null;
                int count = 0;
                while (rst.next()) {
                    packetID = rst.getString("packetID");
                    bloodType = rst.getString("bloodType");
                    bloodGroup = rst.getString("bloodGroup");
                    discardedDate = rst.getDate("discardedDate");
                    String[] ar = {packetID, bloodType, bloodGroup, "" + discardedDate};
                    dtm.addRow(ar);
                    count++;
                }
                totalTxt.setText("" + count);

                TableResizer.resizeColumnWidth(discardedBloodTable);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DiscardedBlood.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DiscardedBlood.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void displayDiscardedBloodPacketsByYear() {
        int year = yearCalendar.getYear();

        if (year >= 0) {
            try {
                dtm = new DefaultTableModel(title, 0);
                discardedBloodTable.setModel(dtm);
                
                ResultSet rst = BloodPacketController.getDiscardedBloodPacketsByYear(year);

                String packetID = null;
                String bloodType = null;
                String bloodGroup = null;
                Date discardedDate = null;
                int count = 0;
                while (rst.next()) {
                    packetID = rst.getString("packetID");
                    bloodType = rst.getString("bloodType");
                    bloodGroup = rst.getString("bloodGroup");
                    discardedDate = rst.getDate("discardedDate");
                    String[] ar = {packetID, bloodType, bloodGroup, "" + discardedDate};
                    dtm.addRow(ar);
                    count++;
                }
                totalTxt.setText("" + count);

                TableResizer.resizeColumnWidth(discardedBloodTable);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DiscardedBlood.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DiscardedBlood.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        durationRadioBtn = new javax.swing.JRadioButton();
        monthRadioBtn = new javax.swing.JRadioButton();
        yearRadioBtn = new javax.swing.JRadioButton();
        RefreshBtn = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        monthCalendar = new com.toedter.calendar.JMonthChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        startDateCalendar = new com.toedter.calendar.JDateChooser();
        endDateCalendar = new com.toedter.calendar.JDateChooser();
        totalTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        yearCalendar = new com.toedter.calendar.JYearChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        discardedBloodTable = new javax.swing.JTable();
        jLabel50 = new javax.swing.JLabel();

        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Discarded Blood"));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Way of Navigating"));

        buttonGroup1.add(durationRadioBtn);
        durationRadioBtn.setText("By Duration");
        durationRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                durationRadioBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(monthRadioBtn);
        monthRadioBtn.setText("By month");
        monthRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthRadioBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(yearRadioBtn);
        yearRadioBtn.setText("By Year");
        yearRadioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearRadioBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(durationRadioBtn)
                .addGap(18, 18, 18)
                .addComponent(monthRadioBtn)
                .addGap(18, 18, 18)
                .addComponent(yearRadioBtn)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(durationRadioBtn)
                    .addComponent(monthRadioBtn)
                    .addComponent(yearRadioBtn))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        RefreshBtn.setText("Refresh");
        RefreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshBtnActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Month"));

        jLabel5.setText("Month");

        monthCalendar.setEnabled(false);
        monthCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                monthCalendarPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(monthCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(294, 294, 294))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Time Duration"));
        jPanel2.setEnabled(false);

        jLabel1.setText("Start Date");

        jLabel3.setText("End Date");

        startDateCalendar.setDateFormatString("yyyy-MM-dd");
        startDateCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                startDateCalendarPropertyChange(evt);
            }
        });

        endDateCalendar.setDateFormatString("yyyy-MM-dd");
        endDateCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                endDateCalendarPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startDateCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endDateCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(endDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(262, 262, 262))
        );

        totalTxt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        totalTxt.setEnabled(false);

        jLabel6.setText("Total");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Year"));

        jLabel4.setText("Year");

        yearCalendar.setEnabled(false);
        yearCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yearCalendarPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(yearCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(294, 294, 294))
        );

        discardedBloodTable.setModel(dtm);
        jScrollPane1.setViewportView(discardedBloodTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RefreshBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RefreshBtn)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jLabel50.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel50.setText("Discarded Blood Stock");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Discarded Blood Stock", jPanel11);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void durationRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_durationRadioBtnActionPerformed
        startDateCalendar.setEnabled(true);
        endDateCalendar.setEnabled(true);
        monthCalendar.setEnabled(false);
        yearCalendar.setEnabled(false);

        displayDiscardedBloodPacketsByDuration();
}//GEN-LAST:event_durationRadioBtnActionPerformed

    private void monthRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthRadioBtnActionPerformed
        startDateCalendar.setEnabled(false);
        endDateCalendar.setEnabled(false);
        monthCalendar.setEnabled(true);
        yearCalendar.setEnabled(false);

        displayDiscardedBloodPacketsByMonth();
}//GEN-LAST:event_monthRadioBtnActionPerformed

    private void yearRadioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearRadioBtnActionPerformed
        startDateCalendar.setEnabled(false);
        endDateCalendar.setEnabled(false);
        monthCalendar.setEnabled(false);
        yearCalendar.setEnabled(true);

        displayDiscardedBloodPacketsByYear();
}//GEN-LAST:event_yearRadioBtnActionPerformed

    private void RefreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshBtnActionPerformed
        if(durationRadioBtn.isSelected()){
            displayDiscardedBloodPacketsByDuration();
        }else if(monthRadioBtn.isSelected()){
            displayDiscardedBloodPacketsByMonth();
        }else if(yearRadioBtn.isSelected()){
            displayDiscardedBloodPacketsByYear();
        }
}//GEN-LAST:event_RefreshBtnActionPerformed

    private void monthCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_monthCalendarPropertyChange
        displayDiscardedBloodPacketsByMonth();
}//GEN-LAST:event_monthCalendarPropertyChange

    private void yearCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yearCalendarPropertyChange
        displayDiscardedBloodPacketsByYear();
}//GEN-LAST:event_yearCalendarPropertyChange

    private void endDateCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_endDateCalendarPropertyChange
        displayDiscardedBloodPacketsByDuration();
    }//GEN-LAST:event_endDateCalendarPropertyChange

    private void startDateCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_startDateCalendarPropertyChange
        displayDiscardedBloodPacketsByDuration();
    }//GEN-LAST:event_startDateCalendarPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RefreshBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTable discardedBloodTable;
    private javax.swing.JRadioButton durationRadioBtn;
    private com.toedter.calendar.JDateChooser endDateCalendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.toedter.calendar.JMonthChooser monthCalendar;
    private javax.swing.JRadioButton monthRadioBtn;
    private com.toedter.calendar.JDateChooser startDateCalendar;
    private javax.swing.JTextField totalTxt;
    private com.toedter.calendar.JYearChooser yearCalendar;
    private javax.swing.JRadioButton yearRadioBtn;
    // End of variables declaration//GEN-END:variables
}
