/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Ruchi;

import Controller.Ruchi.BloodGroupController;
import Controller.Ruchi.BloodPacketController;
import Controller.Ruchi.BloodTypeController;
import Controller.Ruchi.CrossMatchDetailController;
import Controller.Ruchi.DonorController;
import Controller.Ruchi.EmployeeController;
import Controller.TableCleaner;
import Controller.TableResizer;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.BloodPacket;
import model.CrossMatchDetail;

/**
 *
 * @author ruchiranga
 */
public class CrossMatch extends javax.swing.JInternalFrame {

    BloodPacketController packetController;
    BloodGroupController groupController;
    BloodTypeController typeController;
    DonorController donorController;
    EmployeeController employeeController;
    CrossMatchDetailController cdetailController;
    DefaultTableModel dtm;
    DefaultTableModel dtmCom;
    Requests parent;
    String requestNo;

    /**
     * Creates new form CrossMatch
     */
    public CrossMatch(String requestNo, Requests parent) {
        initComponents();
        packetController = new BloodPacketController();
        groupController = new BloodGroupController();
        typeController = new BloodTypeController();
        donorController = new DonorController();
        employeeController = new EmployeeController();
        cdetailController = new CrossMatchDetailController();
        this.requestNo = requestNo;
        this.parent = parent;

        ButtonGroup search_radios = new ButtonGroup();
        search_radios.add(sByDonorRadioButton);
        search_radios.add(sbyComponentRadioButton);
        search_radios.add(sbygroupRadioButton);

        String[] availabilityColumns = {"Packet ID", "Recieved By", "Blood group", "Component Type", "Date of expiry"};
        dtm = new DefaultTableModel(availabilityColumns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        availabilityTable.setModel(dtm);

        String[] compatabilityColumns = {"No.", "Packet ID", "Recieved By", "Blood group", "Date of expiry"};
        dtmCom = new DefaultTableModel(compatabilityColumns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        compatabilityTable.setModel(dtmCom);

        String[] groupList = null;

        try {
            groupList = groupController.getGroupList();
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
            compList = typeController.getComponentList();
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
            donorList = donorController.getDonorList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String donor : donorList) {
            donorsComboBox.addItem(donor);
        }

        String[] emp = null;

        try {
            emp = employeeController.getEmployeeList();
        } catch (SQLException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodGroupingAndTTI.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String name : emp) {
            medOfficerComboBox.addItem(name);
        }

        sbygroupRadioButton.setSelected(true);

        availabilityTable.setAutoCreateRowSorter(true);
        availabilityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableResizer.resizeColumnWidth(availabilityTable);
        TableResizer.resizeColumnWidth(compatabilityTable);

        dateChooser.setDateFormatString("YYYY-MM-dd");
        dateChooser.setDate(new java.util.Date());
        dateChooser.getDateEditor().setEnabled(false);
    }

    private boolean isAlreadyAddedToList(String packetID) {
        for (int i = 0; i < dtmCom.getRowCount(); i++) {
            if (dtmCom.getValueAt(i, 1).toString().equals(packetID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sbygroupRadioButton = new javax.swing.JRadioButton();
        sbyComponentRadioButton = new javax.swing.JRadioButton();
        sByDonorRadioButton = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        groupsComboBox = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        componentsComboBox = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        donorsComboBox = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        availabilityTable = new javax.swing.JTable();
        addToListButton = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        compatabilityTable = new javax.swing.JTable();
        clearListButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        medOfficerComboBox = new javax.swing.JComboBox();
        specialReservationCheckBox = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        markCrossMatchedButton = new javax.swing.JButton();

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

        sbyComponentRadioButton.setText("Search By Component");
        sbyComponentRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sbyComponentRadioButtonActionPerformed(evt);
            }
        });

        sByDonorRadioButton.setText("Search By Donor");
        sByDonorRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sByDonorRadioButtonActionPerformed(evt);
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
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(groupsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Search by Component"));

        jLabel2.setText("Component");

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
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(componentsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(componentsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search by Donor"));

        jLabel3.setText("Donor name");

        donorsComboBox.setEnabled(false);
        donorsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donorsComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(donorsComboBox, 0, 302, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(donorsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Available Blood Packets to be Crossmatched"));

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

        addToListButton.setText("Add to list");
        addToListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollPane)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 856, Short.MAX_VALUE)
                .addComponent(addToListButton))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addToListButton)
                .addContainerGap())
        );

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Compatible With"));

        compatabilityTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), null, null, null, null},
                { new Integer(2), null, null, null, null},
                { new Integer(3), null, null, null, null},
                { new Integer(4), null, null, null, null},
                { new Integer(5), null, null, null, null},
                { new Integer(6), null, null, null, null}
            },
            new String [] {
                "No", "Pack No", "Name of Donor ", "Group", "Expiry Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        compatabilityTable.setEnabled(false);
        jScrollPane2.setViewportView(compatabilityTable);

        clearListButton.setText("Clear List");
        clearListButton.setEnabled(false);
        clearListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(clearListButton))))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(clearListButton))
        );
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(clearListButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setText("Date:");

        jLabel10.setText("Name of the Medical Officer:");

        specialReservationCheckBox.setText("Special Reservation");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(medOfficerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(specialReservationCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(medOfficerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(specialReservationCheckBox))
                .addContainerGap())
        );

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        markCrossMatchedButton.setText("Mark Crossmatched");
        markCrossMatchedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markCrossMatchedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sbygroupRadioButton)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sbyComponentRadioButton)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sByDonorRadioButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(117, 117, 117))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLayeredPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(markCrossMatchedButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sbyComponentRadioButton)
                            .addComponent(sByDonorRadioButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sbygroupRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(markCrossMatchedButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sbygroupRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbygroupRadioButtonActionPerformed
        TableCleaner.clearTable(dtm);
        if (sbygroupRadioButton.isSelected()) {
            groupsComboBox.setEnabled(true);
            componentsComboBox.setEnabled(false);
            donorsComboBox.setEnabled(false);
        }
        String group = groupsComboBox.getSelectedItem().toString();
        BloodPacket[] results = packetController.searchByGroup(group);

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = donorController.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), donorName, packet.getBloodGroup(), packet.getBloodType(), packet.getDateOfExpiry().toString()};
            if (!isAlreadyAddedToList(packet.getPacketID())) {
                dtm.addRow(row);
            }
        }

        if (dtm.getRowCount() >= 0) {
            addToListButton.setEnabled(true);
        } else {
            addToListButton.setEnabled(false);
        }
    }//GEN-LAST:event_sbygroupRadioButtonActionPerformed

    private void sbygroupRadioButtonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_sbygroupRadioButtonPropertyChange
        TableCleaner.clearTable(dtm);
        if (sbygroupRadioButton.isSelected()) {
            groupsComboBox.setEnabled(true);
            componentsComboBox.setEnabled(false);
            donorsComboBox.setEnabled(false);
        }
        String group = groupsComboBox.getSelectedItem().toString();
        BloodPacket[] results = packetController.searchByGroup(group);
        for (BloodPacket packet : results) {
            String donorName = null;

            try {
                donorName = donorController.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), donorName, packet.getBloodGroup(), packet.getBloodType(), packet.getDateOfExpiry().toString()};

            if (!isAlreadyAddedToList(packet.getPacketID())) {
                dtm.addRow(row);
            }
        }
    }//GEN-LAST:event_sbygroupRadioButtonPropertyChange

    private void sbyComponentRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sbyComponentRadioButtonActionPerformed
        TableCleaner.clearTable(dtm);
        if (sbyComponentRadioButton.isSelected()) {
            groupsComboBox.setEnabled(false);
            componentsComboBox.setEnabled(true);
            donorsComboBox.setEnabled(false);
        }
        String component = componentsComboBox.getSelectedItem().toString();
        BloodPacket[] results = packetController.searchByComponent(component);

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = donorController.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), donorName, packet.getBloodGroup(), packet.getBloodType(), packet.getDateOfExpiry().toString()};

            if (!isAlreadyAddedToList(packet.getPacketID())) {
                dtm.addRow(row);
            }
        }

        if (dtm.getRowCount() >= 0) {
            addToListButton.setEnabled(true);
        } else {
            addToListButton.setEnabled(false);
        }
    }//GEN-LAST:event_sbyComponentRadioButtonActionPerformed

    private void sByDonorRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sByDonorRadioButtonActionPerformed
        TableCleaner.clearTable(dtm);
        if (sByDonorRadioButton.isSelected()) {
            groupsComboBox.setEnabled(false);
            componentsComboBox.setEnabled(false);
            donorsComboBox.setEnabled(true);
        }
        String donor = donorsComboBox.getSelectedItem().toString();
        BloodPacket[] results = null;
        try {
            results = packetController.searchByDonor(donor);
        } catch (SQLException ex) {
            Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (BloodPacket packet : results) {
            String donorName = null;
            try {
                donorName = donorController.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), donorName, packet.getBloodGroup(), packet.getBloodType(), packet.getDateOfExpiry().toString()};

            if (!isAlreadyAddedToList(packet.getPacketID())) {
                dtm.addRow(row);
            }
        }
        if (dtm.getRowCount() >= 0) {
            addToListButton.setEnabled(true);
        } else {
            addToListButton.setEnabled(false);
        }
    }//GEN-LAST:event_sByDonorRadioButtonActionPerformed

    private void groupsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupsComboBoxActionPerformed
        if (dtm != null) {
            TableCleaner.clearTable(dtm);
        }

        String group = groupsComboBox.getSelectedItem().toString();
        BloodPacket[] results = packetController.searchByGroup(group);

        for (BloodPacket packet : results) {

            String donorName = null;
            try {
                donorName = donorController.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), donorName, packet.getBloodGroup(), packet.getBloodType(), packet.getDateOfExpiry().toString()};

            if (!isAlreadyAddedToList(packet.getPacketID())) {
                dtm.addRow(row);
            }
        }

        if (dtm.getRowCount() >= 0) {
            addToListButton.setEnabled(true);
        } else {
            addToListButton.setEnabled(false);
        }
    }//GEN-LAST:event_groupsComboBoxActionPerformed

    private void componentsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_componentsComboBoxActionPerformed
        TableCleaner.clearTable(dtm);
        String component = componentsComboBox.getSelectedItem().toString();
        BloodPacket[] results = packetController.searchByComponent(component);

        for (BloodPacket packet : results) {

            String donorName = null;
            try {
                donorName = donorController.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), donorName, packet.getBloodGroup(), packet.getBloodType(), packet.getDateOfExpiry().toString()};

            if (!isAlreadyAddedToList(packet.getPacketID())) {
                dtm.addRow(row);
            }
        }
        if (dtm.getRowCount() >= 0) {
            addToListButton.setEnabled(true);
        } else {
            addToListButton.setEnabled(false);
        }
    }//GEN-LAST:event_componentsComboBoxActionPerformed

    private void donorsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donorsComboBoxActionPerformed
        TableCleaner.clearTable(dtm);
        String donor = donorsComboBox.getSelectedItem().toString();
        BloodPacket[] results = null;
        try {
            results = packetController.searchByDonor(donor);

        } catch (SQLException ex) {
            Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (BloodPacket packet : results) {

            String donorName = null;
            try {
                donorName = donorController.getDonorNameOf(packet.getNic());
            } catch (SQLException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BloodAndComponentAvailability.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] row = {packet.getPacketID(), donorName, packet.getBloodGroup(), packet.getBloodType(), packet.getDateOfExpiry().toString()};

            if (!isAlreadyAddedToList(packet.getPacketID())) {
                dtm.addRow(row);
            }
        }

        if (dtm.getRowCount() >= 0) {
            addToListButton.setEnabled(true);
        } else {
            addToListButton.setEnabled(false);
        }
    }//GEN-LAST:event_donorsComboBoxActionPerformed

    private void addToListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToListButtonActionPerformed
        boolean rowSelected = false;
        int selectedRow = 0;
        for (int i = 0; i < availabilityTable.getRowCount(); i++) {
            if (availabilityTable.isRowSelected(i)) {
                rowSelected = true;
                selectedRow = i;
                break;
            }
        }
        if (!rowSelected) {
            JOptionPane.showMessageDialog(this, "Please select the record of which packet you want to mark as Cross Matched");
        } else {

            String no = null;
            if (dtmCom.getRowCount() == 0) {
                no = "1";
            } else {
                no = "" + (dtmCom.getRowCount() + 1);
            }
            String packetID = (String) dtm.getValueAt(selectedRow, 0);
            String recievedBy = (String) dtm.getValueAt(selectedRow, 1);
            String bloodGroup = (String) dtm.getValueAt(selectedRow, 2);
            String doe = (String) dtm.getValueAt(selectedRow, 4);

            String[] row = {no, packetID, recievedBy, bloodGroup, doe};
            dtm.removeRow(selectedRow);
            dtmCom.addRow(row);

            if (dtm.getRowCount() == 0) {
                addToListButton.setEnabled(false);
            } else {
                addToListButton.setEnabled(true);
            }
            if (dtmCom.getRowCount() > 0) {
                clearListButton.setEnabled(true);
            } else {
                clearListButton.setEnabled(false);
            }

        }

    }//GEN-LAST:event_addToListButtonActionPerformed

    private void clearListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearListButtonActionPerformed
        TableCleaner.clearTable(dtmCom);
        if (groupsComboBox.isEnabled()) {
            groupsComboBox.setSelectedIndex(groupsComboBox.getSelectedIndex());
        } else if (componentsComboBox.isEnabled()) {
            componentsComboBox.setSelectedIndex(componentsComboBox.getSelectedIndex());
        } else {
            donorsComboBox.setSelectedIndex(donorsComboBox.getSelectedIndex());
        }

        if (dtmCom.getRowCount() == 0) {
            clearListButton.setEnabled(false);
        } else {
            clearListButton.setEnabled(true);
        }
        if (dtm.getRowCount() > 0) {
            addToListButton.setEnabled(true);
        } else {
            addToListButton.setEnabled(false);
        }
    }//GEN-LAST:event_clearListButtonActionPerformed

    private void markCrossMatchedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markCrossMatchedButtonActionPerformed
        if (dtmCom.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please add the blood packets needed to be crossmatched to the list", "", JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 0; i < dtmCom.getRowCount(); i++) {
                String packetID = (String) dtmCom.getValueAt(i, 1);
                int packetRes = 0;
                int specialRes = 0;
                int addDetailRes = 0;

                try {

                    packetRes = packetController.markCrossMatched(packetID);
                    if (specialReservationCheckBox.isSelected()) {
                        specialRes = packetController.markSpecialReservation(packetID);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CrossMatch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CrossMatch.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.util.Date chosenDate = dateChooser.getDate();
                Date date = new Date(chosenDate.getTime());
                String doneByName = (String) medOfficerComboBox.getSelectedItem();
                String doneByID;
                try {
                    doneByID = employeeController.getIDOf(doneByName);
                    addDetailRes = cdetailController.addDetail(new CrossMatchDetail(requestNo, packetID, date, doneByID));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CrossMatch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(CrossMatch.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (specialReservationCheckBox.isSelected() && packetRes == 1 && specialRes == 1 && addDetailRes == 1) {
                    JOptionPane.showMessageDialog(this, "Database updated successfully", "", JOptionPane.INFORMATION_MESSAGE);
                    parent.show();
                    this.dispose();
                } else if (!specialReservationCheckBox.isSelected() && packetRes == 1 && addDetailRes == 1) {
                    JOptionPane.showMessageDialog(this, "Database updated successfully", "", JOptionPane.INFORMATION_MESSAGE);
                    parent.show();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error in adding data to the database", "", JOptionPane.ERROR_MESSAGE);

                    this.dispose();
                    parent.show();
                }
            }
        }
    }//GEN-LAST:event_markCrossMatchedButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        parent.show();

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToListButton;
    private javax.swing.JTable availabilityTable;
    private javax.swing.JButton clearListButton;
    private javax.swing.JTable compatabilityTable;
    private javax.swing.JComboBox componentsComboBox;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JComboBox donorsComboBox;
    private javax.swing.JComboBox groupsComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton markCrossMatchedButton;
    private javax.swing.JComboBox medOfficerComboBox;
    private javax.swing.JRadioButton sByDonorRadioButton;
    private javax.swing.JRadioButton sbyComponentRadioButton;
    private javax.swing.JRadioButton sbygroupRadioButton;
    private javax.swing.JCheckBox specialReservationCheckBox;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
