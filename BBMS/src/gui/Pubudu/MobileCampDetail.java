package gui.Pubudu;

import controller.BloodCampController;
import controller.OrganizerController;
import controller.IDGenerator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.BloodCamp;
import model.Organizer;

public class MobileCampDetail extends javax.swing.JInternalFrame {

    public int i = 0;//check for the database availability and dont record twice
    int j = 0;
    JDesktopPane desktopPane;

    public MobileCampDetail(JDesktopPane pane) throws SQLException, ClassNotFoundException {
        initComponents();

        this.desktopPane = pane;

        comboNIC.setSelectedItem(null);

        //auto fill database data in to combo
        fillComboDatabase(comboNIC, "NIC");

        ComboSearch cs = new ComboSearch();
        cs.setSearchableCombo(comboNIC, true, "No NIC");

        comboNIC.setToolTipText("Enter NIC number of Organizer here");
        NameText.setToolTipText("Enter Organizer Name here");
        AddressText.setToolTipText("Enter Organizer Address here");
        TPText.setToolTipText("Enter Organizer TP Number here");
        comboBoxConto(comboNIC);

        String returnsresult = "";
        ResultSet rst;

        rst = BloodCampController.getResultedCampID();
        rst.last();
        String curID = null;
        try {
            curID = rst.getString("campID");
            try {
                returnsresult = IDGenerator.generateNextID(curID);
            } catch (Exception ex) {
                Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            returnsresult = "C000000001";
        }

        textcampno.setText(returnsresult);

    }

    private void fillComboDatabase(JComboBox combo, String column) {
        combo.removeAllItems();
        try {
            ResultSet rst = OrganizerController.getOrganizerDetails();
            while (rst.next()) {
                Object ob = rst.getString(column);
                combo.addItem(ob);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void updateCombo() {
        fillComboDatabase(comboNIC, "NIC");
    }

    MobileCampDetail(PHI aThis, boolean b) {
        initComponents();
        fillComboDatabase(comboNIC, "NIC");
        ComboSearch cs = new ComboSearch();
        cs.setSearchableCombo(comboNIC, true, "No NIC");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        addNewButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        textcampno = new javax.swing.JTextField();
        textexpdonors = new javax.swing.JTextField();
        calender = new org.freixas.jcalendar.JCalendar();
        textplace = new javax.swing.JTextField();
        buttoncancel = new javax.swing.JButton();
        buttonOK = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Organizer"));

        jLabel1.setText("Name");

        jLabel2.setText("NIC Number");

        jLabel3.setText("Address");

        jLabel4.setText("TP Number");

        NameText.setEnabled(false);
        NameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameTextActionPerformed(evt);
            }
        });
        NameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NameTextKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NameTextKeyTyped(evt);
            }
        });

        TPText.setEnabled(false);
        TPText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TPTextActionPerformed(evt);
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

        AddressText.setEnabled(false);
        AddressText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AddressTextKeyPressed(evt);
            }
        });

        TermLable.setText("Term");

        TermText.setEnabled(false);

        comboNIC.setEditable(true);
        comboNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNICActionPerformed(evt);
            }
        });
        comboNIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comboNICKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboNICKeyTyped(evt);
            }
        });

        addNewButton.setText("Add New");
        addNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(TermLable))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TPText)
                    .addComponent(NameText)
                    .addComponent(AddressText)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(comboNIC, 0, 187, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addNewButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TermText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboNIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(addNewButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AddressText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TPText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TermText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TermLable))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Camp"));

        jLabel5.setText("Camp Number");

        jLabel6.setText("Place");

        jLabel7.setText("Date");

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
                    .addComponent(textexpdonors, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calender, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textcampno, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGap(126, 126, 126)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(calender, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textexpdonors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        buttoncancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        buttoncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoncancelActionPerformed(evt);
            }
        });

        buttonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/accept.png"))); // NOI18N
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
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
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttoncancel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonOK, buttoncancel});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttoncancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOK, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NameTextActionPerformed

    private void NameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameTextKeyReleased
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER) {
            AddressText.requestFocus();
        }
    }//GEN-LAST:event_NameTextKeyReleased

    private void NameTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameTextKeyTyped
        char c = evt.getKeyChar();

        if (!Character.isAlphabetic(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_NameTextKeyTyped

    private void TPTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TPTextActionPerformed

    }//GEN-LAST:event_TPTextActionPerformed

    private void TPTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPTextKeyReleased
        char c = evt.getKeyChar();
        if (TPText.getText().length() >= 10) {

            if (c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == java.awt.event.KeyEvent.VK_DELETE) {
                TPText.setEditable(true);
            } else {
                TPText.setEditable(false);
            }

            textplace.requestFocus(true);

        }
    }//GEN-LAST:event_TPTextKeyReleased

    private void TPTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPTextKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE) || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_TPTextKeyTyped

    private void AddressTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddressTextKeyPressed
        char c = evt.getKeyChar();

        if (java.awt.event.KeyEvent.VK_ENTER == c || c == java.awt.event.KeyEvent.VK_TAB) {

            TPText.requestFocus(true);
        }
    }//GEN-LAST:event_AddressTextKeyPressed

    private void comboNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNICActionPerformed
        setData();
    }//GEN-LAST:event_comboNICActionPerformed

    private void comboNICKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboNICKeyReleased

    }//GEN-LAST:event_comboNICKeyReleased

    private void comboNICKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboNICKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE) || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_comboNICKeyTyped

    private void textcampnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textcampnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textcampnoActionPerformed

    private void textexpdonorsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textexpdonorsKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE) || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_textexpdonorsKeyTyped

    private void calenderDateChanged(org.freixas.jcalendar.DateEvent evt) {//GEN-FIRST:event_calenderDateChanged
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        try {
            String selectDate = getDate();
            int selectQuery = 0;
            ResultSet rst = BloodCampController.returnSelectedDate(selectDate, 0);
            dateControll();
            int i = 0;
            while (rst.next()) {

                i++;
            }
            if (i >= 3) {
                JOptionPane.showMessageDialog(this.calender, "DATE FULL");

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_calenderDateChanged

    private void calenderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calenderMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_calenderMousePressed

    private void textplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textplaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textplaceActionPerformed

    private void buttoncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoncancelActionPerformed
        textplace.setText("");

        textexpdonors.setText(null);
        comboNIC.requestFocus();

    }//GEN-LAST:event_buttoncancelActionPerformed

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed
        int addBloodCamp = 0;

        try {
            addBloodCamp = addBloodCamp();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (addBloodCamp == 1) {
            JOptionPane.showMessageDialog(this, "Blood Camp recorded successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            textplace.setText("");
            textexpdonors.setText(null);
            comboNIC.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to record camp details", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }//GEN-LAST:event_buttonOKActionPerformed

    private void addNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewButtonActionPerformed
        AddNewOrganizer window = null;
        window = new AddNewOrganizer(this);

        window.setClosable(true);
        window.setMaximizable(false);
        desktopPane.add(window);
        desktopPane.setRequestFocusEnabled(true);
        window.show();
        this.hide();
    }//GEN-LAST:event_addNewButtonActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MobileCampDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MobileCampDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MobileCampDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MobileCampDetail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MobileCampDetail(null).setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton addNewButton;
    private javax.swing.JButton buttonOK;
    private javax.swing.JButton buttoncancel;
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField textcampno;
    private javax.swing.JTextField textexpdonors;
    private javax.swing.JTextField textplace;
    // End of variables declaration//GEN-END:variables

//    private void addOrganizer() throws ClassNotFoundException, SQLException {
//        String og_Name = NameText.getText();
//        String nIC = comboNIC.getSelectedItem() + "";
//        String address = AddressText.getText();
//        String TPNO = TPText.getText();
//        int CampCount = Integer.parseInt(TermText.getText());
//
//        if (j == 0) {
//            Organizer organizer = new Organizer(nIC, og_Name, address, TPNO, CampCount);
//            OrganizerController.InsertOrganizerData(organizer);
//
//        } else {
//            Organizer organizer = new Organizer(CampCount, nIC);
//            OrganizerController.updateOrganizer(organizer);
//        }
//
//    }
    private String getDate() {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
        String datewithouttime = SDF.format(calender.getDate()).toString().substring(0, 10);
        return datewithouttime;
    }
//add blood camp details to database

    private int addBloodCamp() throws ClassNotFoundException, SQLException {

        String place = textplace.getText();
        int expDonars = Integer.parseInt(textexpdonors.getText());
        String nIC = comboNIC.getSelectedItem() + "";
        String date = getDate();
        String campId = textcampno.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date temp = null;
        try {
            temp = sdf.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(temp.getTime());
        java.sql.Date toSend = new java.sql.Date(temp.getTime());

        BloodCamp boBloodCamp = new BloodCamp(campId, place, toSend, expDonars, nIC);

        return BloodCampController.addBloodCamp(boBloodCamp);
    }

    private void comboBoxConto(JComboBox comboBox) {
        comboBox.setEditable(true);
        final JTextField txt = (JTextField) comboBox.getEditor().getEditorComponent();
        txt.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                char c = evt.getKeyChar();

//                if ((txt.getText()).length() >= 9) {
//                    String NIC;
//                    if (c == java.awt.event.KeyEvent.VK_DELETE || c == java.awt.event.KeyEvent.VK_BACK_SPACE) {
//                        comboNIC.setEditable(true);
//                        setData();
//
//                    } 
//                } else {
//
//                    if (Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == java.awt.event.KeyEvent.VK_DELETE) {
//                        comboNIC.setEditable(true);
//                    } else {
//                        comboNIC.setSelectedItem(txt.getText());
//                    }
//
//                }
            }
        });
    }

    public void setData() {

        try {
            String nic = comboNIC.getSelectedItem() + "";
            ResultSet rs = OrganizerController.getResultedNIC(nic);

            String Name = null;
            String Address = null;
            String Tp = null;

            int CampCount = 1;

            while (rs.next()) {
                j = 1;
                Name = rs.getString("Name");
                Address = rs.getString("Address");
                Tp = rs.getString("Tp");
                CampCount = rs.getInt("CampCount") + 1;
            }
            //enter data from data base in to the jframe

            NameText.setText(Name);
            AddressText.setText(Address);
            TPText.setText(Tp);
            if (!NameText.getText().equals("")) {
                TermText.setText(String.valueOf(CampCount));
            } else {
                TermText.setText("");
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public String getCurrentDate(Calendar currentDate) {
        //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
        return dateNow;
    }

    public void dateControll() {
        Calendar currentDate = Calendar.getInstance();
        String dateNow = getCurrentDate(currentDate);
        int current_Date = Integer.parseInt(dateNow.substring(8, 10));
        int selected_Date = Integer.parseInt(getDate().substring(8, 10));
        int current_Month = Integer.parseInt(dateNow.substring(5, 7));
        int selected_Month = Integer.parseInt(getDate().substring(5, 7));
        int current_Year = Integer.parseInt(dateNow.substring(0, 4));
        int selected_Year = Integer.parseInt(getDate().substring(0, 4));
        if (current_Year > selected_Year) {
            JOptionPane.showMessageDialog(this.calender, "Can't select this Date");
        } else if (current_Year == selected_Year) {
            if (current_Month > selected_Month) {
                JOptionPane.showMessageDialog(this.calender, "can't select this Date");
            } else if (current_Month == selected_Month) {
                if (current_Date >= selected_Date) {
                    JOptionPane.showMessageDialog(this.calender, "can't select this Date");
                }
            }
        }

    }
}
