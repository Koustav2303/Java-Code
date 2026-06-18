import java.util.Random;

/**
 * PROBLEM: Semicircle Distribution Sampling
 * * Sample from a Wigner Semicircle distribution over the interval [-1, 1].
 * The target density function forms a parabolic curve: f(x) = (2 / pi) * sqrt(1 - x^2).
 * * Strategy: Semicircle Peak Enclosure
 * The maximum value of the density function occurs at x = 0, where f(0) = 2 / pi $\approx$ 0.6366. 
 * Use a uniform distribution over [-1, 1] as the proposal, and set the vertical envelope bound M = 2 / pi. 
 * Generate a candidate value x, then sample a vertical threshold height up to M. Reject the point if it falls above the curve.
 */
public class SemicircleDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleSemicircle() {
        double maxPeakM = 2.0 / Math.PI; // Enveloping boundary ceiling constant
        
        while (true) {
            double x = -1.0 + 2.0 * rand.nextDouble(); // Uniform proposal over [-1, 1]
            double u = rand.nextDouble() * maxPeakM; // Vertical threshold
            
            double targetDensity = (2.0 / Math.PI) * Math.sqrt(1.0 - x * x);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Semicircle distribution samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleSemicircle());
        }
        System.out.println();
    }
}