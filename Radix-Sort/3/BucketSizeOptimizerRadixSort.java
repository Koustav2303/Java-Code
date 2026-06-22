import java.util.Arrays;

/**
 * PROBLEM: Bucket Size Optimizer Radix Sort
 * * Automatically select and execute sorting using the theoretically optimal bit-width base $B$ 
 * to sort an integer array in minimal runtime.
 * * Strategy: Radix Runtime Minimization
 * The total execution cost of radix sort matches the relation:
 * $$T(N) = O\left(\frac{W}{B} \cdot (N + 2^B)\right)$$
 * where $W$ is the bit-width of the largest integer. Dynamically evaluate the relationship 
 * between the array size $N$ and candidate values of $B$ to determine the optimal balance 
 * between total passes and bucket overhead.
 */
public class BucketSizeOptimizerRadixSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int max = arr[0];
        for (int val : arr) max = Math.max(max, val);
        if (max == 0) return;

        int bitWidth = 32 - Integer.numberOfLeadingZeros(max);
        int optimalBits = chooseOptimalBitWidth(arr.length, bitWidth);
        
        adaptiveRadixSort(arr, optimalBits, bitWidth);
    }

    private static int chooseOptimalBitWidth(int n, int bitWidth) {
        int bestB = 8; // Default standard base fallback
        double minCost = Double.MAX_VALUE;

        for (int b = 1; b <= 16; b++) {
            double passes = Math.ceil((double) bitWidth / b);
            double currentCost = passes * (n + (1 << b));
            if (currentCost < minCost) {
                minCost = currentCost;
                bestB = b;
            }
        }
        return bestB;
    }

    private static void adaptiveRadixSort(int[] arr, int b, int bitWidth) {
        int n = arr.length;
        int mask = (1 << b) - 1;
        int[] output = new int[n];

        for (int shift = 0; shift < bitWidth; shift += b) {
            int[] count = new int[mask + 1];

            for (int i = 0; i < n; i++) count[(arr[i] >> shift) & mask]++;
            for (int i = 1; i <= mask; i++) count[i] += count[i - 1];
            for (int i = n - 1; i >= 0; i--) {
                int val = (arr[i] >> shift) & mask;
                output[count[val] - 1] = arr[i];
                count[val]--;
            }
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = {45, 12, 89, 4, 10212, 342, 90, 11};
        sort(arr);
        System.out.println("Adaptive Base Sorted: " + Arrays.toString(arr));
    }
}