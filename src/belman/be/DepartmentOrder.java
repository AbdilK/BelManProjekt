/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.be;

import java.sql.Date;

/**
 *
 * @author kedde
 */
public class DepartmentOrder {
    
    private String orderNumber;
    private String currentDepartment;
    private Date departmentStart;
    private Date departmentEnd;
    private String lastDepartment;
    private boolean status;
    private int productionId;
    
    /**
     * Retunere orderNumber
     * @return
     */
    public String getOrderNumber() {
        return orderNumber;
    }
    
    /**
     * Sætter orderNumber
     * @param orderNumber
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    /**
     * Retunere CurrentDepartment
     * @return
     */
    public String getCurrentDepartment() {
        return currentDepartment;
    }
    
    /**
     * Sætter currentDepartment
     * @param currentDepartment
     */
    public void setCurrentDepartment(String currentDepartment) {
        this.currentDepartment = currentDepartment;
    }
    
    /**
     * Retunere departmentStart
     * @return
     */
    public Date getDepartmentStart() {
        return departmentStart;
    }
    
    /**
     * Sætter departmentStart
     * @param departmentStart
     */
    public void setDepartmentStart(Date departmentStart) {
        this.departmentStart = departmentStart;
    }
    
    /**
     * Retunere departmentEnd
     * @return
     */
    public Date getDepartmentEnd() {
        return departmentEnd;
    }
    
    /**
     * Sætter departmentEnd
     * @param departmentEnd
     */
    public void setDepartmentEnd(Date departmentEnd) {
        this.departmentEnd = departmentEnd;
    }
    
    /**
     * Retunere lastDepartment
     * @return
     */
    public String getLastDepartment() {
        return lastDepartment;
    }
    
    /**
     * Sætter lastDepartment
     * @param lastDepartment
     */
    public void setLastDepartment(String lastDepartment) {
        this.lastDepartment = lastDepartment;
    }
    
    /**
     * Retunere status
     * @return
     */
    public boolean isStatus() {
        return status;
    }
    
    /**
     * Sætter status
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /**
     * Retunere productionsId
     * @return
     */
    public int getProductionId() {
        return productionId;
    }
    
    /**
     * Sætter productionsId
     * @param productionId
     */
    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }
}
