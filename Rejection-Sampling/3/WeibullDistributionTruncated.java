import java.util.Random;

/**
 * PROBLEM: WeibullDistributionTruncated
 * * Sample from a Weibull distribution with shape parameter k = 2 and scale lambda = 1 restricted 
 * to the bounded domain interval [0, 3]. The unnormalized target density function is f(x) = x * e^(-x^2).
 * * Strategy: Inflection Shift Mode Enclosure
 * Find the maximum value of this asymmetric distribution function by taking its derivative: 
 * the peak occurs exactly at its mode x = 1 / sqrt(2) ~ 0.7071. The peak height evaluates to 
 * f(0.7071) = 1 / sqrt(2 * e) ~ 0.4289. Establish a uniform proposal distribution over [0, 3] 
 * and scale the envelope multiplier constant to M = 1 / sqrt(2 * e). Reject sample points falling above the curve.
 * * Complexity:
 * Time Complexity: O(1) average execution time per sample.
 */
public class WeibullDistributionTruncated {
    private static final Random rand = new Random();

    public static double sampleBoundedWeibull() {
        // Fixed: Cleaned up placeholder typo into the exact mathematical expression
        double M = 1.0 / Math.sqrt(2.0 * Math.E); // Max peak height: 1 / sqrt(2 * e) ~ 0.4289

        while (true) {
            double x = rand.nextDouble() * 3.0; // Uniform proposal over [0, 3]
            double u = rand.nextDouble() * M;
            
            double targetDensity = x * Math.exp(-(x * x));
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Truncated Weibull (k=2) samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBoundedWeibull());
        }
        System.out.println();
    }
}