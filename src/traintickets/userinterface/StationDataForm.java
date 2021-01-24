/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import traintickets.core.DBExecution;
import traintickets.userinterface.components.ComboBoxRenderer;
import traintickets.userinterface.components.JSpinnerRenderer;
import traintickets.userinterface.components.JTableSpinnerEditor;

/**
 *
 * @author Sanduni Alwis
 */
public class StationDataForm extends javax.swing.JFrame {

    /**
     * Creates new form stationDemo
     * @param parent
     */
    public StationDataForm(Selection parent) {
        initComponents();

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();

        setResizable(false);

        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        
        this.parent = parent;

        String year = parent.getYear();
        yearl_demo.setText(year);
        String month = parent.getMonth();
        monthl_demo.setText(month);

        try {
            String code = parent.getStation();
            ResultSet rs = DBExecution.getInstance().selectStationByStCode(code);

            if (rs.next()) {
                String name = rs.getString("stname");

                namel_demo.setText(name);
                
                fillColumns(name);
                                
            } else {
                JOptionPane.showMessageDialog(null, "error!");

            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jPanel1 = new javax.swing.JPanel();
        namel_demo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        clearTableButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        minusButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_demo = new javax.swing.JTable();
        yearl_demo = new javax.swing.JLabel();
        monthl_demo = new javax.swing.JLabel();
        returnedtkt_kni = new javax.swing.JSpinner();
        bookedtkt_kni = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1440, 490));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setInheritsPopupMenu(true);
        jPanel1.setMaximumSize(new java.awt.Dimension(1440, 590));
        jPanel1.setMinimumSize(new java.awt.Dimension(1440, 590));
        jPanel1.setPreferredSize(new java.awt.Dimension(1440, 490));

        namel_demo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("BOOKED TKT");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("RTN TKT");

        submitButton.setText("SUBMIT");
        submitButton.setMaximumSize(new java.awt.Dimension(89, 27));
        submitButton.setMinimumSize(new java.awt.Dimension(89, 27));
        submitButton.setPreferredSize(new java.awt.Dimension(89, 27));
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        clearTableButton.setText("Clear Table");
        clearTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearTableButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.setMaximumSize(new java.awt.Dimension(89, 27));
        backButton.setMinimumSize(new java.awt.Dimension(89, 27));
        backButton.setPreferredSize(new java.awt.Dimension(89, 27));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        minusButton.setText("-");
        minusButton.setMaximumSize(new java.awt.Dimension(89, 27));
        minusButton.setMinimumSize(new java.awt.Dimension(89, 27));
        minusButton.setPreferredSize(new java.awt.Dimension(89, 27));
        minusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusButtonActionPerformed(evt);
            }
        });

        addButton.setText("+");
        addButton.setMaximumSize(new java.awt.Dimension(89, 27));
        addButton.setMinimumSize(new java.awt.Dimension(89, 27));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(51, 204, 255));

        table_demo.setAutoCreateRowSorter(true);
        table_demo.setBackground(new java.awt.Color(0, 204, 204));
        table_demo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 255, 255)));
        table_demo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        table_demo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "1st Class", "2nd Class", "3rd Class A", "3rd Class B", "3rd Class C"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table_demo.setToolTipText("");
        table_demo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table_demo.setCellSelectionEnabled(true);
        table_demo.setEditingRow(1);
        table_demo.setGridColor(new java.awt.Color(0, 102, 102));
        table_demo.setMaximumSize(new java.awt.Dimension(2147483647, 1500));
        table_demo.setMinimumSize(new java.awt.Dimension(1000, 64));
        table_demo.setNextFocusableComponent(submitButton);
        table_demo.setPreferredSize(new java.awt.Dimension(1400, 800));
        table_demo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table_demo.setSurrendersFocusOnKeystroke(true);
        jScrollPane1.setViewportView(table_demo);
        table_demo.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        returnedtkt_kni.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        returnedtkt_kni.setMaximumSize(new java.awt.Dimension(49, 27));
        returnedtkt_kni.setMinimumSize(new java.awt.Dimension(49, 27));
        returnedtkt_kni.setPreferredSize(new java.awt.Dimension(49, 27));

        bookedtkt_kni.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        bookedtkt_kni.setMaximumSize(new java.awt.Dimension(49, 35));
        bookedtkt_kni.setMinimumSize(new java.awt.Dimension(49, 27));
        bookedtkt_kni.setPreferredSize(new java.awt.Dimension(49, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(namel_demo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(45, 45, 45)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(yearl_demo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(returnedtkt_kni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bookedtkt_kni, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGap(236, 236, 236)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(monthl_demo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clearTableButton, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(minusButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1332, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 84, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(namel_demo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearl_demo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(monthl_demo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(bookedtkt_kni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(returnedtkt_kni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearTableButton))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fillColumns(String origin) {

        try {
            ResultSet rs = DBExecution.getInstance().selectAllDestinationStations(origin);

            JComboBox combo = new JComboBox();
            while (rs.next()) {

                String stations = rs.getString("stname");
                combo.addItem(stations);

            }
            
            table_demo.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(combo));
            table_demo.getColumnModel().getColumn(0).setCellRenderer(new ComboBoxRenderer());
            
            int columnCount = table_demo.getColumnCount();
            
            for(int column=1;column<columnCount;column++){
              table_demo.getColumnModel().getColumn(column).setCellEditor(new JTableSpinnerEditor());
              table_demo.getColumnModel().getColumn(column).setCellRenderer(new JSpinnerRenderer());
            }
               
            table_demo.setRowHeight(20);
            
            ActionMap am = table_demo.getActionMap();

            
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.out.println(e);
        }
    }


    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:

        try {

            Integer year = Integer.parseInt(yearl_demo.getText());
            String month = monthl_demo.getText();
            String origin = namel_demo.getText();
            Integer bookedTkts = Integer.parseInt(bookedtkt_kni.getValue().toString());
            Integer returnedTkts = Integer.parseInt(returnedtkt_kni.getValue().toString());
             
            DBExecution.getInstance().insertStationTicketData(table_demo, year, month, origin, bookedTkts, returnedTkts);
            
            JOptionPane.showMessageDialog(this, "successfull!");
            bookedtkt_kni.setValue(0);
            returnedtkt_kni.setValue(0);
            clearTable();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void addRow() {
        DefaultTableModel dtm = (DefaultTableModel) table_demo.getModel();
        dtm.setRowCount(dtm.getRowCount() + 1);
    }

    private void removeRow() {
        DefaultTableModel dtm = (DefaultTableModel) table_demo.getModel();
        int selectedRow = table_demo.getSelectedRow();
        if(selectedRow>=0){
            dtm.removeRow(selectedRow);
        }else{
            JOptionPane.showMessageDialog(this, "No row is selected to remove.");
        }
    }

    private void clearTable() {
        DefaultTableModel dtm = (DefaultTableModel) table_demo.getModel();
        dtm.setRowCount(0);
    }


    private void clearTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearTableButtonActionPerformed
        clearTable();
    }//GEN-LAST:event_clearTableButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
        dispose();
        parent.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void minusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusButtonActionPerformed
        removeRow();
    }//GEN-LAST:event_minusButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addRow();
    }//GEN-LAST:event_addButtonActionPerformed

    private JFrame parent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JSpinner bookedtkt_kni;
    private javax.swing.JButton clearTableButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton minusButton;
    private javax.swing.JLabel monthl_demo;
    private javax.swing.JLabel namel_demo;
    private javax.swing.JSpinner returnedtkt_kni;
    private javax.swing.JButton submitButton;
    private javax.swing.JTable table_demo;
    private javax.swing.JLabel yearl_demo;
    // End of variables declaration//GEN-END:variables
}