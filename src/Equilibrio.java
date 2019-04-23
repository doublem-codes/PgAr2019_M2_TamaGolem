import java.util.Random;


public class Equilibrio {
    Random random = new Random();

    Partita partita = new Partita();


    public void generaEquilibrio() {
        inserisciElementi(2);//partita.getNumeroElementi()

    }



    private boolean inserisciElementi(int numElementi) {

        int[][] iterazioneElementi = new int[numElementi][numElementi];


        //danneggia
        for (int i = 0; i < numElementi; i++) {
            iterazioneElementi[i] = danneggia(numElementi);
        }

        //subisce
        for (int i = 0; i < numElementi; i++) {
            iterazioneElementi[i] = subisce(numElementi,iterazioneElementi[i]);
        }

        //sistema
        for (int i = 0; i < numElementi; i++) {
            iterazioneElementi[i] = sistemaPotenze(iterazioneElementi[i]);
        }


        return false;
    }

    private int sommaArrayPositivi(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                sum += array[i];
            }

        }
        return sum;
    }

    private int sommaArrayNegativi(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                sum += array[i];
            }

        }
        return sum;
    }

    private int[] danneggia(int numElementi) {

        int[] array = new int[numElementi];

        int numElementiDanneggia = random.nextInt(numElementi);
        if (numElementiDanneggia == 0) {
            numElementiDanneggia = 1;
        }

        do {
            int numMaxPotenza = 0;
            int pos = 0;

            numMaxPotenza = (numElementi - numElementiDanneggia);
            pos = random.nextInt(numElementi);
            array[pos] = random.nextInt(numMaxPotenza) + 1;
            numElementiDanneggia--;
            numElementi--;

        } while (numElementiDanneggia != 0);

        return array;
    }

    private int[] subisce(int numElementi, int[] array) {

        int numSubisce=0;
        int numDanneggia=0;
        for(int j=0; j<array.length;j++){
            if(array[j]==0){
                numDanneggia++;
            }

        }

        numSubisce=random.nextInt(numDanneggia)+1;
        int numMaxPotenza=0;
        for (int i=0; i<array.length && numSubisce != 0;i++){
            if(array[i] !=0){
                i++;
            }else{
                numMaxPotenza = (numElementi - numSubisce);
                array[i]=-(random.nextInt(numMaxPotenza));
                numSubisce--;
            }
        }

        return array;

    }

    private int[] sistemaPotenze(int[] array){
        int numZeri=0;
        for(int i=0;i<array.length;i++){
            if(array[i]==0){
                numZeri++;
            }
        }

        if(numZeri!=array.length){

            int posCambio=random.nextInt(array.length);
            array[posCambio]=0;
            array[posCambio]=-(sommaArrayNegativi(array)+sommaArrayPositivi(array));
        }
        return array;
    }


}
