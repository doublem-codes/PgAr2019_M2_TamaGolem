import java.util.Random;


public class Equilibrio {
    public static String str ="";
    Random random = new Random();
    private int[][] iterazioneElementi;
    Partita partita = new Partita();


    public void generaEquilibrio() {
        iterazioneElementi = new int[5][5];
        inserisciElementi(5);//partita.getNumeroElementi()
        printMatrix(iterazioneElementi);

    }



    private boolean inserisciElementi(int numElementi) {

        //riempi con valore sentinella
        for(int i=0; i<numElementi; i++){
            for(int j=0; j<numElementi; j++){
                    iterazioneElementi[i][j]=numElementi+1;
            }
        }

        //azzera diagonale
        for(int i=0; i<numElementi; i++){
            for(int j=0; j<numElementi; j++){
                if(i==j){
                    iterazioneElementi[i][j]=0;
                }
            }
        }

        //danneggia
        for (int i = 0; i < numElementi; i++) {
            iterazioneElementi[i] = danneggia(iterazioneElementi[i]);
        }

        //subisce
        for (int i = 0; i < numElementi; i++) {
            iterazioneElementi[i] = subisce(iterazioneElementi[i]);
        }

        //sistema

            sistemaPotenze(iterazioneElementi);


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
            if(array[pos1]==(array.length+1)){
                array[pos1] = random.nextInt(numMaxPotenza) + 1;
            }else{
                if(pos1==0){
                    pos2=pos1+1;
                }else{
                    pos2=pos1-1;
                }

                if(array[pos2] == 0){
                    if(pos2==0){
                        pos2=pos2+1;
                    }else{
                        pos2=pos2-1;
                    }

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
                if (array[j] == array.length+1) {
                    numDanneggia++;
                }

            }

            int numMaxPotenza = 0;
            numMaxPotenza = array.length -(numDanneggia);
            for (int i = 0; i < array.length; i++) {

                if (array[i] == array.length+1) {

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

    public boolean printMatrix(int[][] matrix){
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

    private int cercaMinore(int[] array, boolean isSubisce){
        int min=array[0];
        int index=0;
        if(isSubisce){
            for (int i=0; i<array.length;i++){
                if(array[i]<0 && array[i]>min){
                    min=array[i];
                    index=i;
                }
            }
        }else{
            for (int i=0; i<array.length;i++){
                if(array[i]>0 && array[i]<min){
                    min=array[i];
                    index=i;
                }
            }
        }

        return index;
    }

    private boolean checkSumZero(int[] array){
        if(sommaArrayNegativi(array)+sommaArrayPositivi(array) ==0){
            return true;
        }
        return false;
    }


    private int[][] sistemaPotenze(int[][] matrix) {
        int posCambio = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (sommaArrayNegativi(matrix[i]) + sommaArrayPositivi(matrix[i]) == 0) {
                break;
            } else {

                //cerco se ci sono più elementi che danneggiano o che subiscono
                //tra uno dei due trovo il maggiore e lo sostituisco
                int nSubisce = 0, nDanneggia = 0;
                for (int l = 0; l < matrix[i].length; l++) {
                    if (matrix[i][l] > 0) {
                        nDanneggia++;
                    } else {
                        nSubisce++;
                    }
                }

                if (nDanneggia > nSubisce) {
                    posCambio = cercaMaggiore(matrix[i], false);
                } else {
                    posCambio = cercaMaggiore(matrix[i], true);
                }
                matrix[i][posCambio] = 0;
                matrix[i][posCambio] = -(sommaArrayNegativi(matrix[i]) + sommaArrayPositivi(matrix[i]));


            }
        }
        return matrix;
    }





    public static String find(int[] A, int currSum, int index, int sum, int[] solution) {
        if (currSum == sum) {
            System.out.print("\nS:");
            for (int i = 0; i < solution.length; i++) {
                if (solution[i] == 1) {
                    str=str+(A[i]+"&");
                    System.out.print(str);
                }
            }

        } else if (index == A.length) {
            return str;
        } else {
            solution[index] = 1;// select the element
            currSum += A[index];
            find(A, currSum, index + 1, sum, solution);
            currSum -= A[index];
            solution[index] = 0;// do not select the element
            find(A, currSum, index + 1, sum, solution);
        }
        return str ;
    }



}
