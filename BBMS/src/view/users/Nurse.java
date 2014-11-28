/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Nurse.java
 *
 * Created on Jul 28, 2014, 9:26:48 PM
 */
package view.users;

import view.item_management.ReagentRequests;
import view.item_management.ItemRecieval;
import view.item_management.ItemManagement;
import connection.NotifierConnection;
import controller.blood_stock_management.BloodGroupController;
import controller.blood_stock_management.BloodPacketController;
import controller.blood_stock_management.BloodStockController;
import controller.blood_stock_management.BloodStockUpdateNotifier;
import controller.blood_stock_management.BloodTypeController;
import controller.common.TableResizer;
import view.blood_stock_management.BloodPacketForm;
import view.blood_stock_management.BloodRecieval;
import view.statistics.BloodRecievalLog;
import view.blood_stock_management.BloodReturn;
import view.blood_stock_management.ComponentsStock;
import view.statistics.DiscardedBlood;
import view.blood_stock_management.StockBalance;
import view.common.ChangePassword;
import view.common.Login;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Balance;
import model.BloodStockHistory;
import model.ComponentStockHistory;

/**
 *
 * @author Anuradha
 */
public class Nurse extends javax.swing.JFrame implements Observer {

    String[] title = {"PacketID", "Type", "Group"};
    DefaultTableModel dtm = new DefaultTableModel(title, 0);
    
    Calendar currenttime = Calendar.getInstance();
    java.sql.Date sqlCurrentDate = new java.sql.Date((currenttime.getTime()).getTime());

    String[] bloodStockTitle = {"Blood Group", "Un X matched", "X matched", "Reservations", "Under Observation", "Total"};
    DefaultTableModel bloodStockDtm = new DefaultTableModel(bloodStockTitle, 0);
    BloodStockUpdateNotifier notifier = null;

    boolean isBloodStockEntered;
    /**
     * Creates new form Nurse
     */
    public Nurse() throws IOException {
            
            this.setName("Blood Bank Management System");
            initComponents();
            
            
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            FileInputStream imgStream = new FileInputStream(imgfile);
            BufferedImage bi = ImageIO.read(imgStream);
            ImageIcon myImg = new ImageIcon(bi);
            this.setIconImage(myImg.getImage());
            setTitle("Karapitiya Blood Bank Management System");
            setLocationRelativeTo(null);
            
            notifier = NotifierConnection.getNotifierConnection(this);
            
            update(null, null);
            TableResizer.resizeColumnWidth(bloodStockTable);
            TableResizer.resizeColumnWidth(componentStockTable);
            TableResizer.resizeColumnWidth(expiredBloodPacketsTable);
            
            currentDate();
    }
    
