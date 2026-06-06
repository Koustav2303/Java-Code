import java.util.Arrays;

public class CountPrimes {
    public static int countPrimes(int n) {
        if (n <= 2) return 0;
        
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        
        // We only need to check up to the square root of n
        for (int p = 2; p * p < n; p++) {
            if (isPrime[p]) {
                // Cross out all multiples of p starting from p^2
                for (int i = p * p; i < n; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        
        // Count the remaining primes
        int count = 0;
        for (boolean prime : isPrime) {
            if (prime) count++;
        }
        
        return count;
    }

    public static void main(String[] args) {
        int n = 30;
        System.out.println("Number of primes less than " + n + ": " + countPrimes(n));
    }
}