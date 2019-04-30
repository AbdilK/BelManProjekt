/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author Kristian Bertelsen
 */
public class ProgressWindowController implements Initializable {

    @FXML
    private ProgressBar progbarHours;
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
    private Button PrevBtn;
    @FXML
    private Button NextBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
