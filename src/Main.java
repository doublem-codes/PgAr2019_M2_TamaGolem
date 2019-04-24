public class Main {
    public static void main(String[] args) {

        Equilibrio equilibrio = new Equilibrio();

        equilibrio.generaEquilibrio();

        Gestione gestioneFasi = new Gestione();
        do {
            gestioneFasi.fase1();
            gestioneFasi.fase2();
            gestioneFasi.fase3("daf");
        }while(gestioneFasi.finale());

System.out.print(" ");

    }
}
