package spaceinvaders;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Jogo {
    private int rodando;

    private ArrayList<Base> bases;

    private Canhao canhao;

    private ArrayList<Nave> naves;

    private ArrayList<Tiro> tiros;

    public Jogo() {
        //Inicializar todos os objetos
        //Telo 800x600
        //Sprite sprite = new Sprite(); //sprite vazia para versao sem interface grafica
        this.rodando = 0;
        
        this.bases = new ArrayList<Base>();
        for(int i=0; i<4; i++){
            Base base = new Base(8,4*i+2/*,sprite*/);
            this.bases.add(base);
        }
        
        this.canhao = new Canhao(10,2/*, sprite*/);
        
        this.naves = new ArrayList<Nave>();
        for(int i=0; i<5; i++){
            for(int j=0; j<11; j++){
                Nave ship = new Nave(i,j/* sprite*/);
                this.naves.add(ship);
            }
        }
        
        this.tiros = new ArrayList<Tiro>();
    }

    public void rodar() {
        this.rodando = 1;
        char[][] tela = new char[12][16];
        for(int i=0; i<12; i++){
            for(int j=0; j<16; j++){
                tela[i][j] = '_';
            }
        }
        int count = 0;
        int inc = 0;
        while(this.rodando == 1){           
            if(count == 6 || count == 0){
                if(inc == 1){
                    inc = -1;
                    count--;
                }else{
                    inc = 1;
                    count++;
                }
            }else{
                if(inc == 1){
                    count++;
                }else{
                    count--;
                }
            }
            //System.out.println(inc + " "+count);
            for(int i=1; i<=4; i++){
                this.bases.forEach((base)->{tela[base.getX()][base.getY()] = 'B';});
            }
            tela[canhao.getX()][canhao.getY()] = 'C';

            for(int i=0; i<55; i++){
                    tela[this.naves.get(i).getX()][this.naves.get(i).getY()] = 'N';
                    if(count == 6 || count == 0){
                        this.naves.get(i).mover(1, 0);
                    }else{
                        this.naves.get(i).mover(0, inc);
                    }
                    if(this.naves.get(i).getX() == 12){
                        this.rodando = 0;
                    }
                    
            }
            for(int i=0; i<12; i++){
                for(int j=0; j<16; j++){
                   System.out.print(" "+tela[i][j]+" ");
                   tela[i][j] = '_';
                }
                System.out.println("");
            }
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    }

}