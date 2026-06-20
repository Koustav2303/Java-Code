import java.util.Arrays;

/**
 * PROBLEM: In Place Radix Sort
 * * Implement an in-place Radix Sort for integers to minimize memory overhead to O(1) space.
 * * Strategy: Binary Most Significant Bit Partitioning
 * Use an MSD approach starting from the highest bit (bit 31) down to bit 0. 
 * For each bit level, use a two-pointer partitioning approach (similar to QuickSort) to group elements with 
 * a '0' bit on the left and elements with a '1' bit on the right. 
 * Recursively sort each partition for the next lower bit level.
 * * Complexity:
 * Time Complexity: O(N * 32)
 * Space Complexity: O(32) recursion stack frames.
 */
public class InPlaceRadixSort {
    public static void inPlaceRadixSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        sortPartitions(arr, 0, arr.length - 1, 30); // Use bit 30 to preserve sign bit boundaries
    }

    private static void sortPartitions(int[] arr, int low, int high, int bitPosition) {
        if (low >= high || bitPosition < 0) return;

        int left = low;
        int right = high;
        int mask = 1 << bitPosition;

        // Partition the array based on the active bit position mask
        while (left <= right) {
            if ((arr[left] & mask) == 0) {
                left++;
            } else if ((arr[right] & mask) != 0) {
                right--;
            } else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        // Recurse on the left partition (0 bits) and right partition (1 bits)
        sortPartitions(arr, low, right, bitPosition - 1);
        sortPartitions(arr, left, high, bitPosition - 1);
    }

    public static void main(String[] args) {
        int[] arr = {12, 5, 2, 9, 23, 1, 0, 4};
        inPlaceRadixSort(arr);
        System.out.println("In-Place Radix Sorted: " + Arrays.toString(arr));
    }
}