import java.util.Arrays;

public class FindDuplicateNumber {
    public static int findDuplicate(int[] nums) {
        // Step 1: Find the intersection point of the two runners.
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // Step 2: Find the "entrance" to the cycle.
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return fast;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 2};
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Duplicate number is: " + findDuplicate(nums));
    }
}