import java.util.ArrayList;
import java.util.Random;


public class Equilibrio {
    Random random = new Random();
    private int[][] iterazioneElementi;

    public void generaEquilibrio(int numElementi) {
        iterazioneElementi = new int[numElementi][numElementi];
        inserisciElementi(numElementi);
    }

    private boolean isCorrect=false;

    private boolean inserisciElementi(int numElementi) {

        for(int i=0; i<numElementi; i++){
            for(int j=0; j<numElementi; j++){
                iterazioneElementi[i][j]=numElementi+1;
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

            indiceCorretto(j);
            isCorrect=false;
            copiaValore(j);

        }
        if(sommaArrayPositivi(iterazioneElementi[iterazioneElementi.length-1])+sommaArrayNegativi(iterazioneElementi[iterazioneElementi.length-1])!=0){
            inserisciElementi(numElementi);
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

    public void printMatrix(ArrayList<String> nomiElementi){

        int max=0;
        for(String str : nomiElementi){
            int length=str.length()+2;
            if(length>max){
                max=length;
            }
        }
        int count2=max;
        do{
            System.out.print(" ");
            count2--;
        }while(count2>=1);

        for(String str : nomiElementi){
            int length=str.length();
            printSpazi(length,max,false);
            System.out.print(str);
            printSpazi(length,max,true);
        }

        System.out.println();



        for(int i=0; i<iterazioneElementi.length;i++){
            printSpazi(nomiElementi.get(i).length(),max,false);
            System.out.print(nomiElementi.get(i));
            printSpazi(nomiElementi.get(i).length(),max,true);
            for(int j=0; j< iterazioneElementi.length; j++){
                int num=iterazioneElementi[i][j];
                String str= String.valueOf(num);
                printSpazi(str.length(),max,false);
                System.out.print(iterazioneElementi[i][j]);
                printSpazi(str.length(),max,true);
            }
            System.out.println();
        }
    }

    private void printSpazi(int length, int max, boolean isAfter){
        int numSpazi=0;
        if(!isAfter) {

        numSpazi=(int)((max-length)/2);
        do{
            System.out.print(" ");
            numSpazi--;
        }while(numSpazi>=1);
        }

        if(isAfter){
            numSpazi=(int)((max-length)/2);
            int numSpaziDopo=max-(numSpazi+length);
            do{
                System.out.print(" ");
                numSpaziDopo--;
            }while(numSpaziDopo>=1);
        }



    }

    private int cercaMaggiore(int[] array, boolean isSubisce, int zeroIndex){
        int max=array[zeroIndex+1];
        int index=zeroIndex+1;
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

    private void copiaValore(int zeroIndex){
        for(int i=zeroIndex+1;i<iterazioneElementi.length;i++){
            int a = iterazioneElementi[zeroIndex][i];
            iterazioneElementi[i][zeroIndex]=-a;
        }

    }

    private boolean indiceCorretto(int zeroIndex ){
        int index;
        if(!isCorrect){
            isCorrect=false;
            index=index(zeroIndex);
            if(index<zeroIndex){
                //Errore ogni tanto torna un index < dello zeroIndex
                System.out.println("");
            }
            int sumBefore=0, sumAfter=0;
            for(int i=0; i<index;i++){
                sumBefore=sumBefore+iterazioneElementi[zeroIndex][i];
            }
            for(int i=index+1; i<iterazioneElementi.length;i++){
                sumAfter=sumAfter+iterazioneElementi[zeroIndex][i];
            }

            if(sumAfter+sumBefore !=0 && sumAfter+sumBefore>=-(iterazioneElementi.length-1) && sumAfter+sumBefore<=(iterazioneElementi.length-1)){
                iterazioneElementi[zeroIndex][index]=-(sumAfter+sumBefore);
            }else if (sumAfter+sumBefore==0) {
                return true;
                    } else{
                        isCorrect=true;
                    }
        }else{

            isCorrect=true;
            index=index(zeroIndex);
            int numSubisce, numDanneggia;
            numSubisce = sommaArrayNegativi(iterazioneElementi[zeroIndex]);
            numDanneggia = sommaArrayPositivi(iterazioneElementi[zeroIndex]);
            int sum = 0;
            sum=numSubisce + numDanneggia;

            if (iterazioneElementi[zeroIndex][index] + sum <= iterazioneElementi.length - 1 && iterazioneElementi[zeroIndex][index] + sum != 0 && iterazioneElementi[zeroIndex][index] + sum >= -(iterazioneElementi.length - 1)) {
                iterazioneElementi[zeroIndex][index] += sum;
            }else{
                iterazioneElementi[zeroIndex][index]=iterazioneElementi.length-1;
            }
            indiceCorretto(zeroIndex);

        }

        return false;

    }

    private int index(int zeroIndex){
        int index=0;
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
            index=zeroIndex+random.nextInt((iterazioneElementi.length)-zeroIndex);
        }

        return index;
    }


    public int cercaMassimoMatrice(){
        //valore massimo in modulo
        return 1;
    }

    public int danni(int elemento1, int elemento2){

        return 1;
    }

}
