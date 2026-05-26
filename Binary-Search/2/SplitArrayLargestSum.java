public class SplitArrayLargestSum {
    public static int splitArray(int[] nums, int k) {
        int low = 0; // Maximum single element
        int high = 0; // Sum of all elements
        
        for (int num : nums) {
            low = Math.max(low, num);
            high += num;
        }
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (canSplit(nums, k, mid)) {
                high = mid; // Try to find a smaller max sum
            } else {
                low = mid + 1; // Max sum too small, we need more than k splits
            }
        }
        return low;
    }
    
    private static boolean canSplit(int[] nums, int m, int maxSum) {
        int splits = 1;
        int currentSum = 0;
        
        for (int num : nums) {
            if (currentSum + num > maxSum) {
                splits++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }
        return splits <= m;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int k = 2;
        System.out.println("Minimized largest sum: " + splitArray(nums, k)); // 18
    }
}