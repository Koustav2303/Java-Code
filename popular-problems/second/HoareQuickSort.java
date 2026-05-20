import java.util.Arrays;

public class HoareQuickSort {
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do { i++; } while (arr[i] < pivot);
            do { j--; } while (arr[j] > pivot);

            if (i >= j) return j;

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            // Note: Hoare's includes the pivot in the left subarray
            sort(arr, low, pi); 
            sort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {12, 11, 13, 5, 6, 7};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers, 0, numbers.length - 1);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
    }
}