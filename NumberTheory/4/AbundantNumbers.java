/**
 * PROBLEM: Abundant Numbers
 * * An abundant number or excessive number is a number for which the sum of its proper divisors 
 * is greater than the number itself. Write a program to check if a number is abundant.
 * * Strategy: Square Root Divisor Scan
 * Sift through factors from 2 up to $\sqrt{n}$. For each divisor, add both the factor and its 
 * symmetric counterpart ($n / i$) to a running sum. If the final sum exceeds the original number, 
 * it is classified as abundant.
 * * Complexity:
 * Time Complexity: $O(\sqrt{N})$
 * Space Complexity: $O(1)$
 */
public class AbundantNumbers {
    public static boolean isAbundant(int n) {
        if (n <= 1) return false;

        int sum = 1; // 1 is always a proper divisor

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                if (i * i != n) {
                    sum += n / i; // Add the symmetric partner
                }
            }
        }
        return sum > n;
    }

    public static void main(String[] args) {
        System.out.println("Is 12 abundant? " + isAbundant(12)); // true (1 + 2 + 3 + 4 + 6 = 16 > 12)
        System.out.println("Is 15 abundant? " + isAbundant(15)); // false (1 + 3 + 5 = 9 < 15)
    }
}