import java.util.Arrays;

/**
 * PROBLEM: Hybrid Radix Quick Sort
 * * Optimize string sorting performance for highly skewed, non-uniform datasets.
 * * Strategy: Width Adaptive Fallback
 * Combine MSD character processing with an adaptive fallback mechanism. 
 * If a sub-bucket remains large but contains wide, sparse character distributions, switch from counting sort 
 * to a memory-efficient 3-way quicksort partitioning step to avoid allocating large, empty count matrices.
 */
public class HybridRadixQuickSort {
    private static final int COUNT_THRESHOLD = 32;

    public static void hybridSort(String[] arr) {
        sort(arr, 0, arr.length - 1, 0);
    }

    private static int charAt(String s, int d) {
        return (d < s.length()) ? s.charAt(d) : -1;
    }

    private static void sort(String[] arr, int low, int high, int d) {
        if (high <= low) return;

        // Switch to a 3-way quicksort partition if the bucket size drops below the threshold
        if (high - low < COUNT_THRESHOLD) {
            threeWayQuickSort(arr, low, high, d);
            return;
        }

        int[] count = new int[258];
        String[] aux = new String[high - low + 1];

        for (int i = low; i <= high; i++) count[charAt(arr[i], d) + 2]++;
        for (int r = 0; r < 257; r++) count[r + 1] += count[r];
        for (int i = low; i <= high; i++) aux[count[charAt(arr[i], d) + 1]++] = arr[i];
        for (int i = low; i <= high; i++) arr[i] = aux[i - low];

        for (int r = 0; r < 256; r++) {
            sort(arr, low + count[r], low + count[r + 1] - 1, d + 1);
        }
    }

    private static void threeWayQuickSort(String[] arr, int low, int high, int d) {
        if (high <= low) return;
        int lt = low, gt = high;
        int pivot = charAt(arr[low], d);
        int i = low + 1;
        while (i <= gt) {
            int t = charAt(arr[i], d);
            if (t < pivot) swap(arr, lt++, i++);
            else if (t > pivot) swap(arr, i, gt--);
            else i++;
        }
        threeWayQuickSort(arr, low, lt - 1, d);
        if (pivot >= 0) threeWayQuickSort(arr, lt, gt, d + 1);
        threeWayQuickSort(arr, gt + 1, high, d);
    }

    private static void swap(String[] arr, int i, int j) {
        String t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }

    public static void main(String[] args) {
        String[] arr = {"banana", "apple", "apricot", "cherry", "blueberry", "citrus"};
        hybridSort(arr);
        System.out.println("Hybrid Radix-Quicksort Outcome: " + Arrays.toString(arr));
    }
}