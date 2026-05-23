import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TopKFrequentElements {
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        
        // Count frequencies
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Create a list of the unique numbers
        List<Integer> uniqueNums = new ArrayList<>(frequencyMap.keySet());
        
        // Sort the list based on their frequencies stored in the map (Descending)
        uniqueNums.sort((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));

        // Extract the top K elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = uniqueNums.get(i);
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Top " + k + " frequent elements: " + Arrays.toString(topKFrequent(nums, k)));
    }
}