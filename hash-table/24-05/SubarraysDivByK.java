import java.util.Arrays;
import java.util.HashMap;

public class SubarraysDivByK {
    public static int subarraysDivByK(int[] nums, int k) {
        // Map stores <Modulo Result, Frequency>
        HashMap<Integer, Integer> modMap = new HashMap<>();
        modMap.put(0, 1); // Base case: prefix sum itself is divisible by K

        int currentSum = 0;
        int count = 0;

        for (int num : nums) {
            currentSum += num;
            
            // Calculate modulo (handling negative numbers in Java correctly)
            int remainder = currentSum % k;
            if (remainder < 0) {
                remainder += k; 
            }

            // If we've seen this remainder before, it means the subarray between 
            // that past point and our current point is cleanly divisible by K.
            if (modMap.containsKey(remainder)) {
                count += modMap.get(remainder);
            }

            modMap.put(remainder, modMap.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 0, -2, -3, 1};
        int k = 5;
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Total subarrays divisible by " + k + ": " + subarraysDivByK(nums, k));
    }
}