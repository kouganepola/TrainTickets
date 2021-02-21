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
    
    public ResultSet selectUser(String username) throws SQLException, ClassNotFoundException{
    
            Connection con = DBConnector.getDBConnection();    
            String sql = "Select * from users where username=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            
            return rs;
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
    
       
    public ResultSet selectTrainLines() throws SQLException, ClassNotFoundException{
    
            Connection con = DBConnector.getDBConnection();
            String sql1 = "Select * from trainlines";
            PreparedStatement pst = con.prepareStatement(sql1);
            ResultSet rs = pst.executeQuery();
            
            return rs;
    }
    
        public ResultSet selectTrainLine(String trainline) throws SQLException, ClassNotFoundException{
    
            Connection con = DBConnector.getDBConnection();
            String sql1 = "Select * from trainlines where name=?";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, trainline);
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
    
    public ResultSet selectAllActiveStations() throws SQLException, ClassNotFoundException{
    
            Connection con = DBConnector.getDBConnection();
            String sql1 = "Select * from stations where isDeleted = false";
            PreparedStatement pst = con.prepareStatement(sql1);
            ResultSet rs = pst.executeQuery();
            
            return rs;
    }
        
    public ResultSet selectAllActiveDestinationStations(String originName) throws SQLException, ClassNotFoundException{
    
            Connection con = DBConnector.getDBConnection();
            String sql1 = "Select * from stations where stname!=? and isDeleted = false";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, originName);
            ResultSet rs = pst.executeQuery();
            
            return rs;
    }
    
    public void updateStationTicketData(JTable table, Integer year,String month,String origin,Integer bookedTkts,Integer returnedTkts) throws SQLException, ClassNotFoundException{
        
            Connection conn = DBConnector.getDBConnection();
            conn.setAutoCommit(false);
            prepareDeletionStatementForStationTicketData(conn, year, month, origin);
            prepareInsertionStatementForStationTicketData(conn, table, year, month, origin, bookedTkts, returnedTkts);
            conn.commit();
    }
    
    public void deleteStationTicketData(Integer year, String month, String origin) throws ClassNotFoundException, SQLException{
            Connection conn = DBConnector.getDBConnection();
            conn.setAutoCommit(false);
            
            prepareDeletionStatementForStationTicketData(conn, year, month, origin);
            
            conn.commit();
    }
    
    private void prepareDeletionStatementForStationTicketData(Connection conn, Integer year, String month, String origin) throws ClassNotFoundException, SQLException{

            String sql = "DELETE FROM travelled_tickets WHERE year=? and month=?  and `from`=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, year);
            stmt.setString(2,month);
            stmt.setString(3,origin);
            
            stmt.execute();
            
            String sql1 = "DELETE FROM aggregate_counts WHERE year=? and month=?  and origin=?";
     
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setInt(1, year);
            stmt1.setString(2,month);
            stmt1.setString(3,origin);
            
            stmt1.execute();
    }
    
    public void insertStationTicketData(JTable table, Integer year,String month,String origin,Integer bookedTkts,Integer returnedTkts) throws SQLException, ClassNotFoundException{
        
            Connection conn = DBConnector.getDBConnection();
            conn.setAutoCommit(false);
            
            prepareInsertionStatementForStationTicketData(conn, table, year, month, origin, bookedTkts, returnedTkts);
            conn.commit();
    }
    
    private void prepareInsertionStatementForStationTicketData(Connection conn, JTable table, Integer year,String month,String origin,Integer bookedTkts,Integer returnedTkts) throws SQLException, ClassNotFoundException{
        
            int rows = table.getRowCount();

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

    }
    
    public ResultSet getStationTicketDataofMonth(Integer year,String month,String origin) throws SQLException, ClassNotFoundException{
        
            Connection con = DBConnector.getDBConnection();
            String sql1 = "Select `to`, 1stCls, 2ndCls,3rdClsA, 3rdClsB, 3rdClsC, total from travelled_tickets where `from`=? and year=? and month=?";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, origin);
            pst.setInt(2, year);
            pst.setString(3, month);
            ResultSet rs = pst.executeQuery();
            
            return rs;            
    }
    
    public ResultSet getAggregateStationDataofMonth(Integer year,String month,String origin) throws SQLException, ClassNotFoundException{
        
            Connection con = DBConnector.getDBConnection();
            String sql1 = "Select booked_tkt, returned_tkt from aggregate_counts where origin=? and year=? and month=?";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, origin);
            pst.setInt(2, year);
            pst.setString(3, month);
            ResultSet rs = pst.executeQuery();
            
            return rs;            
    }
    
    public void insertUser(String user, String password, String role) throws ClassNotFoundException, SQLException{
        Connection con = DBConnector.getDBConnection();
        String sql = "INSERT INTO users (`username`, `password`, `role`) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, user);
        pst.setString(2, password);
        pst.setString(3, role);
        pst.execute();
        
    }
    
    public void deleteUser(String user) throws ClassNotFoundException, SQLException{
        Connection con = DBConnector.getDBConnection();
        String sql = "DELETE FROM users WHERE username=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, user);
        pst.execute();     
    }
    
    public boolean deleteStation(String stname) throws ClassNotFoundException, SQLException{
        Connection con = DBConnector.getDBConnection();
        String sql = "Update stations set isDeleted = true WHERE stname=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, stname);
        return pst.execute();     
    }
        
    public void insertStation(String stationName, String stationCode, int trainline, boolean anOrigin) throws ClassNotFoundException, SQLException{
        Connection con = DBConnector.getDBConnection();
        String sql = "INSERT INTO stations (`stname`, `stcode`, `trainline`, `anOrigin`) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, stationName);
        pst.setString(2, stationCode);
        pst.setInt(3, trainline);
        pst.setBoolean(4,anOrigin);
        pst.execute();
        
    }
    
}
