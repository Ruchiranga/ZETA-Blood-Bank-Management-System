package view.camp_management;

import controller.camp_management.BloodCampController;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class CheckAvailbleDates extends javax.swing.JInternalFrame {

    DefaultTableModel dtm;

    public CheckAvailbleDates() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        calendar.setToolTipText("Select a Date ");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        calendar = new org.freixas.jcalendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDateInfo = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setTitle("Check Available Dates");

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel1.setText("Details");

        calendar.addDateListener(new org.freixas.jcalendar.DateListener() {
            public void dateChanged(org.freixas.jcalendar.DateEvent evt) {
                calendarDateChanged(evt);
            }
        });

        tableDateInfo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tableDateInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "CampID", "Place", "OrganizerName", "Telephone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableDateInfo.setEnabled(false);
        jScrollPane1.setViewportView(tableDateInfo);

        jLabel2.setFont(new java.awt.Font("Monotype Corsiva", 1, 24)); // NOI18N
        jLabel2.setText("Select Date");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Check Available Dates");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SmallFormHeader.png"))); // NOI18N
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(263, 263, 263))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calendarDateChanged(org.freixas.jcalendar.DateEvent evt) {//GEN-FIRST:event_calendarDateChanged

        resizeColumnWidth(tableDateInfo);
        try {
            String selectDate = getDates();

            ResultSet rst = BloodCampController.returnSelectedDate(selectDate, 0);
            int i = 0;
            while (rst.next()) {

                i++;
            }
            if (i >= 3) {
                JOptionPane.showMessageDialog(this.calendar, "Selected Date has atleast 3 camps scheduled!","Info",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        selectedDateCheck();
        dtm = (DefaultTableModel) tableDateInfo.getModel();
        try {
            addTable();
        } catch (SQLException ex) {
            Logger.getLogger(CheckAvailbleDates.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CheckAvailbleDates.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_calendarDateChanged

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CheckAvailbleDates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckAvailbleDates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckAvailbleDates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckAvailbleDates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckAvailbleDates().setVisible(true);
            }
        });
    }

    private String getDates() {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
        String datewithouttime = SDF.format(calendar.getDate()).toString().substring(0, 10);
        return datewithouttime;
    }

    private void selectedDateCheck() {
        try {
            String selectDate = getDates();
            ResultSet rst = BloodCampController.returnSelectedDate(selectDate, 0);
            int i = 0;
            while (rst.next()) {

                i++;
            }
            if (i >= 100) {
                JOptionPane.showMessageDialog(this.calendar, "DATE FULL");
            }
//            MobileCampDetail MCD = new MobileCampDetail();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MobileCampDetail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.freixas.jcalendar.JCalendar calendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDateInfo;
    // End of variables declaration//GEN-END:variables

    private void addTable() throws SQLException, ClassNotFoundException {

        String SelectedDate = getDates();

        ResultSet rs = BloodCampController.returnSelectedDate(SelectedDate, 1);

        String cpId = null;
        String place = null;
        String date = null;
        String OrganizerName = null;
        String TP = null;
        int rowCount = tableDateInfo.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            dtm.removeRow(0);
        }

        while (rs.next()) {

            cpId = rs.getString("CampID");
            place = rs.getString("PLACE");
            date = rs.getString("DATE").substring(0, 10);
            OrganizerName = rs.getString("Name");
            TP = rs.getString("TP");

            resizeColumnWidth(tableDateInfo);
            Object[] obj = {date, cpId, place, OrganizerName, TP};
            dtm.addRow(obj);

        }

    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width, width);
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
