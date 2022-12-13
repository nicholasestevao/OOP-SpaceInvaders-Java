package spaceinvaders.elementos;
import java.util.*;

/**
 * Interface Movível:
 * Descreve as entidades da tela que se movem
 * @author Nicho
 */
public interface Movivel {
    /**
     * Move determinada entidade
     * @param x deslocamento em x
     * @param y deslocamento em y
     */
    public void mover(int x, int y);

}