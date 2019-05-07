import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.Matrix;

import java.util.ArrayList;
import java.util.Random;


public class Equilibrio {
    Random random = new Random();
    MatrixAdministration matrixAdministration=new MatrixAdministration();
    ArrayAdministration arrayAdministration = new ArrayAdministration();
    private int[][] iterazioneElementi;
    private boolean isCorrect=false;


    /**
     *
     * @param numElementi : numero di pietre
     *  inserisce i valori nella matrice in modo tale che la somma
     *  nelle righe e nelle colonne sia zero
     */

    public void generaEquilibrio(int numElementi) {
        iterazioneElementi = new int[numElementi][numElementi];
        inserisciElementi(numElementi);
    }

    /**
     *
     * @param numElementi : numero di pietre
     * il metodo riempie la matrice
     */
    private void inserisciElementi(int numElementi) {
        /*
        Riempio la matrice con un valore sentinella e azzero la diagonale
         */
        for(int i = 0; i < numElementi; i++){
            for(int j = 0; j < numElementi; j++){
                iterazioneElementi[i][j] = numElementi+1;
                if(i == j){
                    iterazioneElementi[i][j] = 0;
                }
            }
        }

        /*
        Inserisco il valori positivi e negativi nelle celle dove c'è il valore sentinella
         */
        for (int i=0; i<iterazioneElementi.length;i++){
            danneggia(iterazioneElementi[i]);
            subisce(iterazioneElementi[i]);
        }
        /*
        Sistemo gli elementi in modo tale che la somma delle colonne e delle righe sia zero
         */
        for(int j=0;j<iterazioneElementi.length-1;j++){
            sistema(j);
            isCorrect=false;
            iterazioneElementi = matrixAdministration.transposeMatrix(j,iterazioneElementi,true);
        }

        /*
        Se il penultimo elemento non è possibile sistemarlo richiamo il metodo
        Nell'esempio dovrei inserire 0 ma questo non può accadere
        Esempio:

                0    4   -3   -1
               -4    0    3    1
                3   -3    0    x
                1   -1   -1    0

         */
        if((arrayAdministration.sumPositiveElementsOfArray(iterazioneElementi[iterazioneElementi.length-1])+arrayAdministration.sumNegativeElementsOfArray(iterazioneElementi[iterazioneElementi.length-1])!=0)){
            inserisciElementi(numElementi);
        }
    }

    //Inserisco i valori di danneggia nella matrice
    private int[] danneggia(int[] array) {
        int numElementiDanneggia = random.nextInt(array.length - 2) + 1;
        int numMaxPotenza;
        int pos1, pos2=0;
        int numElementi = array.length;
        do {
            numMaxPotenza = numElementi - 1;
            pos1 = random.nextInt(array.length);
            if (array[pos1] == (array.length + 1)) {
                array[pos1] = random.nextInt(numMaxPotenza) + 1;
            } else if (array[pos2] == 0) {
                if (pos2 == 0) {
                    pos2 = pos2 + 1;
                } else {
                    pos2 = pos2 - 1;
                }
            }
            array[pos2] = random.nextInt(numMaxPotenza) + 1;
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

    //Stampa della matrice
    public void printMatrix(ArrayList<String> nomiElementi){
       matrixAdministration.printMatrix(iterazioneElementi,nomiElementi);
    }

    /**
     *
     * @param array : array in cui cercare il valore massimo
     * @param isSubisce : se cercare il valore tra i positivi o i negativi
     * @param zeroIndex : indice dopo il quale cercare il massimo
     * @return
     */
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

    /*
    Sistema la matrice in moto tale che la somma delle righe e delle colonne sia zero

    - isCorrect=false
        modifico il valore all'indice restituito dal metodo index come somma
        degli elementi prima e dopo il numero
    - isCorrect=true
        modifico i valori della matrice dopo lo zero
     */
    private void sistema(int zeroIndex){
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
            /*
                Se la somma prima dell'elemento è nei limiti ed è diverso da
                zero modifico il valore come la somma degli elementi prima
                più quelli dopo
             */
            if(sumAfter+sumBefore != 0 && sumAfter+sumBefore >= -(iterazioneElementi.length-1) && (sumAfter + sumBefore) <= (iterazioneElementi.length-1)){
                iterazioneElementi[zeroIndex][index]=-(sumAfter+sumBefore);
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
            sistema(zeroIndex);
        }
    }

    /*
    L'indice in cui deve essere modificato il valore inizialmente è il massimo
    se l'indice non è corretto viene cambiato l'indice in maniera random dopo
    lo zero
     */

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
            //genero un indice tra zero index < index <= lunghezza totale dell'array
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
