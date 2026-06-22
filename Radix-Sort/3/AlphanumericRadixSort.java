import java.util.Arrays;

/**
 * PROBLEM: Alphanumeric Radix Sort
 * * Lexicographically sort strings containing mixed digits (0-9), uppercase (A-Z), and lowercase (a-z) letters 
 * without using comparison-based operators.
 * * Strategy: 62-Radix Bijective Map
 * Establish a strict custom rank mapping over 62 alphanumeric buckets (10 digits + 26 uppercase + 26 lowercase). 
 * Treat missing character slots in shorter strings as a virtual zero-padding layout anchor to preserve stability.
 * * Complexity:
 * Time Complexity: O(N * K) where K is the maximum string length.
 * Space Complexity: O(N) auxiliary space.
 */
public class AlphanumericRadixSort {
    private static final int RADIX = 63; // 62 characters + 1 padding indicator

    private static int getRank(String s, int d) {
        if (d >= s.length()) return 0; // Padding value for shorter strings
        char c = s.charAt(d);
        if (c >= '0' && c <= '9') return 1 + (c - '0');
        if (c >= 'A' && c <= 'Z') return 11 + (c - 'A');
        if (c >= 'a' && c <= 'z') return 37 + (c - 'a');
        return 0;
    }

    public static void sort(String[] arr) {
        int n = arr.length;
        int maxLen = 0;
        for (String s : arr) maxLen = Math.max(maxLen, s.length());

        String[] output = new String[n];

        // Least Significant Digit (LSD) pass moving right-to-left
        for (int d = maxLen - 1; d >= 0; d--) {
            int[] count = new int[RADIX];

            for (int i = 0; i < n; i++) {
                count[getRank(arr[i], d)]++;
            }

            for (int i = 1; i < RADIX; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int rank = getRank(arr[i], d);
                output[count[rank] - 1] = arr[i];
                count[rank]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"a1B", "0aB", "A1b", "a1a", "9zZ", "z"};
        sort(arr);
        System.out.println("Alphanumeric Radix Sorted: " + Arrays.toString(arr));
    }
}