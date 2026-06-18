import java.util.Random;

/**
 * PROBLEM: Gamma Distribution Sampling
 * * Sample from a Gamma shape distribution ($k = 3, \theta = 1$) restricted to the interval $[0, 5]$. 
 * The unnormalized target density function is $f(x) = x^2 e^{-x}$.
 * * Strategy: Mode Location Peak Analyzer
 * Find the maximum value of the function by taking its derivative: the peak occurs at its mode $x = 2$. 
 * The peak height is $f(2) = 4 e^{-2} \approx 0.5413$. Set a flat uniform proposal over $[0, 5]$ 
 * with an envelope multiplier $M = 0.55$. Reject points that fall above the profile curve.
 */
public class GammaDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleBoundedGamma() {
        double M = 4.0 * Math.exp(-2.0); // Maximum exact mode peak height
        
        while (true) {
            double x = rand.nextDouble() * 5.0; // Uniform proposal candidate over [0, 5]
            double u = rand.nextDouble() * M;
            
            double targetDensity = x * x * Math.exp(-x);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Bounded Gamma(3,1) samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBoundedGamma());
        }
        System.out.println();
    }
}