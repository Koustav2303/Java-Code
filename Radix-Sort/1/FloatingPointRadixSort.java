import java.util.Arrays;

/**
 * PROBLEM: Floating Point Radix Sort
 * * Sort an array of floating-point numbers using radix sort without comparison operators.
 * * Strategy: IEEE-754 Complement Transformations
 * Floating-point binary layouts (IEEE-754) do not sort naturally like integers due to sign bits 
 * and negative structures. Map each float to its raw bit integer representation using `Float.floatToIntBits()`. 
 * Transform the bits to establish an ordered unsigned sequence:
 * - If positive: flip only the sign bit.
 * - If negative: flip all bits to reverse the inverse magnitude sequence.
 * Sort the data using a 4-pass bitwise integer radix sort, then unflip the bits back to restore the floats.
 */
public class FloatingPointRadixSort {
    public static void sortFloats(float[] arr) {
        int n = arr.length;
        int[] intBits = new int[n];

        for (int i = 0; i < n; i++) {
            int bits = Float.floatToIntBits(arr[i]);
            // If sign bit is active (negative float), flip all bits. Otherwise flip only the sign bit.
            if ((bits & 0x80000000) != 0) {
                intBits[i] = bits ^ 0xffffffff;
            } else {
                intBits[i] = bits ^ 0x80000000;
            }
        }

        // Perform stable bitwise integer radix sort passes
        bitwiseSort(intBits);

        // Convert the transformed bits back into float representations
        for (int i = 0; i < n; i++) {
            int bits = intBits[i];
            if ((bits & 0x80000000) == 0) {
                bits = bits ^ 0xffffffff;
            } else {
                bits = bits ^ 0x80000000;
            }
            arr[i] = Float.intBitsToFloat(bits);
        }
    }

    private static void bitwiseSort(int[] arr) {
        int n = arr.length;
        int[] output = new int[n];
        for (int shift = 0; shift < 32; shift += 8) {
            int[] count = new int[256];
            for (int i = 0; i < n; i++) count[(arr[i] >> shift) & 0xFF]++;
            for (int i = 1; i < 256; i++) count[i] += count[i - 1];
            for (int i = n - 1; i >= 0; i--) {
                int b = (arr[i] >> shift) & 0xFF;
                output[count[b] - 1] = arr[i];
                count[b]--;
            }
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        float[] arr = {-23.5f, 3.4f, -0.5f, 100.2f, 0.0f, -0.1f};
        sortFloats(arr);
        System.out.println("Sorted Floating Points: " + Arrays.toString(arr));
    }
}