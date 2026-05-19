import java.util.Arrays;

public class InversionCountMergeSort {
    public static int mergeAndCount(int[] arr, int left, int mid, int right) {
        int[] L = Arrays.copyOfRange(arr, left, mid + 1);
        int[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left, swaps = 0;
        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
                swaps += (mid + 1) - (left + i); // Count the inversion
            }
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
        
        return swaps;
    }

    public static int sortAndCount(int[] arr, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            count += sortAndCount(arr, left, mid);
            count += sortAndCount(arr, mid + 1, right);
            count += mergeAndCount(arr, left, mid, right);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] numbers = {8, 4, 2, 1};
        System.out.println("Original array: " + Arrays.toString(numbers));
        int inversions = sortAndCount(numbers, 0, numbers.length - 1);
        System.out.println("Sorted array:   " + Arrays.toString(numbers));
        System.out.println("Total Inversions: " + inversions);
    }
}