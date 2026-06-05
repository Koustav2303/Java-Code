/**
 * PROBLEM: Count Coprime Pairs
 * * Given an array of positive integers nums, return the number of unique index pairs (i, j) 
 * such that i < j and the two elements are relatively prime (coprime), meaning gcd(nums[i], nums[j]) == 1.
 * * Strategy: Pairwise GCD Reduction
 * Iterate through every unique element combination using a nested loop. Compute the Greatest Common Divisor 
 * using the optimized Euclidean reduction method. Increment the counter if the result equals 1.
 * * Complexity:
 * Time Complexity: O(N^2 * log(min(A, B)))
 * Space Complexity: O(1)
 */
public class CountCoprimePairs {
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int countCoprimes(int[] nums) {
        int pairCount = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (gcd(nums[i], nums[j]) == 1) {
                    pairCount++;
                }
            }
        }
        return pairCount;
    }

    public static void main(String[] args) {
        int[] nums = {12, 5, 7, 15}; 
        System.out.println("Total coprime pairs count: " + countCoprimes(nums)); // 4 -> (12,5), (12,7), (5,7), (7,15)
    }
}