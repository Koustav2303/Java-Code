import java.util.Random;

/**
 * PROBLEM: Polynomial PDF Sampling
 * * Sample from a polynomial target probability density function $f(x) = 3x^2$ defined over the interval $[0, 1]$.
 * * Strategy: Bounded Upper Limit Envelope
 * The maximum value of $f(x)$ on the interval $[0,1]$ occurs at the upper bound $x = 1$, where $f(1) = 3.0$. 
 * Use a flat uniform proposal distribution $g(x) = 1.0$ and set the envelope multiplier to $M = 3.0$. 
 * Generate a candidate, choose a random height under $M$, and reject it if it falls above the target curve.
 */
public class PolynomialPDFSampling {
    private static final Random rand = new Random();

    public static double samplePolynomial() {
        double M = 3.0; // Peak boundary limit constant
        
        while (true) {
            double x = rand.nextDouble(); // Uniform proposal candidate over [0, 1]
            double u = rand.nextDouble() * M;
            
            if (u <= 3.0 * x * x) { // Target density f(x) validation check
                return x;
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Polynomial PDF (3x^2) samples: ");
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.4f ", samplePolynomial());
        }
        System.out.println();
    }
}