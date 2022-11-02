package spaceinvaders;
import java.util.*;
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
     */
    public Tiro(int x, int y, int tipo, Sprite sprite) {
        super(x,y,sprite);
        this.tipo = tipo;
    }
    

    /**
     * Move o tiro
     * @param x
     * @param y 
     */
    public void mover(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
    
    /**
     * Retorna tipo do tiro
     * @return 
     */
    public int getTipo(){
        return this.tipo;
    }

}