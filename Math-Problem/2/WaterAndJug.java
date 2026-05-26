public class WaterAndJug {
    public static boolean canMeasureWater(int x, int y, int z) {
        // If the total capacity is less than z, it's physically impossible
        if (x + y < z) return false;
        // If z is 0, or equals one of the jugs or both combined, it's trivially true
        if (z == 0 || z == x || z == y || z == x + y) return true;
        
        // z must be a multiple of the greatest common divisor of x and y
        return z % gcd(x, y) == 0;
    }

    // Euclidean algorithm to find GCD
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int jug1 = 3, jug2 = 5, target = 4;
        System.out.println("Can measure " + target + " gallons? " + canMeasureWater(jug1, jug2, target));
    }
}