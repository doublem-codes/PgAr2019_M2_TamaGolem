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

        for (int i=0; i<iterazioneElementi.length;i++){
            danneggia(iterazioneElementi[i]);
            subisce(iterazioneElementi[i]);
        }
        for(int j=0;j<iterazioneElementi.length;j++){
            sistemaPotenze(iterazioneElementi[j],j);
            copiaValore(iterazioneElementi,j);
            printMatrix(iterazioneElementi);
            System.out.println("__________________________");
            System.out.println("__________________________");
        }


        return false;
    }

    private int sommaArray(int[] array) {
        int sum = 0;
        for (int numero: array) {
            sum += numero;
        }
        return sum;
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



    private int[] sistemaPotenze(int[] array, int zeroIndex) {

        if(sommaArray(array)==0) return array;
        /*
        int differenza=calcolaDifferenza(array,zeroIndex);
        do{
            int pos=0;
            if(differenza<0){
                pos = cercaMaggiore(array,false);
                array[pos]+=differenza;
            }else{
                pos = cercaMinore(array,true);
                array[pos]+=differenza;
            }


            if(!(array[pos]<=array.length-1 && array[pos] !=0 && array[pos]>=-(array.length-1)) ){
                if(array[pos]==0){
                    if(differenza>0){
                        array[pos]=+1;
                    }else{
                        array[pos]=-1;
                    }
                }else{
                    if(differenza>0){
                        array[pos]=(array.length-1);
                    }else{
                        array[pos]=-(array.length-1);
                    }
                }

            }
            differenza=calcolaDifferenza(array,zeroIndex);
        }while(differenza!=0);

        */

        do {
            int posCambio = 0;

            int nSubisce = 0, nDanneggia = 0;
            for (int l = 0; l < array.length; l++) {
                if(array[l]==0) continue;
                if (array[l] > 0) {
                    nDanneggia++;
                } else {
                    nSubisce++;
                }
            }

            if (nDanneggia > nSubisce) {
                posCambio = cercaMaggiore(array, false,zeroIndex);
            } else {
                posCambio = cercaMaggiore(array, true,zeroIndex);
            }
            array[posCambio] = 0;
            array[posCambio] = -(sommaArrayNegativi(array) + sommaArrayPositivi(array));

            if (!(array[posCambio]<=array.length-1 && array[posCambio] !=0 && array[posCambio]>=-(array.length-1))) {
                if (nDanneggia > nSubisce) {
                    array[posCambio] = +1;
                } else {
                    array[posCambio] = +1;
                }


            }

        }while (sommaArrayNegativi(array) + sommaArrayPositivi(array) != 0);




        return array;
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

    private void copiaValore(int[][] matrix,int zeroIndex){
        for(int i=zeroIndex+1;i<matrix.length;i++){
            int a = matrix[zeroIndex][i];
            matrix[i][zeroIndex]=-a;
        }

    }






}
