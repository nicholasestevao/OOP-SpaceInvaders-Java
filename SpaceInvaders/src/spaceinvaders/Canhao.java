package spaceinvaders;
import java.util.*;
/**
 * Classe Canhao:
 * Entidade controlada pelo jogador
 * @author Nicho
 */
public class Canhao extends Entidade implements Movivel {
    /**
     * Quantidade de vidas do jogador
     */
    private int vidas;
    /**
     * Contrutor da classe Canhao
     * @param x : posicao x do canhao
     * @param y : posicao y do canhao
     */
    public Canhao(int x, int y, int vidas, Sprite sprite) {
        super(x, y, sprite);
        this.vidas = vidas;
    }
    
    /**
     * Cria um tiro a partir da posicao atual do canhao
     */
    public Tiro atirar() {
        int tipo = 0; //tipo tiro canhao
        return new Tiro(this.getX()-1, this.getY(), tipo, new Sprite('i'));
    }

    /**
     * Move o canhao
     * @param x : deslocamento em x do canhao
     * @param y : deslocamento em y do canhao
     */
    public void mover(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }
    
    /**
     * Decrementa vida do canhao
     */
    public void decrementaVida(){
        this.vidas--;
    }
    
    public int getVida(){
        return this.vidas;
    }
    

}