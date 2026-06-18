import java.util.Random;

/**
 * PROBLEM: Conditional Probability Sampling
 * * Given independent continuous variables X and Y distributed uniformly over [0, 10], 
 * sample values from their joint space subject to the strict conditional constraint: X + Y <= 8.
 * * Strategy: Structural State Pruning
 * Naively assigning variables strips away true underlying dependency densities. 
 * Sample independent points across the full uniform domain workspace matrix. 
 * Validate the conditional statement; if it returns false, prune the state and repeat.
 */
public class ConditionalProbabilitySampling {
    private static final Random rand = new Random();

    public static double[] sampleConditional() {
        while (true) {
            double x = rand.nextDouble() * 10.0;
            double y = rand.nextDouble() * 10.0;

            // Conditional state pruning filter constraint
            if (x + y <= 8.0) {
                return new double[]{x, y};
            }
        }
    }

    public static void main(String[] args) {
        double[] sample = sampleConditional();
        System.out.printf("Conditional match validation sample -> X: %.4f, Y: %.4f (Sum: %.4f)%n", 
            sample[0], sample[1], (sample[0] + sample[1]));
    }
}