// --------------------------------------------------
// Name    : Sort Comparison
// Author  : Danial Khorasanizadeh
// Course  : Data Structures - Fall 1400
// Date    : 11/Oct/2021
// --------------------------------------------------

import java.util.Arrays;
import java.util.Random;

public class Sort {

    static boolean isArraySorted(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return true;
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int arraySize = 300000;
        int[] array = new int[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(20001) - 10000;
        }
        int[] insertionSortArray = Arrays.copyOf(array, array.length);
        int[] mergeSortArray = Arrays.copyOf(array, array.length);
        Stopwatch timer = new Stopwatch();
        InsertionSort.insertionSort(insertionSortArray);
        timer.split("Insertion Sort");
        MergeSort.mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
        timer.split("Merge Sort");
    }
}
