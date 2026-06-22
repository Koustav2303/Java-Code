import java.util.Arrays;

/**
 * PROBLEM: Inverted Radix Sort
 * * Implement an LSD radix sort that orders integers in descending order without reversing the array afterward.
 * * Strategy: Inverted Prefix Aggregation
 * Modify the cumulative prefix sum step of the counting sort pass. 
 * Instead of accumulating from bucket 0 to 9, accumulate backwards from bucket 9 down to 0. 
 * This reverses the target placement mapping indices, routing larger values to the front of the output array.
 */
public class InvertedRadixSort {
    public static void sortDescending(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int max = arr[0];
        for (int val : arr) max = Math.max(max, val);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            int n = arr.length;
            int[] output = new int[n];
            int[] count = new int[10];

            for (int i = 0; i < n; i++) {
                count[(arr[i] / exp) % 10]++;
            }

            // Invert the prefix aggregation direction to reverse the sorted order
            for (int i = 8; i >= 0; i--) {
                count[i] += count[i + 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int digit = (arr[i] / exp) % 10;
                output[count[digit] - 1] = arr[i];
                count[digit]--;
            }

            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = {19, 2, 45, 90, 802, 24, 0, 7};
        sortDescending(arr);
        System.out.println("Descending Radix Sorted: " + Arrays.toString(arr));
    }
}