/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author koumudi
 */
public class DBExecution {
    
    private static DBExecution dbStatement = null;
    private DBExecution(){}
    
    public static DBExecution getInstance(){
        if(dbStatement==null){
            dbStatement = new DBExecution();
        }
        
        return dbStatement;    
    }
    
    public ResultSet selectMatchingUser(String username, String password) throws SQLException, ClassNotFoundException{
    
            Connection con = DBConnector.getDBConnection();    
            String sql = "Select * from users where username=? and password=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            
            return rs;
    } 
    
    public ResultSet selectStationByStCode(String stCode) throws SQLException, ClassNotFoundException{
    
            Connection con = DBConnector.getDBConnection();
            String sql1 = "Select * from stations where stcode=?";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, stCode);
            ResultSet rs = pst.executeQuery();
            
            return rs;
    }
    
    public ResultSet selectAllDestinationStations(String originName) throws SQLException, ClassNotFoundException{
    
            Connection con = DBConnector.getDBConnection();
            String sql1 = "Select * from stations where stname!=?";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, originName);
            ResultSet rs = pst.executeQuery();
            
            return rs;
    }
    
    public void insertStationTicketData(JTable table, Integer year,String month,String origin,Integer bookedTkts,Integer returnedTkts) throws SQLException, ClassNotFoundException{
        
            int rows = table.getRowCount();
            Connection conn = DBConnector.getDBConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO travelled_tickets (`year`, `month`, `from`, `to`, `1stCls`, `2ndCls`, `3rdClsA`, `3rdClsB`, `3rdClsC`, `total`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setInt(1,year);
            pst.setString(2,month);
            pst.setString(3,origin);
            
            for (int row = 0; row < rows; row++) {
                String destination = (String) table.getValueAt(row, 0);
                int cls_1 = Integer.parseInt(table.getValueAt(row, 1).toString());
                int cls_2 = Integer.parseInt(table.getValueAt(row, 2).toString());
                int cls_3a = Integer.parseInt(table.getValueAt(row, 3).toString());
                int cls_3b = Integer.parseInt(table.getValueAt(row, 4).toString());
                int cls_3c = Integer.parseInt(table.getValueAt(row, 5).toString());
                int ttl = cls_1 + cls_2 + cls_3a + cls_3b + cls_3c;
                    
                pst.setString(4, destination);
                pst.setInt(5, cls_1);
                pst.setInt(6, cls_2);
                pst.setInt(7, cls_3a);
                pst.setInt(8, cls_3b);
                pst.setInt(9, cls_3c);
                pst.setInt(10, ttl);
                    
                pst.addBatch();
                    
            }

            pst.executeBatch();
            
            String aggragateSql = "INSERT INTO aggregate_counts (`year`, `month`, `origin`, `booked_tkt`, `returned_tkt`) VALUES (?, ?, ?, ?, ?)";
                    
            PreparedStatement aggregatePst = conn.prepareStatement(aggragateSql);
            
            aggregatePst.setInt(1, year);
            aggregatePst.setString(2,month);
            aggregatePst.setString(3,origin);
            aggregatePst.setInt(4,bookedTkts);
            aggregatePst.setInt(5,returnedTkts);
            aggregatePst.execute();
              
            conn.commit();
    }
}
