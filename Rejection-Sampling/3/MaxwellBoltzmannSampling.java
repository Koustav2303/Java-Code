import java.util.Random;

/**
 * PROBLEM: MaxwellBoltzmannSampling
 * * Sample from a standard Maxwell-Boltzmann distribution restricted to the bounded domain interval [0, 4]. 
 * The unnormalized target density function is given by f(x) = x^2 * e^(-x^2 / 2).
 * * Strategy: Mode Inflection Peak Enclosure
 * Find the maximum value by taking the derivative: the peak occurs exactly at its mode x = sqrt(2) ~ 1.4142. 
 * Evaluating the peak yields f(sqrt(2)) = 2 * e^(-1) ~ 0.7358. Establish a flat uniform proposal 
 * distribution over [0, 4] scaled to an upper envelope multiplier M = 0.74. Reject non-matching entries.
 */
public class MaxwellBoltzmannSampling {
    private static final Random rand = new Random();

    public static double sampleBoundedMaxwell() {
        double M = 2.0 * Math.exp(-1.0); // Maximum peak mode height threshold
        
        while (true) {
            double x = rand.nextDouble() * 4.0; // Uniform proposal over [0, 4]
            double u = rand.nextDouble() * M;      // Vertical height check
            
            double targetDensity = x * x * Math.exp(-(x * x) / 2.0);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Bounded Maxwell-Boltzmann samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBoundedMaxwell());
        }
        System.out.println();
    }
}