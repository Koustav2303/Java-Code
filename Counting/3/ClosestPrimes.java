import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PROBLEM: Closest Prime Numbers in Range
 * * Given two integers left and right, find the two prime numbers in the range [left, right] 
 * whose absolute difference is the minimum.
 * * Example:
 * Input: left = 10, right = 19
 * Output: [11, 13]
 * * Approach:
 * Run a Sieve up to 'right'. Collect all primes in the range [left, right] into a list.
 * Iterate through the list to find the adjacent pair with the minimum difference.
 */
public class ClosestPrimes {
    public static int[] closestPrimes(int left, int right) {
        boolean[] isPrime = new boolean[right + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        if (right >= 1) isPrime[1] = false;
        
        for (int i = 2; i * i <= right; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= right; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        List<Integer> primes = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isPrime[i]) primes.add(i);
        }
        
        if (primes.size() < 2) return new int[]{-1, -1};
        
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];
        
        for (int i = 1; i < primes.size(); i++) {
            int diff = primes.get(i) - primes.get(i - 1);
            if (diff < minDiff) {
                minDiff = diff;
                result[0] = primes.get(i - 1);
                result[1] = primes.get(i);
            }
            if (minDiff <= 2) break; // Optimization: 2 or 1 is the smallest possible difference for primes > 2
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] res = closestPrimes(10, 19);
        System.out.println("Closest primes: [" + res[0] + ", " + res[1] + "]"); // [11, 13]
    }
}