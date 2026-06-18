import java.util.Random;

/**
 * PROBLEM: Rand10 From Rand7
 * * Implement a uniform random integer generator `rand10()` in the range $[1, 10]$ 
 * using only an existing uniform random generator `rand7()` in the range $[1, 7]$.
 * * Strategy: Coordinate Base-7 Conversion Map
 * Call `rand7()` twice to generate a 2D matrix index space with 49 slots: `7 * (rand7() - 1) + (rand7() - 1)`. 
 * This creates a uniform range from 0 to 48. Keep the largest multiple of 10 that fits within this range 
 * (40 slots, from 0 to 39) and reject values $\ge 40$. Map the accepted indices to the target range using `(value % 10) + 1`.
 * * Complexity:
 * Time Complexity: $O(1)$ average execution time, with an acceptance rate of $40 / 49 \approx 81.63\%$.
 */
public class Rand10FromRand7 {
    private static final Random rand = new Random();

    private static int rand7() {
        return rand.nextInt(7) + 1; // Native base random generator engine
    }

    public static int rand10() {
        while (true) {
            int row = rand7() - 1;
            int col = rand7() - 1;
            int positionIndex = 7 * row + col; // Generates a uniform range [0, 48]
            
            // Reject values outside the balanced 40-slot window to eliminate modulo bias
            if (positionIndex < 40) {
                return (positionIndex % 10) + 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Generated Rand10 values: ");
        for (int i = 0; i < 12; i++) {
            System.out.print(rand10() + " ");
        }
        System.out.println();
    }
}