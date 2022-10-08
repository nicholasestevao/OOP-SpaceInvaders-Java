package spaceinvaders;
import java.util.*;

public class Tiro extends Entidade implements Movivel {

    public Tiro(int x, int y, Sprite sprite) {
        super(x,y/*,sprite*/);
    }

    public void mover(int x, int y) {
            System.out.println("Mover tiro.");
    }

}