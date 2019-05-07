import java.util.ArrayList;
//LIBRERIA
public class MatrixAdministration {
    /**
     * @param matrix : matrix to search maximum number
     * @return maxNumber : maximum number of the matrix
     */
    public int searchMaximum(int[][] matrix) {
        int maxNumber = matrix[0][0];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (maxNumber < matrix[i][j]) {
                    maxNumber = matrix[i][j];
                }
            }
        }
        return maxNumber;
    }

    /**
     *
     * @param diagonalIndex : where to transpose the matrix
     * @param matrix : matrix to transpose
     * @param invertNumberValue : when transpose the matrix if the value have to be inverted
     * @return transpose of the matrix
     */
    public int[][] transposeMatrix(int diagonalIndex, int[][] matrix, boolean invertNumberValue) {
        for (int i = diagonalIndex + 1; i < matrix.length; i++) {
            int temp = matrix[diagonalIndex][i];
            if (invertNumberValue) {
                matrix[i][diagonalIndex] = -temp;
            } else {
                matrix[i][diagonalIndex] = temp;
            }

        }
        return matrix;
    }

    /**
     *
     * @param matrix : matrix that have to be printed
     * @param arrayList : Elements of the arrayList will be the columns and row name
     */
    public void printMatrix(int[][] matrix, ArrayList<String> arrayList){
        System.out.println();
        int max = 0;
        for(String str : arrayList){
            int length = str.length()+4;
            if(length > max){
                max = length;
            }
        }

        int count2 = max-1;
        do{
            System.out.print(" ");
            count2--;
        }while(count2 >= 1);
        System.out.print("|");

        for(String str : arrayList){
            int length = str.length();
            printSpaces(length,max,false);
            System.out.print(str);
            printSpaces(length,max,true);
        }
        System.out.println();
        printDivision(max*arrayList.size(),max);
        for(int i = 0; i < matrix.length; i++){
            printSpaces(arrayList.get(i).length(),max,false);
            System.out.print(arrayList.get(i));
            printSpaces(arrayList.get(i).length(),max,true);
            for(int j = 0; j< matrix.length; j++){
                int num = matrix[i][j];
                String str = String.valueOf(num);
                printSpaces(str.length(),max,false);
                System.out.print(matrix[i][j]);
                printSpaces(str.length(),max,true);
            }
            System.out.println();
            printDivision(max*arrayList.size(),max);
        }
    }

    /**
     *
     * @param length : length of the string
     * @param max : the maximum number of spaces
     * @param isAfter : boolean to say if the method have to print spaces before of after the string element
     */
    private void printSpaces(int length, int max, boolean isAfter){
        int numberOfSpaces;
        if(!isAfter) {
            numberOfSpaces=((max-length)/2);
            do{
                System.out.print(" ");
                numberOfSpaces--;
            }while(numberOfSpaces>=1);
        }
        if(isAfter){
            numberOfSpaces = ((max-length)/2);
            int numberOfSpacesAfter = max-(numberOfSpaces+length)-1;
            do{
                System.out.print(" ");
                numberOfSpacesAfter--;
            }while(numberOfSpacesAfter >= 1);
            System.out.print("|");
        }
    }

    /**
     *
     * @param totalLenght : the total length of the list
     * @param lenghtEachBox : the length of each box
     */
    private void printDivision(int totalLenght, int lenghtEachBox){
        int numberOfBox=totalLenght/lenghtEachBox +1;
        int count;
        do{
            count=lenghtEachBox-1;
            do{
                System.out.print("-");
                count--;
            }while (count>=1);

            System.out.print("+");
            numberOfBox--;
        }while (numberOfBox>=1);

        System.out.println();

    }
}
