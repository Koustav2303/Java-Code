import java.util.Random;

/**
 * PROBLEM: Rand7 From Rand5
 * * Implement a uniform random integer generator rand7() in the range [1, 7] 
 * using only an existing uniform random generator rand5() in the range [1, 5].
 * * Strategy: Coordinate Base-5 Matrix Map
 * Call rand5() twice to generate a 2D matrix index space with 25 slots: `5 * (rand5() - 1) + (rand5() - 1)`. 
 * This creates a uniform range from 0 to 24. 
 * Keep the largest multiple of 7 that fits within this range (21 slots, from 0 to 20) and reject values $\ge 21$. 
 * Map the accepted indices to the target range using `(value % 7) + 1`.
 * * Complexity:
 * Time Complexity: O(1) average time, with an acceptance rate of 21 / 25 = 84%.
 */
public class Rand7FromRand5 {
    private static final Random rand = new Random();

    private static int rand5() {
        return rand.nextInt(5) + 1; // Native mock random foundation engine
    }

    public static int rand7() {
        while (true) {
            int row = rand5() - 1;
            int col = rand5() - 1;
            int positionIndex = 5 * row + col; // Generates a uniform range [0, 24]
            
            // Reject values outside the balanced 21-slot window to avoid modulo bias
            if (positionIndex < 21) {
                return (positionIndex % 7) + 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Generated Rand7 values: ");
        for (int i = 0; i < 10; i++) {
            System.out.print(rand7() + " ");
        }
        System.out.println();
    }
}