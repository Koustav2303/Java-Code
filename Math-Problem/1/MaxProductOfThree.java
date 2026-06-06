import java.util.Arrays;

public class MaxProductOfThree {
    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        
        // Option 1: Three largest numbers at the end of the sorted array
        int option1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        
        // Option 2: Two smallest (most negative) numbers at the start, and the largest positive number
        int option2 = nums[0] * nums[1] * nums[n - 1];
        
        return Math.max(option1, option2);
    }

    public static void main(String[] args) {
        int[] nums = {-10, -10, 1, 2, 3};
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Maximum product of three numbers: " + maximumProduct(nums));
        // Output is 300 (-10 * -10 * 3)
    }
}