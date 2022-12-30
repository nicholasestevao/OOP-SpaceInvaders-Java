package spaceinvadersgrafico;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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


    public static void main(String[] args) {
        launch(args);
    }
    
}
