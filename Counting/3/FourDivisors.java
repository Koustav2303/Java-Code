/**
 * PROBLEM: Four Divisors
 * * Given an integer array nums, return the sum of divisors of the integers in that array 
 * that have exactly four divisors. If there is no such integer in the array, return 0.
 * * Mathematical Insight:
 * A number has exactly 4 divisors if and only if it is:
 * 1. The cube of a prime (p^3) -> Divisors: 1, p, p^2, p^3
 * 2. The product of exactly two distinct primes (p1 * p2) -> Divisors: 1, p1, p2, p1*p2
 */
public class FourDivisors {
    public static int sumFourDivisors(int[] nums) {
        int totalSum = 0;
        
        for (int num : nums) {
            int divisorSum = 0;
            int count = 0;
            
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    count++;
                    divisorSum += i;
                    
                    if (i != num / i) {
                        count++;
                        divisorSum += num / i;
                    }
                }
                if (count > 4) break; // Optimization: Exceeds 4 divisors
            }
            
            if (count == 4) {
                totalSum += divisorSum;
            }
        }
        
        return totalSum;
    }

    public static void main(String[] args) {
        int[] nums = {21, 4, 7};
        System.out.println("Sum of four-divisor elements: " + sumFourDivisors(nums)); // 32 (Divisors of 21: 1, 3, 7, 21)
    }
}