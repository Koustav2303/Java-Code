/**
 * PROBLEM: Sqrt(x)
 * * Given a non-negative integer x, return the square root of x rounded down to the nearest integer. 
 * Do not use any built-in exponential library functions like Math.sqrt().
 * * Strategy: Bounded Space Multiplier Binary Search
 * The square root of $X$ is bounded within the range $[1, X]$. Use binary search to find the correct value. 
 * To prevent integer overflow during the evaluation steps, use division instead of multiplication: 
 * check `mid <= x / mid` instead of `mid * mid <= x`.
 * * Complexity:
 * Time Complexity: O(log N)
 * Space Complexity: O(1)
 */
public class SqrtX {
    public static int mySqrt(int x) {
        if (x < 2) return x;

        int low = 1;
        int high = x;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // Overflow-safe conditional check equivalent to: mid * mid <= x
            if (mid <= x / mid) {
                ans = mid; // Speculative integer floor match target
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("Square root of 8 (rounded down): " + mySqrt(8));   // 2
        System.out.println("Square root of 16 (rounded down): " + mySqrt(16)); // 4
    }
}