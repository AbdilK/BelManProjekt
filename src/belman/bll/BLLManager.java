/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.bll;

import belman.be.DepartmentOrder;
import belman.dal.OrderDAO;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Abdil-K
 */
public class BLLManager
{
    private OrderDAO dao = new OrderDAO();
    
    public List<DepartmentOrder> getAllDepartmentOrders(String departmentName) throws SQLException {
        return dao.getAllDepartmentOrders(departmentName);
    }
    
    public void markAsDone(int productionID, String departmentName) {
        dao.markAsDone(productionID, departmentName);
    }
}
