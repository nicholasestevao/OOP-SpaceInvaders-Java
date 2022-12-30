
package spaceinvaders.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import spaceinvaders.engine.Jogo;


/**
 * 
 * Thread que roda o jogo
 */
public class ThreadJogo extends Thread{
    private Jogo jogo;
    private Label lbScore;
    private Label lbGameOver;
    private char[] tecla;
    private int delayAliens;
    private Label lbVidas;
    private Pane pPainelPrincipal;
    
    /**
     * Construtor: recebe a tecla digitada pelo jogador e os componentes graficos que sao atualizados com Timeline
     * @param tecla opcao que o jogador apertou
     * @param lbScore label pontuacao
     * @param lbGameOver label de fim de jogo
     * @param lbVidas  label vidas do canhao
     */
    public ThreadJogo(char[] tecla, Label lbScore, Label lbGameOver, Label lbVidas){
        this.jogo = new Jogo(tecla);
        this.lbScore = lbScore;
        this.lbGameOver = lbGameOver;
        this.tecla = tecla;
        this.lbVidas = lbVidas;
    }

    /**
     *
     * @return jogo atual
     */
    public Jogo getJogo() {
        return jogo;
    }
    
    /**
     * Roda o jogo
     */
    public void run(){
        this.jogo.rodar(lbScore, lbGameOver, lbVidas);
        System.out.println("Jogo terminou de rodar");
    }
}
