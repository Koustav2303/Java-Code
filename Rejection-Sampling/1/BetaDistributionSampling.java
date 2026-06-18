import java.util.Random;

/**
 * PROBLEM: Beta Distribution Sampling
 * * Sample from a Beta(2, 2) probability density function over the interval [0, 1].
 * The target density function is f(x) = 6 * x * (1 - x).
 * * Strategy: Uniform Envelope Sieve
 * Use a flat Uniform(0,1) proposal distribution g(x) = 1. 
 * The maximum value of f(x) occurs at x = 0.5, where f(0.5) = 1.5. 
 * Set the scaled multiplier envelope constant M = 1.5. 
 * Generate a random point under the envelope curve; reject it if it falls above the target density boundary line.
 * * Complexity:
 * Time Complexity: O(1) average per sample, dependent on the acceptance rate (1 / M = 66.67%).
 * Space Complexity: O(1)
 */
public class BetaDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleBeta() {
        double M = 1.5; // Enveloping peak bound multiplier
        
        while (true) {
            double x = rand.nextDouble(); // Sample candidate from proposal g(x) = Uniform(0,1)
            double u = rand.nextDouble() * M; // Sample vertical height uniformly up to M
            
            double targetDensity = 6.0 * x * (1.0 - x); // Evaluate f(x)
            
            if (u <= targetDensity) {
                return x; // Accepted!
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Generated Beta(2,2) samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBeta());
        }
        System.out.println();
    }
}