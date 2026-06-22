import java.util.Arrays;

/**
 * PROBLEM: Byte Pack Radix Sort
 * * Sort an array of elements packed with four discrete 8-bit metrics inside a single 32-bit integer.
 * * Strategy: Masked Channel Isolation
 * Isolate each packed 8-bit channel using precise bitwise masks and right shifts. Execute exactly 4 stable 
 * counting sort passes (least significant byte channel to most significant byte channel) to sort the integers 
 * across all packed attributes simultaneously.
 */
public class BytePackRadixSort {
    public static void sortPackedBytes(int[] arr) {
        int n = arr.length;
        int[] output = new int[n];

        // 4 passes to isolate each individual byte channel (0, 8, 16, 24 bits)
        for (int shift = 0; shift < 32; shift += 8) {
            int[] count = new int[256];

            for (int i = 0; i < n; i++) {
                int byteChannel = (arr[i] >> shift) & 0xFF;
                count[byteChannel]++;
            }

            for (int i = 1; i < 256; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int byteChannel = (arr[i] >> shift) & 0xFF;
                output[count[byteChannel] - 1] = arr[i];
                count[byteChannel]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        // Packed metrics layout example: [Alpha][Red][Green][Blue]
        int color1 = (255 << 24) | (10 << 16) | (50 << 8) | 20;
        int color2 = (128 << 24) | (5 << 16)  | (50 << 8) | 10;
        int[] arr = {color1, color2};

        sortPackedBytes(arr);
        System.out.println("Packed Byte Matrix Sorted: " + Arrays.toString(arr));
    }
}