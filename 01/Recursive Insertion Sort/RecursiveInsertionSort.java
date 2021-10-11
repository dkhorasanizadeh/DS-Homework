
// --------------------------------------------------
// Name    : Recursive Insertion Sort
// Author  : Danial Khorasanizadeh
// Course  : Data Structures - Fall 1400
// Date    : 11/Oct/2021
// --------------------------------------------------
import java.util.Scanner;

public class RecursiveInsertionSort {
    public static void recursiveInsertionSort(int[] array, int start, int end) {
        if (start < end) {
            int key = array[start + 1];
            int j = start;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
            recursiveInsertionSort(array, start + 1, end);
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
        recursiveInsertionSort(array, 0, length - 1);
        printArray(array);
        scanner.close();
    }
}
