package spaceinvaders.engine;
import spaceinvaders.elementos.Canhao;
import spaceinvaders.elementos.Nave;
import spaceinvaders.elementos.Base;
import spaceinvaders.elementos.Tiro;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import spaceinvaders.interfaceGrafica.Sprite;
import spaceinvaders.interfaceGrafica.Tela;
import spaceinvadersgrafico.telaController;

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

    /**
     * Controla movimento das naves
     * 1  -> direita
     * -1 -> esquerda
     * 
     * Obs: precisa pertencer ao jogo para poder ser usada em expressoes forEach
     */
    private int direcaoMovNaves;
    /**
     * Variaveis que armazena se o ultimo movimento das naves foi para baixo
     * 
     * Obs: precisa pertencer ao jogo para poder ser usada em expressoes forEach
     */    
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
    //private ArrayList<Tiro> tiros;
    private Tiro tiroCanhao;
    private ArrayList<Tiro> tirosAliens;   
    private int numTirosAliens;
    
    /**
     * Tela (matriz de char)
     */
    private Tela tela;
    
    private char[] op;
    
    
    public Tela getTela(){
        return this.tela;
    }
    
    public Canhao getCanhao(){
        return this.canhao;
    }

    public Tiro getTiroCanhao() {
        return tiroCanhao;
    }

    /**
     * Construtor da classe Jogo:
     * Inicializa todas as entidades
     */
    public Jogo( char[] tecla) {
        //Inicializar todos os objetos
        //Telo 800x600
        this.op = tecla;
        this.rodando = 0;
        this.pontuacao = 0;
        
        //Variaveis que controlam a direção do movimento das naves
        direcaoMovNaves = 1;
        navesDesceram = 1;
        
        //Inicializa as bases
        this.bases = new ArrayList<Base>();
        for(int i=0; i<4; i++){
            Base base = new Base(12,4*i+2, new Sprite("base1.png","B"));
            this.bases.add(base);
        }
        
        //Inicializa canhao
        this.canhao = new Canhao(14,2, 3, new Sprite("canhao.png","C"));
        
        //Inicializa as naves
        this.naves = new ArrayList<Nave>();
        for(int i=0; i<5; i++){
            for(int j=0; j<11; j++){
                String s = "_";
                String img = "";
                if(i==0){
                    s = "Y";
                    img = "alien1.png";
                }
                if(i==1 || i ==2){
                    s = "N";
                    img = "alien2.png";
                }
                if(i==3 || i ==4){
                    s = "W";
                    img = "alien3.png";
                }
                Nave ship = new Nave(i,j, new Sprite(img,s));
                this.naves.add(ship);                              
            }
        }
        
        //Inicializa os tiros
        this.tiroCanhao = new Tiro(0,0,0, new Sprite("tiroCanhao.png", "|"));
        
        this.tirosAliens = new ArrayList<Tiro>();
        this.numTirosAliens = 0;
        
        for(int i=0; i<5; i++){
            Tiro tiro = new Tiro(0,0, 1, new Sprite("tiroAlien.png", "|"));
            this.tirosAliens.add(tiro);
        }
    }
    
    /**
     * Retorna a nave da posicao [X,Y] da tela se ela existir
     */
     private Nave getNave(int x, int y){
        for(int i = 0; i<this.naves.size(); i++){
            if(this.naves.get(i).getX() == x && this.naves.get(i).getY() == y){
                return this.naves.get(i);
            }            
        }
        return null;
    }

    public ArrayList<Base> getBases() {
        return bases;
    }

    public ArrayList<Nave> getNaves() {
        return naves;
    }

    public ArrayList<Tiro> getTiros() {
        return tirosAliens;
    }
     
    
    /**
     * Move o bloco de naves de acordo com o padrão do jogo
     */
    private void moveNaves(){
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
     * 
     * @param delayAliens indica atraso de movimentação dos aliens em relacao ao canhao
     */
    public void rodar(int delayAliens, Pane painel, Label lbScore, Label lbGameOver) {
        
        // Variaveis que leem opcao do jogador
        //Scanner s = new Scanner(System.in);
        //char op = 'f';
        
        // Indica que o jogo esta rodando
        this.rodando = 1;
        
        /**
         * Conta quantas vezes a tela foi atualizada
         */
        int contReload = 0;
        
        /**
         * Indica se esta sendo exibida a nave especial no topo da tela que vale mais pontos
         */
        int flagNaveEspecial = 0;
        /**
         * Nave espcial que vale mais pontos
         */
        Nave naveEspecial = new Nave(0,0, new Sprite("base1.png", "E"));
        
        /**
         * Flag que armazena se o canhao ja atirou
         */
        int flagCanhaoAtirou = 0;
        
        /**
         * Flag que armazena se os aliens ja aceleraram
         */
        int flagAcelerou = 0;
        
        //Inicializa tela
        tela = new Tela();
        
        while(this.rodando == 1){           
            //Vetor auxiliar para remocão de componentes
            ArrayList<Integer> remover = new ArrayList<Integer>();
            
            // Insere canhao na tela
            if(canhao.getVida() > 0){
                tela.insereTela(canhao);
                if(canhao.getSprite().getSprite().equals("X")){
                    //System.out.println("Canhao reviveu e voltou a posicao inicial");
                    canhao.setSprite("C");
                    // Volta canhao para posicao inicial
                    canhao.moverPara(14,2);
                    canhao.getSprite().setImage("canhao.png");
                    canhao.getSprite().getImage().setVisible(true);
                }                
            }else{
                // Fim da fase
                this.rodando = 0;
                System.out.println("Você perdeu todas as suas vidas :(");
                KeyFrame kf= new KeyFrame(Duration.millis(100), new KeyValue(lbGameOver.textProperty(), "Você perdeu todas as suas vidas :("));
                Timeline t = new Timeline(kf);
                t.play();
                break;
            }
            // Verifica fim do jogo
            if(this.rodando == 0){
                System.out.println("Você perdeu!");
                System.out.println("Os alienígenas te alcançaram :(");
                KeyFrame kf= new KeyFrame(Duration.millis(100), new KeyValue(lbGameOver.textProperty(), "Você perdeu!! Os alienígenas te alcançaram :("));
                Timeline t = new Timeline(kf);
                t.play();
                break;
            }
            if(this.naves.size() + flagNaveEspecial == 0){
                this.rodando = 0;  
                System.out.println("Parabéns! Você ganhou :)");
                KeyFrame kf= new KeyFrame(Duration.millis(100), new KeyValue(lbGameOver.textProperty(), "Parabéns! Você ganhou :)"));
                Timeline t = new Timeline(kf);
                t.play();
                break;
            }
            
            //Insere nave especial
            /*if(flagNaveEspecial == 1){
                tela.insereTela(naveEspecial);
                if(System.currentTimeMillis()%3 == 0 && this.numTirosDisparados <49){
                    //Tiro tiro = this.tiros.get(this.numTirosDisparados);                    
                    //naveEspecial.atirar(tiro);   
                    //this.tiros.add(naveEspecial.atirar());                    
                    //this.numTirosDisparados++;
                }
                if(naveEspecial.getSprite().getSprite().equals("X")){
                    flagNaveEspecial = 0;
                }
            }*/
            
            //Move bloco de naves na velocidade determinada pelo delayAliens
            if(contReload%delayAliens == 0){
                this.moveNaves();
                /*if(System.currentTimeMillis()%37 == 0 && flagNaveEspecial == 0){
                     //Aparece nave especial no topo
                     flagNaveEspecial = 1;
                     naveEspecial.setX(1);
                     naveEspecial.setY(0);            
                     
                 }
                 if(flagNaveEspecial == 1 && naveEspecial.getY() < Tela.tamY -1){
                     naveEspecial.mover(0,1);
                 }else{
                     flagNaveEspecial = 0;
                 }
                 //Quando chega na metade da quantidade de aliens eles passam a se mover mais rapido
                 if(this.naves.size() == 27 && flagAcelerou == 0){
                     delayAliens--;
                     flagAcelerou = 1;
                 }*/
            }
            
            //Insere naves na tela
            this.naves.forEach( (nave) ->{
                tela.insereTela(nave);
                if(nave.getSprite().getSprite().equals("X")){
                    //System.out.println("Removeu nave em "+nave.getX() + " "+nave.getY());
                    remover.add(this.naves.indexOf(nave));
                }
            });
            
            //Remove naves marcadas com X (explosao)
            for(int i = 0; i <remover.size(); i++){
                this.naves.get(remover.get(i) - i).getSprite().getImage().setImage(null);
                this.naves.remove((int) remover.get(i) - i);
            }
            
            remover.forEach(indice -> {  
                if(indice < this.naves.size()){
                    this.naves.get(indice).getSprite().getImage().setImage(null);
                    this.naves.remove((int) indice);
                }
            });
            remover.clear();

            //Verifica se alguma nave ja passou da linha do canhao (fim do jogo)
            this.naves.forEach(nave -> {
                if(nave.getX() >= this.canhao.getX()){
                    this.rodando = 0;                    
                }
            });

            
            
            //De vez em quando uma nave da fileira do canhao atira
            if(this.numTirosAliens < 4 && System.currentTimeMillis()%7 == 0){
                ArrayList<Nave> navesFileiraCanhao = new ArrayList<Nave>();
                this.naves.forEach((nave)->{
                    if(nave.getY() == canhao.getY()){
                        navesFileiraCanhao.add(nave);
                    }
                });

                //Uma nave da fileira do canhao atira
                if(!navesFileiraCanhao.isEmpty()){
                    int idNaveFilaAtira = (int)(System.currentTimeMillis())%(navesFileiraCanhao.size());
                    Tiro tiro = this.tirosAliens.get(this.numTirosAliens);                    
                    navesFileiraCanhao.get(Math.abs(idNaveFilaAtira)).atirar(tiro);
                    this.numTirosAliens++;
                    navesFileiraCanhao.clear();
                }
            }else if(this.numTirosAliens < 4 && System.currentTimeMillis()%5 == 0){//De vez em quando uma nave aleatoria atira
                int idNaveAleatoriaAtira = (int)(System.currentTimeMillis())%(this.naves.size());
                //System.out.println("Id nave aleatoria: "+ idNaveAleatoriaAtira);
                //System.out.println("Numero naves: "+ this.naves.size());
                Tiro tiro = this.tirosAliens.get(this.numTirosAliens);                    
                this.naves.get(Math.abs(idNaveAleatoriaAtira)).atirar(tiro);                    
                //System.out.println("nave aleatoria atirou");
                this.numTirosAliens++;
            }
            
            //Insere bases na tela
            this.bases.forEach( (base) ->{
                tela.insereTela(base);
                base.setImage();
                if(base.getSprite().getSprite() == "X"){                    
                    remover.add(this.bases.indexOf(base));
                }
            });
            //Remove bases marcadas com 'X' (explosao)
            for(int i = 0; i<remover.size(); i++){
                this.bases.get(remover.get(i)-i).getSprite().getImage().setImage(null);
                this.bases.remove((int) remover.get(i)-i); 
            }
            remover.clear();
            
            
            // Processa os tiros das naves (move e verifica colisoes)
            int num_tiros_liberados = 0;
            for(int i = 0; i< this.numTirosAliens; i++){
                if(!(tirosAliens.get(i).getX() == 0 && tirosAliens.get(i).getY() == 0)){
                    //System.out.println("Tiro: "+tirosAliens.get(i).getX() + " "+tirosAliens.get(i).getY());
                    //Se o tiro colidiu com um base
                    if(tela.getSprite(tirosAliens.get(i).getX(), tirosAliens.get(i).getY()) == 'B'){
                        //Tiro colidiu com uma base 
                        //System.out.println("Tiro colidiu base");
                        for(int j=0; j<bases.size(); j++){                       
                           if(bases.get(j).getX() == tirosAliens.get(i).getX() && bases.get(j).getY() == tirosAliens.get(i).getY()){
                               //System.out.println("Tiro colidiu com base");
                               bases.get(j).getSprite().getImage().setImage(null);
                               bases.get(j).reduzirEstado();
                               if(bases.get(j).getEstado() == 0){
                                   bases.get(j).setSprite("X");
                                   tela.insereTela(bases.get(j));
                               }
                           }
                        }
                        tirosAliens.get(i).getSprite().getImage().setImage(null);
                        tirosAliens.get(i).moverPara(0, 0);
                        num_tiros_liberados++;
                    }else{
                        // Tiro da nave nao colidiu com uma base
                        //System.out.println("Tiro nave");
                        if(tirosAliens.get(i).getX() < Tela.tamX -1){                            
                            char spritePosTiro = tela.getSprite(tirosAliens.get(i).getX()+1, tirosAliens.get(i).getY());
                            //System.out.println("Posicao: "+tirosAliens.get(i).getPosition()+ " "+tela.getSprite(tirosAliens.get(i).getX(), tirosAliens.get(i).getY()));

                            if(spritePosTiro == 'C'){
                                //Tiro colidiu com o canhao 
                                //System.out.println("Tiro colidiu canhao");
                                this.canhao.setSprite("X");
                                this.canhao.getSprite().setImage("explosao.png");
                                tela.insereTela(this.canhao);
                                this.canhao.decrementaVida();
                                num_tiros_liberados++;
                                tirosAliens.get(i).getSprite().getImage().setImage(null);
                                tirosAliens.get(i).moverPara(0, 0);
                                //break;
                            }else if(tirosAliens.get(i).getSprite().getImage().isVisible()){                            
                                tirosAliens.get(i).mover(1, 0);
                                tela.insereTela(tirosAliens.get(i));
                            }
                        }else{
                            //System.out.println("Tiro da nave com posicao inválida");
                            tirosAliens.get(i).getSprite().getImage().setImage(null);
                            tirosAliens.get(i).moverPara(0, 0);
                            num_tiros_liberados++;
                        }
                    }   
                }
            }
            
            //Remove tiros das naves que colidiram com algo ou chegaram no fim da tela
            /*for(int i=remover.size() - 1; i>=0; i--){
                //System.out.println("Removeu tiro da nave: indice "+(remover.get(i)-i));
                //this.tirosAliens.get((int) remover.get(i)).getSprite().setImage("teste.png");
                this.numTirosAliens--;
                /*int j = remover.get(i) + 1;
                while(j<5 && this.tirosAliens.get(j).getSprite().getImage() != null){
                    j++;
                }
                //System.out.println("Trocou: "+(remover.get(i) - i)+" com "+(j-1));
                Collections.swap(this.tirosAliens, remover.get(i), j-1);*/
            //}
            this.numTirosAliens -= num_tiros_liberados;
            remover.clear();
            
            //Processa tiro do canhao
            if(flagCanhaoAtirou == 1){
                //Se o tiro é do canhao
                //System.out.println("Tiro canhao: x:"+tiroCanhao.getX());
                //System.out.println("Canhao: "+this.canhao.getPosition());
                if(tiroCanhao.getX() > 1){  
                    //System.out.println("Posicao: "+tiroCanhao.getPosition()+ " "+tela.getSprite(tiroCanhao.getX(), tiroCanhao.getY()));
                    if(tela.getSprite(tiroCanhao.getX()-1, tiroCanhao.getY()) == 'Y' ||
                       tela.getSprite(tiroCanhao.getX()-1, tiroCanhao.getY()) == 'N' ||
                       tela.getSprite(tiroCanhao.getX()-1, tiroCanhao.getY()) == 'W' ||
                       tela.getSprite(tiroCanhao.getX()-1, tiroCanhao.getY()) == 'E'){
                        //Tiro colidiu com uma nave 
                        //System.out.println("Colidiu com nave");
                        //tiroCanhao.getSprite().getImage().setVisible(false);
                        tiroCanhao.getSprite().getImage().setImage(null);
                        Nave nave = this.getNave(tiroCanhao.getX()-1, tiroCanhao.getY());
                        
                        if(nave != null){
                            nave.setSprite("X");
                            nave.getSprite().setImage("explosao.png");
                            //this.naves.remove(nave);
                        }else{
                            //System.out.println("Nave nula");
                        };
                        flagCanhaoAtirou = 0;                        
                        
                        this.pontuacao += 10;
                        if(tela.getSprite(tiroCanhao.getX(), tiroCanhao.getY()) == 'E'){
                            this.pontuacao +=40;
                        }
                    }else{
                        // Nao houve colisao
                        tiroCanhao.mover(-1, 0);
                        tela.insereTela(tiroCanhao);
                    }
                    
                }else{
                    System.out.println("Erro: Posicao do tiro é negativa");
                    flagCanhaoAtirou = 0;
                    //tiroCanhao.getSprite().getImage().setVisible(false);
                    tiroCanhao.getSprite().getImage().setImage(null);
                }
            }
            
            
            
            
            // Le  opcao do jogador
            //op = s.next().charAt(0);
            contReload++;
            switch (this.op[0]){
                case 'l':
                    if(canhao.getY() > 0)
                        this.canhao.mover(0, -1);
                        this.op[0] = 'a';
                    break;
                case 'r':
                    if(canhao.getY() <Tela.tamY - 1)
                    this.canhao.mover(0, 1);
                    this.op[0] = 'a';
                    break;
                case 'e':
                    //Garante que o canhao só dê um tiro de cada vez
                    if(flagCanhaoAtirou == 0){
                        this.canhao.atirar(tiroCanhao);   
                        flagCanhaoAtirou = 1;
                        tela.insereTela(tiroCanhao);
                    }          
                    this.op[0] = 'a';
                    break;  
                case 's':
                    break;
            }
            
            tela.imprimeTela();
            System.out.println("Vida canhao: "+this.canhao.getVida());
            System.out.println("Pontuação: "+this.pontuacao);
            
            KeyFrame kf= new KeyFrame(Duration.millis(100), new KeyValue(lbScore.textProperty(), String.valueOf(this.pontuacao)));
            Timeline t = new Timeline(kf);
            t.play();
               
            System.out.print("Estados base: ");
            this.bases.forEach(base -> {System.out.print(base.getEstado()+" ");});
            System.out.println("Num tiros: "+this.numTirosAliens);
            for(int i=0; i<5; i++){
                System.out.println(this.tirosAliens.get(i).getPosition());
            }
            
            try{
                TimeUnit.MILLISECONDS.sleep(450);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    }
    

}