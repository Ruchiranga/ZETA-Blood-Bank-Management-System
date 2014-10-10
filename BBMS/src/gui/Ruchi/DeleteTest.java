/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Ruchi;

import Controller.Ruchi.TestController;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ruchiranga
 */
public class DeleteTest extends javax.swing.JInternalFrame {

    TestController testController;

    /**
     * Creates new form DeleteTest
     */
    public DeleteTest() {
        initComponents();
        testController = new TestController();

        String[] testList = null;

        try {
            testList = testController.getTestList();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String test : testList) {
            testListComboBox.addItem(test);
        }

        if (testListComboBox.getItemCount() == 0) {
            renameButton.setEnabled(false);
            deleteButton.setEnabled(false);
        } else {
            renameButton.setEnabled(true);
            deleteButton.setEnabled(true);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        renameButton = new javax.swing.JButton();
        testListComboBox = new javax.swing.JComboBox();

        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 36)); // NOI18N
        jLabel1.setText("Delete/Update Test");

        jLabel2.setText("Test Name");

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        renameButton.setText("Rename");
        renameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(testListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(renameButton)
                    .addComponent(jLabel1))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(renameButton)
                    .addComponent(testListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String item = (String) testListComboBox.getSelectedItem();
        int selectedIdx = testListComboBox.getSelectedIndex();
        int res = 0;
        try {
            res = testController.deleteTest(item);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (res == 1) {
            JOptionPane.showMessageDialog(this, "Test deleted!", "", JOptionPane.INFORMATION_MESSAGE);
            testListComboBox.removeItemAt(selectedIdx);
        } else {
            JOptionPane.showMessageDialog(this, "Error! Could not delete selected test.", "", JOptionPane.ERROR_MESSAGE);
        }
        if (testListComboBox.getItemCount() == 0) {
            renameButton.setEnabled(false);
            deleteButton.setEnabled(false);
        } else {
            renameButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void renameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameButtonActionPerformed
        String oldValue = (String) testListComboBox.getSelectedItem();
        String input = JOptionPane.showInputDialog(this, "Enter new name");
        if (input != null && !input.isEmpty()) {
            int selectedIdx = testListComboBox.getSelectedIndex();

            int updateTest = 0;

            try {
                updateTest = testController.updateTest(oldValue, input);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DeleteTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DeleteTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (updateTest == 1) {
                JOptionPane.showMessageDialog(this, "Rename successful", "", JOptionPane.INFORMATION_MESSAGE);
                testListComboBox.removeItemAt(selectedIdx);
                testListComboBox.insertItemAt(input, selectedIdx);
                testListComboBox.setSelectedIndex(selectedIdx);
            } else {
                JOptionPane.showMessageDialog(this, "Rename failure", "", JOptionPane.ERROR_MESSAGE);
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_renameButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton renameButton;
    private javax.swing.JComboBox testListComboBox;
    // End of variables declaration//GEN-END:variables
}