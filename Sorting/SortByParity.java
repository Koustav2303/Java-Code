import java.util.Arrays;

public class SortByParity {
    public static int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // If left is even, it's in the right place, move forward
            if (nums[left] % 2 == 0) {
                left++;
            } 
            // If left is odd and right is even, swap them
            else if (nums[right] % 2 == 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            } 
            // If right is odd, it's in the right place, move backward
            else {
                right--;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
        System.out.println("Original array: " + Arrays.toString(nums));
        System.out.println("Sorted by parity: " + Arrays.toString(sortArrayByParity(nums)));
    }
}