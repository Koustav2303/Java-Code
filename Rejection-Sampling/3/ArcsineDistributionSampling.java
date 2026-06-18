import java.util.Random;

/**
 * PROBLEM: Arcsine Distribution Sampling
 * * Sample from an Arcsine distribution restricted to the bounded interval [0.05, 0.95] to avoid 
 * the infinite asymptotic poles at 0 and 1. The unnormalized target density function is:
 * f(x) = 1 / sqrt(x * (1 - x))
 * * Strategy: Bounded Domain Peak Enclosure
 * The maximum value of the density function on the restricted interval [0.05, 0.95] occurs at the 
 * extreme boundary endpoints x = 0.05 and x = 0.95. Evaluating f(0.05) yields 1 / sqrt(0.0475) ~ 4.5883.
 * Establish a flat uniform proposal distribution g(x) over [0.05, 0.95] and scale the envelope 
 * multiplier constant to M = 4.60. Reject candidate values falling above the curve boundary.
 * * Complexity:
 * Time Complexity: O(1) average runtime per sample.
 * Space Complexity: O(1)
 */
public class ArcsineDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleArcsine() {
        double low = 0.05;
        double high = 0.95;
        double M = 4.60; // Peak limit at the boundary thresholds

        while (true) {
            // Sample candidate from proposal g(x) = Uniform(0.05, 0.95)
            double x = low + (high - low) * rand.nextDouble();
            double u = rand.nextDouble() * M; // Vertical height threshold

            double targetDensity = 1.0 / Math.sqrt(x * (1.0 - x));

            if (u <= targetDensity) {
                return x; // Accepted!
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Generated Arcsine samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleArcsine());
        }
        System.out.println();
    }
}