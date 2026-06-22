import java.util.Arrays;

/**
 * PROBLEM: Unsigned Integer Radix Sort
 * * Sort an integer array where values are treated explicitly as unsigned 32-bit integers, 
 * meaning the most significant bit (bit 31) acts as a magnitude indicator rather than a negative sign.
 * * Strategy: Sign Bit Transformation Shift
 * Standard signed radix sort misorders unsigned integers because bit 31 is treated as a negative indicator. 
 * To fix this, run a standard 4-pass byte radix sort for the first 3 passes. On the final pass (bytes 24-31), 
 * transform the extracted byte values by XORing them with `0x80` to toggle the sign bit behavior, 
 * correctly grouping unsigned magnitudes.
 */
public class UnsignedIntRadixSort {
    public static void sortUnsigned(int[] arr) {
        int n = arr.length;
        int[] output = new int[n];

        for (int shift = 0; shift < 32; shift += 8) {
            int[] count = new int[256];

            for (int i = 0; i < n; i++) {
                int byteVal = (arr[i] >> shift) & 0xFF;
                // On the final pass, invert the most significant sign bit to handle unsigned ordering
                if (shift == 24) {
                    byteVal ^= 0x80;
                }
                count[byteVal]++;
            }

            for (int i = 1; i < 256; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int byteVal = (arr[i] >> shift) & 0xFF;
                if (shift == 24) {
                    byteVal ^= 0x80;
                }
                output[count[byteVal] - 1] = arr[i];
                count[byteVal]--;
            }
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        // -1 in signed notation has the exact same bit layout as the maximum unsigned 32-bit integer (4,294,967,295)
        int[] arr = {Integer.MAX_VALUE, 0, -1, 45, -100};
        sortUnsigned(arr);
        
        System.out.print("Unsigned Radix Value Order: ");
        for (int val : arr) {
            System.out.print(Integer.toUnsignedString(val) + " ");
        }
        System.out.println();
    }
}