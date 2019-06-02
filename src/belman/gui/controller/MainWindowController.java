/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Qash
 */
public class MainWindowController implements Initializable {

    @FXML
    private Button PrevBtn;
    @FXML
    private Button NextBtn;
    @FXML
    private GridPane gridPaneOneToSix;
    @FXML
    private Button btnOne;
    @FXML
    private Button btnThree;
    @FXML
    private Button btnFive;
    @FXML
    private Button btnTwo;
    @FXML
    private Button btnFour;
    @FXML
    private Button btnSix;
    @FXML
    private Button btnSeven;
    @FXML
    private AnchorPane MainWindowPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * Går til DepartmentWindow for Halvfab
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
   @FXML
    private void openHalfab(ActionEvent event) throws IOException, SQLException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belman/gui/view/DepartmentWindow.fxml"));
        Parent root = fxmlLoader.load();
        belman.gui.controller.DepartmentWindowController nextController = fxmlLoader.getController();
        nextController.setLblAfdeling("Halvfab");
        nextController.setDepartmentId("Halvfab");
        nextController.getOrders();
        MainWindowPane.getChildren().setAll(root);
        // den vil ikke dur, dog vil de andre
    }

    /**
     * Går til DepartmentWindow for Montage 1
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
    @FXML
    private void openMontage1(ActionEvent event) throws IOException, SQLException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belman/gui/view/DepartmentWindow.fxml"));
        Parent root = fxmlLoader.load();
        belman.gui.controller.DepartmentWindowController nextController = fxmlLoader.getController();
        nextController.setLblAfdeling("Montage 1");
        nextController.setDepartmentId("Montage 1");
        nextController.getOrders();
        MainWindowPane.getChildren().setAll(root);
    }

    /**
     * Går til DepartmentWindow for Bertel
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
    @FXML
    private void openBertel(ActionEvent event) throws IOException, SQLException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belman/gui/view/DepartmentWindow.fxml"));
        Parent root = fxmlLoader.load();
        belman.gui.controller.DepartmentWindowController nextController = fxmlLoader.getController();
        nextController.setLblAfdeling("Bertel");
        nextController.setDepartmentId("Bertel");
        nextController.getOrders();
        MainWindowPane.getChildren().setAll(root);
    }

    /**
     * Går til DepartmentWindow for Bælg
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
    @FXML
    private void openBælg(ActionEvent event) throws IOException, SQLException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belman/gui/view/DepartmentWindow.fxml"));
        Parent root = fxmlLoader.load();
        belman.gui.controller.DepartmentWindowController nextController = fxmlLoader.getController();
        nextController.setLblAfdeling("Bælg");
        nextController.setDepartmentId("BÃ¦lg");
        nextController.getOrders();
        MainWindowPane.getChildren().setAll(root);
    }

    /**
     * Går til DepartmentWindow for Montage 2
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
    @FXML
    private void openMontage2(ActionEvent event)  throws IOException, SQLException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belman/gui/view/DepartmentWindow.fxml"));
        Parent root = fxmlLoader.load();
        belman.gui.controller.DepartmentWindowController nextController = fxmlLoader.getController();
        nextController.setLblAfdeling("Montage 2");
        nextController.setDepartmentId("Montage 2");
        nextController.getOrders();
        MainWindowPane.getChildren().setAll(root);
    }

    /**
     * Går til DepartmentWindow for Maler
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
    @FXML
    private void openMaler(ActionEvent event) throws IOException, SQLException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belman/gui/view/DepartmentWindow.fxml"));
        Parent root = fxmlLoader.load();
        belman.gui.controller.DepartmentWindowController nextController = fxmlLoader.getController();
        nextController.setLblAfdeling("Maler");
        nextController.setDepartmentId("Maler");
        nextController.getOrders();
        MainWindowPane.getChildren().setAll(root);
        
    }

    /**
     * Går til DepartmentWindow for Forsendelse
     * @param event
     * @throws IOException
     * @throws SQLException 
     */
    @FXML
    private void openForsendelse(ActionEvent event) throws IOException, SQLException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/belman/gui/view/DepartmentWindow.fxml"));
        Parent root = fxmlLoader.load();
        belman.gui.controller.DepartmentWindowController nextController = fxmlLoader.getController();
        nextController.setLblAfdeling("Forsendelse");
        nextController.setDepartmentId("Forsendelse");
        nextController.getOrders();
        MainWindowPane.getChildren().setAll(root);
    }

    
    
}
