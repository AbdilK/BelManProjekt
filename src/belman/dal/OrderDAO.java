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
public class OrderDAO implements IOrderDAO {
    
    private DBConnectionProvider db = new DBConnectionProvider();
    
    /**
     *
     * @param departmentName
     * @return
     * @throws SQLException
     */
    public List<DepartmentOrder> getAllDepartmentOrders(String departmentName) throws SQLException {
        List<DepartmentOrder> listOrders = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "SELECT [ProdOrder].OrderNumber, [DepTask].TaskStatus, [ProdOrder].CurrentDepartment, [DepTask].StartDate, [DepTask].EndDate, [DepTask].LastDepartment, [DepTask].ProductionID " + 
                    "FROM ProdOrder JOIN DepTask " +
                    "ON [ProdOrder].id = [DepTask].ProductionID " +
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
    
    /**
     *
     * @param productionID
     * @param endDate
     * @return
     * @throws SQLException
     */
    @Override
    public String getNextDepartmentName(int productionID, Date endDate) throws SQLException {
        String departmentName = "fejl";
        try (Connection con = db.getConnection())
        {
            PreparedStatement ppst1 = con.prepareStatement("SELECT DepartmentName FROM DepTask WHERE ProductionID = ? AND StartDate = CONVERT(Date, ? )");
            
            ppst1.setInt(1, productionID);
            ppst1.setDate(2, endDate);
            
            ResultSet rs = ppst1.executeQuery();
            while(rs.next()) {
                departmentName = rs.getString("DepartmentName");
            }
            
        }
        
        return departmentName;
    }
    
    /**
     *
     * @param orderNumber
     * @param lastDepartment
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    @Override
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
    
    /**
     *
     * @param productionID
     * @param departmentName
     */
    @Override
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
    
    /**
     *
     * @param id
     * @param startDate
     * @return
     * @throws SQLException
     */
    @Override
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
    
    /**
     *
     * @param productionID
     * @param departmentName
     * @param endDate
     */
    public void setCurrentDepartment(int productionID, Date endDate) {
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "UPDATE [ProdOrder] SET CurrentDepartment = ? WHERE id = ?;"
                    );
            stmt.setString(1, getNextDepartmentName(productionID, endDate));
            stmt.setInt(2, productionID);
            stmt.executeUpdate();
            
        } catch (SQLServerException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
