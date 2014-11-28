/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.item_management;

import controller.common.IDGenerator;
import controller.common.TableResizer;
import controller.camp_management.EmployeeController;
import controller.item_management.ItemController;
import controller.item_management.ReagentRequestController;
import controller.common.SearchableCombo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import view.blood_stock_management.BloodPacketForm;
import view.blood_stock_management.BloodReturn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ReagentRequest;
import model.ReagentRequestDetail;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Anuradha
 */
public class ReagentRequests extends javax.swing.JInternalFrame {

    String[] title_requests = {"Item ID", "Item Type", "Quantity", "Units", "Reason for requesting"};
    DefaultTableModel requestItemsDtm = new DefaultTableModel(title_requests, 0);

    DefaultTableModel searchRequestItemsDtm = new DefaultTableModel(title_requests, 0);

    Calendar currenttime = Calendar.getInstance();
    java.util.Date today = new java.util.Date((currenttime.getTime()).getTime());

    /**
     * Creates new form ReagentRequests
     */
    public ReagentRequests() {
        try {
            initComponents();
            
            FileInputStream imgStream = null;
            File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
            imgStream = new FileInputStream(imgfile);
            BufferedImage bi = ImageIO.read(imgStream);
            ImageIcon myImg = new ImageIcon(bi);
            this.setFrameIcon(myImg);
            setTitle("Reagent Requests");
            
            this.setMaximizable(true);
            setRequestItemIDComboItems();
            getNextRequestID();
            requestDateCalendar.setDate(today);
            viewRequestDateCalendar.setDate(today);
            setEmpCombo(empNamesCombo);
            setEmpCombo(viewEmpNamesCombo);
            new SearchableCombo().setSearchableCombo(requestItemIDCombo, true);
            new SearchableCombo().setSearchableCombo(empNamesCombo, true);
        } catch (IOException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setEmpCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = EmployeeController.getAllEmployees();

            while (rst.next()) {
                combo.addItem(rst.getString("Name"));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getNextRequestID() {
        try {
            String returnID = "";
            ResultSet rst;

            rst = ReagentRequestController.getRequestIDs();

            rst.last();
            String curID = null;
            try {
                curID = rst.getString("RequestID");
                try {
                    returnID = IDGenerator.generateNextID(curID);
                } catch (Exception ex) {
                    Logger.getLogger(BloodReturn.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                returnID = "RQ00000001";
            }
            requestIDText.setText(returnID);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setRequestItemIDComboItems() {
        requestItemIDCombo.removeAllItems();
        try {
            ResultSet rst = ItemController.getAllReagentIDs();
            String id = null;
            while (rst.next()) {
                id = rst.getString("itemID");
                requestItemIDCombo.addItem(id);
            }
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

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        requestIDText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        requestItemIDCombo = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        requestItemType = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        requestQtyText = new javax.swing.JTextField();
        requestDeleteBtn = new javax.swing.JButton();
        requestAddBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        requestReasonText = new javax.swing.JTextField();
        empNamesCombo = new javax.swing.JComboBox();
        updateBtn = new javax.swing.JButton();
        requestQtyUnitLabel = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        requestDateCalendar = new com.toedter.calendar.JDateChooser();
        jLabel53 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        viewRequestTable = new javax.swing.JTable();
        editItemBtn = new javax.swing.JButton();
        removeItemBtn = new javax.swing.JButton();
        generateRequestBtn = new javax.swing.JButton();
        updateDatabaseBtn = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        searchRequestingOfficerLabel = new javax.swing.JLabel();
        viewEmpNamesCombo = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        viewitemIDText = new javax.swing.JTextField();
        viewItemTypeText = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        viewRequestQtyText = new javax.swing.JTextField();
        viewRequestReasonText = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        updateItemBtn = new javax.swing.JButton();
        searchUnitsLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        requestItemIDCombo1 = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        requestItemType1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        requestQtyText1 = new javax.swing.JTextField();
        requestAddBtn1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        requestReasonText1 = new javax.swing.JTextField();
        requestQtyUnitLabel1 = new javax.swing.JLabel();
        viewRequestDateCalendar = new com.toedter.calendar.JDateChooser();
        jLabel54 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        viewRequestIDCombo = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        jTabbedPane3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel10.setText("Request ID");

        requestIDText.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        requestIDText.setEnabled(false);
        requestIDText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestIDTextActionPerformed(evt);
            }
        });

        jLabel11.setText("Effective Date*");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Requesting critical reagents additional to the monthly allocation "));

        requestTable.setModel(requestItemsDtm);
        jScrollPane5.setViewportView(requestTable);

        jLabel2.setText("Item (Reagent/Test kit)");

        requestItemIDCombo.setEditable(true);
        requestItemIDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestItemIDComboActionPerformed(evt);
            }
        });

        jLabel12.setText("Item Type");

        requestItemType.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        requestItemType.setEnabled(false);
        requestItemType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestItemTypeActionPerformed(evt);
            }
        });

