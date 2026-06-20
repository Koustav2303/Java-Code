import java.util.Arrays;

/**
 * PROBLEM: Negative Numbers Radix Sort
 * * Implement Radix Sort to correctly handle arrays containing both positive and negative integers.
 * * Strategy: Linear Minimal Offset Scaling
 * Standard radix sort breaks when processing negative values due to modulo operator sign issues. 
 * Find the minimum value in the array. If it is negative, shift every element by adding `Math.abs(min)` 
 * to normalize the array into non-negative integers. Run a standard LSD radix sort pass, 
 * then subtract the offset back at the end to restore the original values.
 */
public class NegativeNumbersRadixSort {
    public static void sortMixedIntegers(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int min = arr[0];
        for (int val : arr) if (val < min) min = val;

        // If a negative value exists, shift all elements to make them non-negative
        int offset = 0;
        if (min < 0) {
            offset = Math.abs(min);
            for (int i = 0; i < arr.length; i++) {
                arr[i] += offset;
            }
        }

        // Execute standard LSD processing
        performLsdRadixSort(arr);

        // Reverse the original offset shift transform across the array components
        if (offset > 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= offset;
            }
        }
    }

    private static void performLsdRadixSort(int[] arr) {
        int max = arr[0];
        for (int val : arr) if (val > max) max = val;

        for (int exp = 1; max / exp > 0; exp *= 10) {
            int[] output = new int[arr.length];
            int[] count = new int[10];
            for (int i = 0; i < arr.length; i++) count[(arr[i] / exp) % 10]++;
            for (int i = 1; i < 10; i++) count[i] += count[i - 1];
            for (int i = arr.length - 1; i >= 0; i--) {
                int d = (arr[i] / exp) % 10;
                output[count[d] - 1] = arr[i];
                count[d]--;
            }
            System.arraycopy(output, 0, arr, 0, arr.length);
        }
    }

    public static void main(String[] args) {
        int[] arr = {-5, 2, -10, 102, 0, -1, 55, -23};
        sortMixedIntegers(arr);
        System.out.println("Mixed Integers Outcome: " + Arrays.toString(arr)); // [-23, -10, -5, -1, 0, 2, 55, 102]
    }
}