import java.util.Arrays;

public class DescendingMergeSort {
    public static void merge(int[] arr, int left, int mid, int right) {
        int[] L = Arrays.copyOfRange(arr, left, mid + 1);
        int[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            if (L[i] >= R[j]) { // Flipped to >= for descending order
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
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
        int[] numbers = {4, 2, 9, 6, 23, 12, 34, 0, 1};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers, 0, numbers.length - 1);
        System.out.println("Sorted descending: " + Arrays.toString(numbers));
    }
}