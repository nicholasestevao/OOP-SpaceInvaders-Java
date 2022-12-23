package spaceinvaders.elementos;
import java.util.*;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import spaceinvaders.interfaceGrafica.Sprite;
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
     * @param x: posicao x do tiro
     * @param y: posicao y do tiro
     * @param tipo: tipo do tiro (0 = canhao e 1= nave)
     * @param sprite: sprite do tiro
     * 
     */
    public Tiro(int x, int y, int tipo, Sprite sprite) {
        super(x,y,sprite);
        this.tipo = tipo;
    }
    

    /**
     * Move o tiro
     * @param x deslocamento em x
     * @param y deslocamento em y
     */
    public void mover(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
        System.out.println("Moveu tiro: "+x+" "+y);
        TranslateTransition trans = new TranslateTransition();
        trans.setDuration(Duration.millis(550));
        trans.setToY(this.getX()*50);
        trans.setToX(this.getY()*50);
        trans.setNode(this.getSprite().getImage());        
        trans.play();
        trans.setOnFinished((e) -> {
            this.getSprite().getImage().setVisible(true);
        });
    }
    
    public void moverPara(int x, int y) {
        this.getSprite().getImage().setVisible(false);
        TranslateTransition trans = new TranslateTransition();
        trans.setDuration(Duration.millis(1));
        System.out.println("Moveu para: "+x+" "+y);
        trans.setToX(y*50);
        trans.setToY(x*50);
        trans.setNode(this.getSprite().getImage());
        trans.setOnFinished((e) -> {
            this.getSprite().getImage().setVisible(true);
        });
        trans.play();
        
        this.setX(x);
        this.setY(y);
    }
    
    /**
     * Retorna tipo do tiro
     * @return  tipo do tiro
     */
    public int getTipo(){
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    

}