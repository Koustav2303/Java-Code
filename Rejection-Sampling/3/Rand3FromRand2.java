import java.util.Random;

/**
 * PROBLEM: Rand3 From Rand2
 * * Implement a uniform random integer generator rand3() in the range [1, 3] using only an 
 * existing uniform random generator rand2() that returns 0 or 1.
 * * Strategy: Discrete Bitwise Rejection Sieve
 * Call rand2() twice to generate a uniform binary bit pair value tracking range from 0 to 3: 
 * positionIndex = 2 * rand2() + rand2(). This creates 4 distinct state configurations. 
 * Reject the maximum value (state 3) to prevent modulo extraction bias, and return uniform outcomes.
 * * Complexity:
 * Time Complexity: O(1) average execution time, with a clean acceptance rate of 3 / 4 = 75%.
 */
public class Rand3FromRand2 {
    private static final Random rand = new Random();

    private static int rand2() {
        return rand.nextInt(2); // Native base bit generation framework
    }

    public static int rand3() {
        while (true) {
            int bit1 = rand2();
            int bit2 = rand2();
            int positionIndex = (bit1 << 1) | bit2; // Generates a uniform discrete range [0, 3]
            
            // Reject the outlier coordinate state to maintain perfect distribution symmetry
            if (positionIndex < 3) {
                return positionIndex + 1;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Generated Rand3 discrete values: ");
        for (int i = 0; i < 15; i++) {
            System.out.print(rand3() + " ");
        }
        System.out.println();
    }
}