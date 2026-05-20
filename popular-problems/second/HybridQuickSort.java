import java.util.Arrays;

public class HybridQuickSort {
    private static final int THRESHOLD = 10;

    public static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void sort(int[] arr, int low, int high) {
        // Condition: If array segment is small, use Insertion Sort
        if (high - low < THRESHOLD) {
            insertionSort(arr, low, high);
            return;
        }
        
        // Otherwise use Quick Sort
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {88, 22, 11, 99, 44, 33, 77, 55, 66, 0, 15, 29, 3, 4};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers, 0, numbers.length - 1);
        System.out.println("Hybrid Sorted:  " + Arrays.toString(numbers));
    }
}