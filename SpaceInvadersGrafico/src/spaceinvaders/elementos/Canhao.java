package spaceinvaders.elementos;
import spaceinvaders.elementos.Tiro;
import java.util.*;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
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
    public void atirar(Tiro tiro) {
        int tipo = 0; //tipo tiro canhao
        tiro.moverPara(this.getX()-1, this.getY());
        tiro.setTipo(tipo);
        System.out.println("Canhao atirou (tiro: "+tiro.getPosition()+") ");
        tiro.getSprite().getImage().setImage(new Image(getClass().getResourceAsStream("Images/tiroCanhao.png")));
        tiro.getSprite().getImage().setVisible(true);
        //return new Tiro(this.getX()+1, this.getY(), tipo, new Sprite("tiroAlien.png","|"));
    }

    /**
     * Move o canhao
     * @param x : deslocamento em x do canhao
     * @param y : deslocamento em y do canhao
     */
    @Override
    public void mover(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
        
        TranslateTransition trans = new TranslateTransition();
        trans.setDuration(Duration.millis(450));
        trans.setByY(x*50);
        trans.setByX(y*50);
        trans.setNode(this.getSprite().getImage());
        trans.play();
    }
    
    public void moverPara(int x, int y) {
        System.out.println("Moveu canhao para: "+x+" "+y);        
        TranslateTransition trans = new TranslateTransition();
        trans.setDuration(Duration.millis(450));
        trans.setToY(0);
        trans.setToX(0);
        trans.setNode(this.getSprite().getImage());        
        trans.play();
        
        this.setX(x);
        this.setY(y);
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