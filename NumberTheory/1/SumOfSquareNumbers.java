/**
 * PROBLEM: Sum of Square Numbers
 * * Given a non-negative integer c, determine whether there are two integers a and b 
 * such that a^2 + b^2 = c.
 * * Strategy: Two-Pointer Search Space Bounding
 * Initialize two pointers: 'low' at 0 and 'high' at sqrt(c). 
 * Compute the sum of their squares. If the sum matches c, a match is found. 
 * If it falls below c, increment the low pointer. If it exceeds c, decrement the high pointer.
 * * Complexity:
 * Time Complexity: O(sqrt(C))
 * Space Complexity: O(1)
 */
public class SumOfSquareNumbers {
    public static boolean judgeSquareSum(int c) {
        long low = 0;
        long high = (long) Math.sqrt(c);

        while (low <= high) {
            long currentSum = low * low + high * high;
            if (currentSum == c) {
                return true;
            } else if (currentSum < c) {
                low++;
            } else {
                high--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Can 5 be written as sum of squares? " + judgeSquareSum(5)); // true (1^2 + 2^2)
        System.out.println("Can 3 be written as sum of squares? " + judgeSquareSum(3)); // false
    }
}