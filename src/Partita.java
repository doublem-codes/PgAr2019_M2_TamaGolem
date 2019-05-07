import java.util.ArrayList;
import java.lang.*;

/**
 * classe che contiene tutti i dati della partita a parte l'equilibrio che ha una classse apposita
 */

public class Partita {

    private int numeroElementi;  // min 3 max meglio se 10
    private int numeroGolem; //numero golem per giocatore
    private int numeroPietreGolem;//numero di pietre per ogni goem
    private int numeroPietreSacca;//pietre presenti nella sacca
    private int numeroPietreTipoSacca;//numero di pietre perogni elemnto
    private int vitaset;//uguale al supW dell'equilibrio

    private String player1;//nome giocatore1
    private String player2;//nome giocatore2

    private ArrayList<Golem> player1Golem ;//arraylist golem giocatore1
    private ArrayList<Golem> player2Golem ;//arraylist golem giocatore2
    private ArrayList<String> sacca ;//arraylist sacca
    private ArrayList<String> nomeElementi ;//arraylist nome elementi

    /**
     * metodo per generazione partita
     * @param numeroElementi numero degli elemnti
     * @param nomeElementi nome degli elemti
     * @param player1 nome giocatore 1
     * @param player2 nome giocatore
     * @param vita vita dei golem
     */
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

    /**
     * metodo per la generazione dei numero di golem
     * @return numero di golem per giocatore
     */
    private int setNumeroGolem() {
        return((int)Math.ceil((double)((this.numeroElementi - 1) * (this.numeroElementi - 2)) / (2 * (this.numeroPietreGolem))));
    }

    /**
     * metodo per la generazione del numero dipietre componenti la sacca
     * @return numero pietre della sacca
     */
    private int setNumeroPietreSacca() {

       return (int)((Math.ceil((((double) (2 * this.numeroGolem * this.numeroPietreGolem))/(double)this.numeroElementi)))*(this.numeroElementi));
    }

    /**
     * metodo per la generazione del numero di  pietre per elememto sacca
     * @return numero di  pietre per elememto sacca
     */
    private int setNumeroPietreTipoSacca() {
        return(this. numeroPietreSacca / this.numeroElementi);
    }

    /**
     * metodo che inizializza i golem dei giocatori
     * @param numeroGolem numero di golem
     * @return array list dei golem
     */
    private ArrayList<Golem> setPlayerGolem(int numeroGolem) {
        ArrayList<Golem> playerGolem = new ArrayList<>();
        for (int i = 0; i < numeroGolem ; i++){
            Golem golem = new Golem();
            golem.setVita(this.vitaset);
            playerGolem.add(golem);
        }
        return playerGolem;
    }

    /**
     * riemipi sacca con gli elmenti con relativa molteplicità
     * @return sacca composta
     */
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

    /**
     * metodo per la generazione dei numero di pietre per ogni golem
     * @return numero di pietre per ogni golem
     */
    public int setNumeroPietreGolem() {
        return (int)Math.ceil((((double) this.numeroElementi+1)/3)) + 1;
    }

    /**
     * metodo per sapere il numero di golem dei giocatori
     * @return numero golem
     */
    public int getNumeroGolem() {
        return numeroGolem;
    }

    /**
     * metodo per sapere il numero di pietre di ogni golem
     * @return numero Pietre Golem
     */
    public int getNumeroPietreGolem() {
        return numeroPietreGolem;
    }

    /**
     * metodo per ottenere il nome del giocatore 1
     * @return nome del giocatore 1
     */
    public String getPlayer1() {
        return player1;
    }

    /**
     * metodo per ottenere il nome del giocatore 2
     * @return nome del giocatore 2
     */
    public String getPlayer2() {
        return player2;
    }

    /**
     * metodo per ottenere i golem del giocatore 1
     * @return golem del giocatore 1
     */
    public ArrayList<Golem> getPlayer1Golem() {
        return player1Golem;
    }

    /**
     * metodo per ottenere i golem del giocatore 2
     * @return golem del giocatore 2
     */
    public ArrayList<Golem> getPlayer2Golem() {
        return player2Golem;
    }

    /**
     * metodo per ottenere la sacca degli elementi
     * @return sacca degli elementi
     */
    public ArrayList<String> getSacca() {
        return sacca;
    }

    /**
     * metodo per ricevere l'arraylist dei nomi degli elementi
     * @return arraylist dei nomi degli elementi
     */
    public ArrayList<String> getNomeElementi() {
        return nomeElementi;
    }

    /**
     * metodo per ottenre vita golem
     * @return vita per set golem
     */
    public int getVitaset() {
        return vitaset;
    }

    /**
     *
     * @param elementopass nome elmento
     * @return posizione nella arrayelemento dove si trova l'elemento
     */
    public int getIndiceElemento(String elementopass){
        int index = 0;
        for (String elemento : this.nomeElementi){
            if(elementopass.equals(elemento)){
                return index;
            }
            index++;
        }
        return 0;
    }

    /**
     *
     * @param elemento nome elmento
     * @return posizione nella sacca dove si trova l'elemento
     */
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


    /**
     * funzione get numero elementi
     * @return numero di elemnti
     */
    public int getNumeroElementi() {
        return numeroElementi;
    }
}
