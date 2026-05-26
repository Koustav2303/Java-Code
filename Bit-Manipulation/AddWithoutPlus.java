public class AddWithoutPlus {
    public static int getSum(int a, int b) {
        while (b != 0) {
            // Calculate the carry (where both bits are 1)
            int carry = (a & b) << 1;
            
            // Add without carrying (XOR)
            a = a ^ b;
            
            // Assign carry to b for the next iteration
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        int a = 15;
        int b = 25;
        System.out.println(a + " + " + b + " = " + getSum(a, b));
    }
}