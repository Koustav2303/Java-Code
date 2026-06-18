import java.util.Random;

/**
 * PROBLEM: VonMisesDistributionSampling
 * * Sample from an unnormalized circular Von Mises distribution over the domain interval [-pi, pi] 
 * with mean direction mu = 0 and concentration parameter kappa = 1. 
 * The target density function is given by: f(x) = e^(cos(x)).
 * * Strategy: Central Periodic Mode Envelope
 * The maximum value of the periodic cosine function inside the exponent occurs at x = 0, 
 * yielding a maximum peak height of f(0) = e^1 ~ 2.7183. Establish a uniform proposal distribution 
 * over [-pi, pi] and scale the vertical envelope bound constant to M = e. Reject out-of-bounds points.
 */
public class VonMisesDistributionSampling {
    private static final Random rand = new Random();

    public static double sampleVonMises() {
        double M = Math.exp(1.0); // Exact maximum peak boundary ceiling constant
        
        while (true) {
            // Uniform proposal candidate over circular domain [-pi, pi]
            double x = -Math.PI + 2.0 * Math.PI * rand.nextDouble();
            double u = rand.nextDouble() * M;
            
            double targetDensity = Math.exp(Math.cos(x));
            
            if (u <= targetDensity) {
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Circular Von Mises samples: ");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%.4f ", sampleVonMises());
        }
        System.out.println();
    }
}