package gui.Pubudu;


import com.sun.glass.events.KeyEvent;
import connection.DBConnection;
import connection.DBHandler;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class MobileCampDetails extends javax.swing.JFrame {
    
    public int i=0;//check for the database availability and dont record twice

    public MobileCampDetails() throws ClassNotFoundException {
        initComponents();
        FillComboBox.fillComboDatabase(comboNIC, "Organizer", "NIC_No");
        ComboSearch cs = new ComboSearch();
        cs.setSearchableCombo(comboNIC, true, "No NIC");
        comboNIC.setSelectedItem(null);
        comboNIC.setToolTipText("Enter NIC number of Organizer here");
        NameText.setToolTipText("Enter Organizer Name here");
        AddressText.setToolTipText("Enter Organizer Adderss here");
        TPText.setToolTipText("Enter Organizer TP Number here");
        comboBoxConto(comboNIC);

    }

    MobileCampDetails(PHI aThis, boolean b) {
        initComponents();
        FillComboBox.fillComboDatabase(comboNIC, "Organizer", "NIC_No");
        ComboSearch cs = new ComboSearch();
        cs.setSearchableCombo(comboNIC, true, "No NIC");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        NameText = new javax.swing.JTextField();
        TPText = new javax.swing.JTextField();
        AddressText = new javax.swing.JTextField();
        TermLable = new javax.swing.JLabel();
        TermText = new javax.swing.JTextField();
        comboNIC = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textcampno = new javax.swing.JTextField();
        textexpdonors = new javax.swing.JTextField();
        calender = new org.freixas.jcalendar.JCalendar();
        textplace = new javax.swing.JTextField();
        buttonOK = new javax.swing.JButton();
        buttonprinter = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        textregisterID = new javax.swing.JTextField();
        buttoncancel = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Organizer"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("NIC Number");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Address");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("TP Number");

        NameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameTextActionPerformed(evt);
            }
        });
        NameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NameTextKeyReleased(evt);
            }
        });

        TPText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TPTextKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TPTextKeyTyped(evt);
            }
        });

        AddressText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AddressTextKeyPressed(evt);
            }
        });

        TermLable.setText("Term");

        comboNIC.setEditable(true);
        comboNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNICActionPerformed(evt);
            }
        });
        comboNIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboNICKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(NameText, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(AddressText)
                                    .addComponent(comboNIC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TPText, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(TermLable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TermText, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AddressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TPText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TermText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TermLable))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Camp"));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Camp Number");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Place");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Date");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("EXP: Donars");

        textcampno.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textcampno.setEnabled(false);
        textcampno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textcampnoActionPerformed(evt);
            }
        });

        textexpdonors.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textexpdonorsKeyTyped(evt);
            }
        });

        calender.addDateListener(new org.freixas.jcalendar.DateListener() {
            public void dateChanged(org.freixas.jcalendar.DateEvent evt) {
                calenderDateChanged(evt);
            }
        });
        calender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                calenderMousePressed(evt);
            }
        });

        textplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textplaceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calender, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textcampno, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textexpdonors, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textplace, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textcampno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(calender, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textexpdonors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(50, 50, 50))
        );

        buttonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/accept.png"))); // NOI18N
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        buttonprinter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Print.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Register ID");

        buttoncancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        buttoncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoncancelActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9)
                .addGap(59, 59, 59)
                .addComponent(textregisterID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(550, 550, 550)
                .addComponent(buttoncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(buttonprinter, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textregisterID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttoncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonprinter, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**/
    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        try {
            addOrganizer();
            showDetails();
            addBloodCamp();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileCampDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MobileCampDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonOKActionPerformed

    private void buttoncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoncancelActionPerformed
        AddressText.setText("");
        textplace.setText("");
        NameText.setText(null);

        TPText.setText(null);

        textexpdonors.setText(null);
        comboNIC.removeAllItems();
        NameText.requestFocus();


    }//GEN-LAST:event_buttoncancelActionPerformed

    private void NameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameTextKeyReleased
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER) {
            AddressText.requestFocus();
        }
    }//GEN-LAST:event_NameTextKeyReleased

    private void TPTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPTextKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE) || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_TPTextKeyTyped

    private void TPTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPTextKeyReleased
        char c = evt.getKeyChar();
        if (TPText.getText().length() >= 10) {

            if (c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == java.awt.event.KeyEvent.VK_DELETE) {
                TPText.setEditable(true);
            } else {
                TPText.setEditable(false);
            }

            textplace.requestFocus();
        }
    }//GEN-LAST:event_TPTextKeyReleased

      
    
    private void textexpdonorsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textexpdonorsKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE) || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_textexpdonorsKeyTyped

    private void calenderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calenderMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_calenderMousePressed

    private void calenderDateChanged(org.freixas.jcalendar.DateEvent evt) {//GEN-FIRST:event_calenderDateChanged

        try {
            String selectDate = getDate();
            String query = "select DATE(DATE) AS date from bloodcamp HAVING date='" + selectDate + "'";
            ResultSet rst = DBHandler.getData(DBConnection.getConnectionToDB(), query);
            int i = 0;
            while (rst.next()) {

                i++;
            }
            if (i >= 3) {
                JOptionPane.showMessageDialog(this.calender, "DATE FULL");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileCampDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MobileCampDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_calenderDateChanged

    private void textcampnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textcampnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textcampnoActionPerformed

    private void NameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameTextActionPerformed

    private void textplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textplaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textplaceActionPerformed

    private void AddressTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddressTextKeyPressed
        char c = evt.getKeyChar();

        if (java.awt.event.KeyEvent.VK_ENTER == c || c == java.awt.event.KeyEvent.VK_TAB) {

            TPText.requestFocus(true);
        }
    }//GEN-LAST:event_AddressTextKeyPressed

    private void comboNICKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboNICKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE) || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_comboNICKeyTyped

    private void comboNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNICActionPerformed

    }//GEN-LAST:event_comboNICActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MobileCampDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MobileCampDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MobileCampDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MobileCampDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MobileCampDetails().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MobileCampDetails.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddressText;
    private javax.swing.JTextField NameText;
    private javax.swing.JTextField TPText;
    private javax.swing.JLabel TermLable;
    private javax.swing.JTextField TermText;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonOK;
    private javax.swing.JButton buttoncancel;
    private javax.swing.JButton buttonprinter;
    private org.freixas.jcalendar.JCalendar calender;
    private javax.swing.JComboBox comboNIC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField textcampno;
    private javax.swing.JTextField textexpdonors;
    private javax.swing.JTextField textplace;
    private javax.swing.JTextField textregisterID;
    // End of variables declaration//GEN-END:variables
