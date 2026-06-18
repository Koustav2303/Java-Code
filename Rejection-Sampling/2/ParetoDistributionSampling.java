import java.util.Random;

/**
 * PROBLEM: Pareto Distribution Sampling
 * * Sample from a Pareto distribution with scale parameters $x_m = 1$ and shape $\alpha = 2$, 
 * restricted to the bounded interval $[1, 5]$. The target probability density function is $f(x) = \frac{2}{x^3}$.
 * * Strategy: Sharp Boundary Initial Peak Envelope
 * The power-law density function decreases monotonically, reaching its maximum value at the lower bound $x = 1$, 
 * where $f(1) = 2.0$. Set a uniform proposal distribution over $[1, 5]$ and scale the envelope multiplier to $M = 2.0$. 
 * Reject any candidate points that fall above the function profile curve.
 */
public class ParetoDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleBoundedPareto() {
        double M = 2.0; // Monotonic boundary start peak value
        
        while (true) {
            double x = 1.0 + 4.0 * rand.nextDouble(); // Uniform proposal over [1, 5]
            double u = rand.nextDouble() * M;
            
            double targetDensity = 2.0 / Math.pow(x, 3);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Bounded Pareto(1,2) samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBoundedPareto());
        }
        System.out.println();
    }
}