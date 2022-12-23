
package spaceinvadersgrafico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import spaceinvaders.engine.Jogo;

public class ThreadJogo extends Thread{
    private Jogo jogo;
    private Pane painel;
    private Label lbScore;
    private Label lbGameOver;
    
    ThreadJogo(Pane painel, char[] tecla, Label lbScore, Label lbGameOver){
        this.jogo = new Jogo(tecla);
        this.painel = painel;
        this.lbScore = lbScore;
        this.lbGameOver = lbGameOver;
    }

    public Jogo getJogo() {
        return jogo;
    }
    
    
    
    public void run(){
        this.jogo.rodar(3, painel, lbScore, lbGameOver);
        /*BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String opcao = "";
        while(!opcao.equals("sair")){
            //opcao = teclado.readLine();
        }*/
        
    }
}
