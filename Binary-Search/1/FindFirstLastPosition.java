import java.util.Arrays;

public class FindFirstLastPosition {
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        result[0] = findBound(nums, target, true);
        if (result[0] != -1) {
            result[1] = findBound(nums, target, false);
        }
        return result;
    }

    private static int findBound(int[] nums, int target, boolean isFirst) {
        int low = 0, high = nums.length - 1;
        int bound = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                bound = mid;
                // If finding the first, keep searching left. Otherwise, search right.
                if (isFirst) high = mid - 1;
                else low = mid + 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return bound;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println("Range for 8: " + Arrays.toString(searchRange(nums, 8))); // [3, 4]
    }
}