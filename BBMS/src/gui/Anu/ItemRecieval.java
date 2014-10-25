/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Anu;

import controller.IDGenerator;
import controller.TableResizer;
import controller.anu.EmployeeController;
import controller.anu.ItemController;
import controller.anu.ReagentRequestController;
import controller.anu.RecievedLogController;
import controller.anu.ItemRecievedLogController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ItemReceivedLog;
import model.ItemRecievedDetail;
import model.ReagentRequest;
import model.ReagentRequestDetail;

/**
 *
 * @author Anuradha
 */
public class ItemRecieval extends javax.swing.JInternalFrame {

    String[] title_recieved = {"Item ID", "Item Type", "Quantity", "Units"};
    DefaultTableModel receivedItemsDtm = new DefaultTableModel(title_recieved, 0);

    DefaultTableModel searchreceivedItemsDtm = new DefaultTableModel(title_recieved, 0);

    Calendar currenttime = Calendar.getInstance();
    java.util.Date today = new java.util.Date((currenttime.getTime()).getTime());

    /**
     * Creates new form ItemRecieval
     */
    public ItemRecieval() {
        initComponents();
        setItemIdComboItems();
        getNextRecievalID();
        viewRecievalDateCalendar.setDate(today);

    }

    private void disAbleUpdateFields() {
        recievedFromText.setEnabled(false);
        viewRequestTable.setEnabled(false);
        editItemBtn.setEnabled(false);
        requestAddBtn1.setEnabled(false);
        removeItemBtn1.setEnabled(false);
        updateDatabaseBtn1.setEnabled(false);
    }

    private void enableUpdateFields() {
        recievedFromText.setEnabled(true);
        viewRequestTable.setEnabled(true);
        editItemBtn.setEnabled(true);
        removeItemBtn1.setEnabled(true);
        updateDatabaseBtn1.setEnabled(true);
    }

