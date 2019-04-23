import java.util.ArrayList;
public class Partita {

    private int numeroElementi;  // min 3 max meglio 10
    private int numeroGolem;
    private int numeroPietre;
    private int numeroPietreSacca;
    private int numeroPietreTipoSacca;


    private String player1;
    ArrayList<Golem> player1Golem = new ArrayList<>();

    private String player2;
    ArrayList<Golem> player2Golem = new ArrayList<>();

    ArrayList<String> sacca = new ArrayList<>();
    ArrayList<String> nomeElementi = new ArrayList<>();


    public void SetPartita(int numeroElementi , ArrayList<String> nomeElementi,String player1,String player2){
        this.player1 = player1;
        this.player2 = player2;
        this.numeroElementi = numeroElementi;
        this.numeroPietre = ((numeroElementi+1)/3) + 1;
        this.numeroGolem = ((numeroElementi - 1) * (numeroElementi - 2)) / (2 * (numeroPietre));
        this.numeroPietreSacca = ((2 * numeroGolem * numeroPietre)/numeroElementi)*(numeroElementi);
        this.numeroPietreTipoSacca = (numeroPietreSacca / numeroPietre);
        this.nomeElementi = nomeElementi;
        this.sacca = setSacca();

    }

    private ArrayList<String> setSacca(){
        ArrayList<String> partial = new ArrayList<>();
        for(int index = 0 ; index <this.numeroPietreTipoSacca; index++){
            partial.add(nomeElementi.get(0));
        }
        return  partial;
    }
}
