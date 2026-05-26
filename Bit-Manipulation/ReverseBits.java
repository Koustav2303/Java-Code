public class ReverseBits {
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // Shift result to the left to make room for the new bit
            result <<= 1;
            // Get the right-most bit of n and add it to result
            result |= (n & 1);
            // Shift n to the right to process the next bit
            n >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        int num = 43261596; 
        System.out.println("Original integer: " + num);
        System.out.println("Reversed bits integer: " + reverseBits(num));
    }
}