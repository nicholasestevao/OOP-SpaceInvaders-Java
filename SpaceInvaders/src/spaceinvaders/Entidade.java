package spaceinvaders;
import java.util.*;


public class Entidade {
    //private Sprite sprite;

    private int posicaoX;

    private int posicaoY;
    
    public Entidade(int x, int y/*, Sprite sprite*/) {
        this.posicaoX = x;
        this.posicaoY = y;
        //this.sprite = sprite;
    }
    
    public int getX(){
        return this.posicaoX;
    }
    public int getY(){
        return this.posicaoY;
    }
    public void setX(int x){
        this.posicaoX = x;
    }
    public void setY(int y){
        this.posicaoY = y;
    }
}