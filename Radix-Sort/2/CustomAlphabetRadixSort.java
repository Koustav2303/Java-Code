import java.util.Arrays;

/**
 * PROBLEM: Custom Alphabet Radix Sort
 * * Alphabetize an array of uniform-length strings based on a non-standard, user-defined character order 
 * instead of standard ASCII ordering.
 * * Strategy: Rank-Mapping LSD Array Matrix
 * Map each character in the custom alphabet to its priority rank index inside a 256-sized integer array. 
 * Run an LSD radix sort starting from the rightmost character position, sorting characters based on 
 * their custom priority rank instead of raw ASCII code values.
 */
public class CustomAlphabetRadixSort {
    public static void sortWithAlphabet(String[] arr, String alphabet, int fixedLength) {
        int n = arr.length;
        int[] rankMap = new int[256];
        
        // Populate custom alphabetical sorting rank weights
        for (int i = 0; i < alphabet.length(); i++) {
            rankMap[alphabet.charAt(i)] = i;
        }

        String[] output = new String[n];
        int radixSize = alphabet.length();

        // Standard LSD backwards loop
        for (int d = fixedLength - 1; d >= 0; d--) {
            int[] count = new int[radixSize];

            for (int i = 0; i < n; i++) {
                int rank = rankMap[arr[i].charAt(d)];
                count[rank]++;
            }

            for (int i = 1; i < radixSize; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int rank = rankMap[arr[i].charAt(d)];
                output[count[rank] - 1] = arr[i];
                count[rank]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"BAC", "BCA", "ABC", "ACB", "CAB", "CBA"};
        String customAlphabet = "CBA"; // Custom priority: 'C' < 'B' < 'A'
        sortWithAlphabet(arr, customAlphabet, 3);
        System.out.println("Custom Alphabet Sorted: " + Arrays.toString(arr)); // Strings starting with C come first
    }
}