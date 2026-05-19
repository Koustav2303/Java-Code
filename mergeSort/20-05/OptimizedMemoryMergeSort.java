import java.util.Arrays;

public class OptimizedMemoryMergeSort {
    // Uses a pre-allocated temp array to save memory allocation time
    public static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k++] = temp[i++];
            } else {
                arr[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            arr[k++] = temp[i++];
        }
    }

    public static void sort(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, temp, left, mid);
            sort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {100, 23, 56, 12, 8, 77, 4};
        // Allocate temp array ONCE here
        int[] temp = new int[numbers.length]; 
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers, temp, 0, numbers.length - 1);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
    }
}