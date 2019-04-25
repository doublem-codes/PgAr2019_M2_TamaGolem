public class Main {
    public static void main(String[] args) {
        Gestione gestioneFasi = new Gestione();
        Equilibrio equilibrio = new Equilibrio();
        equilibrio.generaEquilibrio();
        do {
            gestioneFasi.fase1();
            gestioneFasi.fase2();
            gestioneFasi.fase3("daf");
        }while(gestioneFasi.finale());

System.out.print(" ");

    }
}
