
package spaceinvadersgrafico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.layout.Pane;
import spaceinvaders.engine.Jogo;

public class ThreadJogo extends Thread{
    private Jogo jogo;
    private Pane painel;
    
    ThreadJogo(Pane painel){
        this.jogo = new Jogo();
        this.painel = painel;
    }

    public Jogo getJogo() {
        return jogo;
    }
    
    
    
    public void run(){
        this.jogo.rodar(3, painel);
        /*BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String opcao = "";
        while(!opcao.equals("sair")){
            //opcao = teclado.readLine();
        }*/
        
    }
}
