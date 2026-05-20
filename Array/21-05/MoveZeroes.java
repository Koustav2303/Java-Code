import java.util.Arrays;

public class MoveZeroes {
    public static void move(int[] nums) {
        int insertPos = 0;
        
        // Move all non-zero elements to the front
        for (int num : nums) {
            if (num != 0) {
                nums[insertPos++] = num;
            }
        }
        // Fill the rest of the array with zeroes
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {0, 1, 0, 3, 12};
        System.out.println("Original array: " + Arrays.toString(numbers));
        move(numbers);
        System.out.println("Zeroes moved:   " + Arrays.toString(numbers));
    }
}