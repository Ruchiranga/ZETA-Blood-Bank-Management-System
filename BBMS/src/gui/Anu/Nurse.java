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

import controller.TableResizer;
import controller.anu.BloodGroupController;
import controller.anu.BloodPacketController;
import controller.anu.BloodStockController;
import controller.anu.BloodTypeController;
import connection.NotifierConnection;
import controller.anu.BloodStockUpdateNotifier;
import gui.ChangePassword;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
                System.out.println("Blood Stock is not entered!");
                bloodStockEntered = 0;
            }else{
                System.out.println("Blood Stock is entered!");
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

        jButton5 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        NurseDesktop = new javax.swing.JDesktopPane();
        jButton17 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
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
        jButton22 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        addBloodTypesBtn = new javax.swing.JButton();
        addBloodGroupBtn = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton41 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jButton36 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        addRequesteeBtn = new javax.swing.JButton();
        bloodReturnBtn = new javax.swing.JButton();
        addRequestorBtn = new javax.swing.JButton();
        titlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton38 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Candy Vivian Icon 02.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1370, 705));
        getContentPane().setLayout(null);

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

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Packets to be discarded"));

        expiredBloodPacketsTable.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        expiredBloodPacketsTable.setModel(dtm);
        jScrollPane1.setViewportView(expiredBloodPacketsTable);

        jLabel3.setText("Total");

        totalTxt.setDisabledTextColor(new java.awt.Color(255, 255, 255));
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

        jLayeredPane1.add(NurseDesktop);
        NurseDesktop.setBounds(200, 60, 1170, 650);

        jButton22.setText("Inhouse/Mobile");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton22);
        jButton22.setBounds(0, 100, 200, 30);

        jButton37.setText("Blood Recieval");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton37);
        jButton37.setBounds(0, 130, 200, 30);

        addBloodTypesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I05.jpg"))); // NOI18N
        addBloodTypesBtn.setToolTipText("Blood Types");
        addBloodTypesBtn.setBorder(null);
        addBloodTypesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBloodTypesBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(addBloodTypesBtn);
        addBloodTypesBtn.setBounds(60, 0, 60, 60);

        addBloodGroupBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I07.jpg"))); // NOI18N
        addBloodGroupBtn.setToolTipText("Blood Groups");
        addBloodGroupBtn.setBorder(null);
        addBloodGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBloodGroupBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(addBloodGroupBtn);
        addBloodGroupBtn.setBounds(0, 0, 60, 60);

        jButton33.setText("Blood Discard");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton33);
        jButton33.setBounds(0, 420, 200, 30);

        jLabel5.setFont(new java.awt.Font("Monotype Corsiva", 3, 18)); // NOI18N
        jLabel5.setText("Statistics");
        jLayeredPane1.add(jLabel5);
        jLabel5.setBounds(60, 330, 140, 21);
        jLayeredPane1.add(jSeparator1);
        jSeparator1.setBounds(0, 350, 200, 2);

        jButton41.setText("Blood Recieval");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton41);
        jButton41.setBounds(0, 390, 200, 30);

        jButton25.setText("Blood Return");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton25);
        jButton25.setBounds(0, 160, 200, 30);
        jLayeredPane1.add(jSeparator3);
        jSeparator3.setBounds(0, 220, 200, 2);

        jLabel8.setFont(new java.awt.Font("Monotype Corsiva", 3, 18)); // NOI18N
        jLabel8.setText("Items");
        jLayeredPane1.add(jLabel8);
        jLabel8.setBounds(70, 200, 130, 21);

        jButton36.setText("Item Stock");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton36);
        jButton36.setBounds(0, 230, 200, 30);

        jButton18.setText("Blood Stock");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton18);
        jButton18.setBounds(0, 360, 200, 30);
        jLayeredPane1.add(jSeparator5);
        jSeparator5.setBounds(0, 90, 200, 2);

        jLabel10.setFont(new java.awt.Font("Monotype Corsiva", 3, 18)); // NOI18N
        jLabel10.setText("Data Entry");
        jLayeredPane1.add(jLabel10);
        jLabel10.setBounds(50, 70, 140, 21);

        addRequesteeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I03.png"))); // NOI18N
        addRequesteeBtn.setToolTipText("Requestees");
        addRequesteeBtn.setBorder(null);
        addRequesteeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRequesteeBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(addRequesteeBtn);
        addRequesteeBtn.setBounds(120, 0, 60, 60);

        bloodReturnBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I03.png"))); // NOI18N
        bloodReturnBtn.setToolTipText("Mark returned blood packets");
        bloodReturnBtn.setBorder(null);
        bloodReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodReturnBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(bloodReturnBtn);
        bloodReturnBtn.setBounds(240, 0, 60, 60);

        addRequestorBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/I07.jpg"))); // NOI18N
        addRequestorBtn.setToolTipText("Requestors");
        addRequestorBtn.setBorder(null);
        addRequestorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRequestorBtnActionPerformed(evt);
            }
        });
        jLayeredPane1.add(addRequestorBtn);
        addRequestorBtn.setBounds(180, 0, 60, 60);

        titlePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel1.setText("           Karapitiya Blood Bank Management System");

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1380, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLayeredPane1.add(titlePanel);
        titlePanel.setBounds(360, 0, 1010, 60);

        jButton38.setText("Item Recieval");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton38);
        jButton38.setBounds(0, 260, 200, 30);

        jButton40.setText("Reagent Requests");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(jButton40);
        jButton40.setBounds(0, 290, 200, 30);

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(0, 0, 1370, 705);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here
}//GEN-LAST:event_jButton5ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        
            StockBalance stock = new StockBalance();
            stock.setClosable(true);
            NurseDesktop.add(stock);
            NurseDesktop.setRequestFocusEnabled(true);
            stock.show();
        
}//GEN-LAST:event_jButton18ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        BloodReturn returnedBlood = new BloodReturn();
        returnedBlood.setClosable(true);
        NurseDesktop.add(returnedBlood);
        NurseDesktop.setRequestFocusEnabled(true);
        returnedBlood.show();


}//GEN-LAST:event_jButton25ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        ItemManagement items = new ItemManagement(this);
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
            ChangePassword p = new ChangePassword(3);
            p.setClosable(true);
            NurseDesktop.add(p);
            p.show();
        } catch (IOException ex) {
            Logger.getLogger(Nurse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bloodReturnBtnActionPerformed

    private void addRequesteeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRequesteeBtnActionPerformed
        AddRequestee requestee = new AddRequestee(null);
        requestee.setClosable(true);
        NurseDesktop.add(requestee);
        NurseDesktop.setRequestFocusEnabled(true);
        requestee.show();
    }//GEN-LAST:event_addRequesteeBtnActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        ItemRecieval itemRecieval = new ItemRecieval();
        itemRecieval.setClosable(true);
        NurseDesktop.add(itemRecieval);
        NurseDesktop.setRequestFocusEnabled(true);
        itemRecieval.show();
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        ReagentRequests reagentRequests = new ReagentRequests();
        reagentRequests.setClosable(true);
        NurseDesktop.add(reagentRequests);
        NurseDesktop.setRequestFocusEnabled(true);
        reagentRequests.show();
    }//GEN-LAST:event_jButton40ActionPerformed

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
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JTextField totalTxt;
    // End of variables declaration//GEN-END:variables

}
