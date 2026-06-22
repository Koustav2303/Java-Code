import java.util.Arrays;

/**
 * PROBLEM: Custom Comparator Radix Sort
 * * Sort strings based on a completely dynamic, runtime-configured alphabet rule sequence without using comparison blocks.
 * * Strategy: Alphabet Rank Re-Mapping
 * Construct a translation array tracking the rank mapping configuration of the dynamic alphabet. 
 * Process strings using an LSD approach from right to left, utilizing the customized rank assignments 
 * to determine prefix sum bucket distributions.
 */
public class CustomComparatorRadixSort {
    public static void sortStrings(String[] arr, String customAlphabet, int stringLength) {
        int n = arr.length;
        int[] order = new int[256];
        for (int i = 0; i < customAlphabet.length(); i++) {
            order[customAlphabet.charAt(i)] = i;
        }

        String[] output = new String[n];
        int bucketSize = customAlphabet.length();

        for (int d = stringLength - 1; d >= 0; d--) {
            int[] count = new int[bucketSize];

            for (int i = 0; i < n; i++) {
                count[order[arr[i].charAt(d)]]++;
            }

            for (int i = 1; i < bucketSize; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int rank = order[arr[i].charAt(d)];
                output[count[rank] - 1] = arr[i];
                count[rank]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"zyx", "xyz", "xxx", "yyy"};
        String customRule = "zyx"; // z is smallest, x is largest
        sortStrings(arr, customRule, 3);
        System.out.println("Custom Priority Sort Result: " + Arrays.toString(arr));
    }
}