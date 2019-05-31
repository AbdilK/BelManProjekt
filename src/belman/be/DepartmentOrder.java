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
     *
     * @return
     */
    public String getOrderNumber() {
        return orderNumber;
    }
    
    /**
     *
     * @param orderNumber
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    /**
     *
     * @return
     */
    public String getCurrentDepartment() {
        return currentDepartment;
    }
    
    /**
     *
     * @param currentDepartment
     */
    public void setCurrentDepartment(String currentDepartment) {
        this.currentDepartment = currentDepartment;
    }
    
    /**
     *
     * @return
     */
    public Date getDepartmentStart() {
        return departmentStart;
    }
    
    /**
     *
     * @param departmentStart
     */
    public void setDepartmentStart(Date departmentStart) {
        this.departmentStart = departmentStart;
    }
    
    /**
     *
     * @return
     */
    public Date getDepartmentEnd() {
        return departmentEnd;
    }
    
    /**
     *
     * @param departmentEnd
     */
    public void setDepartmentEnd(Date departmentEnd) {
        this.departmentEnd = departmentEnd;
    }
    
    /**
     *
     * @return
     */
    public String getLastDepartment() {
        return lastDepartment;
    }
    
    /**
     *
     * @param lastDepartment
     */
    public void setLastDepartment(String lastDepartment) {
        this.lastDepartment = lastDepartment;
    }
    
    /**
     *
     * @return
     */
    public boolean isStatus() {
        return status;
    }
    
    /**
     *
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /**
     *
     * @return
     */
    public int getProductionId() {
        return productionId;
    }
    
    /**
     *
     * @param productionId
     */
    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }
}
