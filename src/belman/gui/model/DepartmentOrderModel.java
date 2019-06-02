/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.gui.model;

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
    
    /**
     * Retunere en liste af ordre
     * @return
     */
    public ObservableList<DepartmentOrder> getOrders() {
        return orders;
    }
    
    /**
     * Henter alle aktuelle ordre for en afdeling
     * Sætter de hentede ordre på en liste
     * @param departmentName
     * @throws SQLException
     */
    public void loadOrders(String departmentName) throws SQLException
    {
        List<DepartmentOrder> loadedOrders = bllManager.getAllDepartmentOrders(departmentName);
        
        orders.clear();
        orders.addAll(loadedOrders);
    }
}
