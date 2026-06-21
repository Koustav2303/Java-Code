import java.util.Arrays;

/**
 * PROBLEM: Binary Radix Sort
 * * Sort an array of non-negative integers by evaluating their binary representation exactly 1 bit at a time.
 * * Strategy: 32-Pass Single-Bit Stable Sieve
 * Treat the input data as base-2 values. Loop exactly 32 times (for 32-bit signed integers). 
 * For each pass, execute a stable counting sort using a bucket array of size 2 (representing bit values 0 and 1). 
 * Use bit-shifting and a mask of 1: `(arr[i] >> shift) & 1` to isolate the target bit.
 * * Complexity:
 * Time Complexity: O(N) since the pass count is fixed at 32.
 * Space Complexity: O(N) for the auxiliary stable tracking array.
 */
public class BinaryRadixSort {
    public static void binaryRadixSort(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;

        int[] output = new int[n];

        // Process each of the 32 bits from least significant to most significant
        for (int shift = 0; shift < 31; shift++) {
            int[] count = new int[2]; // Two buckets: 0 and 1

            // Count occurrences of 0 and 1 bits at the current shift position
            for (int i = 0; i < n; i++) {
                int bit = (arr[i] >> shift) & 1;
                count[bit]++;
            }

            // Transform counts to cumulative prefix boundaries
            count[1] += count[0];

            // Build output array backwards to preserve stability
            for (int i = n - 1; i >= 0; i--) {
                int bit = (arr[i] >> shift) & 1;
                output[count[bit] - 1] = arr[i];
                count[bit]--;
            }

            // Sync the stable iteration step back to our working array
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = {43, 3, 12, 8, 90, 21, 0, 5};
        binaryRadixSort(arr);
        System.out.println("Binary Bitwise Sorted: " + Arrays.toString(arr));
    }
}