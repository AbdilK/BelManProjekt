/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belman;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Qash
 */
public class BelMan extends Application {

    /**
     * Starter programmet
     * @param pStage
     * @throws Exception 
     */
    @Override
    public void start(Stage pStage) throws Exception
    {
        pStage.setTitle("BelMan login");
        pStage.centerOnScreen();
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/MainWindow.fxml"));
        
        
        Scene scene = new Scene(root);   
        pStage.setScene(scene);
        pStage.show();
        //Applikationen starter i maksimeret tilstand
        pStage.setMaximized(true);
        //F11 knappen bruges til at lave fullscreen, således det ser bedre ud på eksempelvis en touch skærm
        pStage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.F11) {
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
