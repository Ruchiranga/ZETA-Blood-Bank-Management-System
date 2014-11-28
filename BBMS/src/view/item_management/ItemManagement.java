/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemManagement.java
 *
 * Created on Aug 22, 2014, 7:32:54 AM
 */
package view.item_management;

import controller.common.IDGenerator;
import controller.common.TableResizer;
import controller.item_management.ItemController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import view.blood_stock_management.BloodReturn;
import view.users.Nurse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Item;

/**
 *
 * @author Anuradha
 */
public class ItemManagement extends javax.swing.JInternalFrame {

    String[] title = {"Item ID", "Item Type", "Description", "Quantity", "Units", "isReagent"};
    DefaultTableModel allItemsDtm = new DefaultTableModel(title, 0);
    
    

    String[] only_item_title = {"Item ID", "Item Type", "Description", "Quantity", "Units"};
    DefaultTableModel onlyItemsDtm = new DefaultTableModel(only_item_title, 0);
    String[] only_reagent_title = {"Item ID", "Item Type", "Description", "Quantity", "Units"};
    DefaultTableModel onlyReagentsDtm = new DefaultTableModel(only_reagent_title, 0);

    private Nurse nurseForm;

    /**
     * Creates new form ItemManagement
     */
    public ItemManagement(Nurse nurseForm) throws FileNotFoundException, IOException {
        initComponents();
        setTableItems();
        setItemIDLabel();
        FileInputStream imgStream = null;
        File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
        imgStream = new FileInputStream(imgfile);
        BufferedImage bi = ImageIO.read(imgStream);
        ImageIcon myImg = new ImageIcon(bi);
        this.setFrameIcon(myImg);
        setTitle("Item Management");
        this.nurseForm = nurseForm;
    }

    public void clearItemAddFields() {
        itemTypeText.setText("");
        descriptionText.setText("");
        qtyText.setText("");
        unitsText.setText("");
        reagentCheckbox.setSelected(false);
    }

    public void setItemIDLabel() {
        String itemID = "";
        ResultSet rst;

        try {
            rst = ItemController.getResultIDs();
            rst.last();
            String curID = null;
            try {
                curID = rst.getString("ItemID");
                try {
                    itemID = IDGenerator.generateNextID(curID);
                } catch (Exception ex) {
                    Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                itemID = "I000000001";
            }
            itemIDLabel.setText(itemID);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setTableItems() {
        try {
            allItemsDtm = new DefaultTableModel(title, 0);
            itemTable.setModel(allItemsDtm);

            onlyItemsDtm = new DefaultTableModel(only_item_title, 0);
            onlyItemTable.setModel(onlyItemsDtm);

            onlyReagentsDtm = new DefaultTableModel(only_reagent_title, 0);
            onlyReagentTable.setModel(onlyReagentsDtm);

            ResultSet rst = ItemController.getAllItems();
            String id = null;
            String type = null;
            String desc = null;
            double quantity = 0;
            String unts = null;
            String isReagent = "no";
            int count = 0;
            while (rst.next()) {
                id = rst.getString("itemID");
                type = rst.getString("itemType");
                desc = rst.getString("description");
                quantity = rst.getDouble("quantity");
                unts = rst.getString("units");
                if (rst.getInt("isReagent") == 1) {
                    isReagent = "yes";
                    String[] ar_items = {id, type, desc, "" + quantity, unts};
                    onlyReagentsDtm.addRow(ar_items);
                } else {
                    isReagent = "no";
                    String[] ar_reagents = {id, type, desc, "" + quantity, unts};
                    onlyItemsDtm.addRow(ar_reagents);
                }
                String[] ar = {id, type, desc, "" + quantity, unts, isReagent};
                allItemsDtm.addRow(ar);
                count++;
            }
            totalText.setText("" + count);
            TableResizer.resizeColumnWidth(itemTable);
            TableResizer.resizeColumnWidth(onlyItemTable);
            TableResizer.resizeColumnWidth(onlyReagentTable);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        itemTypeText = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        descriptionText = new javax.swing.JTextField();
        addItemBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        qtyText = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        unitsText = new javax.swing.JTextField();
        reagentCheckbox = new javax.swing.JCheckBox();
        itemIDLabel = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        totalText = new javax.swing.JTextField();
        deleteItemBtn = new javax.swing.JButton();
        updateItemBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        onlyItemTable = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        onlyReagentTable = new javax.swing.JTable();

        jPanel6.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Item Management");
        jPanel6.add(jLabel5);
        jLabel5.setBounds(398, 20, 260, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FormHeader.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel6.add(jLabel2);
        jLabel2.setBounds(0, 0, 940, 73);

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Recieved Log"));

        itemTable.setModel(allItemsDtm);
        jScrollPane2.setViewportView(itemTable);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Item"));

        jLabel6.setText("Item ID");

        jLabel7.setText("Item type*");

        itemTypeText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTypeTextActionPerformed(evt);
            }
        });

        jLabel8.setText("Description");

        descriptionText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionTextActionPerformed(evt);
            }
        });