        jLabel13.setText("Quatity requested*");

        requestQtyText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestQtyTextActionPerformed(evt);
            }
        });
        requestQtyText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                requestQtyTextKeyTyped(evt);
            }
        });

        requestDeleteBtn.setText("Remove Item");
        requestDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestDeleteBtnActionPerformed(evt);
            }
        });

        requestAddBtn.setText("Add Item");
        requestAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestAddBtnActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Details of Emergency Request");

        jLabel15.setText("Requesting officer Name*");

        jLabel18.setText("Reason for emergency request");

        requestReasonText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestReasonTextActionPerformed(evt);
            }
        });

        empNamesCombo.setEditable(true);
        empNamesCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Emp Names" }));

        updateBtn.setText("Update Database");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        jButton18.setText("Generate Request");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(requestItemIDCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(requestItemType)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(empNamesCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(167, 167, 167)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(requestQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 25, Short.MAX_VALUE))
                                    .addComponent(requestReasonText)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(requestAddBtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(requestDeleteBtn)
                            .addComponent(requestQtyUnitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateBtn)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(requestItemIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(requestItemType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(requestQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(requestQtyUnitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(requestDeleteBtn)
                                            .addComponent(requestAddBtn)))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(requestReasonText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 163, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empNamesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBtn)
                    .addComponent(jButton18))
                .addGap(21, 21, 21))
        );

        requestDateCalendar.setDateFormatString("yyyy-MM-dd");

        jLabel53.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel53.setText("Add Reagent Request");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(requestIDText, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                .addGap(198, 198, 198)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(requestDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(348, 348, 348))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(429, 429, 429)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(requestIDText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(requestDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Request Reagents", jPanel5);

        jLabel16.setText("Request ID");

        jLabel17.setText("Effective Date");

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Requesting critical reagents additional to the monthly allocation "));

        viewRequestTable.setModel(searchRequestItemsDtm);
        viewRequestTable.setEnabled(false);
        jScrollPane6.setViewportView(viewRequestTable);

        editItemBtn.setText("Edit Item");
        editItemBtn.setEnabled(false);
        editItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemBtnActionPerformed(evt);
            }
        });

        removeItemBtn.setText("Remove Item");
        removeItemBtn.setEnabled(false);
        removeItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemBtnActionPerformed(evt);
            }
        });

        generateRequestBtn.setText("Generate Request");
        generateRequestBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateRequestBtnActionPerformed(evt);
            }
        });

        updateDatabaseBtn.setText("Update Request");
        updateDatabaseBtn.setEnabled(false);
        updateDatabaseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDatabaseBtnActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setText("Details of Emergency Request");

        searchRequestingOfficerLabel.setText("Requesting officer Name*");

        viewEmpNamesCombo.setEditable(true);
        viewEmpNamesCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Emp Names" }));
        viewEmpNamesCombo.setEnabled(false);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("Update Item");

        jLabel3.setText("Item (Reagent/Test kit)");

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

        jLabel20.setText("Quatity requested*");

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

        viewRequestReasonText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRequestReasonTextActionPerformed(evt);
            }
        });

        jLabel23.setText("Reason for emergency request");

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
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(viewRequestReasonText))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(viewRequestQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(searchUnitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                                    .addComponent(viewitemIDText)
                                    .addComponent(viewItemTypeText))))
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
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewRequestReasonText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(updateItemBtn)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel4.setText("Item (Reagent/Test kit)");

        requestItemIDCombo1.setEditable(true);
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

        jLabel25.setText("Quatity requested*");

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

        jLabel27.setText("Reason for emergency request");

        requestReasonText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestReasonText1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(requestAddBtn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(requestItemType1)
                            .addComponent(requestReasonText1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(requestQtyText1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(requestQtyUnitLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(requestItemIDCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(11, 11, 11))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(requestItemIDCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(requestItemType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(requestQtyUnitLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(requestQtyText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(requestReasonText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(requestAddBtn1)
                        .addContainerGap(22, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewEmpNamesCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(searchRequestingOfficerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(editItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(generateRequestBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updateDatabaseBtn))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1038, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateRequestBtn)
                    .addComponent(updateDatabaseBtn)
                    .addComponent(removeItemBtn)
                    .addComponent(editItemBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(searchRequestingOfficerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(viewEmpNamesCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        viewRequestDateCalendar.setDateFormatString("yyyy-MM-dd");
        viewRequestDateCalendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                viewRequestDateCalendarPropertyChange(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel54.setText("View and Update Reagent Request");

        viewRequestIDCombo.setEditable(true);
        viewRequestIDCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRequestIDComboActionPerformed(evt);
            }
        });

        jButton1.setText("Edit Request");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(jSeparator2))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(viewRequestDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(viewRequestIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(346, 346, 346)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewRequestDateCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewRequestIDCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("View Request Reagents", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void requestIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestIDTextActionPerformed
        if (requestItemIDCombo.getSelectedItem() == null) {
            requestAddBtn.setEnabled(false);
            clearAddFields();
        } else {
            requestAddBtn.setEnabled(true);
        }
    }//GEN-LAST:event_requestIDTextActionPerformed

    private void requestItemIDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestItemIDComboActionPerformed
        try {
            if(requestItemIDCombo.getSelectedItem()==null){
                requestAddBtn.setEnabled(false);
                requestItemType.setText("");
                requestQtyText.setText("");
                requestQtyUnitLabel.setText("");
                requestReasonText.setText("");
            }else{
                ResultSet rst = null;
                String item = ("" + requestItemIDCombo.getSelectedItem()).trim();
                rst = ItemController.getItem(item);

                while (rst.next()) {
                    requestItemType.setText(rst.getString("itemType"));
                    requestQtyUnitLabel.setText(rst.getString("units"));
                }
            }
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_requestItemIDComboActionPerformed

    private void requestItemTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestItemTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_requestItemTypeActionPerformed

    private void requestQtyTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestQtyTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_requestQtyTextActionPerformed

    private void clearAddFields() {
        requestQtyText.setText("");
        requestReasonText.setText("");
    }

    private void requestAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestAddBtnActionPerformed
        //"Item ID", "Item Type", "Quantity", "Units", "Reason for requesting"
        String[] row = {"", "", "", "", ""};
        row[0] = ("" + requestItemIDCombo.getSelectedItem()).trim();
        row[1] = requestItemType.getText();
        row[2] = requestQtyText.getText();
        row[3] = requestQtyUnitLabel.getText();
        row[4] = requestReasonText.getText();
        requestItemsDtm.addRow(row);
        requestItemIDCombo.removeItem(row[0]);
        clearAddFields();
        TableResizer.resizeColumnWidth(requestTable);

    }//GEN-LAST:event_requestAddBtnActionPerformed

    private void requestReasonTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestReasonTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_requestReasonTextActionPerformed

    private void requestDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestDeleteBtnActionPerformed
        int row = requestTable.getSelectedRow();

        if (row >= 0) {
            requestItemIDCombo.addItem(requestItemsDtm.getValueAt(row, 0));
            requestItemsDtm.removeRow(row);
            requestAddBtn.setEnabled(true);
            TableResizer.resizeColumnWidth(requestTable);
        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_requestDeleteBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try {
            int rowCount = requestTable.getRowCount();
            if (rowCount >= 0) {
                //"Item ID", "Item Type", "Quantity", "Units", "Reason for requesting"
                String requestID = requestIDText.getText();

                /*date Requested*/
                java.util.Date dateR = requestDateCalendar.getDate();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String dateCollection = df.format(dateR);
                java.sql.Date sqlDateR = new java.sql.Date(dateR.getTime());

                String recievingOfficerID;

                recievingOfficerID = EmployeeController.getEmpID("" + empNamesCombo.getSelectedItem());

                ReagentRequest request = new ReagentRequest(requestID, sqlDateR, recievingOfficerID);
                int added = ReagentRequestController.addReagentRequest(request);
                if (added == 1) {
                    int res_detail = 0;
                    for (int i = 0; i < rowCount; i++) {
                        String itemID = "" + requestItemsDtm.getValueAt(i, 0);
                        float Qty = Float.parseFloat("" + requestItemsDtm.getValueAt(i, 2));
                        String reason = "" + requestItemsDtm.getValueAt(i, 4);
                        ReagentRequestDetail detail = new ReagentRequestDetail(requestID, itemID, Qty, reason);
                        res_detail += ReagentRequestController.addReagentRequestDetails(detail);
                    }

                    if (res_detail == rowCount) {
                        JOptionPane.showMessageDialog(null, "Request added successfully!");
                        viewRequestAction();
                        requestItemsDtm = new DefaultTableModel(title_requests, 0);
                        requestTable.setModel(requestItemsDtm);
                        setRequestItemIDComboItems();
                        getNextRequestID();
                        requestDateCalendar.setDate(today);
                        setEmpCombo(empNamesCombo);
                        //////////////////////////////////////////////////////////////////////////

                    } else {
                        JOptionPane.showMessageDialog(null, "Error occured while updating database!");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Error!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please add reagents to be requested.");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void clearUpdateFields() {
        viewitemIDText.setText("");
        viewItemTypeText.setText("");
        viewRequestQtyText.setText("");
        viewRequestReasonText.setText("");
    }

    private void viewRequestAction() {
        try {
            /*date Requested*/
            java.util.Date dateR = viewRequestDateCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateCollection = df.format(dateR);
            java.sql.Date sqlDateR = new java.sql.Date(dateR.getTime());

            ResultSet rst = ReagentRequestController.getRequestIDsByDate(sqlDateR);
            viewRequestIDCombo.removeAllItems();
            while (rst.next()) {
                viewRequestIDCombo.addItem(rst.getString("RequestID"));
            }
            new SearchableCombo().setSearchableCombo(viewRequestIDCombo, true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewRequestDateCalendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_viewRequestDateCalendarPropertyChange
        viewRequestAction();
    }//GEN-LAST:event_viewRequestDateCalendarPropertyChange

    private void viewRequestIDComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewRequestIDComboActionPerformed

        setSearchData();
    }//GEN-LAST:event_viewRequestIDComboActionPerformed

    public void setSearchItemIdComboItems() {
        requestItemIDCombo1.removeAllItems();
        try {
            ResultSet rst = ItemController.getAllReagentItems();
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
    
    private void setSearchData() {
        disAbleUpdateFields();
        if (viewRequestIDCombo.getSelectedItem() != null) {
            viewEmpNamesCombo.setVisible(true);
            searchRequestingOfficerLabel.setVisible(true);
            String requestID = "" + viewRequestIDCombo.getSelectedItem();
            try {
                searchRequestItemsDtm = new DefaultTableModel(title_requests, 0);
                viewRequestTable.setModel(searchRequestItemsDtm);
                ResultSet rst = ReagentRequestController.getRequest(requestID);
                while (rst.next()) {
                    viewEmpNamesCombo.setSelectedItem(rst.getString("Name"));
                }
                setSearchItemIdComboItems();
                ResultSet rstDetail = ReagentRequestController.getRequestDetails(requestID);
                //"Item ID", "Item Type", "Quantity", "Units", "Reason for requesting"
                while (rstDetail.next()) {
                    String[] row = {"", "", "", "", ""};
                    row[0] = rstDetail.getString("ItemID");
                    requestItemIDCombo1.removeItem(row[0]);
                    row[1] = rstDetail.getString("ItemType");
                    row[2] = rstDetail.getString("Qty");
                    row[3] = rstDetail.getString("Units");
                    row[4] = rstDetail.getString("Reason");
                    searchRequestItemsDtm.addRow(row);
                }
                new SearchableCombo().setSearchableCombo(viewEmpNamesCombo, true);
                new SearchableCombo().setSearchableCombo(requestItemIDCombo1, true);
                TableResizer.resizeColumnWidth(viewRequestTable);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            viewEmpNamesCombo.setVisible(false);
            searchRequestingOfficerLabel.setVisible(false);
            searchRequestItemsDtm = new DefaultTableModel(title_requests, 0);
            viewRequestTable.setModel(searchRequestItemsDtm);
        }
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        enableUpdateFields();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void editItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemBtnActionPerformed
        int row = viewRequestTable.getSelectedRow();
        if (row >= 0) {
            //"Item ID", "Item Type", "Quantity", "Units", "Reason for requesting"
            viewitemIDText.setText("" + searchRequestItemsDtm.getValueAt(row, 0));
            viewItemTypeText.setText("" + searchRequestItemsDtm.getValueAt(row, 1));
            viewRequestQtyText.setText("" + searchRequestItemsDtm.getValueAt(row, 2));
            searchUnitsLabel.setText("" + searchRequestItemsDtm.getValueAt(row, 3));
            viewRequestReasonText.setText("" + searchRequestItemsDtm.getValueAt(row, 4));
            updateItemBtn.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Select a row");
        }
    }//GEN-LAST:event_editItemBtnActionPerformed

    private void removeItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemBtnActionPerformed
        try {
            int row = viewRequestTable.getSelectedRow();
            if (row >= 0) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected Blood Packet?", "Delete Blood Packet", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    //"Item ID", "Item Type", "Quantity", "Units", "Reason for requesting"
                    String requestID = "" + viewRequestIDCombo.getSelectedItem();
                    String itemID = "" + searchRequestItemsDtm.getValueAt(row, 0);
                    int res;

                    res = ReagentRequestController.deleteRequestDetail(requestID, itemID);

                    if (res == 1) {
                        searchRequestItemsDtm.removeRow(row);
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
    }//GEN-LAST:event_removeItemBtnActionPerformed

    private void generateRequestBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateRequestBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateRequestBtnActionPerformed

    private void updateDatabaseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDatabaseBtnActionPerformed
        try {

            String requestID = "" + viewRequestIDCombo.getSelectedItem();
            String empID = EmployeeController.getEmpID("" + viewEmpNamesCombo.getSelectedItem());
            /*date Requested*/
            java.util.Date dateR = viewRequestDateCalendar.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateCollection = df.format(dateR);
            java.sql.Date sqlDateR = new java.sql.Date(dateR.getTime());

            ReagentRequest request = new ReagentRequest(requestID, sqlDateR, empID);
            int res = ReagentRequestController.updateRequest(request);
            if (res == 1) {
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
    }//GEN-LAST:event_updateDatabaseBtnActionPerformed

    private void viewitemIDTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewitemIDTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewitemIDTextActionPerformed

    private void viewItemTypeTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewItemTypeTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewItemTypeTextActionPerformed

    private void viewRequestQtyTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewRequestQtyTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewRequestQtyTextActionPerformed

    private void viewRequestReasonTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewRequestReasonTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewRequestReasonTextActionPerformed

    private void updateItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateItemBtnActionPerformed
        String itemID = viewitemIDText.getText();
        float qty = Float.parseFloat(viewRequestQtyText.getText());
        String reason = viewRequestReasonText.getText();
        String requestID = "" + viewRequestIDCombo.getSelectedItem();
        ReagentRequestDetail detail = new ReagentRequestDetail(requestID, itemID, qty, reason);
        try {
            int res = ReagentRequestController.updateRequestDetail(detail);
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
                requestReasonText1.setText("");
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
            try{
            String reason = requestReasonText1.getText();
            
            float Qty = Float.parseFloat(requestQtyText1.getText());
            String recievalID = "" + viewRequestIDCombo.getSelectedItem();
            ReagentRequestDetail detail = new ReagentRequestDetail(recievalID, itemID, Qty, reason);
            int res_detail = ReagentRequestController.addReagentRequestDetails(detail);

            if (res_detail == 1) {
                JOptionPane.showMessageDialog(null, "Successfully added!");
                setSearchData();
            } else {
                JOptionPane.showMessageDialog(null, "Error occured while updating!");
            }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please add a valid quantity!");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemRecieval.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemRecieval.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_requestAddBtn1ActionPerformed

    private void requestReasonText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestReasonText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_requestReasonText1ActionPerformed

    private void requestQtyTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_requestQtyTextKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) || c == java.awt.event.KeyEvent.VK_BACK_SPACE || c == '.') || c == java.awt.event.KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_requestQtyTextKeyTyped

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

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            JasperReport jr = JasperCompileManager.compileReport("./src/reports/item_management/ReagentRequestReport.jrxml");
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            params.put("Request_ID",requestIDText.getText());
            params.put("Effective_Date",df.format(requestDateCalendar.getDate()));
            params.put("Requesting_officer_Name",(String)empNamesCombo.getSelectedItem());

            JRTableModelDataSource dataSource = new JRTableModelDataSource(requestTable.getModel());

            JasperPrint jp = JasperFillManager.fillReport(jr, params,dataSource);

            JasperViewer.viewReport(jp);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Error while generating the Report!", "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ReagentRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void enableUpdateFields() {
        viewEmpNamesCombo.setEnabled(true);
        viewRequestTable.setEnabled(true);
        editItemBtn.setEnabled(true);
        removeItemBtn.setEnabled(true);
        updateDatabaseBtn.setEnabled(true);
    }

    private void disAbleUpdateFields() {
        viewEmpNamesCombo.setEnabled(false);
        viewRequestTable.setEnabled(false);
        editItemBtn.setEnabled(false);
        removeItemBtn.setEnabled(false);
        updateDatabaseBtn.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editItemBtn;
    private javax.swing.JComboBox empNamesCombo;
    private javax.swing.JButton generateRequestBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton18;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JButton removeItemBtn;
    private javax.swing.JButton requestAddBtn;
    private javax.swing.JButton requestAddBtn1;
    private com.toedter.calendar.JDateChooser requestDateCalendar;
    private javax.swing.JButton requestDeleteBtn;
    private javax.swing.JTextField requestIDText;
    private javax.swing.JComboBox requestItemIDCombo;
    private javax.swing.JComboBox requestItemIDCombo1;
    private javax.swing.JTextField requestItemType;
    private javax.swing.JTextField requestItemType1;
    private javax.swing.JTextField requestQtyText;
    private javax.swing.JTextField requestQtyText1;
    private javax.swing.JLabel requestQtyUnitLabel;
    private javax.swing.JLabel requestQtyUnitLabel1;
    private javax.swing.JTextField requestReasonText;
    private javax.swing.JTextField requestReasonText1;
    private javax.swing.JTable requestTable;
    private javax.swing.JLabel searchRequestingOfficerLabel;
    private javax.swing.JLabel searchUnitsLabel;
    private javax.swing.JButton updateBtn;
    private javax.swing.JButton updateDatabaseBtn;
    private javax.swing.JButton updateItemBtn;
    private javax.swing.JComboBox viewEmpNamesCombo;
    private javax.swing.JTextField viewItemTypeText;
    private com.toedter.calendar.JDateChooser viewRequestDateCalendar;
    private javax.swing.JComboBox viewRequestIDCombo;
    private javax.swing.JTextField viewRequestQtyText;
    private javax.swing.JTextField viewRequestReasonText;
    private javax.swing.JTable viewRequestTable;
    private javax.swing.JTextField viewitemIDText;
    // End of variables declaration//GEN-END:variables
}
