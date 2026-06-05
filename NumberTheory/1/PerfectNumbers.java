/**
 * PROBLEM: Perfect Numbers
 * * A perfect number is a positive integer that is equal to the sum of its positive divisors, 
 * excluding the number itself. Given an integer n, return true if it is perfect, and false otherwise.
 * * Strategy: Square Root Factor Sifting
 * Iterate through possible divisors from 2 up to sqrt(n). If a divisor 'i' is located, 
 * add both 'i' and its counterpart 'n / i' to the running sum. This optimizes the search 
 * from linear down to square-root time.
 * * Complexity:
 * Time Complexity: O(sqrt(N))
 * Space Complexity: O(1)
 */
public class PerfectNumbers {
    public static boolean checkPerfectNumber(int num) {
        if (num <= 1) return false;

        int sum = 1; // 1 is always a proper divisor for values > 1
        
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i;
                // Add the companion factor if it isn't identical to the current factor
                if (i * i != num) {
                    sum += num / i;
                }
            }
        }
        return sum == num;
    }

    public static void main(String[] args) {
        System.out.println("Is 28 a perfect number? " + checkPerfectNumber(28)); // true (1 + 2 + 4 + 7 + 14 = 28)
        System.out.println("Is 12 a perfect number? " + checkPerfectNumber(12)); // false
    }
}