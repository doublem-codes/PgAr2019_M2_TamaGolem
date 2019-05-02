import java.util.Random;


public class Equilibrio {
    Random random = new Random();
    private int[][] iterazioneElementi;
    Partita partita = new Partita();


    public void generaEquilibrio() {
        iterazioneElementi = new int[5][5];
        inserisciElementi(5);//partita.getNumeroElementi()
        printMatrix(iterazioneElementi);

    }


    private boolean isCorrect=false;

    private int count;
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

        for (int i=0; i<iterazioneElementi.length;i++){
            danneggia(iterazioneElementi[i]);
            subisce(iterazioneElementi[i]);
        }

        //SISTEMA
        for(int j=0;j<iterazioneElementi.length-1;j++){

            int index = indiceCorretto(j);
            if(index>=j){
                index++;
            }
            iterazioneElementi[j][index]=0;
            iterazioneElementi[j][index]=-(sommaArrayNegativi(iterazioneElementi[j]) + sommaArrayPositivi(iterazioneElementi[j]));

            isCorrect=false;
            copiaValore(j);
            printMatrix(iterazioneElementi);
            System.out.println("--------------------------------------");

        }

        return true;
    }

    private int[] danneggia(int[] array) {
        int numElementiDanneggia = random.nextInt(array.length-2)+1;
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

        int numSubisce = 0;
        for (int j = 0; j < array.length; j++) {
            if (array[j] == array.length+1) {
                numSubisce++;
            }

        }

        int numMaxPotenza = array.length-1;
        for (int i = 0; i < array.length && numSubisce!=0; i++) {

            if(array[i]==array.length+1){
                array[i] = -(random.nextInt(numMaxPotenza) + 1);

                numSubisce--;
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

    private int cercaMaggiore(int[] array, boolean isSubisce, int zeroIndex){
        int max=array[0];
        int index=0;
        if(isSubisce){
            for (int i=zeroIndex+1; i<array.length;i++){
                if(array[i]<0 && array[i]<max){
                    max=array[i];
                    index=i;
                }
            }
        }else{
            for (int i=zeroIndex+1; i<array.length;i++){
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
                if(array[i]<0 && array[i]<min){
                    min=array[i];
                    index=i;
                }
            }
        }else{
            for (int i=0; i<array.length;i++){
                if(array[i]>0 && array[i]>min){
                    min=array[i];
                    index=i;
                }
            }
        }

        return index;
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


    private int calcolaDifferenza(int[] array, int zeroIndex){
        int differenza=0;
        int sumBeforeZero=0, sumAfterZero=0;
        for (int j=zeroIndex; j>=0;j--){
            sumBeforeZero+=array[j];
        }
        for (int j=zeroIndex; j<array.length;j++){
            sumAfterZero+=array[j];
        }
        differenza=sumBeforeZero+sumAfterZero;

        return differenza;
    }

    private void copiaValore(int zeroIndex){
        for(int i=zeroIndex+1;i<iterazioneElementi.length;i++){
            int a = iterazioneElementi[zeroIndex][i];
            iterazioneElementi[i][zeroIndex]=-a;
        }

    }

    private int index;
    private int nuovoIndice;

    private int indiceCorretto(int zeroIndex ){

        Random random=new Random();

        int nSubisce = 0, nDanneggia = 0;


        int j=0;
        int[] arrayTemp=new int[iterazioneElementi[zeroIndex].length-1];
        for (int i=0; i<iterazioneElementi[zeroIndex].length;i++){
            if(iterazioneElementi[zeroIndex][i]!=0){
                arrayTemp[j]=iterazioneElementi[zeroIndex][i];
                j++;
            }
        }

        for (int l = 0; l < arrayTemp.length; l++) {
            if (arrayTemp[l] > 0) {
                nDanneggia++;
            } else {
                nSubisce++;
            }
        }

        if(!isCorrect){
            if (nDanneggia > nSubisce) {
                index = cercaMaggiore(arrayTemp, false,zeroIndex);
            } else {
                index = cercaMaggiore(arrayTemp, true,zeroIndex);
            }
        }else {
            index=nuovoIndice-1;
        }

        arrayTemp[index] = 0;
        arrayTemp[index] = -(sommaArrayNegativi(arrayTemp) + sommaArrayPositivi(arrayTemp));

        if ((arrayTemp[index]<=iterazioneElementi[zeroIndex].length-1 && arrayTemp[index] !=0 && arrayTemp[index]>=-(iterazioneElementi[zeroIndex].length-1)) && sommaArrayNegativi(arrayTemp) + sommaArrayPositivi(arrayTemp) == 0)
        {
            return index;
        }else {

            if (index >= zeroIndex) {
                index++;
            }

            int numSubisce, numDanneggia;
            numSubisce = sommaArrayNegativi(iterazioneElementi[zeroIndex]);
            numDanneggia = sommaArrayPositivi(iterazioneElementi[zeroIndex]);
            int sum = 0;
            sum=numSubisce + numDanneggia;


            count++;
            nuovoIndice = zeroIndex+random.nextInt((iterazioneElementi.length)-zeroIndex);

            if (nuovoIndice != zeroIndex) {
                if (iterazioneElementi[zeroIndex][nuovoIndice] + sum <= iterazioneElementi.length - 1 && iterazioneElementi[nuovoIndice][index] + sum != 0 && iterazioneElementi[nuovoIndice][index] + sum >= -(iterazioneElementi.length - 1)) {
                    iterazioneElementi[zeroIndex][nuovoIndice] += sum;
                }else{
                    iterazioneElementi[zeroIndex][nuovoIndice]=iterazioneElementi.length-1;
                }
            }else{
                if(zeroIndex==iterazioneElementi.length){
                    nuovoIndice--;
                }else{
                    nuovoIndice++;
                }
                if (iterazioneElementi[zeroIndex][nuovoIndice] + sum <= iterazioneElementi.length - 1 && iterazioneElementi[nuovoIndice][index] + sum != 0 && iterazioneElementi[nuovoIndice][index] + sum >= -(iterazioneElementi.length - 1)) {
                    iterazioneElementi[zeroIndex][nuovoIndice] = iterazioneElementi[zeroIndex][nuovoIndice] +sum;
                }else{
                    iterazioneElementi[zeroIndex][nuovoIndice]=iterazioneElementi.length-1;
                }

            }
            isCorrect=true;

            indiceCorretto(zeroIndex);
        }

        return index;
    }




}
