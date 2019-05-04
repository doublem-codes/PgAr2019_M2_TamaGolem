import java.util.ArrayList;

public class Gestione {

    //inserimento stringhe di comunicazione
    private static String init_par = "\n--- INIZIALIZZAZIONE PARTITA ---";
    private static String gen_par = "\n--- GENERAZIONE PARTITA ---";
    private static String gen_eql = "\n--- GENERAZIONE EQUILIBRIO ---";
    private static String start_par = "\n--- INIZIO PARTITA ---";
    private static String end_par = "\n--- FINE PARTITA ---";
    private static String eql_par = "\n--- EQUILIBRIO PARTITA GIOCATA ---";
    private static String str_scelta = " SCEGLERE PIETRE DEL GOLEM SCHIERATO \n LE PIETRE A DISPOSIZIONE SONO ";
    private static String str_giocatore = "GIOCATORE ";
    private static String str_rinscelta = " RINSERIRE PIETRE DEl GOLEM SCHIERATO ";
    private static String menu_numero_elementi ="\n--- INSERIMENTO NUMERO PIETRE --- " +
                                                "\n- SE TRA 3 E 5: DIFFICOLTA FACILE" +
                                                "\n- SE TRA 6 E 8: DIFFICOLTA MEDIA" +
                                                "\n- SE TRA 9 E 10:DIFFICOLTA DIFFICLE" +
                                                "\n- !!! INSERIRE NUMERO MAGGORE DI 3 !!!"+
                                                "\n- !!! SE MAGGIORE DI 10 SI POTREBBE COMPROMETTERE IL GIOCO !!!";

    private static String err_p = "--- ERRORE DI INSERIMENTO PIETRE PERCHE' SONO UGUALI TRA I DUE GOLEM ---" +
                                  "\n--- RICARICA PIETRE NELLA SACCA ---";

    private static String abb_text="\n! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !\n";
    private static String abb_capo = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    //inserimento caratteri escape del loop del gioco
    private static char charUscita = 'E';//carattere uscita
    private static char charNuovo = 'N';//carattere nuova partita

    private static int minElementi = 3;

    Partita partita ;
    Equilibrio equilibrio = new Equilibrio();

    private String vincitore = "";
    private String perdente = "";

    /**
     * fase1
     * gestone della creazione di una nuova partita
     * tramite aquisizione dei dati giocatori e del numero di elementi per giocare
     * inoltre si genera l'equilibrio della partita
     */
    public void fase1(){
        System.out.println(init_par);
        String play1 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 1: ");// inserimento nome giocatore 1
        String play2 = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome giocatore 2: ");// inserimento nome giocatore 2
        System.out.println(menu_numero_elementi);

        int numeroElementi= it.unibs.fp.mylib.InputDati.leggiIntero("inserire: ");// inserimento numero elementi;
        while (numeroElementi < minElementi) {//controllo che il numero di elemneti non sia minore del numero minimo
            numeroElementi = it.unibs.fp.mylib.InputDati.leggiIntero("rinserire: ");// rinserimento numero elementi;
        }

        ArrayList<String> nomeElementi = new ArrayList<>();
        for (int index =0 ; index < numeroElementi ; index++){//aquisizione arraylist di elementi
            String nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome dell'elemento numero "+index+" : "); //inserimento di un elemento
            for (int j = 0 ; j < nomeElementi.size(); j++){//controllo sugli elementi inseriti che siano tutti diversi
                if(nome.equals(nomeElementi.get(j))){//controllo che non ci sia nella lista
                    System.out.println("inserire nuovamente elemento " + index +" perchè duplicato");
                    nome = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("rinserire: ");//rinserimento elemto
                    j = -1;//riscorro l'arraylist
                }
            }
            nomeElementi.add(nome);//essendo elemnto diverso lo inserisco nell'arraylist
        }

        System.out.println(gen_eql);
        equilibrio.generaEquilibrio(numeroElementi);//generazione equilibrio
        System.out.println(gen_par);
        int vita = equilibrio.cercaMassimoMatrice();//estrazione supW per set della vita
        partita = new Partita(numeroElementi,nomeElementi,play1,play2,vita);//generazione partita
    }

    /**
     * fase2
     * metodo per il controllo flusso di gioco
     */

