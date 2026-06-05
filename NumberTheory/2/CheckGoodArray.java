/**
 * PROBLEM: Check If It Is a Good Array
 * * Given an array of positive integers nums, you can choose a subset of nums and an equal number 
 * of integers x_i, and calculate the sum: $\sum (nums[i] \cdot x_i)$.
 * Return true if you can choose a subset such that the sum equals 1, otherwise return false.
 * * Strategy: Bézout's Identity Extension
 * According to Bézout's Identity, the linear combination of a set of integers can equal 1 
 * if and only if the Greatest Common Divisor (GCD) of the entire chosen subset is exactly 1. 
 * Therefore, we loop through the array calculating the running GCD. If it hits 1, the array is "good".
 * * Complexity:
 * Time Complexity: $O(N \cdot \log(\min(nums)))$
 * Space Complexity: $O(1)$
 */
public class CheckGoodArray {
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static boolean isGoodArray(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        int runningGcd = nums[0];
        if (runningGcd == 1) return true;

        for (int i = 1; i < nums.length; i++) {
            runningGcd = gcd(runningGcd, nums[i]);
            if (runningGcd == 1) {
                return true; // Early termination: GCD cannot be reduced lower than 1
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {12, 5, 7, 23};
        int[] nums2 = {6, 10, 15}; // gcd(6,10)=2 -> gcd(2,15)=1. Valid!
        System.out.println("Is nums1 a good array? " + isGoodArray(nums1)); // true
        System.out.println("Is nums2 a good array? " + isGoodArray(nums2)); // true
    }
}