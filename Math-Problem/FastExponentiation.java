public class FastExponentiation {
    public static double myPow(double x, int n) {
        long N = n; // Cast to long to handle Integer.MIN_VALUE overflow safely
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double result = 1.0;
        double currentProduct = x;
        
        for (long i = N; i > 0; i /= 2) {
            // If the current power is odd, multiply the result by the current product
            if (i % 2 == 1) {
                result *= currentProduct;
            }
            // Square the product for the next power of 2
            currentProduct *= currentProduct;
        }
        
        return result;
    }

    public static void main(String[] args) {
        double x = 2.0;
        int n = 10;
        System.out.println(x + " raised to the power " + n + " is: " + myPow(x, n));
    }
}