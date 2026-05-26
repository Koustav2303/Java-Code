public class TrailingZeroes {
    public static int trailingZeroes(int n) {
        int count = 0;
        
        // Keep dividing by 5, then 25, then 125, etc. to count all factors of 5
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        
        return count;
    }

    public static void main(String[] args) {
        int n = 25; 
        System.out.println("Number of trailing zeroes in " + n + "! is: " + trailingZeroes(n));
    }
}