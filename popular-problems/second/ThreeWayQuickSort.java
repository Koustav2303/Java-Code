import java.util.Arrays;

public class ThreeWayQuickSort {
    public static void sort(int[] arr, int low, int high) {
        if (low >= high) return;

        int lt = low;      // Left pointer for elements < pivot
        int gt = high;     // Right pointer for elements > pivot
        int i = low + 1;   // Scanning pointer
        int pivot = arr[low];

        while (i <= gt) {
            if (arr[i] < pivot) {
                int temp = arr[lt];
                arr[lt] = arr[i];
                arr[i] = temp;
                lt++;
                i++;
            } else if (arr[i] > pivot) {
                int temp = arr[i];
                arr[i] = arr[gt];
                arr[gt] = temp;
                gt--;
            } else {
                i++; // If equal to pivot, just move the scanning pointer
            }
        }

        // Only sort the strictly less and strictly greater segments
        sort(arr, low, lt - 1);
        sort(arr, gt + 1, high);
    }

    public static void main(String[] args) {
        int[] numbers = {4, 9, 4, 4, 1, 9, 4, 4, 1, 1}; // Lots of duplicates
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers, 0, numbers.length - 1);
        System.out.println("3-Way Sorted:   " + Arrays.toString(numbers));
    }
}