import java.util.Arrays;

public class MedianOfThreeQuickSort {
    public static int getMedianPivot(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        
        // Sort the three elements: arr[low], arr[mid], arr[high]
        if (arr[low] > arr[mid]) swap(arr, low, mid);
        if (arr[low] > arr[high]) swap(arr, low, high);
        if (arr[mid] > arr[high]) swap(arr, mid, high);
        
        // Place the median at the high position for Lomuto partition
        swap(arr, mid, high);
        return arr[high];
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = getMedianPivot(arr, low, high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {100, 23, 1, 5, 87, 44, 29, 10, 2};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers, 0, numbers.length - 1);
        System.out.println("Sorted (Median pivot): " + Arrays.toString(numbers));
    }
}