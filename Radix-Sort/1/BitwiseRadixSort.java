import java.util.Arrays;

/**
 * PROBLEM: Bitwise Radix Sort
 * * Sort an array of unsigned 32-bit integers using bit manipulation and a base-256 radix system.
 * * Strategy: Bitwise Masking Pass
 * Instead of costly decimal modulo operations, process integers 8 bits (1 byte) at a time. 
 * Shift right by 0, 8, 16, and 24 bits, applying a bitwise AND mask with `0xFF` (255) to isolate the target digit byte. 
 * This requires exactly 4 fast stable counting sort passes.
 * * Complexity:
 * Time Complexity: O(N) since the number of passes is fixed at 4.
 * Space Complexity: O(N) for the auxiliary tracking array.
 */
public class BitwiseRadixSort {
    public static void bitwiseRadixSort(int[] arr) {
        int n = arr.length;
        if (n == 0) return;

        int[] output = new int[n];

        // 4 passes for a 32-bit integer (8 bits per byte chunk)
        for (int shift = 0; shift < 32; shift += 8) {
            int[] count = new int[256];

            // Store frequencies of the current byte segment
            for (int i = 0; i < n; i++) {
                int byteVal = (arr[i] >> shift) & 0xFF;
                count[byteVal]++;
            }

            // Convert to cumulative prefixes
            for (int i = 1; i < 256; i++) {
                count[i] += count[i - 1];
            }

            // Build output array backwards to preserve stability
            for (int i = n - 1; i >= 0; i--) {
                int byteVal = (arr[i] >> shift) & 0xFF;
                output[count[byteVal] - 1] = arr[i];
                count[byteVal]--;
            }

            // Copy output stability frame back to working array
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = {10023, 4, 256, 99, 1024, 0};
        bitwiseRadixSort(arr);
        System.out.println("Bitwise Sorted: " + Arrays.toString(arr)); // [0, 4, 99, 256, 1024, 10023]
    }
}