import java.util.Random;

/**
 * PROBLEM: Mixture Model Sampling
 * * Sample from an unnormalized multi-modal probability density function defined by a complex combination 
 * of trigonometric functions: f(x) = sin^2(x) + 0.5 * cos^2(3x) over the interval [0, pi].
 * * Strategy: Maximum Peak Enclosure Bound
 * Find the upper bound of the target function. Since sin^2(x) <= 1 and cos^2(3x) <= 1, 
 * the function is guaranteed to never exceed 1.5. Set the envelope multiplier M = 1.5. 
 * Use a flat uniform distribution over [0, pi] as the proposal, and reject points that fall above the function curve.
 */
public class MixtureModelSampling {
    private static final Random rand = new Random();

    public static double sampleMixture() {
        double maxBoundPeakM = 1.5;
        
        while (true) {
            double x = rand.nextDouble() * Math.PI; // Uniform proposal over [0, pi]
            double u = rand.nextDouble() * maxBoundPeakM; // Vertical threshold
            
            double targetDensity = Math.pow(Math.sin(x), 2) + 0.5 * Math.pow(Math.cos(3.0 * x), 2);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Multi-modal mixture samples: ");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%.4f ", sampleMixture());
        }
        System.out.println();
    }
}