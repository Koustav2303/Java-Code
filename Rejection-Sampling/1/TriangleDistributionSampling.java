import java.util.Random;

/**
 * PROBLEM: Triangle Distribution Sampling
 * * Sample from a symmetric triangular distribution over the interval [0, 2] with a peak value at x = 1.
 * The density function increases linearly from 0 to 1, and decreases linearly from 1 to 2.
 * * Strategy: Envelope Height Check
 * The maximum height of the density function is exactly 1.0 at x = 1.0. 
 * Use a uniform distribution over [0, 2] as the proposal, and set the vertical envelope bound M = 1.0. 
 * Generate a candidate value x, then sample a vertical threshold height up to 1.0. 
 * Reject the point if it falls above the triangular envelope.
 */
public class TriangleDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleTriangle() {
        while (true) {
            double x = rand.nextDouble() * 2.0; // Uniform proposal over [0, 2]
            double u = rand.nextDouble(); // Vertical threshold height up to M = 1.0
            
            double targetDensity = (x <= 1.0) ? x : (2.0 - x); // Evaluate the triangular piece-wise PDF
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Triangular distribution samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleTriangle());
        }
        System.out.println();
    }
}