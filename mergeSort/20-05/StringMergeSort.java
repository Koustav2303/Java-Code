import java.util.Arrays;

public class StringMergeSort {
    public static void merge(String[] arr, int left, int mid, int right) {
        String[] L = Arrays.copyOfRange(arr, left, mid + 1);
        String[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    public static void sort(String[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        String[] words = {"Pineapple", "Apple", "Orange", "Banana", "Grape"};
        System.out.println("Original array: " + Arrays.toString(words));
        sort(words, 0, words.length - 1);
        System.out.println("Sorted array:   " + Arrays.toString(words));
    }
}