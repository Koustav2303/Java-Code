import java.util.Arrays;

/**
 * PROBLEM: Integer MSD Radix Sort
 * * Implement a Most Significant Digit (MSD) Radix Sort working directly on positive integer arrays 
 * via recursive base-10 digit partitioning.
 * * Strategy: Digit Domain Mathematical Splitting
 * Find the highest base-10 power divisor for the maximum element in the array. 
 * Group elements into buckets based on their digit at this position: `(arr[i] / divisor) % 10`. 
 * Recursively sort each non-empty bucket independently for the next lower digit position (`divisor / 10`), 
 * bypassing completed sub-segments to optimize sorting performance.
 */
public class IntegerMSDRadixSort {
    public static void msdRadixSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        
        int max = arr[0];
        for (int val : arr) if (val > max) max = val;

        int startDivisor = 1;
        while (max / startDivisor >= 10) {
            startDivisor *= 10;
        }

        sortBucketRecursive(arr, 0, arr.length - 1, startDivisor);
    }

    private static void sortBucketRecursive(int[] arr, int low, int high, int divisor) {
        if (low >= high || divisor <= 0) return;

        int[] count = new int[11];
        int[] output = new int[high - low + 1];

        for (int i = low; i <= high; i++) {
            int digit = (arr[i] / divisor) % 10;
            count[digit + 1]++;
        }

        for (int r = 0; r < 10; r++) {
            count[r + 1] += count[r];
        }

        for (int i = low; i <= high; i++) {
            int digit = (arr[i] / divisor) % 10;
            output[count[digit]++] = arr[i];
        }

        System.arraycopy(output, 0, arr, low, output.length);

        // Recursively sort each resulting bucket independently
        int startPosition = low;
        for (int r = 0; r < 10; r++) {
            int endPosition = low + count[r] - 1;
            sortBucketRecursive(arr, startPosition, endPosition, divisor / 10);
            startPosition = endPosition + 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {901, 23, 45, 9, 905, 24, 12, 0};
        msdRadixSort(arr);
        System.out.println("MSD Integer Sort Outcome: " + Arrays.toString(arr));
    }
}