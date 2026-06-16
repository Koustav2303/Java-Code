import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * PROBLEM: Intersection of Two Arrays
 * * Given two integer arrays nums1 and nums2, return an array of their intersection. 
 * Each element in the result must be unique and you may return the result in any order.
 * * Strategy: Bounded Intersection Sieve Set
 * Load elements of `nums1` into a HashSet for lookup optimization. Iterate through `nums2`, 
 * checking if an element exists in the set. If a match is found, add it to an intersection set 
 * and remove it from the first lookup set to ensure uniqueness.
 * * Complexity:
 * Time Complexity: O(N + M)
 * Space Complexity: O(N)
 */
public class IntersectionTwoArrays {
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> uniqueItemsSet = new HashSet<>();
        for (int num : nums1) uniqueItemsSet.add(num);

        Set<Integer> intersectionMatchSet = new HashSet<>();
        for (int num : nums2) {
            if (uniqueItemsSet.contains(num)) {
                intersectionMatchSet.add(num);
            }
        }

        // Map the matching set values back into a primitive array format layout
        int[] result = new int[intersectionMatchSet.size()];
        int index = 0;
        for (int val : intersectionMatchSet) {
            result[index++] = val;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] n1 = {1, 2, 2, 1};
        int[] n2 = {2, 2};
        System.out.println("Unique intersection array result: " + Arrays.toString(intersection(n1, n2))); // [2]
    }
}