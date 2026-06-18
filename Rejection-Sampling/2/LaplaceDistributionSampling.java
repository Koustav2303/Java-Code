import java.util.Random;

/**
 * PROBLEM: Laplace Distribution Sampling
 * * Sample from a standard Laplace (double exponential) distribution restricted to the symmetric interval $[-1, 1]$. 
 * The target density function is $f(x) = \frac{1}{2} e^{-|x|}$.
 * * Strategy: Central Peak Uniform Enclosure
 * The maximum density occurs at the sharp corner point $x = 0$, where $f(0) = 0.5$. 
 * Establish a uniform proposal over $[-1, 1]$ and set the envelope height bound to $M = 0.5$. 
 * Generate a candidate, choose a random height under $M$, and reject it if it falls above the function profile.
 */
public class LaplaceDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleBoundedLaplace() {
        double M = 0.5; // Central peak ceiling limit parameter
        
        while (true) {
            double x = -1.0 + 2.0 * rand.nextDouble(); // Uniform proposal over [-1, 1]
            double u = rand.nextDouble() * M;
            
            double targetDensity = 0.5 * Math.exp(-Math.abs(x));
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Bounded Laplace distribution samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBoundedLaplace());
        }
        System.out.println();
    }
}