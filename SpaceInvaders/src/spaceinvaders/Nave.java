package spaceinvaders;
import java.util.*;

public class Nave extends Entidade implements Movivel {

    public Nave(int x, int y/*, Sprite sprite*/) {
        super(x,y/*,sprite*/);
    }

    public void atirar() {
        // TODO implement here
    }


    public void mover(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
    }

}