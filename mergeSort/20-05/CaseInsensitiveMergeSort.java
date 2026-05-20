import java.util.Arrays;

public class CaseInsensitiveMergeSort {
    public static void merge(String[] arr, int left, int mid, int right) {
        String[] L = Arrays.copyOfRange(arr, left, mid + 1);
        String[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            // Using compareToIgnoreCase instead of compareTo
            if (L[i].compareToIgnoreCase(R[j]) <= 0) {
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
        String[] words = {"banana", "Apple", "cherry", "Apricot"};
        System.out.println("Original array: " + Arrays.toString(words));
        sort(words, 0, words.length - 1);
        System.out.println("Case-Insensitive Sorted: " + Arrays.toString(words));
    }
}