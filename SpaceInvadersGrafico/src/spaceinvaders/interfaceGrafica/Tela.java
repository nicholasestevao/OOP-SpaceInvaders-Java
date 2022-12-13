
package spaceinvaders.interfaceGrafica;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import spaceinvaders.elementos.Base;
import spaceinvaders.elementos.Canhao;
import spaceinvaders.elementos.Entidade;
import spaceinvaders.elementos.Nave;
import spaceinvaders.elementos.Tiro;
import spaceinvaders.engine.Jogo;

public class Tela {
    private char[][] tela;
    
    public static final int tamX = 16;
    public static final int tamY= 16;
    
    public Tela(){
        this.tela = new char[tamX][tamY];
        this.limpaTela();
    }
    
    /**
     * Insere entidade na tela
     * @param ent entidade a ser inserida
     */
    public void insereTela(Entidade ent){
        if(ent.getY() == 17){
            System.out.println(ent.getSprite().getSprite());
        }
        tela[ent.getX()][ent.getY()] = ent.getSprite().getSprite().charAt(0);
    }
    
    /**
     * imprime tela no terminal
     */
    public void imprimeTela(){
        for(int i=0; i< tamY; i++){
            for(int j=0; j< tamX; j++){
                System.out.print(" "+tela[i][j]+" ");
            }
            System.out.println(" ");
        }
        this.limpaTela();
        
    }
    
    
    /**
     * reseta a tela para o caractere padrao (_)
     */
    public void limpaTela(){
        for(int i=0; i< tamY; i++){
            for(int j=0; j< tamX; j++){
                this.tela[j][i] = '_';
            }
        }
    }
    
    /**
     * retorna a sprite (char) de um posicao da tela
     * @param x posicao x
     * @param y posicao y
     * @return sprite (char) da posicao x,y da tela
     */
    public char getSprite(int x, int y){
        return tela[x][y];
    }
}
