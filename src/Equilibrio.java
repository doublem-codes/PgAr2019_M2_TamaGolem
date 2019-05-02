import java.util.Random;


public class Equilibrio {
    Random random = new Random();
    private int[][] iterazioneElementi;
    Partita partita = new Partita();


    public void generaEquilibrio(int numeroelementi) {
        inserisciElementi(numeroelementi);//partita.getNumeroElementi()
    }

    private boolean inserisciElementi(int numElementi) {
        iterazioneElementi = new int[numElementi][numElementi];
        //danneggia
        for (int i = 0; i < numElementi; i++) {
            iterazioneElementi[i] = danneggia(numElementi);
        }

        //subisce
        for (int i = 0; i < numElementi; i++) {
            iterazioneElementi[i] = subisce(iterazioneElementi[i]);
        }
        //azzera diagonale
        for(int i=0; i<numElementi; i++){
            for(int j=0; j<numElementi; j++){
                if(i==j){
                    iterazioneElementi[i][j]=0;
                }
            }
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

    private int[] subisce(int[] array) {

            int numDanneggia = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] == 0) {
                    numDanneggia++;
                }

            }

            int numMaxPotenza = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 0) {
                    numMaxPotenza = (numDanneggia);
                    array[i] = -(random.nextInt(numMaxPotenza) + 1);
                    numDanneggia--;
                }
            }
            //completare
       if(sommaArrayPositivi(array)+sommaArrayNegativi(array)==0){

       }
        return array;

    }

    private int[] sistemaPotenze(int[] array){
            int posCambio=random.nextInt(array.length);
            if(array[posCambio]==0){
             if(posCambio>=0 && posCambio<(array.length)){
                 posCambio=posCambio+1;
             }else if(posCambio == array.length){
                 posCambio=posCambio-1;
             }

            }
            array[posCambio]=0;
            array[posCambio]=-(sommaArrayNegativi(array)+sommaArrayPositivi(array));

        return array;
    }

    public boolean printMatrix(){
        int[][] matrix = this.iterazioneElementi;
        for(int i=0; i<matrix.length;i++){
            for(int j=0; j< matrix.length; j++){
                System.out.printf("%10d",matrix[i][j]);
            }
            System.out.println();
        }
        return false;
    }


}
