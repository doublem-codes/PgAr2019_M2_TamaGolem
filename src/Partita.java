import java.util.ArrayList;
import java.lang.*;
public class Partita {

    private int numeroElementi;  // min 3 max meglio 10
    private int numeroGolem;
    private int numeroPietreGolem;
    private int numeroPietreSacca;
    private int numeroPietreTipoSacca;
    private int vitaset;//da implementare

    private String player1;
    private String player2;
    private ArrayList<Golem> player1Golem ;
    private ArrayList<Golem> player2Golem ;
    private ArrayList<String> sacca ;
    private ArrayList<String> nomeElementi ;

    public Partita(int numeroElementi , ArrayList<String> nomeElementi, String player1, String player2, int vita){
        this.player1 = player1;
        this.player2 = player2;
        this.numeroElementi = numeroElementi;
        this.nomeElementi = nomeElementi;
        this.vitaset = vita;
        this.numeroPietreGolem = setNumeroPietreGolem();
        this.numeroGolem = setNumeroGolem();
        this.numeroPietreSacca = setNumeroPietreSacca();
        this.numeroPietreTipoSacca =setNumeroPietreTipoSacca() ;
        this.sacca = setSacca();
        this.player1Golem = setPlayerGolem(numeroGolem);
        this.player2Golem = setPlayerGolem(numeroGolem);
    }

    private int setNumeroGolem() {
        return((int)Math.ceil((double)((this.numeroElementi - 1) * (this.numeroElementi - 2)) / (2 * (this.numeroPietreGolem))));
    }

    private int setNumeroPietreSacca() {

       return (int)((Math.ceil((((double) (2 * this.numeroGolem * this.numeroPietreGolem))/(double)this.numeroElementi)))*(this.numeroElementi));
    }

    private int setNumeroPietreTipoSacca() {
        return(this. numeroPietreSacca / this.numeroElementi);
    }

    private ArrayList<Golem> setPlayerGolem(int numeroGolem) {
        ArrayList<Golem> playerGolem = new ArrayList<>();
        for (int i = 0; i < numeroGolem ; i++){
            Golem golem = new Golem();
            golem.setVita(this.vitaset);
            playerGolem.add(golem);
        }
        return playerGolem;
    }

    private ArrayList<String> setSacca() {
        ArrayList<String> sacca = new ArrayList<>(this.numeroPietreSacca);
        for (int i = 0; i < nomeElementi.size(); i++) {
            String elemento = this.nomeElementi.get(i);
            for (int j = 0; j < this.numeroPietreTipoSacca; j++) {
                sacca.add(elemento);
            }
        }
        return sacca;
    }

    public int setNumeroPietreGolem() {
        return (int)Math.ceil((((double) this.numeroElementi+1)/3)) + 1;
    }
    public int getNumeroGolem() {
        return numeroGolem;
    }
    public int getNumeroPietreGolem() {
        return numeroPietreGolem;
    }

    public String getPlayer1() {
        return player1;
    }
    public String getPlayer2() {
        return player2;
    }
    public ArrayList<Golem> getPlayer1Golem() {
        return player1Golem;
    }
    public ArrayList<Golem> getPlayer2Golem() {
        return player2Golem;
    }
    public ArrayList<String> getSacca() {
        return sacca;
    }
    public ArrayList<String> getNomeElementi() {
        return nomeElementi;
    }

    public int getVitaset() {
        return vitaset;
    }

    public int getIndiceSacca(String elemento){
        int index = 0;
        for (String elementoSacca : this.sacca){
            if(elementoSacca.equals(elemento)){
                return index;
            }
            index++;
        }
        return 0;
    }
}
