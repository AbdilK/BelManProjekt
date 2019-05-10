/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.gui.controller;

import belman.bll.BLLManager;
import belman.be.DepartmentOrder;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author kedde
 */
public class DepartmentOrderModel {
    private ObservableList<DepartmentOrder> orders = FXCollections.observableArrayList();
    private BLLManager bllManager = new BLLManager();
    
    public ObservableList<DepartmentOrder> getOrders() {
        return orders;
    }
    
    public void loadOrders(int departmentId) throws SQLException
    {
        List<DepartmentOrder> loadedOrders = bllManager.getAllDepartmentOrders(departmentId);
        
        orders.clear();
        orders.addAll(loadedOrders);
    }
}
