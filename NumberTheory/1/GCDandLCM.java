/**
 * PROBLEM: GCD and LCM
 * * Given two positive integers a and b, find their Greatest Common Divisor (GCD) 
 * and Least Common Multiple (LCM).
 * * Strategy: Euclidean Reduction
 * The Euclidean algorithm relies on the principle that the GCD of two numbers also divides 
 * their difference. Recursively compute gcd(a, b) = gcd(b, a % b) until b becomes 0. 
 * Calculate LCM using the identity: LCM(a, b) = (a * b) / GCD(a, b).
 * * Complexity:
 * Time Complexity: O(log(min(a, b)))
 * Space Complexity: O(1) auxiliary loop space.
 */
public class GCDandLCM {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        if (a == 0 || b == 0) return 0;
        // Divide first to prevent potential integer overflow during multiplication
        return (a / gcd(a, b)) * b;
    }

    public static void main(String[] args) {
        int a = 24, b = 36;
        System.out.println("GCD of " + a + " and " + b + " is: " + gcd(a, b)); // 12
        System.out.println("LCM of " + a + " and " + b + " is: " + lcm(a, b)); // 72
    }
}