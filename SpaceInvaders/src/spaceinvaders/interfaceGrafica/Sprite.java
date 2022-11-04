package spaceinvaders.interfaceGrafica;
//import java.util.*;
//import java.awt.Image;

/**
 * Classe que armazena imagens das entidades na tela
 * @author Nicho
 */
public class Sprite {
    private String sprite;   
    
    
    /**
     * Imagem da entidade
     */
    //private Image image;

    /**
     * Construtor que recebe a imagem da entidade
     * @param sprite caractere vinculado a essa sprite (sera substituido por uma imagem)
     */
    public Sprite(/*Image image*/ String sprite) {
        //this.image = image;
        this.sprite = sprite;
    }
    
    public String getSprite(){
        return this.sprite;
    }
    
    public void setSprite(String s){
        this.sprite = s;
    }

}