        addItemBtn.setText("Add Item");
        addItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemBtnActionPerformed(evt);
            }
        });

        jLabel9.setText("Quantity*");

        qtyText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtyTextActionPerformed(evt);
            }
        });
        qtyText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                qtyTextKeyTyped(evt);
            }
        });

        jLabel16.setText("Units");

        unitsText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitsTextActionPerformed(evt);
            }
        });

        reagentCheckbox.setText("Reagent");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(reagentCheckbox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(unitsText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(qtyText, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(descriptionText, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(itemTypeText, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(itemIDLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(20, 20, 20))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(itemIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(itemTypeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(descriptionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(qtyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unitsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(reagentCheckbox)
                .addGap(18, 18, 18)
                .addComponent(addItemBtn)
                .addContainerGap())
        );

        jLabel17.setText("Total no. of items");

        totalText.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        totalText.setEnabled(false);

        deleteItemBtn.setText("Delete Item");
        deleteItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteItemBtnActionPerformed(evt);
            }
        });

        updateItemBtn.setText("Update Item");
        updateItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateItemBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalText, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteItemBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateItemBtn)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateItemBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17)
                    .addComponent(deleteItemBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Manage Items", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Stock"));

        onlyItemTable.setModel(onlyItemsDtm);
        jScrollPane3.setViewportView(onlyItemTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Reagent Stock"));

        onlyReagentTable.setModel(onlyReagentsDtm);
        jScrollPane4.setViewportView(onlyReagentTable);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Item/Reagent Stock", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemTypeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTypeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemTypeTextActionPerformed

    private void descriptionTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descriptionTextActionPerformed

    private void addItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemBtnActionPerformed
        try {
            String itemID = itemIDLabel.getText();
            String itemType = itemTypeText.getText();
            String description = descriptionText.getText();
            float qty = Float.parseFloat(qtyText.getText());
            String units = unitsText.getText();
            byte isReagent = 0;

            if (reagentCheckbox.isSelected()) {
                isReagent = 1;
            }

            Item item = new Item(itemID, itemType, description, isReagent, units, qty);
            int added = ItemController.addItem(item);

            if (added == 1) {
                JOptionPane.showMessageDialog(null, "Added Succesfully");
                setTableItems();
                setItemIDLabel();
                clearItemAddFields();
            } else {
                JOptionPane.showMessageDialog(null, "Error!");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addItemBtnActionPerformed

    private void qtyTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtyTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyTextActionPerformed

    private void qtyTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyTextKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == '.') || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_qtyTextKeyTyped

    private void unitsTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitsTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitsTextActionPerformed

    private void deleteItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteItemBtnActionPerformed
        int row = itemTable.getSelectedRow();

        if (row >= 0) {
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected Item?", "Delete Item", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    String itemID = "" + allItemsDtm.getValueAt(row, 0);
                    int res = ItemController.deleteItem(itemID);
                    if (res == 1) {
                        JOptionPane.showMessageDialog(null, "Item deleted successfully.");
                        setTableItems();
                        setItemIDLabel();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error while deleting item");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_deleteItemBtnActionPerformed

    private void updateItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateItemBtnActionPerformed

        //"Item ID", "Item Type", "Description", "Quantity", "Units", "isReagent"
        int row = itemTable.getSelectedRow();

        if (row >= 0) {
            String itemID = "" + allItemsDtm.getValueAt(row, 0);
            String itemType = "" + allItemsDtm.getValueAt(row, 1);
            String description = "" + allItemsDtm.getValueAt(row, 2);
            float qty = Float.parseFloat("" + allItemsDtm.getValueAt(row, 3));
            String units = "" + allItemsDtm.getValueAt(row, 4);
            String isReagentString = "" + allItemsDtm.getValueAt(row, 5);
            byte isReagent;
            if (isReagentString.equalsIgnoreCase("yes")) {
                isReagent = 1;
            } else {
                isReagent = 0;
            }
            Item item = new Item(itemID, itemType, description, isReagent, units, qty);
            UpdateItem updateItemForm = new UpdateItem(item,this);
            updateItemForm.setClosable(true);
            nurseForm.getDesktop().add(updateItemForm);
            nurseForm.getDesktop().setRequestFocusEnabled(true);
            updateItemForm.show();
        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_updateItemBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton deleteItemBtn;
    private javax.swing.JTextField descriptionText;
    private javax.swing.JLabel itemIDLabel;
    private javax.swing.JTable itemTable;
    private javax.swing.JTextField itemTypeText;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable onlyItemTable;
    private javax.swing.JTable onlyReagentTable;
    private javax.swing.JTextField qtyText;
    private javax.swing.JCheckBox reagentCheckbox;
    private javax.swing.JTextField totalText;
    private javax.swing.JTextField unitsText;
    private javax.swing.JButton updateItemBtn;
    // End of variables declaration//GEN-END:variables
}
