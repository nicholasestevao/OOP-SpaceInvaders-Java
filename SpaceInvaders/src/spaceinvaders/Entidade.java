package spaceinvaders;
import java.util.*;

/**
 * Classe Entidade
 * Classe genérica de descreve toda entidade que é impressa na tela
 * @author Nicho
 */
public class Entidade {
    
    
    private Sprite sprite;
    /**
     * Posicao x (horizontal) da entidade na tela
     */
    private int posicaoX;
    /**
     * Posicao y (vertical) da entidade na tela
     */
    private int posicaoY;
    
    /**
     * Construtor da classe Entidade
     * @param x : posicao x
     * @param y : posicao y
     */
    public Entidade(int x, int y, Sprite sprite) {
        this.posicaoX = x;
        this.posicaoY = y;
        this.sprite = sprite;
    }
    /**
     * Retorna posicao X da entidade
     * @return 
     */
    public int getX(){
        return this.posicaoX;
    }
    /**
     * Retorna posica Y da entidade
     * @return 
     */
    public int getY(){
        return this.posicaoY;
    }
    /**
     * Define posicao X da entidade
     * @param x 
     */
    public void setX(int x){
        this.posicaoX = x;
    }
    /**
     * Define posicao Y da entidade
     * @param y 
     */
    public void setY(int y){
        this.posicaoY = y;
    }
    
    public Sprite getSprite(){
        return this.sprite;
    }
    
    public void setSprite(char s){
        this.sprite.setSprite(s);
    }
}