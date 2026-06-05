/**
 * PROBLEM: Pythagorean Triplets
 * * Given a limit, generate and print all primitive Pythagorean Triplets ($a^2 + b^2 = c^2$) 
 * such that $c \le \text{limit}$.
 * * Strategy: Euclid's Triple Generation Formula
 * Generate primitive triplets using coprime integers $m$ and $n$ ($m > n > 0$), where one is even 
 * and the other is odd. Apply the equations:
 * $$a = m^2 - n^2, \quad b = 2mn, \quad c = m^2 + n^2$$
 * * Complexity:
 * Time Complexity: $O(\sqrt{\text{limit}})$
 */
public class PythagoreanTriplets {
    private static int gcd(int a, int b) {
        while (b != 0) { int t = b; b = a % b; a = t; }
        return a;
    }

    public static void generateTriplets(int limit) {
        int m = 2;
        
        while (m * m < limit) {
            for (int n = 1; n < m; n++) {
                // Ensure m and n are coprime and one of them is even
                if ((m - n) % 2 == 1 && gcd(m, n) == 1) {
                    int a = m * m - n * n;
                    int b = 2 * m * n;
                    int c = m * m + n * n;

                    if (c > limit) break;
                    
                    System.out.println("Triplet: " + Math.min(a, b) + ", " + Math.max(a, b) + ", " + c);
                }
            }
            m++;
        }
    }

    public static void main(String[] args) {
        System.out.println("Primitive Triplets up to c = 20:");
        generateTriplets(20); // Expected outputs: [3,4,5], [5,12,13], [8,15,17]
    }
}