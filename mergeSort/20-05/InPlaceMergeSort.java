import java.util.Arrays;

public class InPlaceMergeSort {
    public static void merge(int[] arr, int left, int mid, int right) {
        int mid2 = mid + 1;

        // If the direct merge is already sorted
        if (arr[mid] <= arr[mid2]) {
            return;
        }

        while (left <= mid && mid2 <= right) {
            if (arr[left] <= arr[mid2]) {
                left++;
            } else {
                int value = arr[mid2];
                int index = mid2;

                // Shift all the elements to the right by 1
                while (index != left) {
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[left] = value;

                // Update all the pointers
                left++;
                mid++;
                mid2++;
            }
        }
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
        int[] numbers = {10, 5, 2, 8, 3, 9, 1, 4};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers, 0, numbers.length - 1);
        System.out.println("In-Place Sorted: " + Arrays.toString(numbers));
    }
}