import java.util.Random;

/**
 * PROBLEM: Rayleigh Distribution Sampling
 * * Sample from a standard Rayleigh distribution ($\sigma = 1$) restricted to the interval $[0, 3]$. 
 * The target probability density function is $f(x) = x e^{-\frac{x^2}{2}}$.
 * * Strategy: Mode Coordinate Inflection Peak Enclosure
 * Find the maximum value of the function by taking its derivative: the peak occurs at its mode $x = 1$. 
 * The peak height is $f(1) = e^{-0.5} \approx 0.6065$. Establish a flat uniform proposal over $[0, 3]$ 
 * with an envelope multiplier $M = 0.65$. Reject any sample points that fall above the curve.
 * * Complexity:
 * Time Complexity: $O(1)$ average execution time per sample.
 */
public class RayleighDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleBoundedRayleigh() {
        double M = Math.exp(-0.5); // Peak mode maximum height threshold
        
        while (true) {
            double x = rand.nextDouble() * 3.0; // Uniform proposal candidate over [0, 3]
            double u = rand.nextDouble() * M;
            
            double targetDensity = x * Math.exp(-(x * x) / 2.0);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Bounded Rayleigh distribution samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBoundedRayleigh());
        }
        System.out.println();
    }
}