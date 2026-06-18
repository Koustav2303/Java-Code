import java.util.Random;

/**
 * PROBLEM: Cauchy Distribution Sampling
 * * Sample from a standard Cauchy distribution restricted strictly to the bounded interval $[-2, 2]$. 
 * The target probability density function is $f(x) = \frac{1}{\pi (1 + x^2)}$.
 * * Strategy: Flat Enclosure Window
 * The peak value of the Cauchy PDF occurs at the center mode $x = 0$, where $f(0) = \frac{1}{\pi} \approx 0.3183$. 
 * Establish a flat uniform proposal distribution $g(x)$ over $[-2, 2]$ and scale the upper envelope bound to $M = \frac{1}{\pi}$. 
 * Generate a candidate, choose a random height under $M$, and reject the point if it sits above the target curve.
 */
public class CauchyDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleBoundedCauchy() {
        double M = 1.0 / Math.PI; // Peak vertical density limit indicator
        
        while (true) {
            // Generate a uniform candidate value within range [-2, 2]
            double x = -2.0 + 4.0 * rand.nextDouble();
            double u = rand.nextDouble() * M; // Sample height threshold
            
            double targetDensity = 1.0 / (Math.PI * (1.0 + x * x));
            
            if (u <= targetDensity) {
                return x; // Accepted!
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Bounded Cauchy distribution samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBoundedCauchy());
        }
        System.out.println();
    }
}