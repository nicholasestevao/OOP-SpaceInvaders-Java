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
    
    /**
     * Move a entidade 
     * @param x posicao final da entidade em x
     * @param y posicao final da entidade em y
     */
    public void moverPara(int x, int y);
}