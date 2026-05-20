import java.util.Arrays;

public class CharMergeSort {
    public static void merge(char[] arr, int left, int mid, int right) {
        char[] L = Arrays.copyOfRange(arr, left, mid + 1);
        char[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

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

    public static void sort(char[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        char[] letters = {'z', 'x', 'a', 'm', 'b', 'q'};
        System.out.println("Original letters: " + Arrays.toString(letters));
        sort(letters, 0, letters.length - 1);
        System.out.println("Sorted letters:   " + Arrays.toString(letters));
    }
}