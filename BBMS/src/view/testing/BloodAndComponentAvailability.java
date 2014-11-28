/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.testing;

import controller.common.SearchableCombo;
import controller.common.TableResizer;
import java.awt.image.BufferedImage;
import view.statistics.Requests;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.BloodPacket;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import view.item_management.ReagentRequests;

/**
 *
 * @author ruchiranga
 */
public class BloodAndComponentAvailability extends javax.swing.JInternalFrame {

    AvailabilityHandler handler;
    DefaultTableModel dtm;
    Requests parent;

    /**
     * Creates new form BloodAndComponentAvailability
     */
    public BloodAndComponentAvailability() throws FileNotFoundException, IOException {
        initComponents();
        FileInputStream imgStream = null;
        File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
        imgStream = new FileInputStream(imgfile);
        BufferedImage bi = ImageIO.read(imgStream);
        ImageIcon myImg = new ImageIcon(bi);
        this.setFrameIcon(myImg);
        setTitle("Blood and Component Availability");
        handler = new AvailabilityHandler();

        ButtonGroup search_radios = new ButtonGroup();
        search_radios.add(sByDonorRadioButton);
        search_radios.add(sbyComponentRadioButton);
        search_radios.add(sbygroupRadioButton);

        String[] columns = {"Packet ID", "Blood group", "Component Type", "Received By", "Date of expiry", "Date of collection", "Cross matched", "Under observation"};
        dtm = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        availabilityTable.setModel(dtm);

        String[] groupList = null;

        try {
            groupList = handler.getGroupList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String group : groupList) {
            groupsComboBox.addItem(group);
        }

        String[] compList = null;

        try {
            compList = handler.getComponentList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String comp : compList) {
            componentsComboBox.addItem(comp);
        }

        String[] donorList = null;

        try {
            donorList = handler.getDonorList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String donor : donorList) {
            donorsComboBox.addItem(donor);
        }

        sbygroupRadioButton.setSelected(true);

        availabilityTable.setAutoCreateRowSorter(true);
        availabilityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableResizer.resizeColumnWidth(availabilityTable);

        new SearchableCombo().setSearchableCombo(donorsComboBox, true);

    }

