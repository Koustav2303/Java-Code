import java.util.Arrays;
import java.util.Comparator;

public class ComparatorMergeSort {
    public static void merge(String[] arr, int left, int mid, int right, Comparator<String> comp) {
        String[] L = Arrays.copyOfRange(arr, left, mid + 1);
        String[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            // Apply the custom comparator
            if (comp.compare(L[i], R[j]) <= 0) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    public static void sort(String[] arr, int left, int right, Comparator<String> comp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid, comp);
            sort(arr, mid + 1, right, comp);
            merge(arr, left, mid, right, comp);
        }
    }

    public static void main(String[] args) {
        String[] words = {"Elephant", "Cat", "Hippopotamus", "Dog", "Fish"};
        System.out.println("Original array: " + Arrays.toString(words));
        
        // Sorting by length using a lambda expression
        sort(words, 0, words.length - 1, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        
        System.out.println("Sorted by length: " + Arrays.toString(words));
    }
}