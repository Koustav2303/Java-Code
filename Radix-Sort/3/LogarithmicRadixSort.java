import java.util.Arrays;

/**
 * PROBLEM: Logarithmic Radix Sort
 * * Implement an adaptive integer radix sort that dynamically sets its base size as a function of the array size.
 * * Strategy: Square Root Base Scaling
 * To balance the number of sorting passes against memory bucket allocations, set the base radix 
 * dynamically to $R = 2^B$, where $B = \max(1, \lfloor \log_2(\sqrt{N}) \rfloor)$. 
 * This matches the base configuration to the scale of the input data, keeping memory overhead low.
 */
public class LogarithmicRadixSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;

        int bestB = Math.max(1, (int) (Math.log(Math.sqrt(n)) / Math.log(2)));
        bestB = Math.min(bestB, 16); // Caps maximum radix size to 65536 to protect L2 cache allocations

        int radix = 1 << bestB;
        int mask = radix - 1;

        int max = arr[0];
        for (int val : arr) max = Math.max(max, val);
        int bitWidth = 32 - Integer.numberOfLeadingZeros(max);

        int[] output = new int[n];

        for (int shift = 0; shift < bitWidth; shift += bestB) {
            int[] count = new int[radix];

            for (int i = 0; i < n; i++) count[(arr[i] >> shift) & mask]++;
            for (int i = 1; i < radix; i++) count[i] += count[i - 1];
            for (int i = n - 1; i >= 0; i--) {
                int val = (arr[i] >> shift) & mask;
                output[count[val] - 1] = arr[i];
                count[val]--;
            }
            System.arraycopy(output, 0, arr, 0, n);
        }
    }

    public static void main(String[] args) {
        int[] arr = {743, 12, 904, 2, 0, 23, 943, 11};
        sort(arr);
        System.out.println("Logarithmic Radix Sorted: " + Arrays.toString(arr));
    }
}