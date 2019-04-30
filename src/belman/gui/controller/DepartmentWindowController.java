/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman.gui.controller;

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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Qash
 */
public class DepartmentWindowController implements Initializable {

    @FXML
    private Button PrevBtn;
    @FXML
    private Button NextBtn;
    @FXML
    private AnchorPane bertelPane;
    @FXML
    private Label lblAfdeling;
    private int departmentId;
    @FXML
    private ProgressBar progressBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    public void setDepartmentId(int id){
        departmentId=id;
    }
    
}
