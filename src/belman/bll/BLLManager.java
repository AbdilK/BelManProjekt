/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.bll;

import belman.be.DepartmentOrder;
import belman.dal.OrderDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Abdil-K
 */
public class BLLManager
{
    private OrderDAO dao = new OrderDAO();
    
    /**
     * Sender en liste fra databasen vidre til gui controlleren
     * @param departmentName
     * @return
     * @throws SQLException 
     */
    public List<DepartmentOrder> getAllDepartmentOrders(String departmentName) throws SQLException {
        return dao.getAllDepartmentOrders(departmentName);
    }
    
    /**
     * Sender en opdate fra gui controlleren vidre til daoen
     * @param productionID
     * @param departmentName 
     */
    public void markAsDone(int productionID, String departmentName) {
        dao.markAsDone(productionID, departmentName);
    }
    
    /**
     * Sender en opdate fra gui controlleren vidre til daoen
     * @param productionID
     * @param endDate 
     */
    public void setCurrentDepartment(int productionID, Date endDate) {
        dao.setCurrentDepartment(productionID, endDate);
    }
}
