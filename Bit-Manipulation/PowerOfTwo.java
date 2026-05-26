public class PowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        // A number must be greater than 0, and removing its lowest set bit should yield 0
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        int num1 = 16;
        int num2 = 18;
        
        System.out.println("Is " + num1 + " a power of two? " + isPowerOfTwo(num1));
        System.out.println("Is " + num2 + " a power of two? " + isPowerOfTwo(num2));
    }
}