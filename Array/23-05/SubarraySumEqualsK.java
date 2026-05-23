import java.util.Arrays;
import java.util.HashMap;

public class SubarraySumEqualsK {
    public static int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        // Map stores <PrefixSum, Frequency>
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1); // Base case for subarrays starting at index 0

        for (int num : nums) {
            currentSum += num;

            // If (currentSum - k) exists in map, we found valid subarrays
            if (prefixSumMap.containsKey(currentSum - k)) {
                count += prefixSumMap.get(currentSum - k);
            }

            // Add the current sum to the map
            prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Number of subarrays summing to " + k + ": " + subarraySum(nums, k));
    }
}