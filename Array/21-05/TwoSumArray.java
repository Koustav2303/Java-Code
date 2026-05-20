import java.util.Arrays;
import java.util.HashMap;

public class TwoSumArray {
    public static int[] findTwoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {}; // No solution found
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        
        System.out.println("Array: " + Arrays.toString(numbers));
        int[] result = findTwoSum(numbers, target);
        System.out.println("Indices for target " + target + ": " + Arrays.toString(result));
    }
}