    public void currentDate() {

        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    date_txt.setText("Date " + year + "/" + (month + 1) + "/" + day);

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    time_txt.setText("Time " + hour + ":" + (minute) + ":" + second);

                    try {
                        sleep(1000);
                    } catch (Exception e) {

                    }

                }
            }
        };
        clock.start();
    }

    private void setToBeDiscardedBlood() throws ClassNotFoundException, SQLException {
        
        dtm = new DefaultTableModel(title, 0);
        expiredBloodPacketsTable.setModel(dtm);
        ResultSet rst = BloodPacketController.getExpiredBloodPackets(sqlCurrentDate);
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
    }

    public JDesktopPane getDesktop() {
        return this.NurseDesktop;
    }

    public void update(Observable o, Object arg) {
        
        try {
            
            int resultBalance = 0;
            int updateBalance = 0;
            
            setToBeDiscardedBlood();
            
            isBloodStockEntered = BloodStockController.isBloodStockEntered(sqlCurrentDate);
            int bloodStockEntered;
            if (!isBloodStockEntered) {
                //System.out.println("Blood Stock is not entered!");
                bloodStockEntered = 0;
            }else{
                //System.out.println("Blood Stock is entered!");
                bloodStockEntered = 1;
            }
            
            bloodStockDtm = new DefaultTableModel(bloodStockTitle, 0);
            bloodStockTable.setModel(bloodStockDtm);
            ResultSet rst;
            try {
                rst = BloodGroupController.getAllGroups();
                int groupCount = BloodGroupController.getGroupCount();
                int[] tot = new int[groupCount - 1];
                int i = 0;
                int resultBloodStock = 0;
                int updateBloodStock = 0;
                while (rst.next()) {
                    String bloodGroup = rst.getString("GroupName");
                    String bloodType = "Fresh blood";
                    if (!bloodGroup.equalsIgnoreCase("UG")) {
                        int unX = BloodStockController.getUnX(bloodGroup, bloodType);
                        int x = BloodStockController.getX(bloodGroup, bloodType);
                        int specialReservation = BloodStockController.getSpecialReservation(bloodGroup, bloodType);
                        int underObservation = BloodStockController.getUnderObservation(bloodGroup, bloodType);
                        int total = unX + x + specialReservation + underObservation;
                        tot[i++] = total;
                        String[] row = {bloodGroup, "" + unX, "" + x, "" + specialReservation, "" + underObservation, "" + total};
                        bloodStockDtm.addRow(row);
                        if (bloodStockEntered == 0){
                            BloodStockHistory bloodHistory = new BloodStockHistory(sqlCurrentDate, bloodGroup, unX, x, specialReservation, underObservation, total);
                            resultBloodStock += BloodStockController.addBloodStockHistory(bloodHistory);
                        }else{
                            //////////////////////////////////////////////////////////////////////////////////
                            BloodStockHistory bloodHistory = new BloodStockHistory(sqlCurrentDate, bloodGroup, unX, x, specialReservation, underObservation, total);
                            updateBloodStock += BloodStockController.updateBloodStockHistory(bloodHistory);
                            
                        }
                    }
                    
                }
                int totalOfTested = 0;
                for (int j = 0; j < tot.length; j++) {
                    totalOfTested += tot[j];
                }
                String[] totalRow = {"Total", "", "", "", "", "" + totalOfTested};
                bloodStockDtm.addRow(totalRow);
                
                int totalOfUntested = BloodStockController.getUntestedTotal("UG", "Fresh blood");
                String[] untestedRow = {"Untested", "", "", "", "", "" + totalOfUntested};
                bloodStockDtm.addRow(untestedRow);
                if (bloodStockEntered == 0){
                    BloodStockHistory bloodHistory = new BloodStockHistory(sqlCurrentDate, "UG", -1, -1, -1, -1, totalOfUntested);
                    resultBloodStock += BloodStockController.addBloodStockHistory(bloodHistory);
                }else{
                    
                    BloodStockHistory bloodHistory = new BloodStockHistory(sqlCurrentDate, "UG", -1, -1, -1, -1, totalOfUntested);
                    updateBloodStock += BloodStockController.updateBloodStockHistory(bloodHistory);
                }
                
                if(resultBloodStock == groupCount){
                    //JOptionPane.showMessageDialog(null, "Blood stock added successfully");
                }else if(updateBloodStock == groupCount){
                    //JOptionPane.showMessageDialog(null, "Blood stock updated successfully");
                }else{
                    //JOptionPane.showMessageDialog(null, "Error in updating blood stock");
                }
                
                int lastRow = bloodStockDtm.getRowCount();
                if (lastRow >= 0) {
                    String[] grandTotalRow = {"","","","","Grand Total",""+(totalOfTested+totalOfUntested)};
                    bloodStockDtm.addRow(grandTotalRow);
                }
                
                if(bloodStockEntered == 0){
                    Balance balance = new Balance(sqlCurrentDate, "Fresh blood", (totalOfTested+totalOfUntested));
                    resultBalance += BloodStockController.addBalanceHistory(balance);
                }else{
                    
                    //////////////////////////////////////////////////////////////////////////////
                    Balance balance = new Balance(sqlCurrentDate, "Fresh blood", (totalOfTested+totalOfUntested));
                    updateBalance += BloodStockController.updateBalanceHistory(balance);
                    
                }
                
                setDailyComponentBalance(bloodStockEntered, resultBalance, updateBalance);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    DefaultTableModel componenetStockDtm;

    private void setDailyComponentBalance(int bloodStockEntered, int resultBalance, int updateBalance) throws ClassNotFoundException, SQLException {

        int groupCount = BloodGroupController.getGroupCount();
        String[] componentStockTitle = new String[groupCount + 2];
        String[] groupName = new String[groupCount];
        
        int resultComponent = 0;
        int updateComponent = 0;

        componentStockTitle[0] = "Componenet";
        componentStockTitle[groupCount + 1] = "Total";
        int groupCounter = 1;

        ResultSet rstGroups = BloodGroupController.getAllGroups();
        while (rstGroups.next()) {
            String group = rstGroups.getString("GroupName");
            componentStockTitle[groupCounter] = group;
            groupName[groupCounter - 1] = group;
            groupCounter++;
        }

        componenetStockDtm = new DefaultTableModel(componentStockTitle, 0);
        componentStockTable.setModel(componenetStockDtm);

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
                    int packetCount = BloodStockController.getComponenetPacketCount(type, groupName[i]);
                    if (packetCount >= 0) {
                        rowTotal += packetCount;
                        row[rowElementCount] = "" + packetCount;
                    }
                    rowElementCount++;
                    if (bloodStockEntered == 0){
                        ComponentStockHistory componentStock = new ComponentStockHistory(sqlCurrentDate, type, groupName[i], packetCount);
                        resultComponent += BloodStockController.addComponentStockHistory(componentStock);
                    }else{
                        //////////////////////////////////////////////////////////////////////////////
                        ComponentStockHistory componentStock = new ComponentStockHistory(sqlCurrentDate, type, groupName[i], packetCount);
                        updateComponent += BloodStockController.updateComponentStockHistory(componentStock);
                    }
                }
                row[groupCount + 1] = "" + rowTotal;
                componenetStockDtm.addRow(row);
                if(bloodStockEntered == 0){
                    Balance balance = new Balance(sqlCurrentDate, type, rowTotal);
                    resultBalance += BloodStockController.addBalanceHistory(balance);
                }else{
                    
                    //////////////////////////////////////////////////////////////////////////////
                    Balance balance = new Balance(sqlCurrentDate, type, rowTotal);
                    updateBalance += BloodStockController.updateBalanceHistory(balance);
                    
                }
            }
            
        }
        if(resultComponent == ((typeCount-1)*groupCount)){
            //JOptionPane.showMessageDialog(null, "Component Stock Added successfully");
        }else if(updateComponent == ((typeCount-1)*groupCount)){
            //JOptionPane.showMessageDialog(null, "Component Stock Updated successfully");
        }else{
            //JOptionPane.showMessageDialog(null, "Error occured when updating component stock");
        }
        
        if(resultBalance == typeCount ){
            //JOptionPane.showMessageDialog(null, "Balance added successfully");
        }else if(updateBalance == typeCount){
            //JOptionPane.showMessageDialog(null, "Balance updated successfully");
        }else{
            //JOptionPane.showMessageDialog(null, "Error occured when updating Balance");
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        NurseDesktop = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        expiredBloodPacketsTable = new javax.swing.JTable();
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
        jLabel2 = new javax.swing.JLabel();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        ttiBtn = new javax.swing.JButton();
        bloodGroupBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        desktopPane1 = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        groupingAndTTIRegButton = new javax.swing.JButton();
        BloodGroupButton = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton21 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        testingButton = new javax.swing.JButton();
        time_txt = new javax.swing.JTextField();
        date_txt = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton24 = new javax.swing.JButton();
        sampleDetailsButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        issueRegisterButton = new javax.swing.JButton();
        generateIssuesButton = new javax.swing.JButton();
        addSampleBtn = new javax.swing.JButton();
        passwordBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        bloodGroupingBtn = new javax.swing.JButton();
        issueBtn1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1370, 705));
        getContentPane().setLayout(null);

        jLayeredPane1.setBackground(new java.awt.Color(204, 204, 204));

        NurseDesktop.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/images/red-blood-cells.png")))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Packets to be discarded"));

        expiredBloodPacketsTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        expiredBloodPacketsTable.setModel(dtm);
        jScrollPane1.setViewportView(expiredBloodPacketsTable);

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(discardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(totalTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discardBtn))
                .addGap(16, 16, 16))
        );

        NurseDesktop.add(jPanel1);
        jPanel1.setBounds(860, 160, 310, 490);

        jCalendar1.setBackground(new java.awt.Color(0, 0, 0));
        jCalendar1.setForeground(new java.awt.Color(255, 255, 255));
        NurseDesktop.add(jCalendar1);
        jCalendar1.setBounds(862, 0, 310, 160);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Daily Blood and Component Stock Balance Register"));

        bloodStockTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bloodStockTable.setModel(bloodStockDtm);
        bloodStockTable.setEnabled(false);
        jScrollPane12.setViewportView(bloodStockTable);

        componentStockTable.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        componentStockTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        componentStockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        NurseDesktop.add(jPanel2);
        jPanel2.setBounds(170, 0, 690, 470);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/White-Background-53.jpg"))); // NOI18N
        NurseDesktop.add(jLabel2);
        jLabel2.setBounds(0, 0, 1170, 650);
        NurseDesktop.add(jCalendar2);
        jCalendar2.setBounds(858, 0, 310, 160);

        jLayeredPane1.add(NurseDesktop);
        NurseDesktop.setBounds(200, 60, 1170, 650);

        ttiBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TTI test icon.png"))); // NOI18N
        ttiBtn.setToolTipText("Blood Recieval");
        ttiBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttiBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(ttiBtn);
        ttiBtn.setBounds(60, 0, 60, 60);

        bloodGroupBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/crossmatch.png"))); // NOI18N
        bloodGroupBtn.setToolTipText("Blood Return");
        bloodGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodGroupBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(bloodGroupBtn);
        bloodGroupBtn.setBounds(120, 0, 60, 60);

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel4.setText("Karapitiya Blood Bank Management System");
        jLayeredPane2.add(jLabel4);
        jLabel4.setBounds(380, 0, 690, 60);

        desktopPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));

        javax.swing.GroupLayout desktopPane1Layout = new javax.swing.GroupLayout(desktopPane1);
        desktopPane1.setLayout(desktopPane1Layout);
        desktopPane1Layout.setHorizontalGroup(
            desktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1148, Short.MAX_VALUE)
        );
        desktopPane1Layout.setVerticalGroup(
            desktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );

        jLayeredPane2.add(desktopPane1);
        desktopPane1.setBounds(200, 60, 1150, 650);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tests", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setFocusTraversalPolicyProvider(true);
        jPanel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        groupingAndTTIRegButton.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        groupingAndTTIRegButton.setText("TTI Register");
        groupingAndTTIRegButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupingAndTTIRegButtonActionPerformed(evt);
            }
        });

        BloodGroupButton.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        BloodGroupButton.setText("Blood Grouping and TTI");
        BloodGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BloodGroupButtonActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton6.setText("Delete/Update Test");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(groupingAndTTIRegButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BloodGroupButton, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(groupingAndTTIRegButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BloodGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jLayeredPane2.add(jPanel3);
        jPanel3.setBounds(0, 220, 200, 140);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "View", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton21.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton21.setText("Blood Stock");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton23.setText("Blood Categories");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        testingButton.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        testingButton.setText("Blood and Component Availability");
        testingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(testingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(testingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        jLayeredPane2.add(jPanel4);
        jPanel4.setBounds(0, 520, 200, 160);

        time_txt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        time_txt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        time_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time_txtActionPerformed(evt);
            }
        });
        jLayeredPane2.add(time_txt);
        time_txt.setBounds(1120, 40, 110, 20);

        date_txt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        date_txt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLayeredPane2.add(date_txt);
        date_txt.setBounds(1120, 10, 110, 20);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Requests", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton24.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton24.setText("View Requests");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        sampleDetailsButton.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        sampleDetailsButton.setText("Add Sample Details");
        sampleDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sampleDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sampleDetailsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sampleDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane2.add(jPanel5);
        jPanel5.setBounds(0, 90, 200, 110);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Issues", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        issueRegisterButton.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        issueRegisterButton.setText("Issue Register");
        issueRegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueRegisterButtonActionPerformed(evt);
            }
        });

        generateIssuesButton.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        generateIssuesButton.setText("Generate Issues");
        generateIssuesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateIssuesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(generateIssuesButton, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(issueRegisterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generateIssuesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(issueRegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jLayeredPane2.add(jPanel6);
        jPanel6.setBounds(0, 380, 200, 110);

        addSampleBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addSample.png"))); // NOI18N
        addSampleBtn.setToolTipText("Add/Search Blood Packet Details");
        addSampleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSampleBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(addSampleBtn);
        addSampleBtn.setBounds(0, 0, 60, 60);

        passwordBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/password.png"))); // NOI18N
        passwordBtn.setToolTipText("Change Password");
        passwordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(passwordBtn);
        passwordBtn.setBounds(1250, 0, 60, 60);

        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deletetest.png"))); // NOI18N
        deleteBtn.setToolTipText("Logout");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(deleteBtn);
        deleteBtn.setBounds(1310, 0, 60, 60);

        bloodGroupingBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/viewRequest.png"))); // NOI18N
        bloodGroupingBtn.setToolTipText("Reagent Requests");
        bloodGroupingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodGroupingBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(bloodGroupingBtn);
        bloodGroupingBtn.setBounds(180, 0, 60, 60);

        issueBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blood Issue.png"))); // NOI18N
        issueBtn1.setToolTipText("Item Recieval");
        issueBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueBtn1ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(issueBtn1);
        issueBtn1.setBounds(240, 0, 60, 60);

        jLayeredPane1.add(jLayeredPane2);
        jLayeredPane2.setBounds(0, 0, 1370, 60);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Statistics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton18.setBackground(new java.awt.Color(255, 0, 0));
        jButton18.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton18.setText("Blood Stock");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton41.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton41.setText("Blood Recieval");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton33.setBackground(new java.awt.Color(255, 0, 0));
        jButton33.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton33.setText("Blood Discard");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.add(jPanel7);
        jPanel7.setBounds(0, 450, 200, 150);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Entry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton22.setBackground(new java.awt.Color(255, 0, 0));
        jButton22.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton22.setText("Inhouse/Mobile");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton38.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton38.setText("Blood Recieval");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(255, 0, 0));
        jButton25.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton25.setText("Blood Return");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanel8);
        jPanel8.setBounds(0, 110, 200, 150);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton36.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton36.setText("Item Stock");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton40.setBackground(new java.awt.Color(255, 0, 0));
        jButton40.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton40.setText("Item Recieval");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton44.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton44.setText("Reagent Requests");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                        .addComponent(jButton36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jButton36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.add(jPanel9);
        jPanel9.setBounds(0, 280, 200, 150);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(0, 0, 1370, 705);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        
            StockBalance stock = new StockBalance();
            stock.setClosable(true);
            NurseDesktop.add(stock);
            NurseDesktop.setRequestFocusEnabled(true);
            stock.show();
        
}//GEN-LAST:event_jButton18ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        DiscardedBlood discardedBlood = new DiscardedBlood();
        discardedBlood.setClosable(true);
        discardedBlood.setMaximizable(true);
        NurseDesktop.add(discardedBlood);
        NurseDesktop.setRequestFocusEnabled(true);
        discardedBlood.show();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void discardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardBtnActionPerformed
        int row = expiredBloodPacketsTable.getSelectedRow();

        if (row >= 0) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to discard the selected Blood Packet?", "Discard Blood Packet", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    String packID = "" + dtm.getValueAt(row, 0);
                    int discarded = BloodPacketController.discardPacket(packID, sqlCurrentDate);
                    if (discarded == 1) {
                        notifier.notifyUpdateBloodStock();
                        JOptionPane.showMessageDialog(null, "Blood packet discarded succesfully!");
                        
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

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        BloodRecievalLog bloodRecieval = new BloodRecievalLog();
        bloodRecieval.setClosable(true);
        NurseDesktop.add(bloodRecieval);
        NurseDesktop.setRequestFocusEnabled(true);
        bloodRecieval.show();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void ttiBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttiBtnActionPerformed
        BloodRecieval donors = null;
        try {
            donors = new BloodRecieval(this);
        } catch (IOException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }
        donors.setClosable(true);
        donors.setMaximizable(true);
        NurseDesktop.add(donors);
        NurseDesktop.setRequestFocusEnabled(true);
        donors.show();
    }//GEN-LAST:event_ttiBtnActionPerformed

    private void bloodGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodGroupBtnActionPerformed
        BloodReturn returnedBlood = new BloodReturn();
        returnedBlood.setClosable(true);
        NurseDesktop.add(returnedBlood);
        NurseDesktop.setRequestFocusEnabled(true);
        returnedBlood.show();
    }//GEN-LAST:event_bloodGroupBtnActionPerformed

    private void groupingAndTTIRegButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupingAndTTIRegButtonActionPerformed

    }//GEN-LAST:event_groupingAndTTIRegButtonActionPerformed

    private void BloodGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BloodGroupButtonActionPerformed

    }//GEN-LAST:event_BloodGroupButtonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed

    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void testingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testingButtonActionPerformed

    }//GEN-LAST:event_testingButtonActionPerformed

    private void time_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_time_txtActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed

    }//GEN-LAST:event_jButton24ActionPerformed

    private void sampleDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sampleDetailsButtonActionPerformed

    }//GEN-LAST:event_sampleDetailsButtonActionPerformed

    private void issueRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueRegisterButtonActionPerformed

    }//GEN-LAST:event_issueRegisterButtonActionPerformed

    private void generateIssuesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateIssuesButtonActionPerformed

    }//GEN-LAST:event_generateIssuesButtonActionPerformed

    private void addSampleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSampleBtnActionPerformed
        BloodPacketForm packetForm = new BloodPacketForm();
        packetForm.setClosable(true);
        packetForm.setMaximizable(true);
        NurseDesktop.add(packetForm);
        NurseDesktop.setRequestFocusEnabled(true);
        packetForm.show();
    }//GEN-LAST:event_addSampleBtnActionPerformed

    private void passwordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordBtnActionPerformed
        ChangePassword passwordReset = null;
        try {
            passwordReset = new ChangePassword(3);
        } catch (IOException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }
        passwordReset.setClosable(true);
        passwordReset.setMaximizable(false);
        NurseDesktop.add(passwordReset);
        NurseDesktop.setRequestFocusEnabled(true);
        passwordReset.show();
    }//GEN-LAST:event_passwordBtnActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        BloodPacketForm packetForm = new BloodPacketForm();
        packetForm.setClosable(true);
        packetForm.setMaximizable(true);
        NurseDesktop.add(packetForm);
        NurseDesktop.setRequestFocusEnabled(true);
        packetForm.show();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        BloodRecieval donors = null;
        try {
            donors = new BloodRecieval(this);
        } catch (IOException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }
        donors.setClosable(true);
        donors.setMaximizable(true);
        NurseDesktop.add(donors);
        NurseDesktop.setRequestFocusEnabled(true);
        donors.show();
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        BloodReturn returnedBlood = new BloodReturn();
        returnedBlood.setClosable(true);
        NurseDesktop.add(returnedBlood);
        NurseDesktop.setRequestFocusEnabled(true);
        returnedBlood.show();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        ItemManagement items = null;
        try {
            items = new ItemManagement(this);
        } catch (IOException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }
        items.setClosable(true);
        NurseDesktop.add(items);
        NurseDesktop.setRequestFocusEnabled(true);
        items.show();
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        ItemRecieval itemRecieval = new ItemRecieval();
        itemRecieval.setClosable(true);
        NurseDesktop.add(itemRecieval);
        NurseDesktop.setRequestFocusEnabled(true);
        itemRecieval.show();
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        ReagentRequests reagentRequests = new ReagentRequests();
        reagentRequests.setClosable(true);
        NurseDesktop.add(reagentRequests);
        NurseDesktop.setRequestFocusEnabled(true);
        reagentRequests.show();
    }//GEN-LAST:event_jButton44ActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Log out", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            this.dispose();
            Login bbms = new Login();
            bbms.setVisible(true);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void issueBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueBtn1ActionPerformed
        ItemRecieval itemRecieval = new ItemRecieval();
        itemRecieval.setClosable(true);
        NurseDesktop.add(itemRecieval);
        NurseDesktop.setRequestFocusEnabled(true);
        itemRecieval.show();
    }//GEN-LAST:event_issueBtn1ActionPerformed

    private void bloodGroupingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodGroupingBtnActionPerformed
        ReagentRequests reagentRequests = new ReagentRequests();
        reagentRequests.setClosable(true);
        NurseDesktop.add(reagentRequests);
        NurseDesktop.setRequestFocusEnabled(true);
        reagentRequests.show();
    }//GEN-LAST:event_bloodGroupingBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new Nurse().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BloodGroupButton;
    private javax.swing.JDesktopPane NurseDesktop;
    private javax.swing.JButton addSampleBtn;
    private javax.swing.JButton bloodGroupBtn;
    private javax.swing.JButton bloodGroupingBtn;
    private javax.swing.JTable bloodStockTable;
    private javax.swing.JTable componentStockTable;
    private javax.swing.JTextField date_txt;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JDesktopPane desktopPane1;
    private javax.swing.JButton discardBtn;
    private javax.swing.JTable expiredBloodPacketsTable;
    private javax.swing.JButton generateIssuesButton;
    private javax.swing.JButton groupingAndTTIRegButton;
    private javax.swing.JButton issueBtn1;
    private javax.swing.JButton issueRegisterButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JCalendar jCalendar1;
    private com.toedter.calendar.JCalendar jCalendar2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JButton passwordBtn;
    private javax.swing.JButton sampleDetailsButton;
    private javax.swing.JButton testingButton;
    private javax.swing.JTextField time_txt;
    private javax.swing.JTextField totalTxt;
    private javax.swing.JButton ttiBtn;
    // End of variables declaration//GEN-END:variables

}
