/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author koumudi
 */
public class CustomNumericCellEditor extends DefaultCellEditor{
    private final JTextField textField;
    private boolean editable = true;
    public CustomNumericCellEditor(boolean editable) {
        super(new JTextField());    
        this.editable = editable;
        this.textField = (JTextField)super.getComponent();
        
        this.textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent fe) {                            
                        SwingUtilities.invokeLater(() -> {
                      textField.selectAll();
                                     });
                }

                @Override
                public void focusLost(FocusEvent fe) {
                    
                }
            });
        
    }
    // Prepares the spinner component and returns it.
    @Override
    public Component getTableCellEditorComponent(
        JTable table, Object value, boolean isSelected, int row, int column
    ) {
        if (value!=null && value != "") {
            textField.setText(value.toString());
        }
        else {
            textField.setText("0");
        }
        return textField;
        
    }

    @Override
    public boolean isCellEditable( EventObject eo ) {
                System.out.println("IsEditable"+editable);
        return editable;
    }

    public void setEditable (boolean editable){
        System.out.println("Editable"+editable);
            this.editable = editable;
    }
    // Returns the spinners current value.
    @Override
    public Object getCellEditorValue() {
        return textField.getText();
    }
   
    
    
}
