import java.util.Arrays;

/**
 * PROBLEM: Product of Array Except Self
 * * Given an integer array nums, return an array answer such that answer[i] is equal to 
 * the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * * Example:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * * Approach:
 * This is a variation of Prefix Sum called "Prefix Product".
 * We calculate the product of all elements to the LEFT of `i`, 
 * then multiply it by the product of all elements to the RIGHT of `i`.
 */
public class ProductExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Step 1: Calculate the Prefix Products (left to right)
        // result[i] will momentarily store the product of everything to the left of i
        result[0] = 1; // Nothing to the left of index 0
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // Step 2: Calculate the Suffix Products on the fly (right to left)
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            // Multiply the existing prefix product by the suffix product
            result[i] = result[i] * suffixProduct;
            // Expand the suffix product for the next iteration
            suffixProduct *= nums[i];
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        System.out.println("Product except self: " + Arrays.toString(productExceptSelf(nums))); 
        // [24, 12, 8, 6]
    }
}