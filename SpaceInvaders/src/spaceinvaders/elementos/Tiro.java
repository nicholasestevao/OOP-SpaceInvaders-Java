package spaceinvaders.elementos;
import java.util.*;
import spaceinvaders.interfaceGrafica.Sprite;
/**
 * Classe Tiro
 * @author Nicho
 */
public class Tiro extends Entidade implements Movivel {
    /**
     * Tipo do tiro
     * 0 : canhao
     * 1 : nave alienigena
     */
    private int tipo;
    /**
     * Posicao do tiro
     * @param x: posicao x do tiro
     * @param y: posicao y do tiro
     * @param tipo: tipo do tiro (0 = canhao e 1= nave)
     * @param sprite: sprite do tiro
     * 
     */
    public Tiro(int x, int y, int tipo, Sprite sprite) {
        super(x,y,sprite);
        this.tipo = tipo;
    }
    

    /**
     * Move o tiro
     * @param x deslocamento em x
     * @param y deslocamento em y
     */
    public void mover(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
    
    /**
     * Retorna tipo do tiro
     * @return  tipo do tiro
     */
    public int getTipo(){
        return this.tipo;
    }

}