public class DivideTwoIntegers {
    public static int divide(int dividend, int divisor) {
        // Handle the only overflow case for 32-bit integers
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        // Determine sign and convert to positive longs to prevent overflow during shifts
        boolean negative = (dividend < 0) ^ (divisor < 0);
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        int quotient = 0;
        
        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor;
            long multiple = 1;
            
            // Keep doubling the divisor until it exceeds the remaining dividend
            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }
            
            // Subtract the largest found chunk and add the multiple to the quotient
            absDividend -= tempDivisor;
            quotient += multiple;
        }

        return negative ? -quotient : quotient;
    }

    public static void main(String[] args) {
        int dividend = 43, divisor = 8;
        System.out.println(dividend + " / " + divisor + " = " + divide(dividend, divisor));
    }
}