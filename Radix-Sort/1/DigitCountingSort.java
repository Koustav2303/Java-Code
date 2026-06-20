import java.util.Arrays;

/**
 * PROBLEM: Digit Counting Sort
 * * Implement the classic Least Significant Digit (LSD) Radix Sort to order non-negative base-10 integers.
 * * Strategy: Base-10 Exponent Scaling
 * Find the maximum element to determine the number of digits. 
 * Run an iterative stable counting sort loop, scaling an exponent multiplier `exp` by a factor of 10 
 * at each step (`1, 10, 100...`) to extract individual digits using `(arr[i] / exp) % 10`.
 * * Complexity:
 * Time Complexity: O(N * D) where D is the number of digits in the largest integer.
 */
public class DigitCountingSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int max = arr[0];
        for (int val : arr) {
            if (val > max) max = val;
        }

        // Run counting sort for every digit position exponent multiplier
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // Base-10 digits bucket sizes

        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(arr);
        System.out.println("LSD Digit Sorted: " + Arrays.toString(arr));
    }
}