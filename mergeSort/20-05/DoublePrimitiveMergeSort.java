import java.util.Arrays;

public class DoublePrimitiveMergeSort {
    public static void merge(double[] arr, int left, int mid, int right) {
        double[] L = Arrays.copyOfRange(arr, left, mid + 1);
        double[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    public static void sort(double[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        double[] decimals = {3.14, 1.59, 2.65, 3.58, 9.79};
        System.out.println("Original array: " + Arrays.toString(decimals));
        sort(decimals, 0, decimals.length - 1);
        System.out.println("Sorted array:   " + Arrays.toString(decimals));
    }
}