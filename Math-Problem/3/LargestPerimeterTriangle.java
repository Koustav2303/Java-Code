import java.util.Arrays;

public class LargestPerimeterTriangle {
    public static int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        
        // Start from the largest sides at the end of the sorted array
        for (int i = nums.length - 1; i >= 2; i--) {
            int c = nums[i];
            int b = nums[i - 1];
            int a = nums[i - 2];
            
            // Triangle inequality check
            if (a + b > c) {
                return a + b + c; // First match guarantees the maximum perimeter
            }
        }
        return 0; // No valid triangle can be formed
    }

    public static void main(String[] args) {
        int[] sideLengths = {2, 1, 2, 5, 4};
        System.out.println("Largest perimeter: " + largestPerimeter(sideLengths));
    }
}