    public void fase2(){
        System.out.println(start_par);//inizio partita
        int golem1=0 , golem2=0;
        String stmapaScelta = str_scelta;
        do {
            System.out.println(str_giocatore+partita.getPlayer1() + stmapaScelta);
            partita.getPlayer1Golem().get(golem1).setPietre(acquisiciElementiGolem());
            System.out.println(abb_capo);
            System.out.println(str_giocatore+partita.getPlayer2() + stmapaScelta);
            partita.getPlayer2Golem().get(golem2).setPietre(acquisiciElementiGolem());
            stmapaScelta = str_rinscelta;
        }while (controlloElementiGiocatori(golem1,golem2,true));

        int danno1Tot=0;
        int danno2Tot=0;
        int vita1 = partita.getPlayer1Golem().get(golem1).getVita();
        int vita2 = partita.getPlayer2Golem().get(golem2).getVita();
        do {
            int pietre = 0;
            do {
                int pietra1 =0;
                for (int i = 0;i<partita.getNomeElementi().size();i++){
                    if(partita.getPlayer1Golem().get(golem1).getPietre().get(pietre).equals(partita.getNomeElementi().get(i))) break;
                    pietra1++;
                }
                int pietra2 =0;
                for (int i = 0;i<partita.getNomeElementi().size();i++){
                    if(partita.getPlayer2Golem().get(golem2).getPietre().get(pietre).equals(partita.getNomeElementi().get(i))) break;
                    pietra2++;
                }

                // - danno a 1
                // + danno a 2


                int danno = equilibrio.danni(pietra1,pietra2);

                if (danno>0){
                    vita2 -= danno;
                    danno1Tot += danno;
                }else{
                    vita1 -= danno;
                    danno = -danno;
                    danno2Tot += danno;

                }

                partita.getPlayer1Golem().get(golem1).setVita(vita1);
                partita.getPlayer2Golem().get(golem2).setVita(vita2);

                pietre++;
                if(pietre == partita.getNumeroPietreGolem() ) pietre = 0;
            } while (partita.getPlayer1Golem().get(golem1).getVita() >= 0 && partita.getPlayer2Golem().get(golem2).getVita() >= 0);

            if (partita.getPlayer1Golem().get(golem1).getVita() <= 0)
            {
                if (golem1 == partita.getNumeroGolem()-1)//asseganzione vincitore
                {
                    vincitore = partita.getPlayer2();
                    perdente = partita.getPlayer1();
                    break;
                }

                System.out.println("DANNI ARRECATI AL GOLEM DEL GIOCATORE "+ partita.getPlayer2()+ " SONO "+ danno1Tot);
                System.out.println("VITA RIMASTA AL GOLEN " +golem1+" è: " +partita.getPlayer1Golem().get(golem1).getVita());
                //creazione e set di un nuovo golem
                danno1Tot = 0;
                golem1++;
                stmapaScelta = str_scelta;
                do {
                    System.out.println(partita.getPlayer1() + stmapaScelta);
                    partita.getPlayer1Golem().get(golem1).setPietre(acquisiciElementiGolem());
                    stmapaScelta = str_rinscelta;
                }while (controlloElementiGiocatori(golem1, golem2,false));
                vita1 = partita.getPlayer1Golem().get(golem1).getVita();

            }

            if(partita.getPlayer2Golem().get(golem2).getVita() <= 0)
            {
                if(golem2 == partita.getNumeroGolem()-1)//asseganzione vincitore
                {
                    perdente = partita.getPlayer2();
                    vincitore = partita.getPlayer1();
                    break;
                }
                System.out.println("DANNI ARRECATI AL GOLEM GIOCATORE "+ partita.getPlayer1()+ " SONO " + danno2Tot);
                System.out.println("VITA RIMASTA AL GOLEm " +golem2+" è: " +partita.getPlayer2Golem().get(golem2).getVita());
                //creazione e set di un nuovo golem
                golem2++;
                danno2Tot = 0;
                stmapaScelta = str_scelta;
                do {
                    System.out.println(partita.getPlayer2() + stmapaScelta);
                    partita.getPlayer2Golem().get(golem2).setPietre(acquisiciElementiGolem());
                    stmapaScelta = str_rinscelta;
                }while (controlloElementiGiocatori(golem1, golem2,false));
                vita2 = partita.getPlayer2Golem().get(golem2).getVita();
            }
        }while(true);

        System.out.print(end_par);//fine partita
    }

    public void fase3(){//stamoa a video del vincitore
        System.out.println(abb_text);
        System.out.println("\t\t\t\t\t IL VINCITORE E' " + vincitore);//stampa vincitore
        System.out.println(abb_text);
        System.out.println("\t\t\t\t\t IL PERDENTE E' " + perdente);//stampa perdente
        System.out.println(abb_text);
        System.out.println(eql_par);
        System.out.println("--- STAMPA INDICI ELEMENTI PER DECODIFICA TABELLA ---");
        System.out.println("--- TABELLA EQUILIBRIO DI GIOCO ---");
        equilibrio.printMatrix(partita.getNomeElementi());//stampa a video dell'equilibrio di gioco_________________!!!!!!!!!!!!!!!!!!!!
    }

