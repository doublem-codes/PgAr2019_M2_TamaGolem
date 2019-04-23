import java.util.ArrayList;

public class Gestione {
    private int maxElementi = 10;
    private int minElementi = 3;

    Partita partita = new Partita();

    public void newPartita(){
        String play1 = it.unibs.fp.mylib.InputDati.leggiStringa("inserire nome giocatore 1");
        String play2 = it.unibs.fp.mylib.InputDati.leggiStringa("inserire nome giocatore 2");

        int numeroElemnti = it.unibs.fp.mylib.InputDati.leggiIntero("inserire numero elementi",minElementi,maxElementi);
        ArrayList<String> nomeElemnti = new ArrayList<>();
        for (int index =0 ; index < numeroElemnti ; index++){
            String nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome dell'elemento numero");
        }
        
    }



    //fase 2
    //iterazioni tra i due giocatori
    //e tra i golem


    //fase 3
    // stampa del vincitore
    // stampa dell'equlibrio
    // scelta nuova partita


}
