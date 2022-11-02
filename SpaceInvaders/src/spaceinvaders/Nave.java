package spaceinvaders;
import java.util.*;

/**
 * Entidade Nave alienígena
 * @author Nicho
 */
public class Nave extends Entidade implements Movivel {

    /**
     * Construtor da classe Nave
     * @param x : posicao x da nave
     * @param y : posicao y da nave
     */
    public Nave(int x, int y, Sprite sprite) {
        super(x,y,sprite);
    }

    /**
     * Metodo que atira
     */
    public Tiro atirar() {
        int tipo = 1; //tipo tiro alien
        return new Tiro(this.getX()-1, this.getY(), tipo, new Sprite('!'));
    }

    /**
     * Move nave x unidades para esquerda na horizontal e y unidades para baixo na vertical
     */
    public void mover(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

}