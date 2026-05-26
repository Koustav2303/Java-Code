public class FindMinRotatedArray {
    public static int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        // If the array is NOT rotated (or rotated n times back to normal)
        if (nums[low] <= nums[high]) return nums[low];

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check if mid is the inflection point (mid is greater than the next element)
            if (mid < nums.length - 1 && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            // Check if mid itself is the lowest element (mid is smaller than the previous element)
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            // If left half is sorted, the min must be in the right half
            if (nums[low] <= nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println("Minimum element is: " + findMin(nums)); // 1
    }
}