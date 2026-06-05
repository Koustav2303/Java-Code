/**
 * PROBLEM: Sum of Digits in Base K
 * * Given an integer n (in base 10) and a radix base k, convert n into base k representation 
 * and return the absolute sum of its digits.
 * * Strategy: Progressive Modulo Extraction
 * Convert the number by taking its modulo with base $k$ to extract the least significant digit, 
 * then integer-divide by $k$ to shift right. Accumulate the extracted remainders into a running total.
 * * Complexity:
 * Time Complexity: O(log_k(N))
 * Space Complexity: O(1)
 */
public class SumOfDigitsBaseK {
    public static int sumBase(int n, int k) {
        int digitSum = 0;
        
        while (n > 0) {
            digitSum += (n % k); // Extract remainder digit
            n /= k;             // Shift radix frame right
        }
        
        return digitSum;
    }

    public static void main(String[] args) {
        System.out.println("Sum of digits for 34 in base 6: " + sumBase(34, 6)); // 9 (34 in base 6 is 54 -> 5 + 4 = 9)
    }
}