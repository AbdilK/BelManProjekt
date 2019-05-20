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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Qash
 */
public class OrderDAO {
    
    private DBConnectionProvider db = new DBConnectionProvider();
    
    public List<DepartmentOrder> getAllDepartmentOrders(int departmentId) throws SQLException {
        List<DepartmentOrder> listOrders = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "select [Order].OrderNumber, [Order].CurrentDepartment, [Relations].DepartmentStart, " + 
                    "[Relations].DepartmentEnd, [Relations].LastDepartment from [Order] join [Relations] " +
                    "on [Order].OrderNumber=[Relations].OrderNumber where [Relations].DepartmentId=? " +
                    "and [Relations].DepartmentStart <= (convert(date, getdate()));"
                    );
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DepartmentOrder order = new DepartmentOrder();
                order.setOrderNumber(rs.getInt("OrderNumber"));
                order.setDepartmentStart(rs.getDate("DepartmentStart"));
                order.setDepartmentEnd(rs.getDate("DepartmentEnd"));
                order.setCurrentDepartment(getDepartmentName(rs.getInt("CurrentDepartment")));
                order.setStatus(getIsReady(rs.getInt("OrderNumber"), rs.getInt("LastDepartment")));
                int lastId = rs.getInt("LastDepartment");
                if(lastId > 0) {
                    order.setLastDepartment(getDepartmentName(lastId));
                }
                else {
                    order.setLastDepartment("ingen");
                }
                listOrders.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrders;
    }
    
    public String getDepartmentName(int departmentId) throws SQLException {
        String departmentName = "fejl";
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "select * from [Department] where DepartmentID = ?"
                    );
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
    
    public void markAsDone(int orderNumber, int departmentId) {
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "UPDATE [Relations] SET Status = 1 WHERE OrderNumber = ? AND DepartmentId = ?;"
                    );
            stmt.setInt(1, orderNumber);
            stmt.setInt(2, departmentId);
            stmt.executeUpdate();
            
        } catch (SQLServerException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
