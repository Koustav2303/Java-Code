import java.util.Arrays;

/**
 * PROBLEM: String Huge Integer Radix Sort
 * * Sort an array of massive numeric string integers (e.g., 50 digits each) that exceed standard 32-bit 
 * or 64-bit primitive data type bounds.
 * * Strategy: Left-Zero Padded LSD String Sieve
 * Find the maximum string length among the numeric items. Pad shorter strings with leading zeros 
 * so that all elements share a uniform digit length. Run an LSD radix sort pass across the character 
 * index columns from right to left using a base-10 digit bucket matrix.
 */
public class StringHugeIntRadixSort {
    public static void sortHugeIntegers(String[] arr) {
        int n = arr.length;
        int maxLen = 0;
        for (String s : arr) maxLen = Math.max(maxLen, s.length());

        // Pad shorter strings with leading zeros to align digit columns
        for (int i = 0; i < n; i++) {
            if (arr[i].length() < maxLen) {
                StringBuilder sb = new StringBuilder();
                while (sb.length() < maxLen - arr[i].length()) {
                    sb.append('0');
                }
                sb.append(arr[i]);
                arr[i] = sb.toString();
            }
        }

        String[] output = new String[n];

        // Run LSD radix sort passes across the aligned digit columns from right to left
        for (int d = maxLen - 1; d >= 0; d--) {
            int[] count = new int[10]; // Buckets for digits 0 through 9

            for (int i = 0; i < n; i++) {
                int digit = arr[i].charAt(d) - '0';
                count[digit]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int digit = arr[i].charAt(d) - '0';
                output[count[digit] - 1] = arr[i];
                count[digit]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        String[] hugeInts = {"900000000000000045", "123", "900000000000000012", "456789"};
        sortHugeIntegers(hugeInts);
        System.out.println("Huge Integers Radix Sorted:\n" + Arrays.toString(hugeInts));
    }
}