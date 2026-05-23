import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        int longestStreak = 0;

        for (int num : set) {
            // Only start counting if this number is the START of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println("Longest consecutive sequence length: " + longestConsecutive(nums)); 
        // Sequence is [1, 2, 3, 4] -> length 4
    }
}