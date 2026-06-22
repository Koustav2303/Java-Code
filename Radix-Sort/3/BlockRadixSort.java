import java.util.Arrays;

/**
 * PROBLEM: Block Radix Sort
 * * Optimize an integer radix sort to maximize CPU L1/L2 cache locality and mitigate TLB misses.
 * * Strategy: Cache Tiling Block Deconstruction
 * Standard radix sort over large arrays causes cache trashing due to random writes into 256 distinct buckets. 
 * Partition the array into small, cache-sized "blocks" or "tiles" (e.g., 2048 elements). 
 * Compute the histogram pass globally, but execute the prefix write distribution block by block 
 * to preserve spatial data proximity inside cache lines.
 */
public class BlockRadixSort {
    private static final int BLOCK_SIZE = 2048; // Fits comfortably inside L1 data cache

    public static void sort(int[] arr) {
        int n = arr.length;
        int[] output = new int[n];

        // 4 passes for 32-bit integers (8 bits per byte pass)
        for (int shift = 0; shift < 32; shift += 8) {
            int[] count = new int[256];

            // Global histogram collection pass
            for (int i = 0; i < n; i++) {
                count[(arr[i] >> shift) & 0xFF]++;
            }

            int[] offsets = new int[256];
            for (int i = 1; i < 256; i++) {
                offsets[i] = offsets[i - 1] + count[i - 1];
            }

            // Tiled layout write pass to ensure data locality
            for (int blockStart = 0; blockStart < n; blockStart += BLOCK_SIZE) {
                int blockEnd = Math.min(blockStart + BLOCK_SIZE, n);
                
                // Process only the current memory tile context block
                for (int i = blockStart; i < blockEnd; i++) {
                    int byteVal = (arr[i] >> shift) & 0xFF;
                    output[offsets[byteVal]++] = arr[i];
                }
            }
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5232, 12, 90432, 121, 0, 4, 8432, 234};
        sort(arr);
        System.out.println("Cache Block Sorted: " + Arrays.toString(arr));
    }
}