/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface.components;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author koumudi
 */
public class ComboBoxRenderer extends JComboBox implements TableCellRenderer
{
    public ComboBoxRenderer()
    {
        setBorder(null);
    }

    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        removeAllItems();
        addItem( value );

        return this;
    }
}