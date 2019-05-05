public class Main {
    public static void main(String[] args) {
        Gestione gestioneFasi = new Gestione();
        do {

            gestioneFasi.fase1();//inizializzazione partita
            gestioneFasi.fase2();//gioco partita
            gestioneFasi.fase3();//stampa equilibrio e vincitore

        }while(gestioneFasi.scelta());//scelta nuova partita o uscita
        System.out.println("--- FASE DI GIOCO TERMINATA ---");
    }
}
