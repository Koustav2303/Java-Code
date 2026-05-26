public class UglyNumber {
    public static boolean isUgly(int n) {
        if (n <= 0) return false;
        
        // Keep dividing by allowed prime factors
        for (int factor : new int[] {2, 3, 5}) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        
        return n == 1;
    }

    public static void main(String[] args) {
        int num = 14; 
        System.out.println("Is " + num + " an ugly number? " + isUgly(num)); 
        // False, because it has 7 as a prime factor
        
        int num2 = 30;
        System.out.println("Is " + num2 + " an ugly number? " + isUgly(num2)); 
        // True, factors are 2, 3, 5
    }
}