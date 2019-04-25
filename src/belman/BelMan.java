/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Qash
 */
public class BelMan extends Application {
    
   @Override
    public void start(Stage pStage) throws Exception
    {
        pStage.setTitle("BelMan login");
        pStage.centerOnScreen();
        //pStage.getIcons().add(new Image("icon.png"));
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/MainWindow.fxml"));
        Scene scene = new Scene(root);   
        pStage.setScene(scene);
        pStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
