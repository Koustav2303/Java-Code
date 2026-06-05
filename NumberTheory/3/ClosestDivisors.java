import java.util.Arrays;

/**
 * PROBLEM: Closest Divisors
 * * Given an integer num, find two integers x and y such that their product is either num + 1 
 * or num + 2, and the absolute difference |x - y| is minimal.
 * * Strategy: Symmetric Root Down-Scan
 * The closest factor pair for any target value will sit closest to its square root. 
 * Scan backwards starting from floor(sqrt(num + 2)) down to 1. Check divisors for both target options 
 * (num + 1 and num + 2). The first valid factor found is guaranteed to have the minimal absolute difference.
 * * Complexity:
 * Time Complexity: O(sqrt(N))
 * Space Complexity: O(1)
 */
public class ClosestDivisors {
    public static int[] closestDivisors(int num) {
        // Evaluate the two candidate products
        int[] res1 = getClosestFactors(num + 1);
        int[] res2 = getClosestFactors(num + 2);
        
        // Return the pair with the smaller absolute difference
        return Math.abs(res1[0] - res1[1]) < Math.abs(res2[0] - res2[1]) ? res1 : res2;
    }
    
    private static int[] getClosestFactors(int target) {
        int start = (int) Math.sqrt(target);
        for (int i = start; i >= 1; i--) {
            if (target % i == 0) {
                return new int[]{i, target / i};
            }
        }
        return new int[]{1, target};
    }

    public static void main(String[] args) {
        System.out.println("Closest factors for 8: " + Arrays.toString(closestDivisors(8)));   // [3, 3] from 8+1=9
        System.out.println("Closest factors for 123: " + Arrays.toString(closestDivisors(123))); // [5, 25] from 123+2=125
    }
}