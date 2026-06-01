import java.util.HashSet;
import java.util.Set;

/**
 * PROBLEM: Distinct Prime Factors of Product of Array
 * * Given an array of positive integers nums, return the number of distinct prime factors 
 * of the product of the elements of nums.
 * * Approach:
 * Instead of calculating the massive product (which will overflow), we just find the prime 
 * factors of EACH individual number in the array and add them to a HashSet.
 */
public class DistinctPrimeFactors {
    public static int distinctPrimeFactors(int[] nums) {
        Set<Integer> primeFactors = new HashSet<>();
        
        for (int num : nums) {
            int current = num;
            // Extract all 2s
            while (current % 2 == 0) {
                primeFactors.add(2);
                current /= 2;
            }
            // Extract all odd primes up to the square root
            for (int i = 3; i * i <= current; i += 2) {
                while (current % i == 0) {
                    primeFactors.add(i);
                    current /= i;
                }
            }
            // If the remaining number is greater than 2, it is a prime itself
            if (current > 2) {
                primeFactors.add(current);
            }
        }
        
        return primeFactors.size();
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 3, 7, 10, 6};
        System.out.println("Distinct prime factors: " + distinctPrimeFactors(nums)); // 4 (2, 3, 5, 7)
    }
}