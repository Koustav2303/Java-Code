import java.util.Arrays;

public class HybridMergeSort {
    private static final int THRESHOLD = 10;

    public static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] L = Arrays.copyOfRange(arr, left, mid + 1);
        int[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    public static void sort(int[] arr, int left, int right) {
        // Use Insertion Sort for small subarrays
        if (right - left < THRESHOLD) {
            insertionSort(arr, left, right);
            return;
        }
        
        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void main(String[] args) {
        int[] numbers = {45, 12, 85, 32, 89, 39, 69, 44, 42, 1, 6, 8, 10, 3, 99};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers, 0, numbers.length - 1);
        System.out.println("Hybrid Sorted:  " + Arrays.toString(numbers));
    }
}