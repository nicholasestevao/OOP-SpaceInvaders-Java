package spaceinvaders.elementos;
import java.util.*;
import spaceinvadersgrafico.Sprite;

/**
 * Classe Entidade
 * Classe genérica de descreve toda entidade que é impressa na tela
 * @author Nicho
 */
public abstract class Entidade {
    
    
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
     * @param sprite: imagem (caractere) da entidade
     */
    public Entidade(int x, int y, Sprite sprite) {
        this.posicaoX = x;
        this.posicaoY = y;
        this.sprite = sprite;
    }
    /**
     * Retorna posicao X da entidade
     * @return posicao x da entidade
     */
    public int getX(){
        return this.posicaoX;
    }
    /**
     * Retorna posica Y da entidade
     * @return posicao y da entidade
     */
    public int getY(){
        return this.posicaoY;
    }
    /**
     * Define posicao X da entidade
     * @param x posicao x da entidade
     */
    public void setX(int x){
        this.posicaoX = x;
    }
    /**
     * Define posicao Y da entidade
     * @param y posicao y da entidade
     */
    public void setY(int y){
        this.posicaoY = y;
    }
    
    /**
     * Retorna sprite da entidade
     * @return sprite da entidade
     */
    public Sprite getSprite(){
        return this.sprite;
    }
    
    /**
     * Define sprite da entidade
     * @param s sprite da entidade
     */
    public void setSprite(String s){
        this.sprite.setSprite(s);
    }
    
    /**
     * @return string com posicao x e y da entidade (para debug)
     */
    public String getPosition(){
        return this.getX()+" "+ this.getY();
    }
}