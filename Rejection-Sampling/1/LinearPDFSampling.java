import java.util.Random;

/**
 * PROBLEM: Linear PDF Sampling
 * * Sample from a linear target probability density function f(x) = 2x defined over the interval [0, 1].
 * * Strategy: Bounded Maximum Peak Multiplier
 * The maximum height of f(x) on [0,1] is exactly 2.0 at x = 1.0. 
 * Establish a flat uniform proposal g(x) = 1.0 and set the envelope multiplier M = 2.0. 
 * Generate a candidate value x, then sample a vertical threshold height up to 2.0. 
 * Reject the sample if the vertical height exceeds 2x.
 */
public class LinearPDFSampling {
    private static final Random rand = new Random();

    public static double sampleLinear() {
        double M = 2.0;
        while (true) {
            double x = rand.nextDouble(); // Candidate value from uniform proposal
            double u = rand.nextDouble() * M; // Height under the envelope bound
            
            if (u <= 2.0 * x) { // Target density f(x) validation check
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Linear PDF (2x) samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleLinear());
        }
        System.out.println();
    }
}