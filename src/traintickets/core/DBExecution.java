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
import traintickets.userinterface.components.StationDataTableNumericCellRenderer;

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
    
    public ResultSet selectExistingToStations(String fromStation) throws SQLException, ClassNotFoundException{
    
            Connection con = DBConnector.getDBConnection();
            String sql1 = "SELECT DISTINCT `to` FROM travelled_tickets WHERE `from`=?";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, fromStation);
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
            String sql1 = "REPLACE INTO anomalies (`year`, `month`, `from`, `to`, `ticketType`,`value`, `supervised`) VALUES (?, ?, ? ,? ,?, ?, ?)";
                    
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setInt(1,year);
            pst.setString(2,month);
            pst.setString(3,origin);
            
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            
            pst1.setInt(1,year);
            pst1.setString(2,month);
            pst1.setString(3,origin);
            
            for (int row = 0; row < rows; row++) {
                String destination = (String) table.getValueAt(row, 0);
                                System.out.print("ROW");
                int cls_1 = Integer.parseInt(table.getValueAt(row, 1).toString());
                if(((StationDataTableNumericCellRenderer)table.getCellRenderer(row, 1)).hasCellAnomaly(row, 1)){
                    pst1.setString(4, destination);
                    pst1.setString(5, table.getColumnName(1));
                    pst1.setInt(6, cls_1);
                    pst1.setBoolean(7, false);
                    pst1.addBatch();
                }
                int cls_2 = Integer.parseInt(table.getValueAt(row, 2).toString());
                if(((StationDataTableNumericCellRenderer)table.getCellRenderer(row, 2)).hasCellAnomaly(row, 2)){
                    pst1.setString(4, destination);
                    pst1.setString(5, table.getColumnName(2));
                    pst1.setInt(6, cls_2);
                    pst1.setBoolean(7, false);
                    pst1.addBatch();
                }
                int cls_3a = Integer.parseInt(table.getValueAt(row, 3).toString());
                if(((StationDataTableNumericCellRenderer)table.getCellRenderer(row, 3)).hasCellAnomaly(row, 3)){
                    pst1.setString(4, destination);
                    pst1.setString(5, table.getColumnName(3));
                    pst1.setInt(6, cls_3a);
                    pst1.setBoolean(7, false);
                    pst1.addBatch();
                }
                int cls_3b = Integer.parseInt(table.getValueAt(row, 4).toString());
                if(((StationDataTableNumericCellRenderer)table.getCellRenderer(row, 4)).hasCellAnomaly(row, 4)){
                    pst1.setString(4, destination);
                    pst1.setString(5, table.getColumnName(4));
                    pst1.setInt(6, cls_3b);
                    pst1.setBoolean(7, false);
                    pst1.addBatch();
                    
                }
                int cls_3c = Integer.parseInt(table.getValueAt(row, 5).toString());
                if(((StationDataTableNumericCellRenderer)table.getCellRenderer(row, 5)).hasCellAnomaly(row, 5)){
                    pst1.setString(4, destination);
                    pst1.setString(5, table.getColumnName(5));
                    pst1.setInt(6, cls_3c);
                    pst1.setBoolean(7, false);
                    pst1.addBatch();
                }
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
            pst1.executeBatch();
            
            String aggragateSql = "INSERT INTO aggregate_counts (`year`, `month`, `origin`, `booked_tkt`, `returned_tkt`) VALUES (?, ?, ?, ?, ?)";
                    
            PreparedStatement aggregatePst = conn.prepareStatement(aggragateSql);
            
            aggregatePst.setInt(1, year);
            aggregatePst.setString(2,month);
            aggregatePst.setString(3,origin);
            aggregatePst.setInt(4,bookedTkts);
            aggregatePst.setInt(5,returnedTkts);
            aggregatePst.execute();

    }
    
    public ResultSet getStationTicketDataofMonth(Integer year,String month,String origin, String destination) throws SQLException, ClassNotFoundException{
        
            Connection con = DBConnector.getDBConnection();
            String sql1= "";
            PreparedStatement pst ;
            if(destination.equals("ALL")){
                sql1 = "Select `to`, 1stCls, 2ndCls,3rdClsA, 3rdClsB, 3rdClsC, total from travelled_tickets where `from`=? and year=? and month=?";
                pst = con.prepareStatement(sql1);
                pst.setString(1, origin);
            }else if(origin.equals("ALL")){
                sql1 = "Select `from`, 1stCls, 2ndCls,3rdClsA, 3rdClsB, 3rdClsC, total from travelled_tickets where `to`=? and year=? and month=?";
                pst = con.prepareStatement(sql1);
                pst.setString(1, destination);
            }else{
                sql1 = "Select `to`, 1stCls, 2ndCls,3rdClsA, 3rdClsB, 3rdClsC, total from travelled_tickets where `from`=? and year=? and month=? and `to`=?";
                pst = con.prepareStatement(sql1);
                pst.setString(1, origin);
                pst.setString(4, destination);
            }

            pst.setInt(2, year);
            pst.setString(3, month);
            ResultSet rs = pst.executeQuery();
            
            return rs;            
    }
    
    public ResultSet getAverageTicketDataOfStation(String origin) throws SQLException, ClassNotFoundException{
        
            Connection con = DBConnector.getDBConnection();
            String sql1 = "Select `to`, AVG(1stCls) AS 1stCls, AVG(2ndCls) As 2ndCls, AVG(3rdClsA) AS 3rdClsA, AVG(3rdClsB) AS 3rdClsB, AVG(3rdClsC) AS 3rdClsC from travelled_tickets where `from`=? GROUP BY `to`";
            PreparedStatement pst = con.prepareStatement(sql1);
            pst.setString(1, origin);
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
    
    public ResultSet getAggregateTrainlineDataofMonth(Integer year, String month) throws ClassNotFoundException, SQLException{
            Connection con = DBConnector.getDBConnection();
            String sql = "SELECT stations.trainline AS trainline, SUM(aggregate_counts.booked_tkt) AS booked, SUM(aggregate_counts.returned_tkt) AS returned FROM stations INNER JOIN aggregate_counts ON stations.stname=aggregate_counts.origin where year=? and month=? GROUP BY stations.trainline";
            String sql1 = "SELECT stations.trainline AS trainline, SUM(travelled_tickets.total) AS total FROM stations INNER JOIN travelled_tickets ON stations.stname=travelled_tickets.from where year=? and month=? GROUP BY stations.trainline";
            String sql2 = "SELECT total_data.trainline, total_data.total AS total, agg_data.booked, agg_data.returned FROM ("+sql+") AS agg_data INNER JOIN ("+sql1+") AS total_data ON agg_data.trainline=total_data.trainline";
            String sql3 = "SELECT trainlines.name AS name, overall.total AS total, overall.booked AS booked, overall.returned AS returned FROM trainlines INNER JOIN ("+sql2+") AS overall ON trainlines.lineId=overall.trainline";
            PreparedStatement pst = con.prepareStatement(sql3);
            pst.setInt(1, year);
            pst.setString(2, month);
            pst.setInt(3, year);
            pst.setString(4, month);
            ResultSet rs = pst.executeQuery();
            
            return rs;
    
    }
    
    public ResultSet getAnnualAggregateData() throws ClassNotFoundException, SQLException{
            Connection con = DBConnector.getDBConnection();
            String sql = "SELECT year , SUM(booked_tkt) AS booked, SUM(returned_tkt) AS returned FROM aggregate_counts GROUP BY year";
            String sql1 = "SELECT year , SUM(total) AS total FROM travelled_tickets GROUP BY year";
            String sql2 = "SELECT agg_data.year AS year, agg_data.booked AS booked, agg_data.returned AS returned, total_data.total AS total FROM ("+sql+") AS agg_data INNER JOIN ("+sql1+") AS total_data ON agg_data.year=total_data.year";
            PreparedStatement pst = con.prepareStatement(sql2);
            ResultSet rs = pst.executeQuery();
            
            return rs;
    
    }
    
    public ResultSet getAnnualAggregateDataofStation(String station) throws ClassNotFoundException, SQLException{
            Connection con = DBConnector.getDBConnection();
            String sql = "SELECT year , SUM(booked_tkt) AS booked, SUM(returned_tkt) AS returned FROM aggregate_counts WHERE `origin`=? GROUP BY year";
            String sql1 = "SELECT year , SUM(total) AS total FROM travelled_tickets WHERE `from`=? GROUP BY year";
            String sql2 = "SELECT agg_data.year AS year, agg_data.booked AS booked, agg_data.returned AS returned, total_data.total AS total FROM ("+sql+") AS agg_data INNER JOIN ("+sql1+") AS total_data ON agg_data.year=total_data.year";
            PreparedStatement pst = con.prepareStatement(sql2);
            pst.setString(1, station);
            pst.setString(2, station);
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
    
    public ResultSet getUnsupervisedAnomalies() throws ClassNotFoundException, SQLException{
        Connection con = DBConnector.getDBConnection();
        String sql = "SELECT * from anomalies where supervised=false";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        return rs;
        
    }
    
    public ResultSet getUnsupervisedAnomaly(String from, String to, int year, String month, String ticketClass, int value) throws ClassNotFoundException, SQLException{
        Connection con = DBConnector.getDBConnection();
        String sql = "SELECT * from anomalies WHERE `from`=? and `to`=? and year=? and month=? and ticketType=? and value=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, from);
        pst.setString(2, to);
        pst.setInt(3, year);
        pst.setString(4,month);
        pst.setString(5, ticketClass);
        pst.setInt(6, value);
        ResultSet rs = pst.executeQuery();
        return rs;
        
    }
    
    public void updateAnomaly(String from, String to, int year, String month, String ticketClass, int value) throws ClassNotFoundException, SQLException{
        Connection con = DBConnector.getDBConnection();
        con.setAutoCommit(false);
        String sql = "Update anomalies set value=? , supervised = true WHERE `from`=? and `to`=? and year=? and month=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, value);
        pst.setString(2, from);
        pst.setString(3, to);
        pst.setInt(4, year);
        pst.setString(5,month);
        pst.execute();  
        String sql1 = "Update travelled_tickets set "+ticketClass+"=? WHERE `from`=? and `to`=? and year=? and month=?";
        PreparedStatement pst1 = con.prepareStatement(sql1);
        pst1.setInt(1, value);
        pst1.setString(2, from);
        pst1.setString(3, to);
        pst1.setInt(4, year);
        pst1.setString(5,month);
        pst1.execute();  
        con.commit();
        
    }
    
}
