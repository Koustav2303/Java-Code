public class FindDuplicateBS {
    public static int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            
            // Count how many numbers in the array are less than or equal to mid
            for (int num : nums) {
                if (num <= mid) count++;
            }
            
            // Pigeonhole Principle: If there are more numbers than the range size, 
            // the duplicate is in this lower half
            if (count > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 2};
        System.out.println("The duplicate number is: " + findDuplicate(nums)); // 3
    }
}