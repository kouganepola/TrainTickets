/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface.components;

import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author koumudi
 */
public class JSpinnerRenderer extends JSpinner implements TableCellRenderer
{
    public JSpinnerRenderer()
    {
        setBorder(null);
    }

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
             if (value!=null && value != "") {
                	setValue(value);
                }
                else {
                	setValue(0);
                }

        return this;
    }
}