import java.util.Arrays;

/**
 * PROBLEM: Most Significant Digit Radix Sort
 * * Implement a Most Significant Digit (MSD) Radix Sort to alphabetize strings of varying lengths.
 * * Strategy: Lexicographical Recursive Partitioning
 * Process characters from left to right starting at index 0. 
 * Use counting sort to group strings by their character at the current index position. 
 * Handle varying lengths by treating a missing character position as an explicit padding value of -1. 
 * Recursively sort each resulting character bucket independently.
 */
public class MostSignificantDigitRadixSort {
    private static final int R = 256; // Extended ASCII Alphabet Radix Size

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        return -1; // Pad shorter strings with a terminator value to ensure proper sorting order
    }

    public static void sort(String[] arr) {
        String[] aux = new String[arr.length];
        sortRecursive(arr, 0, arr.length - 1, 0, aux);
    }

    private static void sortRecursive(String[] arr, int low, int high, int d, String[] aux) {
        if (high <= low) return;

        int[] count = new int[R + 2]; // Include offset padding margins safely

        // Compute local character frequency counts
        for (int i = low; i <= high; i++) {
            int c = charAt(arr[i], d);
            count[c + 2]++;
        }

        // Transform frequencies to cumulative totals
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        // Distribute elements into auxiliary storage buckets
        for (int i = low; i <= high; i++) {
            int c = charAt(arr[i], d);
            aux[count[c + 1]++] = arr[i];
        }

        // Copy auxiliary frame targets back to original array references
        for (int i = low; i <= high; i++) {
            arr[i] = aux[i - low];
        }

        // Recursively sort each character bucket independently
        for (int r = 0; r < R; r++) {
            sortRecursive(arr, low + count[r], low + count[r + 1] - 1, d + 1, aux);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"sea", "seashore", "she", "shells", "shore", "are", "sea"};
        sort(arr);
        System.out.println("MSD Variable String Sort Outcome: " + Arrays.toString(arr));
    }
}