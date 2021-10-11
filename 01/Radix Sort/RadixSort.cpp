// --------------------------------------------------
// Name    : Radix Sort
// Author  : Danial Khorasanizadeh
// Course  : Data Structures - Fall 1400
// Date    : 11/Oct/2021
// --------------------------------------------------

#include <algorithm>
#include <iostream>

void countSort(long array[], int length, int position) {
  long result[length] = {0};
  int count[10] = {0};
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

void radixSort(long array[], int length) {
  int maxNum = *std::max_element(array, array + length);
  for (int position = 1; maxNum / position > 0; position *= 10) {
    countSort(array, length, position);
  }
}

static void printArray(long array[], int length) {
  for (int i = 0; i < length; i++) {
    std::cout << array[i] << " ";
  }
}

static void reversePrintArray(long array[], int length) {
  for (int i = length - 1; i >= 0; i--) {
    std::cout << "-" << array[i] << " ";
  }
}

int main(void) {
  int length;
  std::cin >> length;
  long array[length];
  long negativeArray[length];
  int num, j = 0, k = 0;
  for (int i = 0; i < length; i++) {
    std::cin >> num;
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
}