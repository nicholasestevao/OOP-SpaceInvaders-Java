package spaceinvaders;
import spaceinvaders.engine.Jogo;
import java.util.*;

/**
 * Classe main
 * @author Nicho
 */
public class SpaceInvaders {
    /**
     * Metodo main
     * @param args argumento generico padrao do main
     */
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        int delayAliens = 4;
        while(true){
            jogo.rodar(delayAliens);
            if(delayAliens >1){
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
