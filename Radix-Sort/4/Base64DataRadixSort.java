import java.util.Arrays;

/**
 * PROBLEM: Base64 Data Radix Sort
 * * Sort an array of uniform-length Base64 encoded strings lexicographically matching the standard Base64 index table.
 * * Strategy: 64-Radix Rank Conversion
 * Map standard Base64 characters ('A'-'Z', 'a'-'z', '0'-'9', '+', '/') to their literal positional indices (0 to 63).
 * Run an LSD radix sort pass moving from the rightmost character back to index 0, utilizing a fixed bucket size of 64.
 * * Complexity:
 * Time Complexity: O(N * K) where K is the length of the string.
 * Space Complexity: O(N)
 */
public class Base64DataRadixSort {
    private static final int RADIX = 64;
    private static final int[] RANK_MAP = new int[256];

    static {
        int rank = 0;
        for (char c = 'A'; c <= 'Z'; c++) RANK_MAP[c] = rank++;
        for (char c = 'a'; c <= 'z'; c++) RANK_MAP[c] = rank++;
        for (char c = '0'; c <= '9'; c++) RANK_MAP[c] = rank++;
        RANK_MAP['+'] = rank++;
        RANK_MAP['/'] = rank;
    }

    public static void sortBase64(String[] arr, int length) {
        int n = arr.length;
        String[] output = new String[n];

        for (int d = length - 1; d >= 0; d--) {
            int[] count = new int[RADIX];

            for (int i = 0; i < n; i++) {
                count[RANK_MAP[arr[i].charAt(d)]]++;
            }

            for (int i = 1; i < RADIX; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int rank = RANK_MAP[arr[i].charAt(d)];
                output[count[rank] - 1] = arr[i];
                count[rank]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        String[] arr = {"bA++", "AA//", "0012", "ZZZZ", "AA++"};
        sortBase64(arr, 4);
        System.out.println("Base64 Radix Sorted: " + Arrays.toString(arr));
    }
}