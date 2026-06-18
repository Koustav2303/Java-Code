import java.util.Random;

/**
 * PROBLEM: Ellipsoid Uniform Sampling
 * * Generate random coordinate points uniformly distributed within a 3D ellipsoid space defined 
 * by the geometric inequality boundary equation: (x/a)^2 + (y/b)^2 + (z/c)^2 <= 1. 
 * Assume scaling radii values of a = 3.0, b = 2.0, and c = 1.0.
 * * Strategy: Volumetric Box Exclusion Sieve
 * Sample candidate coordinate vectors inside a 3D rectangular bounding box spanning [-a, a], [-b, b], 
 * and [-c, c] along the respective spatial axes. Reject any generated point vectors that fail 
 * the normalized quadratic ellipsoid boundary check.
 */
public class EllipsoidUniformSampling {
    private static final Random rand = new Random();

    public static double[] sampleEllipsoid() {
        double a = 3.0;
        double b = 2.0;
        double c = 1.0;

        while (true) {
            // Transform uniform ranges to map bounding box limits
            double x = -a + 2.0 * a * rand.nextDouble();
            double y = -b + 2.0 * b * rand.nextDouble();
            double z = -c + 2.0 * c * rand.nextDouble();

            // Ellipsoid membership evaluation
            double metric = (x * x) / (a * a) + (y * y) / (b * b) + (z * z) / (c * c);
            
            if (metric <= 1.0) {
                return new double[]{x, y, z}; // Valid spatial coordinate vector located
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Uniform Ellipsoid Samples:");
        for (int i = 0; i < 3; i++) {
            double[] pt = sampleEllipsoid();
            System.out.printf("Vector #%d -> X: %.4f, Y: %.4f, Z: %.4f%n", i + 1, pt[0], pt[1], pt[2]);
        }
    }
}