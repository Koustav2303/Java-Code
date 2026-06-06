/**
 * PROBLEM: Water and Jug Problem
 * * You are given two jugs with capacities x and y liters. There is an infinite water supply. 
 * Determine whether it is possible to measure exactly targetLiters using these two jugs.
 * * Strategy: Bézout's Identity GCD Constraint
 * Measuring fluid steps via jug decants follows the Diophantine identity relationship: $m \cdot x + n \cdot y = d$. 
 * According to Bézout's identity, you can measure a target value if and only if the target volume 
 * is less than or equal to the total capacity of both jugs ($x + y$) and is a clean multiple of 
 * their Greatest Common Divisor (GCD).
 */
public class WaterAndJugProblem {
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static boolean canMeasureWater(int x, int y, int targetLiters) {
        // Impossible to measure volume exceeding absolute physical capacity bounds
        if (x + y < targetLiters) return false;
        if (x == targetLiters || y == targetLiters || x + y == targetLiters) return true;

        // Target must be a multiple of the GCD of both jug volumes
        return targetLiters % gcd(x, y) == 0;
    }

    public static void main(String[] args) {
        System.out.println("Can measure 4L with 3L and 5L jugs? " + canMeasureWater(3, 5, 4)); // true
        System.out.println("Can measure 5L with 2L and 6L jugs? " + canMeasureWater(2, 6, 5)); // false
    }
}