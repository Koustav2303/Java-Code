import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindDuplicates {
    public static List<Integer> find(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Get the absolute value to use as an index
            int index = Math.abs(nums[i]) - 1;
            
            // If the value at that index is already negative, we've seen it before
            if (nums[index] < 0) {
                duplicates.add(Math.abs(nums[i]));
            } else {
                // Otherwise, mark it as seen by making it negative
                nums[index] = -nums[index];
            }
        }
        return duplicates;
    }

    public static void main(String[] args) {
        int[] numbers = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("Original array: " + Arrays.toString(numbers));
        System.out.println("Duplicates found: " + find(numbers));
    }
}