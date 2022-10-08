package spaceinvaders;
import java.util.*;

public class Base extends Entidade {

    public Base(int x, int y/*, Sprite sprite*/) {
        super(x, y/*, sprite*/);
        this.estado = 100; //estado inicial        
    }

    public int estado;

}