public class BitwiseAndRange {
    public static int rangeBitwiseAnd(int left, int right) {
        // Keep clearing the lowest set bit of 'right' until it's <= 'left'
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }

    public static void main(String[] args) {
        int left = 5;  // 101
        int right = 7; // 111
        // Range: 5 (101), 6 (110), 7 (111). Common prefix is 100 (which is 4)
        
        System.out.println("Bitwise AND of range [" + left + ", " + right + "]: " + rangeBitwiseAnd(left, right));
    }
}