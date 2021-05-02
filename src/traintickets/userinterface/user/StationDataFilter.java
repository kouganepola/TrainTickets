/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface.user;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import traintickets.core.DBExecution;
import traintickets.userinterface.components.ComboBoxEditor;
import traintickets.userinterface.components.ComboBoxRenderer;
import traintickets.userinterface.components.TextHandler;

/**
 *
 * @author Sanduni Alwis
 */
public class StationDataFilter extends javax.swing.JFrame {

    /**
     * Creates new form Selection
     * @param parent
     */
    public StationDataFilter(JFrame parent) {
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
        
        ResultSet rs;
        try {
            rs = DBExecution.getInstance().selectAllActiveStations();
        while (rs.next()) {

                String stations = rs.getString("stname");
                stationArray.add(stations);
        }
        stationArray.add("ALL");
        
        setupComboBox(originBox);
        setupComboBox(destinationBox);
        ((JLabel)originBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((JLabel)destinationBox.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        

//
//                   
//            form_table.getColumnModel().getColumn(0).setCellEditor(new ComboBoxEditor(combo));
//            form_table.getColumnModel().getColumn(0).setCellRenderer(new ComboBoxRenderer(combo));
            
        } catch (SQLException ex) {
            Logger.getLogger(StationDataFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StationDataFilter.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel2 = new javax.swing.JPanel();
        yeartxt = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        monthtxt = new javax.swing.JSpinner();
        lbl_code = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        originBox = new javax.swing.JComboBox<>();
        destinationBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        yeartxt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        yeartxt.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1611517380486L), null, null, java.util.Calendar.YEAR));
        yeartxt.setPreferredSize(new java.awt.Dimension(109, 34));
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

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Origin");

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

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Destination");

        originBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        originBox.setPreferredSize(new java.awt.Dimension(109, 34));

        destinationBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        destinationBox.setPreferredSize(new java.awt.Dimension(109, 34));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(89, 89, 89)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(monthtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(53, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(originBox, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(yeartxt, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(49, 49, 49)
                        .addComponent(destinationBox, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_code, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(yeartxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(monthtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(originBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(lbl_code, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(destinationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(okButton))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed

        backButtonFunction();
    }//GEN-LAST:event_backButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed

        executeSelection();

    }//GEN-LAST:event_okButtonActionPerformed

    public void setupComboBox(JComboBox combo){
    

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>)combo.getModel();
        model.removeAllElements();
        stationArray.forEach(model::addElement);
        
        TextHandler textHandler = new TextHandler();
        combo.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        char keyChar = e.getKeyChar();
                        if (!Character.isDefined(keyChar)) {
                            return;
                        }
                        int keyCode = e.getKeyCode();
                        String selectedItem = combo.getSelectedItem().toString();
                        switch (keyCode) {
                            case KeyEvent.VK_BACK_SPACE:
                                textHandler.removeCharAtEnd();
                                break;
                            case KeyEvent.VK_ENTER:
                                textHandler.reset();
                                break;
                            default:
                                textHandler.add(keyChar);
                        }
                        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>)combo.getModel();
                        model.removeAllElements();
                        java.util.List<String> filteredItems = new ArrayList<>();
                        //add matched items first
                        
                        JTextField textfield = (JTextField) combo.getEditor().getEditorComponent();
                        textfield.setText(textHandler.getText());
                        if(!textHandler.getText().isEmpty()){
                            for (String item : stationArray) {
                                if (item.matches(textHandler.getText().toUpperCase()+"(.*)")){
                                    filteredItems.add(item);
                                }
                            }
                            filteredItems.forEach(model::addElement);
                        }else{
                            stationArray.forEach(model::addElement);
                            combo.setSelectedItem(selectedItem);
                        }
                    }
                });
        combo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
            }

            @Override
            public void focusLost(FocusEvent fe) {
                textHandler.reset();
            }
        });


    }
    
    public Object getYear(){
        return yeartxt.getValue();
    }
    
    public String getMonth(){
        return monthtxt.getValue().toString();
    }
        
    public String getOrigin(){
        return originBox.getSelectedItem().toString();
    }

    public String getDestination(){
        return destinationBox.getSelectedItem().toString();
    }
    private void yeartxtStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_yeartxtStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_yeartxtStateChanged

    private void yeartxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yeartxtKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_yeartxtKeyPressed

    private void monthtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_monthtxtKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_monthtxtKeyPressed
        
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

            try {
                if (!originBox.getSelectedItem().toString().equals(destinationBox.getSelectedItem().toString())) {

                        StationDataView view = new StationDataView(this);
                        
                        if(view.getVisible()){                        
                            view.setVisible(true);
                            setVisible(false);
                        }else{
                            view.dispose();
                        }

                } else {
                    JOptionPane.showMessageDialog(null, "Origin and Destination cannot be the same!");

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    
    }
    
    private String operation;
    private javax.swing.JFrame parent;
    private ArrayList<String> stationArray = new ArrayList<>();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> destinationBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_code;
    public static javax.swing.JSpinner monthtxt;
    private javax.swing.JButton okButton;
    private javax.swing.JComboBox<String> originBox;
    public static javax.swing.JSpinner yeartxt;
    // End of variables declaration//GEN-END:variables
}