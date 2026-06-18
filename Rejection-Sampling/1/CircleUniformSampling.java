import java.util.Random;

/**
 * PROBLEM: Circle Uniform Sampling
 * * Generate random coordinate points uniformly distributed within a unit circle of radius 1 centered at (0,0).
 * * Strategy: Geometric Cartesian Rejection
 * Instead of dealing with structural density distortions caused by naive polar random angles, 
 * sample points inside a square bounding box spanning [-1, 1] for both dimensions. 
 * Reject any coordinate point that fails the Euclidean distance metric constraint: x^2 + y^2 > 1.
 */
public class CircleUniformSampling {
    private static final Random rand = new Random();

    public static double[] sampleCircle() {
        while (true) {
            // Transform standard [0,1) ranges into [-1, 1] spatial proposals
            double x = -1.0 + 2.0 * rand.nextDouble();
            double y = -1.0 + 2.0 * rand.nextDouble();

            // Geometric filtering criterion
            if (x * x + y * y <= 1.0) {
                return new double[]{x, y}; // Valid coordinate vector located
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Uniform Circle Samples:");
        for (int i = 0; i < 3; i++) {
            double[] pt = sampleCircle();
            System.out.printf("Point #%d: X = %.4f, Y = %.4f%n", i + 1, pt[0], pt[1]);
        }
    }
}