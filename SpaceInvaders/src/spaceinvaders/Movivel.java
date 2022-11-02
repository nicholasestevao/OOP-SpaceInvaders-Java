package spaceinvaders;
import java.util.*;

/**
 * Interface Movível:
 * Descreve as entidades da tela que se movem
 * @author Nicho
 */
public interface Movivel {
    /**
     * Move determinada entidade
     * @param x
     * @param y 
     */
    public void mover(int x, int y);

}