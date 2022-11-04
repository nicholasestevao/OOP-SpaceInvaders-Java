package spaceinvaders.engine;
import spaceinvaders.elementos.Canhao;
import spaceinvaders.elementos.Nave;
import spaceinvaders.elementos.Base;
import spaceinvaders.elementos.Tiro;
import java.util.*;
import java.util.concurrent.TimeUnit;
import spaceinvaders.interfaceGrafica.Sprite;
import spaceinvaders.interfaceGrafica.Tela;

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
    private ArrayList<Tiro> tiros;

    /**
     * Construtor da classe Jogo:
     * Inicializa todas as entidades
     */
    public Jogo() {
        //Inicializar todos os objetos
        //Telo 800x600
        
        this.rodando = 0;
        this.pontuacao = 0;
        
        //Variaveis que controlam a direção do movimento das naves
        direcaoMovNaves = 1;
        navesDesceram = 1;
        
        //Inicializa as bases
        this.bases = new ArrayList<Base>();
        for(int i=0; i<4; i++){
            Base base = new Base(12,4*i+2, new Sprite("B"));
            this.bases.add(base);
        }
        
        //Inicializa canhao
        this.canhao = new Canhao(14,2, 3, new Sprite("C"));
        
        //Inicializa as naves
        this.naves = new ArrayList<Nave>();
        for(int i=0; i<5; i++){
            for(int j=0; j<11; j++){
                String s = "_";
                if(i==0){
                    s = "Y";
                }
                if(i==1 || i ==2){
                    s = "N";
                }
                if(i==3 || i ==4){
                    s = "W";
                }
                Nave ship = new Nave(i,j, new Sprite(s));
                this.naves.add(ship);
            }
        }
        
        //Inicializa os tiros
        this.tiros = new ArrayList<Tiro>();
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
    public void rodar(int delayAliens) {
        
        // Variaveis que leem opcao do jogador
        Scanner s = new Scanner(System.in);
        char op = 'f';
        
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
        Nave naveEspecial = new Nave(0,0, new Sprite("E"));
        
        /**
         * Flag que armazena se o canhao ja atirou
         */
        int flagCanhaoAtirou = 0;
        
        /**
         * Flag que armazena se os aliens ja aceleraram
         */
        int flagAcelerou = 0;
        
        //Inicializa tela
        Tela tela = new Tela();

        while(this.rodando == 1){
            //Imprime opcoes para o jogador
            System.out.println("Teclas:");
            System.out.println("Mover para esquerda -> a");
            System.out.println("Mover para direita -> d");
            System.out.println("Atirar -> s");
            System.out.println("Nenhuma ação -> f");
            
            //Insere bases na tela
            ArrayList<Integer> remover = new ArrayList<Integer>();
            this.bases.forEach( (base) ->{
                tela.insereTela(base);
                if(base.getSprite().getSprite() == "X"){                    
                    remover.add(this.bases.indexOf(base));
                }
            });
            //Remove bases marcadas com 'X' (explosao)
            remover.forEach(indice -> {this.bases.remove((int) indice);});
            remover.clear();
            
            // Insere canhao na tela
            if(canhao.getVida() > 0){
                tela.insereTela(canhao);
                if(canhao.getSprite().getSprite() == "X"){
                    canhao.setSprite("C");
                    // Volta canhao para posicao inicial
                    canhao.setX(14);
                    canhao.setY(2);
                }
            }else{
                // Fim da fase
                this.rodando = 0;
                System.out.println("Você perdeu todas as suas vidas :(");
                break;
            }
            
            //Move bloco de naves na velocidade determinada pelo delayAliens
            if(contReload%delayAliens == 0){
                this.moveNaves();
                 if(System.currentTimeMillis()%37 == 0 && flagNaveEspecial == 0){
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
                 }
            }
            if(flagNaveEspecial == 1){
                tela.insereTela(naveEspecial);
                if(System.currentTimeMillis()%3 == 0){
                    this.tiros.add(naveEspecial.atirar());
                }
                if(naveEspecial.getSprite().getSprite() == "X"){
                    flagNaveEspecial = 0;
                }
            }
            
            //Insere naves na tela
            this.naves.forEach( (nave) ->{
                //System.out.println("Nave em : "+nave.getX() + " "+nave.getY());
                tela.insereTela(nave);
                if(nave.getSprite().getSprite() == "X"){
                    //System.out.println("Removeu nave em "+nave.getX() + " "+nave.getY());
                    remover.add(this.naves.indexOf(nave));
                }
            });
            
            //Remove naves marcadas com X (explosao)
            remover.forEach(indice -> {  
                if(indice < this.naves.size()){
                    this.naves.remove((int) indice);}});
            remover.clear();

            //Verifica se alguma nave ja passou da linha do canhao (fim do jogo)
            this.naves.forEach(nave -> {
                if(nave.getX() >= this.canhao.getX()){
                    this.rodando = 0;                    
                }
            });

            if(this.rodando == 0){
                System.out.println("Você perdeu!");
                System.out.println("Os alienígenas te alcançaram :(");
                break;
            }
            if(this.naves.size() + flagNaveEspecial == 0){
                this.rodando = 0;  
                System.out.println("Parabéns! Você ganhou :)");
                break;
            }

            //De vez em quando uma nave da fileira do canhao atira
            if(System.currentTimeMillis()%7 == 0){
                ArrayList<Nave> navesFileiraCanhao = new ArrayList<Nave>();
                this.naves.forEach((nave)->{
                    if(nave.getY() == canhao.getY()){
                        navesFileiraCanhao.add(nave);
                    }
                });

                //Uma nave da fileira do canhao atira
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
            
            // Le  opcao do jogador
            op = s.next().charAt(0);
            contReload++;
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
                    //Garante que o canhao só dê um tiro de cada vez
                    if(flagCanhaoAtirou == 0){
                        this.tiros.add(this.canhao.atirar()); 
                        flagCanhaoAtirou = 1;
                    }                    
                    break;  
                case 'f':
                    break;
            }
            
            // Processa os tiros (move e verifica colisoes)
            this.tiros.forEach( tiro ->{ 
                //System.out.println("Tiro: "+tiro.getX() + " "+tiro.getY());
                //Se o tiro colidiu com um base
                if(tela.getSprite(tiro.getX(), tiro.getY()) == 'B'){
                    //Tiro colidiu com uma base  
                    this.bases.forEach((base)-> {
                       if(base.getX() == tiro.getX() && base.getY() == tiro.getY()){
                           base.reduzirEstado();
                           if(base.getEstado() == 0){
                               base.setSprite("X");
                               tela.insereTela(base);
                           }
                       }
                    });
                    remover.add(this.tiros.indexOf(tiro));
                }else{
                    // Tiro nao colidiu com uma base
                    if(tiro.getTipo() == 0){
                        //Se o tiro é do canhao
                        if(tiro.getX() > 1){
                            tiro.mover(-1, 0);
                            if(tela.getSprite(tiro.getX(), tiro.getY()) == 'Y' ||
                               tela.getSprite(tiro.getX(), tiro.getY()) == 'N' ||
                               tela.getSprite(tiro.getX(), tiro.getY()) == 'W' ||
                               tela.getSprite(tiro.getX(), tiro.getY()) == 'E'){
                                //Tiro colidiu com uma nave 
                                Nave nave = this.getNave(tiro.getX(), tiro.getY());
                                if(nave != null){nave.setSprite("X");};
                                remover.add(this.tiros.indexOf(tiro));
                                this.pontuacao += 10;
                                if(tela.getSprite(tiro.getX(), tiro.getY()) == 'E'){
                                    this.pontuacao +=40;
                                }
                            }
                        }else{
                            remover.add(this.tiros.indexOf(tiro));
                        }
                    }else{
                        //Se o tiro é da nave
                        if(tiro.getX() < Tela.tamX -1){
                            tiro.mover(1, 0);
                            char spritePosTiro = tela.getSprite(tiro.getX(), tiro.getY());
                            if(spritePosTiro == 'C'){
                                //Tiro colidiu com o canhao 
                                this.canhao.setSprite("X");
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
            
            //Remove tiros que colidiram com algo ou chegaram no fim da tela
            for(int i =0; i<remover.size(); i++){
                if(remover.get(i) < this.tiros.size()){
                    if(this.tiros.get(remover.get(i)).getTipo() == 0){
                        //Permite que o canhao atire de novo
                        flagCanhaoAtirou = 0;
                    }
                    this.tiros.remove((int) remover.get(i));
                }
            }                
            remover.clear();
            
            // Insere tiros na tela
            this.tiros.forEach(tiro -> {tela.insereTela(tiro);});
            
            tela.imprimeTela();
            //System.out.println("tem "+tiros.size() + " tiros, processou "+cont);
            System.out.println("Vida canhao: "+this.canhao.getVida());
            System.out.println("Pontuação: "+this.pontuacao);
            System.out.print("Estados base: ");
            this.bases.forEach(base -> {System.out.print(base.getEstado()+" ");});
            System.out.println("");
            
            /*try{
                TimeUnit.MILLISECONDS.sleep(500);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }*/
            
        }
    }
    

}