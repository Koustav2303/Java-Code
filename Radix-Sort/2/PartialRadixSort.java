import java.util.Arrays;

/**
 * PROBLEM: Partial Radix Sort
 * * Optimise standard LSD radix sorting iterations when sorting sub-arrays where higher-order digits 
 * have already established a unique relative order.
 * * Strategy: Early Loop Break Conditions
 * If the maximum element divided by the current exponent multiplier drops to zero, all elements have completed 
 * their active sorting requirements. Stop running further counting sort passes early to prevent redundant passes 
 * over already-sorted elements.
 */
public class PartialRadixSort {
    public static void adaptiveRadixSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int max = arr[0];
        for (int val : arr) if (val > max) max = val;

        for (int exp = 1; ; exp *= 10) {
            // Adaptive early termination condition check
            if (max / exp == 0) {
                break; 
            }
            
            stableDigitCountingPass(arr, exp);
        }
    }

    private static void stableDigitCountingPass(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) count[(arr[i] / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            int d = (arr[i] / exp) % 10;
            output[count[d] - 1] = arr[i];
            count[d]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] arr = {9, 2, 5, 7, 4, 1}; // Small max value enables early termination after pass 1
        adaptiveRadixSort(arr);
        System.out.println("Adaptively Sorted: " + Arrays.toString(arr)); // [1, 4, 5, 7, 9]
    }
}