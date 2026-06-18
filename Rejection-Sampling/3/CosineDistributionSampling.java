import java.util.Random;

/**
 * PROBLEM: Cosine Distribution Sampling
 * * Sample from a Cosine distribution over the symmetric interval [-pi/2, pi/2]. 
 * The target probability density function is f(x) = 0.5 * cos(x).
 * * Strategy: Central Peak Uniform Enclosure
 * The maximum value of the target function occurs at the center mode x = 0, where f(0) = 0.5. 
 * Establish a uniform proposal distribution over [-pi/2, pi/2] and scale the vertical envelope 
 * bound multiplier to M = 0.5. Reject any candidate points falling above the cosine profile curve.
 */
public class CosineDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleCosine() {
        double M = 0.5; // Central peak ceiling limit
        
        while (true) {
            // Uniform proposal candidate over [-pi/2, pi/2]
            double x = -Math.PI / 2.0 + Math.PI * rand.nextDouble();
            double u = rand.nextDouble() * M;
            
            double targetDensity = 0.5 * Math.cos(x);
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Cosine distribution samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleCosine());
        }
        System.out.println();
    }
}