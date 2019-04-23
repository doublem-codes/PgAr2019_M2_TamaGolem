import java.util.ArrayList;

public class Gestione {

    private static int maxElementi = 10;
    private static int minElementi = 3;




    Partita partita = new Partita();

    public void fase1(){

        System.out.println("---INIZIALIZZAZIONE PARTITA ---");
        String play1 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 1:");// inserimento nome giocatore 1
        String play2 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 2:");// inserimento nome giocatore 2
        System.out.println("inserire numero di elementi compreso tra  "+ minElementi +"  e  "+ maxElementi);
        int numeroElementi = it.unibs.fp.mylib.InputDati.leggiIntero("inserire numero:",minElementi,maxElementi);// inserimento numero elementi compreso tra min e max elementi
        ArrayList<String> nomeElementi = new ArrayList<>();
        for (int index =0 ; index < numeroElementi ; index++){//controllo sugli elementi inseriti che siano tutti diversi
            String nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome dell'elemento numero:");
            for (int j = 0 ; j < nomeElementi.size(); j++){
                if(nome.equals(nomeElementi.get(j))){
                    nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("rinserire nome dell' ultimo elemento perche duplicato: ");
                    j = -1;
                }
            }
            nomeElementi.add(nome);
        }

        System.out.println("---GENERAZIONE PARTITA ---");
        partita.SetPartita(numeroElementi,nomeElementi,play1,play2);

        System.out.println("---GENERAZIONE EQUILIBRIO ---");
    }


    //fase 2
    //iterazioni tra i due giocatori
    //e tra i golem
    public void fase2(){};

    // stampa dell'equlibrio
    public void fase3(String nome){
        System.out.println("\n! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !");
        System.out.println("\t\t\t\t\til vincitore è " + nome.toUpperCase() );
        System.out.println("! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !\n");
        System.out.println("equilibrio di gioco della partita giocata");
        //stampa equilibrio
    }

    public boolean finale() {
        char input;
        do{
            System.out.println("inserire carattere \n [E] : se si vuole uscire \n [N] : se si vuole inziare una nuova partita ");
            input = it.unibs.fp.mylib.InputDati.leggiChar("inserire");

        }while(input != 'E' &&  input != 'N');
        if (input =='E' ) return false;
        return true;
    }
}
