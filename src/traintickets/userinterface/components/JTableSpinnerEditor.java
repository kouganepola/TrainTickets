/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface.components;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author koumudi
 */
public class JTableSpinnerEditor extends DefaultCellEditor{
    private static JSpinner spinner;
    private final JSpinner.DefaultEditor editor;
    private JTextField textField;

    // Initializes the spinner.
    public JTableSpinnerEditor() {
        super(new JTextField());
        spinner = new JSpinner(new SpinnerNumberModel(0, 0, 10000000, 1));
        
        editor = ((JSpinner.DefaultEditor)spinner.getEditor());
        textField = editor.getTextField();
        
        spinner.addFocusListener( new FocusListener() {
            @Override
            public void focusGained( FocusEvent fe ) {
                System.err.println("Got focus");
                SwingUtilities.invokeLater(() -> {
                    textField.requestFocus();
                });
            }
            @Override
            public void focusLost( FocusEvent fe ) {
            }
        });
        
        textField.addFocusListener( new FocusListener() {
            @Override
            public void focusGained( FocusEvent fe ) {
                System.err.println("Got focus");
                SwingUtilities.invokeLater(() -> {
                    textField.selectAll();
                });
            }
            @Override
            public void focusLost( FocusEvent fe ) {
            }
        });
        
        textField.addActionListener((ActionEvent ae) -> {
            stopCellEditing();
        });
        
        
    }

    // Prepares the spinner component and returns it.
    @Override
    public Component getTableCellEditorComponent(
        JTable table, Object value, boolean isSelected, int row, int column
    ) {
        if (value!=null && value != "") {
            spinner.setValue(value);
        }
        else {
            spinner.setValue(0);
        }
        SwingUtilities.invokeLater(textField::requestFocus);
        return spinner;
        
    }

    @Override
    public boolean isCellEditable( EventObject eo ) {
        System.err.println("isCellEditable");
        return true;
    }

    // Returns the spinners current value.
    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    @Override
    public boolean stopCellEditing() {
        System.err.println("Stopping edit");
        try {
            editor.commitEdit();
            spinner.commitEdit();
        } catch ( java.text.ParseException e ) {
            JOptionPane.showMessageDialog(null,
                "Invalid value, discarding.");
        }
        return super.stopCellEditing();
    }
    
}
