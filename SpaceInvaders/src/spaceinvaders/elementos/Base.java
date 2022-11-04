package spaceinvaders.elementos;
import java.util.*;
import spaceinvaders.interfaceGrafica.Sprite;
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
    
    public void reduzirEstado(){
        this.estado -=25;
    }
    
    public int getEstado(){
        return this.estado;
    }
    

}