    private void setSearchData() {
        disAbleUpdateFields();
        if (viewRecievalIDCombo.getSelectedItem() != null) {
            searchRequestingOfficerLabel.setVisible(true);
            String requestID = "" + viewRecievalIDCombo.getSelectedItem();
            try {
                searchreceivedItemsDtm = new DefaultTableModel(title_recieved, 0);
                //"Item ID", "Item Type", "Quantity", "Units"
                viewRequestTable.setModel(searchreceivedItemsDtm);
                ResultSet rst = ItemRecievedLogController.getRecievalByID(requestID);
                while (rst.next()) {
                    recievedFromText.setText(rst.getString("RecievedFrom"));
                }
                setSearchItemIdComboItems();
                ResultSet rstDetail = ItemRecievedLogController.getRecievedDetail(requestID);
                while (rstDetail.next()) {
                    String[] row = {"", "", "", "", ""};
                    row[0] = rstDetail.getString("ItemID");
                    requestItemIDCombo1.removeItem(row[0]);
                    row[1] = rstDetail.getString("ItemType");
                    row[2] = rstDetail.getString("Qty");
                    row[3] = rstDetail.getString("Units");
                    searchreceivedItemsDtm.addRow(row);
                }
                TableResizer.resizeColumnWidth(viewRequestTable);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            searchreceivedItemsDtm = new DefaultTableModel(title_recieved, 0);
            viewRequestTable.setModel(searchreceivedItemsDtm);
        }
    }

    private void clearUpdateFields() {
        viewitemIDText.setText("");
        viewItemTypeText.setText("");
        viewRequestQtyText.setText("");
        searchUnitsLabel.setText("");
        updateItemBtn.setEnabled(false);
    }

    public void getNextRecievalID() {
        try {
            String returnID = "";
            ResultSet rst;

            rst = ItemRecievedLogController.getRecievedIDs();

            rst.last();
            String curID = null;
            try {
                curID = rst.getString("RecievedID");
                try {
                    returnID = IDGenerator.generateNextID(curID);
                } catch (Exception ex) {
                    Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                returnID = "IR00000001";
            }
            recievedIDTxt.setText(returnID);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSearchItemIdComboItems() {
        requestItemIDCombo1.removeAllItems();
        try {
            ResultSet rst = ItemController.getAllItems();
            String id = null;
            while (rst.next()) {
                id = rst.getString("itemID");
                requestItemIDCombo1.addItem(id);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setItemIdComboItems() {
        itemIDCombo.removeAllItems();
        try {
            ResultSet rst = ItemController.getAllItems();
            String id = null;
            while (rst.next()) {
                id = rst.getString("itemID");
                itemIDCombo.addItem(id);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void setRecievedTableItems() {
//        try {
//            receivedItemsDtm = new DefaultTableModel(title_recieved, 0);
//            recievedTable.setModel(receivedItemsDtm);
//
//            /*date collected*/
//            java.util.Date dateR = receivedDateCalendar.getDate();
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            String dateCollection = df.format(dateR);
//            java.sql.Date sqlDateR = new java.sql.Date(dateR.getTime());
//
//            ResultSet rst = RecievedLogController.getAllLogs(sqlDateR);
//            String id = null;
//            String type = null;
//            double quantity = 0;
//            String unts = null;
//            String recievedFrom = null;
//
//            while (rst.next()) {
//
//                id = rst.getString("itemID");
//
//                ResultSet rst2 = ItemController.getItem(id);
//                while (rst2.next()) {
//                    type = rst2.getString("itemType");
//                    unts = rst2.getString("units");
//
//                }
//
//                quantity = rst.getDouble("qtyRecieved");
//                recievedFrom = rst.getString("recievedFrom");
//
//                String[] ar = {id, type, "" + quantity, unts, recievedFrom};
//                receivedItemsDtm.addRow(ar);
//
//            }
//
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        itemIDCombo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        recievedQtyText = new javax.swing.JTextField();
        addRecievedItemBtn = new javax.swing.JButton();
        receivedDateCalendar = new com.toedter.calendar.JCalendar();
        unitLabel = new javax.swing.JLabel();
        itemTypeLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        recievedTable = new javax.swing.JTable();
        removeItemBtn = new javax.swing.JButton();
        updateDatabaseBtn = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        fromText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        recievedIDTxt = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        viewRequestTable = new javax.swing.JTable();
        editItemBtn = new javax.swing.JButton();
        removeItemBtn1 = new javax.swing.JButton();
        updateDatabaseBtn1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        searchRequestingOfficerLabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        viewitemIDText = new javax.swing.JTextField();
        viewItemTypeText = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        viewRequestQtyText = new javax.swing.JTextField();
        updateItemBtn = new javax.swing.JButton();
        searchUnitsLabel = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        requestItemIDCombo1 = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        requestItemType1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        requestQtyText1 = new javax.swing.JTextField();
        requestAddBtn1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        requestQtyUnitLabel1 = new javax.swing.JLabel();
        recievedFromText = new javax.swing.JTextField();
        viewRecievalDateCalendar = new com.toedter.calendar.JDateChooser();
        jLabel54 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        viewRecievalIDCombo = new javax.swing.JComboBox();
        editRecievalBtn = new javax.swing.JButton();

        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Recieved Log"));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Item"));

        jLabel1.setText("Item ID");

        jLabel3.setText("Item type");

        itemIDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIDComboActionPerformed(evt);
            }
        });

        jLabel4.setText("Quatity");

        recievedQtyText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recievedQtyTextActionPerformed(evt);
            }
        });
        recievedQtyText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                recievedQtyTextKeyTyped(evt);
            }
        });

