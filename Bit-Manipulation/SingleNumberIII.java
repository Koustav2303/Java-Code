import java.util.Arrays;

public class SingleNumberIII {
    public static int[] singleNumber(int[] nums) {
        // Step 1: XOR all elements. The result is (unique1 ^ unique2)
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;
        
        // Step 2: Find the rightmost set bit in the bitmask
        // This bit is 1 because the two unique numbers differ at this bit position.
        int diff = bitmask & (-bitmask);
        
        int x = 0;
        // Step 3: Use this bit to separate the array into two groups and XOR them
        for (int num : nums) {
            if ((num & diff) != 0) {
                x ^= num; // Group with the bit set
            }
        }
        
        // The first unique number is x, the second is bitmask ^ x
        return new int[]{x, bitmask ^ x};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("The two unique numbers are: " + Arrays.toString(singleNumber(nums)));
    }
}