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

   @FXML
    private void openHalfab(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/belman/gui/view/HalfabWindow.fxml"));
        MainWindowPane.getChildren().setAll(pane);
        // den vil ikke dur, dog vil de andre
    }

    @FXML
    private void openMontage1(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/belman/gui/view/Montage1Window.fxml"));
        MainWindowPane.getChildren().setAll(pane);  
    }

    @FXML
    private void openBertel(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/belman/gui/view/BertelWindow.fxml"));
        MainWindowPane.getChildren().setAll(pane);
    }

    @FXML
    private void openBælg(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/belman/gui/view/BælgWindow.fxml"));
        MainWindowPane.getChildren().setAll(pane);
    }

    @FXML
    private void openMontage2(ActionEvent event)  throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/belman/gui/view/Montage2Window.fxml"));
        MainWindowPane.getChildren().setAll(pane);
    }

    @FXML
    private void openMaler(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/belman/gui/view/MalerWindow.fxml"));
        MainWindowPane.getChildren().setAll(pane);
    }

    @FXML
    private void openForsendelse(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/belman/gui/view/ForsendelseWindow.fxml"));
        MainWindowPane.getChildren().setAll(pane);
    }

    
    
}
