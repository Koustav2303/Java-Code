import java.util.Arrays;

public class GenericMergeSort {
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
        Object[] L = Arrays.copyOfRange(arr, left, mid + 1);
        Object[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            if (((T) L[i]).compareTo((T) R[j]) <= 0) {
                arr[k++] = (T) L[i++];
            } else {
                arr[k++] = (T) R[j++];
            }
        }
        while (i < L.length) arr[k++] = (T) L[i++];
        while (j < R.length) arr[k++] = (T) R[j++];
    }

    public static <T extends Comparable<T>> void sort(T[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        Double[] decimals = {9.9, 1.2, 5.5, 3.4, 7.8};
        System.out.println("Original array: " + Arrays.toString(decimals));
        sort(decimals, 0, decimals.length - 1);
        System.out.println("Sorted array:   " + Arrays.toString(decimals));
    }
}