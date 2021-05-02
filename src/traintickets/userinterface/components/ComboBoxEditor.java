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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author koumudi
 */
public class ComboBoxEditor extends AbstractCellEditor
        implements TableCellEditor, ActionListener {
     
    private String  station;
    private JComboBox combo;
    public ComboBoxEditor(JTable table, ArrayList<String> stationArray) {
        combo = new JComboBox();
        
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) combo.getModel();
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
                            case KeyEvent.VK_TAB:
                            case KeyEvent.VK_DOWN:
                            case KeyEvent.VK_UP:
                            case KeyEvent.VK_RIGHT:
                            case KeyEvent.VK_LEFT:
                                textHandler.reset();
                                table.repaint();
                                break;
                            default:
                                textHandler.add(keyChar);
                        }
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
                        table.repaint();
                        
                    }
                });
                
                combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {

                if(ie.getStateChange()==ItemEvent.SELECTED){
                        textHandler.reset();
                        table.repaint();
                
                    }
                }
                });
        
    }
     
    @Override
    public Object getCellEditorValue() {
        return this.station;
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.station = String.valueOf(value);
        combo.setSelectedItem(this.station);
        combo.addActionListener(this);
        return combo;
    }
 
    @Override
    public void actionPerformed(ActionEvent event) {
        if(combo.getSelectedItem()!=null){
            this.station = combo.getSelectedItem().toString();
        }
    }
    
}
