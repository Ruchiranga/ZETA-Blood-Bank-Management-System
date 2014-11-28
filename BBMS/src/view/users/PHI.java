package view.users;

import java.awt.Dimension;
import view.common.ChangePassword;
import view.common.Login;
import view.common.Initial;
import view.camp_management.CheckAvailbleDates;
import view.camp_management.MobileCampDetail;
import view.camp_management.MobileCampTables;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Anuradha
 */
public class PHI extends javax.swing.JFrame {

    /**
     * Creates new form PHI
     */
    public PHI() throws IOException {
        FileInputStream imgStream = null;
        try {
            initComponents();
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            imgStream = new FileInputStream(imgfile);
            BufferedImage bi = ImageIO.read(imgStream);
            ImageIcon myImg = new ImageIcon(bi);
            this.setIconImage(myImg.getImage());
            setTitle("Karapitiya Blood Bank Management System");
            Initial first = new Initial(desktopPane.getSize());
            desktopPane.removeAll();
            desktopPane.add(first);
            first.setVisible(true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                imgStream.close();
            } catch (IOException ex) {
                Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        desktopPane = new javax.swing.JDesktopPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        desktopPane1 = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        groupingAndTTIRegButton = new javax.swing.JButton();
        BloodGroupButton = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        testingButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton22 = new javax.swing.JButton();
        sampleDetailsButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        issueRegisterButton = new javax.swing.JButton();
        generateIssuesButton = new javax.swing.JButton();
        issueBtn = new javax.swing.JButton();
        passwordBtn = new javax.swing.JButton();
        issueRegisterBtn1 = new javax.swing.JButton();
        bloodGroupBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        date_txt = new javax.swing.JTextField();
        time_txt = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        buttonBDC = new javax.swing.JButton();
        buttonCAD = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        buttonMobileDates = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        desktopPane.setBorder(new javax.swing.border.MatteBorder(new javax.swing.ImageIcon(getClass().getResource("/images/red-blood-cells.png")))); // NOI18N
        jLayeredPane1.add(desktopPane);
        desktopPane.setBounds(200, 60, 1170, 650);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel2.setText("Karapitiya Blood Bank Management System");
        jLayeredPane2.add(jLabel2);
        jLabel2.setBounds(330, 0, 750, 60);

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tests", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setFocusTraversalPolicyProvider(true);
        jPanel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

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

        jButton5.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton5.setText("Delete/Update Test");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(groupingAndTTIRegButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BloodGroupButton, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(groupingAndTTIRegButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BloodGroupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jLayeredPane2.add(jPanel2);
        jPanel2.setBounds(0, 220, 200, 140);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "View", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton18.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton18.setText("Blood Stock");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton16.setText("Blood Categories");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        testingButton.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        testingButton.setText("Blood and Component Availability");
        testingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testingButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(testingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(testingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        jLayeredPane2.add(jPanel3);
        jPanel3.setBounds(0, 520, 200, 160);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Requests", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jButton22.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        jButton22.setText("View Requests");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        sampleDetailsButton.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        sampleDetailsButton.setText("Add Sample Details");
        sampleDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sampleDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sampleDetailsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sampleDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane2.add(jPanel4);
        jPanel4.setBounds(0, 90, 200, 110);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Issues", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(generateIssuesButton, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(issueRegisterButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generateIssuesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(issueRegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        jLayeredPane2.add(jPanel1);
        jPanel1.setBounds(0, 380, 200, 110);

        issueBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/blood Issue.png"))); // NOI18N
        issueBtn.setToolTipText("Check available dates");
        issueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(issueBtn);
        issueBtn.setBounds(60, 0, 60, 60);

        passwordBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/password.png"))); // NOI18N
        passwordBtn.setToolTipText("Change Password");
        passwordBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(passwordBtn);
        passwordBtn.setBounds(1250, 0, 60, 60);

        issueRegisterBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/issueregistericon.png"))); // NOI18N
        issueRegisterBtn1.setToolTipText("Mobile Campaign Tables");
        issueRegisterBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueRegisterBtn1ActionPerformed(evt);
            }
        });
        jLayeredPane2.add(issueRegisterBtn1);
        issueRegisterBtn1.setBounds(120, 0, 60, 60);

        bloodGroupBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/crossmatch.png"))); // NOI18N
        bloodGroupBtn.setToolTipText("Mobile Campaigns");
        bloodGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bloodGroupBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(bloodGroupBtn);
        bloodGroupBtn.setBounds(0, 0, 60, 60);

        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deletetest.png"))); // NOI18N
        deleteBtn.setToolTipText("Delete/Update Test ");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jLayeredPane2.add(deleteBtn);
        deleteBtn.setBounds(1310, 0, 60, 60);

        date_txt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        date_txt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLayeredPane2.add(date_txt);
        date_txt.setBounds(1130, 10, 110, 20);

        time_txt.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        time_txt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        time_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                time_txtActionPerformed(evt);
            }
        });
        jLayeredPane2.add(time_txt);
        time_txt.setBounds(1130, 40, 110, 20);

        jLayeredPane1.add(jLayeredPane2);
        jLayeredPane2.setBounds(-2, -1, 1370, 60);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Entry", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        buttonBDC.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        buttonBDC.setText("Blood Donation Camps");
        buttonBDC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBDCActionPerformed(evt);
            }
        });

        buttonCAD.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        buttonCAD.setText("Check available dates");
        buttonCAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCADActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonBDC, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
            .addComponent(buttonCAD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(buttonBDC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(buttonCAD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanel8);
        jPanel8.setBounds(0, 100, 200, 100);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mobile Dates Register", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        buttonMobileDates.setFont(new java.awt.Font("Rockwell", 1, 13)); // NOI18N
        buttonMobileDates.setText("Mobile Dates");
        buttonMobileDates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMobileDatesActionPerformed(evt);
            }
        });
        buttonMobileDates.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonMobileDatesKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonMobileDates, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(buttonMobileDates, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jPanel9);
        jPanel9.setBounds(0, 270, 200, 70);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1380, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCADActionPerformed
        CheckAvailbleDates CAD = null;
        CAD = new CheckAvailbleDates();
        CAD.setClosable(true);
        CAD.setMaximizable(true);
        desktopPane.add(CAD);
        desktopPane.setRequestFocusEnabled(true);
        CAD.show();

}//GEN-LAST:event_buttonCADActionPerformed

    private void buttonBDCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBDCActionPerformed

        MobileCampDetail MCD = null;
        
        try {
            MCD = new MobileCampDetail(desktopPane);
        } catch (SQLException ex) {
            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(MCD);
        MCD.setClosable(true);
        MCD.setMaximizable(true);
        MCD.setSize(new Dimension(927, 600));
//        try {
//            MCD.setMaximum(true);
//        } catch (PropertyVetoException ex) {
//            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
        desktopPane.add(MCD);
        desktopPane.setRequestFocusEnabled(true);
        MCD.show();
        
}//GEN-LAST:event_buttonBDCActionPerformed

    private void buttonMobileDatesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonMobileDatesKeyPressed

    }//GEN-LAST:event_buttonMobileDatesKeyPressed

    private void buttonMobileDatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMobileDatesActionPerformed
        MobileCampTables MCT = null;
        try {
            try {
                MCT = new MobileCampTables();
            } catch (IOException ex) {
                Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
        }
        MCT.setClosable(true);
        MCT.setMaximizable(true);
        desktopPane.add(MCT);
        desktopPane.setRequestFocusEnabled(true);
        MCT.show();

    }//GEN-LAST:event_buttonMobileDatesActionPerformed

    private void bloodGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bloodGroupBtnActionPerformed
        MobileCampDetail MCD = null;
        try {
            MCD = new MobileCampDetail(desktopPane);
        } catch (SQLException ex) {
            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
        }
        MCD.setClosable(true);
        MCD.setMaximizable(true);
        desktopPane.add(MCD);
        desktopPane.setRequestFocusEnabled(true);
        MCD.show();
    }//GEN-LAST:event_bloodGroupBtnActionPerformed

    private void groupingAndTTIRegButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupingAndTTIRegButtonActionPerformed
        
    }//GEN-LAST:event_groupingAndTTIRegButtonActionPerformed

    private void BloodGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BloodGroupButtonActionPerformed
        
    }//GEN-LAST:event_BloodGroupButtonActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void testingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testingButtonActionPerformed
        
    }//GEN-LAST:event_testingButtonActionPerformed

    private void time_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_time_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_time_txtActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        
    }//GEN-LAST:event_jButton22ActionPerformed

    private void sampleDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sampleDetailsButtonActionPerformed
        
    }//GEN-LAST:event_sampleDetailsButtonActionPerformed

    private void issueRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueRegisterButtonActionPerformed
        
    }//GEN-LAST:event_issueRegisterButtonActionPerformed

    private void generateIssuesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateIssuesButtonActionPerformed
        
    }//GEN-LAST:event_generateIssuesButtonActionPerformed

    private void issueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueBtnActionPerformed
        CheckAvailbleDates CAD = null;
        CAD = new CheckAvailbleDates();
        CAD.setClosable(true);
        CAD.setMaximizable(true);
        desktopPane.add(CAD);
        desktopPane.setRequestFocusEnabled(true);
        CAD.show();
    }//GEN-LAST:event_issueBtnActionPerformed

    private void passwordBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordBtnActionPerformed
        ChangePassword passwordReset = null;
        try {
            passwordReset = new ChangePassword(0);
        } catch (IOException ex) {
            Logger.getLogger(MedicalOfficer.class.getName()).log(Level.SEVERE, null, ex);
        }
        passwordReset.setClosable(true);
        passwordReset.setMaximizable(false);
        desktopPane.add(passwordReset);
        desktopPane.setRequestFocusEnabled(true);
        passwordReset.show();
    }//GEN-LAST:event_passwordBtnActionPerformed

    private void issueRegisterBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueRegisterBtn1ActionPerformed
        MobileCampTables MCT = null;
        try {
            try {
                MCT = new MobileCampTables();
            } catch (IOException ex) {
                Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
        }
        MCT.setClosable(true);
        MCT.setMaximizable(true);
        desktopPane.add(MCT);
        desktopPane.setRequestFocusEnabled(true);
        MCT.show();
    }//GEN-LAST:event_issueRegisterBtn1ActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Log out", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            this.dispose();
            Login bbms = new Login();
            bbms.setVisible(true);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PHI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(PHI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BloodGroupButton;
    private javax.swing.JButton bloodGroupBtn;
    private javax.swing.JButton buttonBDC;
    private javax.swing.JButton buttonCAD;
    private javax.swing.JButton buttonMobileDates;
    private javax.swing.JTextField date_txt;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JDesktopPane desktopPane1;
    private javax.swing.JButton generateIssuesButton;
    private javax.swing.JButton groupingAndTTIRegButton;
    private javax.swing.JButton issueBtn;
    private javax.swing.JButton issueRegisterBtn1;
    private javax.swing.JButton issueRegisterButton;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton passwordBtn;
    private javax.swing.JButton sampleDetailsButton;
    private javax.swing.JButton testingButton;
    private javax.swing.JTextField time_txt;
    // End of variables declaration//GEN-END:variables

}
