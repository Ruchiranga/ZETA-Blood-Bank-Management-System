package view.issueing;

import controller.issueing.IssueController;
import controller.issueing.SampleDetailsController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nands
 */
public class IssueRegister extends javax.swing.JInternalFrame {

    String[] title = {"Request No", "Packet ID", "Blood Group", "Issue Component", "Expiry Date", "Issued By", "Issued Date", "Issued Time"};
    DefaultTableModel dtm = new DefaultTableModel(title, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    String request;
    String packetid;
    String bloodgroup;
    String type;
    String expiryDate;
    String issuedDate;
    String issuedOfficer;

    /**
     * Creates new form IssueRegister
     */
    public IssueRegister(Dimension d) throws FileNotFoundException, IOException {
        initComponents();
        FileInputStream imgStream = null;
        File imgfile = new File("..\\BBMS\\src\\images\\drop.png");
        imgStream = new FileInputStream(imgfile);
        BufferedImage bi = ImageIO.read(imgStream);
        ImageIcon myImg = new ImageIcon(bi);
        this.setFrameIcon(myImg);
        setTitle("Daily Issue Register");
        setSize(d);
        fillTable();
        setRequestCombo(requestCombo);
        setPacketCombo(packetCombo);
        setComponentCombo(componentCombo);
        setIssuerCombo(IssuerCombo);

    }

    public void setRequestCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = SampleDetailsController.getAllRequestNos();

            while (rst.next()) {
                combo.addItem(rst.getString("RequestNo"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IssueRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IssueRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setPacketCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = IssueController.getPacketInfo();

            while (rst.next()) {
                combo.addItem(rst.getString("PacketID"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IssueRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IssueRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setComponentCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = SampleDetailsController.getAllTypes();

            while (rst.next()) {
                combo.addItem(rst.getString("BloodType"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IssueRegister.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IssueRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setIssuerCombo(JComboBox combo) {
        try {
            combo.removeAllItems();
            ResultSet rst = null;
            rst = IssueController.getAllEmployers();

            while (rst.next()) {
                combo.addItem(rst.getString("Name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(IssueController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SampleDetails.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        issueTable = new javax.swing.JTable();
        searchPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        requestCombo = new javax.swing.JComboBox();
        requestLabel = new javax.swing.JLabel();
        requestCheck = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        packetCheck = new javax.swing.JCheckBox();
        packetLabel = new javax.swing.JLabel();
        packetCombo = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        groupCheck = new javax.swing.JCheckBox();
        groupCombo = new javax.swing.JComboBox();
        groupLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        componentCombo = new javax.swing.JComboBox();
        componentCheck = new javax.swing.JCheckBox();
        componentLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        expiryLabel = new javax.swing.JLabel();
        expiryCheck = new javax.swing.JCheckBox();
        expireDate = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        officerCheck = new javax.swing.JCheckBox();
        officerLabel = new javax.swing.JLabel();
        IssuerCombo = new javax.swing.JComboBox();
        jPanel9 = new javax.swing.JPanel();
        dateLabel = new javax.swing.JLabel();
        dateCheck = new javax.swing.JCheckBox();
        issueDate = new com.toedter.calendar.JDateChooser();
        searchButton = new javax.swing.JButton();
        reportBtn = new javax.swing.JButton();

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/IssuesRegister.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 102), 2));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        issueTable.setModel(dtm
        );
        jScrollPane2.setViewportView(issueTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        searchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Slelect Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        requestCombo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        requestCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        requestCombo.setEnabled(false);
        requestCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestComboActionPerformed(evt);
            }
        });

        requestLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        requestLabel.setText("Request No");
        requestLabel.setEnabled(false);

        requestCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(requestCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(requestCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(requestLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(requestCheck)
                    .addComponent(requestLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(requestCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        packetCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packetCheckActionPerformed(evt);
            }
        });

        packetLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        packetLabel.setText("Packet ID");
        packetLabel.setEnabled(false);

        packetCombo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        packetCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        packetCombo.setEnabled(false);
        packetCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packetComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(packetCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(packetCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(packetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(packetCheck)
                    .addComponent(packetLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(packetCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        groupCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupCheckActionPerformed(evt);
            }
        });

        groupCombo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        groupCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-" }));
        groupCombo.setEnabled(false);
        groupCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupComboActionPerformed(evt);
            }
        });

        groupLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        groupLabel.setText("Blood Group");
        groupLabel.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(groupCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(groupLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(groupCheck)
                    .addComponent(groupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(groupCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        componentCombo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        componentCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        componentCombo.setEnabled(false);
        componentCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componentComboActionPerformed(evt);
            }
        });

        componentCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                componentCheckActionPerformed(evt);
            }
        });

        componentLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        componentLabel.setText("Component");
        componentLabel.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(componentCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(componentLabel))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(componentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(componentCheck, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(componentLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(componentCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        expiryLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        expiryLabel.setText("Expiry Date");
        expiryLabel.setEnabled(false);

        expiryCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expiryCheckActionPerformed(evt);
            }
        });

        expireDate.setEnabled(false);
        expireDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                expireDatePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(expiryCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(expiryLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(expireDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(expiryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expiryCheck, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expireDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        officerCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                officerCheckActionPerformed(evt);
            }
        });

        officerLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        officerLabel.setText("Issued Officer");
        officerLabel.setEnabled(false);

        IssuerCombo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        IssuerCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        IssuerCombo.setEnabled(false);
        IssuerCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IssuerComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IssuerCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(officerCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(officerLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(officerLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(officerCheck, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IssuerCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        dateLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        dateLabel.setText("Issued Date");
        dateLabel.setEnabled(false);

        dateCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateCheckActionPerformed(evt);
            }
        });

        issueDate.setEnabled(false);
        issueDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                issueDatePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(dateCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(issueDate, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(issueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(44, 44, 44))
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(434, 434, 434))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        searchButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        searchButton.setText("Search An Issue");
        searchButton.setBorder(null);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        reportBtn.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        reportBtn.setText("Generate Report");
        reportBtn.setBorder(null);
        reportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchPanel.setVisible(true);
        searchButton.setVisible(false);
        resizeColumnWidth(issueTable);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void requestCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestCheckActionPerformed
        if (requestCheck.isSelected()) {
            requestCombo.setEnabled(true);
            requestLabel.setEnabled(true);
        }
        if (!requestCheck.isSelected()) {
            requestCombo.setEnabled(false);
            requestLabel.setEnabled(false);
            request = null;
        }
        search();

    }//GEN-LAST:event_requestCheckActionPerformed

    private void packetCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packetCheckActionPerformed
        if (packetCheck.isSelected()) {
            packetCombo.setEnabled(true);
            packetLabel.setEnabled(true);
        }
        if (!packetCheck.isSelected()) {
            packetCombo.setEnabled(false);
            packetLabel.setEnabled(false);
            packetid = null;
        }
        search();

    }//GEN-LAST:event_packetCheckActionPerformed

    private void groupCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupCheckActionPerformed
        if (groupCheck.isSelected()) {
            groupCombo.setEnabled(true);
            groupLabel.setEnabled(true);
        }
        if (!groupCheck.isSelected()) {
            groupCombo.setEnabled(false);
            groupLabel.setEnabled(false);
            bloodgroup = null;
        }
        search();

    }//GEN-LAST:event_groupCheckActionPerformed

    private void componentCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_componentCheckActionPerformed
        if (componentCheck.isSelected()) {
            componentCombo.setEnabled(true);
            componentLabel.setEnabled(true);
        }
        if (!componentCheck.isSelected()) {
            componentCombo.setEnabled(false);
            componentLabel.setEnabled(false);
            type = null;
        }

        search();
    }//GEN-LAST:event_componentCheckActionPerformed

    private void expiryCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expiryCheckActionPerformed
        if (expiryCheck.isSelected()) {
            expireDate.setEnabled(true);
            expiryLabel.setEnabled(true);
        }
        if (!expiryCheck.isSelected()) {
            expireDate.setEnabled(false);
            expiryLabel.setEnabled(false);
            expiryDate = null;
        }
        search();

    }//GEN-LAST:event_expiryCheckActionPerformed

    private void officerCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_officerCheckActionPerformed
        if (officerCheck.isSelected()) {
            officerLabel.setEnabled(true);
            IssuerCombo.setEnabled(true);
        }
        if (!officerCheck.isSelected()) {
            officerLabel.setEnabled(false);
            IssuerCombo.setEnabled(false);
            issuedOfficer = null;
        }
        search();

    }//GEN-LAST:event_officerCheckActionPerformed

    private void dateCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateCheckActionPerformed
        if (dateCheck.isSelected()) {
            issueDate.setEnabled(true);
            dateLabel.setEnabled(true);
        }
        if (!dateCheck.isSelected()) {
            issueDate.setEnabled(false);
            dateLabel.setEnabled(false);
            issuedDate = null;
        }
        search();

    }//GEN-LAST:event_dateCheckActionPerformed

    private void search() {
        dtm = new DefaultTableModel(title, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        issueTable.setModel(dtm);

        request = null;
        packetid = null;
        bloodgroup = null;
        type = null;
        expiryDate = null;
        issuedDate = null;
        issuedOfficer = null;

        if (requestCheck.isSelected()) {
            request = "" + requestCombo.getSelectedItem();
        }
        if (packetCheck.isSelected()) {
            packetid = "" + packetCombo.getSelectedItem();
        }
        if (groupCheck.isSelected()) {
            bloodgroup = "" + groupCombo.getSelectedItem();
        }
        if (componentCheck.isSelected()) {
            type = "" + componentCombo.getSelectedItem();
        }
        if (expiryCheck.isSelected()) {
            java.util.Date expire = expireDate.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            expiryDate = df.format(expire);
            java.sql.Date dateE = new java.sql.Date(expire.getTime());
        }
        if (dateCheck.isSelected()) {
            java.util.Date issue = issueDate.getDate();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            issuedDate = df.format(issue);
            java.sql.Date dateI = new java.sql.Date(issue.getTime());
        }
        if (officerCheck.isSelected()) {
            issuedOfficer = "" + IssuerCombo.getSelectedItem();
        }

        try {
            ResultSet rst = null;
            rst = IssueController.getIssueInfo();

            while (rst.next()) {
                String irequest = rst.getString("RequestNo");
                String ipacketid = rst.getString("PacketID");
                String ibloodgroup = rst.getString("BloodGroup");
                String itype = rst.getString("BloodType");
                String iexpiryDate = rst.getString("DateOfExpiry");
                String iissuedDate = rst.getString("Date");
                String iissuedOfficer = rst.getString("Name");
                String itime = rst.getString("Time");
                int count = 0, satisfy = 0;
                if (request != null) {
                    satisfy++;
                    if (irequest.equals(request)) {
                        count++;
                    }
                }
                if (packetid != null) {
                    satisfy++;
                    if (ipacketid.equals(packetid)) {
                        count++;
                    }
                }
                if (bloodgroup != null) {
                    satisfy++;
                    if (bloodgroup.equals(ibloodgroup)) {
                        count++;
                    }
                }
                if (type != null) {
                    satisfy++;
                    if (type.equals(itype)) {
                        count++;
                    }
                }
                if (issuedDate != null) {
                    satisfy++;
                    if (issuedDate.equals(iissuedDate)) {
                        count++;
                    }
                }
                if (expiryDate != null) {
                    satisfy++;
                    if (expiryDate.equals(iexpiryDate)) {
                        count++;
                    }
                }
                if (issuedOfficer != null) {
                    satisfy++;
                    if (issuedOfficer.equals(iissuedOfficer)) {
                        count++;
                    }
                }

                if (count != 0 && count == satisfy) {
                    String[] row = {irequest, ipacketid, ibloodgroup, itype, iexpiryDate, iissuedOfficer, iissuedDate, itime};
                    dtm.addRow(row);
                    resizeColumnWidth(issueTable);

                }
                if (satisfy == 0) {
                    JOptionPane.showMessageDialog(null, "Select options to continue search");
                    break;
                }

            }
        } catch (SQLException ex) {
            System.out.println("sql");
            Logger.getLogger(IssueController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SampleDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void reportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBtnActionPerformed
        try {

            JasperReport jr = JasperCompileManager.compileReport("./src/reports/issueing/IssueReport.jrxml");
            Map<String, Object> params;
            params = new HashMap<String, Object>();
            JRTableModelDataSource dataSource = new JRTableModelDataSource(issueTable.getModel());
            JasperPrint jp = JasperFillManager.fillReport(jr, params, dataSource);
            JasperViewer.viewReport(jp, false);

        } catch (JRException ex) {
            Logger.getLogger(IssueRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_reportBtnActionPerformed

    private void IssuerComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IssuerComboActionPerformed
        if (IssuerCombo.isEnabled()) {
            search();
        }
    }//GEN-LAST:event_IssuerComboActionPerformed

    private void requestComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestComboActionPerformed
        if (requestCombo.isEnabled()) {
            search();
        }
    }//GEN-LAST:event_requestComboActionPerformed

    private void groupComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupComboActionPerformed
        if (groupCombo.isEnabled()) {
            search();
        }
    }//GEN-LAST:event_groupComboActionPerformed

    private void packetComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packetComboActionPerformed
        if (packetCombo.isEnabled()) {
            search();
        }
    }//GEN-LAST:event_packetComboActionPerformed

    private void componentComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_componentComboActionPerformed
        if (componentCombo.isEnabled()) {
            search();
        }
    }//GEN-LAST:event_componentComboActionPerformed

    private void expireDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_expireDatePropertyChange
        if (expireDate.isEnabled()) {
            search();
        }
    }//GEN-LAST:event_expireDatePropertyChange

    private void issueDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_issueDatePropertyChange
        if (issueDate.isEnabled()) {
            search();
        }
    }//GEN-LAST:event_issueDatePropertyChange

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

    public JPanel getSearchPanel() {
        return this.searchPanel;
    }

    private void fillTable() {
        try {
            ResultSet rst = null;
            rst = IssueController.getIssueInfo();
            dtm = new DefaultTableModel(title, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            issueTable.setModel(dtm);

            while (rst.next()) {

                String requestNo = rst.getString("RequestNo");
                String packetid = rst.getString("PacketId");
                String group = rst.getString("BloodGroup");
                String component = rst.getString("BloodType");
                String dateOfExpiry = rst.getString("DateOfExpiry");
                String issuer = rst.getString("Name");
                String issuedDate = rst.getString("Date");
                String issuedTime = rst.getString("Time");

                String[] selectedRow = {requestNo, packetid, group, component, dateOfExpiry, issuer, issuedDate, issuedTime};

                dtm.addRow(selectedRow);
                resizeColumnWidth(issueTable);
                issueTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            }

        } catch (SQLException ex) {
            System.out.println("sql");
            //Logger.getLogger(BloodPacketForm.class.getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SampleDetails.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox IssuerCombo;
    private javax.swing.JCheckBox componentCheck;
    private javax.swing.JComboBox componentCombo;
    private javax.swing.JLabel componentLabel;
    private javax.swing.JCheckBox dateCheck;
    private javax.swing.JLabel dateLabel;
    private com.toedter.calendar.JDateChooser expireDate;
    private javax.swing.JCheckBox expiryCheck;
    private javax.swing.JLabel expiryLabel;
    private javax.swing.JCheckBox groupCheck;
    private javax.swing.JComboBox groupCombo;
    private javax.swing.JLabel groupLabel;
    private com.toedter.calendar.JDateChooser issueDate;
    private javax.swing.JTable issueTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JCheckBox officerCheck;
    private javax.swing.JLabel officerLabel;
    private javax.swing.JCheckBox packetCheck;
    private javax.swing.JComboBox packetCombo;
    private javax.swing.JLabel packetLabel;
    private javax.swing.JButton reportBtn;
    private javax.swing.JCheckBox requestCheck;
    private javax.swing.JComboBox requestCombo;
    private javax.swing.JLabel requestLabel;
    private javax.swing.JButton searchButton;
    private javax.swing.JPanel searchPanel;
    // End of variables declaration//GEN-END:variables

}
