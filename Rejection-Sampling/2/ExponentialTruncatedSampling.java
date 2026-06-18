import java.util.Random;

/**
 * PROBLEM: Truncated Exponential Sampling
 * * Sample from a standard exponential distribution ($\lambda = 1$) restricted to the interval $[1, 3]$. 
 * The target density function is $f(x) = e^{-x}$.
 * * Strategy: Right-Shift Density Filter
 * The maximum value of the target function on the interval $[1, 3]$ occurs at the lower bound $x = 1$, 
 * where $f(1) = e^{-1} \approx 0.3679$. Use a uniform distribution over $[1, 3]$ as the proposal, 
 * and set the envelope multiplier to $M = e^{-1}$. Reject any sample points that fall above the curve.
 */
public class ExponentialTruncatedSampling {
    private static final Random rand = new Random();

    public static double sampleTruncatedExponential() {
        double M = Math.exp(-1.0); // Maximum density boundary value
        
        while (true) {
            // Uniform proposal candidate over the interval [1, 3]
            double x = 1.0 + 2.0 * rand.nextDouble();
            double u = rand.nextDouble() * M;
            
            double targetDensity = Math.exp(-x);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Truncated Exponential [1,3] samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleTruncatedExponential());
        }
        System.out.println();
    }
}