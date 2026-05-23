import java.util.Arrays;
import java.util.HashMap;

public class ContainsDuplicateII {
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // If the map has the number, check if the distance is <= k
            if (map.containsKey(nums[i])) {
                if (Math.abs(i - map.get(nums[i])) <= k) {
                    return true;
                }
            }
            // Update the map with the most recent index
            map.put(nums[i], i);
        }
        
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Contains duplicate within distance " + k + "? " + containsNearbyDuplicate(nums, k));
    }
}