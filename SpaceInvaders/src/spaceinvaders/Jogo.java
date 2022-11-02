package spaceinvaders;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Classe Jogo:
 * Controla as entidades e roda o jogo
 * @author Nicho
 */
public class Jogo {
    /**
     * Flag que indica se o jogo está rodando
     */
    private int rodando;
    
    /**
     * Pontuacao feita pelo usuario
     */
    private int pontuacao;

    private int direcaoMovNaves;
    private int navesDesceram;
    
    /**
     * Vetor das bases
     */
    private ArrayList<Base> bases;

    /**
     * Canhao (controlado pelo jogador)
     */
    private Canhao canhao;

    /**
     * Vetor de naves alienígenas
     */
    private ArrayList<Nave> naves;

    /**
     * Vetor de tiros disparados
     */
    private ArrayList<Tiro> tiros;

    /**
     * Construtor da classe Jogo:
     * Inicializa todas as entidades
     */
    public Jogo() {
        //Inicializar todos os objetos
        //Telo 800x600
        //Sprite sprite = new Sprite(); //sprite vazia para versao sem interface grafica
        this.rodando = 0;
        this.pontuacao = 0;
        direcaoMovNaves = 1;
        navesDesceram = 1;
        
        this.bases = new ArrayList<Base>();
        for(int i=0; i<4; i++){
            Base base = new Base(12,4*i+2, new Sprite('B'));
            this.bases.add(base);
        }
        
        this.canhao = new Canhao(14,2, 3, new Sprite('C'));
        
        this.naves = new ArrayList<Nave>();
        for(int i=0; i<5; i++){
            for(int j=0; j<11; j++){
                Nave ship = new Nave(i,j, new Sprite('N'));
                this.naves.add(ship);
            }
        }
        
        this.tiros = new ArrayList<Tiro>();
    }
    
    /**
     * Retorna a nave da posicao [X,Y] da tela se ela existir
     */
     public Nave getNave(int x, int y){
        for(int i = 0; i<this.naves.size(); i++){
            if(this.naves.get(i).getX() == x && this.naves.get(i).getY() == y){
                return this.naves.get(i);
            }            
        }
        return null;
    }
     
    public void moveNaves(){
        int yMax = 0;
        int yMin = Tela.tamY - 1;
        for(int i=0; i<this.naves.size(); i++){
            int y = this.naves.get(i).getY();
            if(y > yMax){
                yMax = y;
            }
            if(y< yMin){
                yMin = y;
            }
        }
        //System.out.println("Ymax: "+yMax + " Ymin: "+yMin);
        if(yMax == Tela.tamY - 1 && navesDesceram == 0){
            //move para para baixo
            this.naves.forEach((nave) -> {                
                nave.mover(1, 0);
                //System.out.println("Moveu para " + nave.getX() + " "+nave.getY() );
            });
            direcaoMovNaves = -1;
            navesDesceram = 1;
        }else if(yMin == 0  && navesDesceram == 0){
            //move para baixo
            this.naves.forEach((nave) -> {                
                nave.mover(1, 0);
                //System.out.println("Moveu para " + nave.getX() + " "+nave.getY() );
            });
            direcaoMovNaves = 1;
            navesDesceram = 1;
        }else{
            //move da direcao do mov 
            this.naves.forEach((nave) -> {                
                nave.mover(0, direcaoMovNaves);
                //System.out.println("Moveu para dir " + nave.getX() + " "+nave.getY() );
            });
            navesDesceram = 0;
        }
    }

