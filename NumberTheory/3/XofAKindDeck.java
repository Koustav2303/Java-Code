import java.util.HashMap;
import java.util.Map;

/**
 * PROBLEM: X of a Kind in a Deck of Cards
 * * Given an integer array deck where deck[i] represents the number written on the ith card, 
 * determine if you can partition the deck into one or more groups of cards such that:
 * 1. Each group has exactly X cards.
 * 2. All the cards in each group have the same number value written on them.
 * Return true if and only if a valid partition value X >= 2 exists.
 * * Strategy: Global Frequency GCD Sieve
 * Count the frequency of each card type using a map. To find a common partition size $X \ge 2$, 
 * compute the running Greatest Common Divisor (GCD) across all frequency values. 
 * If the final global GCD stays $\ge 2$, a valid partition size exists.
 */
public class XofAKindDeck {
    private static int gcd(int a, int b) {
        while (b != 0) { int t = b; b = a % b; a = t; }
        return a;
    }

    public static boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int card : deck) {
            counts.put(card, counts.getOrDefault(card, 0) + 1);
        }

        int globalGcd = -1;
        for (int frequency : counts.values()) {
            if (globalGcd == -1) {
                globalGcd = frequency;
            } else {
                globalGcd = gcd(globalGcd, frequency);
            }
        }
        
        return globalGcd >= 2;
    }

    public static void main(String[] args) {
        int[] deck1 = {1, 2, 3, 4, 4, 3, 2, 1}; // Frequencies: all 2s -> gcd=2. Valid!
        int[] deck2 = {1, 1, 1, 2, 2, 2, 3, 3}; // Frequencies: 3, 3, 2 -> gcd=1. Invalid.
        System.out.println("Can deck1 be partitioned? " + hasGroupsSizeX(deck1)); // true
        System.out.println("Can deck2 be partitioned? " + hasGroupsSizeX(deck2)); // false
    }
}