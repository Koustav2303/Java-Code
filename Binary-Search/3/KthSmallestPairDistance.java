import java.util.Arrays;

public class KthSmallestPairDistance {
    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length - 1] - nums[0]; // Max possible distance
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // Count how many pairs have a distance <= mid
            int count = 0;
            int left = 0;
            for (int right = 0; right < nums.length; right++) {
                while (nums[right] - nums[left] > mid) {
                    left++;
                }
                count += right - left;
            }
            
            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 1};
        int k = 1;
        System.out.println("1st smallest pair distance: " + smallestDistancePair(nums, k)); // 0
    }
}