    /**
     * Roda o jogo
     */
    public void rodar() {
        Scanner s = new Scanner(System.in);
        char op = 'f';
        
        this.rodando = 1;
        
        Tela tela = new Tela();

        while(this.rodando == 1){           
            
            //Insere bases na tela
            ArrayList<Integer> remover = new ArrayList();
            this.bases.forEach( (base) ->{
                tela.insereTela(base);
                if(base.getSprite().getSprite() == 'X'){
                    
                    remover.add(this.bases.indexOf(base));
                }
            });
            remover.forEach(indice -> {this.bases.remove((int) indice);});
            remover.clear();
            
            // Insere canhao na tela
            if(canhao.getVida() > 0){
                tela.insereTela(canhao);
                if(canhao.getSprite().getSprite() == 'X'){
                    canhao.setSprite('C');
                }
            }else{
                this.rodando = 0;
                System.out.println("Você perdeu!");
                break;
            }
            
            
            this.moveNaves();

            this.naves.forEach( (nave) ->{
                tela.insereTela(nave);
                if(nave.getSprite().getSprite() == 'X'){
                    remover.add(this.naves.indexOf(nave));
                }
            });
            remover.forEach(indice -> {                
                this.naves.remove((int) indice);});
            remover.clear();
            
            if(System.currentTimeMillis()%7 == 0){
                ArrayList<Nave> navesFileiraCanhao = new ArrayList();
                this.naves.forEach((nave)->{
                    if(nave.getY() == canhao.getY()){
                        navesFileiraCanhao.add(nave);
                    }
                });

                //Uma nave da fileira do canhao atira se ele se moveu
                if(!navesFileiraCanhao.isEmpty()){
                    int idNaveFilAtira = (int)(System.currentTimeMillis())%(navesFileiraCanhao.size());
                    this.tiros.add(navesFileiraCanhao.get(idNaveFilAtira).atirar());
                    navesFileiraCanhao.clear();
                }
            }
            //De vez em quando uma nave aleatoria atira
            if(System.currentTimeMillis()%5 == 0){
                int idNaveAleatoriaAtira = (int)(System.currentTimeMillis())%(this.naves.size());
                this.tiros.add(this.naves.get(idNaveAleatoriaAtira).atirar());
            }
            
            
            op = s.next().charAt(0);
            switch (op){
                case 'a':
                    if(canhao.getY() > 0)
                        this.canhao.mover(0, -1);
                    break;
                case 'd':
                    if(canhao.getY() <Tela.tamY - 1)
                    this.canhao.mover(0, 1);
                    break;
                case 's':
                    this.tiros.add(this.canhao.atirar()); 
                    break;  
                case 'f':
                    break;
            }
            
            this.tiros.forEach( tiro ->{ 
                if(tela.getSprite(tiro.getX(), tiro.getY()) == 'B'){
                    //Tiro colidiu com uma base  
                    this.bases.forEach((base)-> {
                       if(base.getX() == tiro.getX() && base.getY() == tiro.getY()){
                           base.reduzirEstado();
                           if(base.getEstado() == 0){
                               base.setSprite('X');
                               tela.insereTela(base);
                           }
                       }
                    });
                    remover.add(this.tiros.indexOf(tiro));
                }else{
                    // Tiro nao colidiu com uma base
                    if(tiro.getTipo() == 0){
                        if(tiro.getX() > 1){
                            tiro.mover(-1, 0);
                            if(tela.getSprite(tiro.getX(), tiro.getY()) == 'N'){
                                //Tiro colidiu com uma nave  
                                this.getNave(tiro.getX(), tiro.getY()).setSprite('X');
                                remover.add(this.tiros.indexOf(tiro));
                                this.pontuacao += 10;
                            }
                        }else{
                            remover.add(this.tiros.indexOf(tiro));
                        }
                    }else{
                        if(tiro.getX() < Tela.tamX -1){
                            tiro.mover(1, 0);
                            char spritePosTiro = tela.getSprite(tiro.getX(), tiro.getY());
                            if(spritePosTiro == 'C'){
                                //Tiro colidiu com o canhao 
                                this.canhao.setSprite('X');
                                this.canhao.decrementaVida();
                                remover.add(this.tiros.indexOf(tiro));
                                //break;
                            }
                        }else{
                            remover.add(this.tiros.indexOf(tiro));
                        }
                    }
                }      
            });
            remover.forEach(indice -> {                
                this.tiros.remove((int) indice);});
            remover.clear();
            
            this.tiros.forEach(tiro -> {tela.insereTela(tiro);});
            
            tela.imprimeTela();
            //System.out.println("tem "+tiros.size() + " tiros, processou "+cont);
            System.out.println("Vida canhao: "+this.canhao.getVida());
            System.out.println("Pontuação: "+this.pontuacao);
            System.out.print("Estados base: ");
            this.bases.forEach(base -> {System.out.print(base.getEstado()+" ");});
            System.out.println("");
            
            try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    }
    

}