/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.donor_management;

import controller.donor_management.DonorController;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import model.DonorRegisterRecord;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Upekka
 */
public class ViewDonorRegister extends javax.swing.JInternalFrame {

    /**
     * Creates new form ViewDonorRegister
     */
    public ViewDonorRegister() throws FileNotFoundException, IOException {
        initComponents();
        FileInputStream imgStream = null;
        File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
        imgStream = new FileInputStream(imgfile);
        BufferedImage bi = ImageIO.read(imgStream);
        ImageIcon myImg = new ImageIcon(bi);
        this.setFrameIcon(myImg);
        setTitle("View Blood Donor Register");
        loadDonorRegister();
        donorRegisterTable.setAutoCreateRowSorter(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        donorRegisterTable = new javax.swing.JTable();
        printButton = new javax.swing.JButton();
        reloadButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        jPanel2.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Donor Register");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(388, 20, 270, 30);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/FormHeader.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel2.add(jLabel2);
        jLabel2.setBounds(0, 0, 940, 73);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        donorRegisterTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "Serial No", "Unit No", "Donor Name", "DonorAddress", "NIC no", "Tel No", "Age", "Sex", "Weight", "No of Donations", "Blood Group"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        donorRegisterTable.setCellEditor(null);
        donorRegisterTable.setEnabled(false);
        jScrollPane1.setViewportView(donorRegisterTable);

        printButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Print.png"))); // NOI18N
        printButton.setText("Print ");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        reloadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reload.png"))); // NOI18N
        reloadButton.setText("Reload");
        reloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadButtonActionPerformed(evt);
            }
        });

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/CloseButton2.png"))); // NOI18N
        exitButton.setText("Exit");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1114, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(reloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(reloadButton)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(printButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(126, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(540, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        try {
            // TODO add your handling code here:

            JasperReport jr = JasperCompileManager.compileReport("./src/reports/donor_management/DonorRegisterReport.jrxml");
            Map<String, Object> params;
            params = new HashMap<String, Object>();

            JRTableModelDataSource dataSource = new JRTableModelDataSource(donorRegisterTable.getModel());

            JasperPrint jp = JasperFillManager.fillReport(jr, params, dataSource);

            JasperViewer.viewReport(jp, false);

        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Error while generating the Report!", "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ViewDonorRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private void reloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadButtonActionPerformed
        loadDonorRegister();
    }//GEN-LAST:event_reloadButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        int showConfirmDialog = JOptionPane.showConfirmDialog(this, "Are you sure that you want to exit ?");
        System.out.println(showConfirmDialog);
        if (showConfirmDialog == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_exitButtonActionPerformed

    private void loadDonorRegister() {
        try {
            List<DonorRegisterRecord> donorRegister = DonorController.getListOfDonors();
            DefaultTableModel dtm = (DefaultTableModel) donorRegisterTable.getModel();
            dtm.setRowCount(0);
            for (DonorRegisterRecord record : donorRegister) {
                //if (!record.getNic().endsWith("-")) {

                    Object[] rowDataObjects = {record.getDate(), record.getSerialNo(), record.getUnitNo(), record.getDonorName(), record.getDonorAddress(), record.getNic(), record.getTelNo(), record.getAge(), record.getSex(), record.getWeight(), record.getNoOfDonations(), record.getBloodGroup()};

                    dtm.addRow(rowDataObjects);
                //}

            }
            resizeColumnWidth(donorRegisterTable);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewDonorRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDonorRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable donorRegisterTable;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton printButton;
    private javax.swing.JButton reloadButton;
    // End of variables declaration//GEN-END:variables
}