private void addOrganizer() throws ClassNotFoundException, SQLException {
        String og_Name = NameText.getText();
        String nIC = comboNIC.getSelectedItem() + "";
        String address = AddressText.getText();
        String TPNO = TPText.getText();
        int Terms = Integer.parseInt(TermText.getText());
        Terms ++;
       
        if(i==0){    
            Terms--;
             String query = "INSERT INTO Organizer VALUES('" + og_Name + "','" + nIC + "','" + address + "','" + TPNO + "','"+Terms+"')";
             DBHandler.setData(DBConnection.getConnectionToDB(), query);
        }    
        else{
           // String query = "Insert INTo Organizer VALUES()";
        }
    }

    private String getDate() {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
       // DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

       // Date StartDate1 = df.parse(txtStartDate.getText());
        //java.sql.Date StartDate = new java.sql.Date(StartDate1.getTime());
        String datewithouttime;
        datewithouttime = SDF.format(calender.getDate()).toString().substring(0,10);
        System.out.println(".....................");
        System.out.println(datewithouttime);
        return datewithouttime;
    }

    private void addBloodCamp() throws ClassNotFoundException, SQLException {

        String place = textplace.getText();
        int ExpDonars = Integer.parseInt(textexpdonors.getText());
        String nIC = comboNIC.getSelectedItem() + "";
        String Date = getDate();
       
        
        String query = "INSERT INTO bloodcamp (PLACE, DATE, EXP_DON, NIC_No) VALUES('" + place + "','" + Date + "','" + ExpDonars + "','" + nIC + "')";
        DBHandler.setData(DBConnection.getConnectionToDB(), query);

    }

    private void showDetails() throws ClassNotFoundException, SQLException {
        controller con = new controller();
        try {
            // create our mysql database connection            
            Connection conn = con.createConnection();

            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT CAMP_ID FROM bloodcamp ORDER BY CAMP_ID DESC LIMIT 1";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset            
            
            ResultSet rs = con.executeQuery(conn, query);
                    
            int x = 0;
            // iterate through the java resultset
            while (rs.next()) {
                int id = rs.getInt("CAMP_ID");
                x = id + 1;
                //System.out.format("%s\n", id);
            }
            System.out.println(x - 1);
            textcampno.setText(String.valueOf(x - 2));
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
    private void comboBoxConto(JComboBox comboBox){
        comboBox.setEditable(true);
        final JTextField txt=(JTextField)comboBox.getEditor().getEditorComponent();
        txt.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {               
                 char c = evt.getKeyChar();
                 System.out.println((comboNIC.getSelectedItem()+"").length());
        if ((txt.getText()).length() >= 9) {
            String NIC;
            if (c == java.awt.event.KeyEvent.VK_DELETE || c == java.awt.event.KeyEvent.VK_BACK_SPACE) { 
                comboNIC.setEditable(true);
                
            } else {
                comboNIC.setSelectedItem(txt.getText());
                comboNIC.setEditable(false);               
                setdata();                
                NameText.requestFocus();
            }

        } else {
            comboNIC.setEditable(true);            
        }
            }
            
            
});
    }
    
    public void setdata(){
        controller con = new controller();
        try {           
            // create our mysql database connection
            
            Connection conn = con.createConnection();
            
            String NIC_No;
            String query;
            String Nic = comboNIC.getSelectedItem() + "";

            query = ("SELECT * FROM organizer HAVING NIC_No = '" + Nic + "'");

           

            // execute the query, and get a java resultset            
            ResultSet rs = con.executeQuery(conn,query);

            String Name = null;
            String Address = null;
            String Tp = null;
            int Term = 1;
             
            while (rs.next()) {
                i=1;
                Name = rs.getString("Name");
                Address = rs.getString("Adderess");
                Tp = rs.getString("Tp");
                Term = rs.getInt("Term") + 1;
            }
            //enter data from data base in to the jframe

            NameText.setText(Name);
            AddressText.setText(Address);
            TPText.setText(Tp);
            TermText.setText(String.valueOf(Term));          

            
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

}
