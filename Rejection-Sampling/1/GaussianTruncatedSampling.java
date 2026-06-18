import java.util.Random;

/**
 * PROBLEM: Truncated Gaussian Sampling
 * * Sample from a standard normal distribution N(0, 1) restricted strictly to the positive range [1.5, infinity).
 * * Strategy: Bounded Gaussian Filter
 * Use a standard normal generator (via the Box-Muller transformation or nextGaussian()) as the proposal distribution. 
 * Reject any candidate sample that falls below the lower threshold boundary of 1.5.
 */
public class GaussianTruncatedSampling {
    private static final Random rand = new Random();

    public static double sampleTruncatedGaussian(int lowerBound) {
        while (true) {
            double sampleCandidate = rand.nextGaussian(); // Proposal distribution
            
            // Rejection condition check
            if (sampleCandidate >= lowerBound) {
                return sampleCandidate;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Truncated Gaussian samples >= 1.5: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleTruncatedGaussian((int) 1.5));
        }
        System.out.println();
    }
}