    BloodAndComponentAvailability(Requests aThis) {
        initComponents();

        handler = new AvailabilityHandler();

        ButtonGroup search_radios = new ButtonGroup();
        search_radios.add(sByDonorRadioButton);
        search_radios.add(sbyComponentRadioButton);
        search_radios.add(sbygroupRadioButton);

        String[] columns = {"Packet ID", "Blood group", "Component Type", "Recieved By", "Date of expiry", "Date of collection", "Cross matched", "Under observation"};
        dtm = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        availabilityTable.setModel(dtm);

        String[] groupList = null;

        try {
            groupList = handler.getGroupList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String group : groupList) {
            groupsComboBox.addItem(group);
        }

        String[] compList = null;

        try {
            compList = handler.getComponentList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String comp : compList) {
            componentsComboBox.addItem(comp);
        }

        String[] donorList = null;

        try {
            donorList = handler.getDonorList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String donor : donorList) {
            donorsComboBox.addItem(donor);
        }

        sbygroupRadioButton.setSelected(true);

        availabilityTable.setAutoCreateRowSorter(true);
        availabilityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        parent = aThis;
        parent.hide();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sbygroupRadioButton = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        groupsComboBox = new javax.swing.JComboBox();
        sbyComponentRadioButton = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        componentsComboBox = new javax.swing.JComboBox();
        sByDonorRadioButton = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        donorsComboBox = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        availabilityTable = new javax.swing.JTable();
        markUncrossMatchedButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setTitle("Blood and Component Availability");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        sbygroupRadioButton.setText("Search by Group");
        sbygroupRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbygroupRadioButtonActionPerformed(evt);
            }
        });
        sbygroupRadioButton.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                sbygroupRadioButtonPropertyChange(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Search by Group"));

        jLabel1.setText("Group");

        groupsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupsComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(groupsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(groupsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        sbyComponentRadioButton.setText("Search By Component");
        sbyComponentRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbyComponentRadioButtonActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Search by Component"));

        jLabel3.setText("Component");

        componentsComboBox.setEnabled(false);
        componentsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componentsComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(componentsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(componentsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        sByDonorRadioButton.setText("Search By Donor");
        sByDonorRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sByDonorRadioButtonActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Search by Donor"));

        jLabel4.setText("Donor name");

        donorsComboBox.setEditable(true);
        donorsComboBox.setEnabled(false);
        donorsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donorsComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(donorsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(donorsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Available Blood Packets"));

        availabilityTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Packet ID", "Blood Group", "Component type", "Recieved by", "Date of expiry", "Date of collection", "Cross matched", "Under observation", "Specially reserved"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        availabilityTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tableScrollPane.setViewportView(availabilityTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollPane)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        markUncrossMatchedButton.setText("Mark Uncross Matched");
        markUncrossMatchedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markUncrossMatchedButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Print Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
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
                .addGap(138, 138, 138)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sbygroupRadioButton)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sbyComponentRadioButton)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sByDonorRadioButton)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(markUncrossMatchedButton)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sbygroupRadioButton)
                    .addComponent(sbyComponentRadioButton)
                    .addComponent(sByDonorRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(markUncrossMatchedButton))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel6.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("View Blood Availability");
        jPanel6.add(jLabel5);
        jLabel5.setBounds(368, 20, 290, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FormHeader.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel6.add(jLabel2);
        jLabel2.setBounds(0, 0, 940, 73);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(109, 109, 109)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(92, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(539, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        if (parent != null) {
            parent.show();
        }
    }//GEN-LAST:event_formInternalFrameClosed

    private void sbygroupRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbygroupRadioButtonActionPerformed
        handler.clearTable(dtm);
        if (sbygroupRadioButton.isSelected()) {
            groupsComboBox.setEnabled(true);
            componentsComboBox.setEnabled(false);
            donorsComboBox.setEnabled(false);
        }
        String group = groupsComboBox.getSelectedItem().toString();
        BloodPacket[] results = handler.searchByGroup(group);

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = handler.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), packet.getBloodGroup(), packet.getBloodType(), donorName, packet.getDateOfExpiry().toString(), packet.getDateOfDonation().toString(), Integer.toString(packet.isIsCrossmatched()).equals("0") ? "No" : "Yes", Integer.toString(packet.isIsUnderObservation()).equals("0") ? "No" : "Yes"};

            dtm.addRow(row);
        }
    }//GEN-LAST:event_sbygroupRadioButtonActionPerformed

    private void sbygroupRadioButtonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_sbygroupRadioButtonPropertyChange
        handler.clearTable(dtm);
        if (sbygroupRadioButton.isSelected()) {
            groupsComboBox.setEnabled(true);
            componentsComboBox.setEnabled(false);
            donorsComboBox.setEnabled(false);
        }
        String group = groupsComboBox.getSelectedItem().toString();
        BloodPacket[] results = handler.searchByGroup(group);

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = handler.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), packet.getBloodGroup(), packet.getBloodType(), donorName, packet.getDateOfExpiry().toString(), packet.getDateOfDonation().toString(), Integer.toString(packet.isIsCrossmatched()).equals("0") ? "No" : "Yes", Integer.toString(packet.isIsUnderObservation()).equals("0") ? "No" : "Yes"};

            dtm.addRow(row);
        }
    }//GEN-LAST:event_sbygroupRadioButtonPropertyChange

    private void groupsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupsComboBoxActionPerformed
        if (dtm != null) {
            handler.clearTable(dtm);
        }

        String group = groupsComboBox.getSelectedItem().toString();
        BloodPacket[] results = handler.searchByGroup(group);

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = handler.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), packet.getBloodGroup(), packet.getBloodType(), donorName, packet.getDateOfExpiry().toString(), packet.getDateOfDonation().toString(), Integer.toString(packet.isIsCrossmatched()).equals("0") ? "No" : "Yes", Integer.toString(packet.isIsUnderObservation()).equals("0") ? "No" : "Yes"};

            dtm.addRow(row);
        }
    }//GEN-LAST:event_groupsComboBoxActionPerformed

    private void sbyComponentRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbyComponentRadioButtonActionPerformed
        handler.clearTable(dtm);
        if (sbyComponentRadioButton.isSelected()) {
            groupsComboBox.setEnabled(false);
            componentsComboBox.setEnabled(true);
            donorsComboBox.setEnabled(false);
        }
        String component = componentsComboBox.getSelectedItem().toString();
        BloodPacket[] results = handler.searchByComponent(component);

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = handler.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), packet.getBloodGroup(), packet.getBloodType(), donorName, packet.getDateOfExpiry().toString(), packet.getDateOfDonation().toString(), Integer.toString(packet.isIsCrossmatched()).equals("0") ? "No" : "Yes", Integer.toString(packet.isIsUnderObservation()).equals("0") ? "No" : "Yes"};

            dtm.addRow(row);
        }
    }//GEN-LAST:event_sbyComponentRadioButtonActionPerformed

    private void componentsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_componentsComboBoxActionPerformed
        handler.clearTable(dtm);

        String component = componentsComboBox.getSelectedItem().toString();
        BloodPacket[] results = handler.searchByComponent(component);

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = handler.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), packet.getBloodGroup(), packet.getBloodType(), donorName, packet.getDateOfExpiry().toString(), packet.getDateOfDonation().toString(), Integer.toString(packet.isIsCrossmatched()).equals("0") ? "No" : "Yes", Integer.toString(packet.isIsUnderObservation()).equals("0") ? "No" : "Yes"};

            dtm.addRow(row);
        }
    }//GEN-LAST:event_componentsComboBoxActionPerformed

    private void sByDonorRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sByDonorRadioButtonActionPerformed
        handler.clearTable(dtm);
        if (sByDonorRadioButton.isSelected()) {
            groupsComboBox.setEnabled(false);
            componentsComboBox.setEnabled(false);
            donorsComboBox.setEnabled(true);
        }
        String donor = donorsComboBox.getSelectedItem().toString();
        BloodPacket[] results = null;
        try {
            results = handler.searchByDonor(donor);
        } catch (SQLException ex) {
            Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = handler.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), packet.getBloodGroup(), packet.getBloodType(), donorName, packet.getDateOfExpiry().toString(), packet.getDateOfDonation().toString(), Integer.toString(packet.isIsCrossmatched()).equals("0") ? "No" : "Yes", Integer.toString(packet.isIsUnderObservation()).equals("0") ? "No" : "Yes"};

            dtm.addRow(row);
        }
    }//GEN-LAST:event_sByDonorRadioButtonActionPerformed

    private void donorsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donorsComboBoxActionPerformed
        handler.clearTable(dtm);

        String donor = donorsComboBox.getSelectedItem().toString();
        donor = donor.trim();
        BloodPacket[] results = null;
        try {
            results = handler.searchByDonor(donor);
        } catch (SQLException ex) {
            Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = handler.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), packet.getBloodGroup(), packet.getBloodType(), donorName, packet.getDateOfExpiry().toString(), packet.getDateOfDonation().toString(), (Integer.toString(packet.isIsCrossmatched()).equals("0")) ? "No" : "Yes", Integer.toString(packet.isIsUnderObservation()).equals("0") ? "No" : "Yes"};

            dtm.addRow(row);
        }
    }//GEN-LAST:event_donorsComboBoxActionPerformed

    private void markUncrossMatchedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markUncrossMatchedButtonActionPerformed
        boolean rowSelected = false;
        for (int i = 0; i < availabilityTable.getRowCount(); i++) {
            if (availabilityTable.isRowSelected(i)) {
                rowSelected = true;
                break;
            }
        }
        if (!rowSelected) {
            JOptionPane.showMessageDialog(this, "Please select the record of which packet you want to mark as Uncross Matched");
        } else {
            int selection = availabilityTable.getSelectedRow();
            if (dtm.getValueAt(selection, 6).toString().equals("No")) {
                JOptionPane.showMessageDialog(this, "Selected Blood Packet is already Uncross Matched!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int res = JOptionPane.showConfirmDialog(this, "Are you sure?", "Are you sure?", JOptionPane.YES_NO_OPTION);
                if (res == 0) {
                    try {
                        String packetID = (String) dtm.getValueAt(selection, 0);

                        int response;
                        response = handler.setAsUncrossMatched(packetID);

                        if (response == 1) {
                            dtm.setValueAt("No", selection, 6);
                            JOptionPane.showMessageDialog(this, "Database updated successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Error! Failed to update database", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }
    }//GEN-LAST:event_markUncrossMatchedButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            this.setClosed(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String searchCategory="";
        String choice="";
        if(sbygroupRadioButton.isSelected()){
            searchCategory="Group";
            choice=groupsComboBox.getSelectedItem().toString();
        }
        
        if(sbyComponentRadioButton.isSelected()){
            searchCategory="Component";
            choice=componentsComboBox.getSelectedItem().toString();
        }
        
        if(sByDonorRadioButton.isSelected()){
            searchCategory="Donor";
            choice=donorsComboBox.getSelectedItem().toString();
        }
        try {
            JasperReport jr = JasperCompileManager.compileReport("./src/reports/testing/AvailableBloodPacketsReport.jrxml");
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            params.put("searchCategory",searchCategory);
            params.put("choice",choice);
                
            JRTableModelDataSource dataSource = new JRTableModelDataSource(availabilityTable.getModel());
            
            JasperPrint jp = JasperFillManager.fillReport(jr, params,dataSource);
            
            JasperViewer.viewReport(jp);
        } catch (JRException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable availabilityTable;
    private javax.swing.JComboBox componentsComboBox;
    private javax.swing.JComboBox donorsComboBox;
    private javax.swing.JComboBox groupsComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton markUncrossMatchedButton;
    private javax.swing.JRadioButton sByDonorRadioButton;
    private javax.swing.JRadioButton sbyComponentRadioButton;
    private javax.swing.JRadioButton sbygroupRadioButton;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
