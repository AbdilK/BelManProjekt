/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman;


import belman.gui.model.DepartmentOrderModel;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.lang.String;

/**
 *
 * @author Qash
 */
public class BelMan extends Application {
    
    private DepartmentOrderModel DOM = new DepartmentOrderModel();
    
   @Override
    public void start(Stage pStage) throws Exception
    {
        pStage.setTitle("BelMan login");
        pStage.centerOnScreen();
        //pStage.getIcons().add(new Image("icon.png"));
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/MainWindow.fxml"));
        //StackPane pane = new StackPane();
        
        Scene scene = new Scene(root);   
        pStage.setScene(scene);
        pStage.show();
        //pStage.setMaximized(true);
        pStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.F10) {
                        pStage.setFullScreen(true);
                    }
                }
            });
            pStage.show();
            
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    
}
