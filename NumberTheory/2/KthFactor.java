import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: K-th Factor of n
 * * Given two positive integers n and k, consider a list of all factors of n sorted in ascending order.
 * Return the kth factor in this list or -1 if n has fewer than k factors.
 * * Strategy: Symmetric Factor Pairing
 * Loop up to $\sqrt{n}$ to find small factors. If a factor is found, decrement k. If k hits 0, return it. 
 * Simultaneously, track the corresponding large symmetric factors ($n / i$) in a separate list. 
 * If small factors are exhausted, check the large factors list in reverse order.
 * * Complexity:
 * Time Complexity: $O(\sqrt{N})$
 * Space Complexity: $O(\sqrt{N})$ to store the large factor pairings.
 */
public class KthFactor {
    public static int getKthFactor(int n, int k) {
        List<Integer> largeFactors = new ArrayList<>();
        
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i * i != n) {
                    largeFactors.add(n / i); // Cache the larger symmetric factor
                }
                k--;
                if (k == 0) return i; // Small factor matches target index
            }
        }

        // Search the cached large factors in reverse order (which corresponds to ascending magnitude)
        int size = largeFactors.size();
        if (k <= size) {
            return largeFactors.get(size - k);
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("3rd factor of 12: " + getKthFactor(12, 3)); // 3 (Factors: 1, 2, 3, 4, 6, 12)
        System.out.println("4th factor of 7: " + getKthFactor(7, 4));   // -1
    }
}