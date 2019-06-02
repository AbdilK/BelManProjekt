/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.gui.controller;

import belman.BelMan;
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
import java.sql.SQLException;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import belman.bll.BLLManager;
import belman.be.DepartmentOrder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.BorderPane;



/**
 * FXML Controller class
 *
 * @author Qash
 */
public class DepartmentWindowController implements Initializable {
    
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private DepartmentOrder selectedDepartmentOrder;
    belman.be.DepartmentOrder DepartmentOrder = new  belman.be.DepartmentOrder();
    @FXML
    private Button PrevBtn;
    @FXML
    private Button NextBtn;
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
    private Button markAsDoneButton;
    @FXML
    private BorderPane departmentPane;

    /**
     * Initializes the controller class.
     * Initialisere tableviewet
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

    /**
     * Går til MainVindow
     * (Virker ikke)
     * @param event
     * @throws IOException 
     */
    @FXML
    private void PreviousPage(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/belman/gui/view/MainWindow.fxml"));
        bertelPane.getChildren().setAll(pane);
    }
    
    /**
     * For et lable til at fremvise navnet på afdelingen
     * @param department 
     */
    public void setLblAfdeling(String department){
        lblAfdeling.setText("Afdeling: " + department);
    }
    
    /**
     * Sætter departmentens naven
     * @param name 
     */
    public void setDepartmentId(String name){
        departmentName=name;
    }
    
    /**
     * Laver et timstamp for det nuværende tidspunkt
     * @return 
     */
    public Timestamp getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }
    
    /**
     * Henter alle aktuelle ordre for afdelingen fra databasen
     * Starter en tråd der gentages hver femte sekund
     * så ordrene altid er op to date
     * @throws SQLException 
     */
    public void getOrders() throws SQLException {
        
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
        Runnable task = () ->
        {
            try
            {
                model.loadOrders(departmentName);
                System.out.println(getTimestamp() + " Ordrene er opdateret");
            } catch (SQLException ex)
            {
                Logger.getLogger(BelMan.class.getName()).log(Level.SEVERE, null, ex);
            }
        };

        exe.scheduleWithFixedDelay(task, 0, 5, TimeUnit.SECONDS);
        tbvOrders.setItems(model.getOrders());
    }
    
    /**
     * Sætter progressbaren til efter hvor langt opgaven er dato vis
     */
    public void setProgressBar()
    {
        Date startDate = new Date(selectedDepartmentOrder.getDepartmentStart().getTime());
        Date endDate = new Date(selectedDepartmentOrder.getDepartmentEnd().getTime());
        Date now = new Date();
        long totalTime = Math.abs(endDate.getTime() - startDate.getTime());
        long timeRemaining = Math.abs(now.getTime() - startDate.getTime());
        final double percentage = (timeRemaining / ((double) totalTime)) * 100;
        progressBar.setProgress(percentage / 100);
            
    }
    
    /**
     * Markere odren som færdig og den sendes vidre til den næste afdeling
     * @param event 
     */
    @FXML
    private void orderDone(ActionEvent event) 
    {
        
        int svar = JOptionPane.showConfirmDialog(null, "Er du sikker på, at du vil udføre denne handling?", "Sikkerhedsbox", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        switch (svar) {
            case JOptionPane.YES_OPTION:
                bll.markAsDone(selectedDepartmentOrder.getProductionId(), departmentName);
                bll.setCurrentDepartment(selectedDepartmentOrder.getProductionId(), selectedDepartmentOrder.getDepartmentEnd());
                System.out.println("Ordrenummeret: " + selectedDepartmentOrder.getOrderNumber() + " er markeret som færdig og er sendt videre til næste afdeling");
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("Handlingen er blevet annulleret");
                break;
            case JOptionPane.CLOSED_OPTION:
                System.out.println("Handlingen er blevet lukket");
                break;
            default:
                break; 
        }
    }
    
    /**
     * Når man clicker på en ordre bilver den gemt som en variable
     * Variablen sætter labels til info fra ordren
     * Progressbaren hvis færdig procent på opgaven
     * @param click
     * @throws ParseException 
     */
        @FXML public void clickOrder(MouseEvent click) throws ParseException
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
                LocalDate localDate1 = utilDate1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                lblStartDate.setText(String.valueOf(localDate1));
                java.util.Date utilDate2 = new java.util.Date(selectedDepartmentOrder.getDepartmentEnd().getTime());
                LocalDate localDate2 = utilDate2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                lblEndDate.setText(String.valueOf(localDate2));
                
                
                setProgressBar();
                
            }
         }

    @FXML
    private void NextPage(ActionEvent event) {
    }
}