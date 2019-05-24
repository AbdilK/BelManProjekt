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
    
    public String getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public String getCurrentDepartment() {
        return currentDepartment;
    }
    
    public void setCurrentDepartment(String currentDepartment) {
        this.currentDepartment = currentDepartment;
    }
    
    public Date getDepartmentStart() {
        return departmentStart;
    }
    
    public void setDepartmentStart(Date departmentStart) {
        this.departmentStart = departmentStart;
    }
    
    public Date getDepartmentEnd() {
        return departmentEnd;
    }
    
    public void setDepartmentEnd(Date departmentEnd) {
        this.departmentEnd = departmentEnd;
    }
    
    public String getLastDepartment() {
        return lastDepartment;
    }
    
    public void setLastDepartment(String lastDepartment) {
        this.lastDepartment = lastDepartment;
    }
    
    public boolean isStatus() {
        return status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public int getProductionId() {
        return productionId;
    }
    
    public void setProductionId(int productionId) {
        this.productionId = productionId;
    }
}
