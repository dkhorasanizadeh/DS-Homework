
// --------------------------------------------------
// Name    : Merge Sort Worst Case
// Author  : Danial Khorasanizadeh
// Course  : Data Structures - Fall 1400
// Date    : 11/Oct/2021
// --------------------------------------------------
import java.util.Scanner;

public class MergeSortWorstCase {
    static void shuffle(int[] array, int start, int middle, int end) {
        int rightSize = middle - start + 1;
        int leftSize = end - middle;
        int[] rightArray = new int[rightSize];
        int[] leftArray = new int[leftSize];
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = array[start + (i * 2)];
        }
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[start + (i * 2 + 1)];
        }
        int k = start;
        for (int i = 0; i < rightSize; i++) {
            array[k] = rightArray[i];
            k++;
        }
        for (int i = 0; i < leftSize; i++) {
            array[k] = leftArray[i];
            k++;
        }

    }

    public static void mergeSortWorstCase(int[] array, int start, int end) {
        if (start < end) {
            int middle = (end + start) / 2;
            shuffle(array, start, middle, end);
            mergeSortWorstCase(array, start, middle);
            mergeSortWorstCase(array, middle + 1, end);
        }
    }

    static void merge(int[] array, int start, int middle, int end) {
        int[] rightArray = new int[middle - start + 2];
        int[] leftArray = new int[end - middle + 1];
        for (int i = 0; i < middle - start + 1; i++) {
            rightArray[i] = array[start + i];
        }
        for (int i = 0; i < end - middle; i++) {
            leftArray[i] = array[middle + 1 + i];
        }
        rightArray[middle - start + 1] = Integer.MAX_VALUE;
        leftArray[end - middle] = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int k = start; k <= end; k++) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
        }
    }

    public static void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int middle = (end + start) / 2;
            mergeSort(array, start, middle);
            mergeSort(array, middle + 1, end);
            merge(array, start, middle, end);
        }
    }

    static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }
        mergeSort(array, 0, length - 1);
        mergeSortWorstCase(array, 0, length - 1);
        printArray(array);
        scanner.close();
    }
}
