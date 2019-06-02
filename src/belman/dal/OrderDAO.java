/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
     * Henter en liste af aktuelle ordre for en afdeling
     * @param departmentName
     * @return
     * @throws SQLException
     */
    @Override
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
     * Retunere navnet på afdelingen efter den nuværende
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
     * Opdatere ordren til at være færdig i nuværende afdeling
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
     * Checker om en ordre er klar til at blive arbejdet på
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
     * Opdatere CurrentDepartmen for en ordre
     * @param productionID
     * @param endDate
     */
    @Override
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
