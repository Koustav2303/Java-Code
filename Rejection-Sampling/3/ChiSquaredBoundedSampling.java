import java.util.Random;

/**
 * PROBLEM: Chi-Squared Bounded Sampling
 * * Sample from a Chi-Squared distribution with k = 4 degrees of freedom restricted to the interval [0, 10]. 
 * The unnormalized target probability density function is f(x) = x * e^(-x / 2).
 * * Strategy: Mode Location Peak Analyzer
 * Find the maximum value of the function by taking its derivative: the peak occurs at its mode x = k - 2 = 2. 
 * The peak height is f(2) = 2 * e^(-1) ~ 0.7358. Establish a uniform proposal distribution over [0, 10] 
 * and set the scaled envelope multiplier to M = 0.74. Reject points falling above the function curve.
 */
public class ChiSquaredBoundedSampling {
    private static final Random rand = new Random();

    public static double sampleBoundedChiSquared() {
        double M = 2.0 * Math.exp(-1.0); // Exact maximum peak height at mode x = 2
        
        while (true) {
            double x = rand.nextDouble() * 10.0; // Uniform proposal over [0, 10]
            double u = rand.nextDouble() * M;      // Vertical height check
            
            double targetDensity = x * Math.exp(-x / 2.0);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Bounded Chi-Squared (k=4) samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBoundedChiSquared());
        }
        System.out.println();
    }
}