import java.util.Arrays;

/**
 * PROBLEM: Radix Sort With Insertion Sort
 * * Optimize a Most Significant Digit (MSD) string radix sort by eliminating recursive call overhead 
 * on small, sub-partition string buckets.
 * * Strategy: Sub-Bucket Short-Circuit Switch
 * MSD radix sort splits data into increasingly smaller sub-buckets. 
 * Recursion overhead can quickly become a bottleneck for tiny buckets. 
 * Set a threshold parameter (e.g., 12 elements). When a bucket's size drops below this limit, 
 * switch to an in-place insertion sort to handle the remaining elements efficiently.
 */
public class RadixSortWithInsertionSort {
    private static final int R = 256;
    private static final int CUTOFF = 12; // Short-circuit cutoff threshold parameter

    public static void sort(String[] arr) {
        String[] aux = new String[arr.length];
        sort(arr, 0, arr.length - 1, 0, aux);
    }

    private static int charAt(String s, int d) {
        return (d < s.length()) ? s.charAt(d) : -1;
    }

    private static void sort(String[] arr, int low, int high, int d, String[] aux) {
        // Switch to insertion sort for small sub-bucket partitions
        if (high - low <= CUTOFF) {
            insertionSort(arr, low, high, d);
            return;
        }

        int[] count = new int[R + 2];
        for (int i = low; i <= high; i++) count[charAt(arr[i], d) + 2]++;
        for (int r = 0; r < R + 1; r++) count[r + 1] += count[r];
        for (int i = low; i <= high; i++) aux[count[charAt(arr[i], d) + 1]++] = arr[i];
        for (int i = low; i <= high; i++) arr[i] = aux[i - low];

        for (int r = 0; r < R; r++) {
            sort(arr, low + count[r], low + count[r + 1] - 1, d + 1, aux);
        }
    }

    private static void insertionSort(String[] arr, int low, int high, int d) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > low && less(arr[j], arr[j - 1], d); j--) {
                String temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        for (int i = d; i < Math.max(v.length(), w.length()); i++) {
            int c1 = charAt(v, i); int c2 = charAt(w, i);
            if (c1 != c2) return c1 < c2;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] arr = {"zebra", "apple", "banana", "app", "apricot", "zinc", "fox", "egg"};
        sort(arr);
        System.out.println("Hybrid MSD Sorted: " + Arrays.toString(arr));
    }
}