import java.util.Arrays;

/**
 * Merging Sort
 * Proc1: Merging of two sorted Arrays
 * Proc2: Division on Many arrays, Combining of Many arrays
 */
public class Merging {

    public static int[] mergeTwoSortedArrays(int[] arrayOne, int[] arrayTwo) {
//        System.out.println(Arrays.toString(arrayOne));
//        System.out.println(Arrays.toString(arrayTwo));
        int range = arrayOne.length + arrayTwo.length;
        int arrayOnePointer = 0;
        int arrayTwoPointer = 0;
        int[] result = new int[range];
        for (int i = 0; i < range; i++) {
            if ((arrayOnePointer < arrayOne.length) && (arrayTwoPointer < arrayTwo.length)) {
                if (arrayOne[arrayOnePointer] < arrayTwo[arrayTwoPointer]) {
                    result[i] = arrayOne[arrayOnePointer];
                    arrayOnePointer++;
                } else {
                    result[i] = arrayTwo[arrayTwoPointer];
                    arrayTwoPointer++;
                }
            } else {
                if (arrayTwoPointer >= arrayTwo.length) {
                    result[i] = arrayOne[arrayOnePointer];
                    arrayOnePointer++;
                } else if (arrayOnePointer >= arrayOne.length) {
                    result[i] = arrayTwo[arrayTwoPointer];
                    arrayTwoPointer++;
                }
            }
//            System.out.println(arrayOnePointer + ":" + arrayOne.length + "|" +
//                    arrayTwoPointer + ":" + arrayTwo.length + "|"
//                    + Arrays.toString(result));

        }
        return result;
    }

    public static void testMergingSortedArrays() {
        int[] arrayOne = {1, 5, 9, 11, 17, 21};
        int[] arrayTwo = {3, 7, 13, 15, 19, 31, 33};
        int[] oneElementArrayOne = {13};
        int[] oneElementArrayTwo = {3};
        int[] biggerArray = {100, 101, 105, 106, 110};

        System.out.println("\n1. Usual arrays");
        Merging.mergeTwoSortedArrays(arrayOne, arrayTwo);
        System.out.println("\n2. one element arrays");
        mergeTwoSortedArrays(oneElementArrayOne, oneElementArrayTwo);
        System.out.println("\n3. with all bigger elements");
        mergeTwoSortedArrays(arrayOne, biggerArray);
    }

    public static int[][] arraysDivision(int[] array) {
        int[][] result = new int[array.length][1];
        for (int i = 0; i < array.length; i++) {
            result[i][0] = array[i];
        }
        return result;
    }

    public static int[]mergeSort(int[]arrayVar){
        System.out.println("***Merge Sort");
        int length = arrayVar.length;
        int degree, range = 0;
        int k = (int) (Math.log10(length + 1) * (3.321));
        System.out.println("k=" + k);
        System.out.println("length=" + length);

        int[][][] arr = new int[k+1][][];
        arr[0] = arraysDivision(arrayVar);
        System.out.println("***arr[0]");
        System.out.println(Arrays.deepToString(arr[0]));

        for (int i = 1; i <= k; i++) {
            degree = (int) (Math.pow(2, i));
            range = (length) / degree;
            System.out.println("range="+range+" degree="+degree+"size="+(degree+(length%degree)));
            arr[i] = new int[range][degree+(length%degree)];
            for (int j = 0; j <= (range - 1); j++) {
                arr[i][j] = mergeTwoSortedArrays(arr[i - 1][2 * j], arr[i - 1][2 * j + 1]);
            }
            System.out.println(Arrays.deepToString(arr[i]));
        }
        return arr[k][0];
    }

    public static void main(String[] args) {

//        testMergingSortedArrays();

        int[] array0 =
                { 5, 3,61, 7, 8,18,31,17,
                        23,14,34,56,78,11,98,15,
                        4, 1,33,12, 9,54, 3, 7,
                        45,32,27,78,12,67,78,62
                };

        int[] array1 = { 5, 3,61, 7,
                8,11,31,17,
                23,14,34,56,
                78,11,98,15};

        int[] array2 = {34,23,50,61};

        int[] array = {34,23,50,61,5,11,43,18,1};

        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(mergeSort(array)));
    }
}