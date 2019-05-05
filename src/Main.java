
public class Main {
    public static void main(String[] args) {

        Gestione gestioneFasi = new Gestione();

        do {

            gestioneFasi.fase1();
            gestioneFasi.fase2();
            gestioneFasi.fase3();

        }while(gestioneFasi.scelta());

        System.out.println("--- FASE DI GIOCO TERMINATA ---");
    }
}
