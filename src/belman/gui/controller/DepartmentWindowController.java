/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.gui.controller;

import belman.gui.model.DepartmentOrderModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import belman.be.DepartmentOrder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import belman.bll.BLLManager;
import belman.be.DepartmentOrder;


/**
 * FXML Controller class
 *
 * @author Qash
 */
public class DepartmentWindowController implements Initializable {
    
    private DepartmentOrder selectedDepartmentOrder;
    belman.be.DepartmentOrder DepartmentOrder = new  belman.be.DepartmentOrder();
    @FXML
    private Button PrevBtn;
    @FXML
    private Button NextBtn;
    @FXML
    private AnchorPane bertelPane;
    @FXML
    private Label lblAfdeling;
    private String departmentName;
    private DepartmentOrderModel model = new DepartmentOrderModel();
    private BLLManager bll = new BLLManager();
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TableView<DepartmentOrder> tbvOrders;
    @FXML
    private TableColumn<DepartmentOrder, String> tbcOrderNumber;
    @FXML
    private TableColumn<DepartmentOrder, Boolean> tbcStatus;
    @FXML
    private TableColumn<DepartmentOrder, String> tbcAfdeling;
    @FXML
    private TableColumn<DepartmentOrder, String> tbcStartDato;
    @FXML
    private TableColumn<DepartmentOrder, String> tbcSlutDato;
    @FXML
    private Label orderIdLabel;
    @FXML
    private Label currentDepartmentLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;
    @FXML
    private Label shippingDateLabel;
    @FXML
    private Label progressLabel;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblCurrentDep;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblStartDate;
    @FXML
    private Label lblEndDate;
    @FXML
    private Label lblShippingDate;
    @FXML
    private Button markAsDoneButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbcOrderNumber.setCellValueFactory(new PropertyValueFactory("orderNumber"));
        tbcStatus.setCellValueFactory(new PropertyValueFactory("status"));
        tbcAfdeling.setCellValueFactory(new PropertyValueFactory("currentDepartment"));
        tbcStartDato.setCellValueFactory(new PropertyValueFactory("departmentStart"));
        tbcSlutDato.setCellValueFactory(new PropertyValueFactory("departmentEnd"));
        tbcStatus.setCellFactory(tc -> new TableCell<DepartmentOrder, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null
                        : item.booleanValue() ? "Ready" : "Delayed");
            }
        });
        
        progressBar.setProgress(0.0);
    }    

    @FXML
    private void PreviousPage(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/belman/gui/view/MainWindow.fxml"));
        bertelPane.getChildren().setAll(pane);
    }

    @FXML
    private void NextPage(ActionEvent event) {
    }
    
    public void setLblAfdeling(String department){
        lblAfdeling.setText("Afdeling: " + department);
    }
    
    public void setDepartmentId(String name){
        departmentName=name;
    }
    
    public void getOrders() throws SQLException {
        model.loadOrders(departmentName);
        tbvOrders.setItems(model.getOrders());
    }
    class bg_Thread implements Runnable
    {
        @Override
        public void run()
        {
            for(int i =0 ; i< 100 ; i++)
            {
             progressBar.setProgress(i / 100.0);
                try 
                {
                    Thread.sleep(100);
                } 
                    catch (InterruptedException ex) 
                    {
                        Logger.getLogger(DepartmentWindowController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
        }
    }  
    
    @FXML
    private void orderDone(ActionEvent event) 
    {
        bll.markAsDone(selectedDepartmentOrder.getProductionId(), departmentName);
        System.out.println("Order number: " + selectedDepartmentOrder.getOrderNumber() + " has been marked as done");
    }
    
        @FXML public void clickOrder(MouseEvent click)
            //is used to quickly reload whenever needed. (Happens on Playlists)
        {
            {
                               
                selectedDepartmentOrder = tbvOrders.getSelectionModel().getSelectedItem();
                lblOrderId.setText(selectedDepartmentOrder.getOrderNumber());
                //lblStatus.setText(Boolean.toString(selectedDepartmentOrder.isStatus()));
                
                if (selectedDepartmentOrder.isStatus() == true)
                {
                lblStatus.setText("Ready");
                }
                else {
                
                lblStatus.setText("Delayed");        
                
                }
                lblCurrentDep.setText(selectedDepartmentOrder.getCurrentDepartment());
                java.util.Date utilDate1 = new java.util.Date(selectedDepartmentOrder.getDepartmentStart().getTime());
                lblStartDate.setText(String.valueOf(utilDate1));
                java.util.Date utilDate2 = new java.util.Date(selectedDepartmentOrder.getDepartmentEnd().getTime());
                lblEndDate.setText(String.valueOf(utilDate2));
                java.util.Date utilDate3 = new java.util.Date(selectedDepartmentOrder.getDepartmentEnd().getTime());
                lblEndDate.setText(String.valueOf(utilDate3));
                
                
            }
         }
}