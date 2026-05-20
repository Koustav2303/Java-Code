import java.util.Arrays;

public class RotateArray {
    public static void rotate(int[] nums, int k) {
        k = k % nums.length; // Handle cases where k > array length
        
        reverse(nums, 0, nums.length - 1); // Reverse whole array
        reverse(nums, 0, k - 1);           // Reverse first k elements
        reverse(nums, k, nums.length - 1); // Reverse the rest
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        
        System.out.println("Original array: " + Arrays.toString(numbers));
        rotate(numbers, k);
        System.out.println("Rotated by " + k + ": " + Arrays.toString(numbers));
    }
}