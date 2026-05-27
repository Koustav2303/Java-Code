public class NthMagicalNumber {
    public static int nthMagicalNumber(int n, int a, int b) {
        long MOD = 1000000007;
        long L = lcm(a, b);
        
        long low = Math.min(a, b);
        long high = (long) n * Math.min(a, b);
        
        while (low < high) {
            long mid = low + (high - low) / 2;
            
            // Inclusion-Exclusion Principle: Multiples of A + Multiples of B - Multiples of both (LCM)
            long magicalCount = (mid / a) + (mid / b) - (mid / L);
            
            if (magicalCount < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        
        return (int) (low % MOD);
    }
    
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    
    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    public static void main(String[] args) {
        int n = 4, a = 2, b = 3;
        System.out.println(n + "th magical number: " + nthMagicalNumber(n, a, b)); // 6 (2, 3, 4, 6)
    }
}