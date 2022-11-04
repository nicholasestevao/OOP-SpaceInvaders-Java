package spaceinvaders.elementos;
import spaceinvaders.elementos.Tiro;
import java.util.*;
import spaceinvaders.interfaceGrafica.Sprite;

/**
 * Entidade Nave alienígena
 * @author Nicho
 */
public class Nave extends Entidade implements Movivel {

    /**
     * Construtor da classe Nave
     * @param x : posicao x da nave
     * @param y : posicao y da nave
     * @param sprite: sprite da nave
     */
    public Nave(int x, int y, Sprite sprite) {
        super(x,y,sprite);
    }

    /**
     * Metodo que atira
     * @return objeto tiro gerado a partir da posicao da nave
     */
    public Tiro atirar() {
        int tipo = 1; //tipo tiro alien
        return new Tiro(this.getX()+1, this.getY(), tipo, new Sprite("|"));
    }

    /**
     * Move nave x unidades para esquerda na horizontal e y unidades para baixo na vertical
     * @param x: deslocamento em x da nave
     * @param y: deslocamento em y da nave
     */
    public void mover(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

}