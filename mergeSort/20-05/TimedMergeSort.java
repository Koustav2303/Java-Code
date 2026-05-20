import java.util.Arrays;

public class TimedMergeSort {
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
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {100, 43, 65, 23, 98, 12, 5, 87, 34, 56};
        System.out.println("Original array: " + Arrays.toString(numbers));
        
        long startTime = System.nanoTime();
        
        sort(numbers, 0, numbers.length - 1);
        
        long endTime = System.nanoTime();
        
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
        System.out.println("Merge Sort completed in: " + (endTime - startTime) + " nanoseconds.");
    }
}