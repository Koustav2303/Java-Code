import java.util.Random;

/**
 * PROBLEM: Spherical Shell Sampling
 * * Sample points uniformly distributed within a hollow 3D spherical shell 
 * with an inner radius of 3.0 and an outer radius of 5.0.
 * * Strategy: Volumetric Bounding Box Exclusion
 * Generate points inside a 3D cube bounding box spanning [-5.0, 5.0] for all three axes. 
 * Calculate the squared Euclidean distance: `distSq = x^2 + y^2 + z^2`. 
 * Accept the point if it falls within the shell boundaries: 3.0^2 <= distSq <= 5.0^2.
 */
public class SphericalShellSampling {
    private static final Random rand = new Random();

    public static double[] sampleSphericalShell() {
        double innerR = 3.0;
        double outerR = 5.0;
        
        double innerSq = innerR * innerR;
        double outerSq = outerR * outerR;

        while (true) {
            double x = -5.0 + 10.0 * rand.nextDouble();
            double y = -5.0 + 10.0 * rand.nextDouble();
            double z = -5.0 + 10.0 * rand.nextDouble();

            double distanceSquared = x * x + y * y + z * z;
            
            // Volumetric filter condition check
            if (distanceSquared >= innerSq && distanceSquared <= outerSq) {
                return new double[]{x, y, z};
            }
        }
    }

    public static void main(String[] args) {
        double[] pt = sampleSphericalShell();
        double radius = Math.sqrt(pt[0]*pt[0] + pt[1]*pt[1] + pt[2]*pt[2]);
        System.out.printf("Shell vector -> X: %.4f, Y: %.4f, Z: %.4f (Calculated Radius: %.4f)%n", 
            pt[0], pt[1], pt[2], radius);
    }
}