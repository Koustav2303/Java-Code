import java.util.Arrays;

/**
 * PROBLEM: Packed Integer Radix Sort
 * * Sort composite coordinate pairs packed into a single 64-bit `long` primitive variable. 
 * Each long contains a primary 32-bit integer key in its upper bits, and a secondary 32-bit key in its lower bits.
 * * Strategy: Masked Multi-Byte LSD Extraction
 * Sort the low 32-bit key and high 32-bit key simultaneously by running a standard bitwise radix sort 
 * across the entire 64-bit primitive length. Use 8 sequential passes (8 bits per byte pass) 
 * to handle both embedded key fields in a single execution pipeline.
 */
public class PackedIntegerRadixSort {
    public static void sortPackedLongs(long[] arr) {
        int n = arr.length;
        long[] output = new long[n];

        // 8 passes are required to evaluate all 64 bits of the packed fields
        for (int shift = 0; shift < 64; shift += 8) {
            int[] count = new int[256];

            for (int i = 0; i < n; i++) {
                int byteVal = (int) ((arr[i] >> shift) & 0xFF);
                count[byteVal]++;
            }

            for (int i = 1; i < 256; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int byteVal = (int) ((arr[i] >> shift) & 0xFF);
                output[count[byteVal] - 1] = arr[i];
                count[byteVal]--;
            }
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        // Pack pairs (high, low) using bitwise shifts
        long p1 = ((long) 2 << 32) | (5 & 0xFFFFFFFFL);
        long p2 = ((long) 1 << 32) | (9 & 0xFFFFFFFFL);
        long p3 = ((long) 2 << 32) | (1 & 0xFFFFFFFFL);

        long[] arr = {p1, p2, p3};
        sortPackedLongs(arr);

        System.out.print("Unpacked Sort Outputs: ");
        for (long val : arr) {
            int high = (int) (val >> 32);
            int low = (int) (val & 0xFFFFFFFFL);
            System.out.print("(" + high + "," + low + ") "); // (1,9) (2,1) (2,5)
        }
        System.out.println();
    }
}