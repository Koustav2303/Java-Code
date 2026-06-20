import java.util.Arrays;

/**
 * PROBLEM: Fixed Length String Radix Sort
 * * Sort an array of strings that all share the exact same character length alphabetically.
 * * Strategy: Right-to-Left Character Scan
 * Since all strings are of equal length, process characters starting from the rightmost index 
 * down to index 0 using an LSD approach. Use an extended ASCII counting sort bucket size of 256 
 * at each position to preserve alphabetical sorting alignments.
 */
public class FixedLengthStringRadixSort {
    public static void sortStrings(String[] arr, int stringLength) {
        int n = arr.length;
        String[] output = new String[n];

        // LSD: Loop from the rightmost character index back to the first character
        for (int d = stringLength - 1; d >= 0; d--) {
            int[] count = new int[256];

            for (int i = 0; i < n; i++) {
                char ch = arr[i].charAt(d);
                count[ch]++;
            }

            for (int i = 1; i < 256; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                char ch = arr[i].charAt(d);
                output[count[ch] - 1] = arr[i];
                count[ch]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"cat", "bat", "rat", "car", "cab", "bar"};
        sortStrings(arr, 3);
        System.out.println("Lexicographically Sorted: " + Arrays.toString(arr)); // [bar, bat, cab, car, cat, rat]
    }
}