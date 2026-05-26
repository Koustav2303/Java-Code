public class SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // If not found, 'low' naturally rests at the exact insertion index
        return low;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        System.out.println("Insert 5 at index: " + searchInsert(nums, 5)); // Output: 2
        System.out.println("Insert 2 at index: " + searchInsert(nums, 2)); // Output: 1
    }
}