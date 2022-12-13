package spaceinvaders.interfaceGrafica;
//import java.util.*;
//import java.awt.Image;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Classe que armazena imagens das entidades na tela
 * @author Nicho
 */
public class Sprite {
    private String sprite;   
    
    
    /**
     * Imagem da entidade
     */
    private ImageView image;

    /**
     * Construtor que recebe a imagem da entidade
     * @param sprite caractere vinculado a essa sprite (sera substituido por uma imagem)
     */
    public Sprite(String image, String sprite) {
        Image temp = new Image(getClass().getResourceAsStream("Images/"+image));
        this.image = new ImageView(temp);
        this.sprite = sprite;
    }
    
    public String getSprite(){
        return this.sprite;
    }
    
    public void setSprite(String s){
        this.sprite = s;
    }
    
    public ImageView getImage(){
        return this.image;
    }
    
    public void setImage(String image){
        Image temp = new Image(getClass().getResourceAsStream("Images/"+image));
        this.image.setImage(temp);
    }
    

}