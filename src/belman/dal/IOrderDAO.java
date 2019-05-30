/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.dal;

import belman.be.DepartmentOrder;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Qash
 */
public interface IOrderDAO {
    
    public List<DepartmentOrder> getAllDepartmentOrders(String departmentName) throws SQLException;
    
    public String getDepartmentName(int productionID, Date endDate) throws SQLException;
    
    public boolean getIsReady(int orderNumber, int lastDepartment) throws SQLServerException, SQLException;
    
    public void markAsDone(int productionID, String departmentName);
    
    public boolean getStatus(int id, java.sql.Date startDate) throws SQLException;
       
    
}
