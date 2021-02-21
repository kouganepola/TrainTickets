/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import traintickets.core.DBExecution;

/**
 *
 * @author Sanduni Alwis
 */
public class Selection extends javax.swing.JFrame {

    /**
     * Creates new form Selection
     * @param parent
     */
    public Selection(JFrame parent, String operation) {
        initComponents();
        
        yeartxt.setEditor(new JSpinner.DateEditor(yeartxt, new SimpleDateFormat("yyyy").toPattern()));
        JSpinner.DefaultEditor yearEditor = (JSpinner.DefaultEditor)yeartxt.getEditor();
        yearEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        
        JSpinner.DefaultEditor monthEditor = (JSpinner.DefaultEditor)monthtxt.getEditor();
        monthEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        
        
        Toolkit toolkit = getToolkit();
        setResizable(false);
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        
        this.operation = operation;
        this.parent = parent;
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
        yeartxt = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        codetxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        monthtxt = new javax.swing.JSpinner();
        lbl_code = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        yeartxt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        yeartxt.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1611517380486L), null, null, java.util.Calendar.YEAR));
        yeartxt.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                yeartxtStateChanged(evt);
            }
        });
        yeartxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                yeartxtKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Month");

        okButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        okButton.setText("OK");
        okButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        okButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                okButtonKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Year");

        codetxt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        codetxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        codetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codetxtActionPerformed(evt);
            }
        });
        codetxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codetxtKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Station Code");

        backButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        backButton.setText("Back");
        backButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        backButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                backButtonKeyPressed(evt);
            }
        });

        monthtxt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        monthtxt.setModel(new javax.swing.SpinnerListModel(new String[] {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"}));
        monthtxt.setAutoscrolls(true);
        monthtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                monthtxtKeyPressed(evt);
            }
        });

        lbl_code.setForeground(new java.awt.Color(204, 18, 18));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_code, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(29, 29, 29)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(yeartxt)
                                .addComponent(codetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(monthtxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(51, 51, 51))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yeartxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(codetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_code, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(okButton))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        backButtonFunction();
    }//GEN-LAST:event_backButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed

        executeSelection();

    }//GEN-LAST:event_okButtonActionPerformed

    public Object getYear(){
        return yeartxt.getValue();
    }
    
    public String getMonth(){
        return monthtxt.getValue().toString();
    }
        
    public String getStation(){
        return codetxt.getText();
    }

    private void yeartxtStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_yeartxtStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yeartxtStateChanged

    private void codetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codetxtActionPerformed

    private void yeartxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yeartxtKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_yeartxtKeyPressed

    private void monthtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthtxtKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_monthtxtKeyPressed

    private void codetxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codetxtKeyPressed

        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            executeSelection();

        }

    }//GEN-LAST:event_codetxtKeyPressed
        
    private void okButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_okButtonKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            executeSelection();

        }
    }//GEN-LAST:event_okButtonKeyPressed

    private void backButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_backButtonKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode()== java.awt.event.KeyEvent.VK_ENTER){ 
            backButtonFunction();

        }
    }//GEN-LAST:event_backButtonKeyPressed

    private void backButtonFunction(){
        parent.setVisible(true);
        dispose();
    }
    
    public String getOperation(){
        return this.operation;
    }
    
    private void executeSelection(){
        
        if (codetxt.getText().trim().isEmpty()) {
            lbl_code.setText(" ST CODE is empty!");

        } else {

            try {
                
                ResultSet rs = DBExecution.getInstance().selectStationByStCode(codetxt.getText());

                if (rs.next()) {
                    
                    if(parent instanceof UserFunctions){
                        StationDataForm form = new StationDataForm(this);
                        if(form.getVisible()){                        
                            form.setVisible(true);
                            setVisible(false);
                        }else{
                            form.dispose();
                        }
                        
                    }else if (parent instanceof ShowDataFunctions){
                        StationDataView view = new StationDataView(this);
                        
                        if(view.getVisible()){                        
                            view.setVisible(true);
                            setVisible(false);
                        }else{
                            view.dispose();
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid ST CODE!");
                    codetxt.setText("");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    
    }
    
    private String operation;
    private javax.swing.JFrame parent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    public static javax.swing.JTextField codetxt;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_code;
    public static javax.swing.JSpinner monthtxt;
    private javax.swing.JButton okButton;
    public static javax.swing.JSpinner yeartxt;
    // End of variables declaration//GEN-END:variables
}
