import java.util.Random;

/**
 * PROBLEM: Simpson Distribution Sampling
 * * Sample from an asymmetrical Simpson triangular distribution over the interval [0, 4] 
 * peaking at the specific inflection mode coordinates c = 1.0. The piecewise probability density 
 * function is defined as f(x) = x for 0 <= x <= 1, and f(x) = (4 - x) / 3 for 1 < x <= 4.
 * * Strategy: Piecewise Linear Height Check
 * The maximum absolute height value of this Simpson triangular profile occurs at the mode x = 1.0, 
 * where f(1.0) = 1.0. Set a flat uniform proposal over [0, 4] with an envelope parameter multiplier M = 1.0. 
 * Reject generated points falling above the piecewise linear constraints.
 */
public class SimpsonDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleSimpsonTriangle() {
        double M = 1.0; // Peak mode limit at x = 1
        
        while (true) {
            double x = rand.nextDouble() * 4.0; // Uniform proposal candidate over [0, 4]
            double u = rand.nextDouble() * M;      // Height check threshold
            
            double targetDensity;
            if (x <= 1.0) {
                targetDensity = x; // Rising linear ramp slope
            } else {
                targetDensity = (4.0 - x) / 3.0; // Falling linear decay slope
            }
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Simpson Triangular PDF samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", sampleSimpsonTriangle());
        }
        System.out.println();
    }
}