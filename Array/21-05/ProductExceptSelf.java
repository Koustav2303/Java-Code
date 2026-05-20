import java.util.Arrays;

public class ProductExceptSelf {
    public static int[] product(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Compute left products directly into result array
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // Compute right products on the fly and multiply with the left products
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * rightProduct;
            rightProduct *= nums[i];
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4};
        System.out.println("Original array: " + Arrays.toString(numbers));
        System.out.println("Product except self: " + Arrays.toString(product(numbers)));
    }
}