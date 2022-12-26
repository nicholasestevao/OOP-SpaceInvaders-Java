
package spaceinvadersgrafico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import spaceinvaders.engine.Jogo;

public class ThreadJogo extends Thread{
    private Jogo jogo;
    private Label lbScore;
    private Label lbGameOver;
    private char[] tecla;
    private int delayAliens;
    private Label lbVidas;
    private Pane pPainelPrincipal;
    
    ThreadJogo( char[] tecla, Label lbScore, Label lbGameOver, Label lbVidas, Pane pPainelPrincipal){
        this.jogo = new Jogo(tecla);
        this.lbScore = lbScore;
        this.lbGameOver = lbGameOver;
        this.tecla = tecla;
        delayAliens = 4;
        this.lbVidas = lbVidas;
    }

    public Jogo getJogo() {
        return jogo;
    }
    
    
    public void run(){
        this.jogo.rodar(this.delayAliens, lbScore, lbGameOver, lbVidas);
        System.out.println("Jogo terminou de rodar");
    }
}
