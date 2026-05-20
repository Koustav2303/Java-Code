import java.util.Arrays;

public class MissingNumber {
    public static int findMissing(int[] nums) {
        int n = nums.length;
        // Formula for the sum of first n natural numbers
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        
        for (int num : nums) {
            actualSum += num;
        }
        
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] numbers = {3, 0, 1}; // Missing 2
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Missing number is: " + findMissing(numbers));
    }
}