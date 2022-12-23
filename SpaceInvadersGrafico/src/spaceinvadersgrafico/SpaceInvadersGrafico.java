/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvadersgrafico;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author Nicho
 */
public class SpaceInvadersGrafico extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tela.fxml"));
        Parent root = loader.load();
        telaController controller  = loader.getController();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()){
                    case RIGHT:
                        controller.teclaDireitaPressionada();
                        break;
                    case LEFT:
                        controller.teclaEsquerdaPressionada();
                        break;
                    case SPACE:
                        controller.teclaEspacoPressionada();
                        break;
                    case ESCAPE:
                        controller.teclaEscPressionada();
                        break;
                }
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
