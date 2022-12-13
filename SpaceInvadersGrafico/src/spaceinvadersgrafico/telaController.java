/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvadersgrafico;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import spaceinvaders.elementos.Base;
import spaceinvaders.elementos.Canhao;
import spaceinvaders.elementos.Nave;
import spaceinvaders.elementos.Tiro;
import spaceinvaders.engine.Jogo;
import spaceinvaders.interfaceGrafica.Tela;
import static spaceinvaders.interfaceGrafica.Tela.tamX;
import static spaceinvaders.interfaceGrafica.Tela.tamY;

/**
 *
 * @author Nicho
 */
public class telaController implements Initializable {
    
    private Jogo jogo;
    
    private ThreadJogo threadJogo;
    
    @FXML
    private Button bJogar;
    
    @FXML
    private Pane pPainelPrincipal;
    
    @FXML
    private ImageView imgCanhao;

    
    @FXML
    public void jogar(){
        pPainelPrincipal.getChildren().clear();
        System.out.println("Adicionando canhao");
        System.out.println(jogo.getCanhao().getSprite().getImage().toString());
        ImageView canhaoView = jogo.getCanhao().getSprite().getImage();
        canhaoView.setY(jogo.getCanhao().getX()*50);
        canhaoView.setX(jogo.getCanhao().getY()*50);
        pPainelPrincipal.getChildren().add(canhaoView);
        System.out.println("canhao");
        ArrayList<Base> bases = jogo.getBases();
        for(int i=0; i< bases.size(); i++){
            ImageView baseView = bases.get(i).getSprite().getImage();
            baseView.setY(bases.get(i).getX()*50);
            baseView.setX(bases.get(i).getY()*50);
            pPainelPrincipal.getChildren().add(baseView);
        }
        ArrayList<Nave> naves = jogo.getNaves();
        for(int i=0; i< naves.size(); i++){
            ImageView naveView = naves.get(i).getSprite().getImage();
            naveView.setY(naves.get(i).getX()*50);
            naveView.setX(naves.get(i).getY()*50);
            pPainelPrincipal.getChildren().add(naveView);
        }
        Tiro tiroCanhao = jogo.getTiroCanhao();
        ImageView tiroCanhaoView = tiroCanhao.getSprite().getImage();
        tiroCanhaoView.setY(0);
        tiroCanhaoView.setX(0);
        tiroCanhaoView.setVisible(false);
        pPainelPrincipal.getChildren().add(tiroCanhaoView);
        
        ArrayList<Tiro> tirosAliens = jogo.getTiros();
        for(int i=0; i<5; i++){
            ImageView tiroView = tirosAliens.get(i).getSprite().getImage();
            tiroView.setY(0);
            tiroView.setX(0);
            tiroView.setVisible(false);
            pPainelPrincipal.getChildren().add(tiroView);
        }
        
        
        /*int delayAliens = 4;
        while(true){
            jogo.rodar(delayAliens, this.pPainelPrincipal);
            break;
            if(delayAliens >1){
                delayAliens--;
            }else{
                break;
            }
            jogo = new Jogo();
        }*/
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {             
        threadJogo = new ThreadJogo(this.pPainelPrincipal);
        threadJogo.start();
        this.jogo = threadJogo.getJogo();
    }    
    
    
    
}
