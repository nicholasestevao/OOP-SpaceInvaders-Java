package spaceinvaders.elementos;
import spaceinvaders.elementos.Tiro;
import java.util.*;
import spaceinvaders.interfaceGrafica.Sprite;
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
     * @param vidas: nro de vidas do canhao
     * @param sprite: sprite do canhao
     */
    public Canhao(int x, int y, int vidas, Sprite sprite) {
        super(x, y, sprite);
        this.vidas = vidas;
    }
    
    /**
     * Cria um tiro a partir da posicao atual do canhao
     * @return objeto tiro gerado a partir da posicao do canhao
     */
    public Tiro atirar() {
        int tipo = 0; //tipo tiro canhao
        return new Tiro(this.getX()-1, this.getY(), tipo, new Sprite("*"));
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
    
    /**
     * Retorna vida do canhao
     * @return vida do canhao
     */
    public int getVida(){
        return this.vidas;
    }
    

}