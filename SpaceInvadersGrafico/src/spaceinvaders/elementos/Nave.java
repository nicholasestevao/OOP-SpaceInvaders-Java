package spaceinvaders.elementos;
import spaceinvaders.elementos.Tiro;
import java.util.*;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import spaceinvadersgrafico.Sprite;

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
    public void atirar(Tiro tiro) {
        int tipo = 1; //tipo tiro alien
        tiro.getSprite().getImage().setVisible(false);
        tiro.moverPara(this.getX(), this.getY());
        tiro.setTipo(tipo);
        tiro.getSprite().getImage().setImage(new Image(getClass().getResourceAsStream("tiroAlien.png")));
        //return new Tiro(this.getX()+1, this.getY(), tipo, new Sprite("tiroAlien.png","|"));
    }

    /**
     * Move nave x unidades para esquerda na horizontal e y unidades para baixo na vertical
     * @param x: deslocamento em x da nave
     * @param y: deslocamento em y da nave
     */
    public void mover(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
        TranslateTransition trans = new TranslateTransition();
        trans.setDuration(Duration.millis(250));
        trans.setByY(x*50);
        trans.setByX(y*50);
        trans.setNode(this.getSprite().getImage());
        trans.play();
    }
    
    /**
     * Move a nave
     * @param x posicao final do canhao em x
     * @param y posicao final do canhao em y
     */
    public void moverPara(int x, int y) {
        //System.out.println("Moveu nave para: "+x+" "+y);        
        TranslateTransition trans = new TranslateTransition();
        trans.setDuration(Duration.millis(250));
        trans.setToY(0);
        trans.setToX(0);
        trans.setNode(this.getSprite().getImage());        
        trans.play();
        
        this.setX(x);
        this.setY(y);
    }

}