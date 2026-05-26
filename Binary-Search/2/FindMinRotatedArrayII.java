public class FindMinRotatedArrayII {
    public static int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            if (nums[mid] < nums[high]) {
                // Right half is sorted, min must be in the left half (including mid)
                high = mid;
            } else if (nums[mid] > nums[high]) {
                // Left half is sorted, min must be strictly in the right half
                low = mid + 1;
            } else {
                // We are not sure, so we safely shrink the upper bound
                high--;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 0, 1, 2};
        System.out.println("Minimum element is: " + findMin(nums)); // 0
    }
}