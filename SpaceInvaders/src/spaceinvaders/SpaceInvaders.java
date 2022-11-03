package spaceinvaders;
import java.util.*;

/**
 * Classe main
 * @author Nicho
 */
public class SpaceInvaders {
    /**
     * Metodo main
     */
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        int delayAliens = 3;
        while(true){
            jogo.rodar(delayAliens);
            if(delayAliens >0){
                delayAliens--;
            }
            jogo = new Jogo();
        }
        
        /*System.out.println("oi");
        try{TimeUnit.SECONDS.sleep(2);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("3 seg");*/
    }
    
}
