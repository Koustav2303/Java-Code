import java.util.Arrays;

public class MissingNumberBitwise {
    public static int missingNumber(int[] nums) {
        int missing = nums.length;
        
        for (int i = 0; i < nums.length; i++) {
            // XOR the index and the value at that index
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, 1}; // Missing 2
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Missing Number: " + missingNumber(nums));
    }
}