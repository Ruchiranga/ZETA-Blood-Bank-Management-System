/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ComponentsStock.java
 *
 * Created on Sep 7, 2014, 5:43:16 PM
 */
package view.blood_stock_management;

import controller.blood_stock_management.BloodStockController;
import controller.blood_stock_management.BloodTypeController;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anuradha
 */
public class ComponentsStock extends javax.swing.JInternalFrame {

    Calendar currenttime = Calendar.getInstance();
    java.sql.Date sqldate = new java.sql.Date((currenttime.getTime()).getTime());
    
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
    DefaultTableModel bloodDtm = new DefaultTableModel(bloodData, null);

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
    DefaultTableModel bloodDtm2 = new DefaultTableModel(bloodData, null);
    
    /** Creates new form ComponentsStock */
    public ComponentsStock() {
        try {
            initComponents();
            FileInputStream imgStream = null;
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            imgStream = new FileInputStream(imgfile);
            BufferedImage bi = ImageIO.read(imgStream);
            ImageIcon myImg = new ImageIcon(bi);
            this.setFrameIcon(myImg);
            setTitle("Component Stock");
            try {
                setDailyBloodStockDetails(sqldate);
            } catch (ParseException ex) {
                Logger.getLogger(ComponentsStock.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ComponentsStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setDailyBloodStockDetails(java.sql.Date sqlDateC) throws ParseException {

        try {

            ArrayList<String> bloodTypes = new ArrayList<String>();

            ResultSet rstTypes = BloodTypeController.getAllTypes();
            while (rstTypes.next()) {
                bloodTypes.add(rstTypes.getString("BloodType"));
            }

            int typeCount = BloodTypeController.getTypeCount();
            String[] typeTitle1;
            
            typeTitle1 = new String[typeCount+1];
                

            typeTitle1[0] = "";

            int counter = 0;
            for (int i = 1; i < typeTitle1.length; i++) {
                typeTitle1[i] = bloodTypes.get(counter);
                counter++;
            }

            bloodDtm = new DefaultTableModel(bloodData, typeTitle1);
            DailyBloodDetailTable1.setModel(bloodDtm);
//            bloodDtm2 = new DefaultTableModel(bloodData, typeTitle2);
//            DailyBloodDetailTable2.setModel(bloodDtm2);
            
            Calendar currenttime = Calendar.getInstance();
            //2014-10-10
            java.util.Date today = new java.util.Date((currenttime.getTime()).getTime());

            int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String prevDay = dateFormat.format(today.getTime() - MILLIS_IN_DAY);
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
            
//            for (int i = 1; i < typeTitle2.length; i++) {
//                //=======================================================================================================
//                int balance = BloodStockController.getBalanceStockHistoryByType(sqlYesterday, typeTitle2[i]);
//
//                if (balance >= 0) {
//                    bloodDtm2.setValueAt("" + balance, 0, i);
//
//                    int inhouse = BloodStockController.getInhouseBloodCount(typeTitle2[i], sqlDateC);
//                    bloodDtm2.setValueAt("" + inhouse, 1, i);
//
//                    int mobile = BloodStockController.getMobileBloodCount(typeTitle2[i], sqlDateC);
//                    bloodDtm2.setValueAt("" + mobile, 2, i);
//
//                    bloodDtm2.setValueAt("" + (balance + inhouse + mobile), 3, i);
//
//                    int returned = BloodStockController.getReturnBloodCount(typeTitle2[i], sqlDateC);
//                    bloodDtm2.setValueAt("" + returned, 4, i);
//
//                    int recieved = BloodStockController.getRecievedBloodCount(typeTitle2[i], sqlDateC);
//                    bloodDtm2.setValueAt("" + recieved, 5, i);
//
//                    bloodDtm2.setValueAt("" + (balance + inhouse + mobile + returned + recieved), 6, i);
//
//                    int patientIssued = BloodStockController.getPatientIssuedBloodCount(typeTitle2[i], sqlDateC);
//                    int bulkIssued = BloodStockController.getBulkIssuedBloodCount(typeTitle2[i], sqlDateC);
//
//                    int issued = 0;
//                    if (patientIssued >= 0 && bulkIssued >= 0) {
//                        issued = patientIssued + bulkIssued;
//                    }
//
//                    bloodDtm2.setValueAt("" + issued, 7, i);
//
//                    bloodDtm2.setValueAt("" + (balance + inhouse + mobile + returned + recieved - issued), 8, i);
//
//                    int discarded = BloodStockController.getDiscardedBloodCount(typeTitle2[i], sqlDateC);
//                    bloodDtm2.setValueAt("" + discarded, 9, i);
//
//                    bloodDtm2.setValueAt("" + (balance + inhouse + mobile + returned + recieved - issued - discarded), 10, i);
//
//                }
//                //==============================================================================================
//            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockBalance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StockBalance.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        DailyBloodDetailTable1 = new javax.swing.JTable();
        jLabel51 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(601, 325));
        setOpaque(true);
        setPreferredSize(new java.awt.Dimension(620, 625));

        jTabbedPane1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        DailyBloodDetailTable1.setModel(bloodDtm);
        jScrollPane2.setViewportView(DailyBloodDetailTable1);

        jLabel51.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel51.setText("Components Stock");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Daily Blood and Components Stock Balance", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DailyBloodDetailTable1;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
