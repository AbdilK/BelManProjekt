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

/**
 *
 * @author Qash
 */
public class OrderDAO {
    
    private DBConnectionProvider db;
    
    public List<DepartmentOrder> getAllDepartmentOrders(int departmentId) throws SQLException {
        List<DepartmentOrder> listOrders = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "select [Order].OrderNumber, [Order].CurrentDepartment, [Relations].DepartmentStart," + 
                    " [Relations].DepartmentEnd, [Relations].LastDepartment from [Order] join [Relations]" +
                    "on [Order].OrderNumber=[Relations].OrderNumber where [Relations].DepartmentId=?" +
                    "and [Relations].DepartmentStart <= (convert(date, getdate()));"
                    );
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DepartmentOrder order = new DepartmentOrder();
                order.setOrderNumber(rs.getInt("OrderNumber"));
                order.setDepartmentStart(rs.getDate("DepartmentStart"));
                order.setDepartmentEnd(rs.getDate("DepartmentEnd"));
                order.setStatus(true);
                order.setLastDepartment(getDepartmentName(rs.getInt("LastDepartment")));
                listOrders.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrders;
    }
    
    public String getDepartmentName(int departmentId) throws SQLException {
        String departmentName = null;
        try (Connection con = db.getConnection()) {
            PreparedStatement stmt;
            stmt = con.prepareStatement(
                    "select from Department where DepartmentID = ?"
                    );
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();
            departmentName = rs.getString("DepartmentName");
        }
        
        return departmentName;
    }
}
