import java.util.Arrays;

public class CyclicSort {
    public static void sort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            // The correct index for the current number (e.g., 3 belongs at index 2)
            int correctIndex = nums[i] - 1;
            
            // If the number is not at its correct index, swap it
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[correctIndex]) {
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {3, 5, 2, 1, 4};
        System.out.println("Original array: " + Arrays.toString(numbers));
        sort(numbers);
        System.out.println("Cyclic Sorted: " + Arrays.toString(numbers));
    }
}