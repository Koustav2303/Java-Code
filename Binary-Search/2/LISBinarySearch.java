import java.util.Arrays;

public class LISBinarySearch {
    public static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0; // Current size of the LIS
        
        for (int x : nums) {
            int low = 0, high = size;
            
            // Binary search to find where the current number fits in the tails array
            while (low != high) {
                int mid = low + (high - low) / 2;
                if (tails[mid] < x) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            
            tails[low] = x; // Overwrite or extend
            if (low == size) size++;
        }
        return size;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS (Binary Search): " + lengthOfLIS(nums)); // 4
    }
}