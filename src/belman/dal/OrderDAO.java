/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.dal;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import belman.bll.DBConnectionProvider;
import belman.be.DepartmentOrder;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Qash
 */
public class OrderDAO {
    
    private DBConnectionProvider db = new DBConnectionProvider();
    
    public List<DepartmentOrder> getAllDepartmentOrders(String departmentName) throws SQLException {
        List<DepartmentOrder> listOrders = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "SELECT [ProdOrder].OrderNumber, [DepTask].TaskStatus, [ProdOrder].CurrentDepartment, [DepTask].StartDate, [DepTask].EndDate, [DepTask].LastDepartment, [DepTask].ProductionID " + 
                    "FROM ProdOrder JOIN DepTask " +
                    "ON [ProdOrder].id = [DepTask].DepartmentID " +
                    "WHERE [DepTask]. DepartmentName = ? " +
                    "AND [DepTask].StartDate <= (convert(date, getdate())) " +
                    "AND [DepTask].TaskStatus = 0"
                    );
            stmt.setString(1, departmentName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DepartmentOrder order = new DepartmentOrder();
                order.setOrderNumber(rs.getString("OrderNumber"));
                order.setDepartmentStart(rs.getDate("StartDate"));
                order.setDepartmentEnd(rs.getDate("EndDate"));
                order.setCurrentDepartment(rs.getString("CurrentDepartment"));
                order.setLastDepartment(rs.getString("LastDepartment"));
                order.setProductionId(rs.getInt("ProductionID"));
                if("Halvfab".equals(departmentName)) {
                    order.setStatus(true);
                }
                else {
                    order.setStatus(getStatus(rs.getInt("ProductionID"), rs.getDate("StartDate")));
                }
                listOrders.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrders;
    }
    
    public String getDepartmentName(int productionID, Date endDate) throws SQLException {
        String departmentName = "fejl";
        try (Connection con = db.getConnection())
        {
            PreparedStatement ppst1 = con.prepareStatement("SELECT DepartmentName FROM DepTask WHERE ProductionID = ? AND EndDate = ?");
            
            ppst1.setInt(1, productionID);
            ppst1.setDate(2, endDate);
            
            ResultSet rs = ppst1.executeQuery();
            while(rs.next()) {
                departmentName = rs.getString("DepartmentName");
            }
            
        }
        
        return departmentName;
    }
    
    public boolean getIsReady(int orderNumber, int lastDepartment) throws SQLServerException, SQLException{
        boolean ready = false;
        if(lastDepartment==0) {
            ready = true;
        }
        else {
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "SELECT [Relations].Status FROM [Relations] WHERE OrderNumber = ? AND DepartmentId = ?;"
                    );
            stmt.setInt(1, orderNumber);
            stmt.setInt(2, lastDepartment);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ready = rs.getBoolean("Status");
            }
        }
        }
        
        return ready;
    }
    
    public void markAsDone(int productionID, String departmentName) {
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "UPDATE [DepTask] SET TaskStatus = 1 WHERE ProductionID = ? AND DepartmentName = ?;"
                    );
            stmt.setInt(1, productionID);
            stmt.setString(2, departmentName);
            stmt.executeUpdate();
            
        } catch (SQLServerException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean getStatus(int id, java.sql.Date startDate) throws SQLException {
        
        boolean status = false;
        try (Connection con = db.getConnection())
        {
            PreparedStatement ppst1 = con.prepareStatement("SELECT TaskStatus FROM DepTask WHERE ProductionID = ? AND EndDate = ?");
            
            ppst1.setInt(1, id);
            ppst1.setDate(2, startDate);
            
            ResultSet rs = ppst1.executeQuery();
            while(rs.next()) {
                status = rs.getBoolean("TaskStatus");
            }
            
        }
        
        return status;
    }
    
    public void setCurrentDepartment(int productionID, String departmentName, Date endDate) {
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "UPDATE [ProdOrder] SET CurrentDepartment = ? WHERE ProductionID = ? AND DepartmentName = ?;"
                    );
            stmt.setString(1, getDepartmentName(productionID, endDate));
            stmt.setInt(2, productionID);
            stmt.setString(3, departmentName);
            stmt.executeUpdate();
            
        } catch (SQLServerException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
