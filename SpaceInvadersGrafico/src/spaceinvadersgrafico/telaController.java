/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvadersgrafico;

import spaceinvaders.engine.ThreadJogo;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import spaceinvaders.elementos.Base;
import spaceinvaders.elementos.Canhao;
import spaceinvaders.elementos.Nave;
import spaceinvaders.elementos.Tiro;
import spaceinvaders.engine.Jogo;
import static spaceinvaders.engine.MatrizEntidades.tamX;
import static spaceinvaders.engine.MatrizEntidades.tamY;

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
    private Label lbScore;
    
    @FXML
    private Label lbVidas;
    
    @FXML
    private Label lbGameOver;
    
    @FXML
    private Pane pPainelPrincipal;
    
    @FXML
    private Pane pInstrucoes;
    
    @FXML
    private Pane pGameVariables;
        
    private char[] tecla; // tecla apertada pelo jogador

    
    @FXML
    public void jogar(){
        threadJogo = new ThreadJogo( tecla, lbScore, lbGameOver, lbVidas);
        this.pInstrucoes.setVisible(false);
        this.pGameVariables.setVisible(true);
        this.jogo = threadJogo.getJogo();
        this.bJogar.setDisable(true);
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
        
        Nave naveEspecial = jogo.getNaveEspecial();
        ImageView naveEspecialView = naveEspecial.getSprite().getImage();
        naveEspecialView.setX(0);
        naveEspecialView.setY(50);
        naveEspecialView.setVisible(false);
        pPainelPrincipal.getChildren().add(naveEspecialView);
        
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
        
        threadJogo.start();
        
    }
    
    @FXML
    public void sair(){
        Stage stage = (Stage) this.pPainelPrincipal.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        tecla = new char[1];
        tecla[0] = 'a';        
        this.pGameVariables.setVisible(false);
    }    
    
    public void teclaDireitaPressionada(){
        tecla[0] = 'r';
        System.out.println("Pressionou tecla direita");
    }
    
    public void teclaEsquerdaPressionada(){
        tecla[0] = 'l';
        System.out.println("Pressionou tecla esquerda");
    }
    
    public void teclaEspacoPressionada(){
        tecla[0] = 'e';
        System.out.println("Pressionou tecla espaco");
    }
    
    public void teclaEscPressionada(){
        tecla[0] = 's';
        System.out.println("Pressionou tecla ESC");
        try{
                TimeUnit.MILLISECONDS.sleep(250);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        tecla[0] = 'a';
        this.pInstrucoes.setVisible(true);
        this.pGameVariables.setVisible(false);
        for(int i=pPainelPrincipal.getChildren().size() - 1; i>=0; i--){
            if(pPainelPrincipal.getChildren().get(i).getId() == null){
                pPainelPrincipal.getChildren().remove(i);
            }
               
        }
        this.lbGameOver.setText("");
        this.bJogar.setDisable(false);
        
    }
    
    
    
} 
