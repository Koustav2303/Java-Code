import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Prime Subtraction Operation
 * * You are given a 0-indexed integer array nums. You can pick an index i and subtract a strictly smaller 
 * prime number from nums[i].
 * Return true if you can make nums a strictly increasing array, or false otherwise.
 * * Approach:
 * Precompute primes up to 1000. Traverse the array. For each element, subtract the largest possible 
 * prime that still leaves the current element strictly greater than the previous element.
 */
public class PrimeSubtraction {
    public static boolean primeSubOperation(int[] nums) {
        List<Integer> primes = getPrimes(1000);
        int prev = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            
            // We want to subtract the largest prime P such that (current - P) > prev
            // This is equivalent to finding the largest prime P < current - prev
            int target = current - prev;
            int largestValidPrime = 0;
            
            for (int p : primes) {
                if (p < target) {
                    largestValidPrime = p;
                } else {
                    break; // Since primes are sorted, we can break early
                }
            }
            
            int finalValue = current - largestValidPrime;
            if (finalValue <= prev) return false; // Could not make it strictly increasing
            
            prev = finalValue;
        }
        
        return true;
    }
    
    private static List<Integer> getPrimes(int max) {
        boolean[] isPrime = new boolean[max + 1];
        for (int i = 2; i <= max; i++) isPrime[i] = true;
        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) isPrime[j] = false;
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= max; i++) if (isPrime[i]) primes.add(i);
        return primes;
    }

    public static void main(String[] args) {
        int[] nums = {4, 9, 6, 10};
        System.out.println("Can make strictly increasing? " + primeSubOperation(nums)); // true
    }
}