import java.util.ArrayList;
public class Gestione {

    private static String init_par = "---INIZIALIZZAZIONE PARTITA ---";
    private static String gen_par = "--- GENERAZIONE PARTITA ---";
    private static String gen_eql = "--- GENERAZIONE EQUILIBRIO ---";
    private static String start_par = "--- INIZIO PARTITA ---";
    private static String end_par = "--- FINE PARTITA ---";
    private static String eql_par = "--- EQUILIBRIO PARTITA GIOCATA ---";
    private static String abb_text="\n! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !\n";


    private static char charExit = 'E';
    private static char charNew = 'N';

    private static int maxElementi = 10;
    private static int minElementi = 3;

    Partita partita = new Partita();
    Equilibrio equilibrio = new Equilibrio();

    public void fase1(){
        System.out.println(init_par);
        String play1 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 1:");// inserimento nome giocatore 1
        String play2 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 2:");// inserimento nome giocatore 2
        System.out.println("inserire numero di elementi del gioco compreso tra  "+ minElementi +"  e  "+ maxElementi);

        int numeroElementi = it.unibs.fp.mylib.InputDati.leggiIntero("inserire:",minElementi,maxElementi);// inserimento numero elementi compreso tra min e max elementi
        ArrayList<String> nomeElementi = new ArrayList<>();
        for (int index =0 ; index < numeroElementi ; index++){//controllo sugli elementi inseriti che siano tutti diversi
            String nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome dell'elemento numero "+index+" : ");
            for (int j = 0 ; j < nomeElementi.size(); j++){
                if(nome.equals(nomeElementi.get(j))){
                    System.out.println("inserire nuovamente elemento " + index +" perchè duplicato");
                    nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("rinserire: ");
                    j = -1;
                }
            }
            nomeElementi.add(nome);
        }

        System.out.println(gen_par);
        partita.SetPartita(numeroElementi,nomeElementi,play1,play2);
        System.out.println(gen_eql);
       // equilibrio.generaEquilibrio(numeroElementi);
    }


    //fase 2
    //iterazioni tra i due giocatori
    //e tra i golem
    public void fase2(){
        System.out.println(start_par);

        //gestione partita

        System.out.print(end_par);
    }

    // stampa dell'equlibrio
    public void fase3(String nome){
        System.out.println(abb_text);
        System.out.println("\t\t\t\t\til vincitore è " + nome.toUpperCase() );
        System.out.println(abb_text);
        //equilibrio.printMatrix();
        System.out.println(eql_par);
    }

    public boolean finale() {
        char input;
        System.out.println("inserire carattere \n [ " +charExit+" ] : se si vuole uscire \n [ "+charNew+" ] : se si vuole inziare una nuova partita ");
        do{
            input = it.unibs.fp.mylib.InputDati.leggiChar("inserire");
        }while(input != charExit &&  input != charNew);
        if (input == charExit ) return false;
        return true;
    }

}
