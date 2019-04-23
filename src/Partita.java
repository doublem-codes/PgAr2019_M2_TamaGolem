import java.util.ArrayList;
public class Partita {

    private int numeroElementi;  // min 3 max meglio 10
    private int numeroGolem;
    private int numeroPietre;
    private int numeroPietreSacca;
    private int numeroPietreTipoSacca;

    private String player1;
    private String player2;
    private ArrayList<Golem> player1Golem = new ArrayList<>();
    private ArrayList<Golem> player2Golem = new ArrayList<>();
    private ArrayList<String> sacca = new ArrayList<>();
    private ArrayList<String> nomeElementi = new ArrayList<>();

    public void SetPartita(int numeroElementi , ArrayList<String> nomeElementi, String player1, String player2){
        this.player1 = player1;
        this.player2 = player2;
        this.numeroElementi = numeroElementi;
        this.numeroPietre = ((numeroElementi+1)/3) + 1;
        this.numeroGolem = ((numeroElementi - 1) * (numeroElementi - 2)) / (2 * (numeroPietre));
        this.numeroPietreSacca = ((2 * numeroGolem * numeroPietre)/numeroElementi)*(numeroElementi);
        this.numeroPietreTipoSacca = (numeroPietreSacca / numeroPietre);
        this.nomeElementi = nomeElementi;
        //this.sacca = setSacca();
        //this.player1Golem = setGolem();
        //this.player2Golem = setGolem();
    }

    public int getNumeroElementi() {
        return numeroElementi;
    }
    public int getNumeroGolem() {
        return numeroGolem;
    }
    public int getNumeroPietre() {
        return numeroPietre;
    }
    public int getNumeroPietreSacca() {
        return numeroPietreSacca;
    }
    public int getNumeroPietreTipoSacca() {
        return numeroPietreTipoSacca;
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

    public void setSacca(ArrayList<String> sacca) {
        this.sacca = sacca;
    }

}
