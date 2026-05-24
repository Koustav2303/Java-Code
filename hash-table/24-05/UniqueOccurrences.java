import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class UniqueOccurrences {
    public static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        
        // Count occurrences of each number
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Put all the occurrence counts into a HashSet
        HashSet<Integer> uniqueCounts = new HashSet<>(countMap.values());

        // If the set size equals the map size, all frequencies were unique
        return countMap.size() == uniqueCounts.size();
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 2, 1, 1, 3};
        System.out.println("Array: " + Arrays.toString(numbers));
        System.out.println("Are occurrences unique? " + uniqueOccurrences(numbers));
        // 1 appears 3 times, 2 appears 2 times, 3 appears 1 time. (Unique! True)
    }
}