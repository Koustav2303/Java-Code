import java.util.Arrays;

public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // Find the first decreasing element from the right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        if (i >= 0) {
            int j = nums.length - 1;
            // Find the element just larger than nums[i]
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        
        // Reverse the descending sequence to get the smallest lexicographical order
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println("Original: " + Arrays.toString(nums));
        nextPermutation(nums);
        System.out.println("Next:     " + Arrays.toString(nums));
    }
}