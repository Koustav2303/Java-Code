import java.util.Random;

/**
 * PROBLEM: Logistic Distribution Truncated
 * * Sample from a standard Logistic distribution restricted strictly to the symmetric interval [-4, 4]. 
 * The target probability density function is f(x) = e^(-x) / (1 + e^(-x))^2.
 * * Strategy: Bounded Space Peak Enclosure
 * The maximum value of the logistic distribution function occurs at its center mean x = 0, 
 * where f(0) = 1 / (1 + 1)^2 = 0.25. Set a flat uniform proposal distribution over [-4, 4] 
 * with an upper envelope multiplier constant M = 0.25. Reject points falling above the curve.
 */
public class LogisticDistributionTruncated {
    private static final Random rand = new Random();

    public static double sampleTruncatedLogistic() {
        double M = 0.25; // Maximum peak density boundary height
        
        while (true) {
            double x = -4.0 + 8.0 * rand.nextDouble(); // Uniform proposal over [-4, 4]
            double u = rand.nextDouble() * M;
            
            double expTerm = Math.exp(-x);
            double targetDensity = expTerm / Math.pow(1.0 + expTerm, 2);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Truncated Logistic [-4,4] samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleTruncatedLogistic());
        }
        System.out.println();
    }
}