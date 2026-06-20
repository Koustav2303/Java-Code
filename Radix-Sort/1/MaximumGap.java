import java.util.Arrays;

/**
 * PROBLEM: Maximum Gap
 * * Given an unsorted integer array, return the maximum difference between successive elements in its sorted form. 
 * Your algorithm must run in linear time and space.
 * * Strategy: LSD Radix Sorting Core
 * Comparison-based sorting takes $O(N \log N)$ time, which breaks the linear time requirement. 
 * Instead, use a stable base-10 LSD Radix Sort to order the array in linear time, 
 * then loop through the sorted array to find the maximum gap between adjacent numbers.
 */
public class MaximumGap {
    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        // Linear Radix Sort pass execution
        radixSort(nums);

        int maxGap = 0;
        for (int i = 1; i < nums.length; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }
        return maxGap;
    }

    private static void radixSort(int[] arr) {
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
        int[] nums = {3, 6, 9, 1};
        System.out.println("Maximum Gap between elements: " + maximumGap(nums)); // 3 (sorted sequence: 1, 3, 6, 9)
    }
}