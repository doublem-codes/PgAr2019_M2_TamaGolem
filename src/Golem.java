import java.util.ArrayList;

public class Golem {
    private int vita;  // a scelta meglio se V = sup di W;
    private ArrayList<String> pietre = new ArrayList<>();
    public ArrayList<String> getPietre() {
        return pietre;
    }
    public void setPietre(ArrayList<String> pietre) {
        this.pietre = pietre;
    }
    public int getVita() {
        return vita;
    }
    public void setVita(int vita) {
        this.vita = vita;
    }

}
