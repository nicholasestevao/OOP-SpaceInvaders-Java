package spaceinvaders.elementos;
import java.util.*;
import spaceinvadersgrafico.Sprite;
/**
 * Classe Base:
 * São os blocos fixos na tela atrás dos quais o jogado pode esconder o canhão e que recebem dano quando
 * atingidos por tiros das naves alienígenas
 * @author Nicho
 */
public class Base extends Entidade {
    /**
     * Estado da base: indica sua "saúde".
     * Diminui à medida que a base é atingida por tiros
     */
    private int estado;
    
    /**
     * Construtor da classe Base:
     * @param x : posicao x da Base
     * @param y : posicao y da Base
     * @param sprite: sprite da Base
     */
    public Base(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.estado = 100; //estado inicial        
    }   
    
    /**
     * Rediz estado da base, quando ela é atingida por um tiro de alienigena
     */
    public void reduzirEstado(){
        this.estado -=25;
    }
    
    /**
     * @return estado atual da base
     */
    public int getEstado(){
        return this.estado;
    }
    
    /**
     * Define imagem da base de acordo com o estado atual dela
     */
    public void setImage(){
        if(this.estado == 100){
            this.getSprite().setImage("base_100.png");
        }else if(this.estado == 75){
            this.getSprite().setImage("base_75.png");
        }else if(this.estado == 50){
            this.getSprite().setImage("base_50.png");
        }else if(this.estado == 25){
            this.getSprite().setImage("base_25.png");
        }else{
            this.getSprite().setImage("base1.png");
        }
    }
    

}