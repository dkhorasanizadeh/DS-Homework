public class MergeSort {
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
}
