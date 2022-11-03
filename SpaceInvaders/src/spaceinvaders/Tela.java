
package spaceinvaders;

public class Tela {
    private char[][] tela;
    public static final int tamX = 16;
    public static final int tamY= 16;
    
    public Tela(){
        this.tela = new char[tamX][tamY];
        this.limpaTela();
    }
    
    public void insereTela(Entidade ent){
        if(ent.getY() == 17){
            System.out.println(" oi");
            System.out.println(ent.getSprite().getSprite());
        }
        tela[ent.getX()][ent.getY()] = ent.getSprite().getSprite().charAt(0);
    }
    
    public void imprimeTela(){
        for(int i=0; i< tamY; i++){
            for(int j=0; j< tamX; j++){
                System.out.print(" "+tela[i][j]+" ");
            }
            System.out.println(" ");
        }
        this.limpaTela();
        
    }
    
    public void limpaTela(){
        for(int i=0; i< tamY; i++){
            for(int j=0; j< tamX; j++){
                this.tela[j][i] = '_';
            }
        }
    }
    
    public char getSprite(int x, int y){
        return tela[x][y];
    }
}
