public class SquareRoot {
    public static int mySqrt(int x) {
        if (x < 2) return x;
        
        long low = 1;
        long high = x / 2;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long square = mid * mid;
            
            if (square == x) {
                return (int) mid;
            } else if (square < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        // Return high because we want the truncated integer root
        return (int) high;
    }

    public static void main(String[] args) {
        int num = 8; // Sqrt is 2.82842... truncates to 2
        System.out.println("Integer square root of " + num + " is: " + mySqrt(num));
    }
}