        addRecievedItemBtn.setText("Add Item");
        addRecievedItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRecievedItemBtnActionPerformed(evt);
            }
        });

        receivedDateCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                receivedDateCalendarPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(receivedDateCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(itemIDCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(recievedQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(unitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(addRecievedItemBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(itemIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(itemTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recievedQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(addRecievedItemBtn)
                .addGap(18, 18, 18)
                .addComponent(receivedDateCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );

        recievedTable.setModel(receivedItemsDtm);
        jScrollPane1.setViewportView(recievedTable);

        removeItemBtn.setText("Remove Item");
        removeItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemBtnActionPerformed(evt);
            }
        });

        updateDatabaseBtn.setText("Update Database");
        updateDatabaseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDatabaseBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removeItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateDatabaseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateDatabaseBtn)
                    .addComponent(removeItemBtn))
                .addGap(24, 24, 24))
        );

        jLabel50.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel50.setText("Add Recieved Items");

        jLabel5.setText("From");

        fromText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromTextActionPerformed(evt);
            }
        });

        jLabel6.setText("Recieved ID");

        recievedIDTxt.setEnabled(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(recievedIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 52, Short.MAX_VALUE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(recievedIDTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Items Received", jPanel11);

        jLabel16.setText("Recieval ID");

        jLabel17.setText("Effective Date");

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Recieval Details"));

        viewRequestTable.setModel(searchreceivedItemsDtm);
        viewRequestTable.setEnabled(false);
        jScrollPane6.setViewportView(viewRequestTable);

        editItemBtn.setText("Edit Item");
        editItemBtn.setEnabled(false);
        editItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemBtnActionPerformed(evt);
            }
        });

        removeItemBtn1.setText("Remove Item");
        removeItemBtn1.setEnabled(false);
        removeItemBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemBtn1ActionPerformed(evt);
            }
        });

        updateDatabaseBtn1.setText("Update Request");
        updateDatabaseBtn1.setEnabled(false);
        updateDatabaseBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDatabaseBtn1ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Details of Recieval");

        searchRequestingOfficerLabel.setText("Recieved From");

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Update Item");

        jLabel7.setText("Item");

        viewitemIDText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        viewitemIDText.setEnabled(false);
        viewitemIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewitemIDTextActionPerformed(evt);
            }
        });

        viewItemTypeText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        viewItemTypeText.setEnabled(false);
        viewItemTypeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewItemTypeTextActionPerformed(evt);
            }
        });

        jLabel19.setText("Item Type");

        jLabel20.setText("Quatity requested");

        viewRequestQtyText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRequestQtyTextActionPerformed(evt);
            }
        });
        viewRequestQtyText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                viewRequestQtyTextKeyTyped(evt);
            }
        });

        updateItemBtn.setText("Update Item");
        updateItemBtn.setEnabled(false);
        updateItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateItemBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updateItemBtn))
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(viewRequestQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchUnitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                            .addComponent(viewitemIDText)
                            .addComponent(viewItemTypeText))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewitemIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewItemTypeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewRequestQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(searchUnitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(updateItemBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel8.setText("Item");

        requestItemIDCombo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestItemIDCombo1ActionPerformed(evt);
            }
        });

        jLabel22.setText("Item Type");

        requestItemType1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        requestItemType1.setEnabled(false);
        requestItemType1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestItemType1ActionPerformed(evt);
            }
        });

        jLabel25.setText("Quatity requested");

        requestQtyText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestQtyText1ActionPerformed(evt);
            }
        });
        requestQtyText1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                requestQtyText1KeyTyped(evt);
            }
        });

        requestAddBtn1.setText("Add Item");
        requestAddBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestAddBtn1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("Add Item");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(requestItemType1)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addComponent(requestQtyText1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(requestQtyUnitLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(requestItemIDCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(11, 11, 11))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(requestAddBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(requestItemIDCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(requestItemType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(requestQtyUnitLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(requestQtyText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(requestAddBtn1)
                .addGap(36, 36, 36))
        );

        recievedFromText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        recievedFromText.setEnabled(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(searchRequestingOfficerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(161, 161, 161)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(recievedFromText, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(editItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeItemBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateDatabaseBtn1))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateDatabaseBtn1)
                    .addComponent(removeItemBtn1)
                    .addComponent(editItemBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(searchRequestingOfficerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recievedFromText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );

        viewRecievalDateCalendar.setDateFormatString("yyyy-MM-dd");
        viewRecievalDateCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                viewRecievalDateCalendarPropertyChange(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel54.setText("View and Update Item Recieval");

        viewRecievalIDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRecievalIDComboActionPerformed(evt);
            }
        });

        editRecievalBtn.setText("Edit Recieval");
        editRecievalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRecievalBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(jSeparator2))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(viewRecievalDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(viewRecievalIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(editRecievalBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewRecievalDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewRecievalIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editRecievalBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane3.addTab("View Item Recieval", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemIDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIDComboActionPerformed
        try {
            if (itemIDCombo.getSelectedItem() == null) {
                itemTypeLabel.setText("");
                unitLabel.setText("");
                addRecievedItemBtn.setEnabled(false);
            } else {
                recievedQtyText.setText("");
                addRecievedItemBtn.setEnabled(true);
                ResultSet rst = ItemController.getItem("" + itemIDCombo.getSelectedItem());
                while (rst.next()) {
                    itemTypeLabel.setText(rst.getString("itemType"));
                    unitLabel.setText(rst.getString("units"));
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_itemIDComboActionPerformed

    private void recievedQtyTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recievedQtyTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recievedQtyTextActionPerformed

    private void addRecievedItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRecievedItemBtnActionPerformed

        String itemID = "" + itemIDCombo.getSelectedItem();
        /*date collected*/
//                        java.util.Date dateR = receivedDateCalendar.getDate();
//                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateCollection = df.format(dateR);
//                        java.sql.Date sqlDateR = new java.sql.Date(dateR.getTime());
        double recievedQty = Double.parseDouble(recievedQtyText.getText());
        String[] row = {itemID, itemTypeLabel.getText(), "" + recievedQty, unitLabel.getText()};
        receivedItemsDtm.addRow(row);
        TableResizer.resizeColumnWidth(recievedTable);
        itemIDCombo.removeItem(itemID);
    }//GEN-LAST:event_addRecievedItemBtnActionPerformed

    private void fromTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fromTextActionPerformed

    private void receivedDateCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_receivedDateCalendarPropertyChange


    }//GEN-LAST:event_receivedDateCalendarPropertyChange

    private void updateDatabaseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDatabaseBtnActionPerformed
        try {
            int rowCount = recievedTable.getRowCount();
            if (rowCount >= 0) {
                //"Item ID", "Item Type", "Quantity", "Units"
                String recievedID = recievedIDTxt.getText();

                /*date Requested*/
                java.util.Date dateR = receivedDateCalendar.getDate();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dateCollection = df.format(dateR);
                java.sql.Date sqlDateR = new java.sql.Date(dateR.getTime());

                String from = fromText.getText();

                ItemReceivedLog log = new ItemReceivedLog(recievedID, sqlDateR, from);
                int added = ItemRecievedLogController.addItemRecieval(log);
                if (added == 1) {
                    int res_detail = 0;
                    for (int i = 0; i < rowCount; i++) {
                        String itemID = "" + receivedItemsDtm.getValueAt(i, 0);
                        float Qty = Float.parseFloat("" + receivedItemsDtm.getValueAt(i, 2));
                        ItemRecievedDetail detail = new ItemRecievedDetail(recievedID, itemID, Qty);
                        res_detail += ItemRecievedLogController.addItemRecievalDetail(detail);
                    }

                    if (res_detail == rowCount) {
                        JOptionPane.showMessageDialog(null, "Request added successfully!");
                        setItemIdComboItems();
                        getNextRecievalID();
                        receivedItemsDtm = new DefaultTableModel(title_recieved, 0);
                        recievedTable.setModel(receivedItemsDtm);
                        receivedDateCalendar.setDate(today);
                        fromText.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occured while updating database!");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please add items for the recieval.");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateDatabaseBtnActionPerformed

    private void removeItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemBtnActionPerformed
        int row = recievedTable.getSelectedRow();

        if (row >= 0) {
            itemIDCombo.addItem(receivedItemsDtm.getValueAt(row, 0));
            receivedItemsDtm.removeRow(row);

        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_removeItemBtnActionPerformed

    private void editItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemBtnActionPerformed
        int row = viewRequestTable.getSelectedRow();
        if (row >= 0) {
            //"Item ID", "Item Type", "Quantity", "Units", "Reason for requesting"
            viewitemIDText.setText("" + searchreceivedItemsDtm.getValueAt(row, 0));
            viewItemTypeText.setText("" + searchreceivedItemsDtm.getValueAt(row, 1));
            viewRequestQtyText.setText("" + searchreceivedItemsDtm.getValueAt(row, 2));
            searchUnitsLabel.setText("" + searchreceivedItemsDtm.getValueAt(row, 3));
            updateItemBtn.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_editItemBtnActionPerformed

    private void removeItemBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemBtn1ActionPerformed
        try {
            int row = viewRequestTable.getSelectedRow();
            if (row >= 0) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected Blood Packet?", "Delete Blood Packet", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    //"Item ID", "Item Type", "Quantity", "Units", "Reason for requesting"
                    String recievalID = "" + viewRecievalIDCombo.getSelectedItem();
                    String itemID = "" + searchreceivedItemsDtm.getValueAt(row, 0);
                    int res;

                    res = ItemRecievedLogController.deleteRecievalDetail(recievalID, itemID);

                    if (res == 1) {
                        searchreceivedItemsDtm.removeRow(row);
                        setSearchData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error occured while deleting blood packet");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Select a row");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_removeItemBtn1ActionPerformed

    private void updateDatabaseBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDatabaseBtn1ActionPerformed
        try {

            String recievalID = "" + viewRecievalIDCombo.getSelectedItem();
            String recievedFrom = "" + recievedFromText.getText();
            /*date Requested*/
            java.util.Date dateR = viewRecievalDateCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateCollection = df.format(dateR);
            java.sql.Date sqlDateR = new java.sql.Date(dateR.getTime());

            ItemReceivedLog log = new ItemReceivedLog(recievalID, sqlDateR, recievedFrom);
            int updated = ItemRecievedLogController.updateItemRecieval(log);
            if (updated == 1) {
                JOptionPane.showMessageDialog(null, "Successfully updated!");
                setSearchData();
            } else {
                JOptionPane.showMessageDialog(null, "Error occured while updating!");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateDatabaseBtn1ActionPerformed

    private void viewitemIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewitemIDTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewitemIDTextActionPerformed

    private void viewItemTypeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewItemTypeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewItemTypeTextActionPerformed

    private void viewRequestQtyTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewRequestQtyTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewRequestQtyTextActionPerformed

    private void updateItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateItemBtnActionPerformed
        String itemID = viewitemIDText.getText();
        float qty = Float.parseFloat(viewRequestQtyText.getText());
        String requestID = "" + viewRecievalIDCombo.getSelectedItem();
        ItemRecievedDetail detail = new ItemRecievedDetail(requestID, itemID, qty);
        try {
            int res = ItemRecievedLogController.updateRecievedDetail(detail);
            if (res == 1) {
                JOptionPane.showMessageDialog(null, "Successfully updated!");
                clearUpdateFields();
                setSearchData();
            } else {
                JOptionPane.showMessageDialog(null, "Error occured while updating!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateItemBtnActionPerformed

    private void requestItemIDCombo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestItemIDCombo1ActionPerformed
        try {
            if (requestItemIDCombo1.getSelectedItem() == null) {
                requestItemType1.setText("");
                requestQtyText1.setText("");
                requestQtyUnitLabel1.setText("");
                requestAddBtn1.setEnabled(false);
            } else {
                requestAddBtn1.setEnabled(true);
                requestQtyText1.setText("");
                requestAddBtn1.setEnabled(true);
                ResultSet rst = ItemController.getItem("" + requestItemIDCombo1.getSelectedItem());
                while (rst.next()) {
                    requestItemType1.setText(rst.getString("itemType"));
                    requestQtyUnitLabel1.setText(rst.getString("units"));
                }
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_requestItemIDCombo1ActionPerformed

    private void requestItemType1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestItemType1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_requestItemType1ActionPerformed

    private void requestQtyText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestQtyText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_requestQtyText1ActionPerformed

    private void requestAddBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestAddBtn1ActionPerformed
        try {
            String itemID = "" + requestItemIDCombo1.getSelectedItem();
            try {
                float Qty = Float.parseFloat(requestQtyText1.getText());
                String recievalID = "" + viewRecievalIDCombo.getSelectedItem();
                ItemRecievedDetail detail = new ItemRecievedDetail(recievalID, itemID, Qty);
                int res_detail;

                res_detail = ItemRecievedLogController.addItemRecievalDetail(detail);

                if (res_detail == 1) {
                    JOptionPane.showMessageDialog(null, "Successfully added!");
                    setSearchData();
                } else {
                    JOptionPane.showMessageDialog(null, "Error occured while updating!");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please add a valid quantity!");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemRecieval.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemRecieval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_requestAddBtn1ActionPerformed

    private void viewRecievalAction() {
        try {
            /*date Requested*/
            java.util.Date dateR = viewRecievalDateCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateCollection = df.format(dateR);
            java.sql.Date sqlDateR = new java.sql.Date(dateR.getTime());

            ResultSet rst = ItemRecievedLogController.getRecievedIDsByDate(sqlDateR);
            viewRecievalIDCombo.removeAllItems();
            while (rst.next()) {
                viewRecievalIDCombo.addItem(rst.getString("RecievedID"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void viewRecievalDateCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_viewRecievalDateCalendarPropertyChange
        viewRecievalAction();
    }//GEN-LAST:event_viewRecievalDateCalendarPropertyChange

    private void viewRecievalIDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewRecievalIDComboActionPerformed

        setSearchData();

    }//GEN-LAST:event_viewRecievalIDComboActionPerformed

    private void editRecievalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRecievalBtnActionPerformed
        enableUpdateFields();
    }//GEN-LAST:event_editRecievalBtnActionPerformed

    private void recievedQtyTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recievedQtyTextKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == '.') || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_recievedQtyTextKeyTyped

    private void viewRequestQtyTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_viewRequestQtyTextKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == '.') || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_viewRequestQtyTextKeyTyped

    private void requestQtyText1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_requestQtyText1KeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == '.') || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_requestQtyText1KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addRecievedItemBtn;
    private javax.swing.JButton editItemBtn;
    private javax.swing.JButton editRecievalBtn;
    private javax.swing.JTextField fromText;
    private javax.swing.JComboBox itemIDCombo;
    private javax.swing.JLabel itemTypeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.toedter.calendar.JCalendar receivedDateCalendar;
    private javax.swing.JTextField recievedFromText;
    private javax.swing.JTextField recievedIDTxt;
    private javax.swing.JTextField recievedQtyText;
    private javax.swing.JTable recievedTable;
    private javax.swing.JButton removeItemBtn;
    private javax.swing.JButton removeItemBtn1;
    private javax.swing.JButton requestAddBtn1;
    private javax.swing.JComboBox requestItemIDCombo1;
    private javax.swing.JTextField requestItemType1;
    private javax.swing.JTextField requestQtyText1;
    private javax.swing.JLabel requestQtyUnitLabel1;
    private javax.swing.JLabel searchRequestingOfficerLabel;
    private javax.swing.JLabel searchUnitsLabel;
    private javax.swing.JLabel unitLabel;
    private javax.swing.JButton updateDatabaseBtn;
    private javax.swing.JButton updateDatabaseBtn1;
    private javax.swing.JButton updateItemBtn;
    private javax.swing.JTextField viewItemTypeText;
    private com.toedter.calendar.JDateChooser viewRecievalDateCalendar;
    private javax.swing.JComboBox viewRecievalIDCombo;
    private javax.swing.JTextField viewRequestQtyText;
    private javax.swing.JTable viewRequestTable;
    private javax.swing.JTextField viewitemIDText;
    // End of variables declaration//GEN-END:variables
}
