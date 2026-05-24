import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ArrayIntersectionFrequencies {
    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        // Count frequencies of the first array
        for (int num : nums1) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        ArrayList<Integer> resultList = new ArrayList<>();
        // Check the second array against our map
        for (int num : nums2) {
            if (counts.containsKey(num) && counts.get(num) > 0) {
                resultList.add(num);
                counts.put(num, counts.get(num) - 1); // Decrease available count
            }
        }

        // Convert ArrayList to primitive array
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 9, 5, 4, 4};
        int[] arr2 = {9, 4, 9, 8, 4};
        
        System.out.println("Array 1: " + Arrays.toString(arr1));
        System.out.println("Array 2: " + Arrays.toString(arr2));
        System.out.println("Intersection: " + Arrays.toString(intersect(arr1, arr2)));
    }
}