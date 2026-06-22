import java.util.Arrays;

/**
 * PROBLEM: Double Precision Radix Sort
 * * Sort an array of double-precision floating-point numbers without comparison operators.
 * * Strategy: IEEE-754 Bitwise Complement Transform
 * Map doubles into raw 64-bit integer bits using Double.doubleToLongBits().
 * Adjust the bits to establish a naturally ordered unsigned sequence:
 * - If positive: flip the most significant sign bit.
 * - If negative: flip all bits to correct the inverse magnitude sequence.
 * Run an 8-pass bitwise radix sort (8 bits per pass across all 64 bits), then unflip the bits to restore the doubles.
 */
public class DoublePrecisionRadixSort {
    public static void sortDoubles(double[] arr) {
        int n = arr.length;
        long[] longBits = new long[n];

        for (int i = 0; i < n; i++) {
            long bits = Double.doubleToLongBits(arr[i]);
            // Sign bit check (bit 63)
            if ((bits & 0x8000000000000000L) != 0) {
                longBits[i] = bits ^ 0xFFFFFFFFFFFFFFFFL;
            } else {
                longBits[i] = bits ^ 0x8000000000000000L;
            }
        }

        longBitsRadixPass(longBits);

        for (int i = 0; i < n; i++) {
            long bits = longBits[i];
            if ((bits & 0x8000000000000000L) == 0) {
                bits = bits ^ 0xFFFFFFFFFFFFFFFFL;
            } else {
                bits = bits ^ 0x8000000000000000L;
            }
            arr[i] = Double.longBitsToDouble(bits);
        }
    }

    private static void longBitsRadixPass(long[] arr) {
        int n = arr.length;
        long[] output = new long[n];

        // 8 passes required to clear all 64 bits total (8 bits per chunk pass)
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
        double[] arr = {-54.32, 100.45, -0.001, 0.0, 45.32, -1200.5};
        sortDoubles(arr);
        System.out.println("64-Bit Float Radix Sorted: " + Arrays.toString(arr));
    }
}