    public boolean scelta() {

        System.out.println("inserire carattere \n [ " +charUscita+" ] : se si vuole uscire \n [ "+charNuovo+" ] : se si vuole inziare una nuova partita ");
        char input = it.unibs.fp.mylib.InputDati.leggiChar("inserire");
        while(input != charUscita &&  input != charNuovo){//controllo dato in input
            input = it.unibs.fp.mylib.InputDati.leggiChar("rinserire");//rinserimento dato
        }
        return input != charUscita;
    }

    private void stampaElementi(){
        for(int i = 0 ; i<partita.getNomeElementi().size();i++){
            System.out.println("elemento " + (i+1) + " : " + partita.getNomeElementi().get(i));
        }
    }

    /**
     * metodo utilizzato per stampare gli elementi della sacca
     * divisi per elemnti con cancolo di quanti c'è ne sono
     * @param sacca passaggio sacca partita
     */
    private void stampaSacca(ArrayList<String> sacca){
        if(sacca.size()==0){//caso sacca vuota
            System.out.println("SACCA VUOTA");
            return;
        }//_____________________________________________________________________________________________________________
        String elmentoSacca = sacca.get(0);//lettura prima elemento sacca
        int numeroRestante = 1;
        for(int i = 1; i<sacca.size();i++){//ciclo che calcola in numero di elemnti restanti
            if (elmentoSacca.equals(sacca.get(i))){
                numeroRestante++;
            }else{//se diverso inizializzo il conto degli elenti
                System.out.println(elmentoSacca +": "+ numeroRestante);
                numeroRestante = 1;
                elmentoSacca = sacca.get(i);
            }
        }
        System.out.println(elmentoSacca +": "+ numeroRestante);
    }

    /**
     * metodo che evita loop infinito in caso di scelta di pietre uguali tra i due giocatori
     * in caso siano uguali si procedere a rimeterre le pietre nella sacca
     *
     * @param numGolem1 passaggio indice golem del primo giocatore
     * @param numGolem2 passaggio indice golem del secondo giocatore
     * @param isDue utilizzato per riempire la sacca
     * @return true se sono uguli false se sono diversi
     */
    private boolean controlloElementiGiocatori(int numGolem1,int numGolem2,boolean isDue){
        int conta = 0;
        for (int i = 0; i <partita.getNumeroPietreGolem();i++ ){//conteggio elemnti uguali
            if(partita.getPlayer1Golem().get(numGolem1).getPietre().get(i).equals(partita.getPlayer2Golem().get(numGolem2).getPietre().get(i))){
                conta++;
            }
        }
        if(conta == partita.getNumeroPietreGolem()){//se il numero di elenti uguali e al numeo dele pietre dei golem allora rinserisco gli elementi nella sacca
            System.out.println(err_p);
            if(isDue){
                for(int k = 0; k < partita.getNumeroPietreGolem(); k++)//rinserimento elementi
                {
                    int indice =partita.getIndiceSacca(partita.getPlayer1Golem().get(numGolem1).getPietre().get(k));//estrazione indice elemnto uguale se non c'è si aggiunge in testa
                    partita.getSacca().add(indice,partita.getPlayer1Golem().get(numGolem1).getPietre().get(k));
                    partita.getSacca().add(indice,partita.getPlayer2Golem().get(numGolem2).getPietre().get(k));
                }
            }else{//basta uno dei due essendo le pietre uguali
                for(int k=0; k < partita.getNumeroPietreGolem();k++)//rinserimento elementi
                {
                    int indice =partita.getIndiceSacca(partita.getPlayer1Golem().get(numGolem1).getPietre().get(k));//estrazione indice elemnto uguale se non c'è si aggiunge in testa
                    partita.getSacca().add(indice,partita.getPlayer1Golem().get(numGolem1).getPietre().get(k));
                }
            }
            return true;
        }
        return false;
    }


    private ArrayList<String> acquisiciElementiGolem(){
        ArrayList<String>elementi = new ArrayList<>();
        do {
            stampaSacca(partita.getSacca());
            String elemento;
            boolean isFind=true;
            do {
                elemento = it.unibs.fp.mylib.InputDati.leggiStringaNonVuota("inserire nome pietra numero" + (elementi.size() + 1));
                for (String nomeElemento :partita.getNomeElementi()) {
                    if(elemento.equals(nomeElemento)) isFind = false;
                }
            }while (isFind);
            int i;
            for (i = 0; i < partita.getSacca().size(); i++) {
                if (elemento.equals(partita.getSacca().get(i))) {
                    elementi.add(elemento);
                    break;
                }
            }
            partita.getSacca().remove(i);
        }while(elementi.size() != partita.getNumeroPietreGolem());
        return elementi;
    }
}