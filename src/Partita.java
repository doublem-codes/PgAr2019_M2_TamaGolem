import java.util.ArrayList;
public class Partita {

    private int numeroElementi;  // min 3 max meglio 10
    private int numeroGolem;
    private int numeroPietreGolem;
    private int numeroPietreSacca;
    private int numeroPietreTipoSacca;
    private int vitaset;//da implementare
    private String player1;
    private String player2;
    private ArrayList<Golem> player1Golem = new ArrayList<>();
    private ArrayList<Golem> player2Golem = new ArrayList<>();
    private ArrayList<String> sacca = new ArrayList<>();
    private ArrayList<String> nomeElementi = new ArrayList<>();

    public Partita(){

    }

    public Partita(int numeroElementi , ArrayList<String> nomeElementi, String player1, String player2, int vita){
        this.player1 = player1;
        this.player2 = player2;
        this.numeroElementi = numeroElementi;
        this.nomeElementi = nomeElementi;
        this.vitaset = vita;
        this.numeroPietreGolem = setNumeroPietreGolem();
        this.numeroGolem = setNumeroGolem();//errore
        this.numeroPietreSacca = setNumeroPietreSacca();
        this.numeroPietreTipoSacca =setNumeroPietreTipoSacca() ;
        this.sacca = setSacca();
        this.player1Golem = setPlayerGolem(numeroGolem);
        this.player2Golem = setPlayerGolem(numeroGolem);
    }

    private int setNumeroGolem() {
        if (numeroElementi==3) return 1;
        return((this.numeroElementi - 1) * (this.numeroElementi - 2)) / (2 * (this.numeroPietreGolem));
    }

    private int setNumeroPietreSacca() {
       return ((2 * this.numeroGolem * this.numeroPietreGolem)/this.numeroElementi)*(this.numeroElementi);
    }

    private int setNumeroPietreTipoSacca() {
        return(this. numeroPietreSacca / this.numeroPietreGolem);
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

    public void setGolemPietre(ArrayList<String> pietre,int index,boolean isOne){
        if (isOne)
        {
            player1Golem.get(index).setPietre(pietre);
        }else {
            player2Golem.get(index).setPietre(pietre);
        }
    }


    public int setNumeroPietreGolem() {
        return  ((this.numeroElementi+1)/3) + 1;
    }


    public int getNumeroElementi() {
        return numeroElementi;
    }
    public int getNumeroGolem() {
        return numeroGolem;
    }
    public int getNumeroPietreGolem() {
        return numeroPietreGolem;
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





}
