public class ArrayAdministration {

    /**
     *
     * @param array : array which elements have to be sum
     * @return sum : the sum of all the positive elements
     */
    public int sumPositiveElementsOfArray(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0) {
                sum += array[i];
            }
        }
        return sum;
    }

    /**
     *
     * @param array : array which elements have to be sum
     * @return : sum : the sum of all the positive elements
     */
    public int sumNegativeElementsOfArray(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                sum += array[i];
            }
        }
        return sum;
    }
}
