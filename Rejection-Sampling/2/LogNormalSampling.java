import java.util.Random;

/**
 * PROBLEM: Log-Normal Sampling
 * * Sample from a standard log-normal distribution ($\mu = 0, \sigma = 1$) restricted to the interval $[0.5, 2.5]$. 
 * The target probability density function is $f(x) = \frac{1}{x \sqrt{2\pi}} e^{-\frac{(\ln x)^2}{2}}$.
 * * Strategy: Bounded Space Maximum Analyzer
 * On the restricted interval $[0.5, 2.5]$, the function reaches its peak value near $x = 0.5$ or $x = 1.0$. 
 * Evaluating $f(1.0)$ yields $\frac{1}{\sqrt{2\pi}} \approx 0.3989$. Set a flat uniform proposal over $[0.5, 2.5]$ 
 * with an envelope multiplier $M = 0.45$. Reject points that fall above the curve.
 */
public class LogNormalSampling {
    private static final Random rand = new Random();

    public static double sampleBoundedLogNormal() {
        double M = 1.0 / Math.sqrt(2.0 * Math.PI); // Upper density ceiling limit estimate
        
        while (true) {
            // Uniform proposal candidate over the interval [0.5, 2.5]
            double x = 0.5 + 2.0 * rand.nextDouble();
            double u = rand.nextDouble() * M;
            
            double targetDensity = (1.0 / (x * Math.sqrt(2.0 * Math.PI))) * Math.exp(-Math.pow(Math.log(x), 2) / 2.0);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Truncated Log-Normal samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleBoundedLogNormal());
        }
        System.out.println();
    }
}