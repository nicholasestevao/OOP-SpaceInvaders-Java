package spaceinvaders;
import java.util.*;
import java.awt.Image;

/**
 * Classe que armazena imagens das entidades na tela
 * @author Nicho
 */
public class Sprite {
    private char sprite;   
    
    
    /**
     * Imagem da entidade
     */
    //private Image image;

    /**
     * Construtor que recebe a imagem da entidade
     * @param image 
     */
    public Sprite(/*Image image*/ char sprite) {
        //this.image = image;
        this.sprite = sprite;
    }
    
    public char getSprite(){
        return this.sprite;
    }
    
    public void setSprite(char s){
        this.sprite = s;
    }

}