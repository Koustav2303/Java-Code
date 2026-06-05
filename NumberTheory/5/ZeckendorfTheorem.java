import java.util.ArrayList;
import java.util.List;

/**
 * PROBLEM: Zeckendorf's Theorem
 * * Zeckendorf's theorem states that every positive integer can be uniquely represented as the sum 
 * of one or more distinct non-consecutive Fibonacci numbers. Return this unique decomposition array.
 * * Strategy: Greedy Decomposition
 * Generate all Fibonacci numbers up to $N$. Iterate backwards from the largest generated Fibonacci value. 
 * Greedily subtract the largest possible Fibonacci number that is less than or equal to the remaining total, 
 * then skip its immediate neighbor to satisfy the non-consecutive constraint.
 */
public class ZeckendorfTheorem {
    public static List<Integer> getZeckendorfDecomposition(int n) {
        List<Integer> result = new ArrayList<>();
        if (n <= 0) return result;

        // Step 1: Generate Fibonacci numbers up to n
        List<Integer> fibs = new ArrayList<>();
        fibs.add(1);
        fibs.add(2);
        while (true) {
            int nextFib = fibs.get(fibs.size() - 1) + fibs.get(fibs.size() - 2);
            if (nextFib > n) break;
            fibs.add(nextFib);
        }

        // Step 2: Greedily extract non-consecutive factors backwards
        for (int i = fibs.size() - 1; i >= 0; i--) {
            int currentFib = fibs.get(i);
            if (n >= currentFib) {
                result.add(currentFib);
                n -= currentFib;
                i--; // Skip the next adjacent neighbor automatically to enforce non-consecutive constraints
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Zeckendorf partition for 64: " + getZeckendorfDecomposition(64)); // [55, 8, 1]
    }
}