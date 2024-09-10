package oop.kolodina;

/**
 * class of HeapSort with methods heapify(to sift down elements) and heapsort.
 */
public class HeapSort {
    /**
     *method to sift down non-leaf element until it reaches it's correct position in max-heap.
     *
     *@param arr - input array
     *@param n - number of elements in array
     *@param idx - index of the element we run heapify from
     */
    public static void heapify(int[] arr, int n, int idx) {
        int max = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        if (left < n && arr[left] > arr[max]) {
            max = left;
        }
        if (right < n && arr[right] > arr[max]) {
            max = right;
        }
        if (max != idx) {
            int temp = arr[max];
            arr[max] = arr[idx];
            arr[idx] = temp;
            heapify(arr, n, max);
        }
    }
    /**
     * building the max-heap and retrieving sorted array.
     *
     * @param arr - input array to be sorted
     * @param n - number of elements in array
     */

    public static void heapsort(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int j = n - 1; j >= 0; j--) {
            int temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            heapify(arr, j, 0);
        }
    }
    /**
     *main method.
     *
     *@param args - standart arguments
     */

    public static void main(String[] args) {
        int[] array = {5, 6, 3, 4};
        heapsort(array, array.length);
    }
}