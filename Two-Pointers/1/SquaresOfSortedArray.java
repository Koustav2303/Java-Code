import java.util.Arrays;

/**
 * PROBLEM: Squares of a Sorted Array
 * * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number 
 * sorted in non-decreasing order.
 * * Example:
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 * Solve it in O(n) time utilizing two pointers.
 */
public class SquaresOfSortedArray {
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        int left = 0;
        int right = n - 1;
        
        // Fill the result array from back to front (largest to smallest)
        for (int i = n - 1; i >= 0; i--) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            
            // Place the larger square at the end of the current available slot
            if (leftSquare > rightSquare) {
                result[i] = leftSquare;
                left++;
            } else {
                result[i] = rightSquare;
                right--;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        System.out.println("Sorted Squares: " + Arrays.toString(sortedSquares(nums))); 
        // [0, 1, 9, 16, 100]
    }
}