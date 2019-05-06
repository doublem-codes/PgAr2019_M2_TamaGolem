import java.util.ArrayList;
import java.util.Random;

public class Equilibrio {
    Random random = new Random();
    MatrixAdministration matrixAdministration=new MatrixAdministration();
    ArrayAdministration arrayAdministration = new ArrayAdministration();
    private int[][] iterazioneElementi;
    private boolean isCorrect=false;

    public void generaEquilibrio(int numElementi) {
        iterazioneElementi = new int[numElementi][numElementi];
        inserisciElementi(numElementi);
    }

    private boolean inserisciElementi(int numElementi) {
        for(int i = 0; i < numElementi; i++){
            for(int j = 0; j < numElementi; j++){
                iterazioneElementi[i][j] = numElementi+1;
                if(i == j){
                    iterazioneElementi[i][j] = 0;
                }
            }
        }

        for (int i=0; i<iterazioneElementi.length;i++){
            danneggia(iterazioneElementi[i]);
            subisce(iterazioneElementi[i]);
        }
        //SISTEMA
        for(int j=0;j<iterazioneElementi.length-1;j++){
            indiceCorretto(j);
            isCorrect=false;
            iterazioneElementi = matrixAdministration.transposeMatrix(j,iterazioneElementi,true);
        }
        if((arrayAdministration.sumPositiveElementsOfArray(iterazioneElementi[iterazioneElementi.length-1])+arrayAdministration.sumNegativeElementsOfArray(iterazioneElementi[iterazioneElementi.length-1])!=0)){
            inserisciElementi(numElementi);
        }
        return true;
    }

    private int[] danneggia(int[] array) {
        int numElementiDanneggia = random.nextInt(array.length-2)+1;
        int numMaxPotenza ;
        int pos1, pos2;
        int numElementi = array.length;
        do {
            numMaxPotenza = numElementi-1;
            pos1 = random.nextInt(array.length);
            if(array[pos1] == (array.length+1)){
                array[pos1] = random.nextInt(numMaxPotenza) + 1;
            }else{
                if(pos1 == 0){
                    pos2 = pos1+1;
                }else{
                    pos2 = pos1-1;
                }
                if(array[pos2] == 0){
                    if(pos2 == 0){
                        pos2 = pos2+1;
                    }else{
                        pos2 = pos2-1;
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
        for (int i = 0; i < array.length && numSubisce != 0; i++) {
            if(array[i] == array.length+1){
                array[i] = -(random.nextInt(numMaxPotenza) + 1);
                numSubisce--;
                numMaxPotenza--;
            }
        }
        return array;
    }

    public void printMatrix(ArrayList<String> nomiElementi){
       matrixAdministration.printMatrix(iterazioneElementi,nomiElementi);
    }

    private int cercaMaggiore(int[] array, boolean isSubisce, int zeroIndex){
        int max = array[zeroIndex+1];
        int index = zeroIndex+1;
        if(isSubisce){
            for (int i = zeroIndex+1; i < array.length; i++){
                if(array[i] < 0 && array[i] < max){
                    max = array[i];
                    index = i;
                }
            }
        }else{
            for (int i = zeroIndex+1; i < array.length; i++){
                if(array[i]>0 && array[i]>max){
                    max = array[i];
                    index = i;
                }
            }
        }
        return index;
    }

    private boolean indiceCorretto(int zeroIndex ){
        int index;
        if(!isCorrect){
            isCorrect = false;
            index = index(zeroIndex);
            int sumBefore = 0, sumAfter = 0;
            for(int i=0; i<index;i++){
                sumBefore = sumBefore+iterazioneElementi[zeroIndex][i];
            }
            for(int i = index+1; i < iterazioneElementi.length; i++){
                sumAfter = sumAfter+iterazioneElementi[zeroIndex][i];
            }

            if(sumAfter+sumBefore != 0 && sumAfter+sumBefore >= -(iterazioneElementi.length-1) && (sumAfter + sumBefore) <= (iterazioneElementi.length-1)){
                iterazioneElementi[zeroIndex][index]=-(sumAfter+sumBefore);
            }else if (sumAfter+sumBefore == 0) {
                return true;
            } else{
                isCorrect = true;
            }
        }else{
            isCorrect = true;
            index = index(zeroIndex);
            int numSubisce, numDanneggia;
            numSubisce = arrayAdministration.sumNegativeElementsOfArray(iterazioneElementi[zeroIndex]);
            numDanneggia = arrayAdministration.sumPositiveElementsOfArray(iterazioneElementi[zeroIndex]);
            int sum = numSubisce + numDanneggia;

            if (iterazioneElementi[zeroIndex][index] + sum <= iterazioneElementi.length - 1 && iterazioneElementi[zeroIndex][index] + sum != 0 && iterazioneElementi[zeroIndex][index] + sum >= -(iterazioneElementi.length - 1)) {
                iterazioneElementi[zeroIndex][index] += sum;
            }else{
                iterazioneElementi[zeroIndex][index] = iterazioneElementi.length-1;
            }
            indiceCorretto(zeroIndex);
        }
        return false;
    }

    private int index(int zeroIndex){
        int index;
        int nSubisce = 0, nDanneggia = 0;
        if(!isCorrect){
            for (int l = 0; l < iterazioneElementi.length; l++) {
                if (iterazioneElementi[zeroIndex][l] > 0) {
                    nDanneggia++;
                } else {
                    nSubisce++;
                }
            }
            if (nDanneggia > nSubisce) {
                index = cercaMaggiore(iterazioneElementi[zeroIndex], false,zeroIndex);
            } else {
                index = cercaMaggiore(iterazioneElementi[zeroIndex], true,zeroIndex);
            }
        }else {
            index = zeroIndex + random.nextInt((iterazioneElementi.length)-zeroIndex);
        }
        return index;
    }

    public int cercaMassimoMatrice(){
        return matrixAdministration.searchMaximum(iterazioneElementi);
    }

    public int danni(int elemento1, int elemento2){
        return iterazioneElementi[elemento1][elemento2];
    }
}
