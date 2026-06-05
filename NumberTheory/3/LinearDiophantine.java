/**
 * PROBLEM: Linear Diophantine Equation
 * * Determine if the equation ax + by = c has an integer solution pair (x, y). 
 * If a solution exists, extract the base primitive solution coefficients.
 * * Strategy: Bézout's Identity Filter
 * A linear Diophantine equation has an integer solution if and only if the constant term $c$ 
 * is a multiple of the Greatest Common Divisor (GCD) of $a$ and $b$: $c \pmod{\text{gcd}(a, b)} == 0$. 
 * Compute the baseline coefficients using the Extended Euclidean Algorithm, then scale the values by $c / \text{gcd}(a, b)$.
 */
public class LinearDiophantine {
    static class Solution {
        boolean possible;
        int x, y, gcd;
        Solution(boolean p, int x, int y, int g) { this.possible = p; this.x = x; this.y = y; this.gcd = g; }
    }

    private static int[] extGCD(int a, int b) {
        if (b == 0) return new int[]{1, 0, a};
        int[] prev = extGCD(b, a % b);
        return new int[]{prev[1], prev[0] - (a / b) * prev[1], prev[2]};
    }

    public static Solution solveDiophantine(int a, int b, int c) {
        int[] gcdData = extGCD(Math.abs(a), Math.abs(b));
        int x0 = gcdData[0], y0 = gcdData[1], g = gcdData[2];

        // System condition check via Bézout's identity filter
        if (c % g != 0) {
            return new Solution(false, 0, 0, g);
        }

        // Scale primitive solution coefficients up to match constant c
        int scaleFactor = c / g;
        int finalX = x0 * scaleFactor * (a < 0 ? -1 : 1);
        int finalY = y0 * scaleFactor * (b < 0 ? -1 : 1);

        return new Solution(true, finalX, finalY, g);
    }

    public static void main(String[] args) {
        Solution res = solveDiophantine(4, 6, 10); // 4x + 6y = 10 -> Solvable, gcd(4,6)=2, 10%2=0
        System.out.println("Solvable? " + res.possible + " Base Link Configuration: x=" + res.x + ", y=" + res.y);
    }
}