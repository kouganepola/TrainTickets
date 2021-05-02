/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface.components;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import traintickets.core.DBExecution;

/**
 *
 * @author koumudi
 */
public class StationDataTableNumericCellRenderer extends JHintTextField implements TableCellRenderer
{
    private int average =0;
    private boolean getAverageDataIntoAccount = false;
    private String operation;
    private String origin;
    private int year ;
    private String month;
    private Map<String, ArrayList<String>> anomalies = new HashMap<String, ArrayList<String>>();
    public StationDataTableNumericCellRenderer(String operation,String origin, int year, String month)
    {
        setBorder(null);
        this.operation= operation;
        this.origin = origin;
        this.year = year;
        this.month = month;
    }

    private void setAverageValue(int average){
        this.average = average;
    }

        
    public boolean hasCellAnomaly(int row, int column){
        if(anomalies.containsKey(String.valueOf(row))){
            return anomalies.get(String.valueOf(row)).contains(String.valueOf(column));
        }
        return false;
    }
    
    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
       try {
             if (value!=null && value != "") {
                 if (value.toString().matches("[0-9]+")){
                    findDifference(table, row, column);
                    if( this.getAverageDataIntoAccount && 
                            (average-Integer.parseInt(value.toString()) > average*0.05 || Integer.parseInt(value.toString())- average > average*0.05 )){
                        setBackground(Color.RED);
                        ArrayList<String> defaultArrayList = new ArrayList<String>();
                        ArrayList<String> existingRowAnomalies = anomalies.getOrDefault(String.valueOf(row),defaultArrayList ); 
                        existingRowAnomalies.add(String.valueOf(column));
                        anomalies.put(String.valueOf(row), existingRowAnomalies);
                    }else{
                        ArrayList<String> defaultArrayList = new ArrayList<String>();
                        ArrayList<String> existingRowAnomalies = anomalies.getOrDefault(String.valueOf(row),defaultArrayList ); 
                        existingRowAnomalies.remove(String.valueOf(column));
                        if(existingRowAnomalies.isEmpty()){
                            anomalies.remove(String.valueOf(row));
                        }else{
                            anomalies.put(String.valueOf(row), existingRowAnomalies);
                        }
                        setBackground(Color.WHITE);
                    }
                           setText(value.toString());
                 }
            }
            else {
                	setText("0");
            }

        } catch (SQLException ex) {
            Logger.getLogger(StationDataTableNumericCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StationDataTableNumericCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
               return this;
    }  
    
        private void findDifference(JTable table, int row, int column) throws SQLException, ClassNotFoundException{
            ResultSet averageData = DBExecution.getInstance().getAverageTicketDataOfStation(this.origin); 
            String ticketClass = "1stCls";
            String properTicketClass = "1st Class";
            switch(column){
                case 1:
                    ticketClass = "1stCls";
                    properTicketClass = "1st Class";
                    break;
                case 2:
                    ticketClass = "2ndCls";
                    properTicketClass = "2nd Class";
                    break;
                case 3:
                    ticketClass = "3rdClsA";
                    properTicketClass = "3rd Class A";
                    break;
                case 4:
                    ticketClass = "3rdClsB";
                    properTicketClass = "3rd Class B";
                    break;
                case 5:
                    ticketClass = "3rdClsC";
                    properTicketClass = "3rd Class C";
                    break;

            }
            System.out.println(ticketClass);
            this.getAverageDataIntoAccount = false;
            while(averageData.next()){
                System.out.println("hasNext");
                    String destination = (String) table.getValueAt(row, 0);
                    if(destination != null && destination.equals(averageData.getString("to"))){
                        int cls_1 = Integer.parseInt(table.getValueAt(row, column).toString());
                        setAverageValue(averageData.getInt(ticketClass));
                        this.getAverageDataIntoAccount = true && isSupervisedAnomaly(destination, properTicketClass, cls_1);
                        
                    }

            }
    }
        
        private boolean isSupervisedAnomaly(String to, String ticketClass, int value) throws ClassNotFoundException, SQLException{
            
            System.out.println(origin+" "+to+" "+year+month+ticketClass+value);
            ResultSet rs = DBExecution.getInstance().getUnsupervisedAnomaly(origin,to,year, month, ticketClass, value);
            if(rs.next() && rs.getBoolean("supervised")){
                System.out.println(to+","+ticketClass+value);
                return false;
            }
            return true;
        
        }
    
}