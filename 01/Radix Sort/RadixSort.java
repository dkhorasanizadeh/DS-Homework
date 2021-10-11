
// --------------------------------------------------
// Name    : Radix Sort
// Author  : Danial Khorasanizadeh
// Course  : Data Structures - Fall 1400
// Date    : 11/Oct/2021
// --------------------------------------------------

import java.util.Arrays;
import java.util.Scanner;

public class RadixSort {
    static void countSort(int[] array, int length, int position) {
        int[] result = new int[length];
        int[] count = new int[10];
        Arrays.fill(count, 0);
        for (int i = 0; i < length; i++) {
            count[(array[i] / position) % 10]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = length - 1; i >= 0; i--) {
            result[count[(array[i] / position) % 10] - 1] = array[i];
            count[(array[i] / position) % 10]--;
        }
        for (int i = 0; i < length; i++) {
            array[i] = result[i];
        }
    }

    public static void radixSort(int[] array, int length) {
        int maxNum = Arrays.stream(array).max().getAsInt();
        for (int position = 1; maxNum / position > 0; position *= 10) {
            countSort(array, length, position);
        }
    }

    static void printArray(int[] array, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    static void reversePrintArray(int[] array, int length) {
        for (int i = length - 1; i >= 0; i--) {
            System.out.print("-" + array[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] array = new int[length];
        int[] negativeArray = new int[length];
        int num, j = 0, k = 0;
        for (int i = 0; i < length; i++) {
            num = scanner.nextInt();
            if (num >= 0) {
                array[j] = num;
                j++;
            } else {
                negativeArray[k] = -1 * num;
                k++;
            }
        }
        radixSort(array, j);
        radixSort(negativeArray, k);
        reversePrintArray(negativeArray, k);
        printArray(array, j);
        scanner.close();
    }

}
