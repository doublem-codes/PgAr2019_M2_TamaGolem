import java.util.Random;


public class Equilibrio {
    Random random = new Random();
    private int[][] iterazioneElementi;
    Partita partita = new Partita();


    public void generaEquilibrio() {

        inserisciElementi(5);//partita.getNumeroElementi()
        printMatrix(iterazioneElementi);

    }



    private boolean inserisciElementi(int numElementi) {
        iterazioneElementi = new int[numElementi][numElementi];
        //danneggia
        for (int i = 0; i < numElementi; i++) {
            iterazioneElementi[i] = danneggia(iterazioneElementi[i]);
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

            iterazioneElementi = sistemaPotenze(iterazioneElementi);


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

    private int[] danneggia(int[] array) {


        int numElementiDanneggia = random.nextInt(array.length-1)+1;
        int numMaxPotenza = 0;
        int pos1 = 0, pos2 =0;
        int numElementi=array.length;
        do {
            numMaxPotenza = numElementi-1;
            pos1 = random.nextInt(array.length);
            if(array[pos1]==0){
                array[pos1] = random.nextInt(numMaxPotenza) + 1;
            }else{
                if(pos1==0){
                    pos2=pos1+1;
                }else{
                    pos2=pos1-1;
                }

                array[pos2] = random.nextInt(numMaxPotenza) + 1;
            }

            numElementi--;
            numElementiDanneggia--;

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
                numMaxPotenza = array.length -(numDanneggia);
                if (array[i] == 0) {

                    if(numMaxPotenza!=0){
                        array[i] = -(random.nextInt(numMaxPotenza) + 1);
                    }else {
                        array[i]=-1;
                    }

                        numMaxPotenza--;
                }
            }
        return array;

    }

    private int[][] sistemaPotenze(int[][] matrix){
            int posCambio=0;
            for(int i=0;i<matrix.length;i++){
                if(sommaArrayNegativi(matrix[i])+sommaArrayPositivi(matrix[i])==0){
                    i++;
                }else{

                    //cerco se ci sono più elementi che danneggiano o che subiscono
                    //tra uno dei due trovo il maggiore e lo sostituisco
                    int nSubisce=0, nDanneggia=0;
                    for(int l=0; l<matrix[i].length;l++){
                        if(matrix[i][l]>0){
                            nDanneggia++;
                        }else{
                            nSubisce++;
                        }
                    }

                    if(nDanneggia>nSubisce){
                        posCambio=cercaMaggiore(matrix[i],false);
                    }else{
                        posCambio=cercaMaggiore(matrix[i],true);
                    }
                    matrix[i][posCambio]=0;
                    matrix[i][posCambio]=-(sommaArrayNegativi(matrix[i])+sommaArrayPositivi(matrix[i]));



                }
            }




        return matrix;
    }

    private boolean printMatrix(int[][] matrix){
        for(int i=0; i<matrix.length;i++){
            for(int j=0; j< matrix.length; j++){
                System.out.printf("%10d",matrix[i][j]);
            }
            System.out.println();
        }
        return false;
    }

    private int cercaMaggiore(int[] array, boolean isSubisce){
        int max=array[0];
        int index=0;
        if(isSubisce){
            for (int i=0; i<array.length;i++){
                if(array[i]<0 && array[i]<max){
                    max=array[i];
                    index=i;
                }
            }
        }else{
            for (int i=0; i<array.length;i++){
                if(array[i]>0 && array[i]>max){
                    max=array[i];
                    index=i;
                }
            }
        }

        return index;
    }

}
