import java.util.Arrays;

/**
 * PROBLEM: Hexadecimal Radix Sort
 * * Sort an array of 32-bit integers using base-16 processing loops (4 bits at a time) to balance 
 * bucket space usage and execution loops.
 * * Strategy: Nibble Masking Shift Blocks
 * Instead of performing 32 single-bit passes (base-2) or using a large 256-sized bucket array (base-256), 
 * process integers 4 bits (1 nibble) at a time. This requires exactly 8 passes using a fixed bucket size 
 * of 16. Isolate the target nibble using a bitwise mask of `0x0F` (15).
 */
public class HexadecimalRadixSort {
    public static void hexadecimalRadixSort(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;

        int[] output = new int[n];

        // 8 passes for a 32-bit integer (4 bits per hexadecimal nibble block)
        for (int shift = 0; shift < 32; shift += 4) {
            int[] count = new int[16]; // Base-16 bucket allocations

            for (int i = 0; i < n; i++) {
                int nibbleVal = (arr[i] >> shift) & 0x0F;
                count[nibbleVal]++;
            }

            for (int i = 1; i < 16; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int nibbleVal = (arr[i] >> shift) & 0x0F;
                output[count[nibbleVal] - 1] = arr[i];
                count[nibbleVal]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = {0x1A2B, 0x9, 0xFF, 0xABC, 0x123, 0x0};
        hexadecimalRadixSort(arr);
        System.out.print("Hexadecimal Engine Sort Outcome: ");
        for (int val : arr) System.out.print(Integer.toHexString(val).toUpperCase() + " ");
        System.out.println();
    }
}