/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StockBalance.java
 *
 * Created on Jul 28, 2014, 6:21:45 PM
 */
package gui.Anu;

import controller.TableResizer;
import controller.anu.BloodGroupController;
import controller.anu.BloodStockController;
import controller.anu.BloodTypeController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Anuradha
 */
public class StockBalance extends javax.swing.JInternalFrame {

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    String[] bloodStockTitle = {"Blood Group", "Un X matched", "X matched", "Special Reservations", "Under Observation", "Total"};
    DefaultTableModel dailyBloodStockDtm = new DefaultTableModel(bloodStockTitle, 0);

    /**
     * Creates new form StockBalance
     */
    public StockBalance() {
        try {
            initComponents();
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            FileInputStream imgStream = new FileInputStream(imgfile);
            BufferedImage bi = ImageIO.read(imgStream);
            ImageIcon myImg = new ImageIcon(bi);
            this.setFrameIcon(myImg);

            Calendar currenttime = Calendar.getInstance();
            java.util.Date today = new java.util.Date((currenttime.getTime()).getTime());
            DailyBloodStockDate.setDate(today);

            //setDailyBloodStock();
        } catch (IOException ex) {
            Logger.getLogger(StockBalance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDailyBloodStock() throws ParseException {

        dailyBloodStockDtm = new DefaultTableModel(bloodStockTitle, 0);
        DailyBloodStockTable.setModel(dailyBloodStockDtm);

        try {

            /*Date*/
            java.util.Date dateC = DailyBloodStockDate.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateCollection = df.format(dateC);
            java.sql.Date sqlDateC = new java.sql.Date(dateC.getTime());

            System.out.println("" + sqlDateC);

            ResultSet bloodStockRst = BloodStockController.getBloodStockHistory(sqlDateC);

            String[] ungrouped = new String[6];

            int isThere = 0;

            while (bloodStockRst.next()) {
                isThere = 1;
                String bloodGroup = bloodStockRst.getString("BloodGroup");

                if (!bloodGroup.equalsIgnoreCase("UG")) {
                    String[] row = new String[6];
                    row[0] = bloodGroup;
                    row[1] = bloodStockRst.getString("Unx");
                    row[2] = bloodStockRst.getString("x");
                    row[3] = bloodStockRst.getString("Spec_res");
                    row[4] = bloodStockRst.getString("Underobs");
                    row[5] = bloodStockRst.getString("Total");

                    dailyBloodStockDtm.addRow(row);
                } else {
                    ungrouped[0] = bloodGroup;
                    ungrouped[1] = "";
                    ungrouped[2] = "";
                    ungrouped[3] = "";
                    ungrouped[4] = "";
                    //ungrouped[1] = bloodStockRst.getString("Unx");
                    //ungrouped[2] = bloodStockRst.getString("x");
                    //ungrouped[3] = bloodStockRst.getString("Spec_res");
                    //ungrouped[4] = bloodStockRst.getString("Underobs");
                    ungrouped[5] = bloodStockRst.getString("Total");
                }

            }

            if (isThere == 1) {
                int bloodTotal = 0;
                for (int i = 0; i < dailyBloodStockDtm.getRowCount(); i++) {
                    bloodTotal += Integer.parseInt("" + dailyBloodStockDtm.getValueAt(i, 5));
                }

                String[] total = {"Total", "", "", "", "", "" + bloodTotal};
                dailyBloodStockDtm.addRow(total);

                dailyBloodStockDtm.addRow(ungrouped);

                int ungroupedTotal = Integer.parseInt(ungrouped[5]);
                String[] grandTotal = {"", "", "", "", "Grand Total", "" + (bloodTotal + ungroupedTotal)};
                dailyBloodStockDtm.addRow(grandTotal);
            }
            setDailyComponentBalance(sqlDateC);
            setDailyBloodStockDetails(sqlDateC);
            TableResizer.resizeColumnWidth(DailyBloodStockTable);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockBalance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StockBalance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    String[] componentStockTitle = {"Component", "A +ve", "A neg", "B +ve", "B neg", "AB +ve", "AB neg", "O +ve", "O neg", "UG", "Total"};
    DefaultTableModel dailyComponentStockDtm = new DefaultTableModel(componentStockTitle, 0);

    String[] bloodTitle = {"", "Fresh blood", "CRYO", "FFP"};
    Object[][] bloodData = {{"Balance", null, null, null},
    {"Inhouse", null, null, null},
    {"Mobile", null, null, null},
    {"", null, null, null},
    {"Return", null, null, null},
    {"Recieve", null, null, null},
    {"", null, null, null},
    {"Issue", null, null, null},
    {"", null, null, null},
    {"Discard", null, null, null},
    {"Total", null, null, null}};
    DefaultTableModel bloodDtm = new DefaultTableModel(bloodData, bloodTitle);

    String[] bloodTitle2 = {"", "Plasma", "CSP", "Platelets"};
    Object[][] bloodData2 = {{"Balance", null, null, null},
    {"Inhouse", null, null, null},
    {"Mobile", null, null, null},
    {"", null, null, null},
    {"Return", null, null, null},
    {"Recieve", null, null, null},
    {"", null, null, null},
    {"Issue", null, null, null},
    {"", null, null, null},
    {"Discard", null, null, null},
    {"Total", null, null, null}};
    DefaultTableModel bloodDtm2 = new DefaultTableModel(bloodData, bloodTitle);

    private void setDailyBloodStockDetails(java.sql.Date sqlDateC) throws ParseException {

        try {

            ArrayList<String> bloodTypes = new ArrayList<String>();

            ResultSet rstTypes = BloodTypeController.getAllTypes();
            while (rstTypes.next()) {
                bloodTypes.add(rstTypes.getString("BloodType"));
            }

            int typeCount = BloodTypeController.getTypeCount();
            String[] typeTitle1;
            String[] typeTitle2;

            if ((typeCount % 2) == 0) {
                typeTitle1 = new String[(typeCount + 2) / 2 ];
                typeTitle2 = new String[(typeCount + 2) / 2];
            } else {
                typeTitle1 = new String[(typeCount + 2) / 2];
                typeTitle2 = new String[((typeCount + 2) / 2) + 1];
            }

            typeTitle1[0] = "";
            typeTitle2[0] = "";

            int counter = 0;
            for (int i = 1; i < typeTitle1.length; i++) {
                typeTitle1[i] = bloodTypes.get(counter);
                counter++;
            }
            for (int i = 1; i < typeTitle2.length; i++) {
                typeTitle2[i] = bloodTypes.get(counter);
                counter++;
            }

            bloodDtm = new DefaultTableModel(bloodData, typeTitle1);
            DailyBloodDetailTable1.setModel(bloodDtm);
            bloodDtm2 = new DefaultTableModel(bloodData, typeTitle2);
            DailyBloodDetailTable2.setModel(bloodDtm2);

            int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String prevDay = dateFormat.format(DailyBloodStockDate.getDate().getTime() - MILLIS_IN_DAY);
            java.util.Date parsed = dateFormat.parse(prevDay);

            java.sql.Date sqlYesterday = new java.sql.Date(parsed.getTime());

            System.out.println("Yesterday = " + sqlYesterday);

            for (int i = 1; i < typeTitle1.length; i++) {
                //=======================================================================================================
                int balance = BloodStockController.getBalanceStockHistoryByType(sqlYesterday, typeTitle1[i]);

                if (balance >= 0) {
                    bloodDtm.setValueAt("" + balance, 0, i);

                    int inhouse = BloodStockController.getInhouseBloodCount(typeTitle1[i], sqlDateC);
                    bloodDtm.setValueAt("" + inhouse, 1, i);

                    int mobile = BloodStockController.getMobileBloodCount(typeTitle1[i], sqlDateC);
                    bloodDtm.setValueAt("" + mobile, 2, i);

                    bloodDtm.setValueAt("" + (balance + inhouse + mobile), 3, i);

                    int returned = BloodStockController.getReturnBloodCount(typeTitle1[i], sqlDateC);
                    bloodDtm.setValueAt("" + returned, 4, i);

                    int recieved = BloodStockController.getRecievedBloodCount(typeTitle1[i], sqlDateC);
                    bloodDtm.setValueAt("" + recieved, 5, i);

                    bloodDtm.setValueAt("" + (balance + inhouse + mobile + returned + recieved), 6, i);

                    int patientIssued = BloodStockController.getPatientIssuedBloodCount(typeTitle1[i], sqlDateC);
                    int bulkIssued = BloodStockController.getBulkIssuedBloodCount(typeTitle1[i], sqlDateC);

                    int issued = 0;
                    if (patientIssued >= 0 && bulkIssued >= 0) {
                        issued = patientIssued + bulkIssued;
                    }

                    bloodDtm.setValueAt("" + issued, 7, i);

                    bloodDtm.setValueAt("" + (balance + inhouse + mobile + returned + recieved - issued), 8, i);

                    int discarded = BloodStockController.getDiscardedBloodCount(typeTitle1[i], sqlDateC);
                    bloodDtm.setValueAt("" + discarded, 9, i);

                    bloodDtm.setValueAt("" + (balance + inhouse + mobile + returned + recieved - issued - discarded), 10, i);

                }
                //==============================================================================================
            }
            
            for (int i = 1; i < typeTitle2.length; i++) {
                //=======================================================================================================
                int balance = BloodStockController.getBalanceStockHistoryByType(sqlYesterday, typeTitle2[i]);

                if (balance >= 0) {
                    bloodDtm2.setValueAt("" + balance, 0, i);

                    int inhouse = BloodStockController.getInhouseBloodCount(typeTitle2[i], sqlDateC);
                    bloodDtm2.setValueAt("" + inhouse, 1, i);

                    int mobile = BloodStockController.getMobileBloodCount(typeTitle2[i], sqlDateC);
                    bloodDtm2.setValueAt("" + mobile, 2, i);

                    bloodDtm2.setValueAt("" + (balance + inhouse + mobile), 3, i);

                    int returned = BloodStockController.getReturnBloodCount(typeTitle2[i], sqlDateC);
                    bloodDtm2.setValueAt("" + returned, 4, i);

                    int recieved = BloodStockController.getRecievedBloodCount(typeTitle2[i], sqlDateC);
                    bloodDtm2.setValueAt("" + recieved, 5, i);

                    bloodDtm2.setValueAt("" + (balance + inhouse + mobile + returned + recieved), 6, i);

                    int patientIssued = BloodStockController.getPatientIssuedBloodCount(typeTitle2[i], sqlDateC);
                    int bulkIssued = BloodStockController.getBulkIssuedBloodCount(typeTitle2[i], sqlDateC);

                    int issued = 0;
                    if (patientIssued >= 0 && bulkIssued >= 0) {
                        issued = patientIssued + bulkIssued;
                    }

                    bloodDtm2.setValueAt("" + issued, 7, i);

                    bloodDtm2.setValueAt("" + (balance + inhouse + mobile + returned + recieved - issued), 8, i);

                    int discarded = BloodStockController.getDiscardedBloodCount(typeTitle2[i], sqlDateC);
                    bloodDtm2.setValueAt("" + discarded, 9, i);

                    bloodDtm2.setValueAt("" + (balance + inhouse + mobile + returned + recieved - issued - discarded), 10, i);

                }
                //==============================================================================================
            }
            TableResizer.resizeColumnWidth(DailyBloodDetailTable1);
            TableResizer.resizeColumnWidth(DailyBloodDetailTable2);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockBalance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StockBalance.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void setDailyComponentBalance(java.sql.Date date) throws ClassNotFoundException, SQLException {

        int groupCount = BloodGroupController.getGroupCount();
        String[] componentStockTitle = new String[groupCount + 2];
        String[] groupName = new String[groupCount];
        
        componentStockTitle[0] = "Component";
        componentStockTitle[groupCount + 1] = "Total";
        int groupCounter = 1;

        ResultSet rstGroups = BloodGroupController.getAllGroups();
        while (rstGroups.next()) {
            String group = rstGroups.getString("GroupName");
            componentStockTitle[groupCounter] = group;
            groupName[groupCounter - 1] = group;
            groupCounter++;
        }

        dailyComponentStockDtm = new DefaultTableModel(componentStockTitle, 0);
        DailyComponenetStockTable.setModel(dailyComponentStockDtm);

        ResultSet rstTypes = BloodTypeController.getAllTypes();
        
        int typeCount = 0;
        while (rstTypes.next()) {
            typeCount++;
            String type = rstTypes.getString("BloodType");
            if (!type.equalsIgnoreCase("Fresh blood")) {
                String[] row = new String[groupCount + 2];
                row[0] = type;
                int rowTotal = 0;
                int rowElementCount = 1;
                
                for (int i = 0; i < groupCount; i++) {
                    int packetCount = BloodStockController.getDailyComponentStockHistory(date, type, groupName[i]);
                    if (packetCount >= 0) {
                        rowTotal += packetCount;
                        row[rowElementCount] = "" + packetCount;
                    }
                    rowElementCount++;
                }
                row[groupCount + 1] = "" + rowTotal;
                dailyComponentStockDtm.addRow(row);
                
            }
            
        }
        TableResizer.resizeColumnWidth(DailyComponenetStockTable);
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        DailyBloodStockDate = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        DailyBloodStockTable = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        DailyComponenetStockTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        DailyBloodDetailTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        DailyBloodDetailTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setTitle("Stock Balance");

        jLabel53.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel53.setText("Daily Blood Stock Balance");

        jLabel1.setText("Date");

        DailyBloodStockDate.setForeground(new java.awt.Color(255, 255, 255));
        DailyBloodStockDate.setDateFormatString("yyyy-MM-dd");
        DailyBloodStockDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DailyBloodStockDateMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DailyBloodStockDateMousePressed(evt);
            }
        });
        DailyBloodStockDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DailyBloodStockDatePropertyChange(evt);
            }
        });

        DailyBloodStockTable.setModel(dailyBloodStockDtm);
        DailyBloodStockTable.setEnabled(false);
        jScrollPane4.setViewportView(DailyBloodStockTable);

        DailyComponenetStockTable.setModel(dailyComponentStockDtm);
        DailyComponenetStockTable.setEnabled(false);
        jScrollPane9.setViewportView(DailyComponenetStockTable);

        DailyBloodDetailTable1.setModel(bloodDtm);
        jScrollPane2.setViewportView(DailyBloodDetailTable1);

        DailyBloodDetailTable2.setModel(bloodDtm2);
        jScrollPane3.setViewportView(DailyBloodDetailTable2);

        jButton1.setText("Print Daily Blood Stock Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Print Daily Component Stock Table");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(DailyBloodStockDate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DailyBloodStockDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Daily Blood and Component Stock Balance", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1162, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DailyBloodStockDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DailyBloodStockDateMouseClicked

    }//GEN-LAST:event_DailyBloodStockDateMouseClicked

    private void DailyBloodStockDateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DailyBloodStockDateMousePressed

    }//GEN-LAST:event_DailyBloodStockDateMousePressed

    private void DailyBloodStockDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DailyBloodStockDatePropertyChange
        try {
            setDailyBloodStock();
        } catch (ParseException ex) {
            Logger.getLogger(StockBalance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DailyBloodStockDatePropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            JasperReport jr = JasperCompileManager.compileReport("./src/Reports/Anu/StockBalance.jrxml");
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            params.put("Date",df.format(DailyBloodStockDate.getDate()));
            JRTableModelDataSource dataSource = new JRTableModelDataSource(DailyBloodStockTable.getModel());

            JasperPrint jp = JasperFillManager.fillReport(jr, params,dataSource);

            JasperViewer.viewReport(jp,false);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Error while generating the Report!", "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            JasperReport jr2 = JasperCompileManager.compileReport("./src/Reports/Anu/ComponentBalance.jrxml");
            Map<String, Object> params2;
            params2 = new HashMap<String, Object>();
            params2.put("Date",df.format(DailyBloodStockDate.getDate()));

            JRTableModelDataSource dataSource2 = new JRTableModelDataSource(DailyComponenetStockTable.getModel());

            JasperPrint jp2 = JasperFillManager.fillReport(jr2, params2,dataSource2);

            JasperViewer.viewReport(jp2,false);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Error while generating the Report!", "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DailyBloodDetailTable1;
    private javax.swing.JTable DailyBloodDetailTable2;
    private com.toedter.calendar.JDateChooser DailyBloodStockDate;
    private javax.swing.JTable DailyBloodStockTable;
    private javax.swing.JTable DailyComponenetStockTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
