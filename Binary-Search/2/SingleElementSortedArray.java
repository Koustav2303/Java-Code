public class SingleElementSortedArray {
    public static int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // Ensure mid is even for uniform comparison logic
            if (mid % 2 == 1) mid--;
            
            // If the pair matches, the single element is to the right
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                // Otherwise, it's at mid or to the left
                high = mid;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("The single element is: " + singleNonDuplicate(nums)); // 2
    }
}