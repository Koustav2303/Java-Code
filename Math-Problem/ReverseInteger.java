public class ReverseInteger {
    public static int reverse(int x) {
        int reversed = 0;
        
        while (x != 0) {
            int pop = x % 10; // Pop the last digit
            x /= 10;
            
            // Check for overflow before multiplying by 10
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            
            reversed = (reversed * 10) + pop;
        }
        
        return reversed;
    }

    public static void main(String[] args) {
        int num = 12345;
        System.out.println("Original: " + num);
        System.out.println("Reversed: " + reverse(num));
    }
}