import java.util.Random;

/**
 * PROBLEM: Bivariate Normal Sampling
 * * Sample coordinate pairs from a standard bivariate normal distribution with zero correlation, 
 * restricted to a diamond-shaped spatial domain: $|x| + |y| \le 1.5$.
 * * Strategy: Multi-Variable Gaussian Sieve
 * Use two independent standard normal random variables generated via `rand.nextGaussian()` as the joint 
 * proposal distribution. Evaluate the candidate coordinates against the linear diamond inequality boundary. 
 * Reject the vector step completely if it falls outside the boundary lines.
 * * Complexity:
 * Time Complexity: $O(1)$ average execution time per valid draw.
 * Space Complexity: $O(1)$ constant overhead footprint.
 */
public class BivariateNormalSampling {
    private static final Random rand = new Random();

    public static double[] sampleBoundedBivariateNormal() {
        while (true) {
            double x = rand.nextGaussian(); // Independent proposal X
            double y = rand.nextGaussian(); // Independent proposal Y
            
            // Linear spatial filter constraint
            if (Math.abs(x) + Math.abs(y) <= 1.5) {
                return new double[]{x, y}; // Valid joint coordinate pair found
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Diamond-bounded Bivariate Normal Samples:");
        for (int i = 0; i < 3; i++) {
            double[] pair = sampleBoundedBivariateNormal();
            System.out.printf("Sample #%d -> X: %.4f, Y: %.4f%n", i + 1, pair[0], pair[1]);
        }
    }
}